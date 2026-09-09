package com.example.javajframe.interfaceswing;

import com.example.javajframe.beans.MateriaBeans;
import com.example.javajframe.datos.MateriaDatos;

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
 * Ejercicio Complementario (seccion IV): mantenimiento de Materia con formularios.
 */
public class frmMateria extends JFrame {

    private MateriaBeans materiaBeans;
    private final MateriaDatos materiaDatos = new MateriaDatos();
    private DefaultTableModel modelo;

    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTable tblMaterias;
    private JButton btnGuardar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public frmMateria() {
        initComponents();
        setTitle("Mantenimiento de Materia");
        setMinimumSize(new java.awt.Dimension(600, 450));
        setLocationRelativeTo(null);

        cargarTabla();

        tblMaterias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                obtenerFilaDeTabla(tblMaterias.getSelectedRow());
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

        c.gridx = 0; c.gridy = fila; c.weightx = 0; panel.add(new JLabel("Descripcion:"), c);
        txtDescripcion = new JTextField();
        c.gridx = 1; c.weightx = 1; panel.add(txtDescripcion, c);
        fila++;

        JPanel panelBotones = new JPanel();
        btnGuardar = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");
        btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnGuardar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        c.gridx = 0; c.gridy = fila; c.gridwidth = 2; panel.add(panelBotones, c);

        tblMaterias = new JTable();
        JScrollPane scroll = new JScrollPane(tblMaterias);
        scroll.setPreferredSize(new java.awt.Dimension(550, 200));

        JPanel contenedor = new JPanel(new java.awt.BorderLayout(10, 10));
        contenedor.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contenedor.add(panel, java.awt.BorderLayout.NORTH);
        contenedor.add(scroll, java.awt.BorderLayout.CENTER);

        setContentPane(contenedor);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    private void cargarTabla() {
        modelo = materiaDatos.selectMaterias();
        tblMaterias.setModel(modelo);
    }

    private void obtenerFilaDeTabla(int fila) {
        if (fila > -1) {
            txtCodigo.setText(modelo.getValueAt(fila, 0).toString());
            txtNombre.setText(modelo.getValueAt(fila, 1).toString());
            txtDescripcion.setText(modelo.getValueAt(fila, 2) == null ? "" : modelo.getValueAt(fila, 2).toString());
            btnGuardar.setText("Editar");
        }
    }

    private void guardar() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText().trim());
            if (txtNombre.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre de la materia es obligatorio.");
                return;
            }
            materiaBeans = new MateriaBeans(codigo, txtNombre.getText().trim(), txtDescripcion.getText().trim());

            if (btnGuardar.getText().equals("Guardar"))
                materiaDatos.insert(materiaBeans);
            else
                materiaDatos.update(materiaBeans);

            cargarTabla();
            limpiar();
            JOptionPane.showMessageDialog(this, "Materia guardada correctamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El codigo debe ser numerico.");
        }
    }

    private void eliminar() {
        if (txtCodigo.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una materia de la tabla primero.");
            return;
        }
        materiaDatos.delete(Integer.parseInt(txtCodigo.getText().trim()));
        cargarTabla();
        limpiar();
        JOptionPane.showMessageDialog(this, "Materia eliminada correctamente.");
    }

    private void limpiar() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtDescripcion.setText("");
        btnGuardar.setText("Guardar");
    }
}
