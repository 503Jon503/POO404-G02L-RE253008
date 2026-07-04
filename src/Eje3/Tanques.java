package Eje3;
import javax.swing.JOptionPane;

public class Tanques {

    public static final double DENSIDAD_GASOLINA = 750.0;

    public static void main(String[] args) {

        double radio = solicitarRadio();
        double altura = solicitarAltura();

        double volumen = calcularVolumen(radio, altura);
        double litrosDiesel = calcularLitros(volumen);
        double kgGasolina = calcularGasolina(volumen);

        mostrarResultados(radio, altura, litrosDiesel, kgGasolina);

    }

    public static double solicitarRadio() {

        return Double.parseDouble(
                JOptionPane.showInputDialog("Ingrese el radio del tanque (m)")
        );

    }

    public static double solicitarAltura() {

        return Double.parseDouble(
                JOptionPane.showInputDialog("Ingrese la altura del tanque (m)")
        );

    }

    public static double calcularVolumen(double radio, double altura) {

        return Math.PI * Math.pow(radio,2) * altura;

    }

    public static double calcularLitros(double volumen) {

        return volumen * 1000;

    }

    public static double calcularGasolina(double volumen) {

        return volumen * DENSIDAD_GASOLINA;

    }

    public static void mostrarResultados(double radio,
                                         double altura,
                                         double litros,
                                         double gasolina) {

        System.out.println("Radio: " + radio + " m");
        System.out.println("Altura: " + altura + " m");
        System.out.println("Capacidad Diesel: " + litros + " litros");
        System.out.println("Capacidad Gasolina: " + gasolina + " kg");

    }

}