package modelos;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * Clase del Procedimiento - Parte 4.
 * Permite ingresar interactivamente (via JOptionPane) una nueva persona
 * a la tabla persona de personabdd, validando telefono y fecha con Testing.
 */
public class IngresoDatos {

    private int id;
    private String ids;
    private String nombre;
    private int edad;
    private String edads;
    private String telefono;
    private String sexo;
    private int idocupacion;
    private String idocupacions;
    private String fechanac;
    private Connection conexion;
    private ResultSet rs;
    private Statement s;

    public void obtenerOcupacion() throws SQLException {
        rs = s.executeQuery("select count(*) from ocupaciones");
        int tamanio = 0;
        while (rs.next()) {
            tamanio = rs.getInt(1);
        }
        String[] options2 = new String[tamanio];
        int contador = 0;
        rs = s.executeQuery("select * from ocupaciones");
        String texto = "";
        while (rs.next()) {
            options2[contador] = rs.getString("ocupacion");
            contador++;
        }
        idocupacions = (String) JOptionPane.showInputDialog(null,
                "Seleccione una ocupacion", "ocupacion persona",
                JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
        rs = s.executeQuery(
                "select id_ocupacion from ocupaciones where ocupacion ='" +
                        idocupacions + "'");
        while (rs.next()) {
            idocupacion = rs.getInt(1);
        }
    }

    public void ingreso() throws SQLException {
        JOptionPane.showMessageDialog(null,
                "A continuacion, ingresara a datos de nueva Persona",
                "Registo de Persona", JOptionPane.INFORMATION_MESSAGE);
        ids = JOptionPane.showInputDialog("Ingrese el ID");
        id = Integer.parseInt(ids);
        nombre = JOptionPane.showInputDialog("Ingrese el Nombre");
        edads = JOptionPane.showInputDialog("Ingrese la edad");
        edad = Integer.parseInt(edads);
        telefono = JOptionPane.showInputDialog("Ingrese su Telefono");
        do {
            if (Testing.compareTelephone(telefono) == true) {
                break;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Numero de Telefono invalido");
                telefono = JOptionPane.showInputDialog(
                        "Ingrese nuevamente al Telefono");
            }
        } while (true);
        String[] options = {"Femenino", "Masculino"};
        sexo = (String) JOptionPane.showInputDialog(null,
                "Seleccione el sexo:", "Sexo persona",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        obtenerOcupacion();
        fechanac = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento");
        do {
            if (Testing.compareDate(fechanac) == true)
                break; // finaliza ciclo do-while
            else {
                JOptionPane.showMessageDialog(null, "Formato fecha invalido");
                fechanac = JOptionPane.showInputDialog(
                        "Ingrese nuevamente a la fecha de nacimiento");
            }
        } while (true);
    }

    public IngresoDatos() {
        // Se utiliza un try por los posibles errores de MySQL
        try {
            // obtenemos el driver de para mysql
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Se obtiene una conexión con la base de datos.
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost/personabdd", "root", "");
            // IMPORTANTE: EL CAMPO PASSWORD DEBE IR EN BLANCO POR DEFECTO

            // Permite ejecutar sentencias SQL sin parámetros
            s = conexion.createStatement();
            // Metodo para ingresar valores
            ingreso();
            s.executeUpdate(
                    "Insert into persona values(" + id + ",\"" + nombre + "\"," + edad + ",\"" +
                            telefono + "\",\"" + sexo + "\"," + idocupacion + ",\"" +
                            fechanac + "\")");
            JOptionPane.showMessageDialog(null,
                    "Persona Ingresada Correctamente");
        } catch (ClassNotFoundException e1) {
            // Error si no puedo leer el driver de MySQL
            System.out.println("ERROR:No encuentro el driver de la BD: " +
                    e1.getMessage());
            System.exit(0);
        } catch (SQLException e2) {
            // Error SQL: login/passwd ó sentencia sql erronea
            System.out.println("ERROR:Fallo en SQL: " + e2.getMessage());
            System.exit(0);
        }
    }

    public void mostrardatos() throws SQLException {
        rs = s.executeQuery("select * from persona");
        while (rs.next()) {
            JOptionPane.showMessageDialog(null, "ID: " +
                    rs.getString("id_persona") +
                    "\nNombre: " + rs.getString("nombre_persona") +
                    "\nEdad: " + rs.getInt("edad_persona") +
                    "\nTelefono: " + rs.getString("telefono_persona")
            );
        }
    }

    public void cierreconexion() throws SQLException {
        // Se cierra la conexión con la base de datos.
        if (conexion != null) {
            conexion.close();
        }
    }
}
