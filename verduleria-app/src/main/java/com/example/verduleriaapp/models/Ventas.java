package com.example.verduleriaapp.models;

import java.util.Date;

public class Ventas {
    private Long idVenta;         // ID de la venta (PRIMARY KEY)
    private Long idCliente;       // ID del cliente (FOREIGN KEY)
    private Date fechaVenta;      // Fecha de la venta
    private Double total;         // Total de la venta

    // Constructor vacío
    public Ventas() {
    }

    // Constructor con todos los atributos
    public Ventas(Long idVenta, Long idCliente, Date fechaVenta, Double total) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.fechaVenta = fechaVenta;
        this.total = total;
    }

    // Getters y Setters
    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Ventas{" +
                "idVenta=" + idVenta +
                ", idCliente=" + idCliente +
                ", fechaVenta=" + fechaVenta +
                ", total=" + total +
                '}';
    }
}