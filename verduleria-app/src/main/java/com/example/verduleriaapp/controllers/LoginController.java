package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.DAO.UsuarioDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {

    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;
    @FXML private Button btnIngresar;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtPasswordConfirm;
    @FXML private Button btnRegistrar;

    private final Connection connection;
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

   public LoginController(Connection connection) {
       this.connection = connection;
   }

    @FXML
    private void handleLogin() {
        String correo = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        if (correo.isEmpty() || contrasena.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos vacíos", "Por favor, completa todos los campos.");
            return;
        }

        try {
            boolean valida = usuarioDAO.validarUsuario(correo, contrasena);
            boolean admin = usuarioDAO.validarAdmin(correo, contrasena);
            if (valida) {
                if (admin) {
                    abrirNuevaVentana("/views/main-admin.fxml");
                } else {
                    abrirNuevaVentana("/views/main-view.fxml");
                }
            } else {
                mostrarAlerta(Alert.AlertType.ERROR, "Error de autenticación", "Correo o contraseña incorrectos.");
            }
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al validar usuario.");
            e.printStackTrace();
        }
    }


    @FXML
    private void handleRegister() {
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String password = txtPassword.getText();
        String passwordConfirm = txtPasswordConfirm.getText();

        if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos vacíos", "Por favor, completa todos los campos.");
            return;
        }

        if (!password.equals(passwordConfirm)) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Las contraseñas no coinciden.");
            return;
        }
        try{

            usuarioDAO.crearUsuario(nombre, correo, password, 2);
        } catch (SQLException e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Error al registrar usuario.");
            e.printStackTrace();
            return;
        }

            limpiarCamposRegistro();
        }


    private void abrirNuevaVentana(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.setMaximized(true);
            stage.show();

            Stage loginStage = (Stage) btnIngresar.getScene().getWindow();
            loginStage.close();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo abrir la ventana principal.");
        }
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarCamposRegistro() {
        txtNombre.clear();
        txtCorreo.clear();
        txtPassword.clear();
        txtPasswordConfirm.clear();
    }
}