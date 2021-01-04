package eus.healthit.bchef.core.view.components;

import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.border.Border;

public class TransparentRoundBackgroundBorder implements Border {

    private int radius;


    TransparentRoundBackgroundBorder(int radius) {
        this.radius = radius;
    }


    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    public boolean isBorderOpaque() {
        return true;
    }


    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    	Graphics2D g2 = (Graphics2D) g;
    	AbstractButton b = (AbstractButton)c;
    	g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
    	FontMetrics fM = g2.getFontMetrics();
    	
        g2.fillRoundRect(x, y, width, height, radius, radius);
        JButton boton = (JButton) c;
        boton.getUI().paint(g2, (JComponent)c);
    }
}