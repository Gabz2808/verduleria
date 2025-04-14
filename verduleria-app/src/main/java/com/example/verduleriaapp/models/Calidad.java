package com.example.verduleriaapp.models;

import java.util.Objects;

public class Calidad {
    private Long idCalidad;       // Representa el campo ID_CALIDAD (PK)
    private Long idLote;          // Representa el campo ID_LOTE
    private String descripcion;   // Representa el campo DESCRIPCION

    // Constructor vacío
    public Calidad() {
    }

    // Constructor con parámetros
    public Calidad(Long idCalidad, Long idLote, String descripcion) {
        this.idCalidad = idCalidad;
        this.idLote = idLote;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getIdCalidad() {
        return idCalidad;
    }

    public void setIdCalidad(Long idCalidad) {
        this.idCalidad = idCalidad;
    }

    public Long getIdLote() {
        return idLote;
    }

    public void setIdLote(Long idLote) {
        this.idLote = idLote;
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
        Calidad calidad = (Calidad) o;
        return Objects.equals(idCalidad, calidad.idCalidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCalidad);
    }

    @Override
    public String toString() {
        return "Calidad{" +
                "idCalidad=" + idCalidad +
                ", idLote=" + idLote +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}