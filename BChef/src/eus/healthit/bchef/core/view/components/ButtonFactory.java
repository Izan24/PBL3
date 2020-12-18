package eus.healthit.bchef.core.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import eus.healthit.bchef.core.view.borders.RoundedBorder;

public class ButtonFactory {

	public ButtonFactory() {
	}

	/**
	 * @return returns a JButton
	 *
	 * @param name     -> the name of the button
	 * @param command  -> the actionCommand
	 * @param listener -> the actionListener
	 * @param icon     -> the Icon of the button
	 * @param bgColor  -> the bgColor of the button
	 * @param fgColor  -> the fgColor of the button
	 */
	public RoundedButton createRoundedButton(String name, String command, ActionListener listener, ImageIcon icon,
			Color bgColor, Color fgColor) {

		RoundedButton button = new RoundedButton(name, command, listener, icon, bgColor, fgColor);

		return button;
	}

	public class RoundedButton extends JButton {

		String name, command;
		ActionListener listener;
		ImageIcon icon;
		Color bgColor, fgColor;

		public RoundedButton(String name, String command, ActionListener listener, ImageIcon icon, Color bgColor,
				Color fgColor) {
			super(name);
			this.setActionCommand(command);
			this.addActionListener(listener);
			this.setIcon(icon);
			this.setBorder(new RoundedBorder(30));
			this.setSize(new Dimension(50, 100));
			this.setBorderPainted(true);
			this.setOpaque(false);

			this.name = name;
			this.command = command;
			this.listener = listener;
			this.icon = icon;
			this.bgColor = bgColor;
			this.fgColor = fgColor;
		}

		@Override
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

			g2.setColor(bgColor);
			g2.fillRoundRect(this.getX(), this.getY(), this.getWidth(), this.getHeight(), 30, 30);
		

			super.paintComponent(g2);

			System.out.println(name + " " + bgColor.getRed() + " " + bgColor.getGreen() + " " + bgColor.getBlue());
		}
	}
}
