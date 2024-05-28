package org.adrian.pruebas;

public enum Rol {
    ADMIN("Administrador"),
    USUARIO("Usuario");

    private final String descripcion;

    Rol(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}