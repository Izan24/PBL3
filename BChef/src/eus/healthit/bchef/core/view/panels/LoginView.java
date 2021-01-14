package eus.healthit.bchef.core.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import eus.healthit.bchef.core.models.User;

public class LoginView extends JPanel {

	JTextField username;
	JPasswordField password;
	User user;

	public LoginView() {
		super(new BorderLayout(50, 50));
		this.setBorder(BorderFactory.createEmptyBorder(150,50,50,50));
		this.setBackground(Color.getHSBColor((float) 182 / 360, (float) 0.44, (float) 0.97));
		this.setOpaque(true);

		addComponents();
	}

	private void addComponents() {
		this.add(createNorthPanel(), BorderLayout.NORTH);
		this.add(createSouthPanel(), BorderLayout.CENTER);
	}

	private JPanel createNorthPanel() {
		JPanel panelLogo = new JPanel(new FlowLayout());
		panelLogo.setPreferredSize(new Dimension(600, 300));
		panelLogo.setBackground(Color.getHSBColor((float) 182 / 360, (float) 0.44, (float) 0.97));

		ImageIcon logo = new ImageIcon("resources/viewIcons/BChefLogoWhite.png");
		JLabel labelLogo = new JLabel(logo);
		panelLogo.add(labelLogo);

		return panelLogo;
	}

	private JPanel createSouthPanel() {

		JPanel southPanel = new JPanel(new GridLayout(2, 1, 10, 10));

		southPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		southPanel.setBackground(Color.getHSBColor((float) 182 / 360, (float) 0.44, (float) 0.97));

		JPanel panelUsername = new JPanel(new FlowLayout());
		JPanel panelPassword = new JPanel(new FlowLayout());

		panelUsername.setBackground(Color.getHSBColor((float) 182 / 360, (float) 0.44, (float) 0.97));
		panelPassword.setBackground(Color.getHSBColor((float) 182 / 360, (float) 0.44, (float) 0.97));

		username = new JTextField("");
		password = new JPasswordField("");
		username.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		password.setFont(new Font("Gill Sans MT", Font.PLAIN, 15));
		username.setPreferredSize(new Dimension(500, 20));
		password.setPreferredSize(new Dimension(500, 20));
		username.setToolTipText("Username");
		password.setToolTipText("Password");

		panelUsername.add(username);
		panelPassword.add(password);

		southPanel.add(panelUsername);
		southPanel.add(panelPassword);

		return southPanel;
	}

}
