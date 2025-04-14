package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Reportes;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportesDAO {
    private Connection connection;

    public ReportesDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void crearReporte(String tipo, Date fechaGenerado, String descripcion) throws SQLException {
        String sql = "{call CREAR_REPORTE(?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, tipo);
            if (fechaGenerado != null) {
                stmt.setDate(2, new java.sql.Date(fechaGenerado.getTime()));
            } else {
                stmt.setNull(2, Types.DATE);
            }
            stmt.setString(3, descripcion);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al crear reporte: " + e.getMessage());
            throw e;
        }
    }

    public void eliminarReporte(int idReporte) throws SQLException {
        String sql = "{call ELIMINAR_REPORTE(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idReporte);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar reporte: " + e.getMessage());
            throw e;
        }
    }

    public void actualizarReporte(int idReporte, String tipo, Date fechaGenerado, String descripcion) throws SQLException {
        String sql = "{call ACTUALIZAR_REPORTE(?,?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idReporte);
            stmt.setString(2, tipo);
            if (fechaGenerado != null) {
                stmt.setDate(3, new java.sql.Date(fechaGenerado.getTime()));
            } else {
                stmt.setNull(3, Types.DATE);
            }
            stmt.setString(4, descripcion);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar reporte: " + e.getMessage());
            throw e;
        }
    }

    public List<Reportes> obtenerReportes() throws SQLException {
        List<Reportes> reportes = new ArrayList<>();
        String sql = "SELECT * FROM REPORTES";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reportes r = new Reportes();
                r.setIdReporte(rs.getLong("ID_REPORTE"));
                r.setTipo(rs.getString("TIPO"));
                r.setFechaGenerado(rs.getDate("FECHA_GENERADO"));
                r.setDescripcion(rs.getString("DESCRIPCION"));
                reportes.add(r);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener reportes: " + e.getMessage());
            throw e;
        }
        return reportes;
    }
}
