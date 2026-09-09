package interfaceswing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Formulario de demostracion de JRadioButton.
 * Guia de Laboratorio #6 - POO404 - Universidad Don Bosco.
 * PARTE 4 del procedimiento (pasos 56-72).
 *
 * Al seleccionar cada JRadioButton se cambia la imagen mostrada en lblImagen.
 */
public class frmBotonesRadio extends JFrame {

    private JPanel pnlImagenes;          // Field name: pnlImagenes
    private JLabel lblTitulo;
    private JLabel lblImagen;            // field name: lblImagen
    private JRadioButton rbtOpcion1;     // field name: rbtOpcion1
    private JRadioButton rbtOpcion2;     // field name: rbtOpcion2
    private JRadioButton rbtOpcion3;     // field name: rbtOpcion3
    private ButtonGroup buttonGroup1;    // buttonGroup: buttonGroup1

    public frmBotonesRadio(String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        this.setContentPane(pnlImagenes);
        this.setMinimumSize(new Dimension(400, 400));
        this.setLocationRelativeTo(getParent());
    }

    private void initComponents() {
        // Grilla logica de 4 filas x 2 columnas (paso 58)
        pnlImagenes = new JPanel(new GridBagLayout());
        pnlImagenes.setName("pnlImagenes");
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        lblTitulo = new JLabel("Seleccione una imagen");
        lblTitulo.setFont(new Font("Segoe Print", Font.BOLD, 22));

        lblImagen = new JLabel();
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setIcon(cargarIcono("/interfaceswing/recursos/question-icon.jpeg"));

        Font fontOpciones = new Font("Segoe Print", Font.PLAIN, 18);
        rbtOpcion1 = new JRadioButton("Opcion 1");
        rbtOpcion2 = new JRadioButton("Opcion 2");
        rbtOpcion3 = new JRadioButton("Opcion 3");
        for (JRadioButton r : new JRadioButton[]{rbtOpcion1, rbtOpcion2, rbtOpcion3}) {
            r.setFont(fontOpciones);
        }

        // Grupo de botones: solo una opcion seleccionable a la vez (paso 60)
        buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(rbtOpcion1);
        buttonGroup1.add(rbtOpcion2);
        buttonGroup1.add(rbtOpcion3);

        // Fila 0: titulo fusionado en las 2 columnas (paso 62)
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        pnlImagenes.add(lblTitulo, gbc);

        // Filas 1-3, columna 0: radio buttons
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1; pnlImagenes.add(rbtOpcion1, gbc);
        gbc.gridx = 0; gbc.gridy = 2; pnlImagenes.add(rbtOpcion2, gbc);
        gbc.gridx = 0; gbc.gridy = 3; pnlImagenes.add(rbtOpcion3, gbc);

        // Columna 1, filas 1-3 fusionadas: imagen (paso 61)
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridheight = 3;
        pnlImagenes.add(lblImagen, gbc);

        // Listener (ActionPerformed) para cada JRadioButton (pasos 69-70)
        rbtOpcion1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblImagen.setIcon(cargarIcono("/interfaceswing/recursos/img1.jpeg"));
            }
        });
        rbtOpcion2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lblImagen.setIcon(cargarIcono("/interfaceswing/recursos/img2.jpeg"));
            }
        });
        rbtOpcion3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon imagen = cargarIcono("/interfaceswing/recursos/img3.jpeg");
                lblImagen.setIcon(imagen);
            }
        });
    }

    // Utilidad para cargar iconos sin romper la app si el recurso faltara
    private ImageIcon cargarIcono(String resourcePath) {
        try {
            URL url = getClass().getResource(resourcePath);
            if (url != null) {
                ImageIcon icono = new ImageIcon(url);
                Image escalada = icono.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                return new ImageIcon(escalada);
            }
        } catch (Exception ex) {
            System.err.println("No se pudo cargar el icono " + resourcePath + ": " + ex.getMessage());
        }
        return null;
    }
}
