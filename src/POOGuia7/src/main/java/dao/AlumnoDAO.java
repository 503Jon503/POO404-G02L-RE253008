package dao;

import conexiones.Conexion;
import negocio.Alumno;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO con el CRUD de la tabla alumno (Ejercicio Complementario 2),
 * reutilizando la clase conexiones.Conexion del procedimiento.
 */
public class AlumnoDAO {

    // Se usa el constructor sobrecargado de Conexion para apuntar a colegiobdd
    private Conexion obtenerConexion() throws SQLException {
        return new Conexion("colegiobdd");
    }

    public void insertar(Alumno a) throws SQLException {
        Conexion con = obtenerConexion();
        String sql = "insert into alumno (Cod_alumno, Nombre, Apellido, Edad, Direccion) values (" +
                a.getCodAlumno() + ",'" + a.getNombre() + "','" + a.getApellido() + "'," +
                a.getEdad() + ",'" + a.getDireccion() + "')";
        con.setQuery(sql);
        con.cerrarConexion();
    }

    public void actualizar(Alumno a) throws SQLException {
        Conexion con = obtenerConexion();
        String sql = "update alumno set Nombre='" + a.getNombre() + "', Apellido='" + a.getApellido() +
                "', Edad=" + a.getEdad() + ", Direccion='" + a.getDireccion() +
                "' where Cod_alumno=" + a.getCodAlumno();
        con.setQuery(sql);
        con.cerrarConexion();
    }

    public void eliminar(int codAlumno) throws SQLException {
        Conexion con = obtenerConexion();
        String sql = "delete from alumno where Cod_alumno=" + codAlumno;
        con.setQuery(sql);
        con.cerrarConexion();
    }

    public Alumno buscarPorCodigo(int codAlumno) throws SQLException {
        Conexion con = obtenerConexion();
        con.setRs("select * from alumno where Cod_alumno=" + codAlumno);
        ResultSet rs = con.getRs();
        Alumno a = null;
        if (rs.next()) {
            a = new Alumno(
                    rs.getInt("Cod_alumno"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getInt("Edad"),
                    rs.getString("Direccion"));
        }
        con.cerrarConexion();
        return a;
    }

    public List<Alumno> listarTodos() throws SQLException {
        Conexion con = obtenerConexion();
        con.setRs("select * from alumno");
        ResultSet rs = con.getRs();
        List<Alumno> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Alumno(
                    rs.getInt("Cod_alumno"),
                    rs.getString("Nombre"),
                    rs.getString("Apellido"),
                    rs.getInt("Edad"),
                    rs.getString("Direccion")));
        }
        con.cerrarConexion();
        return lista;
    }
}
