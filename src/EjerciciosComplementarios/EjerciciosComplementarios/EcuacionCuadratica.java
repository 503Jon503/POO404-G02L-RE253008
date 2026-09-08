package EjerciciosComplementarios;

public class EcuacionCuadratica {

    public static void main(String[] args) {
        //Se prueban las 3 ecuaciones propuestas en la guia
        System.out.println("Ecuacion 1: 16x^2 + 0x + 9 = 0");
        calcularRaices(16, 0, 9);

        System.out.println("\nEcuacion 2: 1x^2 - 2x + 5 = 0");
        calcularRaices(1, -2, 5);

        System.out.println("\nEcuacion 3: 4x^2 - 4x + 7 = 0");
        calcularRaices(4, -4, 7);
    }

    //Metodo complementario que calcula el determinante (discriminante)
    static double calcularDeterminante(double a, double b, double c) {
        return (b * b) - (4 * a * c);
    }

    //Metodo principal que calcula e imprime las raices de la ecuacion
    static void calcularRaices(double a, double b, double c) {

        if (a == 0) {
            System.out.println("El coeficiente 'a' no puede ser 0 (no seria una ecuacion cuadratica).");
            return;
        }

        double determinante = calcularDeterminante(a, b, c);
        System.out.println("Determinante: " + determinante);

        if (determinante > 0) {
            //Raices reales y distintas
            double raiz1 = (-b + Math.sqrt(determinante)) / (2 * a);
            double raiz2 = (-b - Math.sqrt(determinante)) / (2 * a);
            System.out.println("Raices reales:");
            System.out.println("x1 = " + raiz1);
            System.out.println("x2 = " + raiz2);

        } else if (determinante == 0) {
            //Raiz real doble
            double raiz = -b / (2 * a);
            System.out.println("Raiz real doble:");
            System.out.println("x = " + raiz);

        } else {
            //Raices complejas conjugadas
            double parteReal = -b / (2 * a);
            double parteImaginaria = Math.sqrt(-determinante) / (2 * a);
            System.out.println("Raices complejas:");
            System.out.println("x1 = " + parteReal + " + " + parteImaginaria + "i");
            System.out.println("x2 = " + parteReal + " - " + parteImaginaria + "i");
        }
    }
}
