package com.example.javajframe.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Paso 8 de la Guia de Laboratorio #8.
 * Clase de utilidad para obtener y cerrar conexiones JDBC hacia personabdd.
 */
public class Conexion {

    // Valores de conexion a MySql
    private static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    // El puerto es opcional, si no se especifica se usa el 3306 por defecto
    private static String JDBC_URL = "jdbc:mysql://localhost:3306/personabdd";
    private static String JDBC_USER = "root";
    private static String JDBC_PASS = "";
    private static Driver driver = null;
    private static Connection con = null;

    // Para que no haya problemas al obtener la conexion de
    // manera concurrente, se usa la palabra synchronized
    public static synchronized Connection getConnection() throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    /**
     * Sobrecarga NO pedida por la guia (la cual solo trabaja con personabdd),
     * agregada para poder reutilizar esta misma clase Conexion en el
     * Ejercicio Complementario, donde el mantenimiento de Alumno/Materia usa
     * la base de datos colegiobdd en lugar de personabdd.
     */
    public static synchronized Connection getConnection(String nombreBdd) throws SQLException {
        try {
            Class.forName(JDBC_DRIVER);
            String url = "jdbc:mysql://localhost:3306/" + nombreBdd;
            con = DriverManager.getConnection(url, JDBC_USER, JDBC_PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void close(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
