package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.OrdenCompraDAO;
import com.example.verduleriaapp.models.OrdenCompra;
import com.example.verduleriaapp.models.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class GestionOrdenesDeCompraController {

    @FXML
    private TableView<OrdenCompra> tablaOrdenesCompra;
    @FXML
    private TableColumn<OrdenCompra, Integer> colNumeroOrden;
    @FXML
    private TableColumn<OrdenCompra, Integer> colIdProveedor;
    @FXML
    private TableColumn<OrdenCompra, LocalDate> colFecha;
    @FXML
    private TableColumn<OrdenCompra, String> colEstado;

    @FXML
    private ComboBox<Proveedor> comboProveedores;
    @FXML
    private DatePicker pickerFecha;
    @FXML
    private TextField txtEstado;

    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnLimpiar;

    private ObservableList<OrdenCompra> listaOrdenesCompra;
    private ObservableList<Proveedor> listaProveedores;
    private OrdenCompraDAO ordenCompraDAO;

    // Inicializar el controlador
    public void initialize() {
        // Configuración de las columnas de la tabla
        colNumeroOrden.setCellValueFactory(new PropertyValueFactory<>("numeroOrden"));
        colIdProveedor.setCellValueFactory(new PropertyValueFactory<>("idProveedor"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        // Instanciar el DAO para las órdenes de compra
        ordenCompraDAO = new OrdenCompraDAO();
        cargarOrdenesCompra();
        cargarComboProveedores();
    }

    // Cargar las órdenes de compra desde la base de datos
    private void cargarOrdenesCompra() {
        try {
            List<OrdenCompra> ordenes = ordenCompraDAO.obtenerOrdenes();
            listaOrdenesCompra = FXCollections.observableArrayList(ordenes);
            tablaOrdenesCompra.setItems(listaOrdenesCompra);
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar órdenes de compra: " + e.getMessage());
        }
    }

    // Cargar la lista de proveedores al combo box
    private void cargarComboProveedores() {
        try {
            List<Proveedor> proveedores = ordenCompraDAO.obtenerProveedores(); // Asume que este método existe
            listaProveedores = FXCollections.observableArrayList(proveedores);
            comboProveedores.setItems(listaProveedores);
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar proveedores: " + e.getMessage());
        }
    }

    // Manejar evento de agregar una nueva orden
    @FXML
    private void handleAgregar() {
        Proveedor proveedorSeleccionado = comboProveedores.getSelectionModel().getSelectedItem();
        LocalDate fecha = pickerFecha.getValue();
        String estado = txtEstado.getText();

        if (proveedorSeleccionado != null && fecha != null && !estado.isEmpty()) {
            try {
                OrdenCompra nuevaOrden = new OrdenCompra(0, proveedorSeleccionado.getId(), fecha, estado); // ID generado automáticamente
                ordenCompraDAO.insertarOrdenCompra(nuevaOrden);
                listaOrdenesCompra.add(nuevaOrden);
                limpiarFormulario();
                mostrarAlerta("Orden de compra agregada exitosamente.");
            } catch (SQLException e) {
                mostrarAlerta("Error al agregar orden de compra: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Por favor, complete todos los campos.");
        }
    }

    // Manejar evento de actualizar una orden seleccionada
    @FXML
    private void handleActualizar() {
        OrdenCompra seleccionada = tablaOrdenesCompra.getSelectionModel().getSelectedItem();
        Proveedor proveedorSeleccionado = comboProveedores.getSelectionModel().getSelectedItem();
        LocalDate fecha = pickerFecha.getValue();
        String estado = txtEstado.getText();

        if (seleccionada != null && proveedorSeleccionado != null && fecha != null && !estado.isEmpty()) {
            try {
                seleccionada.setIdProveedor(proveedorSeleccionado.getId());
                seleccionada.setFecha(fecha);
                seleccionada.setEstado(estado);
                ordenCompraDAO.actualizarOrdenCompra(seleccionada);
                tablaOrdenesCompra.refresh();
                limpiarFormulario();
                mostrarAlerta("Orden de compra actualizada exitosamente.");
            } catch (SQLException e) {
                mostrarAlerta("Error al actualizar la orden de compra: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Por favor, complete todos los campos y/o seleccione una orden.");
        }
    }

    // Manejar evento de eliminar una orden seleccionada
    @FXML
    private void handleEliminar() {
        OrdenCompra seleccionada = tablaOrdenesCompra.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            try {
                ordenCompraDAO.eliminarOrdenCompra(seleccionada.getNumeroOrden());
                listaOrdenesCompra.remove(seleccionada);
                mostrarAlerta("Orden de compra eliminada exitosamente.");
            } catch (SQLException e) {
                mostrarAlerta("Error al eliminar la orden de compra: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Por favor, seleccione una orden.");
        }
    }

    // Limpiar el formulario
    @FXML
    private void limpiarFormulario() {
        comboProveedores.setValue(null);
        pickerFecha.setValue(null);
        txtEstado.clear();
        tablaOrdenesCompra.getSelectionModel().clearSelection();
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