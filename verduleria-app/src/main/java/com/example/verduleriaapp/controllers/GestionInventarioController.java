package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.HistorialStockDAO;
import com.example.verduleriaapp.DAO.InventarioDAO;
import com.example.verduleriaapp.DAO.ProductoDAO;
import com.example.verduleriaapp.models.Inventario;
import com.example.verduleriaapp.models.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.List;

public class GestionInventarioController {

    @FXML
    private TableView<Inventario> tablaInventario;
    @FXML
    private TableColumn<Inventario, Long> colIdInventario;
    @FXML
    private TableColumn<Inventario, Integer> colIdProducto;
    @FXML
    private TableColumn<Inventario, Integer> colStock;
    @FXML
    private TableColumn<Inventario, String> colUbicacion;

    @FXML
    private ComboBox<Producto> comboProductos;
    @FXML
    private TextField txtStock;
    @FXML
    private TextField txtUbicacion;

    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiar;

    private ObservableList<Inventario> listaInventario;
    private ObservableList<Producto> listaProductos;

    private InventarioDAO inventarioDAO;
    private ProductoDAO productoDAO;
    private HistorialStockDAO historialStockDAO;

    // Método para inicializar los datos al cargar la pantalla
    public void initialize() {
        // Configurar columnas
        colIdInventario.setCellValueFactory(cellData -> cellData.getValue().idInventarioProperty().asObject());
        colIdProducto.setCellValueFactory(cellData -> cellData.getValue().idProductoProperty().asObject());
        colStock.setCellValueFactory(cellData -> cellData.getValue().stockProperty().asObject());
        colUbicacion.setCellValueFactory(cellData -> cellData.getValue().ubicacionProperty());

        // Instanciar los DAOs
        inventarioDAO = new InventarioDAO();
        productoDAO = new ProductoDAO();
        historialStockDAO = new HistorialStockDAO();

        // Cargar datos
        cargarInventario();
        cargarProductos();
    }

    // Cargar Inventario
    private void cargarInventario() {
        try {
            List<Inventario> inventarios = inventarioDAO.obtenerInventarios();
            listaInventario = FXCollections.observableArrayList(inventarios);
            tablaInventario.setItems(listaInventario);
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar inventario: " + e.getMessage());
        }
    }

    // Cargar Productos en ComboBox
    private void cargarProductos() {
        try {
            List<Producto> productos = productoDAO.obtenerProductos();
            listaProductos = FXCollections.observableArrayList(productos);
            comboProductos.setItems(listaProductos);
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar productos: " + e.getMessage());
        }
    }

    // Agregar inventario
    @FXML
    private void handleAgregar() {
        Producto productoSeleccionado = comboProductos.getSelectionModel().getSelectedItem();
        String ubicacion = txtUbicacion.getText();
        try {
            int stock = Integer.parseInt(txtStock.getText());
            if (productoSeleccionado != null && !ubicacion.isEmpty()) {
                Inventario nuevoInventario = new Inventario(0L, productoSeleccionado.getIdProducto(), stock, ubicacion);
                inventarioDAO.agregarInventario(nuevoInventario);
                listaInventario.add(nuevoInventario);
                limpiarFormulario();
                mostrarAlerta("Inventario agregado exitosamente.");
            } else {
                mostrarAlerta("Por favor, complete todos los campos.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("El stock debe ser un número válido.");
        } catch (SQLException e) {
            mostrarAlerta("Error al agregar inventario: " + e.getMessage());
        }
    }

    // Actualizar inventario
    @FXML
    private void handleActualizar() {
        Inventario inventarioSeleccionado = tablaInventario.getSelectionModel().getSelectedItem();
        Producto productoSeleccionado = comboProductos.getSelectionModel().getSelectedItem();
        try {
            int stockNuevo = Integer.parseInt(txtStock.getText());
            String ubicacionNueva = txtUbicacion.getText();

            if (inventarioSeleccionado != null && productoSeleccionado != null) {
                int stockAnterior = inventarioSeleccionado.getStock();
                inventarioSeleccionado.setIdProducto(productoSeleccionado.getIdProducto());
                inventarioSeleccionado.setStock(stockNuevo);
                inventarioSeleccionado.setUbicacion(ubicacionNueva);

                inventarioDAO.actualizarInventario(inventarioSeleccionado);
                historialStockDAO.agregarHistorialStock(productoSeleccionado.getIdProducto(), stockAnterior, stockNuevo, new java.sql.Date(System.currentTimeMillis()));
                tablaInventario.refresh();
                limpiarFormulario();
                mostrarAlerta("Inventario actualizado exitosamente.");
            } else {
                mostrarAlerta("Seleccione un inventario para actualizar.");
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("El stock debe ser un número válido.");
        } catch (SQLException e) {
            mostrarAlerta("Error al actualizar inventario: " + e.getMessage());
        }
    }

    // Eliminar inventario
    @FXML
    private void handleEliminar() {
        Inventario seleccionado = tablaInventario.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try {
                inventarioDAO.eliminarInventario(seleccionado.getIdInventario());
                listaInventario.remove(seleccionado);
                mostrarAlerta("Inventario eliminado exitosamente.");
            } catch (SQLException e) {
                mostrarAlerta("Error al eliminar inventario: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Seleccione un inventario para eliminar.");
        }
    }

    // Limpiar formulario
    @FXML
    private void limpiarFormulario() {
        comboProductos.setValue(null);
        txtStock.clear();
        txtUbicacion.clear();
        tablaInventario.getSelectionModel().clearSelection();
    }

    // Mostrar una alerta informativa
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}