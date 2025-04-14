package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Logs;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogsDAO {
    private Connection connection;

    public LogsDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void agregarLog(String descripcion, Timestamp fechaHora) throws SQLException {
        String sql = "{call AGREGAR_LOG(?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, descripcion);
            stmt.setTimestamp(2, fechaHora);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar log: " + e.getMessage());
            throw e;
        }
    }

    public List<Logs> obtenerLogs() throws SQLException {
        List<Logs> logs = new ArrayList<>();
        String sql = "SELECT * FROM LOGS";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Logs log = new Logs();
                log.setIdLog(rs.getLong("ID_LOG"));
                log.setDescripcion(rs.getString("DESCRIPCION"));
                log.setFechaHora(rs.getTimestamp("FECHA_HORA"));
                logs.add(log);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener logs: " + e.getMessage());
            throw e;
        }
        return logs;
    }
}
