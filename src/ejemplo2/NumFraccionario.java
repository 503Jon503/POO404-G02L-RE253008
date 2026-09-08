package ejemplo2;

import ejemplo2.interfaces.IComparable;
import ejemplo2.interfaces.INumero;

public class NumFraccionario implements INumero, IComparable {

    //campos privados indicados en el diagrama UML
    private int num;
    private int deno;

    //encapsulamiento (getters/setters)
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDeno() {
        return deno;
    }

    public void setDeno(int deno) {
        // para evitar una fraccion con denominador cero, ej: 8/0, -4/0, etc.
        if (deno != 0)
            this.deno = deno;
    }

    //constructores (sobrecarga)
    public NumFraccionario() {
        //asigna entero 0, pero en formato de una fraccion (0/1)
        setNum(0);
        setDeno(1);
    }

    public NumFraccionario(int num, int deno) { // Sobrecarga 1
        setDeno(1); //evitar fraccion con denominador 0

        setNum(num);
        setDeno(deno);
    }

    public NumFraccionario(int entero) { // Sobrecarga 2
        //para almacenar un entero como una fraccion, ej: 3 se expresara como 3/1
        setNum(entero);
        setDeno(1);
    }

    // Ejercicio complementario 1: si la fraccion (segun sus valores originales, sin
    // simplificar) es impropia (|numerador| >= denominador), se muestra en formato de
    // fraccion mixta: entero(residuo/denominador). Si es propia, se muestra tal cual
    // esta almacenada (comportamiento original del procedimiento).
    @Override
    public String toString() {
        int n = getNum();
        int d = getDeno();

        if (d == 1) {
            return String.valueOf(n);
        }

        if (Math.abs(n) >= d) {
            // Nota: se usa Math.abs() antes de invocar a calcularMCD() porque ese metodo
            // (tal como esta definido mas abajo, siguiendo el algoritmo de Euclides con el
            // operador % de Java) puede retornar un valor negativo cuando el primer
            // argumento es negativo. Forzar valores absolutos garantiza un MCD positivo
            // y una simplificacion correcta para el formato mixto.
            int mcd = calcularMCD(Math.abs(n), Math.abs(d));
            if (mcd == 0) mcd = 1;

            int nSimp = n / mcd;
            int dSimp = d / mcd;
            if (dSimp < 0) { //normaliza signo para que el denominador quede positivo
                nSimp = -nSimp;
                dSimp = -dSimp;
            }

            int parteEntera = nSimp / dSimp; //division entera trunca hacia cero
            int residuo = Math.abs(nSimp % dSimp);

            if (residuo == 0 || dSimp == 1) {
                return String.valueOf(parteEntera);
            }
            return parteEntera + "(" + residuo + "/" + dSimp + ")";
        }

        //fraccion propia: se muestra tal como esta almacenada (sin simplificar)
        return n + "/" + d;
    }

    //Operaciones propias sobre solamente fracciones (a/b)

    // Metodo para calcular el Maximo Comun Divisor (Algoritmo de Euclides)
    public int calcularMCD(int a, int b) {
        int temp;
        while (b != 0) {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Metodo para calcular el MCM usando el MCD
    public int calcularMCM(int a, int b) {
        int mcm;
        if (a == 0 || b == 0) {
            return 0; // El MCM con 0 es 0
        }
        mcm = (a * b) / calcularMCD(a, b);
        return mcm;
    }

    public int calcularMCM(NumFraccionario a, NumFraccionario b) { //(sobrecarga)
        //extrae denominadores para invocar a sobrecarga que recibe solo denominadores
        return calcularMCM(a.getDeno(), b.getDeno());
    }

    public int calcularMCD(NumFraccionario a, NumFraccionario b) { //(sobrecarga)
        //extrae denominadores para invocar a sobrecarga que recibe solo denominadores
        return calcularMCD(a.getDeno(), b.getDeno());
    }

    // simplificar valor de fraccion interna de esta instancia
    // Nota (decision propia): se calcula el mcd con Math.abs() y se normaliza el signo del
    // denominador al final. Esto es necesario porque calcularMCD(int,int), tal como esta
    // definido arriba siguiendo el algoritmo de Euclides con el operador % de Java, puede
    // retornar un valor negativo cuando alguno de sus argumentos es negativo (efecto
    // secundario del operador % con numeros negativos en Java). Sin esta correccion, una
    // fraccion como -32/84 podria simplificarse incorrectamente a 8/-21 en vez de -8/21.
    public NumFraccionario Simplificar() {
        int mcd = calcularMCD(Math.abs(getNum()), Math.abs(getDeno()));
        if (mcd == 0) mcd = 1;
        int nSimp = getNum() / mcd;
        int dSimp = getDeno() / mcd;
        if (dSimp < 0) {
            nSimp = -nSimp;
            dSimp = -dSimp;
        }
        setNum(nSimp);
        setDeno(dSimp);
        //de manera opcional, retorna una nueva instancia de fraccion simplificada
        return new NumFraccionario(getNum(), getDeno());
    }

    //(sobrecarga)
    // simplifica fraccion recibida con numerador y denominador recibidos
    // (ver nota de correccion de signo explicada en Simplificar() sin parametros)
    public NumFraccionario Simplificar(int num, int deno) {
        NumFraccionario r = new NumFraccionario();
        int mcd = calcularMCD(Math.abs(num), Math.abs(deno));
        if (mcd == 0) mcd = 1;

        int nSimp = num / mcd;
        int dSimp = deno / mcd;
        if (dSimp < 0) {
            nSimp = -nSimp;
            dSimp = -dSimp;
        }
        r.setNum(nSimp);
        r.setDeno(dSimp);
        return r;
    }

    private boolean EsNegativa() {
        //retorna true si numerador de fraccion interna es negativa
        if (this.getNum() < 0) return true;
        return false;
    }

    public double aDecimal() {
        //retorna valor de cociente de dividir numerador y denominador
        return (double) this.getNum() / (double) this.getDeno();
    }

    //Implementacion final (paso 29) del prototipo Sumarle de la interface INumero
    @Override
    public NumFraccionario Sumarle(INumero valor) {
        //define fraccion con valor cero (0/1), en caso que no pueda hacer la operacion
        NumFraccionario s = new NumFraccionario();
        //prueba si instancia recibida es de este mismo tipo de clase NumFraccionario
        if (valor instanceof NumFraccionario) {
            NumFraccionario a, b;
            a = new NumFraccionario(getNum(), getDeno()); //valor de fraccion interna
            b = (NumFraccionario) valor; //convierte parametro a objeto de clase NumFraccionario
            //Calcula suma de fraccion interna de esta instancia con objeto recibido en parametros
            //y actualiza valor de fraccion interna con este resultado
            int mcm = calcularMCM(a.getDeno(), b.getDeno());

            s.setNum(mcm / getDeno() * getNum() + mcm / b.getDeno() * b.getNum());
            s.setDeno(mcm);
            //simplifica fraccion obtenida
            s = Simplificar(s.getNum(), s.getDeno());
        }
        return s;
    }

    //Decision propia: el diagrama UML del paso 15 incluye a "Multiplicarle" como metodo
    //sobreescrito de NumFraccionario, aunque el procedimiento escrito nunca detalla su
    //cuerpo (queda como prototipo comentado en la interface base). Se implementa aqui
    //siguiendo la regla matematica de multiplicacion de fracciones: (a/b)*(c/d) = (a*c)/(b*d)
    @Override
    public NumFraccionario Multiplicarle(INumero valor) {
        NumFraccionario resultado = new NumFraccionario();
        if (valor instanceof NumFraccionario) {
            NumFraccionario otro = (NumFraccionario) valor;
            int numResultado = this.getNum() * otro.getNum();
            int denoResultado = this.getDeno() * otro.getDeno();
            resultado = Simplificar(numResultado, denoResultado);
        }
        return resultado;
    }

    //Prototipo activado en el paso 33. Formula: (a/b) / (c/d) = (a*d)/(b*c)
    @Override
    public NumFraccionario Dividir(INumero valor) {
        NumFraccionario resultado = new NumFraccionario();
        if (valor instanceof NumFraccionario) {
            NumFraccionario otro = (NumFraccionario) valor;
            int numResultado = this.getNum() * otro.getDeno();
            int denoResultado = this.getDeno() * otro.getNum();
            //normaliza el signo para que el denominador quede siempre positivo
            if (denoResultado < 0) {
                numResultado = -numResultado;
                denoResultado = -denoResultado;
            }
            resultado = Simplificar(numResultado, denoResultado);
        }
        return resultado;
    }

    //Prototipo activado en el paso 34. Soporta exponentes positivos y negativos:
    //(a/b)^n si n>=0, o (b/a)^|n| si n<0 (una potencia negativa invierte la fraccion)
    @Override
    public NumFraccionario ElevarA(int n) {
        int numResultado, denoResultado;
        if (n >= 0) {
            numResultado = potenciaEntera(getNum(), n);
            denoResultado = potenciaEntera(getDeno(), n);
        } else {
            int exp = -n;
            numResultado = potenciaEntera(getDeno(), exp);
            denoResultado = potenciaEntera(getNum(), exp);
        }
        if (denoResultado < 0) {
            numResultado = -numResultado;
            denoResultado = -denoResultado;
        }
        return Simplificar(numResultado, denoResultado);
    }

    //metodo auxiliar privado para calcular potencias enteras (base^exponente), exponente >= 0
    private int potenciaEntera(int base, int exponente) {
        int resultado = 1;
        for (int i = 0; i < exponente; i++) {
            resultado *= base;
        }
        return resultado;
    }

    //Implementacion final (paso 30) del prototipo EsMayorQue de la interface IComparable
    @Override
    public boolean EsMayorQue(IComparable valor) {
        if (valor instanceof NumFraccionario) {
            //compara fraccion interna con fraccion recibida en objeto
            NumFraccionario p1, p2;

            p1 = new NumFraccionario(getNum(), getDeno());
            p2 = (NumFraccionario) valor;

            if (!p1.EsNegativa() && !p2.EsNegativa()) //Si ambas son positivas
            {
                if (p1.aDecimal() > p2.aDecimal()) return true;
            } else if (p1.EsNegativa() && p2.EsNegativa()) //si ambas son negativas
            {
                //cambia sentido de prueba
                if (p2.aDecimal() > p1.aDecimal()) return true;
            } else
                //tienen signos distintos, si fraccion interna tiene signo positivo, sera mayor
                if (!this.EsNegativa()) return true;
        }
        return false; //fraccion local es menor que fraccion recibida
    }

    //Decision propia: el diagrama UML del paso 15 incluye a "EsMenorQue" como metodo
    //sobreescrito de NumFraccionario, aunque el codigo base de la interface lo deja
    //comentado. Se implementa comparando los valores decimales de ambas fracciones.
    @Override
    public boolean EsMenorQue(IComparable valor) {
        if (valor instanceof NumFraccionario) {
            NumFraccionario otro = (NumFraccionario) valor;
            return this.aDecimal() < otro.aDecimal();
        }
        return false;
    }
}
