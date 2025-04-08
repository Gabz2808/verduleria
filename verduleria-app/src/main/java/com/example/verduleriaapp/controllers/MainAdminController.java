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
import java.sql.Connection;

public class MainAdminController {

    private Connection connection;
    private final String loginRoute = "/login.fxml";

    public MainAdminController() {
        // Constructor vacío necesario para JavaFX
    }



    @FXML
    private Pane MainPane;

    private final ConexionOracle conexionOracle = new ConexionOracle(); // ✅ Creamos instancia

    private static final String BASE_ROUTE = "/views/";


    public void initialize() {
    }

    @FXML
    private void loadProductPane() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/producto-view.fxml"));
            AnchorPane productPane = loader.load();
            MainPane.getChildren().setAll(productPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void loadCategoryPane() {
        loadPane("categoria-view.fxml", CategoriaController.class);
    }

    @FXML
    private void loadInventoryPane() {
        loadPane("inventario-view.fxml", InventarioController.class);
    }

    @FXML
    private void loadSalesPane() {
        loadPane("venta-view.fxml", VentaController.class);
    }

    @FXML
    private void loadPurchasesPane() {
        loadPane("compra-view.fxml", CompraController.class);
    }

    @FXML
    private void loadReturnsPane() {
        loadPane("devolucion-view.fxml", DevolucionController.class);
    }

    @FXML
    private void loadClientsPane() {
        loadPane("cliente-view.fxml", ClienteController.class);
    }

    @FXML
    private void loadSuppliersPane() {
        loadPane("proveedor-view.fxml", ProveedorController.class);
    }

    @FXML
    private void loadUsersPane() {
        loadPane("usuario-view.fxml", UsuarioController.class);
    }

    @FXML
    private void loadReportsPane() {
        loadPane("reporte-view.fxml", ReporteController.class);
    }

    @FXML
    private void loadAlertsPane() {
        loadPane("alerta-view.fxml", AlertaController.class);
    }

    @FXML
    private void loadExpensesPane() {
        loadPane("gasto-view.fxml", GastoController.class);
    }

    private void loadPane(String route, Class<?> controllerClass) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(BASE_ROUTE + route));
            loader.setControllerFactory(param -> {
                try {
                    // Retorna un nuevo controlador con la conexión inyectada
                    return controllerClass.getConstructor(Connection.class).newInstance(connection);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            });

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
