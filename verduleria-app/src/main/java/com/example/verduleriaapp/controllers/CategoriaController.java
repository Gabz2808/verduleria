package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.CategoriaDAO;
import com.example.verduleriaapp.models.Categoria;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoriaController {

    private Connection connection;
    private CategoriaDAO categoriaDAO;

    @FXML private TableView<Categoria> categoriaTable;
    @FXML private TableColumn<Categoria, Integer> idColumn;
    @FXML private TableColumn<Categoria, String> nombreColumn;
    @FXML private TableColumn<Categoria, String> descripcionColumn;
    @FXML private Button btnAgregar, btnEditar, btnEliminar;

    public CategoriaController(Connection connection) {
        this.connection = connection;
        this.categoriaDAO = new CategoriaDAO();
    }

    @FXML
    public void initialize() {
        try {
            cargarCategorias();
        } catch (SQLException e) {
            e.printStackTrace(); // o mostrar alerta
        }
    }    public CategoriaController() {
        // Constructor vacío requerido por FXMLLoader
    }

        private void cargarCategorias() throws SQLException {
        List<Categoria> categorias = categoriaDAO.obtenerCategorias(); // tu método DAO
        ObservableList<Categoria> lista = FXCollections.observableArrayList(categorias);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id_categoria"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        descripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        categoriaTable.setItems(lista);
    }
}
