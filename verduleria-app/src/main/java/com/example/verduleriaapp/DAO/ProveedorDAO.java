package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Proveedor;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    private Connection connection;

    public ProveedorDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    // Método para agregar un nuevo proveedor
    public void agregarProveedor(Proveedor proveedor) throws SQLException {
        String sql = "INSERT INTO PROVEEDORES (ID_PROVEEDOR, NOMBRE, CONTACTO, DIRECCION) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, proveedor.getId());
            stmt.setString(2, proveedor.getNombre());
            stmt.setString(3, proveedor.getContacto());
            stmt.setString(4, proveedor.getDireccion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar proveedor: " + e.getMessage());
            throw e;
        }
    }

    // Método para eliminar un proveedor por su ID
    public void eliminarProveedor(int idProveedor) throws SQLException {
        String sql = "DELETE FROM PROVEEDORES WHERE ID_PROVEEDOR = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProveedor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar proveedor: " + e.getMessage());
            throw e;
        }
    }

    // Método para actualizar un proveedor
    public void actualizarProveedor(Proveedor proveedor) throws SQLException {
        String sql = "UPDATE PROVEEDORES SET NOMBRE = ?, CONTACTO = ?, DIRECCION = ? WHERE ID_PROVEEDOR = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, proveedor.getNombre());
            stmt.setString(2, proveedor.getContacto());
            stmt.setString(3, proveedor.getDireccion());
            stmt.setInt(4, proveedor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar proveedor: " + e.getMessage());
            throw e;
        }
    }

    // Método para obtener un proveedor por su ID
    public Proveedor obtenerProveedorPorId(int idProveedor) throws SQLException {
        Proveedor proveedor = null;
        String sql = "SELECT * FROM PROVEEDORES WHERE ID_PROVEEDOR = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idProveedor);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    proveedor = new Proveedor(
                            rs.getInt("ID_PROVEEDOR"),
                            rs.getString("NOMBRE"),
                            rs.getString("CONTACTO"),
                            rs.getString("DIRECCION")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener proveedor por ID: " + e.getMessage());
            throw e;
        }
        return proveedor;
    }

    // Método para obtener todos los proveedores
    public List<Proveedor> obtenerProveedores() throws SQLException {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM PROVEEDORES";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                        rs.getInt("ID_PROVEEDOR"),
                        rs.getString("NOMBRE"),
                        rs.getString("CONTACTO"),
                        rs.getString("DIRECCION")
                );
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener proveedores: " + e.getMessage());
            throw e;
        }
        return proveedores;
    }
}