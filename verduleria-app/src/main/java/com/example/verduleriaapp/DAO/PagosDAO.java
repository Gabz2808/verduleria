package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Pagos;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagosDAO {
    private Connection connection;

    public PagosDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void crearPago(Long idVenta, String metodoPago, Double monto) throws SQLException {
        String sql = "{call CREAR_PAGO(?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setLong(1, idVenta);
            stmt.setString(2, metodoPago);
            stmt.setDouble(3, monto);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al crear pago: " + e.getMessage());
            throw e;
        }
    }

    public void eliminarPago(int idPago) throws SQLException {
        String sql = "{call ELIMINAR_PAGO(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idPago);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar pago: " + e.getMessage());
            throw e;
        }
    }

    public void actualizarPago(int idPago, Long idVenta, String metodoPago, Double monto) throws SQLException {
        String sql = "{call ACTUALIZAR_PAGO(?,?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idPago);
            stmt.setLong(2, idVenta);
            stmt.setString(3, metodoPago);
            stmt.setDouble(4, monto);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar pago: " + e.getMessage());
            throw e;
        }
    }

    public List<Pagos> obtenerPagos() throws SQLException {
        List<Pagos> pagos = new ArrayList<>();
        String sql = "SELECT * FROM PAGOS";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pagos p = new Pagos();
                p.setIdPago(rs.getLong("ID_PAGO"));
                p.setIdVenta(rs.getLong("ID_VENTA"));
                p.setMetodoPago(rs.getString("METODO_PAGO"));
                p.setMonto(rs.getDouble("MONTO"));
                pagos.add(p);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener pagos: " + e.getMessage());
            throw e;
        }
        return pagos;
    }
}