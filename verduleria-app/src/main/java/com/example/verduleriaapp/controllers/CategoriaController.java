package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.CategoriaDAO;
import javafx.fxml.FXML;

import java.sql.Connection;

public class CategoriaController {
    private Connection connection;
    private CategoriaDAO ccategoriaDAO;

    public CategoriaController(Connection connection) {
        this.connection = connection;
        this.ccategoriaDAO = new CategoriaDAO();

    }

    @FXML
    public void initialize() {

    }
}
