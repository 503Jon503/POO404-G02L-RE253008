package com.example.javajframe.beans;

/**
 * Ejercicio Complementario (seccion IV): mantenimiento de Alumno con
 * formularios. Se sigue el mismo estilo de JavaBean usado en PersonaBeans.
 */
public class AlumnoBeans {

    private int codAlumno;
    private String nombre;
    private String apellido;
    private int edad;
    private String direccion;

    public AlumnoBeans() {
    }

    public AlumnoBeans(int codAlumno, String nombre, String apellido, int edad, String direccion) {
        setCodAlumno(codAlumno);
        setNombre(nombre);
        setApellido(apellido);
        setEdad(edad);
        setDireccion(direccion);
    }

    public int getCodAlumno() {
        return codAlumno;
    }

    public void setCodAlumno(int codAlumno) {
        this.codAlumno = codAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
