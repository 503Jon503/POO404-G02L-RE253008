package aplicacion;

import modelos.*;
import java.util.Scanner;

/**
 * Clase Principal del Procedimiento (Partes 2 a 5).
 *
 * La guia pide ir comentando/descomentando manualmente cada prueba dentro
 * de main(). Para que el proyecto quede funcional sin abrir un IDE, se
 * conserva el mismo codigo de cada paso pero organizado en un menu de
 * consola: cada opcion ejecuta exactamente lo que la guia solicita en su
 * respectiva parte.
 */
public class Principal {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== Guia de Laboratorio #7 - JDBC (POO404) ===");
        System.out.println("1. Parte 2: VerPersonas (listar personas por consola)");
        System.out.println("2. Parte 4 (paso 22-25): probar Testing.compareTelephone / compareDate");
        System.out.println("3. Parte 4 (paso 27-33): IngresoDatos (ingresar persona con JOptionPane)");
        System.out.println("4. Parte 5 (paso 40-46): VerificarNulos (nulo vs vacio)");
        System.out.print("Seleccione una opcion: ");
        String opcion = sc.nextLine();

        switch (opcion) {
            case "1":
                // Paso 17: new VerPersonas();
                new VerPersonas();
                break;

            case "2":
                // Paso 23: prueba de la clase Testing
                System.out.print("numero de telefono ");
                if (Testing.compareTelephone("2526-1485"))
                    System.out.println("es valido");
                else
                    System.out.println("es incorrecto");

                System.out.print("fecha ");
                if (Testing.compareDate("2021-06-14"))
                    System.out.println("es valido");
                else
                    System.out.println("es incorrecto");
                break;

            case "3":
                // Paso 32: IngresoDatos ing = new IngresoDatos(); ing.mostrardatos(); ing.cierreconexion();
                IngresoDatos ing = new IngresoDatos();
                ing.mostrardatos();
                ing.cierreconexion();
                break;

            case "4":
                // Paso 45: new VerificarNulos();
                new VerificarNulos();
                break;

            default:
                System.out.println("Opcion invalida.");
        }
    }
}
