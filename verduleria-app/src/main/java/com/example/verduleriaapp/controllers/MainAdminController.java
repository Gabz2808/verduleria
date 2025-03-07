package com.example.verduleriaapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class MainAdminController {

    @FXML
    public void logout(ActionEvent event) {


        try {
            String loginRoute = "/login.fxml";
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(loginRoute));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400); // Establecer tamaño fijo

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Verdulería");

            InputStream iconStream = getClass().getResourceAsStream("/assets/fruits.png");
            if (iconStream != null) {
                stage.getIcons().add(new Image(iconStream));
            } else {
                System.out.println("❌ No se encontró el archivo de icono.");
            }

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
