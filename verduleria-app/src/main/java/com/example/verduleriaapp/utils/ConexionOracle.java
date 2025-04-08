package com.example.verduleriaapp.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracle {
    private final String url = "jdbc:oracle:thin:@localhost:1521:xe"; // Cambia "localhost" y "xe" según tu configuración
    private final String user = "USER_JAVA";
    private final String password = "java01";
    private Connection conn; // Almacenar la conexión

    public ConexionOracle() {
        conectar();
    }

    private void conectar() {
        try {
            // Cargar el driver (opcional para Java 6 y 7)
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establecer la conexión
            conn = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("🔌 Conexión cerrada");
            }
        } catch (SQLException e) {
            System.out.println("⚠️ Error al cerrar la conexión");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return conn;
    }
}