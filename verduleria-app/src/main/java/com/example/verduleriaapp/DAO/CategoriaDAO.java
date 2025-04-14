package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Categoria;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private Connection connection;

    public CategoriaDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void crearCategoria(String nombre, String descripcion) throws SQLException {
        String sql = "{call CREAR_CATEGORIA(?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al crear categoría: " + e.getMessage());
            throw e;
        }
    }

    public void eliminarCategoria(int idCategoria) throws SQLException {
        String sql = "{call ELIMINAR_CATEGORIA(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idCategoria);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar categoría: " + e.getMessage());
            throw e;
        }
    }

    public void actualizarCategoria(int idCategoria, String nombre, String descripcion) throws SQLException {
        String sql = "{call ACTUALIZAR_CATEGORIA(?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idCategoria);
            stmt.setString(2, nombre);
            stmt.setString(3, descripcion);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar categoría: " + e.getMessage());
            throw e;
        }
    }

    public List<Categoria> obtenerCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIAS";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Categoria c = new Categoria();
                c.setId(rs.getInt("ID_CATEGORIA"));
                c.setNombre(rs.getString("NOMBRE"));
                c.setDescripcion(rs.getString("DESCRIPCION"));
                categorias.add(c);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener categorías: " + e.getMessage());
            throw e;
        }
        return categorias;
    }
}
