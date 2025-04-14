package com.example.verduleriaapp.models;

import java.util.Date;
import java.util.Objects;

public class HistorialPrecios {
    private Long idHistorial;      // Representa el campo ID_HISTORIAL (PK)
    private Long idProducto;       // Representa el campo ID_PRODUCTO (FK)
    private Double precioAnterior; // Representa el campo PRECIO_ANTERIOR (10,2)
    private Double precioNuevo;    // Representa el campo PRECIO_NUEVO (10,2)
    private Date fechaCambio;      // Representa el campo FECHA_CAMBIO

    // Constructor vacío
    public HistorialPrecios() {
    }

    // Constructor con parámetros
    public HistorialPrecios(Long idHistorial, Long idProducto, Double precioAnterior, Double precioNuevo, Date fechaCambio) {
        this.idHistorial = idHistorial;
        this.idProducto = idProducto;
        this.precioAnterior = precioAnterior;
        this.precioNuevo = precioNuevo;
        this.fechaCambio = fechaCambio;
    }

    // Getters y Setters
    public Long getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Long idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Double getPrecioAnterior() {
        return precioAnterior;
    }

    public void setPrecioAnterior(Double precioAnterior) {
        this.precioAnterior = precioAnterior;
    }

    public Double getPrecioNuevo() {
        return precioNuevo;
    }

    public void setPrecioNuevo(Double precioNuevo) {
        this.precioNuevo = precioNuevo;
    }

    public Date getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(Date fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    // Métodos adicionales: equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistorialPrecios that = (HistorialPrecios) o;
        return Objects.equals(idHistorial, that.idHistorial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistorial);
    }

    @Override
    public String toString() {
        return "HistorialPrecios{" +
                "idHistorial=" + idHistorial +
                ", idProducto=" + idProducto +
                ", precioAnterior=" + precioAnterior +
                ", precioNuevo=" + precioNuevo +
                ", fechaCambio=" + fechaCambio +
                '}';
    }
}