package com.example.verduleriaapp.models;

public class Inventario {
    private int idInventario; // ID del inventario
    private int stock;        // Stock del producto
    private String ubicacion; // Ubicación del producto

    // Constructor
    public Inventario(int idInventario, int stock, String ubicacion) {
        this.idInventario = idInventario;
        this.stock = stock;
        this.ubicacion = ubicacion;
    }

    public Inventario(int stock, String ubicacion) {
    }

    // Getter y setter para idInventario
    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    // Getter y setter para stock
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    // Getter y setter para ubicacion
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}