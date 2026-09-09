package com.example.javajframe.datos;

import com.example.javajframe.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 * Ejercicio Complementario: mantenimiento de la tabla intermedia
 * alumno_materia y reporte de materias que cursa un alumno especifico.
 */
public class AlumnoMateriaDatos {

    private static final String BDD = "colegiobdd";

    public int matricular(int codAlumno, int codMateria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement(
                    "INSERT INTO alumno_materia(Cod_alumno, Cod_materia) VALUES(?,?)");
            stmt.setInt(1, codAlumno);
            stmt.setInt(2, codMateria);
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int desmatricular(int codAlumno, int codMateria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement(
                    "DELETE FROM alumno_materia WHERE Cod_alumno=? AND Cod_materia=?");
            stmt.setInt(1, codAlumno);
            stmt.setInt(2, codMateria);
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    /** Tabla general: todas las matriculas, con nombre de alumno y de materia. */
    public DefaultTableModel selectMatriculas() {
        DefaultTableModel dtm = new DefaultTableModel();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT a.Cod_alumno, a.Nombre AS Alumno, m.Cod_materia, m.Nombre AS Materia " +
                "FROM alumno_materia am " +
                "INNER JOIN alumno a ON a.Cod_alumno = am.Cod_alumno " +
                "INNER JOIN materia m ON m.Cod_materia = am.Cod_materia " +
                "ORDER BY a.Cod_alumno, m.Cod_materia";
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement(sql);
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

    /** Ejercicio 3 de la Guia #7 / reporte de esta guia: materias que cursa un alumno. */
    public DefaultComboBoxModel<String> materiasDeAlumno(int codAlumno) {
        DefaultComboBoxModel<String> materias = new DefaultComboBoxModel<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT m.Nombre FROM materia m " +
                "INNER JOIN alumno_materia am ON m.Cod_materia = am.Cod_materia " +
                "WHERE am.Cod_alumno = ?";
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codAlumno);
            rs = stmt.executeQuery();
            while (rs.next()) {
                materias.addElement(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return materias;
    }

    public DefaultComboBoxModel<String> selectNombresAlumnos() {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement("SELECT Nombre FROM alumno ORDER BY Nombre");
            rs = stmt.executeQuery();
            while (rs.next()) {
                modelo.addElement(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return modelo;
    }

    public int getCodAlumnoPorNombre(String nombre) {
        int cod = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement("SELECT Cod_alumno FROM alumno WHERE Nombre=?");
            stmt.setString(1, nombre);
            rs = stmt.executeQuery();
            if (rs.next()) {
                cod = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cod;
    }
}
