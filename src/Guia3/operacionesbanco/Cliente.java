package operacionesbanco;

public class Cliente {

    //Campos encapsulados
    private String dui;
    private String nombre;
    private String apellido;

    //Metodo constructor
    public Cliente(String nom, String apell, String dui) {
        this.nombre = nom;
        this.apellido = apell;
        this.dui = dui;
    }

    //Metodos de propiedad (getters y setters)
    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
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

    public String DatosCliente() {
        //ej. de cadena que retorna:
        // Alfredo Ruiz (dui: 4175838-6)
        return String.format("%s %s (dui: %s)", this.nombre, this.apellido, this.dui);
    }
}
