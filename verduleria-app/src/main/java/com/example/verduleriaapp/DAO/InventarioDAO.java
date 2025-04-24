package com.example.verduleriaapp.DAO;

import com.example.verduleriaapp.models.Inventario;
import com.example.verduleriaapp.utils.ConexionOracle;
import oracle.jdbc.internal.OracleTypes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class InventarioDAO {
    private Connection connection;

    public InventarioDAO() {
        this.connection = new ConexionOracle().getConnection();
    }

    // Obtener inventario completo a través del procedimiento almacenado
    public List<Map<String, Object>> obtenerInventarioCompletoSP() throws SQLException {
        List<Map<String, Object>> inventarioCompleto = new ArrayList<>();
        String sql = "{ CALL sp_obtener_inventario_completo(?) }";

        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.registerOutParameter(1, OracleTypes.CURSOR); // Configurar el cursor de resultados
            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(1)) {
                while (rs.next()) {
                    Map<String, Object> registro = new HashMap<>();
                    registro.put("idProducto", rs.getLong("ID_PRODUCTO"));
                    registro.put("nombreProducto", rs.getString("NOMBRE_PRODUCTO"));
                    registro.put("nombreCategoria", rs.getString("NOMBRE_CATEGORIA"));
                    registro.put("stock", rs.getInt("STOCK"));
                    registro.put("ubicacion", rs.getString("UBICACION"));
                    inventarioCompleto.add(registro);
                }
            }
        }

        return inventarioCompleto;
    }

    // Obtener inventario filtrado por categoría usando el SP correspondiente
    public List<Map<String, Object>> obtenerInventarioPorCategoriaSP(String categoria) throws SQLException {
        List<Map<String, Object>> inventarioPorCategoria = new ArrayList<>();
        String sql = "{ CALL sp_obtener_inventario_por_categoria(?, ?) }";

        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, categoria); // Pasar la categoría al procedimiento
            cs.registerOutParameter(2, OracleTypes.CURSOR); // Configurar el cursor de resultados
            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                while (rs.next()) {
                    Map<String, Object> registro = new HashMap<>();
                    registro.put("idProducto", rs.getLong("ID_PRODUCTO"));
                    registro.put("nombreProducto", rs.getString("NOMBRE_PRODUCTO"));
                    registro.put("nombreCategoria", rs.getString("NOMBRE_CATEGORIA"));
                    registro.put("stock", rs.getInt("STOCK"));
                    registro.put("ubicacion", rs.getString("UBICACION"));
                    inventarioPorCategoria.add(registro);
                }
            }
        }

        return inventarioPorCategoria;
    }

    // Modificar el stock del producto mediante el SP sp_modificar_stock
    public void modificarStockProducto(String nombreProducto, int cantidad, String operacion) throws SQLException {
        String sql = "{ CALL sp_modificar_stock(?, ?, ?) }";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, nombreProducto); // Nombre del producto
            cs.setInt(2, cantidad);         // Cantidad a modificar
            cs.setString(3, operacion);     // Operación: AUMENTAR o DISMINUIR
            cs.execute();
        }
    }

    // Otros métodos mantienen su funcionalidad omitida...

    public void agregarInventario(Inventario inventario) throws SQLException {
        String sql = "INSERT INTO Inventario (STOCK, UBICACION) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, inventario.getStock()); // Establece el valor para STOCK
            ps.setString(2, inventario.getUbicacion()); // Establece el valor para UBICACION

            ps.executeUpdate(); // Ejecuta la consulta
        }
    }

    public void eliminarInventario(int idInventario) throws SQLException {
        String sql = "{CALL ELIMINAR_INVENTARIO(?)}";

        try (CallableStatement cs = connection.prepareCall(sql)) {
            // Establecer el parámetro de entrada
            cs.setInt(1, idInventario);

            // Ejecutar el procedimiento almacenado
            cs.execute();
        }
    }
    public List<Map<String, Object>> obtenerInventarioCompleto() throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();
        String sql = "SELECT ID_PRODUCTO, NOMBRE_PRODUCTO, NOMBRE_CATEGORIA, STOCK, UBICACION FROM VISTA_INVENTARIO_COMPLETO";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> fila = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    fila.put(metaData.getColumnName(i), rs.getObject(i));
                }
                lista.add(fila);
            }
        }

        return lista;
    }

}