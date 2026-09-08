package herencia;

public class Vehiculo {

    //tipovehiculo es "solo lectura" (solo tiene metodo get publico),
    //pero se declara protected para que las subclases puedan asignarlo directamente
    protected String tipovehiculo;
    protected String nombre;
    protected String marca;

    public Vehiculo() {
        tipovehiculo = "ninguno";
        nombre = "desconocido";
        marca = "pendiente";
    }

    //Metodo de propiedad de SOLO lectura
    public String getTipovehiculo() {
        return tipovehiculo;
    }

    //Metodos de propiedad (lectura y escritura)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void verdatos() {
        System.out.println("Este es un vehiculo genérico, sin nombre ni marca");
    }
}
