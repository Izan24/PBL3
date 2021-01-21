package eus.healthit.bchef.core.view.borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.border.Border;

public class RoundedBorder implements Border{
	
	private int radius;
	private Color borderColor;

	
	public RoundedBorder(int radius, Color borderColor) {
		this.radius = radius;
		this.borderColor = borderColor;
	}
	
	public RoundedBorder(int radius, Color borderColor, boolean icon) {
		this.radius = radius;
		this.borderColor = borderColor;
	}
	
	
	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(2, 8, 2, 8);
	}

	@Override
	public boolean isBorderOpaque() {
		return true;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g2.setColor(borderColor);
		g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
	}
	
}
