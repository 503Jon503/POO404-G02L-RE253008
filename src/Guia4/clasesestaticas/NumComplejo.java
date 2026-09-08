package clasesestaticas;

public class NumComplejo {

    private double real;
    private double ima;

    private void asignarvalor(double r, double i) {
        this.real = r;
        this.ima = i;
    }

    public NumComplejo() {
        asignarvalor(0, 0);
    }

    public NumComplejo(double r, double i) {
        asignarvalor(0, 0);
        this.real = r;
        this.ima = i;
    }

    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getIma() {
        return ima;
    }

    public void setIma(double ima) {
        this.ima = ima;
    }

    public String vervalor() {
        //String con representacion del num. complejo almacenado en el objeto
        String res = "";
        //parte real del num. complejo
        res = String.format("%.2f", real);
        //parte imaginaria del num. complejo (si es positiva, antepone el signo +)
        if (ima >= 0) res += "+";
        res += String.format("%.2fi", ima);
        return res;
    }

    public NumComplejo getConjugada() {
        //por ej, la Conjugada del Complejo -4+3i es -4-3i
        NumComplejo temp = new NumComplejo();
        //se genera un numero complejo, resultado de invertir signo
        //de la parte imaginaria del valor almacenado en el objeto
        temp.setReal(getReal());
        temp.setIma(-1 * getIma());
        return temp; //retorna conjugada del num. complejo actual
    }
}
