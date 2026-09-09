package aplicacion;

import interfaceswing.frmPersona;
import interfaceswing.frmBotonesRadio;

/**
 * Clase principal del proyecto POOGuia6.
 * Guia de Laboratorio #6 - POO404 - Universidad Don Bosco.
 *
 * Contiene el metodo estatico de inicio (main), tal como lo pide el
 * paso 4 del procedimiento.
 */
public class Principal {

    public static void main(String[] args) {

        // Se ejecuta en el hilo de eventos de Swing (buena práctica en apps GUI)
        javax.swing.SwingUtilities.invokeLater(() -> {

            // ---- PARTE 1 y 3 del procedimiento + Ejercicios Complementarios ----
            // Formulario de ingreso de datos de una persona, con tabla y
            // persistencia en CSV (ver clase frmPersona).
            frmPersona formpersona = new frmPersona("Ingreso de datos de una persona");
            formpersona.setVisible(true);

            // ---- PARTE 4 del procedimiento ----
            // Para probar el formulario de seleccion de imagenes con
            // JRadioButton (frmBotonesRadio), comente la instancia anterior
            // y descomente las siguientes 2 lineas, tal como se hizo en el
            // paso 67 de la guia:
            //
            // frmBotonesRadio frame = new frmBotonesRadio(
            //         "Seleccion de imagenes con radiobutton");
            // frame.setVisible(true);
        });
    }
}
