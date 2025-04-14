package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Unidad;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UnidadDAO {
    private Connection connection;

    public UnidadDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void crearUnidad(String nombre, String abreviatura) throws SQLException {
        String sql = "{call CREAR_UNIDAD(?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, nombre);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al crear unidad: " + e.getMessage());
            throw e;
        }
    }

    public void eliminarUnidad(int idUnidad) throws SQLException {
        String sql = "{call ELIMINAR_UNIDAD(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idUnidad);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar unidad: " + e.getMessage());
            throw e;
        }
    }

    public void actualizarUnidad(int idUnidad, String nombre, String abreviatura) throws SQLException {
        String sql = "{call ACTUALIZAR_UNIDAD(?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idUnidad);
            stmt.setString(2, nombre);
            stmt.setString(3, abreviatura);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar unidad: " + e.getMessage());
            throw e;
        }
    }

    public List<Unidad> obtenerUnidades() throws SQLException {
        List<Unidad> unidades = new ArrayList<>();
        String sql = "SELECT * FROM UNIDADES";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Unidad u = new Unidad();
                u.setId(rs.getInt("ID"));
                u.setNombre(rs.getString("NOMBRE"));
                unidades.add(u);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener unidades: " + e.getMessage());
            throw e;
        }
        return unidades;
    }
}
