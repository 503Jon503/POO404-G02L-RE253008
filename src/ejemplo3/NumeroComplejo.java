package ejemplo3;

import ejemplo2.interfaces.INumero;

/**
 * Ejercicio complementario 3.
 * Abstrae el concepto de numero complejo (a + b.i), formado por una parte real (a)
 * y una parte imaginaria (b.i). Solamente implementa la interface INumero (usada en
 * la Parte 2 del procedimiento), tal como lo pide el enunciado del ejercicio.
 */
public class NumeroComplejo implements INumero {

    //campos privados: parte real e imaginaria del numero complejo
    private double real;
    private double imaginario;

    //encapsulamiento (getters/setters)
    public double getReal() {
        return real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginario() {
        return imaginario;
    }

    public void setImaginario(double imaginario) {
        this.imaginario = imaginario;
    }

    //constructores (sobrecarga)
    public NumeroComplejo() {
        setReal(0);
        setImaginario(0);
    }

    public NumeroComplejo(double real, double imaginario) {
        setReal(real);
        setImaginario(imaginario);
    }

    public NumeroComplejo(double real) { //numero complejo con parte imaginaria cero
        setReal(real);
        setImaginario(0);
    }

    //Sobreescritura de toString siguiendo la tabla de formato dada en el enunciado del
    //Ejercicio 3: se omite la parte que vale cero, salvo cuando ambas partes son cero.
    @Override
    public String toString() {
        boolean realEsCero = real == 0.0;
        boolean imgEsCero = imaginario == 0.0;

        if (realEsCero && imgEsCero) {
            return String.format("%.2f", 0.0);
        }
        if (imgEsCero) {
            return String.format("%.2f", real);
        }
        if (realEsCero) {
            return String.format("%.2fi", imaginario);
        }
        String signo = imaginario < 0 ? "-" : "+";
        return String.format("%.2f %s %.2fi", real, signo, Math.abs(imaginario));
    }

    //Suma de numeros complejos: (a+bi) + (c+di) = (a+c) + (b+d)i
    @Override
    public NumeroComplejo Sumarle(INumero valor) {
        NumeroComplejo resultado = new NumeroComplejo(this.real, this.imaginario);
        if (valor instanceof NumeroComplejo) {
            NumeroComplejo otro = (NumeroComplejo) valor;
            resultado = new NumeroComplejo(this.real + otro.real, this.imaginario + otro.imaginario);
        }
        return resultado;
    }

    //Multiplicacion de numeros complejos: (a+bi)*(c+di) = (ac-bd) + (ad+bc)i
    @Override
    public NumeroComplejo Multiplicarle(INumero valor) {
        NumeroComplejo resultado = new NumeroComplejo(0, 0);
        if (valor instanceof NumeroComplejo) {
            NumeroComplejo otro = (NumeroComplejo) valor;
            double nuevoReal = (this.real * otro.real) - (this.imaginario * otro.imaginario);
            double nuevoImag = (this.real * otro.imaginario) + (this.imaginario * otro.real);
            resultado = new NumeroComplejo(nuevoReal, nuevoImag);
        }
        return resultado;
    }

    //Division de numeros complejos: (a+bi)/(c+di) = ((ac+bd)/(c^2+d^2)) + ((bc-ad)/(c^2+d^2))i
    @Override
    public NumeroComplejo Dividir(INumero valor) {
        NumeroComplejo resultado = new NumeroComplejo(0, 0);
        if (valor instanceof NumeroComplejo) {
            NumeroComplejo otro = (NumeroComplejo) valor;
            double denominador = (otro.real * otro.real) + (otro.imaginario * otro.imaginario);
            if (denominador != 0) {
                double nuevoReal = ((this.real * otro.real) + (this.imaginario * otro.imaginario)) / denominador;
                double nuevoImag = ((this.imaginario * otro.real) - (this.real * otro.imaginario)) / denominador;
                resultado = new NumeroComplejo(nuevoReal, nuevoImag);
            }
        }
        return resultado;
    }

    //Potencia de un numero complejo elevado a un entero n (positivo o negativo).
    //Investigacion propia: se usa la forma polar del numero complejo (modulo r y angulo
    //theta) junto con la formula de De Moivre: (r*(cos(theta)+i*sen(theta)))^n =
    //r^n * (cos(n*theta) + i*sen(n*theta)). Esto evita tener que multiplicar el numero
    //complejo por si mismo de forma repetida y funciona tanto para exponentes positivos
    //como negativos (fuente: definicion estandar del Teorema de De Moivre para numeros
    //complejos en forma polar).
    @Override
    public NumeroComplejo ElevarA(int n) {
        double r = Math.hypot(real, imaginario); //modulo del numero complejo
        double theta = Math.atan2(imaginario, real); //angulo (argumento) del numero complejo

        double rN = Math.pow(r, n);
        double thetaN = theta * n;

        double nuevoReal = rN * Math.cos(thetaN);
        double nuevoImag = rN * Math.sin(thetaN);
        return new NumeroComplejo(nuevoReal, nuevoImag);
    }

    //Metodo local publico que solicita el Ejercicio 3: retorna la conjugada del numero
    //complejo almacenado en la instancia. La conjugada de (a+bi) es (a-bi).
    public INumero Conjugada() {
        return new NumeroComplejo(this.real, -this.imaginario);
    }
}
