package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Usuario;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    private byte[] hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("❌ Error al hashear la contraseña", e);
        }
    }

    public void crearUsuario(String nombre, String correo, String password, int idrol) throws SQLException {
        String sql = "{call CREAR_USUARIO(?,?,?,?)}";
        byte[] hashedPassword = hashPassword(password);

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, correo);
            stmt.setBytes(3, hashedPassword);
            stmt.setInt(4, idrol);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al crear usuario: " + e.getMessage());
            throw e;
        }
    }

    public boolean validarUsuario(String correo, String contrasena) throws SQLException {
        String sql = "{call sp_validar_usuario(?,?,?)}";
        boolean valido = false;
        byte[] hashedPassword = hashPassword(contrasena);

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, correo);
            stmt.setBytes(2, hashedPassword);
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.execute();
            valido = stmt.getInt(3) == 1;
        } catch (SQLException e) {
            System.err.println("❌ Error al validar usuario: " + e.getMessage());
            throw e;
        }

        return valido;
    }

    public boolean validarAdmin(String correo, String contrasena) throws SQLException {
        String sql = "{call sp_validar_admin(?,?,?)}";
        boolean valido = false;
        byte[] hashedPassword = hashPassword(contrasena);

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, correo);
            stmt.setBytes(2, hashedPassword);
            stmt.registerOutParameter(3, Types.INTEGER);
            stmt.execute();
            valido = stmt.getInt(3) == 1;
        } catch (SQLException e) {
            System.err.println("❌ Error al validar admin: " + e.getMessage());
            throw e;
        }

        return valido;
    }

    public void eliminarUsuario(int idUsuario) throws SQLException {
        String sql = "{call ELIMINAR_USUARIO(?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al eliminar usuario: " + e.getMessage());
            throw e;
        }
    }

    public void actualizarUsuario(int idUsuario, String nombre, String correo, String password, int idRol) throws SQLException {
        String sql = "{call ACTUALIZAR_USUARIO(?,?,?,?,?)}";
        byte[] hashedPassword = hashPassword(password);

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setString(2, nombre);
            stmt.setString(3, correo);
            stmt.setBytes(4, hashedPassword);
            stmt.setInt(5, idRol);
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al actualizar usuario: " + e.getMessage());
            throw e;
        }
    }

    public List<Usuario> obtenerUsuarios() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM USUARIOS";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("ID_USUARIO"));
                u.setNombre(rs.getString("NOMBRE"));
                u.setCorreo(rs.getString("CORREO"));
                u.setIdRol(rs.getInt("ID_ROL"));
                u.setFechaCreacion(rs.getTimestamp("FECHA_CREACION"));
                usuarios.add(u);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener usuarios: " + e.getMessage());
            throw e;
        }
        return usuarios;
    }
}