package ejemplo2;

import ejemplo2.interfaces.INumero;

import java.util.ArrayList;
import java.util.List;

public class Aplicacion2 {

    public static void main(String[] args) {
        //instancias de clase NumFraccionario
        NumFraccionario f0, f1, f2, f3, f4;
        NumFraccionario r1, r2, r3;
        //para almacenar calculos
        int mcm, mcd;
        System.out.println("Operaciones con Fracciones");
        //inicializa instancias
        f0 = new NumFraccionario();
        f1 = new NumFraccionario(2, 5); // 2/5
        f2 = new NumFraccionario(3, 8); // 3/8
        f3 = new NumFraccionario(-168, 210); // -168/210
        f4 = new NumFraccionario(3); //  3/1
        System.out.format("Las fracciones para las pruebas seran %s, %s, %s, %s y %s\n\n",
                f0, f1, f2, f3, f4);

        mcm = f0.calcularMCM(630, 1050);
        mcd = f0.calcularMCD(630, 1050);
        System.out.format("El mcm de 630 y 1050 es %d, el mcd es %d\n", mcm, mcd);

        mcm = f0.calcularMCM(f1, f2);
        mcd = f0.calcularMCD(f1, f2);
        System.out.format("El mcm de los denominadores de %s y %s es %d, el mcd es %d\n",
                f1, f2, mcm, mcd);
        System.out.format("La fraccion equivalente y simplificada de %s ", f3);
        r1 = f3.Simplificar();
        System.out.format("es %s\n", r1);

        System.out.format("La fraccion equivalente y simplificada de 300/270 es %s\n",
                f0.Simplificar(300, 270));

        f0 = new NumFraccionario(-23, 125); //fraccion -23/125
        System.out.format("El valor decimal de la fraccion %s es %.4f\n",
                f0, f0.aDecimal());

        //paso 31: demostracion de Sumarle y EsMayorQue
        r2 = f1.Sumarle(f2); //calcula suma de 3er numero con 4to numero
        System.out.format("\nLa suma de %s con %s es %s\n", f1, f2, r2);

        //suma un entero con la fraccion
        f0 = f1.Sumarle(f4);
        System.out.format("Suma de %s y %s es %s\n", f1, f4, f0);

        if (f1.EsMayorQue(f4)) //compara fraccion f1 con f4
            System.out.format("La fraccion %s es mayor que %s\n", f1, f4);
        else
            System.out.format("La fraccion %s es mayor que %s\n", f4, f1);

        //paso 33: demostracion del metodo Dividir, con la fraccion 2/3 como base
        NumFraccionario base = new NumFraccionario(2, 3);
        System.out.println("\nDemostracion del metodo Dividir (base = " + base + ")");
        System.out.format("%s / %s = %s\n", base, new NumFraccionario(4, 9),
                base.Dividir(new NumFraccionario(4, 9)));
        System.out.format("%s / %s = %s\n", base, new NumFraccionario(-3, 7),
                base.Dividir(new NumFraccionario(-3, 7)));
        System.out.format("%s / %s = %s\n", base, new NumFraccionario(-28, 16),
                base.Dividir(new NumFraccionario(-28, 16)));
        //Nota: la tabla de la guia usa un cuarto ejemplo con NFraccionario(16) cuyo resultado
        //esperado es -1/24; ese resultado solo es posible si el entero recibido es -16
        //(probable error de digitado/OCR en el enunciado). Se demuestra aqui con -16 para
        //reproducir fielmente el resultado esperado por la guia.
        System.out.format("%s / %s = %s\n", base, new NumFraccionario(-16),
                base.Dividir(new NumFraccionario(-16)));

        //paso 34: 3 demostraciones del metodo ElevarA
        System.out.println("\nDemostracion del metodo ElevarA");
        NumFraccionario p1 = new NumFraccionario(1, 3);
        System.out.format("(%s)^2 = %s\n", p1, p1.ElevarA(2));
        NumFraccionario p2 = new NumFraccionario(1, 2);
        System.out.format("(%s)^-3 = %s\n", p2, p2.ElevarA(-3));
        NumFraccionario p3 = new NumFraccionario(-6, 10);
        System.out.format("(%s)^-3 = %s\n", p3, p3.ElevarA(-3));

        //====================================================================
        //Ejercicio complementario 1: demostracion del formato de fraccion mixta
        //====================================================================
        System.out.println("\nEjercicio complementario 1: fracciones mixtas");
        NumFraccionario m1 = new NumFraccionario(52, 14);
        NumFraccionario m2 = new NumFraccionario(-280, 84);
        NumFraccionario m3 = new NumFraccionario(-525, 140);
        System.out.format("52/14 = %s\n", m1);
        System.out.format("-280/84 = %s\n", m2);
        System.out.format("-525/140 = %s\n", m3);

        //====================================================================
        //Ejercicio complementario 2: demostracion del metodo procesarFracciones
        //====================================================================
        System.out.println("\nEjercicio complementario 2: suma de una coleccion de fracciones");
        List<NumFraccionario> lista = new ArrayList<>();
        lista.add(new NumFraccionario(1, 2));
        lista.add(new NumFraccionario(1, 3));
        lista.add(new NumFraccionario(2, 5));
        procesarFracciones(lista);

        //demostracion con coleccion vacia (debe mostrar el mensaje de error)
        procesarFracciones(new ArrayList<NumFraccionario>());
    }

    /**
     * Ejercicio complementario 2.
     * Recibe una coleccion de NumFraccionario, imprime cada una en pantalla y al final
     * muestra la suma de todos los valores recibidos. Si la coleccion no trae al menos
     * una fraccion, se informa el error y finaliza sin procesar nada mas.
     */
    public static void procesarFracciones(List<NumFraccionario> fracciones) {
        if (fracciones == null || fracciones.isEmpty()) {
            System.out.println("Datos recibidos son incompletos, fin de ejecucion del metodo");
            return;
        }

        System.out.println("Fracciones recibidas:");
        NumFraccionario suma = new NumFraccionario(); //inicia en 0/1
        for (NumFraccionario f : fracciones) {
            System.out.println("- " + f);
            //Sumarle retorna NumFraccionario, pero se declara con el tipo de la interface
            //INumero para reforzar el uso del polimorfismo pedido por la guia
            INumero acumulado = suma.Sumarle(f);
            suma = (NumFraccionario) acumulado;
        }
        System.out.format("La suma de las %d fracciones recibidas es %s\n",
                fracciones.size(), suma);
    }
}
