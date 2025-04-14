package com.example.verduleriaapp.models;

import java.util.Date;
import java.util.Objects;

public class HistorialStock {
    private Long idHistorial; // Representa el campo ID_HISTORIAL (PK)
    private Long idProducto;  // Representa el campo ID_PRODUCTO (FK)
    private Integer cantidadAnterior; // Representa el campo CANTIDAD_ANTERIOR
    private Integer cantidadNueva;    // Representa el campo CANTIDAD_NUEVA
    private Date fechaCambio;         // Representa el campo FECHA_CAMBIO

    // Constructor vacío
    public HistorialStock() {
    }

    // Constructor con parámetros
    public HistorialStock(Long idHistorial, Long idProducto, Integer cantidadAnterior, Integer cantidadNueva, Date fechaCambio) {
        this.idHistorial = idHistorial;
        this.idProducto = idProducto;
        this.cantidadAnterior = cantidadAnterior;
        this.cantidadNueva = cantidadNueva;
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

    public Integer getCantidadAnterior() {
        return cantidadAnterior;
    }

    public void setCantidadAnterior(Integer cantidadAnterior) {
        this.cantidadAnterior = cantidadAnterior;
    }

    public Integer getCantidadNueva() {
        return cantidadNueva;
    }

    public void setCantidadNueva(Integer cantidadNueva) {
        this.cantidadNueva = cantidadNueva;
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
        HistorialStock that = (HistorialStock) o;
        return Objects.equals(idHistorial, that.idHistorial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idHistorial);
    }

    @Override
    public String toString() {
        return "HistorialStock{" +
                "idHistorial=" + idHistorial +
                ", idProducto=" + idProducto +
                ", cantidadAnterior=" + cantidadAnterior +
                ", cantidadNueva=" + cantidadNueva +
                ", fechaCambio=" + fechaCambio +
                '}';
    }
}