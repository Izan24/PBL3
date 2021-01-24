package eus.healthit.bchef.core.app.ui.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CenterViewBchef extends JPanel {

	public CenterViewBchef() {
		super(new GridLayout());

		this.add(createLogoPanel(), BorderLayout.CENTER);
	}

	private JPanel createLogoPanel() {
		JPanel panelLogo = new JPanel(new GridLayout());
		panelLogo.setPreferredSize(new Dimension(600, 600));
		panelLogo.setBackground(new Color(128, 192, 255));

		ImageIcon logo = new ImageIcon("resources/viewIcons/BChefLogoWhite.png");
		JLabel labelLogo = new JLabel(logo);
		panelLogo.add(labelLogo);

		return panelLogo;
	}
}
