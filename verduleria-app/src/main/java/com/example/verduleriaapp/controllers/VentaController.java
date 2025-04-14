package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.VentasDAO;
import javafx.fxml.FXML;

import java.sql.Connection;

public class VentaController {
    private final Connection connection;
    private final VentasDAO ventaDAO;

    public VentaController(Connection connection) {
        this.connection = connection;
        this.ventaDAO = new VentasDAO();
    }

    @FXML
    public void initialize() {

    }
}
