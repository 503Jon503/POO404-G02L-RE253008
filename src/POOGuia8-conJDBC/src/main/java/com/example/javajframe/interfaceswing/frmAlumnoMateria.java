package com.example.javajframe.interfaceswing;

import com.example.javajframe.datos.AlumnoMateriaDatos;
import com.example.javajframe.datos.MateriaDatos;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Ejercicio Complementario (seccion IV): mantenimiento de AlumnoMateria
 * (matricula/desmatricula) y el reporte de materias que cursa un alumno
 * especifico elegido por el usuario (pedido en el Ejercicio 3 de la Guia #7,
 * ahora resuelto con formularios).
 */
public class frmAlumnoMateria extends JFrame {

    private final AlumnoMateriaDatos amDatos = new AlumnoMateriaDatos();
    private final MateriaDatos materiaDatos = new MateriaDatos();

    private JComboBox<String> cmbAlumno;
    private JComboBox<String> cmbMateria;
    private JTable tblMatriculas;
    private JButton btnMatricular;
    private JButton btnDesmatricular;
    private JButton btnVerReporte;

    public frmAlumnoMateria() {
        initComponents();
        setTitle("Matricula de Alumno en Materia");
        setMinimumSize(new java.awt.Dimension(650, 480));
        setLocationRelativeTo(null);

        cargarCombos();
        cargarTabla();

        btnMatricular.addActionListener(e -> matricular());
        btnDesmatricular.addActionListener(e -> desmatricular());
        btnVerReporte.addActionListener(e -> verReporte());
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0; panel.add(new JLabel("Alumno:"), c);
        cmbAlumno = new JComboBox<>();
        c.gridx = 1; c.weightx = 1; panel.add(cmbAlumno, c);

        c.gridx = 0; c.gridy = 1; c.weightx = 0; panel.add(new JLabel("Materia:"), c);
        cmbMateria = new JComboBox<>();
        c.gridx = 1; c.weightx = 1; panel.add(cmbMateria, c);

        JPanel panelBotones = new JPanel();
        btnMatricular = new JButton("Matricular");
        btnDesmatricular = new JButton("Desmatricular");
        btnVerReporte = new JButton("Ver materias del alumno");
        panelBotones.add(btnMatricular);
        panelBotones.add(btnDesmatricular);
        panelBotones.add(btnVerReporte);
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2; panel.add(panelBotones, c);

        tblMatriculas = new JTable();
        JScrollPane scroll = new JScrollPane(tblMatriculas);
        scroll.setPreferredSize(new java.awt.Dimension(600, 250));

        JPanel contenedor = new JPanel(new BorderLayout(10, 10));
        contenedor.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contenedor.add(panel, BorderLayout.NORTH);
        contenedor.add(scroll, BorderLayout.CENTER);

        setContentPane(contenedor);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    private void cargarCombos() {
        cmbAlumno.setModel(amDatos.selectNombresAlumnos());
        cmbMateria.setModel(materiaDatos.selectNombresMaterias());
    }

    private void cargarTabla() {
        DefaultTableModel modelo = amDatos.selectMatriculas();
        tblMatriculas.setModel(modelo);
    }

    private void matricular() {
        if (cmbAlumno.getSelectedItem() == null || cmbMateria.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un alumno y una materia.");
            return;
        }
        int codAlumno = amDatos.getCodAlumnoPorNombre(cmbAlumno.getSelectedItem().toString());
        int codMateria = materiaDatos.getCodMateriaPorNombre(cmbMateria.getSelectedItem().toString());
        amDatos.matricular(codAlumno, codMateria);
        cargarTabla();
        JOptionPane.showMessageDialog(this, "Alumno matriculado correctamente.");
    }

    private void desmatricular() {
        if (cmbAlumno.getSelectedItem() == null || cmbMateria.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un alumno y una materia.");
            return;
        }
        int codAlumno = amDatos.getCodAlumnoPorNombre(cmbAlumno.getSelectedItem().toString());
        int codMateria = materiaDatos.getCodMateriaPorNombre(cmbMateria.getSelectedItem().toString());
        amDatos.desmatricular(codAlumno, codMateria);
        cargarTabla();
        JOptionPane.showMessageDialog(this, "Matricula eliminada correctamente.");
    }

    /** Ejercicio 3 de la Guia #7: lista las materias que cursa el alumno seleccionado. */
    private void verReporte() {
        if (cmbAlumno.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un alumno.");
            return;
        }
        String nombreAlumno = cmbAlumno.getSelectedItem().toString();
        int codAlumno = amDatos.getCodAlumnoPorNombre(nombreAlumno);
        javax.swing.DefaultComboBoxModel<String> materias = amDatos.materiasDeAlumno(codAlumno);

        StringBuilder sb = new StringBuilder();
        sb.append("Materias que cursa ").append(nombreAlumno).append(":\n");
        if (materias.getSize() == 0) {
            sb.append(" (no esta matriculado en ninguna materia)");
        } else {
            for (int i = 0; i < materias.getSize(); i++) {
                sb.append(" - ").append(materias.getElementAt(i)).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Reporte de materias", JOptionPane.INFORMATION_MESSAGE);
    }
}
