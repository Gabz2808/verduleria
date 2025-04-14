package com.example.verduleriaapp.models;

import javafx.beans.property.*;

public class Proveedor {
    private LongProperty id; // Cambiado de int a long
    private StringProperty nombre;
    private StringProperty contacto;
    private StringProperty direccion;

    // Constructor vacío
    public Proveedor() {
        this.id = new SimpleLongProperty();
        this.nombre = new SimpleStringProperty();
        this.contacto = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
    }

    // Constructor con parámetros
    public Proveedor(long id, String nombre, String contacto, String direccion) {
        this.id = new SimpleLongProperty(id); // Cambiado de int a long
        this.nombre = new SimpleStringProperty(nombre);
        this.contacto = new SimpleStringProperty(contacto);
        this.direccion = new SimpleStringProperty(direccion);
    }

    // Getters y setters para ID
    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public LongProperty idProperty() {
        return id;
    }

    // Getters y setters para Nombre
    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    // Getters y setters para Contacto
    public String getContacto() {
        return contacto.get();
    }

    public void setContacto(String contacto) {
        this.contacto.set(contacto);
    }

    public StringProperty contactoProperty() {
        return contacto;
    }

    // Getters y setters para Dirección
    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public StringProperty direccionProperty() {
        return direccion;
    }
}