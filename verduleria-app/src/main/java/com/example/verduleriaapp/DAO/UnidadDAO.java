package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Unidad;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnidadDAO {

    private Connection connection;

    public UnidadDAO() {
        // Aquí deberías usar la conexión que ya tienes establecida, como la clase ConexionOracle
        this.connection = new ConexionOracle().getConnection();
    }

    public List<Unidad> obtenerUnidades() throws SQLException {
        List<Unidad> unidades = new ArrayList<>();
        String query = "SELECT * FROM UNIDADES";  // Reemplaza "UNIDAD" por el nombre correcto de tu tabla

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("ID_UNIDAD");
                String nombre = rs.getString("NOMBRE");
                unidades.add(new Unidad(id, nombre));
            }
        }

        return unidades;
    }
}
