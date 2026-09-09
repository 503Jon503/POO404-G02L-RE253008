package com.example.javajframe.interfaceswing;

import com.example.javajframe.beans.PersonaBeans;
import com.example.javajframe.datos.OcupacionesDatos;
import com.example.javajframe.datos.PersonasDatos;
import com.example.javajframe.interfaceswing.recursos.IconoGenerator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

/**
 * Pasos 12 a 24 de la Guia de Laboratorio #8.
 *
 * Este formulario reproduce el frmPersona descrito en la guia (que en el
 * proyecto original venia de POOGuia6 y se generaba con el editor visual de
 * NetBeans). Al no usar NetBeans, la interfaz se construyo a mano con
 * GridBagLayout, pero se conservan exactamente los mismos nombres de
 * variables y metodos que pide la guia (txtId, txtNombre, cmbOcupacion,
 * tblDatos, btnObtenerDatos, btnEliminar, btnLimpiar, LimpiarControles,
 * ObtenerFiladeTabla, EliminarDatos), para que el comportamiento y el
 * codigo relevante sean identicos a los solicitados.
 */
public class frmPersona extends JFrame {

    // ---- Campos de datos / logica (paso 18) ----
    private PersonaBeans personaBeans = null;
    private PersonasDatos personasDatos = new PersonasDatos();
    private OcupacionesDatos ocupacionesDatos = new OcupacionesDatos();
    private DefaultTableModel modelo;

    // ---- Controles del formulario ----
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtEdad;
    private JTextField txtTelefono;
    private JComboBox<String> cmbSexo;
    private JComboBox cmbOcupacion;
    private JTextField txtFechaNacimiento;
    private JTable tblDatos;
    private JButton btnObtenerDatos;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    public frmPersona() {
        initComponents();

        // Paso 19a: dimension minima de la ventana
        setMinimumSize(new java.awt.Dimension(750, 600));
        setTitle("Ingreso de datos de una persona");
        setLocationRelativeTo(null);

        // Paso 19c: en vez de cargar la tabla con datos fijos, se obtienen
        // los datos reales desde la base de datos.
        modelo = personasDatos.selectPersona();
        tblDatos.setModel(modelo);
        cmbOcupacion.setModel(ocupacionesDatos.selectOcupaciones());

        // Click sobre una fila de la tabla -> llena el formulario (paso 22)
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                int fila = tblDatos.getSelectedRow();
                int columna = tblDatos.getSelectedColumn();
                ObtenerFiladeTabla(fila, columna);
            }
        });

        // Paso 20: click en Guardar/Editar
        btnObtenerDatos.addActionListener(e -> btnObtenerDatosActionPerformed());

        // Paso 24: Listener MouseClicked del boton Eliminar
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });

        btnLimpiar.addActionListener(e -> btnLimpiar());
    }

    /** Construye la interfaz grafica (equivalente al codigo generado por el editor visual). */
    private void initComponents() {
        Font fuenteControles = new Font("Segoe Print", Font.BOLD, 16);

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ---- Cabecera (icono + titulo) ----
        JPanel panelCabecera = new JPanel(new BorderLayout(10, 0));
        JLabel lblIcono = new JLabel(IconoGenerator.generarIconoPersona(48, new Color(255, 153, 51)));
        JLabel lblTitulo = new JLabel("Datos de la Persona.");
        lblTitulo.setFont(new Font("Segoe Print", Font.BOLD, 24));
        panelCabecera.add(lblIcono, BorderLayout.WEST);
        panelCabecera.add(lblTitulo, BorderLayout.CENTER);
        panelPrincipal.add(panelCabecera, BorderLayout.NORTH);

        // ---- Formulario ----
        JPanel panelFormulario = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 4, 4, 4);
        c.fill = GridBagConstraints.HORIZONTAL;

        int fila = 0;

        c.gridx = 0; c.gridy = fila; c.weightx = 0;
        panelFormulario.add(new JLabel("Id:"), c);
        txtId = new JTextField();
        txtId.setEditable(false); // paso 13: txtId editable=false
        c.gridx = 1; c.weightx = 1;
        panelFormulario.add(txtId, c);
        fila++;

        c.gridx = 0; c.gridy = fila; c.weightx = 0;
        panelFormulario.add(new JLabel("Ingrese su nombre:"), c);
        txtNombre = new JTextField();
        c.gridx = 1; c.weightx = 1;
        panelFormulario.add(txtNombre, c);
        fila++;

        c.gridx = 0; c.gridy = fila; c.weightx = 0;
        panelFormulario.add(new JLabel("Ingrese su edad:"), c);
        txtEdad = new JTextField();
        c.gridx = 1; c.weightx = 1;
        panelFormulario.add(txtEdad, c);
        fila++;

        c.gridx = 0; c.gridy = fila; c.weightx = 0;
        panelFormulario.add(new JLabel("Ingrese su telefono:"), c);
        txtTelefono = new JTextField();
        c.gridx = 1; c.weightx = 1;
        panelFormulario.add(txtTelefono, c);
        fila++;

        c.gridx = 0; c.gridy = fila; c.weightx = 0;
        panelFormulario.add(new JLabel("Seleccione su sexo:"), c);
        cmbSexo = new JComboBox<>(new String[]{"Masculino", "Femenino"});
        c.gridx = 1; c.weightx = 1;
        panelFormulario.add(cmbSexo, c);
        fila++;

        // Controles agregados en el paso 12/13 (fuente Segoe Print, Bold, 16)
        JLabel lblOcupacion = new JLabel("Seleccione su Ocupación:");
        lblOcupacion.setFont(fuenteControles);
        c.gridx = 0; c.gridy = fila; c.weightx = 0;
        panelFormulario.add(lblOcupacion, c);
        cmbOcupacion = new JComboBox<>();
        cmbOcupacion.setFont(fuenteControles);
        c.gridx = 1; c.weightx = 1;
        panelFormulario.add(cmbOcupacion, c);
        fila++;

        JLabel lblFechaNacimiento = new JLabel("Ingrese su fecha de Nacimiento:");
        lblFechaNacimiento.setFont(fuenteControles);
        c.gridx = 0; c.gridy = fila; c.weightx = 0;
        panelFormulario.add(lblFechaNacimiento, c);
        txtFechaNacimiento = new JTextField();
        txtFechaNacimiento.setFont(fuenteControles);
        c.gridx = 1; c.weightx = 1;
        panelFormulario.add(txtFechaNacimiento, c);
        fila++;

        // ---- Botones ----
        JPanel panelBotones = new JPanel();
        btnObtenerDatos = new JButton("Guardar");
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(fuenteControles);
        btnLimpiar = new JButton("Limpiar");
        panelBotones.add(btnObtenerDatos);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        c.gridx = 0; c.gridy = fila; c.gridwidth = 2; c.weightx = 1;
        panelFormulario.add(panelBotones, c);

        panelPrincipal.add(panelFormulario, BorderLayout.CENTER);

        // ---- Tabla de resultados ----
        tblDatos = new JTable();
        JScrollPane scroll = new JScrollPane(tblDatos);
        scroll.setPreferredSize(new java.awt.Dimension(700, 220));
        panelPrincipal.add(scroll, BorderLayout.SOUTH);

        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
    }

    /** Paso 21: reinicia los controles del formulario a su estado inicial. */
    private void LimpiarControles() {
        txtId.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        txtTelefono.setText("");
        cmbSexo.setSelectedIndex(0);
        // Lineas agregadas en el paso 21
        cmbOcupacion.setSelectedIndex(0);
        txtFechaNacimiento.setText("");
        btnObtenerDatos.setText("Guardar");
    }

    /** Metodo asociado al boton Limpiar (paso 20/21: se invoca como btnLimpiar();). */
    private void btnLimpiar() {
        LimpiarControles();
    }

    /** Paso 22: llena el formulario con los datos de la fila seleccionada en la tabla. */
    private void ObtenerFiladeTabla(int fila, int columna) {
        if ((fila > -1) && (columna > -1)) {
            txtId.setText(modelo.getValueAt(fila, 0).toString());
            txtNombre.setText(modelo.getValueAt(fila, 1).toString());
            txtEdad.setText(modelo.getValueAt(fila, 2).toString());
            txtTelefono.setText(modelo.getValueAt(fila, 3).toString());
            cmbSexo.setSelectedItem(modelo.getValueAt(fila, 4).toString());
            // Lineas agregadas en el paso 22
            cmbOcupacion.setSelectedItem(modelo.getValueAt(fila, 5).toString());
            txtFechaNacimiento.setText(modelo.getValueAt(fila, 6).toString());
            btnObtenerDatos.setText("Editar");
        }
    }

    /** Paso 20: logica del boton Guardar/Editar. */
    private void btnObtenerDatosActionPerformed() {
        int id;
        String nombres;
        int edad;
        String telefono;
        String sexo;
        int idOcupacion;
        String fechaNacimiento;

        // adquiere de controles del form a datos dados por usuario
        if (txtId.getText().isEmpty()) id = 0;
        else id = Integer.parseInt(txtId.getText());
        nombres = txtNombre.getText();
        edad = Integer.parseInt(txtEdad.getText());
        telefono = txtTelefono.getText();
        sexo = cmbSexo.getSelectedItem().toString();
        idOcupacion = ocupacionesDatos.getIdOcupacion(cmbOcupacion.getSelectedItem().toString());
        fechaNacimiento = txtFechaNacimiento.getText();

        personaBeans = new PersonaBeans(id, nombres, edad, telefono, sexo, idOcupacion, fechaNacimiento);

        if (btnObtenerDatos.getText().equals("Guardar"))
            personasDatos.insert(personaBeans);
        else if (btnObtenerDatos.getText().equals("Editar"))
            personasDatos.update(personaBeans);

        modelo = personasDatos.selectPersona();
        tblDatos.setModel(modelo);

        btnLimpiar();
    }

    /** Paso 23: elimina el registro cuyo Id esta cargado en txtId. */
    public void EliminarDatos() {
        personasDatos.delete(Integer.parseInt(txtId.getText()));
        btnLimpiar();
        modelo = personasDatos.selectPersona();
        tblDatos.setModel(modelo);
    }

    /** Paso 24: listener MouseClicked del boton Eliminar (codigo tal cual la guia). */
    private void btnEliminarMouseClicked(MouseEvent evt) {
        EliminarDatos();
    }
}
