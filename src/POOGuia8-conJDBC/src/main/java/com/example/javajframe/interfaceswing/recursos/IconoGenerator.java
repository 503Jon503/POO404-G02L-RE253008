package com.example.javajframe.interfaceswing.recursos;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * La guia original usa un icono/imagen decorativo (personas frente a una
 * computadora) en la cabecera de los formularios. Como este proyecto se
 * entrega sin abrir NetBeans y sin archivos binarios de imagen incluidos,
 * esta clase genera en tiempo de ejecucion un icono simple (silueta de
 * persona dentro de un circulo) para cumplir la misma funcion decorativa
 * sin depender de un recurso externo.
 */
public class IconoGenerator {

    public static ImageIcon generarIconoPersona(int size, Color colorFondo) {
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // circulo de fondo
        g2.setColor(colorFondo);
        g2.fill(new Ellipse2D.Double(0, 0, size, size));

        // cabeza
        g2.setColor(Color.WHITE);
        int headSize = size / 3;
        g2.fill(new Ellipse2D.Double((size - headSize) / 2.0, size * 0.18, headSize, headSize));

        // cuerpo (medio circulo/ovalo)
        int bodyWidth = (int) (size * 0.6);
        int bodyHeight = (int) (size * 0.4);
        g2.fill(new Ellipse2D.Double((size - bodyWidth) / 2.0, size * 0.55, bodyWidth, bodyHeight));

        g2.dispose();
        return new ImageIcon(img);
    }
}
