package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.HistorialPrecios;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialPreciosDAO {
    private Connection connection;

    public HistorialPreciosDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void agregarHistorialPrecio(Long idProducto, Double precioAnterior, Double precioNuevo, Date fechaCambio) throws SQLException {
        String sql = "{call AGREGAR_HISTORIAL_PRECIO(?,?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setLong(1, idProducto);
            stmt.setDouble(2, precioAnterior);
            stmt.setDouble(3, precioNuevo);
            stmt.setDate(4, new java.sql.Date(fechaCambio.getTime()));
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar historial de precios: " + e.getMessage());
            throw e;
        }
    }

    public List<HistorialPrecios> obtenerHistorialPrecios(Long idProducto) throws SQLException {
        List<HistorialPrecios> historial = new ArrayList<>();
        String sql = "SELECT * FROM HISTORIAL_PRECIOS WHERE ID_PRODUCTO = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idProducto);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    HistorialPrecios hp = new HistorialPrecios();
                    hp.setIdHistorial(rs.getLong("IdHistorial"));
                    hp.setIdProducto(rs.getLong("ID_PRODUCTO"));
                    hp.setPrecioAnterior(rs.getDouble("PRECIO_ANTERIOR"));
                    hp.setPrecioNuevo(rs.getDouble("PRECIO_NUEVO"));
                    hp.setFechaCambio(rs.getDate("FECHA_CAMBIO"));
                    historial.add(hp);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener historial de precios: " + e.getMessage());
            throw e;
        }
        return historial;
    }
}
