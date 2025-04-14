package com.example.verduleriaapp.models;

import java.util.Date;
import java.util.Objects;

public class Alertas {
    private Long idAlerta;         // Representa el campo ID_ALERTA (PK)
    private String tipo;           // Representa el campo TIPO
    private String mensaje;        // Representa el campo MENSAJE
    private Date fecha;            // Representa el campo FECHA

    // Constructor vacío
    public Alertas() {
    }

    // Constructor con parámetros
    public Alertas(Long idAlerta, String tipo, String mensaje, Date fecha) {
        this.idAlerta = idAlerta;
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    // Getters y Setters
    public Long getIdAlerta() {
        return idAlerta;
    }

    public void setIdAlerta(Long idAlerta) {
        this.idAlerta = idAlerta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    // Métodos adicionales: equals, hashCode y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alertas alertas = (Alertas) o;
        return Objects.equals(idAlerta, alertas.idAlerta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlerta);
    }

    @Override
    public String toString() {
        return "Alertas{" +
                "idAlerta=" + idAlerta +
                ", tipo='" + tipo + '\'' +
                ", mensaje='" + mensaje + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}