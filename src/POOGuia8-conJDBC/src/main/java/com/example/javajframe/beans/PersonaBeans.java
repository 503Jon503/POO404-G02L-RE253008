package com.example.javajframe.beans;

/**
 * Pasos 9-11 de la Guia de Laboratorio #8. Campos segun el UML mostrado.
 */
public class PersonaBeans {

    private int idPersona;
    private String nombre;
    private int edad;
    private String telefono;
    private String sexo;
    private int idOcupacion;
    private String fechanacimiento;

    public PersonaBeans() {
    }

    public PersonaBeans(int idPersona, String nombre,
                         int edad, String telefono, String sexo,
                         int idOcupacion, String fechanacimiento) {
        setIdPersona(idPersona);
        setNombre(nombre);
        setEdad(edad);
        setTelefono(telefono);
        setSexo(sexo);
        setIdOcupacion(idOcupacion);
        setFechaNacimiento(fechanacimiento);
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdOcupacion() {
        return idOcupacion;
    }

    public void setIdOcupacion(int idOcupacion) {
        this.idOcupacion = idOcupacion;
    }

    public String getFechaNacimiento() {
        return fechanacimiento;
    }

    public void setFechaNacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }
}
