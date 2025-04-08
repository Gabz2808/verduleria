package com.example.verduleriaapp.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Producto {

    private IntegerProperty idProducto;
    private StringProperty nombre;
    private IntegerProperty idCategoria;
    private IntegerProperty idUnidad;
    private IntegerProperty idProveedor;

    public Producto(int idProducto, String nombre, int idCategoria, int idUnidad, int idProveedor) {
        this.idProducto = new SimpleIntegerProperty(idProducto);
        this.nombre = new SimpleStringProperty(nombre);
        this.idCategoria = new SimpleIntegerProperty(idCategoria);
        this.idUnidad = new SimpleIntegerProperty(idUnidad);
        this.idProveedor = new SimpleIntegerProperty(idProveedor);
    }

    public Producto( String nombre, int idCategoria, int idUnidad, int idProveedor) {
        this.nombre = new SimpleStringProperty(nombre);
        this.idCategoria = new SimpleIntegerProperty(idCategoria);
        this.idUnidad = new SimpleIntegerProperty(idUnidad);
        this.idProveedor = new SimpleIntegerProperty(idProveedor);
    }

    // Getters y setters para las propiedades
    public IntegerProperty idProductoProperty() {
        return idProducto;
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public IntegerProperty idCategoriaProperty() {
        return idCategoria;
    }

    public IntegerProperty idUnidadProperty() {
        return idUnidad;
    }

    public IntegerProperty idProveedorProperty() {
        return idProveedor;
    }

    // Getters tradicionales
    public int getIdProducto() {
        return idProducto.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public int getIdCategoria() {
        return idCategoria.get();
    }

    public int getIdUnidad() {
        return idUnidad.get();
    }

    public int getIdProveedor() {
        return idProveedor.get();
    }

    // Setters tradicionales
    public void setIdProducto(int idProducto) {
        this.idProducto.set(idProducto);
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria.set(idCategoria);
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad.set(idUnidad);
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor.set(idProveedor);
    }
}
