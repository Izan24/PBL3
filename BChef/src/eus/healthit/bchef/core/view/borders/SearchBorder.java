package eus.healthit.bchef.core.view.borders;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.io.File;
import java.io.IOException;
import java.nio.channels.NonReadableChannelException;

import javax.imageio.ImageIO;

public class SearchBorder extends RoundedBorder{

	public static final String IMAGEFILE = "resources/menuicons/search.png";
	
	private Image searchImage;	
	
	public SearchBorder(int radius, Color borderColor) {
		super(radius, borderColor);
		try {
			this.searchImage = ImageIO.read(new File(IMAGEFILE));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public SearchBorder(int radius, Color borderColor, String icon) {
		super(radius, borderColor);
		try {
			searchImage = ImageIO.read(new File(icon));
		} catch (Exception e) {
		}
	}
	
	@Override
	public Insets getBorderInsets(Component c) {
		Insets insets;
		if (searchImage == null) {
			insets = new Insets(2, 8, 2, 8);
		} else {
			insets = new Insets(2, 50, 2, 8);
		}
		return insets;
	}

	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		super.paintBorder(c, g, x, y, width, height);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		if (searchImage != null) {
			g2.drawImage(searchImage.getScaledInstance(20, 20, Image.SCALE_SMOOTH), 10, 8, null);
			g2.drawLine(38, 5, 38, height-6);
		}
	}
	
}
