package com.example.verduleriaapp.models;

public class DevolucionDetalle {
    private int idDevolucion;
    private int idProducto;
    private int idOrden;
    private int idCliente;
    private String motivo;
    private int cantidad;

    public DevolucionDetalle(int idDevolucion, int idProducto, int idOrden, int idCliente, String motivo, int cantidad) {
        this.idDevolucion = idDevolucion;
        this.idProducto = idProducto;
        this.idOrden = idOrden;
        this.idCliente = idCliente;
        this.motivo = motivo;
        this.cantidad = cantidad;
    }

    public int getIdDevolucion() { return idDevolucion; }
    public void setIdDevolucion(int idDevolucion) { this.idDevolucion = idDevolucion; }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public int getIdOrden() { return idOrden; }
    public void setIdOrden(int idOrden) { this.idOrden = idOrden; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
