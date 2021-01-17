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

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import eus.healthit.bchef.core.controllers.view.LoginViewController;
import eus.healthit.bchef.core.controllers.view.LoginViewControllerAC;
import eus.healthit.bchef.core.controllers.view.WindowFrameController;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.borders.RoundedBorder;
import eus.healthit.bchef.core.view.borders.SearchBorder;
import eus.healthit.bchef.core.view.components.RoundedJPasswordField;
import eus.healthit.bchef.core.view.components.RoundedTextField;
import eus.healthit.bchef.core.view.components.UIRoundButton;

public class LoginView extends JPanel {

	public static final String DEFAULT_USERNAME_TEXT = "Nombre de usuario";
	public static final String DEFAULT_PASSWORD_TEXT = "Contrase�a";

	Font textFont = new Font("Segoe UI", Font.PLAIN, 20);
	Color bgColor = Color.white;
//	Color bgColor = new Color(145, 238, 255);

	LoginViewController controller;

	JTextField username;
	JPasswordField password;
	JButton loginButton, createAccButton;
	JLabel textLabel;
	User user;

	public LoginView(WindowFrameController windowFrameController) {
		super(new GridLayout());
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.setPreferredSize(new Dimension(480, 640));
		this.setBackground(bgColor);
		this.setOpaque(true);

		controller = new LoginViewController(this, windowFrameController);

		initTextFields();
		initButtons();
		initJLabels();

		this.add(createBoxPanel());
	}

	private void initButtons() {
		loginButton = new JButton("Iniciar sesion");
		loginButton.setPreferredSize(new Dimension(150, 35));
		loginButton.setBackground(new Color(28, 162, 243));
		loginButton.setForeground(Color.white);
		loginButton.setFont(textFont);
		loginButton.setBorder(BorderFactory.createEmptyBorder());
		loginButton.setFocusable(false);
		loginButton.setUI(new UIRoundButton(loginButton, 30, new Color(28, 162, 243), Color.white,
				new Font("Segoe UI", Font.BOLD, 15), controller, LoginViewControllerAC.LOGIN));

		createAccButton = new JButton("Crear cuenta");
		createAccButton.setPreferredSize(new Dimension(150, 40));
		createAccButton.setBackground(bgColor);
		createAccButton.setForeground(new Color(28, 162, 243));
		createAccButton.setFont(textFont);
		createAccButton.setFocusable(false);
		createAccButton.setUI(new UIRoundButton(createAccButton, 30, bgColor, new Color(234, 246, 254),
				new Color(210, 236, 252), new Color(28, 162, 243), new Font("Segoe UI", Font.BOLD, 15), controller,
				LoginViewControllerAC.CREATE_ACCOUNT, "Crear cuenta", "Crear cuenta"));
		createAccButton.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
				BorderFactory.createEmptyBorder(60, 40, 60, 40)));
	}

	private void initTextFields() {
		username = new RoundedTextField(DEFAULT_USERNAME_TEXT);
		username.setPreferredSize(new Dimension(350, 40));
		username.setBorder(new SearchBorder(20, new Color(200, 200, 200)));
		username.setText(DEFAULT_USERNAME_TEXT);
		username.setForeground(Color.gray);
		username.setOpaque(false);

		password = new RoundedJPasswordField(DEFAULT_PASSWORD_TEXT);
		password.setPreferredSize(new Dimension(350, 40));
		password.setBorder(new SearchBorder(20, new Color(200, 200, 200)));
		password.setText(DEFAULT_PASSWORD_TEXT);
		password.setEchoChar((char) 0);
		password.setForeground(Color.gray);
	}

	private void initJLabels() {
		textLabel = new JLabel("Iniciar sesi�n");
		textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 32));
		textLabel.setBackground(Color.white);
		textLabel.setForeground(Color.gray);
	}

	private Component createBoxPanel() {

		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.add(createPanel());
		verticalBox.add(Box.createVerticalGlue());

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.add(Box.createHorizontalGlue());
		horizontalBox.add(verticalBox);
		horizontalBox.add(Box.createHorizontalGlue());

		return horizontalBox;
	}

	private JPanel createPanel() {
		JPanel flowPanel = new JPanel(new FlowLayout());
		flowPanel.setBackground(bgColor);

		JPanel loginPanel = new JPanel(new GridBagLayout());

		loginPanel.setBackground(bgColor);
		loginPanel.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
				BorderFactory.createEmptyBorder(60, 40, 60, 40)));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;

		loginPanel.add(createNorthPanel(), constraints);
		constraints.gridy = 1;
		loginPanel.add(createTextPanel(), constraints);
		constraints.gridy = 2;
		loginPanel.add(createCenterPanel(), constraints);
		constraints.gridy = 3;
		loginPanel.add(createSouthPanel(), constraints);

		flowPanel.add(loginPanel);

		return flowPanel;
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

	private JPanel createTextPanel() {
		JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		textPanel.setBackground(bgColor);

		textPanel.add(textLabel);

		return textPanel;
	}

	private JPanel createCenterPanel() {

		JPanel centerPanel = new JPanel(new GridLayout(2, 1, 10, 10));

		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 50, 0));
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
		southPanel.setBackground(bgColor);

		southPanel.add(createAccButton, BorderLayout.WEST);
		southPanel.add(loginButton, BorderLayout.EAST);
		return southPanel;
	}

//	public void resetAllFields() {
//		initTextFields();
//	}

}
