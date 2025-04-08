package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.ProductoDAO;
import com.example.verduleriaapp.DAO.UnidadDAO;
import com.example.verduleriaapp.DAO.CategoriaDAO;
import com.example.verduleriaapp.DAO.ProveedorDAO;
import com.example.verduleriaapp.models.Producto;
import com.example.verduleriaapp.models.Unidad;
import com.example.verduleriaapp.models.Categoria;
import com.example.verduleriaapp.models.Proveedor;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

import java.sql.SQLException;
import java.util.List;

public class add_productController {

    @FXML
    private TextField nombreField;
    @FXML
    private ComboBox<Categoria> categoriaComboBox;
    @FXML
    private ComboBox<Unidad> unidadComboBox;
    @FXML
    private ComboBox<Proveedor> proveedorComboBox;
    @FXML
    private Button agregarButton;
    @FXML
    private Button cancelarButton;

    private CategoriaDAO categoriaDAO;
    private UnidadDAO unidadDAO;
    private ProveedorDAO proveedorDAO;
    private ProductoDAO productoDAO;
    // Inicializar el controlador, cargar las opciones en los ComboBox
    @FXML
    public void initialize() {
        categoriaDAO = new CategoriaDAO();  // Asegúrate de tener implementado el DAO
        unidadDAO = new UnidadDAO();
        proveedorDAO = new ProveedorDAO();
        productoDAO = new ProductoDAO();

        cargarCategorias();
        cargarUnidades();
        cargarProveedores();
    }

    // Cargar las categorías en el ComboBox
    private void cargarCategorias() {
        try {
            List<Categoria> categorias = categoriaDAO.obtenerCategorias();
            categoriaComboBox.getItems().addAll(categorias);
        } catch (Exception e) {
            mostrarAlerta("Error al cargar categorías: " + e.getMessage());
        }
    }

    // Cargar las unidades en el ComboBox
    private void cargarUnidades() {
        try {
            List<Unidad> unidades = unidadDAO.obtenerUnidades();
            unidadComboBox.getItems().addAll(unidades);
        } catch (Exception e) {
            mostrarAlerta("Error al cargar unidades: " + e.getMessage());
        }
    }

    // Cargar los proveedores en el ComboBox
    private void cargarProveedores() {
        try {
            List<Proveedor> proveedores = proveedorDAO.obtenerProveedores();
            proveedorComboBox.getItems().addAll(proveedores);
        } catch (Exception e) {
            mostrarAlerta("Error al cargar proveedores: " + e.getMessage());
        }
    }

    // Método para manejar la acción de agregar producto
    @FXML
    private void handleAgregar() throws SQLException {
        if (nombreField.getText().isEmpty() || categoriaComboBox.getSelectionModel().isEmpty() ||
                unidadComboBox.getSelectionModel().isEmpty() || proveedorComboBox.getSelectionModel().isEmpty()) {
            mostrarAlerta("Por favor, complete todos los campos.");
            return;
        }

        // Crear una instancia de Producto con los datos del formulario
        String nombre = nombreField.getText();
        Categoria categoria = categoriaComboBox.getValue();
        Unidad unidad = unidadComboBox.getValue();
        Proveedor proveedor = proveedorComboBox.getValue();

        // Agregar el producto a la base de datos
        productoDAO.agregarProducto(new Producto(nombre, categoria.getId(), unidad.getId(), proveedor.getId()));

        mostrarAlerta("Producto agregado exitosamente.");
    }


    // Método para manejar la acción de cancelar
    @FXML
    private void handleCancelar() {
        nombreField.clear();
        categoriaComboBox.getSelectionModel().clearSelection();
        unidadComboBox.getSelectionModel().clearSelection();
        proveedorComboBox.getSelectionModel().clearSelection();
    }

    // Método para mostrar alertas
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
