package ejemplo1;

import java.util.Scanner;

public class Aplicacion1 {

    //metodo de inicio de la aplicacion
    public static void main(String[] args) {
        //objeto que almacenara solamente a instancias de clases derivadas de interface IPago
        IPago metodopago = null;

        Scanner sc = new Scanner(System.in);
        float monto; //valor de monto a pagar
        int tipopago; //eleccion de metodo para pagar monto
        String resultado;

        System.out.println("Tienda de conveniencia\n");
        System.out.print("Ingrese valor del monto a realizar: $ ");
        monto = sc.nextFloat();
        System.out.println("\nMetodos de Pago:");
        System.out.println("\t1. Tarjeta de credito\n\t2. Bitcoins\n\t3. Efectivo");
        System.out.println("Seleccione numero con metodo de pago del monto ingresado: ");
        tipopago = sc.nextInt();

        switch (tipopago) {
            case 1:
                metodopago = new PagoConTarjeta();
                break;
            case 2:
                metodopago = new PagoConBitcoin();
                break;
            case 3:
                metodopago = new PagoConEfectivo();
                break;
        } //fin switch tipo pago

        //ejecuta el pago con metodo elegido
        //observe que "metodopago" nunca instancia a la interface IPago,
        //solo referencia a objetos instanciados de las subclases que la implementan
        if (metodopago == null) {
            System.out.println("ERROR: No eligio un metodo de pago valido");
        } else {
            resultado = metodopago.ProcesarPago(monto);
            System.out.println(resultado);
        }

        sc.close();
    }
}
