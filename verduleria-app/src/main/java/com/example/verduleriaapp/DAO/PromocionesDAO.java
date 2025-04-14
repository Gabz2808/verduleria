package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Promociones;
import com.example.verduleriaapp.utils.ConexionOracle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PromocionesDAO {
    private Connection connection;

    public PromocionesDAO() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            throw new RuntimeException("❌ Error al inicializar la conexión a la base de datos.");
        }
    }

    public void agregarPromocion(String descripcion, Double descuento, Date fechaInicio, Date fechaFin) throws SQLException {
        String sql = "{call AGREGAR_PROMOCION(?,?,?,?)}";

        try (CallableStatement stmt = connection.prepareCall(sql)) {
            stmt.setString(1, descripcion);
            stmt.setDouble(2, descuento);
            stmt.setDate(3, new java.sql.Date(fechaInicio.getTime()));
            stmt.setDate(4, new java.sql.Date(fechaFin.getTime()));
            stmt.execute();
        } catch (SQLException e) {
            System.err.println("❌ Error al agregar promoción: " + e.getMessage());
            throw e;
        }
    }

    public List<Promociones> obtenerPromociones() throws SQLException {
        List<Promociones> promociones = new ArrayList<>();
        String sql = "SELECT * FROM PROMOCIONES";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Promociones promocion = new Promociones();
                promocion.setIdPromocion(rs.getLong("ID_PROMOCION"));
                promocion.setDescuento(rs.getDouble("DESCUENTO"));
                promocion.setFechaInicio(rs.getDate("FECHA_INICIO"));
                promocion.setFechaFin(rs.getDate("FECHA_FIN"));
                promociones.add(promocion);
            }
        } catch (SQLException e) {
            System.err.println("❌ Error al obtener promociones: " + e.getMessage());
            throw e;
        }
        return promociones;
    }
}
