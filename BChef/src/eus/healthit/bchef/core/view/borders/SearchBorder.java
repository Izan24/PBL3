package eus.healthit.bchef.core.view.borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.File;

import javax.imageio.ImageIO;

public class SearchBorder extends RoundedBorder{

	public static final String IMAGEFILE = "resources/menuicons/search.png";
	
	private Image searchImage;
	
	
	public SearchBorder(int radius, Color borderColor) {
		super(radius, borderColor);
		try {
			searchImage = ImageIO.read(new File(IMAGEFILE));
		} catch (Exception e) {
		}
	}
	
	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(2, 50, 2, 8);
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		super.paintBorder(c, g, x, y, width, height);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g2.drawImage(searchImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH), 10, 8, null);
		g2.drawLine(38, 5, 38, height-6);
	}
	
}
