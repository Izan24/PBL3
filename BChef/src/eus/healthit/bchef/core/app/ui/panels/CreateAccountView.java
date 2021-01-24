package eus.healthit.bchef.core.app.ui.panels;

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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import eus.healthit.bchef.core.app.controllers.account.register.CreateAccountController;
import eus.healthit.bchef.core.app.controllers.account.register.CreateAccountControllerAC;
import eus.healthit.bchef.core.app.controllers.windowFrame.WindowFrameController;
import eus.healthit.bchef.core.app.ui.WindowFrame;
import eus.healthit.bchef.core.app.ui.borders.RoundedBorder;
import eus.healthit.bchef.core.app.ui.borders.SearchBorder;
import eus.healthit.bchef.core.app.ui.components.RoundedJPasswordFieldShow;
import eus.healthit.bchef.core.app.ui.components.RoundedTextField;
import eus.healthit.bchef.core.app.ui.components.UIRoundButton;

public class CreateAccountView extends JPanel {

	public static final String DEFAULT_NAME_TEXT = "Nombre";
	public static final String DEFAULT_SURNAME_TEXT = "Apellido";
	public static final String DEFAULT_EMAIL_TEXT = "Correo electronico";
	public static final String DEFAULT_USERNAME_TEXT = "Nombre de usuario";
	public static final String DEFAULT_PWD_TEXT = "Contrase�a";
	public static final String DEFAULT_CONFPWD_TEXT = "Confirma la contrase�a";

	Font textFont = new Font("Segoe UI", Font.PLAIN, 20);

	Color bgColor = Color.white;
	JTextField name, surname, email, username;

	JPasswordField pwd, pwdConfirm;
	JLabel logoLabel, textLabel;

	JButton createAcc, gotoLogin;

	JCheckBox showPWDBox;

	CreateAccountController controller;

	public CreateAccountView(WindowFrameController windowFrameController, WindowFrame window) {
		super(new GridLayout());
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.setPreferredSize(new Dimension(480, 640));
		this.setBackground(bgColor);
		this.setOpaque(true);

		controller = new CreateAccountController(this, windowFrameController, window);

		initCheckBoxes();
		initButtons();
		initTextFields();
		initJLabels();

		this.add(createBoxPanel());
	}

	private void initButtons() {
		createAcc = new JButton("Crear cuenta");
		createAcc.setPreferredSize(new Dimension(150, 35));
		createAcc.setBackground(new Color(28, 162, 243));
		createAcc.setForeground(Color.white);
		createAcc.setFont(textFont);
		createAcc.setBorder(BorderFactory.createEmptyBorder());
		createAcc.setFocusable(false);
		createAcc.setUI(new UIRoundButton(createAcc, 30, new Color(28, 162, 243), Color.white,
				new Font("Segoe UI", Font.BOLD, 13), controller, CreateAccountControllerAC.CREATEACC));

		gotoLogin = new JButton("Ya tengo una cuenta");
		gotoLogin.setPreferredSize(new Dimension(150, 40));
		gotoLogin.setBackground(bgColor);
		gotoLogin.setForeground(new Color(28, 162, 243));
		gotoLogin.setFont(textFont);
		gotoLogin.setFocusable(false);
		gotoLogin.setUI(new UIRoundButton(gotoLogin, 30, bgColor, new Color(234, 246, 254), new Color(210, 236, 252),
				new Color(28, 162, 243), new Font("Segoe UI", Font.BOLD, 13), controller,
				CreateAccountControllerAC.GOTO_LOGIN, "Ya tengo una cuenta", "Ya tengo una cuenta"));
		gotoLogin.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
				BorderFactory.createEmptyBorder(60, 40, 60, 40)));
	}

	private void initTextFields() {
		name = new RoundedTextField(DEFAULT_NAME_TEXT);
		name.setPreferredSize(new Dimension(350, 40));
		name.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		name.setText(DEFAULT_NAME_TEXT);
		name.setForeground(Color.gray);
		name.setOpaque(false);

		surname = new RoundedTextField(DEFAULT_SURNAME_TEXT);
		surname.setPreferredSize(new Dimension(350, 40));
		surname.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		surname.setText(DEFAULT_SURNAME_TEXT);
		surname.setForeground(Color.gray);
		surname.setOpaque(false);

		email = new RoundedTextField(DEFAULT_EMAIL_TEXT);
		email.setPreferredSize(new Dimension(350, 40));
		email.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		email.setText(DEFAULT_EMAIL_TEXT);
		email.setForeground(Color.gray);
		email.setOpaque(false);

		username = new RoundedTextField(DEFAULT_USERNAME_TEXT);
		username.setPreferredSize(new Dimension(350, 40));
		username.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		username.setText(DEFAULT_USERNAME_TEXT);
		username.setForeground(Color.gray);
		username.setOpaque(false);

		pwd = new RoundedJPasswordFieldShow(DEFAULT_PWD_TEXT, showPWDBox);
		pwd.setPreferredSize(new Dimension(350, 40));
		pwd.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		pwd.setText(DEFAULT_PWD_TEXT);
		pwd.setEchoChar((char) 0);
		pwd.setForeground(Color.gray);

		pwdConfirm = new RoundedJPasswordFieldShow(DEFAULT_CONFPWD_TEXT, showPWDBox);
		pwdConfirm.setPreferredSize(new Dimension(350, 40));
		pwdConfirm.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		pwdConfirm.setText(DEFAULT_CONFPWD_TEXT);
		pwdConfirm.setEchoChar((char) 0);
		pwdConfirm.setForeground(Color.gray);
	}

	private void initJLabels() {
		logoLabel = new JLabel();
		logoLabel.setBackground(bgColor);
		logoLabel.setIcon(new ImageIcon("resources/menuIcons/bchef_icon.png"));

		textLabel = new JLabel("Crea una cuenta de BChef");
		textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		textLabel.setBackground(Color.white);
		textLabel.setForeground(Color.gray);
	}

	private void initCheckBoxes() {
		showPWDBox = new JCheckBox("Mostrar contrase�a");
		showPWDBox.addActionListener(controller);
		showPWDBox.setActionCommand(CreateAccountControllerAC.SHOW_PWD);
		showPWDBox.setBackground(bgColor);
		showPWDBox.setForeground(Color.LIGHT_GRAY);
		showPWDBox.setBorder(BorderFactory.createEmptyBorder());
		showPWDBox.setIcon(new ImageIcon("resources/menuicons/unChecked.png"));
		showPWDBox.setSelectedIcon(new ImageIcon("resources/menuicons/checked.png"));
		showPWDBox.setRolloverSelectedIcon(new ImageIcon("resources/menuicons/checkedMouseOver.png"));
		showPWDBox.setRolloverIcon(new ImageIcon("resources/menuicons/unCheckedMouseOver.png"));
		showPWDBox.setFocusable(false);
	}

	private Component createBoxPanel() {
		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.add(createFlow());
		verticalBox.add(Box.createVerticalGlue());

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.add(Box.createHorizontalGlue());
		horizontalBox.add(verticalBox);
		horizontalBox.add(Box.createHorizontalGlue());

		return horizontalBox;
	}

	private Component createFlow() {
		JPanel flowPanel = new JPanel(new FlowLayout());
		flowPanel.setBackground(bgColor);

		flowPanel.add(createContentPanel());

		return flowPanel;
	}

	private Component createContentPanel() {
		JPanel contentPanel = new JPanel(new GridBagLayout());

		contentPanel.setBackground(bgColor);
		contentPanel.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
				BorderFactory.createEmptyBorder(60, 40, 60, 40)));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;

		contentPanel.add(createTitlePanel(), constraints);
		constraints.gridy = 1;
		contentPanel.add(createNameSurnamePanel(), constraints);
		constraints.gridy = 2;
		contentPanel.add(createEmailPanel(), constraints);
		constraints.gridy = 3;
		contentPanel.add(createUsername(), constraints);
		constraints.gridy = 4;
		contentPanel.add(createPwdPanel(), constraints);
		constraints.gridy = 5;
		contentPanel.add(createShowPwdPanel(), constraints);
		constraints.gridy = 6;
		contentPanel.add(createButtonPanel(), constraints);

		return contentPanel;
	}

	private Box createTitlePanel() {
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		horizontalBox.add(logoLabel);
		horizontalBox.add(Box.createRigidArea(new Dimension(6, 0)));
		horizontalBox.add(textLabel);

		return horizontalBox;
	}

	private Component createNameSurnamePanel() {
		JPanel nameSurnamePanel = new JPanel(new GridLayout(1, 2, 10, 10));
		nameSurnamePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		nameSurnamePanel.setBackground(bgColor);

		nameSurnamePanel.add(name);
		nameSurnamePanel.add(surname);

		return nameSurnamePanel;
	}

	private Component createEmailPanel() {
		JPanel emailPanel = new JPanel(new GridLayout(1, 1, 10, 10));
		emailPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		emailPanel.setBackground(bgColor);

		emailPanel.add(email);

		return emailPanel;
	}

	private Component createUsername() {
		JPanel usernamePanel = new JPanel(new GridLayout(1, 1, 10, 10));
		usernamePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		usernamePanel.setBackground(bgColor);

		usernamePanel.add(username);

		return usernamePanel;
	}

	private Component createPwdPanel() {
		JPanel pwdPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		pwdPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		pwdPanel.setBackground(bgColor);

		pwdPanel.add(pwd);
		pwdPanel.add(pwdConfirm);

		return pwdPanel;
	}

	private Component createShowPwdPanel() {
		JPanel showPwd = new JPanel(new FlowLayout(FlowLayout.LEFT));
		showPwd.setBackground(bgColor);
		showPwd.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

		showPwd.add(showPWDBox);

		return showPwd;
	}

	private Component createButtonPanel() {
		JPanel buttonPanel = new JPanel(new BorderLayout(30, 30));
		buttonPanel.setBackground(bgColor);

		buttonPanel.add(createAcc, BorderLayout.EAST);
		buttonPanel.add(gotoLogin, BorderLayout.WEST);
		return buttonPanel;
	}

	public void changePwdState() {
		if (pwd.getEchoChar() == (char) 0) {
			pwd.setEchoChar('*');
		} else if (pwd.getEchoChar() == '*') {
			pwd.setEchoChar((char) 0);
		}
	}

	public void changePwdConfirmState() {
		if (pwdConfirm.getEchoChar() == (char) 0) {
			pwdConfirm.setEchoChar('*');
		} else if (pwdConfirm.getEchoChar() == '*') {
			pwdConfirm.setEchoChar((char) 0);
		}
	}

	public String getName() {
		return name.getText();
	}

	public String getSurname() {
		return surname.getText();
	}

	public String getEmail() {
		return email.getText();
	}

	public String getUsername() {
		return username.getText();
	}

	public String getPwd() {
		return String.valueOf(pwd.getPassword());
	}

	public String getPwdConfirm() {
		return String.valueOf(pwdConfirm.getPassword());
	}
}
