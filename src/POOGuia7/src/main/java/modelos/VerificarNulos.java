package modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import conexiones.Conexion;

/**
 * Clase del Procedimiento - Parte 5.
 * Distingue entre un campo NULL y un campo vacio ("") en la tabla Empleados.
 */
public class VerificarNulos {

    public VerificarNulos() throws SQLException {
        Conexion con = new Conexion();
        String sql = "select codigo, nombre from empleados ";
        ResultSet rs;
        // objeto almacena en un ResultSet a registro de consulta sql
        con.setRs(sql);
        // llena objeto rs con filas obtenidas de consulta a tabla empleados
        rs = con.getRs();

        int id;
        String nombre;
        while (rs.next()) {
            id = rs.getInt(1);
            nombre = rs.getString(2);
            System.out.print("ID: " + id);
            if (nombre == null) {
                System.out.println(", Nombre 'Null' ");
            } else if (nombre.equals(""))
                System.out.println(", Nombre Vacio");
            else
                System.out.println(", Nombre almacenado: " + nombre);
        }
        con.cerrarConexion();
    }
}
