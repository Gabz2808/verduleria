package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Calidad;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CalidadDAO {
    private Connection connection;

    public CalidadDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void crearCalidad(String descripcion) throws SQLException {
        String sql = "{call CREAR_CALIDAD(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, descripcion);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al crear calidad: " + e.getMessage());
            throw e;
        }
    }

    public void eliminarCalidad(int idCalidad) throws SQLException {
        String sql = "{call ELIMINAR_CALIDAD(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idCalidad);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar calidad: " + e.getMessage());
            throw e;
        }
    }

    public void actualizarCalidad(int idCalidad, String descripcion) throws SQLException {
        String sql = "{call ACTUALIZAR_CALIDAD(?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idCalidad);
            stmt.setString(2, descripcion);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar calidad: " + e.getMessage());
            throw e;
        }
    }

    public List<Calidad> obtenerCalidades() throws SQLException {
        List<Calidad> calidades = new ArrayList<>();
        String sql = "SELECT * FROM CALIDADES";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Calidad c = new Calidad();
                c.setIdCalidad(rs.getLong("ID_CALIDAD"));
                c.setDescripcion(rs.getString("DESCRIPCION"));
                calidades.add(c);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener calidades: " + e.getMessage());
            throw e;
        }
        return calidades;
    }
}
