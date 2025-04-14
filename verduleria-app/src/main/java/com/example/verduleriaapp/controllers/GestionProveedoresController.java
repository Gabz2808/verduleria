package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.models.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class GestionProveedoresController {

    @FXML
    private AnchorPane anchorPaneProveedores;

    @FXML
    private TableView<Proveedor> tablaProveedores;

    @FXML
    private TableColumn<Proveedor, Integer> colId;

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

    private ObservableList<Proveedor> listaProveedores;

    public void initialize() {
        // Inicializar columnas de la tabla
        colId.setCellValueFactory(data -> data.getValue().idProperty().asObject());
        colNombre.setCellValueFactory(data -> data.getValue().nombreProperty());
        colContacto.setCellValueFactory(data -> data.getValue().contactoProperty());
        colDireccion.setCellValueFactory(data -> data.getValue().direccionProperty());

        // Inicializar lista de proveedores
        listaProveedores = FXCollections.observableArrayList();
        tablaProveedores.setItems(listaProveedores);
    }

    @FXML
    private void onAgregarProveedor() {
        String nombre = txtNombre.getText();
        String contacto = txtContacto.getText();
        String direccion = txtDireccion.getText();

        // Supongamos que el ID es autogenerado
        Proveedor nuevoProveedor = new Proveedor(listaProveedores.size() + 1, nombre, contacto, direccion);
        listaProveedores.add(nuevoProveedor);
        limpiarFormulario();
    }

    @FXML
    private void onEditarProveedor() {
        Proveedor seleccionado = tablaProveedores.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            seleccionado.setNombre(txtNombre.getText());
            seleccionado.setContacto(txtContacto.getText());
            seleccionado.setDireccion(txtDireccion.getText());
            tablaProveedores.refresh();
            limpiarFormulario();
        }
    }

    @FXML
    private void onEliminarProveedor() {
        Proveedor seleccionado = tablaProveedores.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaProveedores.remove(seleccionado);
        }
    }

    private void limpiarFormulario() {
        txtNombre.clear();
        txtContacto.clear();
        txtDireccion.clear();
        tablaProveedores.getSelectionModel().clearSelection();
    }
}