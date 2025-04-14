package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.InventarioDAO;
import com.example.verduleriaapp.models.Inventario;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestionInventarioController {

    @FXML
    private TableView<Map<String, Object>> tablaInventario; // Tabla para mostrar todos los productos
    @FXML
    private TableColumn<Map<String, Object>, Long> colIdInventario; // ID del inventario
    @FXML
    private TableColumn<Map<String, Object>, String> colNombreProducto; // Nombre del producto
    @FXML
    private TableColumn<Map<String, Object>, String> colCategoria; // Categoría del producto
    @FXML
    private TableColumn<Map<String, Object>, Integer> colStock; // Cantidad en el stock
    @FXML
    private TableColumn<Map<String, Object>, String> colUbicacion; // Ubicación dentro del inventario


    @FXML
    private TableColumn<Inventario, String> colNombre;

    @FXML
    private TableColumn<Inventario, Integer> colCantidad;

    @FXML
    private ComboBox<String> comboProductos; // Desplegable para seleccionar productos
    @FXML
    private ComboBox<String> comboCategorias; // Combobox para filtrar por categoría
    @FXML
    private TextField txtStock; // Campo de texto para cantidad en el stock
    @FXML
    private Button btnComprar; // Botón para registrar una compra
    @FXML
    private Button btnVender; // Botón para registrar una venta
    @FXML
    private Button btnDevolver; // Botón para registrar una devolución
    @FXML
    private TextField txtUbicacion;

    private final ObservableList<Map<String, Object>> listaInventario; // Lista de los datos mostrados en la tabla
    private final InventarioDAO inventarioDAO; // DAO para interactuar con la base de datos

    public GestionInventarioController() {
        this.inventarioDAO = new InventarioDAO();
        this.listaInventario = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        try {
            configurarTabla();
            cargarCategorias(); // Cargar listado de categorías
            cargarInventario(); // Cargar el inventario completo
        } catch (SQLException e) {
            mostrarAlerta("Error al inicializar la ventana: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }



    private void cargarCategorias() throws SQLException {
        // Simulando la carga de categorías desde la base de datos
        List<String> categorias = new ArrayList<>();
        categorias.add("Frutas");
        categorias.add("Verduras");
        categorias.add("Lácteos");
        comboCategorias.setItems(FXCollections.observableArrayList(categorias));
    }

    @FXML
    private void filtrarPorCategoria() {
        String categoriaSeleccionada = comboCategorias.getValue();
        if (categoriaSeleccionada == null) {
            mostrarAlerta("Por favor, selecciona una categoría.", Alert.AlertType.WARNING);
            return;
        }

        try {
            List<Map<String, Object>> inventarioFiltrado = inventarioDAO.obtenerInventarioPorCategoriaSP(categoriaSeleccionada);
            listaInventario.setAll(inventarioFiltrado);
        } catch (SQLException e) {
            mostrarAlerta("Error al filtrar el inventario: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleFiltrarPorCategoria() {
        filtrarPorCategoria(); // Llama al método que ya estaba definido para filtrar el inventario
    }
    @FXML
    private void handleAgregar() {
        try {
            // Validar datos del formulario antes de proceder
            String productoSeleccionado = comboProductos.getValue();
            String ubicacion = txtUbicacion.getText();
            String stockTexto = txtStock.getText();

            if (productoSeleccionado == null || productoSeleccionado.isEmpty() ||
                    ubicacion == null || ubicacion.isEmpty() || stockTexto == null || stockTexto.isEmpty()) {
                mostrarAlerta("Todos los campos son obligatorios.", Alert.AlertType.WARNING);
                return;
            }

            int stock = Integer.parseInt(stockTexto); // Intentar convertir el stock a número
            // Aquí puedes crear un nuevo objeto Inventario y guardarlo
            inventarioDAO.agregarInventario(new Inventario(stock, ubicacion));

            cargarInventario(); // Refrescar tabla con datos actualizados
            limpiarFormulario();
            mostrarAlerta("Producto registrado con éxito.", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            mostrarAlerta("El campo de stock debe contener un número válido.", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            mostrarAlerta("Ocurrió un error al guardar el inventario: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }private void configurarTabla() {
        colIdInventario.setCellValueFactory(new PropertyValueFactory<>("idInventario")); // Nombre del atributo en el modelo
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre")); // Nombre del atributo en el modelo
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad")); // Nombre del atributo en el modelo
    }


    @FXML
    private void registrarCompra() {
        modificarStock("AUMENTAR");
    }

    @FXML
    private void registrarVenta() {
        modificarStock("DISMINUIR");
    }

    @FXML
    private void registrarDevolucion() {
        modificarStock("AUMENTAR");
    }

    private void modificarStock(String operacion) {
        // Validar los datos antes de realizar las operaciones.
        String productoSeleccionado = comboProductos.getValue();
        if (productoSeleccionado == null || txtStock.getText().trim().isEmpty()) {
            mostrarAlerta("Por favor, selecciona un producto y proporciona una cantidad válida.", Alert.AlertType.WARNING);
            return;
        }

        try {
            int cantidad = Integer.parseInt(txtStock.getText());
            inventarioDAO.modificarStockProducto(productoSeleccionado, cantidad, operacion);
            cargarInventario(); // Actualizar la tabla del inventario
            limpiarFormulario(); // Limpiar formulario
            mostrarAlerta("Operación de " + operacion + " realizada con éxito.", Alert.AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            mostrarAlerta("La cantidad debe ser un número válido.", Alert.AlertType.ERROR);
        } catch (SQLException e) {
            mostrarAlerta("Error al realizar la operación: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void limpiarFormulario() {
        comboProductos.setValue(null);
        txtStock.clear();
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("Gestión de Inventario");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
    @FXML
    private void handleEliminar() {
        try {
            // Obtener el objeto seleccionado en la tabla
            Inventario inventarioSeleccionado = (Inventario) tablaInventario.getSelectionModel().getSelectedItem();

            if (inventarioSeleccionado == null) {
                mostrarAlerta("Por favor, selecciona un registro para eliminar.", Alert.AlertType.WARNING);
                return;
            }

            // Confirmar eliminación
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar Eliminación");
            confirmacion.setHeaderText("Estás a punto de eliminar un registro.");
            confirmacion.setContentText("¿Deseas continuar?");
            if (confirmacion.showAndWait().get() != ButtonType.OK) {
                return;
            }

            // Llamar al DAO para eliminar el registro
            InventarioDAO inventarioDAO = new InventarioDAO();
            inventarioDAO.eliminarInventario(inventarioSeleccionado.getIdInventario());

            cargarInventario(); // Actualizar tabla
            mostrarAlerta("Registro eliminado correctamente.", Alert.AlertType.INFORMATION);

        } catch (SQLException e) {
            mostrarAlerta("Ocurrió un error al eliminar el registro: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleRefrescarInventario(ActionEvent event) {
        // Aquí debes llamar al método que recarga la tabla de inventario
        cargarInventario(); // Este método debería estar implementado en tu controlador
    }

    // Método hipotético que recarga el inventario
    private void cargarInventario() {
        // Lógica para recargar los datos de inventario y refrescar la vista
        System.out.println("Inventario actualizado correctamente.");
    }

}