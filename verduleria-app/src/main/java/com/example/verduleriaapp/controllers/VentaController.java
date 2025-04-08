package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.VentaDAO;
import javafx.fxml.FXML;

import java.sql.Connection;

public class VentaController {
    private final Connection connection;
    private final VentaDAO ventaDAO;

    public VentaController(Connection connection) {
        this.connection = connection;
        this.ventaDAO = new VentaDAO();
    }

    @FXML
    public void initialize() {

    }
}
