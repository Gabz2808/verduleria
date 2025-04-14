package com.example.verduleriaapp.models;

import java.util.Objects;

public class Pagos {
    private Long idPago;           // Representa el campo ID_PAGO (PK)
    private Long idVenta;          // Representa el campo ID_VENTA (probable clave foránea)
    private String metodoPago;     // Representa el campo METODO_PAGO
    private Double monto;          // Representa el campo MONTO

    // Constructor vacío
    public Pagos() {
    }

    // Constructor con parámetros
    public Pagos(Long idPago, Long idVenta, String metodoPago, Double monto) {
        this.idPago = idPago;
        this.idVenta = idVenta;
        this.metodoPago = metodoPago;
        this.monto = monto;
    }

    // Getters y Setters
    public Long getIdPago() {
        return idPago;
    }

    public void setIdPago(Long idPago) {
        this.idPago = idPago;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    // Métodos adicionales: equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagos pagos = (Pagos) o;
        return Objects.equals(idPago, pagos.idPago);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPago);
    }

    @Override
    public String toString() {
        return "Pagos{" +
                "idPago=" + idPago +
                ", idVenta=" + idVenta +
                ", metodoPago='" + metodoPago + '\'' +
                ", monto=" + monto +
                '}';
    }
}