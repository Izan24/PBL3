package eus.healthit.bchef.core.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.ButtonUI;

public class UIRoundButton extends ButtonUI implements MouseListener {

	int radius;
	Color backGroundColor;
	Color backGroundColorActive;
	Color backGroundColorPressed;
	Color foreGroundColor;
	Font font;

	public UIRoundButton(JButton j, int radius, Color backGroundColor, Color foreGroundColor, Font font) {
		this.radius = radius;
		this.backGroundColor = backGroundColor;
		this.backGroundColorActive = backGroundColor.darker();
		this.backGroundColorPressed = backGroundColorActive.darker();
		this.foreGroundColor = foreGroundColor;
		this.font = font;
		j.addMouseListener(this);
		j.setBackground(backGroundColor);
		j.setOpaque(false);
	}

	@Override
	public void paint(Graphics g, JComponent j) {
		Graphics2D g2 = (Graphics2D) g;
		JButton jButton = (JButton) j;
		g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

		g2.setColor(jButton.getBackground());
		g2.fillRoundRect(0, 0, jButton.getWidth(), jButton.getHeight(), radius, radius);
		g2.setFont(font);
		FontMetrics fm = g2.getFontMetrics(font);
		g2.setColor(this.foreGroundColor);
		g2.drawString(jButton.getText(), (j.getWidth() / 2 - fm.stringWidth(jButton.getText()) / 2),
				(int) (jButton.getSize().getHeight() + fm.getHeight() / 2) / 2);

	}

	public void mousePressed(MouseEvent e) {
		JButton c = (JButton) e.getComponent();
		c.setBackground(backGroundColorPressed);
	}

	public void mouseReleased(MouseEvent e) {
		JButton c = (JButton) e.getComponent();
		if ((e.getX() > c.getX() && e.getX() < c.getWidth() + c.getX())
				&& (e.getY() > c.getY() && e.getY() < c.getHeight() + c.getY())) {
			c.setBackground(backGroundColorActive);
		} else {
			c.setBackground(backGroundColor);
		}
	}

	public void mouseEntered(MouseEvent e) {
		JButton c = (JButton) e.getComponent();
		c.setBackground(backGroundColorActive);

	}

	public void mouseExited(MouseEvent e) {
		JButton c = (JButton) e.getComponent();
		c.setBackground(backGroundColor);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Uwu");

	}

}
