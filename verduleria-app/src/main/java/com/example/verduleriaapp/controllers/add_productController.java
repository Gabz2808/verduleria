package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.ProductoDAO;
import com.example.verduleriaapp.DAO.UnidadDAO;
import com.example.verduleriaapp.DAO.CategoriaDAO;
import com.example.verduleriaapp.DAO.ProveedorDAO;
import com.example.verduleriaapp.models.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class add_productController {

    @FXML private TextField nombreField;
    @FXML private ComboBox<Categoria> categoriaComboBox;
    @FXML private ComboBox<Unidad> unidadComboBox;
    @FXML private ComboBox<Proveedor> proveedorComboBox;
    @FXML private Button agregarButton;
    @FXML private Button cancelarButton;

    private Connection conn;
    private CategoriaDAO categoriaDAO;
    private UnidadDAO unidadDAO;
    private ProveedorDAO proveedorDAO;
    private ProductoDAO productoDAO;

    private Producto productoEditable; // Producto actual si está en modo edición

    @FXML
    public void initialize() {
        // El DAO se inicializará luego de inyectar la conexión con setConnection()
    }

    public void setConnection(Connection connection) {
        this.conn = connection;
        this.categoriaDAO = new CategoriaDAO();
        this.unidadDAO = new UnidadDAO();
        this.proveedorDAO = new ProveedorDAO();
        this.productoDAO = new ProductoDAO();

        cargarCategorias();
        cargarUnidades();
        cargarProveedores();
    }

    private void cargarCategorias() {
        try {
            List<Categoria> categorias = categoriaDAO.obtenerCategorias();
            categoriaComboBox.getItems().setAll(categorias);
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar categorías:\n" + e.getMessage());
        }
    }

    private void cargarUnidades() {
        try {
            List<Unidad> unidades = unidadDAO.obtenerUnidades();
            unidadComboBox.getItems().setAll(unidades);
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar unidades:\n" + e.getMessage());
        }
    }

    private void cargarProveedores() {
        try {
            List<Proveedor> proveedores = proveedorDAO.obtenerProveedores();
            proveedorComboBox.getItems().setAll(proveedores);
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar proveedores:\n" + e.getMessage());
        }
    }

    @FXML
    private void handleAgregar() {
        try {
            if (nombreField.getText().isEmpty() || categoriaComboBox.getValue() == null ||
                    unidadComboBox.getValue() == null || proveedorComboBox.getValue() == null) {
                mostrarAlerta("Por favor, complete todos los campos.");
                return;
            }

            String nombre = nombreField.getText().trim();
            int idCategoria = categoriaComboBox.getValue().getId();
            int idUnidad = unidadComboBox.getValue().getId();
            int idProveedor = (int) proveedorComboBox.getValue().getId();

            if (productoEditable == null) {
                // Agregar nuevo producto
                productoDAO.agregarProducto(new Producto(nombre, idCategoria, idUnidad, idProveedor));
                mostrarAlerta("Producto agregado exitosamente.");
            } else {
                // Actualizar producto existente
                productoEditable.setNombre(nombre);
                productoEditable.setIdCategoria(idCategoria);
                productoEditable.setIdUnidad(idUnidad);
                productoEditable.setIdProveedor(idProveedor);
                productoDAO.actualizarProducto(productoEditable);
                mostrarAlerta("Producto actualizado exitosamente.");
            }

            cerrarVentana();
        } catch (SQLException e) {
            mostrarAlerta("Error al guardar el producto:\n" + e.getMessage());
        }
    }

    @FXML
    private void handleCancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) cancelarButton.getScene().getWindow();
        stage.close();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Se llama al editar un producto desde el controller principal
    public void setProducto(Producto producto) {
        try {
            this.productoEditable = producto;
            nombreField.setText(producto.getNombre());
            categoriaComboBox.setValue(categoriaDAO.buscarPorId(producto.getIdCategoria()));
            unidadComboBox.setValue(unidadDAO.buscarPorId(producto.getIdUnidad()));
            proveedorComboBox.setValue(proveedorDAO.buscarPorId(producto.getIdProveedor()));
            agregarButton.setText("Actualizar");
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar datos del producto:\n" + e.getMessage());
        }
    }
}
