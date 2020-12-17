package eus.healthit.bchef.core.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import eus.healthit.bchef.core.view.components.ButtonFactory;

public class LeftMenuView extends JPanel {

	JButton buttonHome, buttonProfile, buttonList, buttonChef;

	public LeftMenuView() {
		super(new FlowLayout(10, 10, 10));
		this.setSize(new Dimension(90,20));

		initButtons();

		this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
		this.add(createMenuBar());
	}

	private void initButtons() {
		buttonHome = ButtonFactory.createRoundedButton("Home", "Home", null, null, Color.pink, Color.black);
		buttonProfile = ButtonFactory.createRoundedButton("Profile", "Profile", null, null, Color.yellow, Color.black);
		buttonList = ButtonFactory.createRoundedButton("Shopping List", "Shopping List", null, null, Color.red, Color.black);
		buttonChef = ButtonFactory.createRoundedButton("B-Chef", "B-Chef", null,
				new ImageIcon("resources/menuIcons/menuIconPrueba.png"), Color.cyan, Color.black);
	}

	private JPanel createMenuBar() {
		JPanel leftMenu = new JPanel(new GridLayout(4, 1, 10, 10));
		leftMenu.setSize(new Dimension(90, 20));

		leftMenu.add(buttonHome);
		leftMenu.add(buttonProfile);
		leftMenu.add(buttonList);
		leftMenu.add(buttonChef);

		this.add(leftMenu, BorderLayout.NORTH);

		return leftMenu;
	}
}
