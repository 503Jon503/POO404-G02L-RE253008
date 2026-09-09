package com.example.javajframe.datos;

import com.example.javajframe.beans.AlumnoBeans;
import com.example.javajframe.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 * Ejercicio Complementario: CRUD de la tabla alumno (colegiobdd), siguiendo
 * el mismo estilo (PreparedStatement + try/finally) usado en PersonasDatos.
 */
public class AlumnoDatos {

    private static final String BDD = "colegiobdd";

    private final String SQL_INSERT =
            "INSERT INTO alumno(Cod_alumno, Nombre, Apellido, Edad, Direccion) VALUES(?,?,?,?,?)";
    private final String SQL_UPDATE =
            "UPDATE alumno SET Nombre=?, Apellido=?, Edad=?, Direccion=? WHERE Cod_alumno=?";
    private final String SQL_DELETE =
            "DELETE FROM alumno WHERE Cod_alumno=?";
    private final String SQL_SELECT =
            "SELECT Cod_alumno, Nombre, Apellido, Edad, Direccion FROM alumno ORDER BY Cod_alumno";

    public int insert(AlumnoBeans alumno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setInt(index++, alumno.getCodAlumno());
            stmt.setString(index++, alumno.getNombre());
            stmt.setString(index++, alumno.getApellido());
            stmt.setInt(index++, alumno.getEdad());
            stmt.setString(index++, alumno.getDireccion());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int update(AlumnoBeans alumno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, alumno.getNombre());
            stmt.setString(index++, alumno.getApellido());
            stmt.setInt(index++, alumno.getEdad());
            stmt.setString(index++, alumno.getDireccion());
            stmt.setInt(index++, alumno.getCodAlumno());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int delete(int codAlumno) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, codAlumno);
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public DefaultTableModel selectAlumnos() {
        DefaultTableModel dtm = new DefaultTableModel();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            ResultSetMetaData meta = rs.getMetaData();
            int numberOfColumns = meta.getColumnCount();
            for (int i = 1; i <= numberOfColumns; i++) {
                dtm.addColumn(meta.getColumnLabel(i));
            }
            while (rs.next()) {
                Object[] fila = new Object[numberOfColumns];
                for (int i = 0; i < numberOfColumns; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                dtm.addRow(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return dtm;
    }
}
