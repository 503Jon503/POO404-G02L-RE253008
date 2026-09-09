package modelos;

import java.sql.*;

/**
 * Clase del Procedimiento - Parte 2.
 * Se conecta directamente a la BDD personabdd y muestra por consola
 * el contenido de la tabla persona.
 */
public class VerPersonas {

    public VerPersonas() // metodo constructor
    {
        // Se utiliza un try por los posibles errores de MySQL
        try {
            // obtenemos el driver de para mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Se obtiene una conexión con la base de datos.
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/personabdd", "root", "");
            // IMPORTANTE: EL CAMPO PASSWORD POR DEFECTO DEBE IR EN BLANCO,
            // O ASIGNAR EL PASSWORD DE SU SERVIDOR SQL

            // Permite ejecutar sentencias SQL sin parámetros
            Statement s = conexion.createStatement();
            // Contiene la tabla resultado de la pregunta SQL que se haya realizado
            ResultSet rs = s.executeQuery("select * from persona");

            // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while (rs.next()) {
                // Podemos mostrar los datos de otra forma ver mas abajo e la guia.
                System.out.println("ID: " + rs.getInt(1) +
                        "\nNombre: " + rs.getString(2) +
                        "\nEdad: " + rs.getString(3) +
                        "\nTelefono: " + rs.getString(4));
                System.out.println("**************************");
            }

            // Se cierra la conexión con la base de datos. NUNCA OLVIDE CERRARLA
            conexion.close();
        } catch (ClassNotFoundException e1) {
            // Error si no puedo leer el driver de MySQL
            System.out.println("ERROR:No encuentro el driver de la BD: " + e1.getMessage());
        } catch (SQLException e2) {
            // Error SQL: login/password, sentencia sql erronea
            System.out.println("ERROR:Fallo en SQL: " + e2.getMessage());
        }
    }
}
