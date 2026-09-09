package negocio;

/**
 * Clase de negocio para la tabla "materia" del Ejercicio Complementario 1.
 * Columnas segun la guia: Cod_materia, Nombre, Descripcion.
 */
public class Materia {

    private int codMateria;
    private String nombre;
    private String descripcion;

    public Materia() {
    }

    public Materia(int codMateria, String nombre, String descripcion) {
        this.codMateria = codMateria;
        this.nombre = nombre;
        this.descripcion = descripcion;
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

    @Override
    public String toString() {
        return "Cod: " + codMateria + " | " + nombre + " | " + descripcion;
    }
}
