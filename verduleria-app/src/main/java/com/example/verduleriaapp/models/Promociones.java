package com.example.verduleriaapp.models;

import java.util.Date;
import java.util.Objects;

public class Promociones {
    private Long idPromocion;    // Representa el campo ID_PROMOCION (PK)
    private Long idProducto;     // Representa el campo ID_PRODUCTO (FK)
    private Double descuento;    // Representa el campo DESCUENTO (5,2)
    private Date fechaInicio;    // Representa el campo FECHA_INICIO
    private Date fechaFin;       // Representa el campo FECHA_FIN

    // Constructor vacío
    public Promociones() {
    }

    // Constructor con parámetros
    public Promociones(Long idPromocion, Long idProducto, Double descuento, Date fechaInicio, Date fechaFin) {
        this.idPromocion = idPromocion;
        this.idProducto = idProducto;
        this.descuento = descuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    // Getters y Setters
    public Long getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Long idPromocion) {
        this.idPromocion = idPromocion;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    // Métodos adicionales: equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Promociones that = (Promociones) o;
        return Objects.equals(idPromocion, that.idPromocion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPromocion);
    }

    @Override
    public String toString() {
        return "Promociones{" +
                "idPromocion=" + idPromocion +
                ", idProducto=" + idProducto +
                ", descuento=" + descuento +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                '}';
    }
}