package clasesabstractas;

public abstract class UnidAlmac {

    protected int capac;   //capacidad de almacenamiento
    protected int rendim;  //rendimiento (velocidad de transferencia)

    //Metodo abstracto: cada subclase define su propia implementacion
    public abstract String mostrarparametros();
}
