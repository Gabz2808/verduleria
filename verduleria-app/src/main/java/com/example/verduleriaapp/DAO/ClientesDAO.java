package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Clientes;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {
    private Connection connection;

    public ClientesDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    // Método para agregar un cliente
    public void agregarCliente(Clientes clientes) throws SQLException {
        String sql = "INSERT INTO CLIENTES (ID_CLIENTE, NOMBRE, CONTACTO, DIRECCION) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, clientes.getIdCliente());
            stmt.setString(2, clientes.getNombre());
            stmt.setString(3, clientes.getContacto());
            stmt.setString(4, clientes.getDireccion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar cliente: " + e.getMessage());
            throw e;
        }
    }

    // Método para eliminar un cliente por su ID
    public void eliminarCliente(Long idCliente) throws SQLException {
        String sql = "DELETE FROM CLIENTES WHERE ID_CLIENTE = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idCliente);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar cliente: " + e.getMessage());
            throw e;
        }
    }

    // Método para actualizar un cliente
    public void actualizarCliente(Clientes clientes) throws SQLException {
        String sql = "UPDATE CLIENTES SET NOMBRE = ?, CONTACTO = ?, DIRECCION = ? WHERE ID_CLIENTE = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, clientes.getNombre());
            stmt.setString(2, clientes.getContacto());
            stmt.setString(3, clientes.getDireccion());
            stmt.setLong(4, clientes.getIdCliente());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar cliente: " + e.getMessage());
            throw e;
        }
    }

    // Método para obtener un cliente por su ID
    public Clientes obtenerClientePorId(Long idCliente) throws SQLException {
        Clientes clientes = null;
        String sql = "SELECT * FROM CLIENTES WHERE ID_CLIENTE = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    clientes = new Clientes();
                    clientes.setIdCliente(rs.getLong("ID_CLIENTE"));
                    clientes.setNombre(rs.getString("NOMBRE"));
                    clientes.setContacto(rs.getString("CONTACTO"));
                    clientes.setDireccion(rs.getString("DIRECCION"));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener cliente por ID: " + e.getMessage());
            throw e;
        }
        return clientes;
    }

    // Método para obtener todos los clientes
    public List<Clientes> obtenerClientes() throws SQLException {
        List<Clientes> clientes = new ArrayList<>();
        String sql = "SELECT * FROM CLIENTES";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Clientes cliente = new Clientes();
                cliente.setIdCliente(rs.getLong("ID_CLIENTE"));
                cliente.setNombre(rs.getString("NOMBRE"));
                cliente.setContacto(rs.getString("CONTACTO"));
                cliente.setDireccion(rs.getString("DIRECCION"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener clientes: " + e.getMessage());
            throw e;
        }
        return clientes;
    }
}