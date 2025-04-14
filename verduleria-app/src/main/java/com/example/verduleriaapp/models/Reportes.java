package com.example.verduleriaapp.models;

import java.util.Date;
import java.util.Objects;

public class Reportes {
    private Long idReporte;       // Representa el campo ID_REPORTE (PK)
    private String tipo;          // Representa el campo TIPO
    private Date fechaGenerado;   // Representa el campo FECHA_GENERADO
    private String descripcion;   // Representa el campo DESCRIPCION

    // Constructor vacío
    public Reportes() {
    }

    // Constructor con parámetros
    public Reportes(Long idReporte, String tipo, Date fechaGenerado, String descripcion) {
        this.idReporte = idReporte;
        this.tipo = tipo;
        this.fechaGenerado = fechaGenerado;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Long idReporte) {
        this.idReporte = idReporte;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFechaGenerado() {
        return fechaGenerado;
    }

    public void setFechaGenerado(Date fechaGenerado) {
        this.fechaGenerado = fechaGenerado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Métodos adicionales: equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reportes reportes = (Reportes) o;
        return Objects.equals(idReporte, reportes.idReporte);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReporte);
    }

    @Override
    public String toString() {
        return "Reportes{" +
                "idReporte=" + idReporte +
                ", tipo='" + tipo + '\'' +
                ", fechaGenerado=" + fechaGenerado +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}