package ejemplo3;

public class Aplicacion3 {

    public static void main(String[] args) {
        System.out.println("Ejercicio complementario 3: numeros complejos\n");

        //demostracion de toString con los casos de la tabla del enunciado
        NumeroComplejo c1 = new NumeroComplejo(5, -3);
        NumeroComplejo c2 = new NumeroComplejo(0, 6.27);
        NumeroComplejo c3 = new NumeroComplejo(-8.4, 0);
        NumeroComplejo c4 = new NumeroComplejo(0, 0);
        NumeroComplejo c5 = new NumeroComplejo(-8.4, -2);
        NumeroComplejo c6 = new NumeroComplejo(6.1, 4);

        System.out.println("Real: 5, imaginario: -3i  -> " + c1);
        System.out.println("Real: 0, imaginario: 6.27i -> " + c2);
        System.out.println("Real: -8.4, imaginario: 0i -> " + c3);
        System.out.println("Real: 0, imaginario: 0i    -> " + c4);
        System.out.println("Real: -8.4, imaginario: -2i -> " + c5);
        System.out.println("Real: 6.1, imaginario: 4i  -> " + c6);

        //demostracion de Sumarle, Multiplicarle, Dividir y ElevarA
        System.out.println("\nOperaciones entre numeros complejos");
        System.out.format("%s + %s = %s\n", c1, c6, c1.Sumarle(c6));
        System.out.format("%s * %s = %s\n", c1, c6, c1.Multiplicarle(c6));
        System.out.format("%s / %s = %s\n", c1, c6, c1.Dividir(c6));
        System.out.format("(%s)^2 = %s\n", c6, c6.ElevarA(2));

        //demostracion del metodo Conjugada solicitado en el Ejercicio 3
        System.out.println("\nConjugadas");
        System.out.format("Conjugada de %s es %s\n", c1, c1.Conjugada());
        System.out.format("Conjugada de %s es %s\n", c6, c6.Conjugada());
    }
}
