package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Gastos;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GastosDAO {
    private Connection connection;

    public GastosDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void crearGasto(String descripcion, Double monto, Date fecha) throws SQLException {
        String sql = "{call CREAR_GASTO(?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, descripcion);
            stmt.setDouble(2, monto);
            if (fecha != null) {
                stmt.setDate(3, new java.sql.Date(fecha.getTime()));
            } else {
                stmt.setNull(3, Types.DATE);
            }
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al crear gasto: " + e.getMessage());
            throw e;
        }
    }

    public void eliminarGasto(int idGasto) throws SQLException {
        String sql = "{call ELIMINAR_GASTO(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idGasto);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar gasto: " + e.getMessage());
            throw e;
        }
    }

    public void actualizarGasto(int idGasto, String descripcion, Double monto, Date fecha) throws SQLException {
        String sql = "{call ACTUALIZAR_GASTO(?,?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idGasto);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, monto);
            if (fecha != null) {
                stmt.setDate(4, new java.sql.Date(fecha.getTime()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar gasto: " + e.getMessage());
            throw e;
        }
    }

    public List<Gastos> obtenerGastos() throws SQLException {
        List<Gastos> gastos = new ArrayList<>();
        String sql = "SELECT * FROM GASTOS";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Gastos g = new Gastos();
                g.setIdGasto(rs.getLong("ID_GASTO"));
                g.setDescripcion(rs.getString("DESCRIPCION"));
                g.setMonto(rs.getDouble("MONTO"));
                g.setFecha(rs.getDate("FECHA"));
                gastos.add(g);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener gastos: " + e.getMessage());
            throw e;
        }
        return gastos;
    }
}