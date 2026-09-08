package EjerciciosComplementarios;

import java.util.Random;

public class DescuentoCliente {

    public static void main(String[] args) {
        //Pruebas del metodo con distintos clientes y montos
        aplicarDescuento("Juan Perez", 50.00);
        aplicarDescuento("Maria Lopez", 120.50);
        aplicarDescuento("Carlos Rivas", 80.00);
    }

    static void aplicarDescuento(String nombreCliente, double montoCompra) {

        //Colores posibles de la bolita
        String[] colores = {"cafe", "roja", "azul", "verde"};

        //Se genera un numero aleatorio entre 0 y 3 para elegir el color
        Random random = new Random();
        int indice = random.nextInt(colores.length);
        String colorBolita = colores[indice];

        double porcentajeDescuento;

        switch (colorBolita) {
            case "cafe":
                porcentajeDescuento = 0.10;
                break;
            case "roja":
                porcentajeDescuento = 0.15;
                break;
            case "azul":
                porcentajeDescuento = 0.25;
                break;
            case "verde":
                porcentajeDescuento = 0.50;
                break;
            default:
                porcentajeDescuento = 0.0;
                break;
        }

        double montoDescuento = montoCompra * porcentajeDescuento;
        double montoFinal = montoCompra - montoDescuento;

        System.out.println("Cliente: " + nombreCliente);
        System.out.println("Monto de la compra: $" + montoCompra);
        System.out.println("Bolita generada: " + colorBolita +
                " -> Descuento del " + (int) (porcentajeDescuento * 100) + "%");
        System.out.println("Monto del descuento: $" + montoDescuento);
        System.out.println("Total a pagar: $" + montoFinal);
        System.out.println("-----------------------------------------");
    }
}
