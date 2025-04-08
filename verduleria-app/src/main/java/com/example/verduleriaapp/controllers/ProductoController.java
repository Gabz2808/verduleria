package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.ProductoDAO;
import com.example.verduleriaapp.models.Producto;
import com.example.verduleriaapp.utils.ConexionOracle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductoController {

    @FXML
    private TableView<Producto> tablaProductos;
    @FXML
    private TableColumn<Producto, Integer> colId;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, Integer> colCategoria;
    @FXML
    private TableColumn<Producto, Integer> colUnidad;
    @FXML
    private TableColumn<Producto, Integer> colProveedor;

    private ObservableList<Producto> productos;
    private ProductoDAO productoDAO;

    // Método para inicializar el controlador
    public void initialize() {
        // Configuración de las columnas de la tabla
        colId.setCellValueFactory(cellData -> cellData.getValue().idProductoProperty().asObject());
        colNombre.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colCategoria.setCellValueFactory(cellData -> cellData.getValue().idCategoriaProperty().asObject());
        colUnidad.setCellValueFactory(cellData -> cellData.getValue().idUnidadProperty().asObject());
        colProveedor.setCellValueFactory(cellData -> cellData.getValue().idProveedorProperty().asObject());

        // Inicializar DAO con la conexión a la base de datos
        productoDAO = new ProductoDAO(); // Pasa la conexión al DAO

        // Cargar los productos desde la base de datos
        cargarProductos();
    }

    // Método para cargar los productos
    private void cargarProductos() {
        try {
            List<Producto> listaProductos = productoDAO.obtenerProductos();
            productos = FXCollections.observableArrayList(listaProductos);
            tablaProductos.setItems(productos); // Vincula los datos a la tabla
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar productos: " + e.getMessage());
        }
    }

    // Método para abrir la ventana de agregar producto
    @FXML
    private void handleAgregar() {
        try {
            // Cargar el formulario de agregar producto
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/components/add_product.fxml"));
            AnchorPane formAgregar = loader.load();

            // Crear una nueva ventana para mostrar el formulario
            Stage stage = new Stage();
            stage.setTitle("Agregar Producto");
            stage.setScene(new Scene(formAgregar));
            stage.show();
        } catch (IOException e) {
            mostrarAlerta("Error al abrir el formulario de agregar producto: " + e.getMessage());
        }
    }

    // Método para manejar la actualización del producto
    @FXML
    private void handleActualizar() {
        mostrarAlerta("Función para actualizar producto en desarrollo.");
    }

    // Método para eliminar un producto
    @FXML
    private void handleEliminar() {
        Producto selectedProducto = tablaProductos.getSelectionModel().getSelectedItem();
        if (selectedProducto != null) {
            try {
                productoDAO.eliminarProducto(selectedProducto.getIdProducto());
                productos.remove(selectedProducto); // Elimina el producto de la lista
                mostrarAlerta("Producto eliminado exitosamente.");
            } catch (SQLException e) {
                mostrarAlerta("Error al eliminar producto: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Por favor, seleccione un producto.");
        }
    }

    // Método para mostrar alertas de información
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
