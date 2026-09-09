package com.example.javajframe.beans;

/**
 * Ejercicio Complementario (seccion IV): mantenimiento de Materia con formularios.
 */
public class MateriaBeans {

    private int codMateria;
    private String nombre;
    private String descripcion;

    public MateriaBeans() {
    }

    public MateriaBeans(int codMateria, String nombre, String descripcion) {
        setCodMateria(codMateria);
        setNombre(nombre);
        setDescripcion(descripcion);
    }

    public int getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(int codMateria) {
        this.codMateria = codMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
