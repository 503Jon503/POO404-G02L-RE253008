package dao;

import conexiones.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO para la tabla intermedia alumno_materia (relacion N a N) y para el
 * reporte pedido en el Ejercicio Complementario 3: listar los nombres de
 * las materias que esta cursando un alumno especifico elegido por el usuario.
 */
public class AlumnoMateriaDAO {

    private Conexion obtenerConexion() throws SQLException {
        return new Conexion("colegiobdd");
    }

    /** Matricula (asocia) a un alumno en una materia. */
    public void matricular(int codAlumno, int codMateria) throws SQLException {
        Conexion con = obtenerConexion();
        String sql = "insert into alumno_materia (Cod_alumno, Cod_materia) values (" +
                codAlumno + "," + codMateria + ")";
        con.setQuery(sql);
        con.cerrarConexion();
    }

    /** Elimina la matricula de un alumno en una materia. */
    public void desmatricular(int codAlumno, int codMateria) throws SQLException {
        Conexion con = obtenerConexion();
        String sql = "delete from alumno_materia where Cod_alumno=" + codAlumno +
                " and Cod_materia=" + codMateria;
        con.setQuery(sql);
        con.cerrarConexion();
    }

    /**
     * Ejercicio Complementario 3: retorna los nombres de las materias
     * que esta cursando el alumno indicado.
     */
    public List<String> materiasDeAlumno(int codAlumno) throws SQLException {
        Conexion con = obtenerConexion();
        String sql = "select m.Nombre from materia m " +
                "inner join alumno_materia am on m.Cod_materia = am.Cod_materia " +
                "where am.Cod_alumno = " + codAlumno;
        con.setRs(sql);
        ResultSet rs = con.getRs();
        List<String> materias = new ArrayList<>();
        while (rs.next()) {
            materias.add(rs.getString("Nombre"));
        }
        con.cerrarConexion();
        return materias;
    }
}
