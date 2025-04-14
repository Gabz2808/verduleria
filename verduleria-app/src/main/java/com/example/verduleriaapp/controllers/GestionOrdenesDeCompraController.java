package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.DetalleComprasDAO;
import com.example.verduleriaapp.models.ProductosMasComprados;
import com.example.verduleriaapp.models.ResumenComprasPorFecha;
import com.example.verduleriaapp.models.ComprasPorProveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class GestionOrdenesDeCompraController {

    @FXML
    private TableView<ResumenComprasPorFecha> tablaResumenCompras;
    @FXML
    private TableColumn<ResumenComprasPorFecha, String> colFechaResumen;
    @FXML
    private TableColumn<ResumenComprasPorFecha, Double> colTotalDia;

    @FXML
    private TableView<ProductosMasComprados> tablaProductosMasComprados;
    @FXML
    private TableColumn<ProductosMasComprados, String> colProducto;
    @FXML
    private TableColumn<ProductosMasComprados, Integer> colCantidad;
    @FXML
    private TableColumn<ProductosMasComprados, Double> colTotalVendido;

    @FXML
    private TableView<ComprasPorProveedor> tablaComprasPorProveedor;
    @FXML
    private TableColumn<ComprasPorProveedor, String> colProveedor;
    @FXML
    private TableColumn<ComprasPorProveedor, Integer> colNumeroCompras;
    @FXML
    private TableColumn<ComprasPorProveedor, Double> colTotalGastado;

    private DetalleComprasDAO detalleComprasDAO;

    private ObservableList<ResumenComprasPorFecha> listaResumenCompras;
    private ObservableList<ProductosMasComprados> listaProductosMasComprados;
    private ObservableList<ComprasPorProveedor> listaComprasProveedores;

    public void initialize() {
        // Configurar columnas de cada tabla
        configurarColumnas();

        // Inicializar el DAO
        detalleComprasDAO = new DetalleComprasDAO();

        // Cargar datos para cada tabla
        cargarResumenCompras();
        cargarProductosMasComprados();
        cargarComprasPorProveedor();
    }

    private void configurarColumnas() {
        // Configurar tabla resumen de compras
        colFechaResumen.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
        colTotalDia.setCellValueFactory(new PropertyValueFactory<>("totalDelDia"));

        // Configurar tabla productos más comprados
        colProducto.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("totalCantidad"));
        colTotalVendido.setCellValueFactory(new PropertyValueFactory<>("totalVendido"));

        // Configurar tabla compras por proveedor
        colProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        colNumeroCompras.setCellValueFactory(new PropertyValueFactory<>("numeroCompras"));
        colTotalGastado.setCellValueFactory(new PropertyValueFactory<>("totalGastado"));
    }

    private void cargarResumenCompras() {
        try {
            List<ResumenComprasPorFecha> resumen = detalleComprasDAO.obtenerResumenComprasPorFecha();
            listaResumenCompras = FXCollections.observableArrayList(resumen);
            tablaResumenCompras.setItems(listaResumenCompras);
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar el resumen de compras: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void cargarProductosMasComprados() {
        try {
            List<ProductosMasComprados> productos = detalleComprasDAO.obtenerProductosMasComprados();
            listaProductosMasComprados = FXCollections.observableArrayList(productos);
            tablaProductosMasComprados.setItems(listaProductosMasComprados);
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar los productos más comprados: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void cargarComprasPorProveedor() {
        try {
            List<ComprasPorProveedor> compras = detalleComprasDAO.obtenerComprasPorProveedor();
            listaComprasProveedores = FXCollections.observableArrayList(compras);
            tablaComprasPorProveedor.setItems(listaComprasProveedores);
        } catch (SQLException e) {
            mostrarAlerta("Error al cargar las compras por proveedor: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle((tipo == Alert.AlertType.ERROR) ? "Error" : "Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}