package com.example.verduleriaapp.models;

public class ComprasPorProveedor {
    private String proveedor;
    private Integer numeroCompras;
    private Double totalGastado;

    public ComprasPorProveedor() {}

    public ComprasPorProveedor(String proveedor, Integer numeroCompras, Double totalGastado) {
        this.proveedor = proveedor;
        this.numeroCompras = numeroCompras;
        this.totalGastado = totalGastado;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Integer getNumeroCompras() {
        return numeroCompras;
    }

    public void setNumeroCompras(Integer numeroCompras) {
        this.numeroCompras = numeroCompras;
    }

    public Double getTotalGastado() {
        return totalGastado;
    }

    public void setTotalGastado(Double totalGastado) {
        this.totalGastado = totalGastado;
    }

    @Override
    public String toString() {
        return "ComprasPorProveedor{" +
                "proveedor='" + proveedor + '\'' +
                ", numeroCompras=" + numeroCompras +
                ", totalGastado=" + totalGastado +
                '}';
    }
}