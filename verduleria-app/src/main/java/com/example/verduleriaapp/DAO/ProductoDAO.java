package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Producto;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private Connection connection;

    public ProductoDAO() {
        this.connection = new ConexionOracle().getConnection();
    }

    public List<Producto> obtenerProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM PRODUCTOS";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                productos.add(new Producto(
                        rs.getInt("ID_PRODUCTO"),
                        rs.getString("NOMBRE"),
                        rs.getInt("ID_CATEGORIA"),
                        rs.getInt("ID_UNIDAD"),
                        rs.getInt("ID_PROVEEDOR")
                ));
            }
        }
        return productos;
    }

    // Método para agregar producto
    public void agregarProducto(Producto producto) throws SQLException {
        String query = "INSERT INTO PRODUCTOS (NOMBRE, ID_CATEGORIA, ID_UNIDAD, ID_PROVEEDOR) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getIdCategoria());
            stmt.setInt(3, producto.getIdUnidad());
            stmt.setInt(4, producto.getIdProveedor());
            stmt.executeUpdate();
        }
    }

    // Método para actualizar producto
    public void actualizarProducto(Producto producto) throws SQLException {
        String query = "UPDATE PRODUCTOS SET NOMBRE = ?, ID_CATEGORIA = ?, ID_UNIDAD = ?, ID_PROVEEDOR = ? WHERE ID_PRODUCTO = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getIdCategoria());
            stmt.setInt(3, producto.getIdUnidad());
            stmt.setInt(4, producto.getIdProveedor());
            stmt.setInt(5, producto.getIdProducto());
            stmt.executeUpdate();
        }
    }

    // Método para eliminar producto
    public void eliminarProducto(int idProducto) throws SQLException {
        String query = "DELETE FROM PRODUCTOS WHERE ID_PRODUCTO = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idProducto);
            stmt.executeUpdate();
        }
    }
}
