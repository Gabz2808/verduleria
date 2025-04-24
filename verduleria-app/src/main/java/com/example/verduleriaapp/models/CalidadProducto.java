package com.example.verduleriaapp.models;

import java.sql.Date;

public class CalidadProducto {
    private int idCalidad;
    private String descripcion;
    private int id_producto;
    private String nombreProducto;

    public CalidadProducto(int idCalidad, String descripcion, int id_producto, String nombreProducto) {
        this.idCalidad = idCalidad;
        this.descripcion = descripcion;
        this.id_producto = id_producto;
        this.nombreProducto = nombreProducto;
    }

    public CalidadProducto() {

    }

    public int getIdCalidad() {
        return idCalidad;
    }

    public void setIdCalidad(int idCalidad) {
        this.idCalidad = idCalidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_producto() {
        return id_producto;
    }
    public int getIdProducto() {
        return id_producto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
