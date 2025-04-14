package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Roles;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolesDAO {
    private Connection connection;

    public RolesDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void agregarRol(String nombreRol) throws SQLException {
        String sql = "{call AGREGAR_ROL(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, nombreRol);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar rol: " + e.getMessage());
            throw e;
        }
    }

    public void eliminarRol(int idRol) throws SQLException {
        String sql = "{call ELIMINAR_ROL(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idRol);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar rol: " + e.getMessage());
            throw e;
        }
    }

    public List<Roles> obtenerRoles() throws SQLException {
        List<Roles> roles = new ArrayList<>();
        String sql = "SELECT * FROM ROLES";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Roles rol = new Roles();
                rol.setIdRol(rs.getLong("ID_ROL"));
                rol.setNombre(rs.getString("NOMBRE_ROL"));
                roles.add(rol);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener roles: " + e.getMessage());
            throw e;
        }
        return roles;
    }
}
