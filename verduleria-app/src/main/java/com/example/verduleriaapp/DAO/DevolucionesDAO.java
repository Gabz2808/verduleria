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

    // Método para obtener todas las devoluciones desde la vista
    public List<Devoluciones> obtenerDevolucionesDesdeVista() throws SQLException {
        List<Devoluciones> devoluciones = new ArrayList<>();
        String sql = "SELECT * FROM USER_JAVA.V_DEVOLUCIONES"; // Usamos la vista V_DEVOLUCIONES

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Devoluciones devolucion = new Devoluciones();
                devolucion.setIdDevolucion(rs.getLong("ID_DEVOLUCION"));
                devolucion.setIdProducto(rs.getLong("ID_PRODUCTO"));
                devolucion.setIdorden(rs.getLong("ID_ORDEN"));
                devolucion.setIdCliente(rs.getLong("ID_CLIENTE"));
                devolucion.setMotivo(rs.getString("MOTIVO"));
                devolucion.setCantidad(rs.getInt("CANTIDAD"));
                devoluciones.add(devolucion);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener devoluciones desde la vista: " + e.getMessage());
            throw e;
        }
        return devoluciones;
    }
}
