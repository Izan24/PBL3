package eus.healthit.bchef.core.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import eus.healthit.bchef.core.controllers.view.LoginViewController;
import eus.healthit.bchef.core.controllers.view.LoginViewControllerAC;
import eus.healthit.bchef.core.controllers.view.NorthViewControllerAC;
import eus.healthit.bchef.core.controllers.view.ProfileVisitControllerAC;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.borders.SearchBorder;
import eus.healthit.bchef.core.view.components.RoundedJPasswordField;
import eus.healthit.bchef.core.view.components.RoundedTextField;

public class LoginView extends JPanel {

	public static final String DEFAULT_USERNAME_TEXT = "Nombre de usuario";
	public static final String DEFAULT_PASSWORD_TEXT = "Contraseña";

	Font textFont = new Font("Gill Sans MT", Font.PLAIN, 20);
	Color bgColor = Color.white;
//	Color bgColor = new Color(145, 238, 255);

	LoginViewController controller;

	JTextField username;
	JPasswordField password;
	JButton loginButton, createAccButton;
	User user;

	public LoginView() {
		super(new GridLayout(1, 1));
//		this.setBorder(BorderFactory.createEmptyBorder(150, 50, 50, 50));
		this.setBackground(bgColor);
		this.setOpaque(true);

		controller = new LoginViewController(this);

		initTextFields();
		initButtons();

		this.add(createPanel());
	}

	private void initButtons() {
		loginButton = new JButton("Iniciar sesion");
		loginButton.setPreferredSize(new Dimension(150, 35));
		loginButton.setBackground(new Color(28, 162, 243));
		loginButton.setForeground(Color.white);
		loginButton.setFont(textFont);
		loginButton.setBorder(BorderFactory.createEmptyBorder());
		loginButton.setFocusable(false);
		loginButton.addActionListener(controller);
		loginButton.setActionCommand(LoginViewControllerAC.LOGIN);

		createAccButton = new JButton("Crear cuenta");
		createAccButton.setPreferredSize(new Dimension(150, 40));
		createAccButton.setBackground(bgColor);
		createAccButton.setForeground(new Color(28, 162, 243));
		createAccButton.setBorder(new LineBorder(new Color(28, 162, 243), 1));
		createAccButton.setFont(textFont);
		createAccButton.setFocusable(false);
		createAccButton.addActionListener(controller);
		createAccButton.setActionCommand(LoginViewControllerAC.CREATE_ACCOUNT);
	}

	private void initTextFields() {
		username = new RoundedTextField(DEFAULT_USERNAME_TEXT);
		username.setPreferredSize(new Dimension(350, 20));
		username.setBorder(new SearchBorder(20, new Color(200, 200, 200)));
		username.setText(DEFAULT_USERNAME_TEXT);
		username.setForeground(Color.lightGray);

		password = new RoundedJPasswordField(DEFAULT_PASSWORD_TEXT);
		password.setPreferredSize(new Dimension(350, 20));
		password.setBorder(new SearchBorder(20, new Color(200, 200, 200)));
		password.setText(DEFAULT_PASSWORD_TEXT);
		password.setEchoChar((char) 0);
		password.setForeground(Color.lightGray);
	}

	private JPanel createPanel() {

		JPanel loginPanel = new JPanel(new GridBagLayout());

		loginPanel.setBackground(bgColor);
		loginPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 100, 0, 50);
		constraints.gridx = 0;
		constraints.gridy = 0;

		loginPanel.add(createNorthPanel(), constraints);
		constraints.gridy = 1;
		loginPanel.add(createCenterPanel(), constraints);
		constraints.gridy = 2;
		loginPanel.add(createSouthPanel(), constraints);

		return loginPanel;
	}

	private JPanel createNorthPanel() {
		JPanel panelLogo = new JPanel(new FlowLayout());
//		panelLogo.setPreferredSize(new Dimension(600, 300));
		panelLogo.setBackground(bgColor);

		ImageIcon logo = new ImageIcon("resources/viewIcons/BChefLogoWhite.png");
		JLabel labelLogo = new JLabel(logo);
		panelLogo.add(labelLogo);

		return panelLogo;
	}

	private JPanel createCenterPanel() {

		JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));

		centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		centerPanel.setBackground(bgColor);

		JPanel panelUsername = new JPanel(new FlowLayout());
		JPanel panelPassword = new JPanel(new FlowLayout());

		panelUsername.setBackground(bgColor);
		panelPassword.setBackground(bgColor);

		panelUsername.add(username);
		panelPassword.add(password);

		centerPanel.add(panelUsername);
		centerPanel.add(panelPassword);

		return centerPanel;
	}

	private Component createSouthPanel() {
		JPanel southPanel = new JPanel(new BorderLayout(30, 30));
		southPanel.setBorder(BorderFactory.createEmptyBorder(0, 225, 0, 225));
		southPanel.setBackground(bgColor);

		southPanel.add(createAccButton, BorderLayout.WEST);
		southPanel.add(loginButton, BorderLayout.EAST);
		return southPanel;
	}

}
