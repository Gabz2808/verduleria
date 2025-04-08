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
    private final String inventoryRoute = "/views/inventario-view.fxml";
    private final String salesRoute = "/views/ventas-view.fxml";
    private final String purchasesRoute = "/views/compras-view.fxml";
    private final String returnsRoute = "/views/devoluciones-view.fxml";
    private final String clientsRoute = "/views/clientes-view.fxml";
    private final String suppliersRoute = "/views/proveedores-view.fxml";
    private final String usersRoute = "/views/usuarios-view.fxml";
    private final String reportsRoute = "/views/reportes-view.fxml";
    private final String alertsRoute = "/views/alertas-view.fxml";
    private final String expensesRoute = "/views/gastos-view.fxml";
    private final String loginRoute = "/login.fxml";

    public void initialize() {
        // Opcional: Puedes cargar un Pane inicial si lo deseas
    }

    @FXML
    private void loadProductPane() {
        loadPane(productRoute);
    }

    @FXML
    private void loadCategoryPane() {
        loadPane(categoryRoute);
    }

    @FXML
    private void loadInventoryPane() {
        loadPane(inventoryRoute);
    }

    @FXML
    private void loadSalesPane() {
        loadPane(salesRoute);
    }

    @FXML
    private void loadPurchasesPane() {
        loadPane(purchasesRoute);
    }

    @FXML
    private void loadReturnsPane() {
        loadPane(returnsRoute);
    }

    @FXML
    private void loadClientsPane() {
        loadPane(clientsRoute);
    }

    @FXML
    private void loadSuppliersPane() {
        loadPane(suppliersRoute);
    }

    @FXML
    private void loadUsersPane() {
        loadPane(usersRoute);
    }

    @FXML
    private void loadReportsPane() {
        loadPane(reportsRoute);
    }

    @FXML
    private void loadAlertsPane() {
        loadPane(alertsRoute);
    }

    @FXML
    private void loadExpensesPane() {
        loadPane(expensesRoute);
    }

    private void loadPane(String route) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(route));
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