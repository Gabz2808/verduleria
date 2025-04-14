package com.example.verduleriaapp.models;

public class Devoluciones {
    private Long idDevolucion;   // ID de la devolución (Primary Key)
    private Long idProducto;     // ID del producto (Foreign Key)
    private Long idVenta;        // ID de la venta (Foreign Key -> MODIFICADO)
    private Long idCliente;      // ID del cliente (Foreign Key)
    private String motivo;       // Motivo de la devolución
    private int cantidad;        // Cantidad de productos devueltos

    // Constructor vacío
    public Devoluciones() {
    }

    // Constructor con todos los atributos
    public Devoluciones(Long idDevolucion, Long idProducto, Long idVenta, Long idCliente, String motivo, int cantidad) {
        this.idDevolucion = idDevolucion;
        this.idProducto = idProducto;
        this.idVenta = idVenta;   // Cambio aplicado
        this.idCliente = idCliente;
        this.motivo = motivo;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public Long getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(Long idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public Long getIdVenta() { // Cambio aplicado
        return idVenta;
    }

    public void setIdVenta(Long idVenta) { // Cambio aplicado
        this.idVenta = idVenta;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Devoluciones{" +
                "idDevolucion=" + idDevolucion +
                ", idProducto=" + idProducto +
                ", idVenta=" + idVenta +   // Cambio aplicado
                ", idCliente=" + idCliente +
                ", motivo='" + motivo + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}