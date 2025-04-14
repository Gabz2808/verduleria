package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.HistorialStock;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistorialStockDAO {
    private Connection connection;

    public HistorialStockDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void agregarHistorialStock(Long idProducto, int cantidadAnterior, int cantidadNueva, Date fechaCambio) throws SQLException {
        String sql = "{call AGREGAR_HISTORIAL_STOCK(?,?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setLong(1, idProducto);
            stmt.setInt(2, cantidadAnterior);
            stmt.setInt(3, cantidadNueva);
            stmt.setDate(4, new java.sql.Date(fechaCambio.getTime()));
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar historial de stock: " + e.getMessage());
            throw e;
        }
    }

    public List<HistorialStock> obtenerHistorialStock(Long idProducto) throws SQLException {
        List<HistorialStock> historial = new ArrayList<>();
        String sql = "SELECT * FROM HISTORIAL_STOCK WHERE ID_PRODUCTO = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idProducto);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    HistorialStock hs = new HistorialStock();
                    hs.setIdHistorialStock(rs.getLong("ID_HISTORIAL_STOCK"));
                    hs.setIdProducto(rs.getLong("ID_PRODUCTO"));
                    hs.setCantidadAnterior(rs.getInt("CANTIDAD_ANTERIOR"));
                    hs.setCantidadNueva(rs.getInt("CANTIDAD_NUEVA"));
                    hs.setFechaCambio(rs.getDate("FECHA_CAMBIO"));
                    historial.add(hs);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener historial de stock: " + e.getMessage());
            throw e;
        }
        return historial;
    }
}
