package it.unibo.oop.view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Objects;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
    
	private static final long serialVersionUID = -992912248972775131L;
	private Image image;  
    private int iWidth2;
    private int iHeight2;

    public ImagePanel(Image image)
    {
        this.image = Objects.requireNonNull(image);
        this.iWidth2 = image.getWidth(this)/2;
        this.iHeight2 = image.getHeight(this)/2;
    }


    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        /* 
         * i calcoli sotto servono per centrare l'immagine e farla 
         * rimanere centrata anche con ridimensionamenti della finesta
         */
        int x = this.getParent().getWidth()/2 - this.iWidth2;  
        int y = this.getParent().getHeight()/2 - this.iHeight2;
        g.drawImage(image, x, y, this);
    }
}