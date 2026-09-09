package com.example.javajframe.aplicacion;

import com.example.javajframe.interfaceswing.frmMenuPrincipal;
import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicacion. Lanza el menu principal, desde donde
 * se puede abrir frmPersona (procedimiento de la Guia #8) o cualquiera de
 * los formularios del Ejercicio Complementario.
 */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new frmMenuPrincipal().setVisible(true));
    }
}
