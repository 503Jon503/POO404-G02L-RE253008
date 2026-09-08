package aplicacion;

import herencia.*;
import clasesestaticas.*;
import clasesabstractas.*;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        //Descomente UNICAMENTE el metodo de demostracion que desee ejecutar

        demoherencia();
        //democlaseestatica();
        //democlasescomplejas();
        //demoabstraccion1();
        //demoabstraccion2();
        //demoabstraccion3();
    }

    //====================== PARTE 1: HERENCIA ======================
    static void demoherencia() {
        Barco velero, ronald;
        System.out.println("Demostracion del principio de herencia de clases\n");

        velero = new Barco("Paraiso", "Marca Leonard", Propulsion.VELAS);
        velero.verdatos();

        ronald = new Barco("Titanic", "Clase Olympic", Propulsion.MOTOR);
        ronald.setNombre("Portaaviones Ronald Reagan");
        ronald.setMarca("Clase Nimitz");
        ronald.verdatos();

        Moto honda = new Moto("Honda ATV", "Modelo 2024", 4, 420);
        honda.verdatos();

        //---------- Ejercicio complementario 1: demostracion de la clase Camion ----------
        System.out.println("\nDemostracion de la subclase Camion:");

        //Objeto 1: creado con datos apropiados
        Camion volquete = new Camion("Volvo FH16", "Modelo 2023", 10, 25);
        volquete.verdatos();

        //Objeto 2: creado con cantidad de ruedas invalida (7 no es un valor
        //aceptado, por lo que conserva el valor predeterminado de 6 ruedas)
        Camion cisterna = new Camion("Kenworth T800", "Modelo 2022", 7, 30);
        cisterna.verdatos();

        //Objeto 3: se crea con datos por defecto y luego se modifican sus
        //campos de manera individual
        Camion repartidor = new Camion("Isuzu NPR", "Modelo 2021", 6, 5);
        repartidor.setNombre("Isuzu Elf");
        repartidor.setMarca("Modelo 2024");
        repartidor.setTotruedas(12);
        repartidor.setTonelaje(15);
        repartidor.verdatos();

        System.exit(0);
    }

    //====================== PARTE 2: CLASES ESTATICAS ======================
    static void democlaseestatica() {
        NumComplejo a, b, c, d, e;

        a = new NumComplejo();      // 0+0i
        b = new NumComplejo(3, 8);  // 3+8i
        c = new NumComplejo(-2, -5);// -2-5i
        d = new NumComplejo(0, -4); // 0-4i

        System.out.printf("a = %s \nb = %s \nc = %s \nd = %s",
                a.vervalor(), b.vervalor(), c.vervalor(), d.vervalor());

        e = b.getConjugada();
        System.out.printf("\nla conjugada de complejo b es %s\n", e.vervalor());

        System.exit(0);
    }

    static void democlasescomplejas() {
        NumComplejo b = new NumComplejo(3, 8);
        NumComplejo c = new NumComplejo(-2, -5);
        NumComplejo d = new NumComplejo(0, -4);

        NumComplejo suma, resta, prod, div, pot;

        suma = MateComplejos.Suma(b, c);
        System.out.printf("La suma de %s con %s es %s\n",
                b.vervalor(), c.vervalor(), suma.vervalor());

        resta = MateComplejos.Resta(b, d);
        System.out.printf("La resta de %s con %s es %s\n",
                b.vervalor(), d.vervalor(), resta.vervalor());

        //---------- Ejercicio complementario 2: Multiplicar, Dividir y Potencia ----------
        NumComplejo n1 = new NumComplejo(4, -6);
        NumComplejo n2 = new NumComplejo(-3, 2);

        prod = MateComplejos.Multiplicar(n1, n2);
        System.out.printf("La multiplicacion de %s con %s es %s\n",
                n1.vervalor(), n2.vervalor(), prod.vervalor());

        div = MateComplejos.Dividir(n1, n2);
        System.out.printf("La division de %s entre %s es %s\n",
                n1.vervalor(), n2.vervalor(), div.vervalor());

        pot = MateComplejos.Potencia(b, 2);
        System.out.printf("La potencia de %s elevado a 2 es %s\n",
                b.vervalor(), pot.vervalor());

        System.exit(0);
    }

    //====================== PARTE 3: CLASES ABSTRACTAS ======================
    static void demoabstraccion1() {
        Scanner teclado = new Scanner(System.in);
        HDD Kington, Seagate;

        Kington = new HDD();
        Seagate = new HDD(2048, 175, 5400, 3);

        System.out.println("Estado inicial de par de discos duros (HDD):");
        System.out.println("* Kington: " + Kington.mostrarparametros());
        System.out.println("* Seagate: " + Seagate.mostrarparametros());

        System.out.print("\nIngrese la nueva capacidad (en GB) de Kington ? ");
        Kington.setCapac(teclado.nextInt());
        System.out.println("Nuevo estado del HDD Kington:");
        System.out.println("* Kington: " + Kington.mostrarparametros());

        System.out.print("\nAhora ingrese el nuevo rendimiento (en MB/s) de Seagate ? ");
        Seagate.setRendim(teclado.nextInt());
        System.out.println("Nuevo estado del HDD Seagate:");
        System.out.println("* Seagate: " + Seagate.mostrarparametros());

        System.exit(0);
    }

    static void demoabstraccion2() {
        Scanner teclado = new Scanner(System.in);
        SSD disco1, disco2;

        disco1 = new SSD(); //interfaz SATA predeterminada
        disco2 = new SSD('P', 3, 5000); //asigna interfaz PCIe

        System.out.println("Estado inicial de par de medios (SSD):");
        System.out.println("* SSD 1: " + disco1.mostrarparametros());
        System.out.println("* SSD 2: " + disco2.mostrarparametros());

        System.out.print("\nIngrese la nueva capacidad (256 o 512) GB de SSD 1 ? ");
        disco1.setCapac(teclado.nextInt());
        System.out.println("Nuevo estado del SSD 1:");
        System.out.println("* SSD 1: " + disco1.mostrarparametros());

        System.out.print("\nIngrese letra (s: SATA o p: PCIe) para cambiar tipo Interfaz del SSD 2? ");
        disco2.setInterfaz(teclado.next().charAt(0));
        System.out.println("Nuevo estado del SSD 2:");
        System.out.println("* SSD 2: " + disco2.mostrarparametros());

        System.exit(0);
    }

    //---------- Ejercicio complementario 3: demostracion de la clase MicroSD ----------
    static void demoabstraccion3() {
        Scanner teclado = new Scanner(System.in);
        MicroSD sd1, sd2, sd3;

        sd1 = new MicroSD();                          //valores predeterminados (UHS-I)
        sd2 = new MicroSD(64, 200, 10, "UHS-II");
        sd3 = new MicroSD(128, 400, 6, "UHS-III");

        System.out.println("Estado inicial de las memorias microSD:");
        System.out.println("* SD 1: " + sd1.mostrarparametros());
        System.out.println("* SD 2: " + sd2.mostrarparametros());
        System.out.println("* SD 3: " + sd3.mostrarparametros());

        System.out.print("\nIngrese el nuevo tipo de bus de SD 1 (UHS-I, UHS-II o UHS-III): ");
        sd1.setTipoBus(teclado.nextLine());
        System.out.println("Nuevo estado de SD 1: " + sd1.mostrarparametros());

        System.out.print("\nIngrese la nueva capacidad (en GB) de SD 2: ");
        sd2.setCapac(teclado.nextInt());
        System.out.println("Nuevo estado de SD 2: " + sd2.mostrarparametros());

        System.out.print("\nIngrese la nueva clase de velocidad (2, 4, 6 o 10) de SD 3: ");
        sd3.setClaseVelocidad(teclado.nextInt());
        System.out.println("Nuevo estado de SD 3: " + sd3.mostrarparametros());

        System.exit(0);
    }
}
