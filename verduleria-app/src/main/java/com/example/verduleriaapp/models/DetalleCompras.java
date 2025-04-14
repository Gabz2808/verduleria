package com.example.verduleriaapp.models;

import java.util.Objects;

public class DetalleCompras {
    private Long idDetalleCompra;   // Representa el campo ID_DETALLE_COMPRA (PK)
    private Long idCompra;          // Representa el campo ID_COMPRA (FK)
    private Long idProducto;        // Representa el campo ID_PRODUCTO (FK)
    private Integer cantidad;       // Representa el campo CANTIDAD
    private Double precioUnitario;  // Representa el campo PRECIO_UNITARIO (10,2)

    // Constructor vacío
    public DetalleCompras() {
    }

    // Constructor con parámetros
    public DetalleCompras(Long idDetalleCompra, Long idCompra, Long idProducto, Integer cantidad, Double precioUnitario) {
        this.idDetalleCompra = idDetalleCompra;
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Getters y Setters
    public Long getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(Long idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Long idCompra) {
        this.idCompra = idCompra;
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
        DetalleCompras that = (DetalleCompras) o;
        return Objects.equals(idDetalleCompra, that.idDetalleCompra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDetalleCompra);
    }

    @Override
    public String toString() {
        return "DetalleCompras{" +
                "idDetalleCompra=" + idDetalleCompra +
                ", idCompra=" + idCompra +
                ", idProducto=" + idProducto +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                '}';
    }
}