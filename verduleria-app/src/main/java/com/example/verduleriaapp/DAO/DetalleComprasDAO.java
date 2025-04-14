package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.DetalleCompras;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleComprasDAO {
    private Connection connection;

    public DetalleComprasDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void agregarDetalleCompra(Long idCompra, Long idProducto, int cantidad, Double precioUnitario) throws SQLException {
        String sql = "{call AGREGAR_DETALLE_COMPRA(?,?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setLong(1, idCompra);
            stmt.setLong(2, idProducto);
            stmt.setInt(3, cantidad);
            stmt.setDouble(4, precioUnitario);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar detalle de compra: " + e.getMessage());
            throw e;
        }
    }

    public void eliminarDetalleCompra(Long idDetalleCompra) throws SQLException {
        String sql = "{call ELIMINAR_DETALLE_COMPRA(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setLong(1, idDetalleCompra);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar detalle de compra: " + e.getMessage());
            throw e;
        }
    }

    public List<DetalleCompras> obtenerDetallesCompras(Long idCompra) throws SQLException {
        List<DetalleCompras> detalles = new ArrayList<>();
        String sql = "SELECT * FROM DETALLE_COMPRAS WHERE ID_COMPRA = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idCompra);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DetalleCompras dc = new DetalleCompras();
                    dc.setIdDetalleCompra(rs.getLong("ID_DETALLE_COMPRA"));
                    dc.setIdCompra(rs.getLong("ID_COMPRA"));
                    dc.setIdProducto(rs.getLong("ID_PRODUCTO"));
                    dc.setCantidad(rs.getInt("CANTIDAD"));
                    dc.setPrecioUnitario(rs.getDouble("PRECIO_UNITARIO"));
                    detalles.add(dc);
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener detalles de compra: " + e.getMessage());
            throw e;
        }
        return detalles;
    }
}
