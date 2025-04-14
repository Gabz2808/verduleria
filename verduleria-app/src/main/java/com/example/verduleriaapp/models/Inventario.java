package com.example.verduleriaapp.models;

import java.util.Objects;

public class Inventario {
    private Long idInventario; // Representa el campo ID_INVENTARIO (PK)
    private String descripcion; // Representa el campo DESCRIPCION

    // Constructor vacío
    public Inventario() {
    }

    // Constructor con parámetros
    public Inventario(Long idInventario, String descripcion) {
        this.idInventario = idInventario;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
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
        Inventario that = (Inventario) o;
        return Objects.equals(idInventario, that.idInventario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInventario);
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "idInventario=" + idInventario +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}