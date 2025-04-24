package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.ProductoDAO;
import com.example.verduleriaapp.models.Producto;
import com.example.verduleriaapp.models.VistaProducto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductoController {

    @FXML
    private TableColumn<VistaProducto, Integer> colId;
    @FXML
    private TableColumn<VistaProducto, String> colNombre;
    @FXML
    private TableColumn<VistaProducto, String> colCategoria;
    @FXML
    private TableColumn<VistaProducto, String> colUnidad;
    @FXML
    private TableColumn<VistaProducto, String> colProveedor;
    @FXML
    private Button agregarProductoBtn;
    @FXML
    private TableView<VistaProducto> tablaProductos;

    private ObservableList<VistaProducto> productos;
    private final ProductoDAO productoDAO;

    public ProductoController() {
        productoDAO = new ProductoDAO();
    }

    @FXML
    public void initialize() {
        try {
            cargarDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarDatos() throws SQLException {
        List<VistaProducto> listaProductos = productoDAO.obtenerVistaProductos();
        productos = FXCollections.observableArrayList(listaProductos);
        tablaProductos.setItems(productos);

        colId.setCellValueFactory(new PropertyValueFactory<>("id_producto"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("producto"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        colUnidad.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        colProveedor.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
        System.out.println("Productos encontrados: " + listaProductos.size());
        for (VistaProducto p : listaProductos) {
            System.out.println(p.getProducto());
        }

    }




    private void mostrarAlerta(String mensaje) {
        javafx.scene.control.Alert alerta = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


}
