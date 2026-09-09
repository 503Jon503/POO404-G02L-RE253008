package dao;

import conexiones.Conexion;
import negocio.Materia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO con el CRUD de la tabla materia (Ejercicio Complementario 2).
 */
public class MateriaDAO {

    private Conexion obtenerConexion() throws SQLException {
        return new Conexion("colegiobdd");
    }

    public void insertar(Materia m) throws SQLException {
        Conexion con = obtenerConexion();
        String sql = "insert into materia (Cod_materia, Nombre, Descripcion) values (" +
                m.getCodMateria() + ",'" + m.getNombre() + "','" + m.getDescripcion() + "')";
        con.setQuery(sql);
        con.cerrarConexion();
    }

    public void actualizar(Materia m) throws SQLException {
        Conexion con = obtenerConexion();
        String sql = "update materia set Nombre='" + m.getNombre() + "', Descripcion='" +
                m.getDescripcion() + "' where Cod_materia=" + m.getCodMateria();
        con.setQuery(sql);
        con.cerrarConexion();
    }

    public void eliminar(int codMateria) throws SQLException {
        Conexion con = obtenerConexion();
        String sql = "delete from materia where Cod_materia=" + codMateria;
        con.setQuery(sql);
        con.cerrarConexion();
    }

    public Materia buscarPorCodigo(int codMateria) throws SQLException {
        Conexion con = obtenerConexion();
        con.setRs("select * from materia where Cod_materia=" + codMateria);
        ResultSet rs = con.getRs();
        Materia m = null;
        if (rs.next()) {
            m = new Materia(
                    rs.getInt("Cod_materia"),
                    rs.getString("Nombre"),
                    rs.getString("Descripcion"));
        }
        con.cerrarConexion();
        return m;
    }

    public List<Materia> listarTodos() throws SQLException {
        Conexion con = obtenerConexion();
        con.setRs("select * from materia");
        ResultSet rs = con.getRs();
        List<Materia> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Materia(
                    rs.getInt("Cod_materia"),
                    rs.getString("Nombre"),
                    rs.getString("Descripcion")));
        }
        con.cerrarConexion();
        return lista;
    }
}
