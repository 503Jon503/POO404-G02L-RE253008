package Colecciones;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Biblioteca {

    //Coleccion HashMap declarada como campo estatico de la clase: ISBN -> Titulo
    static HashMap<String, String> libros = new HashMap<>();
    static Scanner sc = new Scanner(System.in);

    //ISBN de 10 digitos, o 13 digitos que inicia con 978 o 979 (puede terminar en X si es de 10)
    static final String PATRON_ISBN = "^(97[89]\\d{10}|\\d{9}[\\dX])$";

    public static void main(String[] args) {

        int opcion;

        do {
            mostrarMenu();
            opcion = leerOpcionMenu();

            switch (opcion) {
                case 1:
                    crearLibro();
                    break;
                case 2:
                    leerLibro();
                    break;
                case 3:
                    actualizarLibro();
                    break;
                case 4:
                    eliminarLibro();
                    break;
                case 5:
                    System.out.println("Finalizando aplicacion...");
                    break;
                default:
                    System.out.println("Opcion invalida, intente de nuevo.");
                    break;
            }

        } while (opcion != 5);

        sc.close();
    }

    static void mostrarMenu() {
        System.out.println("\n===== MENU BIBLIOTECA =====");
        System.out.println("1. Agregar libro (Create)");
        System.out.println("2. Consultar libro(s) (Read)");
        System.out.println("3. Actualizar libro (Update)");
        System.out.println("4. Eliminar libro (Delete)");
        System.out.println("5. Finalizar");
        System.out.print("Seleccione una opcion: ");
    }

    static int leerOpcionMenu() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; //fuerza a caer en el "default" del switch
        }
    }

    //---------------------- CREATE ----------------------
    static void crearLibro() {
        String isbn = solicitarISBNNuevo();
        if (isbn == null) return; //se cancelo por exceso de intentos

        String titulo = solicitarTitulo();
        if (titulo == null) return;

        libros.put(isbn, titulo);
        System.out.println("Libro agregado correctamente.");
    }

    //---------------------- READ ----------------------
    static void leerLibro() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados todavia.");
            return;
        }

        System.out.print("Ingrese el ISBN a consultar (dejar vacio para ver todos): ");
        String isbn = sc.nextLine().trim();

        if (isbn.isEmpty()) {
            System.out.println("Listado completo de libros:");
            for (Map.Entry<String, String> entry : libros.entrySet()) {
                System.out.println("ISBN: " + entry.getKey() + " - Titulo: " + entry.getValue());
            }
        } else if (libros.containsKey(isbn)) {
            System.out.println("Titulo encontrado: " + libros.get(isbn));
        } else {
            System.out.println("No existe ningun libro registrado con ese ISBN.");
        }
    }

    //---------------------- UPDATE ----------------------
    static void actualizarLibro() {
        String isbn = solicitarISBNExistente();
        if (isbn == null) return;

        String nuevoTitulo = solicitarTitulo();
        if (nuevoTitulo == null) return;

        libros.put(isbn, nuevoTitulo);
        System.out.println("Libro actualizado correctamente.");
    }

    //---------------------- DELETE ----------------------
    static void eliminarLibro() {
        String isbn = solicitarISBNExistente();
        if (isbn == null) return;

        libros.remove(isbn);
        System.out.println("Libro eliminado correctamente.");
    }

    //---------------------- Metodos de apoyo ----------------------

    //Pide un ISBN valido y que NO exista aun (para crear). 3 intentos maximo.
    static String solicitarISBNNuevo() {
        int intentos = 0;
        while (intentos < 3) {
            System.out.print("Ingrese el ISBN (10 o 13 digitos, sin guiones): ");
            String isbn = sc.nextLine().trim();

            if (!Pattern.matches(PATRON_ISBN, isbn)) {
                intentos++;
                System.out.println("ISBN con formato invalido. Intento " + intentos + " de 3.");
            } else if (libros.containsKey(isbn)) {
                intentos++;
                System.out.println("Ya existe un libro con ese ISBN. Intento " + intentos + " de 3.");
            } else {
                return isbn;
            }
        }
        System.out.println("Se supero el numero de intentos permitidos. Operacion cancelada.");
        return null;
    }

    //Pide un ISBN valido que SI exista (para actualizar/eliminar). 3 intentos maximo.
    static String solicitarISBNExistente() {
        int intentos = 0;
        while (intentos < 3) {
            System.out.print("Ingrese el ISBN del libro: ");
            String isbn = sc.nextLine().trim();

            if (!Pattern.matches(PATRON_ISBN, isbn)) {
                intentos++;
                System.out.println("ISBN con formato invalido. Intento " + intentos + " de 3.");
            } else if (!libros.containsKey(isbn)) {
                intentos++;
                System.out.println("No existe ningun libro con ese ISBN. Intento " + intentos + " de 3.");
            } else {
                return isbn;
            }
        }
        System.out.println("Se supero el numero de intentos permitidos. Operacion cancelada.");
        return null;
    }

    //Pide un titulo no vacio. 3 intentos maximo.
    static String solicitarTitulo() {
        int intentos = 0;
        while (intentos < 3) {
            System.out.print("Ingrese el titulo del libro: ");
            String titulo = sc.nextLine().trim();

            if (!titulo.isEmpty()) {
                return titulo;
            }
            intentos++;
            System.out.println("El titulo no puede estar vacio. Intento " + intentos + " de 3.");
        }
        System.out.println("Se supero el numero de intentos permitidos. Operacion cancelada.");
        return null;
    }
}
