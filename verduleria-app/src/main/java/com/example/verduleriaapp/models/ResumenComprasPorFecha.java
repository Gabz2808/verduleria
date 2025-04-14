package com.example.verduleriaapp.models;

import java.time.LocalDate;

public class ResumenComprasPorFecha {
    private LocalDate fechaCompra;
    private Double totalDelDia;

    public ResumenComprasPorFecha() {}

    public ResumenComprasPorFecha(LocalDate fechaCompra, Double totalDelDia) {
        this.fechaCompra = fechaCompra;
        this.totalDelDia = totalDelDia;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getTotalDelDia() {
        return totalDelDia;
    }

    public void setTotalDelDia(Double totalDelDia) {
        this.totalDelDia = totalDelDia;
    }

    @Override
    public String toString() {
        return "ResumenComprasPorFecha{" +
                "fechaCompra=" + fechaCompra +
                ", totalDelDia=" + totalDelDia +
                '}';
    }
}