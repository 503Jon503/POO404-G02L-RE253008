package herencia;

public class Barco extends Vehiculo {

    protected Propulsion propulsion;

    public Barco(String nom, String marca, Propulsion tipopropulsion) {
        //inicializa campos
        tipovehiculo = "Barco";
        propulsion = Propulsion.MOTOR;
        //modifica campos segun valores de parametros
        setNombre(nom);
        setMarca(marca);
        setPropulsion(tipopropulsion);
    }

    public Propulsion getPropulsion() {
        return propulsion;
    }

    public void setPropulsion(Propulsion propulsion) {
        this.propulsion = propulsion;
    }

    //Reemplaza (override) al metodo heredado de la superclase Vehiculo
    public void verdatos() {
        //ej. Barco Portaaviones Ronald Reagan, Clase Nimitz, Propulsion: MOTOR
        String descrip = String.format(
                "%s %s, %s, Propulsion: %s\n",
                getTipovehiculo(), getNombre(), getMarca(),
                getPropulsion().toString());
        System.out.println(descrip);
    }
}
