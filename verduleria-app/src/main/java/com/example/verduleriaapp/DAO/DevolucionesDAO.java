package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Devoluciones;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DevolucionesDAO {
    private Connection connection;

    public DevolucionesDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    // Método para crear una devolución
    public void crearDevolucion(Long idProducto, Long idVenta, Long idCliente, String motivo, int cantidad) throws SQLException {
        String sql = "{call CREAR_DEVOLUCION(?,?,?,?,?)}"; // Usar el procedimiento almacenado correspondiente

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setLong(1, idProducto);
            stmt.setLong(2, idVenta);  // Cambio aplicado
            stmt.setLong(3, idCliente);
            stmt.setString(4, motivo);
            stmt.setInt(5, cantidad);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al crear devolución: " + e.getMessage());
            throw e;
        }
    }

    // Método para eliminar una devolución
    public void eliminarDevolucion(Long idDevolucion) throws SQLException {
        String sql = "{call ELIMINAR_DEVOLUCION(?)}"; // Usar el procedimiento almacenado correspondiente

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setLong(1, idDevolucion);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar devolución: " + e.getMessage());
            throw e;
        }
    }

    // Método para actualizar una devolución
    public void actualizarDevolucion(Long idDevolucion, Long idProducto, Long idVenta, Long idCliente, String motivo, int cantidad) throws SQLException {
        String sql = "{call ACTUALIZAR_DEVOLUCION(?,?,?,?,?,?)}"; // Usar el procedimiento almacenado correspondiente

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setLong(1, idDevolucion);
            stmt.setLong(2, idProducto);
            stmt.setLong(3, idVenta);  // Cambio aplicado
            stmt.setLong(4, idCliente);
            stmt.setString(5, motivo);
            stmt.setInt(6, cantidad);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar devolución: " + e.getMessage());
            throw e;
        }
    }

    // Método para obtener todas las devoluciones
    public List<Devoluciones> obtenerDevoluciones() throws SQLException {
        List<Devoluciones> devoluciones = new ArrayList<>();
        String sql = "SELECT * FROM USER_JAVA.DEVOLUCIONES";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Devoluciones devolucion = new Devoluciones();
                devolucion.setIdDevolucion(rs.getLong("ID_DEVOLUCION"));
                devolucion.setIdProducto(rs.getLong("ID_PRODUCTO"));
                devolucion.setIdVenta(rs.getLong("ID_VENTA")); // Cambio aplicado
                devolucion.setIdCliente(rs.getLong("ID_CLIENTE"));
                devolucion.setMotivo(rs.getString("MOTIVO"));
                devolucion.setCantidad(rs.getInt("CANTIDAD"));
                devoluciones.add(devolucion);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener devoluciones: " + e.getMessage());
            throw e;
        }
        return devoluciones;
    }

    // Método para obtener una devolución por ID
    public Devoluciones obtenerDevolucionPorId(Long idDevolucion) throws SQLException {
        Devoluciones devolucion = null;
        String sql = "SELECT * FROM USER_JAVA.DEVOLUCIONES WHERE ID_DEVOLUCION = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idDevolucion);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    devolucion = new Devoluciones();
                    devolucion.setIdDevolucion(rs.getLong("ID_DEVOLUCION"));
                    devolucion.setIdProducto(rs.getLong("ID_PRODUCTO"));
                    devolucion.setIdVenta(rs.getLong("ID_VENTA")); // Cambio aplicado
                    devolucion.setIdCliente(rs.getLong("ID_CLIENTE"));
                    devolucion.setMotivo(rs.getString("MOTIVO"));
                    devolucion.setCantidad(rs.getInt("CANTIDAD"));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener devolución por ID: " + e.getMessage());
            throw e;
        }
        return devolucion;
    }
}