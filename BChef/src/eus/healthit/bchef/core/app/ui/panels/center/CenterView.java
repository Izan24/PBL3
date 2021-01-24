package eus.healthit.bchef.core.app.ui.panels.center;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;

public class CenterView extends JPanel {

	CenterViewController controller;

	public CenterView() {
		super(new GridLayout(1, 1));
		this.setSize(new Dimension(90, 20));

		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	}

	public void setView(JComponent component) {
		this.removeAll();
		this.add(component);
		this.revalidate();
		this.repaint();
	}
}
