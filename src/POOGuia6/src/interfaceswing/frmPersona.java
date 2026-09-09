package interfaceswing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Formulario de ingreso de datos de una persona.
 * Guia de Laboratorio #6 - POO404 - Universidad Don Bosco.
 *
 * Implementa:
 *  - PARTE 1: formulario con Label, TextField y ComboBox (pasos 8-32).
 *  - PARTE 1 (funcionalidad de botones): btnObtenerDatos / btnLimpiar (pasos 33-38).
 *  - PARTE 3: modelo de tabla (DefaultTableModel) y JTable tblDatos (pasos 39-55).
 *  - EJERCICIOS COMPLEMENTARIOS: lectura/escritura de un archivo CSV y
 *    metodo de insercion/actualizacion de filas por Id.
 */
public class frmPersona extends JFrame {

    // ----- Controles del formulario (nombres tal como los define la guia) -----
    private JPanel pnlPersona;      // Accessible Name: pnlPersona
    private JPanel pnlBotones;      // Accessible Name: pnlBotones
    private JLabel lblFoto;
    private JLabel lblTitulo;
    private JLabel lblId;
    private JLabel lblNombre;
    private JLabel lblEdad;
    private JLabel lblTelefono;
    private JLabel lblSexo;
    private JTextField txtId;               // Variable Name: txtId
    private JTextField txtNombre;           // Variable Name: txtNombre
    private JTextField txtEdad;             // Variable Name: txtEdad
    private JTextField txtTelefono;         // Variable Name: txtTelefono
    private JComboBox<String> cmbSexo;      // Variable Name: cmbSexo
    private JButton btnObtenerDatos;        // Variable Name: btnObtenerDatos
    private JButton btnLimpiar;             // Variable Name: btnLimpiar
    private JTable tblDatos;                // Variable Name: tblDatos
    private JScrollPane scrollDatos;

    // Modelo de tabla (paso 43 del procedimiento)
    private DefaultTableModel modelo;

    // ----- Rutas usadas por los Ejercicios Complementarios -----
    // Se intenta primero como recurso de classpath (si el CSV viaja empaquetado
    // dentro del jar/target, junto al paquete "datos"), y como respaldo se
    // busca directamente en el sistema de archivos del proyecto.
    private static final String CSV_RESOURCE_PATH = "/datos/personas.csv";
    private static final String CSV_FILE_PATH =
            "src" + File.separator + "datos" + File.separator + "personas.csv";

    public frmPersona(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(700, 600));
        this.setLocationRelativeTo(getParent());
        initComponents();

        // Arreglo de objeto, para inicializar con vacio la tabla
        Object[][] data = null;
        // Arreglo de String para crear los nombres de las columnas
        String[] colums = {"Id", "Nombres", "Edad", "Telefono", "Sexo"};
        // Instancia del modelo
        modelo = new DefaultTableModel(data, colums);
        // Seteo del modelo, el cual tendra la estructura que permitira
        // a la tabla representar los datos
        tblDatos.setModel(modelo);

        // Ejercicio Complementario (a): se invoca UNICAMENTE desde el
        // constructor. Solo si el archivo CSV existe, se cargan sus
        // registros hacia tblDatos.
        cargarDatosDesdeCSV();
    }

    // =========================================================================
    // Construccion manual de la interfaz (equivalente a lo que NetBeans/IntelliJ
    // generarian en initComponents() a traves del disenador visual).
    // =========================================================================
    private void initComponents() {
        setLayout(new BorderLayout(10, 10));

        // ---------- Encabezado: imagen + "Datos de la Persona." ----------
        JPanel pnlEncabezado = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        lblFoto = new JLabel();
        lblFoto.setIcon(cargarIcono("/interfaceswing/recursos/man.png", 80, 80));
        lblTitulo = new JLabel("Datos de la Persona.");
        lblTitulo.setFont(new Font("Segoe Print", Font.BOLD, 28));
        pnlEncabezado.add(lblFoto);
        pnlEncabezado.add(lblTitulo);

        // ---------- Panel de campos de datos (pnlPersona) ----------
        pnlPersona = new JPanel(new GridBagLayout());
        pnlPersona.setName("pnlPersona");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fontCampos = new Font("Segoe Print", Font.BOLD, 16);

        lblId = new JLabel("Ingrese su id:");
        lblNombre = new JLabel("Ingrese su nombre:");
        lblEdad = new JLabel("Ingrese su edad:");
        lblTelefono = new JLabel("Ingrese su telefono:");
        lblSexo = new JLabel("Selece su sexo:");
        for (JLabel l : new JLabel[]{lblId, lblNombre, lblEdad, lblTelefono, lblSexo}) {
            l.setFont(fontCampos);
        }

        txtId = new JTextField(20);
        txtNombre = new JTextField(20);
        txtEdad = new JTextField(20);
        txtTelefono = new JTextField(20);
        cmbSexo = new JComboBox<>(new String[]{"Masculino", "Femenino"});
        for (JTextField t : new JTextField[]{txtId, txtNombre, txtEdad, txtTelefono}) {
            t.setFont(fontCampos);
        }
        cmbSexo.setFont(fontCampos);

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; pnlPersona.add(lblId, gbc);
        gbc.gridx = 1; pnlPersona.add(txtId, gbc);
        row++;
        gbc.gridx = 0; gbc.gridy = row; pnlPersona.add(lblNombre, gbc);
        gbc.gridx = 1; pnlPersona.add(txtNombre, gbc);
        row++;
        gbc.gridx = 0; gbc.gridy = row; pnlPersona.add(lblEdad, gbc);
        gbc.gridx = 1; pnlPersona.add(txtEdad, gbc);
        row++;
        gbc.gridx = 0; gbc.gridy = row; pnlPersona.add(lblTelefono, gbc);
        gbc.gridx = 1; pnlPersona.add(txtTelefono, gbc);
        row++;
        gbc.gridx = 0; gbc.gridy = row; pnlPersona.add(lblSexo, gbc);
        gbc.gridx = 1; pnlPersona.add(cmbSexo, gbc);

        // ---------- Panel de botones (pnlBotones) ----------
        pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlBotones.setName("pnlBotones");
        btnLimpiar = new JButton("Limpiar");
        btnObtenerDatos = new JButton("Obtener Datos");
        pnlBotones.add(btnLimpiar);
        pnlBotones.add(btnObtenerDatos);

        // ---------- Tabla de datos (Parte 3) ----------
        tblDatos = new JTable();
        scrollDatos = new JScrollPane(tblDatos);
        scrollDatos.setPreferredSize(new Dimension(650, 200));

        // ---------- Composicion general ----------
        JPanel pnlCentro = new JPanel(new BorderLayout(5, 10));
        pnlCentro.add(pnlPersona, BorderLayout.NORTH);
        pnlCentro.add(pnlBotones, BorderLayout.CENTER);
        pnlCentro.add(scrollDatos, BorderLayout.SOUTH);

        add(pnlEncabezado, BorderLayout.NORTH);
        add(pnlCentro, BorderLayout.CENTER);

        // ---------- Eventos (equivalentes a los Listener generados por el IDE) ----------
        btnObtenerDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                btnObtenerDatosMouseClicked(evt);
            }
        });
        btnLimpiar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                btnLimpiarMouseClicked(evt);
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                tblDatosMouseClicked(evt);
            }
        });
    }

    // ---------- Metodos "...MouseClicked" (pasos 36 y 48 de la guia) ----------
    private void btnObtenerDatosMouseClicked(MouseEvent evt) {
        btnObtenerDatos();
    }

    private void btnLimpiarMouseClicked(MouseEvent evt) {
        btnLimpiar();
    }

    private void tblDatosMouseClicked(MouseEvent evt) {
        tblObtenerFila(evt);
    }

    // ---------- Paso 34 y 45 y 53: obtener datos del form y llevarlos a la tabla ----------
    private void btnObtenerDatos() {
        String id;
        String nombres;
        String edad;
        String telefono;
        String sexo;
        // adquiere de controles del form a datos dados por usuario
        id = txtId.getText();
        nombres = txtNombre.getText();
        edad = txtEdad.getText();
        telefono = txtTelefono.getText();
        sexo = cmbSexo.getSelectedItem().toString();

        JOptionPane.showMessageDialog(null,
                "Datos Obtenidos: \n ID: " + id +
                        "\n nombres: " + nombres + "\n Edad: " + edad +
                        "\n Telefono: " + telefono + "\n Sexo: " + sexo);

        // toma variables con datos recibidos del usuario
        // y crea un vector generico (tipo Object)
        Object[] newRow = {id, nombres, edad, telefono, sexo};

        // Ejercicio Complementario (c): en lugar de siempre insertar una fila
        // nueva (modelo.addRow), se delega en el metodo que decide si inserta
        // o actualiza segun si el Id ya existe en tblDatos.
        insertarOActualizarPersona(newRow);

        // Ejercicio Complementario (b): cada vez que se agrega/actualiza un
        // registro se vuelca el contenido completo de la tabla hacia el CSV,
        // manteniendolo sincronizado con lo que el usuario ve en pantalla.
        guardarDatosEnCSV();

        btnLimpiar();
    }

    // ---------- Paso 34: limpiar controles ----------
    private void btnLimpiar() {
        // borra contenido actual de los controles del form
        txtId.setText("");
        txtNombre.setText("");
        txtEdad.setText("");
        txtTelefono.setText("");
        // pone el combobox en su valor de indice 0
        cmbSexo.setSelectedIndex(0);
        txtId.requestFocus(); // recibe el foco
    }

    // ---------- Paso 47: obtener la fila clickeada de la tabla ----------
    private void tblObtenerFila(MouseEvent e) {
        // obtiene num. fila y columna de celda de tabla en
        // donde usuario hizo clic
        int fila = tblDatos.rowAtPoint(e.getPoint());
        int columna = tblDatos.columnAtPoint(e.getPoint());

        // traslada datos de fila hacia controles del form,
        // solo si tabla ya tiene filas
        if ((fila > -1) && (columna > -1)) {
            txtId.setText(modelo.getValueAt(fila, 0).toString());
            txtNombre.setText(modelo.getValueAt(fila, 1).toString());
            txtEdad.setText(modelo.getValueAt(fila, 2).toString());
            txtTelefono.setText(modelo.getValueAt(fila, 3).toString());
            cmbSexo.setSelectedItem(modelo.getValueAt(fila, 4).toString());
        }
    }

    /* =========================================================================
       EJERCICIOS COMPLEMENTARIOS
       ---------------------------------------------------------------------
       Investigacion breve sobre CSV (Comma-Separated Values):
       Es un formato de texto plano, estandarizado de facto por el RFC 4180,
       que representa datos tabulares separando cada valor de una fila con
       una coma (u otro delimitador) y cada fila con un salto de linea. Su
       utilidad principal es la interoperabilidad: permite exportar/importar
       datos entre hojas de calculo, bases de datos y aplicaciones distintas
       sin depender de un formato binario propietario, y es ideal para
       respaldos simples o cargas iniciales de datos como la de este
       ejercicio (persistir/leer los registros de tblDatos).
       Fuente de referencia: RFC 4180 - "Common Format and MIME Type for
       Comma-Separated Values (CSV) Files" (IETF, 2005).
       ========================================================================= */

    /**
     * Literal (a): busca y accede al contenido del archivo CSV del paquete
     * "datos". Solo si el archivo existe, carga sus registros hacia
     * tblDatos. Se invoca UNICAMENTE desde el metodo constructor.
     */
    private void cargarDatosDesdeCSV() {
        List<String> lineas = leerLineasCSV();
        if (lineas == null || lineas.isEmpty()) {
            // El archivo no existe todavia (o esta vacio): no se carga nada.
            return;
        }
        // La primera linea es el encabezado (Id,Nombres,Edad,Telefono,Sexo)
        for (int i = 1; i < lineas.size(); i++) {
            String linea = lineas.get(i).trim();
            if (linea.isEmpty()) {
                continue;
            }
            String[] campos = linea.split(",", -1);
            if (campos.length >= 5) {
                modelo.addRow(new Object[]{
                        campos[0], campos[1], campos[2], campos[3], campos[4]
                });
            }
        }
    }

    /**
     * Metodo de apoyo: intenta leer las lineas del CSV primero como recurso
     * de classpath y, si no se encuentra, directamente desde la ruta del
     * proyecto (src/datos/personas.csv).
     */
    private List<String> leerLineasCSV() {
        try {
            InputStream in = getClass().getResourceAsStream(CSV_RESOURCE_PATH);
            if (in != null) {
                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(in, StandardCharsets.UTF_8))) {
                    return br.lines().collect(Collectors.toList());
                }
            }
            File archivo = new File(CSV_FILE_PATH);
            if (archivo.exists()) {
                return Files.readAllLines(archivo.toPath(), StandardCharsets.UTF_8);
            }
        } catch (IOException ex) {
            System.err.println("No se pudo leer el archivo CSV: " + ex.getMessage());
        }
        return null; // el archivo aun no existe
    }

    /**
     * Literal (b): recorre todas las filas de tblDatos y escribe sus datos
     * hacia el archivo CSV del paquete "datos" (el mismo utilizado por el
     * metodo de carga del literal a).
     */
    private void guardarDatosEnCSV() {
        try {
            File archivo = new File(CSV_FILE_PATH);
            File carpeta = archivo.getParentFile();
            if (carpeta != null && !carpeta.exists()) {
                carpeta.mkdirs();
            }
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
                bw.write("Id,Nombres,Edad,Telefono,Sexo");
                bw.newLine();
                for (int i = 0; i < modelo.getRowCount(); i++) {
                    StringBuilder sb = new StringBuilder();
                    for (int c = 0; c < modelo.getColumnCount(); c++) {
                        if (c > 0) {
                            sb.append(",");
                        }
                        Object valor = modelo.getValueAt(i, c);
                        sb.append(valor == null ? "" : valor.toString());
                    }
                    bw.write(sb.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            System.err.println("No se pudo escribir el archivo CSV: " + ex.getMessage());
        }
    }

    /**
     * Literal (c): recibe un vector con los datos de una persona. Si ya
     * existe una fila con el mismo Id (columna 0) en tblDatos, actualiza
     * esa fila con los nuevos valores; si no existe, inserta el vector
     * como una fila nueva.
     */
    private void insertarOActualizarPersona(Object[] datosPersona) {
        String idBuscado = String.valueOf(datosPersona[0]);
        boolean encontrado = false;
        for (int i = 0; i < modelo.getRowCount(); i++) {
            String idActual = String.valueOf(modelo.getValueAt(i, 0));
            if (idActual.equals(idBuscado)) {
                for (int col = 0; col < datosPersona.length; col++) {
                    modelo.setValueAt(datosPersona[col], i, col);
                }
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            modelo.addRow(datosPersona);
        }
    }

    // ---------- Utilidad para cargar iconos sin romper la app si faltan ----------
    private ImageIcon cargarIcono(String resourcePath, int width, int height) {
        try {
            URL url = getClass().getResource(resourcePath);
            if (url != null) {
                ImageIcon icono = new ImageIcon(url);
                Image escalada = icono.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
                return new ImageIcon(escalada);
            }
        } catch (Exception ex) {
            System.err.println("No se pudo cargar el icono " + resourcePath + ": " + ex.getMessage());
        }
        return null;
    }
}
