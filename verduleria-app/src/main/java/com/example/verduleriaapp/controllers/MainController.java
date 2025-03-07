package com.example.verduleriaapp.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;

public class MainController {

    @FXML
    private Pane MainPane;

    private final String productRoute = "/views/producto-view.fxml";
    private final String categoryRoute = "/views/categoria-view.fxml";
    private final String loginRoute = "/login.fxml";

    public void initialize() {
        // Opcional: Puedes cargar un Pane inicial si lo deseas

    }

    @FXML
    private void loadProductPane() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(productRoute));
            Parent newPane = loader.load();
            MainPane.getChildren().clear();
            MainPane.getChildren().add(newPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
        private void loadCategoryPane() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(categoryRoute));
            Parent newPane = loader.load();
            MainPane.getChildren().clear();
            MainPane.getChildren().add(newPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
}
    @FXML
    public void logout(ActionEvent event) {
        try {
            // Cargar FXML
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(loginRoute));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400); // Establecer tamaño fijo

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Verdulería");

            // Cargar ícono
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
