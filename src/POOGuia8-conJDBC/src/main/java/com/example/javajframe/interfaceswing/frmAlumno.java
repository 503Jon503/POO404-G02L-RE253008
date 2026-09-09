package com.example.javajframe.interfaceswing;

import com.example.javajframe.beans.AlumnoBeans;
import com.example.javajframe.datos.AlumnoDatos;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Ejercicio Complementario (seccion IV): mantenimiento de Alumno con
 * formularios, siguiendo el mismo patron de frmPersona (tabla + formulario +
 * botones Guardar/Editar, Eliminar, Limpiar).
 */
public class frmAlumno extends JFrame {

    private AlumnoBeans alumnoBeans;
    private final AlumnoDatos alumnoDatos = new AlumnoDatos();
    private DefaultTableModel modelo;

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEdad;
    private JTextField txtDireccion;
    private JTable tblAlumnos;
    private JButton btnGuardar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public frmAlumno() {
        initComponents();
        setTitle("Mantenimiento de Alumno");
        setMinimumSize(new java.awt.Dimension(650, 500));
        setLocationRelativeTo(null);

        cargarTabla();

        tblAlumnos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                obtenerFilaDeTabla(tblAlumnos.getSelectedRow());
            }
        });

        btnGuardar.addActionListener(e -> guardar());
        btnEliminar.addActionListener(e -> eliminar());
        btnLimpiar.addActionListener(e -> limpiar());
    }

    private void initComponents() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.HORIZONTAL;

        int fila = 0;
        c.gridx = 0; c.gridy = fila; panel.add(new JLabel("Codigo:"), c);
        txtCodigo = new JTextField();
        c.gridx = 1; c.weightx = 1; panel.add(txtCodigo, c);
        fila++;

        c.gridx = 0; c.gridy = fila; c.weightx = 0; panel.add(new JLabel("Nombre:"), c);
        txtNombre = new JTextField();
        c.gridx = 1; c.weightx = 1; panel.add(txtNombre, c);
        fila++;

        c.gridx = 0; c.gridy = fila; c.weightx = 0; panel.add(new JLabel("Apellido:"), c);
        txtApellido = new JTextField();
        c.gridx = 1; c.weightx = 1; panel.add(txtApellido, c);
        fila++;

        c.gridx = 0; c.gridy = fila; c.weightx = 0; panel.add(new JLabel("Edad:"), c);
        txtEdad = new JTextField();
        c.gridx = 1; c.weightx = 1; panel.add(txtEdad, c);
        fila++;

        c.gridx = 0; c.gridy = fila; c.weightx = 0; panel.add(new JLabel("Direccion:"), c);
        txtDireccion = new JTextField();
        c.gridx = 1; c.weightx = 1; panel.add(txtDireccion, c);
        fila++;

        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        c.gridx = 0; c.gridy = fila; c.gridwidth = 2; panel.add(panelBotones, c);

        tblAlumnos = new JTable();
        JScrollPane scroll = new JScrollPane(tblAlumnos);
        scroll.setPreferredSize(new java.awt.Dimension(600, 220));

        JPanel contenedor = new JPanel(new java.awt.BorderLayout(10, 10));
        contenedor.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contenedor.add(panel, java.awt.BorderLayout.NORTH);
        contenedor.add(scroll, java.awt.BorderLayout.CENTER);

        setContentPane(contenedor);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    private void cargarTabla() {
        modelo = alumnoDatos.selectAlumnos();
        tblAlumnos.setModel(modelo);
    }

    private void obtenerFilaDeTabla(int fila) {
        if (fila > -1) {
            txtCodigo.setText(modelo.getValueAt(fila, 0).toString());
            txtNombre.setText(modelo.getValueAt(fila, 1).toString());
            txtApellido.setText(modelo.getValueAt(fila, 2).toString());
            txtEdad.setText(modelo.getValueAt(fila, 3).toString());
            txtDireccion.setText(modelo.getValueAt(fila, 4) == null ? "" : modelo.getValueAt(fila, 4).toString());
            btnGuardar.setText("Editar");
        }
    }

    private void guardar() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText().trim());
            int edad = Integer.parseInt(txtEdad.getText().trim());
            if (txtNombre.getText().trim().isEmpty() || txtApellido.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y apellido son obligatorios.");
                return;
            }
            // Rango de edad razonable para un alumno (ver README: no especificado por la guia)
            if (edad < 15 || edad > 100) {
                JOptionPane.showMessageDialog(this, "Edad invalida. Debe estar entre 15 y 100 anios.");
                return;
            }
            alumnoBeans = new AlumnoBeans(codigo, txtNombre.getText().trim(),
                    txtApellido.getText().trim(), edad, txtDireccion.getText().trim());

            if (btnGuardar.getText().equals("Guardar"))
                alumnoDatos.insert(alumnoBeans);
            else
                alumnoDatos.update(alumnoBeans);

            cargarTabla();
            limpiar();
            JOptionPane.showMessageDialog(this, "Alumno guardado correctamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Codigo y edad deben ser numericos.");
        }
    }

    private void eliminar() {
        if (txtCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione un alumno de la tabla primero.");
            return;
        }
        alumnoDatos.delete(Integer.parseInt(txtCodigo.getText().trim()));
        cargarTabla();
        limpiar();
        JOptionPane.showMessageDialog(this, "Alumno eliminado correctamente.");
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEdad.setText("");
        txtDireccion.setText("");
        btnGuardar.setText("Guardar");
    }
}
