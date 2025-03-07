package com.example.verduleriaapp;

import conf.ScreenConfig;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.List;

public class MainController {
    private ScreenConfig screenConfig;
    private List<Double> dimensiones;

    @FXML
    private StackPane contenedorPrincipal;

    @FXML
    private Label labelPrincipal;

    public MainController() {
        // Inicializar ScreenConfig y obtener dimensiones de pantalla
        this.screenConfig = new ScreenConfig();
        this.dimensiones = screenConfig.obtenerAnchoAltura();
    }

    // Método para cambiar el contenido del StackPane dinámicamente
    private void cambiarVista(String fxmlPath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent nuevaVista = fxmlLoader.load();

            // Limpiar el contenedor y agregar la nueva vista
            contenedorPrincipal.getChildren().clear();
            contenedorPrincipal.getChildren().add(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("❌ Error al cargar la vista: " + fxmlPath);
        }
    }

    // Métodos de navegación
    @FXML
    private void mostrarInicio() {
        cambiarVista("com/example/verduleriaapp/main.fxml");
        labelPrincipal.setText("Bienvenido a la Verdulería");
    }

    @FXML
    private void mostrarProductos() {
        cambiarVista("/com/example/verduleriaapp/pages/product.fxml");
    }

    @FXML
    private void mostrarPedidos() {
        cambiarVista("/com/example/verduleriaapp/pages/orders.fxml");
    }

    @FXML
    private void mostrarConfiguracion() {
        cambiarVista("/com/example/verduleriaapp/pages/settings.fxml");
    }

}
