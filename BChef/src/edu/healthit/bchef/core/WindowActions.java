package edu.healthit.bchef.core;

import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class WindowActions {

	AbstractAction accHome, accProfile, accShlist, accBchef;
	JFrame view;

	public WindowActions(JFrame view) {
		initActions();
		this.view = view;
	}

	private void initActions() {
		accHome = new ActionManager("Home", new ImageIcon("resources/menuIcons/menuIconPrueba.png"), "Home", KeyEvent.VK_H, view);
		accProfile = new ActionManager("Profile", new ImageIcon("resources/menuIcons/menuIconPrueba.png"), "Profile", KeyEvent.VK_P, view);
		accShlist = new ActionManager("Shop List", new ImageIcon("resources/menuIcons/menuIconPrueba.png"), "Shop List", KeyEvent.VK_S, view);
		accBchef = new ActionManager("B-Chef", new ImageIcon("resources/menuIcons/menuIconPrueba.png"), "B-Chef", KeyEvent.VK_B, view);
	}

}
