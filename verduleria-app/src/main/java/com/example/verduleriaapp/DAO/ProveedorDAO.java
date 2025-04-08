package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Proveedor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.example.verduleriaapp.utils.ConexionOracle;

public class ProveedorDAO {

    private Connection connection;

    public ProveedorDAO() {
        // Aquí deberías usar la conexión que ya tienes establecida, como la clase ConexionOracle
        this.connection = new ConexionOracle().getConnection();
    }

    public List<Proveedor> obtenerProveedores() throws SQLException {
        List<Proveedor> proveedores = new ArrayList<>();
        String query = "SELECT * FROM PROVEEDORES";  // Reemplaza "PROVEEDOR" por el nombre correcto de tu tabla

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID_PROVEEDOR");
                String nombre = rs.getString("NOMBRE");
                String contacto = rs.getString("CONTACTO");
                String direccion = rs.getString("DIRECCION");
                proveedores.add(new Proveedor(id, nombre, contacto, direccion));
            }
        }

        return proveedores;
    }
}
