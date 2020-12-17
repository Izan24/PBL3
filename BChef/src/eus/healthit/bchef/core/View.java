package eus.healthit.bchef.core;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel {

	JPanel leftMenu;

	public View() {
		super(new BorderLayout());
		setContent();
	}

	private void setContent() {
		this.add(createMenuBar());
	}

	private JPanel createMenuBar() {
		leftMenu = new JPanel(new GridLayout(1, 4, 10, 10));

		leftMenu.add(createRoundedButton("Home", "Home", null, null));

		return null;
	}

	private JButton createRoundedButton(String name, String command, ActionListener listener, ImageIcon icon) {
		JButton button = new JButton(name);

		button.setActionCommand(command);
		button.addActionListener(listener);
		button.setIcon(icon);
		button.setBorder(new RoundedBorder(23));

		return button;

	}

}