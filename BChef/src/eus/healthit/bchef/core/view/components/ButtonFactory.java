package eus.healthit.bchef.core.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import eus.healthit.bchef.core.view.borders.RoundedBorder;

public class ButtonFactory {

	public class roundedButton extends JButton {

		String name, command;
		ActionListener listener; 
		ImageIcon icon; 
		Color bgColor, fgColor;
		
		public roundedButton(String name, String command, ActionListener listener, ImageIcon icon, Color bgColor,
				Color fgColor) {
			super(name);
			this.setActionCommand(command);
			this.addActionListener(listener);
			this.setIcon(icon);
			this.setBorder(new RoundedBorder(30));
			this.setSize(new Dimension(50, 100));
			this.setBorderPainted(true);
			this.setBackground(bgColor);
		}

		@Override
		protected void paintComponent(Graphics g) {
			g.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
		}	
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
	public static JButton createRoundedButton(String name, String command, ActionListener listener, ImageIcon icon,
			Color bgColor, Color fgColor) {
		JButton button = new JButton(name);
		button.setActionCommand(command);
		button.addActionListener(listener);
		button.setIcon(icon);
		button.setBorder(new RoundedBorder(30));
		button.setSize(new Dimension(50, 100));
		button.setBorderPainted(true);
		button.setBackground(bgColor);

		return button;
	}
}
