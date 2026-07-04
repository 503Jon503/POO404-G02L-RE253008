package Eje2;

import javax.swing.JOptionPane;

public class Capacitores {

    public static void main(String[] args) {

        double c1, c2, c3;
        double serie, paralelo;

        String dato1 = JOptionPane.showInputDialog("Ingrese el valor del capacitor 1 (uF)");
        String dato2 = JOptionPane.showInputDialog("Ingrese el valor del capacitor 2 (uF)");
        String dato3 = JOptionPane.showInputDialog("Ingrese el valor del capacitor 3 (uF)");

        // Validar que no estén vacíos
        if (dato1 == null || dato2 == null || dato3 == null ||
                dato1.isEmpty() || dato2.isEmpty() || dato3.isEmpty()) {

            JOptionPane.showMessageDialog(null,
                    "No ingreso un valor apropiado.\nEl programa finalizara.");
            System.exit(0);
        }

        c1 = Double.parseDouble(dato1);
        c2 = Double.parseDouble(dato2);
        c3 = Double.parseDouble(dato3);

        // Validar que sean mayores que cero
        if (c1 <= 0 || c2 <= 0 || c3 <= 0) {

            JOptionPane.showMessageDialog(null,
                    "No ingreso un valor apropiado.\nEl programa finalizara.");
            System.exit(0);
        }

        serie = capacitorSerie(c1, c2, c3);
        paralelo = capacitorParalelo(c1, c2, c3);

        System.out.println("===== RESULTADOS =====");
        System.out.println("Capacitor 1: " + c1 + " uF");
        System.out.println("Capacitor 2: " + c2 + " uF");
        System.out.println("Capacitor 3: " + c3 + " uF");
        System.out.println("Equivalente en Serie: " + serie + " uF");
        System.out.println("Equivalente en Paralelo: " + paralelo + " uF");
    }

    public static double capacitorSerie(double c1, double c2, double c3) {

        return 1 / ((1 / c1) + (1 / c2) + (1 / c3));

    }

    public static double capacitorParalelo(double c1, double c2, double c3) {

        return c1 + c2 + c3;

    }

}