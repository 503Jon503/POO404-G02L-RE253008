package aplicacion;

import dao.AlumnoDAO;
import dao.AlumnoMateriaDAO;
import dao.MateriaDAO;
import negocio.Alumno;
import negocio.Materia;
import validaciones.ValidadorDatos;
import javax.swing.JOptionPane;
import java.util.List;
import java.util.Scanner;

/**
 * Punto de entrada para los Ejercicios Complementarios (seccion IV de la
 * guia): CRUD de alumno y materia, matricula en alumno_materia, y el
 * reporte de materias que cursa un alumno especifico.
 *
 * Siguiendo las Notas de la guia: JOptionPane se usa unicamente para
 * informar el exito o el fallo de una operacion solicitada por el usuario;
 * toda la navegacion del menu y la captura de datos se hace por consola,
 * y toda validacion de datos se delega a la clase validaciones.ValidadorDatos.
 */
public class MenuComplementario {

    private static final Scanner sc = new Scanner(System.in);
    private static final AlumnoDAO alumnoDAO = new AlumnoDAO();
    private static final MateriaDAO materiaDAO = new MateriaDAO();
    private static final AlumnoMateriaDAO amDAO = new AlumnoMateriaDAO();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("\n=== Ejercicios Complementarios - Guia #7 (POO404) ===");
            System.out.println("1. Registrar alumno");
            System.out.println("2. Listar / actualizar / eliminar alumno");
            System.out.println("3. Registrar materia");
            System.out.println("4. Listar / actualizar / eliminar materia");
            System.out.println("5. Matricular alumno en una materia");
            System.out.println("6. Reporte: materias que cursa un alumno");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = leerEntero();

            try {
                switch (opcion) {
                    case 1: registrarAlumno(); break;
                    case 2: gestionarAlumnos(); break;
                    case 3: registrarMateria(); break;
                    case 4: gestionarMaterias(); break;
                    case 5: matricular(); break;
                    case 6: reporteMateriasDeAlumno(); break;
                    case 0: System.out.println("Saliendo..."); break;
                    default: System.out.println("Opcion invalida.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "ERROR al procesar la operacion: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (opcion != 0);
    }

    // ---------------------- ALUMNO ----------------------

    private static void registrarAlumno() throws Exception {
        System.out.print("Codigo de alumno: ");
        Integer cod = ValidadorDatos.parsearEntero(sc.nextLine());
        if (cod == null || !ValidadorDatos.esCodigoValido(cod)) {
            System.out.println("Codigo invalido.");
            return;
        }
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Edad: ");
        Integer edad = ValidadorDatos.parsearEntero(sc.nextLine());
        System.out.print("Direccion: ");
        String direccion = sc.nextLine();

        if (!ValidadorDatos.esTextoValido(nombre) || !ValidadorDatos.esTextoValido(apellido)) {
            JOptionPane.showMessageDialog(null, "Nombre y apellido son obligatorios.");
            return;
        }
        if (edad == null || !ValidadorDatos.esEdadValida(edad)) {
            JOptionPane.showMessageDialog(null, "Edad invalida. Debe estar entre " +
                    ValidadorDatos.getEdadMinima() + " y " + ValidadorDatos.getEdadMaxima() + " anios.");
            return;
        }

        alumnoDAO.insertar(new Alumno(cod, nombre, apellido, edad, direccion));
        JOptionPane.showMessageDialog(null, "Alumno registrado correctamente.");
    }

    private static void gestionarAlumnos() throws Exception {
        List<Alumno> lista = alumnoDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }
        lista.forEach(System.out::println);

        System.out.print("\nDesee (A)ctualizar, (E)liminar o (N)ada: ");
        String accion = sc.nextLine().trim().toUpperCase();
        if (accion.equals("N")) return;

        System.out.print("Codigo del alumno: ");
        Integer cod = ValidadorDatos.parsearEntero(sc.nextLine());
        if (cod == null) {
            System.out.println("Codigo invalido.");
            return;
        }

        if (accion.equals("E")) {
            alumnoDAO.eliminar(cod);
            JOptionPane.showMessageDialog(null, "Alumno eliminado correctamente.");
        } else if (accion.equals("A")) {
            Alumno actual = alumnoDAO.buscarPorCodigo(cod);
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "No existe un alumno con ese codigo.");
                return;
            }
            System.out.print("Nuevo nombre (" + actual.getNombre() + "): ");
            String nombre = sc.nextLine();
            System.out.print("Nuevo apellido (" + actual.getApellido() + "): ");
            String apellido = sc.nextLine();
            System.out.print("Nueva edad (" + actual.getEdad() + "): ");
            Integer edad = ValidadorDatos.parsearEntero(sc.nextLine());
            System.out.print("Nueva direccion (" + actual.getDireccion() + "): ");
            String direccion = sc.nextLine();

            actual.setNombre(ValidadorDatos.esTextoValido(nombre) ? nombre : actual.getNombre());
            actual.setApellido(ValidadorDatos.esTextoValido(apellido) ? apellido : actual.getApellido());
            actual.setEdad(edad != null && ValidadorDatos.esEdadValida(edad) ? edad : actual.getEdad());
            actual.setDireccion(ValidadorDatos.esTextoValido(direccion) ? direccion : actual.getDireccion());

            alumnoDAO.actualizar(actual);
            JOptionPane.showMessageDialog(null, "Alumno actualizado correctamente.");
        }
    }

    // ---------------------- MATERIA ----------------------

    private static void registrarMateria() throws Exception {
        System.out.print("Codigo de materia: ");
        Integer cod = ValidadorDatos.parsearEntero(sc.nextLine());
        if (cod == null || !ValidadorDatos.esCodigoValido(cod)) {
            System.out.println("Codigo invalido.");
            return;
        }
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Descripcion: ");
        String descripcion = sc.nextLine();

        if (!ValidadorDatos.esTextoValido(nombre)) {
            JOptionPane.showMessageDialog(null, "El nombre de la materia es obligatorio.");
            return;
        }

        materiaDAO.insertar(new Materia(cod, nombre, descripcion));
        JOptionPane.showMessageDialog(null, "Materia registrada correctamente.");
    }

    private static void gestionarMaterias() throws Exception {
        List<Materia> lista = materiaDAO.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay materias registradas.");
            return;
        }
        lista.forEach(System.out::println);

        System.out.print("\nDesee (A)ctualizar, (E)liminar o (N)ada: ");
        String accion = sc.nextLine().trim().toUpperCase();
        if (accion.equals("N")) return;

        System.out.print("Codigo de la materia: ");
        Integer cod = ValidadorDatos.parsearEntero(sc.nextLine());
        if (cod == null) {
            System.out.println("Codigo invalido.");
            return;
        }

        if (accion.equals("E")) {
            materiaDAO.eliminar(cod);
            JOptionPane.showMessageDialog(null, "Materia eliminada correctamente.");
        } else if (accion.equals("A")) {
            Materia actual = materiaDAO.buscarPorCodigo(cod);
            if (actual == null) {
                JOptionPane.showMessageDialog(null, "No existe una materia con ese codigo.");
                return;
            }
            System.out.print("Nuevo nombre (" + actual.getNombre() + "): ");
            String nombre = sc.nextLine();
            System.out.print("Nueva descripcion (" + actual.getDescripcion() + "): ");
            String descripcion = sc.nextLine();

            actual.setNombre(ValidadorDatos.esTextoValido(nombre) ? nombre : actual.getNombre());
            actual.setDescripcion(ValidadorDatos.esTextoValido(descripcion) ? descripcion : actual.getDescripcion());

            materiaDAO.actualizar(actual);
            JOptionPane.showMessageDialog(null, "Materia actualizada correctamente.");
        }
    }

    // ---------------------- ALUMNO_MATERIA / REPORTE ----------------------

    private static void matricular() throws Exception {
        System.out.print("Codigo de alumno: ");
        Integer codAlumno = ValidadorDatos.parsearEntero(sc.nextLine());
        System.out.print("Codigo de materia: ");
        Integer codMateria = ValidadorDatos.parsearEntero(sc.nextLine());

        if (codAlumno == null || codMateria == null) {
            System.out.println("Datos invalidos.");
            return;
        }
        amDAO.matricular(codAlumno, codMateria);
        JOptionPane.showMessageDialog(null, "Alumno matriculado correctamente en la materia.");
    }

    private static void reporteMateriasDeAlumno() throws Exception {
        System.out.print("Codigo del alumno a consultar: ");
        Integer codAlumno = ValidadorDatos.parsearEntero(sc.nextLine());
        if (codAlumno == null) {
            System.out.println("Codigo invalido.");
            return;
        }
        Alumno a = alumnoDAO.buscarPorCodigo(codAlumno);
        if (a == null) {
            JOptionPane.showMessageDialog(null, "No existe un alumno con ese codigo.");
            return;
        }
        List<String> materias = amDAO.materiasDeAlumno(codAlumno);
        System.out.println("\nMaterias que cursa " + a.getNombre() + " " + a.getApellido() + ":");
        if (materias.isEmpty()) {
            System.out.println(" (no esta matriculado en ninguna materia)");
        } else {
            materias.forEach(m -> System.out.println(" - " + m));
        }
    }

    private static int leerEntero() {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
}
