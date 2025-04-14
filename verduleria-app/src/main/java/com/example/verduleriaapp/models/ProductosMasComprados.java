package com.example.verduleriaapp.models;

public class ProductosMasComprados {
    private String producto;
    private Integer totalCantidad;
    private Double totalVendido;

    public ProductosMasComprados() {}

    public ProductosMasComprados(String producto, Integer totalCantidad, Double totalVendido) {
        this.producto = producto;
        this.totalCantidad = totalCantidad;
        this.totalVendido = totalVendido;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getTotalCantidad() {
        return totalCantidad;
    }

    public void setTotalCantidad(Integer totalCantidad) {
        this.totalCantidad = totalCantidad;
    }

    public Double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Double totalVendido) {
        this.totalVendido = totalVendido;
    }

    @Override
    public String toString() {
        return "ProductosMasComprados{" +
                "producto='" + producto + '\'' +
                ", totalCantidad=" + totalCantidad +
                ", totalVendido=" + totalVendido +
                '}';
    }
}