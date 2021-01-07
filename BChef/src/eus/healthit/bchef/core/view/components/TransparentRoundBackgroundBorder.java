package eus.healthit.bchef.core.view.components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JComponent;
import javax.swing.border.Border;

public class TransparentRoundBackgroundBorder implements Border {
	
	static final int PADDING = 40;

	static final int IMAGEWIDTH = 22;
	
    private int radius;


    TransparentRoundBackgroundBorder(int radius) {
        this.radius = radius;
    }
    
    TransparentRoundBackgroundBorder(int radius, int align) {
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
    	CustomButton b = (CustomButton)c;
    	g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
    	
    	g2.setColor(b.getBackgroundColor());
    	Dimension d = b.getSize();
    	String caption = b.getFrontText();
    	FontMetrics fm = g2.getFontMetrics();
    	int botonWidth = PADDING + IMAGEWIDTH + fm.stringWidth(caption);
    	g2.setColor(Color.white);
    	g2.fillRect(x, y, width, height);
    	g2.setColor(b.getBackgroundColor());
    	if (b.getAlign() == CustomButton.LEFT_ALIGN) {
    		g2.fillRoundRect(x, y, x+botonWidth, height, radius, radius);
		}
    	else {
    		g2.fillRoundRect(x, y, width, height, radius, radius);
		}
        
        b.getUI().paint(g2, (JComponent)c);
    }
}