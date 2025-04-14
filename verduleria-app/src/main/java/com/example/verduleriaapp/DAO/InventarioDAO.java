package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Inventario;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {
    private Connection connection;

    public InventarioDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    // Método para agregar un nuevo inventario
    public void agregarInventario(Inventario inventario) throws SQLException {
        String sql = "INSERT INTO INVENTARIO (ID_INVENTARIO, DESCRIPCION) VALUES (?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, inventario.getIdInventario());
            stmt.setString(2, inventario.getDescripcion());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar inventario: " + e.getMessage());
            throw e;
        }
    }

    // Método para eliminar un inventario por ID
    public void eliminarInventario(Long idInventario) throws SQLException {
        String sql = "DELETE FROM INVENTARIO WHERE ID_INVENTARIO = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idInventario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar inventario: " + e.getMessage());
            throw e;
        }
    }

    // Método para actualizar un inventario
    public void actualizarInventario(Inventario inventario) throws SQLException {
        String sql = "UPDATE INVENTARIO SET DESCRIPCION = ? WHERE ID_INVENTARIO = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, inventario.getDescripcion());
            stmt.setLong(2, inventario.getIdInventario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar inventario: " + e.getMessage());
            throw e;
        }
    }

    // Método para obtener un inventario por su ID
    public Inventario obtenerInventarioPorId(Long idInventario) throws SQLException {
        Inventario inventario = null;
        String sql = "SELECT * FROM INVENTARIO WHERE ID_INVENTARIO = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idInventario);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    inventario = new Inventario();
                    inventario.setIdInventario(rs.getLong("ID_INVENTARIO"));
                    inventario.setDescripcion(rs.getString("DESCRIPCION"));
                }
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener inventario por ID: " + e.getMessage());
            throw e;
        }
        return inventario;
    }

    // Método para obtener toda la lista de inventarios
    public List<Inventario> obtenerInventarios() throws SQLException {
        List<Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM INVENTARIO";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Inventario inventario = new Inventario();
                inventario.setIdInventario(rs.getLong("ID_INVENTARIO"));
                inventario.setDescripcion(rs.getString("DESCRIPCION"));
                inventarios.add(inventario);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener inventarios: " + e.getMessage());
            throw e;
        }
        return inventarios;
    }
}