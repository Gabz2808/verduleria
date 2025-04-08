package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.InventarioDAO;
import javafx.fxml.FXML;

import java.sql.Connection;

public class InventarioController {


    private Connection connection;
    private  InventarioDAO inventarioDAO;

    public InventarioController(Connection connection) {
    this.connection = connection;
    this.inventarioDAO = new InventarioDAO();
    }

    @FXML
    public void initialize() {

    }
}
