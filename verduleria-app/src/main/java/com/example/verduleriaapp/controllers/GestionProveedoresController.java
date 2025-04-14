package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.models.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class GestionProveedoresController {

    @FXML
    private TableView<Proveedor> tablaProveedores;
    @FXML
    private TableColumn<Proveedor, Number> colId;
    @FXML
    private TableColumn<Proveedor, String> colNombre;
    @FXML
    private TableColumn<Proveedor, String> colContacto;
    @FXML
    private TableColumn<Proveedor, String> colDireccion;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtContacto;
    @FXML
    private TextField txtDireccion;

    private ObservableList<Proveedor> listaProveedores; // Lista que alimentará la tabla

    public void initialize() {
        // Asignar propiedades a las columnas
        colId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colContacto.setCellValueFactory(cellData -> cellData.getValue().contactoProperty());
        colDireccion.setCellValueFactory(cellData -> cellData.getValue().direccionProperty());

        // Inicializar la lista de proveedores
        listaProveedores = FXCollections.observableArrayList();
        cargarProveedores();
        tablaProveedores.setItems(listaProveedores);
    }

    // Simular carga de datos
    private void cargarProveedores() {
        listaProveedores.add(new Proveedor(1, "Proveedor 1", "Contacto 1", "Dirección 1"));
        listaProveedores.add(new Proveedor(2, "Proveedor 2", "Contacto 2", "Dirección 2"));
        listaProveedores.add(new Proveedor(3, "Proveedor 3", "Contacto 3", "Dirección 3"));
    }

    @FXML
    private void handleAgregarProveedor() {
        // Obtener valores del formulario
        String nombre = txtNombre.getText();
        String contacto = txtContacto.getText();
        String direccion = txtDireccion.getText();

        // Validar datos
        if (nombre.isEmpty() || contacto.isEmpty() || direccion.isEmpty()) {
            mostrarAlerta("Por favor, completa todos los campos.", Alert.AlertType.WARNING);
            return;
        }

        // Agregar proveedor a la lista y a la tabla
        listaProveedores.add(new Proveedor(listaProveedores.size() + 1, nombre, contacto, direccion));
        limpiarFormulario();
    }

    // Método para limpiar los campos de texto
    @FXML
    private void limpiarFormulario() {
        txtNombre.clear();
        txtContacto.clear();
        txtDireccion.clear();
    }

    // Mostrar una alerta
    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle("Mensaje");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}