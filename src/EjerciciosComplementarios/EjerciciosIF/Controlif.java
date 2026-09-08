package EjerciciosIF;

import java.util.Scanner;
import javax.swing.JOptionPane;

public class Controlif {

    public static void main(String[] args) {
        //Llamada al metodo del ejercicio complementario 1
        ejemplo3();
    }

    static void ejemplo1() {
        Scanner reader = new Scanner(System.in);
        int Var1, Var2;
        System.out.print("Ingrese numero1: ");
        Var1 = reader.nextInt();
        System.out.print("Ingrese numero2: ");
        Var2 = reader.nextInt();
        if (Var1 == Var2)
            System.out.print("Los numeros ingresados son iguales ");
        else
            System.out.println("Los numeros ingresados No son iguales ");
    }

    static void ejemplo2() {
        String v1 = "";
        v1 = JOptionPane.showInputDialog("Ingrese un valor");
        if (v1 == null)
            JOptionPane.showMessageDialog(null, "Apretaste cancelar");
        else {
            if (v1.equals(""))
                JOptionPane.showMessageDialog(null,
                        "No ingresaste nada");
            else
                JOptionPane.showMessageDialog(null,
                        "El valor de V1 es: " + v1);
        }
        System.exit(0);
    }

    static void ejemplo3() {
        int result = JOptionPane.showConfirmDialog(null,
                "Replace existing selection?");
        System.out.println(
                "El numero devuelto por showConfirmDialog es: " + result);

        /* ---- Bloque original con if, ahora comentado ----
        if (result == JOptionPane.YES_OPTION)
            System.out.println("Yes");
        else if (result == JOptionPane.NO_OPTION)
            System.out.println("No");
        else if (result == JOptionPane.CANCEL_OPTION)
            System.out.println("Cancel");
        else if (result == JOptionPane.CLOSED_OPTION)
            System.out.println("Closed");
        */

        //Bloque nuevo, usando switch en lugar de los if
        switch (result) {
            case JOptionPane.YES_OPTION:
                System.out.println("Yes");
                break;
            case JOptionPane.NO_OPTION:
                System.out.println("No");
                break;
            case JOptionPane.CANCEL_OPTION:
                System.out.println("Cancel");
                break;
            case JOptionPane.CLOSED_OPTION:
                System.out.println("Closed");
                break;
        }

        System.exit(0);
    }

    static void ejemplo4() {
        String datos;
        double sueldo, afp = 0, isss, totalre, nsueldo, renta;
        datos = JOptionPane.showInputDialog(" Ingrese el sueldo del empleado (a) ");
        sueldo = Double.parseDouble(datos);
        if (sueldo > 300)
            afp = (sueldo * 0.0625);

        JOptionPane.showMessageDialog(null,
                " Este empleado tiene un sueldo de " + sueldo +
                        " y el descuento del AFP es " + afp);

        datos = (String) JOptionPane.showInputDialog(null,
                "Ingrese el Sexo:\nSi es Masculino (M)\nSi es Femenino (F)",
                "Sexo del Empleado", JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"M", "F"},
                "F");

        if (datos.equals("M")) {
            isss = sueldo * 0.03;
            renta = sueldo * 0.10;
            totalre = afp + isss + renta;
            nsueldo = sueldo - totalre;
            JOptionPane.showMessageDialog(null,
                    "A este empleado se le detiene " + isss +
                            " en concepto de ISSS\nAdemas se le retiene: " + renta +
                            " En concepto de Renta\nLo que hace un total de " +
                            totalre + "\nY su nuevo Sueldo es de: " + nsueldo);
        }
    }
}
