package mantenimiento;

import operacionesbanco.Cliente;
import operacionesbanco.CuentaBancaria;

import java.util.ArrayList;
import java.util.Scanner;

public class MantenimientoCuentas {

    static ArrayList<Cliente> clientes = new ArrayList<>();
    static ArrayList<CuentaBancaria> cuentas = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        //Clientes precargados de ejemplo (tal como se muestran en la guia)
        clientes.add(new Cliente("Iris", "Bonilla", "0858786-6"));
        clientes.add(new Cliente("Armando", "Nieto", "0158224-4"));
        clientes.add(new Cliente("Gilberto", "Zacarias", "6380044-7"));

        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    abrirCuentaAhorro();
                    break;
                case 3:
                    System.out.println("Saliendo de la aplicacion...");
                    break;
                default:
                    System.out.println("Opcion invalida, intente de nuevo.");
                    break;
            }

        } while (opcion != 3);

        sc.close();
    }

    static void mostrarMenu() {
        System.out.println("\n===== MANTENIMIENTO DE CUENTAS DE AHORRO =====");
        System.out.println("A) Registrar un cliente");
        System.out.println("B) Abrir una cuenta de ahorro");
        System.out.println("C) Salir de la aplicacion");
        System.out.print("Seleccione una opcion (A/B/C): ");
    }

    //Traduce la letra ingresada por el usuario a un numero de opcion interno
    static int leerOpcion() {
        String entrada = sc.nextLine().trim().toUpperCase();
        if (entrada.isEmpty()) return -1;

        switch (entrada.charAt(0)) {
            case 'A':
                return 1;
            case 'B':
                return 2;
            case 'C':
                return 3;
            default:
                return -1;
        }
    }

    //---------------------- OPCION A: Registrar un cliente ----------------------
    static void registrarCliente() {
        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = sc.nextLine().trim();

        System.out.print("Ingrese el apellido del cliente: ");
        String apellido = sc.nextLine().trim();

        System.out.print("Ingrese el DUI del cliente: ");
        String dui = sc.nextLine().trim();

        Cliente nuevoCliente = new Cliente(nombre, apellido, dui);
        clientes.add(nuevoCliente);

        mostrarListadoClientes();

        System.out.print("-> Presione Enter para retornar al menu");
        sc.nextLine();
    }

    static void mostrarListadoClientes() {
        System.out.println("\nClientes registrados (" + clientes.size() + "):");
        System.out.println("#  Datos del Cliente");
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + clientes.get(i).DatosCliente());
        }
    }

    //---------------------- OPCION B: Abrir una cuenta de ahorro ----------------------
    static void abrirCuentaAhorro() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados. Registre uno primero (opcion A).");
            return;
        }

        mostrarListadoClientes();

        Cliente clienteSeleccionado = solicitarClienteValido();
        if (clienteSeleccionado == null) return; //se cancelo por exceso de intentos

        double montoInicial = solicitarMontoValido();
        if (montoInicial < 0) return; //se cancelo por exceso de intentos

        CuentaBancaria cuentaNueva = new CuentaBancaria(clienteSeleccionado, montoInicial);
        cuentas.add(cuentaNueva);

        System.out.println("\nCuenta de ahorro creada correctamente.");
        cuentaNueva.vertransacciones();

        System.out.print("-> Presione Enter para retornar al menu");
        sc.nextLine();
    }

    //Pide el numero de cliente del listado. Maximo 3 intentos.
    static Cliente solicitarClienteValido() {
        int intentos = 0;
        while (intentos < 3) {
            System.out.print("\nDigite el numero del cliente al que abrira la cuenta: ");
            String entrada = sc.nextLine().trim();
            try {
                int indice = Integer.parseInt(entrada) - 1;
                if (indice >= 0 && indice < clientes.size()) {
                    return clientes.get(indice);
                }
                System.out.println("Numero fuera de rango.");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido.");
            }
            intentos++;
        }
        System.out.println("Se supero el numero de intentos permitidos. Operacion cancelada.");
        return null;
    }

    //Pide el monto inicial de apertura (>= 0). Maximo 3 intentos.
    static double solicitarMontoValido() {
        int intentos = 0;
        while (intentos < 3) {
            System.out.print("Ingrese el monto inicial de apertura: $");
            String entrada = sc.nextLine().trim();
            try {
                double monto = Double.parseDouble(entrada);
                if (monto >= 0) {
                    return monto;
                }
                System.out.println("El monto no puede ser negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un valor numerico valido.");
            }
            intentos++;
        }
        System.out.println("Se supero el numero de intentos permitidos. Operacion cancelada.");
        return -1;
    }
}
