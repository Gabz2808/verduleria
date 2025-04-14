package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Alertas;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertasDAO {
    private Connection connection;

    public AlertasDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void crearAlerta(String tipo, String mensaje, Date fecha) throws SQLException {
        String sql = "{call CREAR_ALERTA(?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, tipo);
            stmt.setString(2, mensaje);
            if (fecha != null) {
                stmt.setDate(3, new java.sql.Date(fecha.getTime()));
            } else {
                stmt.setNull(3, Types.DATE);
            }
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al crear alerta: " + e.getMessage());
            throw e;
        }
    }

    public void eliminarAlerta(int idAlerta) throws SQLException {
        String sql = "{call ELIMINAR_ALERTA(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idAlerta);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar alerta: " + e.getMessage());
            throw e;
        }
    }

    public void actualizarAlerta(int idAlerta, String tipo, String mensaje, Date fecha) throws SQLException {
        String sql = "{call ACTUALIZAR_ALERTA(?,?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idAlerta);
            stmt.setString(2, tipo);
            stmt.setString(3, mensaje);
            if (fecha != null) {
                stmt.setDate(4, new java.sql.Date(fecha.getTime()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar alerta: " + e.getMessage());
            throw e;
        }
    }

    public List<Alertas> obtenerAlertas() throws SQLException {
        List<Alertas> alertas = new ArrayList<>();
        String sql = "SELECT * FROM ALERTAS";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Alertas a = new Alertas();
                a.setIdAlerta(rs.getLong("ID_ALERTA"));
                a.setTipo(rs.getString("TIPO"));
                a.setMensaje(rs.getString("MENSAJE"));
                a.setFecha(rs.getDate("FECHA"));
                alertas.add(a);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener alertas: " + e.getMessage());
            throw e;
        }
        return alertas;
    }
}