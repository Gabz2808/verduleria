package com.example.verduleriaapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtContrasena;

    @FXML
    private Button btnIngresar;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtPasswordConfirm;

    @FXML
    private Button btnRegistrar;

    @FXML
    private void handleLogin() {
        String usuario = txtUsuario.getText();
        String contrasena = txtContrasena.getText();

        abrirNuevaVentana("/views/main-view.fxml");  // Ruta corregida

        // Aquí puedes agregar la lógica de autenticación con la base de datos
        System.out.println("✅ Usuario autenticado: " + usuario);
    }

    @FXML
    private void handleRegister() {
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        String password = txtPassword.getText();
        String passwordConfirm = txtPasswordConfirm.getText();

        if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            System.out.println("❌ Todos los campos son obligatorios.");
            return;
        }

        if (!password.equals(passwordConfirm)) {
            System.out.println("❌ Las contraseñas no coinciden.");
            return;
        }

        System.out.println("✅ Usuario registrado: " + nombre);
    }

    private void abrirNuevaVentana(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Menú Principal");
            stage.setMaximized(true);  // Pantalla completa

            stage.show();

            // Cerrar la ventana de login
            Stage loginStage = (Stage) btnIngresar.getScene().getWindow();
            loginStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error al abrir la nueva ventana: " + fxmlPath);
        }
    }
}
