package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.InventarioDAO;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class GestionInventarioController {

    @FXML
    private TableView<Map<String, Object>> tablaInventario;
    @FXML
    private TableColumn<Map<String, Object>, Long> colIdInventario;
    @FXML
    private TableColumn<Map<String, Object>, String> colNombreProducto;
    @FXML
    private TableColumn<Map<String, Object>, String> colCategoria;
    @FXML
    private TableColumn<Map<String, Object>, Integer> colStock;
    @FXML
    private TableColumn<Map<String, Object>, String> colUbicacion;

    @FXML
    private TextField txtStock;

    private  ObservableList<Map<String, Object>> listaInventario;
    private  InventarioDAO inventarioDAO;

    public GestionInventarioController(InventarioDAO inventarioDAO) {
        this.inventarioDAO = inventarioDAO;
        this.listaInventario = FXCollections.observableArrayList();
    }
    public GestionInventarioController() {
    }
    @FXML
    public void initialize() {
        inventarioDAO = new InventarioDAO();
        listaInventario = FXCollections.observableArrayList();
        configurarTabla();
        cargarInventario();
    }


    private void configurarTabla() {
        colIdInventario.setCellValueFactory(cellData -> {
            Object value = cellData.getValue().get("ID_PRODUCTO");
            return new SimpleObjectProperty<>(value instanceof Number ? ((Number) value).longValue() : null);
        });

        colNombreProducto.setCellValueFactory(cellData -> {
            Object value = cellData.getValue().get("NOMBRE_PRODUCTO");
            return new SimpleObjectProperty<>(value != null ? value.toString() : null);
        });

        colCategoria.setCellValueFactory(cellData -> {
            Object value = cellData.getValue().get("NOMBRE_CATEGORIA");
            return new SimpleObjectProperty<>(value != null ? value.toString() : null);
        });

        colStock.setCellValueFactory(cellData -> {
            Object value = cellData.getValue().get("STOCK");
            return new SimpleObjectProperty<>(value instanceof Number ? ((Number) value).intValue() : null);
        });

        colUbicacion.setCellValueFactory(cellData -> {
            Object value = cellData.getValue().get("UBICACION");
            return new SimpleObjectProperty<>(value != null ? value.toString() : null);
        });

        tablaInventario.setItems(listaInventario);
    }

    private void cargarInventario() {
        try {
            List<Map<String, Object>> datos = inventarioDAO.obtenerInventarioCompleto();
            listaInventario.setAll(datos);
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar el inventario: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Gestión de Inventario");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
