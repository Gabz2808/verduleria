package com.example.verduleriaapp.models;

public class Proveedor {

    private int id;
    private String nombre;
    private String contacto;
    private String direccion;

    // Constructor
    public Proveedor(int id, String nombre, String contacto, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.direccion = direccion;
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return nombre;  // Se mostrará solo el nombre en el ComboBox
    }
}
