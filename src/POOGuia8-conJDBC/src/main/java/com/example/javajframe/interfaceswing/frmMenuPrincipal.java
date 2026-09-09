package com.example.javajframe.interfaceswing;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Menu principal de la aplicacion: da acceso al formulario frmPersona del
 * procedimiento (Guia #8) y a los formularios del Ejercicio Complementario
 * (mantenimiento de Alumno, Materia y AlumnoMateria).
 * No fue pedido explicitamente por la guia, pero se agrega para poder
 * navegar entre todos los formularios sin tener que cambiar la clase main().
 */
public class frmMenuPrincipal extends JFrame {

    public frmMenuPrincipal() {
        setTitle("POOGuia6 - conJDBC");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(320, 220));
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 1, 8, 8));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JButton btnPersona = new JButton("Mantenimiento de Persona (Guia #8)");
        JButton btnAlumno = new JButton("Mantenimiento de Alumno");
        JButton btnMateria = new JButton("Mantenimiento de Materia");
        JButton btnMatricula = new JButton("Matricula Alumno-Materia");

        btnPersona.addActionListener(e -> new frmPersona().setVisible(true));
        btnAlumno.addActionListener(e -> new frmAlumno().setVisible(true));
        btnMateria.addActionListener(e -> new frmMateria().setVisible(true));
        btnMatricula.addActionListener(e -> new frmAlumnoMateria().setVisible(true));

        panel.add(btnPersona);
        panel.add(btnAlumno);
        panel.add(btnMateria);
        panel.add(btnMatricula);

        setContentPane(panel);
        pack();
    }
}
