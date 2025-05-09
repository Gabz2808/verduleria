package com.example.verduleriaapp.controllers;

import com.example.verduleriaapp.utils.ConexionOracle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;

public class MainAdminController {

    // Conexión a la base de datos
    private Connection connection;

    // Rutas para diferentes vistas

    @FXML
    private Pane MainPane;

    // Constructor inicializa la conexión a la base de datos
    public MainAdminController() {
        this.connection = new ConexionOracle().getConnection();
        if (this.connection == null) {
            System.out.println("❌ Error al conectar a la base de datos.");
        }
    }

    public void initialize() {
        // Puedes agregar inicializaciones generales aquí si es necesario.
    }

    @FXML
    private void loadProductPane() {
        loadPane("/views/producto-view.fxml");
    }

    @FXML
    private void loadCategoryPane() {
        loadPane("/views/categoria-view.fxml");
    }

    @FXML
    private void loadInventoryPane() {
        loadPane("/views/GestionInventario.fxml");
    }

    @FXML
    private void loadOrdersPane() {
        loadPane("/views/GestionOrdenesDeCompra.fxml");
    }

    @FXML
    private void loadSuppliersPane() {
        // Nota que la ruta no tiene un "/views" inicial porque este ya debe estar dentro del classpath
        loadPane("/views/GestionProveedoresView.fxml");
    }

    @FXML
    private void loadReturnsPane() {
        loadPane("/views/devolucion-view.fxml");
    }

    @FXML
    private void loadReportsPane() {
        loadPane("/views/reporte-view.fxml");
    }

    @FXML
    private void loadExpensesPane() {
        loadPane("/views/gasto-view.fxml");
    }

    @FXML
    private void loadQualityPane() {
        loadPane("/views/calidad-productos-view.fxml");
    }

    // Método genérico para cargar un nuevo pane en el área principal
    private void loadPane(String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            // Inyección de conexión en el controlador si lo necesita
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
            Scene loginScene = new Scene(loginPane, 600, 400); // Tamaño fijo

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