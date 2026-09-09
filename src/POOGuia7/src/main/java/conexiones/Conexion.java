package conexiones;

import java.sql.*;

/**
 * Clase del Procedimiento - Parte 5.
 * Encapsula la conexion JDBC a personabdd y ofrece metodos genericos
 * para ejecutar consultas y sentencias de actualizacion.
 * Tambien es reutilizada por el paquete dao de los Ejercicios Complementarios,
 * cambiando unicamente la URL de conexion hacia colegiobdd cuando aplica.
 */
public class Conexion {

    // campos
    private Connection conexion = null;
    private Statement s = null;
    private ResultSet rs = null;
    private String ingresoempleados = "";

    // Contructor
    public Conexion() throws SQLException {
        try {
            // obtenemos el driver de para mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Se obtiene una conexión con la base de datos.
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/personabdd", "root", "");
            // Permite ejecutar sentencias SQL sin parámetros
            s = conexion.createStatement();
            System.out.println("Conexion Exitosa");
        } catch (ClassNotFoundException e1) {
            // Error si no puedo leer el driver de MySQL
            System.out.println("ERROR:No encuentro el driver de la BD: " +
                    e1.getMessage());
        } catch (SQLException e2) {
            // Error SQL: login/passwd ó sentencia sql errónea
            System.out.println("ERROR:Fallo en SQL: " + e2.getMessage());
        }
    }

    /**
     * Constructor sobrecargado para poder apuntar a una base de datos distinta
     * de personabdd (por ejemplo colegiobdd, usada en los Ejercicios
     * Complementarios). La guia no describe este caso, por lo que se agrega
     * como sobrecarga adicional sin modificar el comportamiento original.
     */
    public Conexion(String nombreBdd) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/" + nombreBdd, "root", "");
            s = conexion.createStatement();
            System.out.println("Conexion Exitosa a " + nombreBdd);
        } catch (ClassNotFoundException e1) {
            System.out.println("ERROR:No encuentro el driver de la BD: " +
                    e1.getMessage());
        } catch (SQLException e2) {
            System.out.println("ERROR:Fallo en SQL: " + e2.getMessage());
        }
    }

    // Metodo que permite obtener los valores del resulset
    public ResultSet getRs() {
        return rs;
    }

    // Metodo que permite fijar la tabla resultado de la pregunta
    // SQL realizada
    public void setRs(String sql) {
        try {
            this.rs = s.executeQuery(sql);
        } catch (SQLException e2) {
            System.out.println("ERROR:Fallo en SQL: " + e2.getMessage());
        }
    }

    // Metodo que recibe un sql como parametro que sea un update,insert,delete
    public void setQuery(String sql) throws SQLException {
        this.s.executeUpdate(sql);
    }

    // Metodo que cierra la conexion
    public void cerrarConexion() throws SQLException {
        conexion.close();
    }
}
