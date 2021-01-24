package eus.healthit.bchef.core.app.ui.components;

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

import eus.healthit.bchef.core.app.controllers.interfaces.IRoundButtonListener;

public class UIRoundButton extends ButtonUI implements MouseListener {

	int radius;
	Color backGroundColor;
	Color backGroundColorActive;
	Color backGroundColorPressed;
	Color foreGroundColor;
	Font font;
	IRoundButtonListener listener;
	String actionCommand;
	String primaryText, secondaryText;

//	public UIRoundButton(JButton j, int radius, Color backGroundColor, Color foreGroundColor, Font font) {
//		this.radius = radius;
//		this.backGroundColor = backGroundColor;
//		this.backGroundColorActive = backGroundColor.darker();
//		this.backGroundColorPressed = backGroundColorActive.darker();
//		this.foreGroundColor = foreGroundColor;
//		this.font = font;
//		j.addMouseListener(this);
//		j.setBackground(backGroundColor);
//		j.setOpaque(false);
//	}

	public UIRoundButton(JButton j, int radius, Color backGroundColor, Color foreGroundColor, Font font,
			IRoundButtonListener listener, String actionCommand) {
		this.radius = radius;
		this.backGroundColor = backGroundColor;
		this.backGroundColorActive = backGroundColor.darker();
		this.backGroundColorPressed = backGroundColorActive.darker();
		this.foreGroundColor = foreGroundColor;
		this.font = font;
		this.listener = listener;
		this.actionCommand = actionCommand;
		j.addMouseListener(this);
		j.setBackground(backGroundColor);
		j.setOpaque(false);
	}

	public UIRoundButton(JButton j, int radius, Color backGroundColorPrimary, Color backGroundColorSecondary,
			Color foreGroundColor, Font font, IRoundButtonListener listener, String actionCommand, String primaryText,
			String secondaryText) {
		this.radius = radius;
		this.backGroundColor = backGroundColorPrimary;
		this.backGroundColorActive = backGroundColorSecondary;
		this.backGroundColorPressed = backGroundColorSecondary.darker();
		this.foreGroundColor = foreGroundColor;
		this.font = font;
		this.listener = listener;
		this.actionCommand = actionCommand;
		this.primaryText = primaryText;
		this.secondaryText = secondaryText;
		j.addMouseListener(this);
		j.setBackground(backGroundColor);
		j.setOpaque(false);
	}

	public UIRoundButton(JButton j, int radius, Color backGroundColorPrimary, Color backGroundColorSecondary,
			Color backGroundColorTerciary, Color foreGroundColor, Font font, IRoundButtonListener listener,
			String actionCommand, String primaryText, String secondaryText) {
		this.radius = radius;
		this.backGroundColor = backGroundColorPrimary;
		this.backGroundColorActive = backGroundColorSecondary;
		this.backGroundColorPressed = backGroundColorTerciary;
		this.foreGroundColor = foreGroundColor;
		this.font = font;
		this.listener = listener;
		this.actionCommand = actionCommand;
		this.primaryText = primaryText;
		this.secondaryText = secondaryText;
		j.addMouseListener(this);
		j.setBackground(backGroundColor);
		j.setOpaque(false);
	}

	public UIRoundButton(JButton j, int radius, Color backGroundColorPrimary, Color backGroundColorSecondary,
			Color backGroundColorTerciary, Color foreGroundColor, Font font, IRoundButtonListener listener,
			String actionCommand, String primaryText) {
		this.radius = radius;
		this.backGroundColor = backGroundColorPrimary;
		this.backGroundColorActive = backGroundColorSecondary;
		this.backGroundColorPressed = backGroundColorTerciary;
		this.foreGroundColor = foreGroundColor;
		this.font = font;
		this.listener = listener;
		this.actionCommand = actionCommand;
		this.primaryText = primaryText;
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
		if (secondaryText != null && primaryText != null) {
			c.setText(secondaryText);
		}
	}

	public void mouseExited(MouseEvent e) {
		JButton c = (JButton) e.getComponent();
		c.setBackground(backGroundColor);
		if (secondaryText != null && primaryText != null) {
			c.setText(primaryText);
		}
	}

	public void setPrimaryText(String primaryText) {
		this.primaryText = primaryText;
	}

	public void setSecondaryText(String secondaryText) {
		this.secondaryText = secondaryText;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		listener.actionPerformed(actionCommand);
	}

	public void setBackgroundColorPrimary(Color color) {
		this.backGroundColor = color;
	}

	public void setBackgrounSecondary(Color color) {
		this.backGroundColorActive = color;
		this.backGroundColorPressed = color.darker();
	}

	public void serBackgroundSimple(Color color) {
		this.backGroundColor = color;
		this.backGroundColorActive = backGroundColor.darker();
		this.backGroundColorPressed = backGroundColorActive.darker();
	}

}
