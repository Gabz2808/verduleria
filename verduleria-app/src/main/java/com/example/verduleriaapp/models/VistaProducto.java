package com.example.verduleriaapp.models;

public class VistaProducto {
    private int idProducto;
    private String producto;
    private String categoria;
    private String proveedor;
    private String unidad;
    private int idInventario;
    private int disponible;



    public VistaProducto(int idProducto, String producto, String categoria, String proveedor, String unidad, int idInventario, int disponible) {
        this.idProducto = idProducto;
        this.producto = producto;
        this.categoria = categoria;
        this.proveedor = proveedor;
        this.unidad = unidad;
        this.idInventario = idInventario;
        this.disponible = disponible;
    }

    public int getIdProducto() { return idProducto; }
    public String getProducto() { return producto; }
    public String getCategoria() { return categoria; }
    public String getProveedor() { return proveedor; }
    public String getUnidad() { return unidad; }
    public int getIdInventario() { return idInventario; }
    public int getDisponible() { return disponible; }

}
