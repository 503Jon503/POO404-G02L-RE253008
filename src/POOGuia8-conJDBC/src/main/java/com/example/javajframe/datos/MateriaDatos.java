package com.example.javajframe.datos;

import com.example.javajframe.beans.MateriaBeans;
import com.example.javajframe.util.Conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 * Ejercicio Complementario: CRUD de la tabla materia (colegiobdd).
 */
public class MateriaDatos {

    private static final String BDD = "colegiobdd";

    private final String SQL_INSERT =
            "INSERT INTO materia(Cod_materia, Nombre, Descripcion) VALUES(?,?,?)";
    private final String SQL_UPDATE =
            "UPDATE materia SET Nombre=?, Descripcion=? WHERE Cod_materia=?";
    private final String SQL_DELETE =
            "DELETE FROM materia WHERE Cod_materia=?";
    private final String SQL_SELECT =
            "SELECT Cod_materia, Nombre, Descripcion FROM materia ORDER BY Cod_materia";

    public int insert(MateriaBeans materia) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement(SQL_INSERT);
            int index = 1;
            stmt.setInt(index++, materia.getCodMateria());
            stmt.setString(index++, materia.getNombre());
            stmt.setString(index++, materia.getDescripcion());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int update(MateriaBeans materia) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement(SQL_UPDATE);
            int index = 1;
            stmt.setString(index++, materia.getNombre());
            stmt.setString(index++, materia.getDescripcion());
            stmt.setInt(index++, materia.getCodMateria());
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public int delete(int codMateria) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, codMateria);
            rows = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }

    public DefaultTableModel selectMaterias() {
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

    /** Utilizado para llenar combos de materia en el formulario de matricula. */
    public DefaultComboBoxModel<String> selectNombresMaterias() {
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement("SELECT Nombre FROM materia ORDER BY Nombre");
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

    public int getCodMateriaPorNombre(String nombre) {
        int cod = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection(BDD);
            stmt = conn.prepareStatement("SELECT Cod_materia FROM materia WHERE Nombre=?");
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
