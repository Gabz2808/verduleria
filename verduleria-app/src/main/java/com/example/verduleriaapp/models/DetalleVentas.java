package com.example.verduleriaapp.models;

import java.util.Objects;

public class DetalleVentas {
    private Long idDetalleVenta;   // Representa el campo ID_DETALLE_VENTA (PK)
    private Long idVenta;          // Representa el campo ID_VENTA
    private Long idProducto;       // Representa el campo ID_PRODUCTO (FK)
    private Integer cantidad;      // Representa el campo CANTIDAD
    private Double precioUnitario; // Representa el campo PRECIO_UNITARIO (10,2)

    // Constructor vacío
    public DetalleVentas() {
    }

    // Constructor con parámetros
    public DetalleVentas(Long idDetalleVenta, Long idVenta, Long idProducto, Integer cantidad, Double precioUnitario) {
        this.idDetalleVenta = idDetalleVenta;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Getters y Setters
    public Long getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(Long idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public Long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Long idVenta) {
        this.idVenta = idVenta;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    // Métodos adicionales: equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleVentas that = (DetalleVentas) o;
        return Objects.equals(idDetalleVenta, that.idDetalleVenta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetalleVenta);
    }

    @Override
    public String toString() {
        return "DetalleVentas{" +
                "idDetalleVenta=" + idDetalleVenta +
                ", idVenta=" + idVenta +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                '}';
    }
}