package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.DevolucionesDAO;
import com.example.verduleriaapp.models.Devoluciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class DevolucionesController {

    @FXML
    private TableView<Devoluciones> tableDevoluciones;

    @FXML
    private TableColumn<Devoluciones, Long> colIdDevolucion;

    @FXML
    private TableColumn<Devoluciones, Long> colIdProducto;

    @FXML
    private TableColumn<Devoluciones, Long> colIdOrden;

    @FXML
    private TableColumn<Devoluciones, Long> colIdCliente;

    @FXML
    private TableColumn<Devoluciones, String> colMotivo;

    @FXML
    private TableColumn<Devoluciones, Integer> colCantidad;


    @FXML
    private TableColumn<Devoluciones, Date> colFecha;

    private DevolucionesDAO devolucionesDAO;

    public DevolucionesController() {
        this.devolucionesDAO = new DevolucionesDAO();
    }

    @FXML
    public void initialize() {
        colIdDevolucion.setCellValueFactory(new PropertyValueFactory<>("idDevolucion"));
        colIdProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        colIdOrden.setCellValueFactory(new PropertyValueFactory<>("idOrden"));
        colIdCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<>("motivo"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
    }
}
