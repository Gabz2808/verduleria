package com.example.verduleriaapp.models;

import java.util.Objects;

public class Cliente {
    private Long idCliente; // Representa el campo ID_CLIENTE
    private String nombre; // Representa el campo NOMBRE
    private String contacto; // Representa el campo CONTACTO
    private String direccion; // Representa el campo DIRECCION

    // Constructor vacío
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(Long idCliente, String nombre, String contacto, String direccion) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.contacto = contacto;
        this.direccion = direccion;
    }

    // Getters y Setters
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    // Métodos adicionales: equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(idCliente, cliente.idCliente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", nombre='" + nombre + '\'' +
                ", contacto='" + contacto + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}