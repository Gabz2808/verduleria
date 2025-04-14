package com.example.verduleriaapp.models;

import java.util.Date;
import java.util.Objects;

public class Logs {
    private Long idLog;         // Representa el campo ID_LOG (PK)
    private Long idUsuario;     // Representa el campo ID_USUARIO (FK)
    private String accion;      // Representa el campo ACCION
    private Date fecha;         // Representa el campo FECHA

    // Constructor vacío
    public Logs() {
    }

    // Constructor con parámetros
    public Logs(Long idLog, Long idUsuario, String accion, Date fecha) {
        this.idLog = idLog;
        this.idUsuario = idUsuario;
        this.accion = accion;
        this.fecha = fecha;
    }

    // Getters y Setters
    public Long getIdLog() {
        return idLog;
    }

    public void setIdLog(Long idLog) {
        this.idLog = idLog;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
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
        Logs logs = (Logs) o;
        return Objects.equals(idLog, logs.idLog);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLog);
    }

    @Override
    public String toString() {
        return "Logs{" +
                "idLog=" + idLog +
                ", idUsuario=" + idUsuario +
                ", accion='" + accion + '\'' +
                ", fecha=" + fecha +
                '}';
    }
}