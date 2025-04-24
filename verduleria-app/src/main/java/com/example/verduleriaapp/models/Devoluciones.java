package com.example.verduleriaapp.models;

import javafx.beans.property.*;

public class Devoluciones {
    private LongProperty idDevolucion;
    private LongProperty idProducto;
    private LongProperty idorden;
    private LongProperty idCliente;
    private StringProperty motivo;
    private IntegerProperty cantidad;

    public Devoluciones() {
        this.idDevolucion = new SimpleLongProperty();
        this.idProducto = new SimpleLongProperty();
        this.idorden = new SimpleLongProperty();
        this.idCliente = new SimpleLongProperty();
        this.motivo = new SimpleStringProperty();
        this.cantidad = new SimpleIntegerProperty();
    }

    // Getters y setters
    public long getIdDevolucion() {
        return idDevolucion.get();
    }

    public void setIdDevolucion(long idDevolucion) {
        this.idDevolucion.set(idDevolucion);
    }

    public LongProperty idDevolucionProperty() {
        return idDevolucion;
    }

    public long getIdProducto() {
        return idProducto.get();
    }

    public void setIdProducto(long idProducto) {
        this.idProducto.set(idProducto);
    }

    public LongProperty idProductoProperty() {
        return idProducto;
    }

    public long getIdorden() {
        return idorden.get();
    }

    public void setIdorden(long idorden) {
        this.idorden.set(idorden);
    }

    public LongProperty idordenProperty() {
        return idorden;
    }

    public long getIdCliente() {
        return idCliente.get();
    }

    public void setIdCliente(long idCliente) {
        this.idCliente.set(idCliente);
    }

    public LongProperty idClienteProperty() {
        return idCliente;
    }

    public String getMotivo() {
        return motivo.get();
    }

    public void setMotivo(String motivo) {
        this.motivo.set(motivo);
    }

    public StringProperty motivoProperty() {
        return motivo;
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public IntegerProperty cantidadProperty() {
        return cantidad;
    }
}
