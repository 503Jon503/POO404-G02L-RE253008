package herencia;

public class Terrestre extends Vehiculo {

    //Campo protegido, con solo accesor get (el set lo define cada subclase,
    //ya que la cantidad de ruedas valida depende del tipo de vehiculo terrestre)
    protected int totruedas;

    public int getTotruedas() {
        return totruedas;
    }
}
