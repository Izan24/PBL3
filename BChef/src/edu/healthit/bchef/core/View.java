package edu.healthit.bchef.core;

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

		createButton();
		leftMenu.add();

		return null;
	}

	private JButton createButton(String name, String command, ActionListener listener, ImageIcon icon) {
		JButton button = new JButton(name);

		button.setActionCommand(command);
		button.addActionListener(listener);
		button.setIcon(icon);

		return button;

	}

}