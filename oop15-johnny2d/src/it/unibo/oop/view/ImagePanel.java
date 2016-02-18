package it.unibo.oop.view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Objects;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    
	private static final long serialVersionUID = -992912248972775131L;
	private Image image;

    public ImagePanel(Image image) {
        this.image = Objects.requireNonNull(image);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        /* 
         * l'immagine viene adattata allo schermo.
         */
        g.drawImage(this.image, 0, 0, this.getWidth(), this.getHeight(), this);
    }
}