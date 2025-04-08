package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Categoria;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private Connection connection;

    public CategoriaDAO() {
        // Aquí deberías usar la conexión que ya tienes establecida, como la clase ConexionOracle
        this.connection = new ConexionOracle().getConnection();
    }

    public List<Categoria> obtenerCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String query = "SELECT * FROM CATEGORIAS";  // Reemplaza "CATEGORIA" por el nombre correcto de tu tabla

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID_CATEGORIA");
                String nombre = rs.getString("NOMBRE");
                String descripcion = rs.getString("DESCRIPCION");
                categorias.add(new Categoria(id, nombre, descripcion));
            }
        }

        return categorias;
    }
}
