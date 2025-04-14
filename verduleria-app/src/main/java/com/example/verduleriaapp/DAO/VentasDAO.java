package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Ventas;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentasDAO {
    private Connection connection;

    public VentasDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    // Método para crear una nueva venta
    public void crearVenta(Long idCliente, Date fechaVenta, Double total) throws SQLException {
        String sql = "{call CREAR_VENTA(?,?,?)}"; // Reemplazar por el nombre real del procedimiento almacenado

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setLong(1, idCliente);
            stmt.setDate(2, new java.sql.Date(fechaVenta.getTime()));
            stmt.setDouble(3, total);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al crear venta: " + e.getMessage());
            throw e;
        }
    }

    // Método para eliminar una venta
    public void eliminarVenta(Long idVenta) throws SQLException {
        String sql = "{call ELIMINAR_VENTA(?)}"; // Reemplazar por el nombre real del procedimiento almacenado

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setLong(1, idVenta);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar venta: " + e.getMessage());
            throw e;
        }
    }

    // Método para actualizar una venta
    public void actualizarVenta(Long idVenta, Long idCliente, Date fechaVenta, Double total) throws SQLException {
        String sql = "{call ACTUALIZAR_VENTA(?,?,?,?)}"; // Reemplazar por el nombre real del procedimiento almacenado

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setLong(1, idVenta);
            stmt.setLong(2, idCliente);
            stmt.setDate(3, new java.sql.Date(fechaVenta.getTime()));
            stmt.setDouble(4, total);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar venta: " + e.getMessage());
            throw e;
        }
    }

    // Método para obtener todas las ventas
    public List<Ventas> obtenerVentas() throws SQLException {
        List<Ventas> ventas = new ArrayList<>();
        String sql = "SELECT * FROM SER_JAVA.VENTAS";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Ventas venta = new Ventas();
                venta.setIdVenta(rs.getLong("ID_VENTA"));
                venta.setIdCliente(rs.getLong("ID_CLIENTE"));
                venta.setFechaVenta(rs.getDate("FECHA_VENTA"));
                venta.setTotal(rs.getDouble("TOTAL"));
                ventas.add(venta);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener ventas: " + e.getMessage());
            throw e;
        }
        return ventas;
    }

    // Método para obtener una venta específica por ID
    public Ventas obtenerVentaPorId(Long idVenta) throws SQLException {
        Ventas venta = null;
        String sql = "SELECT * FROM SER_JAVA.VENTAS WHERE ID_VENTA = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idVenta);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    venta = new Ventas();
                    venta.setIdVenta(rs.getLong("ID_VENTA"));
                    venta.setIdCliente(rs.getLong("ID_CLIENTE"));
                    venta.setFechaVenta(rs.getDate("FECHA_VENTA"));
                    venta.setTotal(rs.getDouble("TOTAL"));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener venta por ID: " + e.getMessage());
            throw e;
        }
        return venta;
    }
}