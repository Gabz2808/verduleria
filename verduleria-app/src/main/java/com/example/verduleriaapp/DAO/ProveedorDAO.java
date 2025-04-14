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

    // Obtener todos los proveedores
    public List<Proveedor> obtenerProveedores() throws SQLException {
        List<Proveedor> listaProveedores = new ArrayList<>();
        String query = "SELECT ID, NOMBRE, CONTACTO, DIRECCION FROM PROVEEDORES";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                long id = resultSet.getLong("ID"); // Usa getLong
                String nombre = resultSet.getString("NOMBRE");
                String contacto = resultSet.getString("CONTACTO");
                String direccion = resultSet.getString("DIRECCION");

                listaProveedores.add(new Proveedor(id, nombre, contacto, direccion));
            }
        }
        return listaProveedores;
    }

    // Agregar un nuevo proveedor
    public void agregarProveedor(Proveedor proveedor) throws SQLException {
        String query = "INSERT INTO PROVEEDORES (ID, NOMBRE, CONTACTO, DIRECCION) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, proveedor.getId()); // Usa setLong
            statement.setString(2, proveedor.getNombre());
            statement.setString(3, proveedor.getContacto());
            statement.setString(4, proveedor.getDireccion());
            statement.executeUpdate();
        }
    }

    // Actualizar un proveedor existente
    public void actualizarProveedor(Proveedor proveedor) throws SQLException {
        String query = "UPDATE PROVEEDORES SET NOMBRE = ?, CONTACTO = ?, DIRECCION = ? WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, proveedor.getNombre());
            statement.setString(2, proveedor.getContacto());
            statement.setString(3, proveedor.getDireccion());
            statement.setLong(4, proveedor.getId()); // Usa setLong
            statement.executeUpdate();
        }
    }

    // Eliminar un proveedor por ID
    public void eliminarProveedor(long id) throws SQLException {
        String query = "DELETE FROM PROVEEDORES WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id); // Usa setLong
            statement.executeUpdate();
        }
    }
}

