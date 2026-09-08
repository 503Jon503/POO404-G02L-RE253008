package clasesestaticas;

public class MateComplejos {

    public static NumComplejo Suma(NumComplejo n1, NumComplejo n2) {
        NumComplejo res = new NumComplejo();
        res.setReal(n1.getReal() + n2.getReal());
        res.setIma(n1.getIma() + n2.getIma());
        return res;
    }

    public static NumComplejo Resta(NumComplejo n1, NumComplejo n2) {
        NumComplejo res = new NumComplejo();
        res.setReal(n1.getReal() - n2.getReal());
        res.setIma(n1.getIma() - n2.getIma());
        return res;
    }

    //(a+bi) * (c+di) = (ac - bd) + (ad + bc)i
    public static NumComplejo Multiplicar(NumComplejo n1, NumComplejo n2) {
        double a = n1.getReal(), b = n1.getIma();
        double c = n2.getReal(), d = n2.getIma();

        NumComplejo res = new NumComplejo();
        res.setReal((a * c) - (b * d));
        res.setIma((a * d) + (b * c));
        return res;
    }

    //(a+bi) / (c+di) = [(ac + bd) + (bc - ad)i] / (c^2 + d^2)
    public static NumComplejo Dividir(NumComplejo n1, NumComplejo n2) {
        double a = n1.getReal(), b = n1.getIma();
        double c = n2.getReal(), d = n2.getIma();
        double denominador = (c * c) + (d * d);

        NumComplejo res = new NumComplejo();
        if (denominador != 0) {
            res.setReal(((a * c) + (b * d)) / denominador);
            res.setIma(((b * c) - (a * d)) / denominador);
        }
        return res;
    }

    //Eleva un numero complejo a una potencia entera (n1^exponente),
    //mediante multiplicaciones sucesivas mediante el metodo Multiplicar
    public static NumComplejo Potencia(NumComplejo n1, int exponente) {
        NumComplejo res = new NumComplejo(1, 0); //1+0i (elemento neutro de la multiplicacion)
        for (int i = 0; i < exponente; i++) {
            res = Multiplicar(res, n1);
        }
        return res;
    }
}
