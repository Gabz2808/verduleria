package com.example.verduleriaapp.models;

import java.util.Date;
import java.util.Objects;

public class Gastos {
    private Long idGasto;          // Representa el campo ID_GASTO (PK)
    private String descripcion;   // Representa el campo DESCRIPCION
    private Double monto;         // Representa el campo MONTO
    private Date fecha;           // Representa el campo FECHA

    // Constructor vacío
    public Gastos() {
    }

    // Constructor con parámetros
    public Gastos(Long idGasto, String descripcion, Double monto, Date fecha) {
        this.idGasto = idGasto;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
    }

    // Getters y Setters
    public Long getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(Long idGasto) {
        this.idGasto = idGasto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // Métodos adicionales: equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gastos gastos = (Gastos) o;
        return Objects.equals(idGasto, gastos.idGasto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGasto);
    }

    @Override
    public String toString() {
        return "Gastos{" +
                "idGasto=" + idGasto +
                ", descripcion='" + descripcion + '\'' +
                ", monto=" + monto +
                ", fecha=" + fecha +
                '}';
    }
}