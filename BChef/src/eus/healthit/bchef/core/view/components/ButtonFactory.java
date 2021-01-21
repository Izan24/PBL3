package eus.healthit.bchef.core.view.components;

import java.awt.AWTEvent;
import java.awt.AWTEventMulticaster;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

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
	public static RoundedButton createRoundedButton(String name, String command, ActionListener listener,
			ImageIcon icon, Color bgColor, Color fgColor, Font font) {

		RoundedButton button = new RoundedButton(name, command, listener, icon, bgColor, fgColor, font);

		return button;
	}

	public static class RoundedButton extends JButton {

		String name, command;
		ActionListener listener;
		ImageIcon icon;
		Color bgColor, fgColor;
		boolean pressed;
		Font font;

		public RoundedButton(String name, String command, ActionListener listener, ImageIcon icon, Color bgColor,
				Color fgColor, Font font) {
			super(name);

			this.name = name;
			this.command = command;
			this.listener = listener;
			this.icon = icon;
			this.bgColor = bgColor;
			this.fgColor = fgColor;
			this.font = font;

			super.setActionCommand(command);
			super.addActionListener(listener);
			super.setIcon(icon);
			super.setBorder(new RoundedBorder(30, Color.black));
			super.setSize(new Dimension(50, 100));
			super.setBackground(bgColor);
			super.setForeground(fgColor);
			super.setActionCommand(command);
			super.setBorderPainted(false);
			super.setOpaque(true);
			super.setFocusPainted(false);
			enableEvents(AWTEvent.MOUSE_EVENT_MASK);

		}

		/**
		 * gets the label
		 *
		 * @see setLabel
		 */
		public String getLabel() {
			return name;
		}

		/**
		 * sets the label
		 *
		 * @see getLabel
		 */
		public void setLabel(String label) {
			this.name = label;
			invalidate();
			repaint();
		}

		/**
		 * paints the RoundedButton
		 */
		@Override
		public void paint(Graphics g) {

			// paint the interior of the button
			if (pressed) {
				g.setColor(Color.gray);
			} else {
				g.setColor(bgColor);
			}
			g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

			// draw the perimeter of the button
			g.setColor(getBackground().darker().darker().darker());
			g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

			// draw the label centered in the button
			Font f = font;
			if (f != null) {
				FontMetrics fm = getFontMetrics(font);
				g.setColor(fgColor);
				g.drawString(name, getWidth() / 2 - fm.stringWidth(name) / 2, getHeight() / 2 + fm.getMaxDescent());
			}
		}

		/**
		 * The preferred size of the button.
		 */
		@Override
		public Dimension getPreferredSize() {
			Font f = font;
			if (f != null) {
				FontMetrics fm = getFontMetrics(getFont());
				int max = Math.max(fm.stringWidth(name) + 40, fm.getHeight() + 40);
				return new Dimension(max, max);
			} else {
				return new Dimension(100, 100);
			}
		}

		/**
		 * The minimum size of the button.
		 */
		@Override
		public Dimension getMinimumSize() {
			return new Dimension(100, 100);
		}

		/**
		 * Adds the specified action listener to receive action events from this button.
		 *
		 * @param listener the action listener
		 */
		public void addActionListener(ActionListener listener) {
			actionListener = AWTEventMulticaster.add(actionListener, listener);
			enableEvents(AWTEvent.MOUSE_EVENT_MASK);
		}

		/**
		 * Removes the specified action listener so it no longer receives action events
		 * from this button.
		 *
		 * @param listener the action listener
		 */
		public void removeActionListener(ActionListener listener) {
			actionListener = AWTEventMulticaster.remove(actionListener, listener);
		}

		/**
		 * Determine if click was inside round button.
		 */
		@Override
		public boolean contains(int x, int y) {
			int mx = getSize().width / 2;
			int my = getSize().height / 2;
			return (((mx - x) * (mx - x) + (my - y) * (my - y)) <= mx * mx);
		}

		/**
		 * Paints the button and distribute an action event to all listeners.
		 */
		@Override
		public void processMouseEvent(MouseEvent e) {
			Graphics g;
			switch (e.getID()) {
			case MouseEvent.MOUSE_PRESSED:
				pressed = true;

				repaint();
				break;
			case MouseEvent.MOUSE_RELEASED:
				if (actionListener != null) {
					actionListener.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, name));
				}
				if (pressed == true) {
					pressed = false;

					repaint();
				}
				break;
			case MouseEvent.MOUSE_ENTERED:

				break;
			case MouseEvent.MOUSE_EXITED:
				if (pressed == true) {
					pressed = false;

					repaint();
				}
				break;
			}
			super.processMouseEvent(e);
		}
	}
}