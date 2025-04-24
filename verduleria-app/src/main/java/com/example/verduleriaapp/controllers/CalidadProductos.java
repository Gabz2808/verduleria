package com.example.verduleriaapp.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import com.example.verduleriaapp.models.CalidadProducto;
import com.example.verduleriaapp.utils.ConexionOracle;

public class CalidadProductos implements Initializable {

    @FXML private TableView<CalidadProducto> tablaCalidad;
    @FXML private TableColumn<CalidadProducto, Integer> colIdCalidad;
    @FXML private TableColumn<CalidadProducto, String> colDescripcion;
    @FXML private TableColumn<CalidadProducto, Date> colFecha;
    @FXML private TableColumn<CalidadProducto, String> colNombreProducto;

    private ObservableList<CalidadProducto> listaCalidad = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colIdCalidad.setCellValueFactory(new PropertyValueFactory<>("idCalidad"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colNombreProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));

        cargarDatos();
    }

    private void cargarDatos() {
        try (Connection connection = new ConexionOracle().getConnection()) {
            if (connection == null) {
                System.err.println("❌ No se pudo obtener conexión a la base de datos.");
                return;
            }

            String sql = "SELECT * FROM V_CALIDAD_PRODUCTOS_NOMBRES";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                listaCalidad.add(new CalidadProducto(
                        resultSet.getInt("ID_CALIDAD"),
                        resultSet.getString("DESCRIPCION"),
                        resultSet.getInt("ID_PRODUCTO"),
                        resultSet.getString("NOMBRE_PRODUCTO")
                ));
            }

            tablaCalidad.setItems(listaCalidad);
        } catch (SQLException e) {
            System.err.println("❌ Error al cargar datos de calidad: " + e.getMessage());
        }
    }
}
