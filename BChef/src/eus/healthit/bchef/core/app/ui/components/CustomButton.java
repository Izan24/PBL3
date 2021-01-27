package eus.healthit.bchef.core.app.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class CustomButton extends JButton {


	private static final long serialVersionUID = 6765253974090856039L;

	public static final int LEFT_ALIGN = 0;

	public static final int CENTER_ALIGN = 1;
	
	private int align;
	
	private Color fontColor;

	private Color backgroundColor;

	private Image image;

	private String frontText;

	public CustomButton(String text, String imgPathNormal, String imgPathActive, Color fontNormal, Color fontActive,
			Color backgroundNormal, Color backgroundActive, Font font) {
		super();
		try {
			this.setUI(new RoundTransparentButton(this, fontNormal, fontActive, backgroundNormal, backgroundActive,
					ImageIO.read(new File(imgPathNormal)), ImageIO.read(new File(imgPathActive))));
			this.image = ImageIO.read(new File(imgPathNormal));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.fontColor = fontNormal;
		this.frontText = text;
		this.align = LEFT_ALIGN;
		this.backgroundColor = backgroundNormal;
		super.setBackground(Color.white);
		super.setFont(font);
	}
	
	public CustomButton(String text, String imgPathNormal, String imgPathActive, Color fontNormal, Color fontActive,
			Color backgroundNormal, Color backgroundActive, Font font, int align) {
		super();
		try {
			this.setUI(new RoundTransparentButton(this, fontNormal, fontActive, backgroundNormal, backgroundActive,
					ImageIO.read(new File(imgPathNormal)), ImageIO.read(new File(imgPathActive))));
			this.image = ImageIO.read(new File(imgPathNormal));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.fontColor = fontNormal;
		this.frontText = text;
		this.align = align;
		this.backgroundColor = backgroundNormal;
		super.setBackground(Color.white);
		super.setFont(font);
	}


	public CustomButton(String text, Color fontNormal, Color fontActive, Color backgroundNormal, Color backgroundActive,
			Font font) {
		super();
		this.setUI(new RoundTransparentButton(this, fontNormal, fontActive, backgroundNormal, backgroundActive));
		this.image = null;
		this.fontColor = fontNormal;
		this.frontText = text;
		this.align = LEFT_ALIGN;
		this.backgroundColor = backgroundNormal;
		super.setBackground(new Color(0,0,0,0));
		super.setFont(font);
	}

	public int getAlign() {
		return align;
	}
	
	public void setAlign(int align) {
		this.align = align;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getFrontText() {
		return frontText;
	}

	public void setFrontText(String frontText) {
		this.frontText = frontText;
	}

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
