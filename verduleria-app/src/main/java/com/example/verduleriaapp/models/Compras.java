package com.example.verduleriaapp.models;

import java.util.Date;
import java.util.Objects;

public class Compras {
    private Long idCompra;        // Representa el campo ID_COMPRA (PK)
    private Long idProveedor;     // Representa el campo ID_PROVEEDOR (FK)
    private Date fechaCompra;     // Representa el campo FECHA_COMPRA
    private Double total;         // Representa el campo TOTAL (10,2)

    // Constructor vacío
    public Compras() {
    }

    // Constructor con parámetros
    public Compras(Long idCompra, Long idProveedor, Date fechaCompra, Double total) {
        this.idCompra = idCompra;
        this.idProveedor = idProveedor;
        this.fechaCompra = fechaCompra;
        this.total = total;
    }

    // Getters y Setters
    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
    }

    public Long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Long idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    // Métodos adicionales: equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compras compras = (Compras) o;
        return Objects.equals(idCompra, compras.idCompra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCompra);
    }

    @Override
    public String toString() {
        return "Compras{" +
                "idCompra=" + idCompra +
                ", idProveedor=" + idProveedor +
                ", fechaCompra=" + fechaCompra +
                ", total=" + total +
                '}';
    }
}