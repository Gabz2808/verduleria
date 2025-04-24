package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.utils.ConexionOracle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
public class MainController {

    private Connection connection;

    @FXML
    private Pane MainPane;

    public MainController() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            System.out.println("❌ Error al conectar a la base de datos.");
        }
    }

    public void initialize() {
        // Inicializaciones generales si es necesario
    }

    @FXML
    private void loadProductPane() {
        loadPane("/views/producto-view.fxml");
    }

    @FXML
    private void loadInventoryPane() {
        loadPane("/views/GestionInventario.fxml");
    }

    @FXML
    private void loadReturnsPane() {
        loadPane("/views/devolucion-view.fxml");
    }
    @FXML
    private void loadCategoryPane(){
        loadPane("/views/categoria-view.fxml");
    }

    @FXML
    private void loadPurchasesPane() {
        loadPane("/views/compra-view.fxml");
    }

    @FXML
    private void loadQualityPane() {
        loadPane("/views/calidad-productos-view.fxml");
    }

    private void loadPane(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            loader.setControllerFactory(controllerClass -> {
                try {
                    if (controllerClass == CategoriaController.class) {
                        return new CategoriaController(connection);
                    }
                    return controllerClass.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            Parent pane = loader.load();
            MainPane.getChildren().setAll(pane);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error al cargar la vista: " + fxmlPath);
        }
    }

    @FXML
    public void logout(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/login.fxml"));
            Parent loginPane = fxmlLoader.load();
            Scene loginScene = new Scene(loginPane, 600, 400);

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.setTitle("Iniciar Sesión - Verdulería");

            InputStream iconStream = getClass().getResourceAsStream("/assets/fruits.png");
            if (iconStream != null) {
                currentStage.getIcons().add(new Image(iconStream));
            } else {
                System.out.println("❌ No se encontró el icono.");
            }

            currentStage.setScene(loginScene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error al cerrar sesión.");
        }
    }
}