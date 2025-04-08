package com.example.verduleriaapp.models;

public class Unidad {

    private int id;
    private String nombre;

    // Constructor
    public Unidad(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;  // Se mostrará solo el nombre en el ComboBox
    }
}
