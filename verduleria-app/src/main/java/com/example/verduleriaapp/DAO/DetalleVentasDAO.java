package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.DetalleVentas;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleVentasDAO {
    private Connection connection;

    public DetalleVentasDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void agregarDetalleVenta(Long idVenta, Long idProducto, int cantidad, Double precioUnitario) throws SQLException {
        String sql = "{call AGREGAR_DETALLE_VENTA(?,?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setLong(1, idVenta);
            stmt.setLong(2, idProducto);
            stmt.setInt(3, cantidad);
            stmt.setDouble(4, precioUnitario);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar detalle de venta: " + e.getMessage());
            throw e;
        }
    }

    public void eliminarDetalleVenta(Long idDetalleVenta) throws SQLException {
        String sql = "{call ELIMINAR_DETALLE_VENTA(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setLong(1, idDetalleVenta);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar detalle de venta: " + e.getMessage());
            throw e;
        }
    }

    public List<DetalleVentas> obtenerDetallesVentas(Long idVenta) throws SQLException {
        List<DetalleVentas> detalles = new ArrayList<>();
        String sql = "SELECT * FROM DETALLE_VENTAS WHERE ID_VENTA = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idVenta);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DetalleVentas dv = new DetalleVentas();
                    dv.setIdDetalleVenta(rs.getLong("ID_DETALLE_VENTA"));
                    dv.setIdVenta(rs.getLong("ID_VENTA"));
                    dv.setIdProducto(rs.getLong("ID_PRODUCTO"));
                    dv.setCantidad(rs.getInt("CANTIDAD"));
                    dv.setPrecioUnitario(rs.getDouble("PRECIO_UNITARIO"));
                    detalles.add(dv);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener detalles de venta: " + e.getMessage());
            throw e;
        }
        return detalles;
    }
}
