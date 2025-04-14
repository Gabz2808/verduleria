package com.example.verduleriaapp.models;

import java.util.Objects;

public class Roles {
    private Long idRol;         // Representa el campo ID_ROL (PK)
    private String nombre;      // Representa el campo NOMBRE (UNIQUE)

    // Constructor vacío
    public Roles() {
    }

    // Constructor con parámetros
    public Roles(Long idRol, String nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }

    // Getters y Setters
    public Long getIdRol() {
        return idRol;
    }

    public void setIdRol(Long idRol) {
        this.idRol = idRol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos adicionales: equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roles roles = (Roles) o;
        return Objects.equals(idRol, roles.idRol) &&
                Objects.equals(nombre, roles.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol, nombre);
    }

    @Override
    public String toString() {
        return "Roles{" +
                "idRol=" + idRol +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}