package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.ComprasPorProveedor;
import com.example.verduleriaapp.models.DetalleCompras;
import com.example.verduleriaapp.models.ProductosMasComprados;
import com.example.verduleriaapp.models.ResumenComprasPorFecha;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.time.LocalDate;
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

    public List<ResumenComprasPorFecha> obtenerResumenComprasPorFecha() throws SQLException {
        List<ResumenComprasPorFecha> resumen = new ArrayList<>();
        String sql = "SELECT * FROM VW_RESUMEN_COMPRAS_POR_FECHA";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                LocalDate fechaCompra = rs.getDate("FECHA_COMPRA").toLocalDate();
                Double totalDelDia = rs.getDouble("TOTAL_DEL_DIA");

                ResumenComprasPorFecha r = new ResumenComprasPorFecha(fechaCompra, totalDelDia);
                resumen.add(r);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener el resumen de compras por fecha: " + e.getMessage());
            throw e;
        }

        return resumen;
    }

    public List<ProductosMasComprados> obtenerProductosMasComprados() throws SQLException {
        List<ProductosMasComprados> productos = new ArrayList<>();
        String sql = "SELECT * FROM VW_PRODUCTOS_MAS_COMPRADOS";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String producto = rs.getString("PRODUCTO");
                Integer totalCantidad = rs.getInt("TOTAL_CANTIDAD");
                Double totalVendido = rs.getDouble("TOTAL_VENDIDO");

                ProductosMasComprados p = new ProductosMasComprados(producto, totalCantidad, totalVendido);
                productos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener los productos más comprados: " + e.getMessage());
            throw e;
        }

        return productos;
    }

    public List<ComprasPorProveedor> obtenerComprasPorProveedor() throws SQLException {
        List<ComprasPorProveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM VW_COMPRAS_POR_PROVEEDOR";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String proveedor = rs.getString("PROVEEDOR");
                Integer numeroCompras = rs.getInt("NUMERO_COMPRAS");
                Double totalGastado = rs.getDouble("TOTAL_GASTADO");

                ComprasPorProveedor p = new ComprasPorProveedor(proveedor, numeroCompras, totalGastado);
                proveedores.add(p);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener compras por proveedor: " + e.getMessage());
            throw e;
        }

        return proveedores;
    }
}
