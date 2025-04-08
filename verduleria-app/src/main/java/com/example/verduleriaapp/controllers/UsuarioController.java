package com.example.verduleriaapp.controllers;

import com.dlsc.formsfx.model.structure.PasswordField;
import com.example.verduleriaapp.DAO.UsuarioDAO;
import com.example.verduleriaapp.models.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.awt.*;

public class UsuarioController {
    @FXML
    private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasena;
    @FXML private TextField txtRol;
    @FXML private DatePicker dpFechaCreacion;
    @FXML private TableView<Usuario> tableUsuarios;
    @FXML private TableColumn<Usuario, Integer> colId;
    @FXML private TableColumn<Usuario, String> colNombre;
    @FXML private TableColumn<Usuario, String> colCorreo;
    @FXML private TableColumn<Usuario, Integer> colRol;
    @FXML private TableColumn<Usuario, String> colFecha;

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    
}
