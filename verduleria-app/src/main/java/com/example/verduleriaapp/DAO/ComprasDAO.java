package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Compras;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComprasDAO {
    private Connection connection;

    public ComprasDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void crearCompras(String proveedor, Double montoTotal, Date fecha) throws SQLException {
        String sql = "{call CREAR_Compras(?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, proveedor);
            stmt.setDouble(2, montoTotal);
            if (fecha != null) {
                stmt.setDate(3, new java.sql.Date(fecha.getTime()));
            } else {
                stmt.setNull(3, Types.DATE);
            }
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al crear Compras: " + e.getMessage());
            throw e;
        }
    }

    public void eliminarCompras(int idCompras) throws SQLException {
        String sql = "{call ELIMINAR_Compras(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idCompras);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar Compras: " + e.getMessage());
            throw e;
        }
    }

    public void actualizarCompras(int idCompras, String proveedor, Double montoTotal, Date fecha) throws SQLException {
        String sql = "{call ACTUALIZAR_Compras(?,?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idCompras);
            stmt.setString(2, proveedor);
            stmt.setDouble(3, montoTotal);
            if (fecha != null) {
                stmt.setDate(4, new java.sql.Date(fecha.getTime()));
            } else {
                stmt.setNull(4, Types.DATE);
            }
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar Compras: " + e.getMessage());
            throw e;
        }
    }

    public List<Compras> obtenerComprass() throws SQLException {
        List<Compras> Comprass = new ArrayList<>();
        String sql = "SELECT * FROM ComprasS";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Compras c = new Compras();
                c.setIdCompra(rs.getLong("ID_Compras"));
                c.setIdProveedor(rs.getLong("ID_PROVEEDOR"));
                c.setTotal(rs.getDouble("MONTO_TOTAL"));
                c.setFechaCompra(rs.getDate("FECHA"));
                Comprass.add(c);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener Comprass: " + e.getMessage());
            throw e;
        }
        return Comprass;
    }
}
