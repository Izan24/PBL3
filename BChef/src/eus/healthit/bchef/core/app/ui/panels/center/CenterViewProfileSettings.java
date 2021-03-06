package eus.healthit.bchef.core.app.ui.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import eus.healthit.bchef.core.app.controllers.account.register.CreateAccountControllerAC;
import eus.healthit.bchef.core.app.controllers.profile.settings.ProfileSettingsController;
import eus.healthit.bchef.core.app.controllers.profile.settings.ProfileSettingsControllerAC;
import eus.healthit.bchef.core.app.controllers.windowFrame.WindowFrameController;
import eus.healthit.bchef.core.app.ui.WindowFrame;
import eus.healthit.bchef.core.app.ui.borders.RoundedBorder;
import eus.healthit.bchef.core.app.ui.borders.SearchBorder;
import eus.healthit.bchef.core.app.ui.components.CustomScrollbarUI;
import eus.healthit.bchef.core.app.ui.components.RoundedJPasswordFieldShow;
import eus.healthit.bchef.core.app.ui.components.RoundedTextField;
import eus.healthit.bchef.core.app.ui.components.UIRoundButton;
import eus.healthit.bchef.core.models.User;

public class CenterViewProfileSettings extends JPanel {

	private static final long serialVersionUID = 1138503141486221824L;

	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");

	String DEFAULT_NAME_TEXT = rb.getString("name_text");
	String DEFAULT_SURNAME_TEXT = rb.getString("surname_text");
	String DEFAULT_EMAIL_TEXT = rb.getString("email_text");
	String DEFAULT_USERNAME_TEXT = rb.getString("username_text");
	String DEFAULT_PWD_TEXT = rb.getString("pwd_text");
	String DEFAULT_NEWPWD_TEXT = rb.getString("pwd_confirm_text");

	Color bgColor = Color.white;
	Color textColor = new Color(129, 145, 160);
	Color selectedColor = new Color(30, 170, 255);

	Font textFont = new Font("Segoe UI", Font.PLAIN, 20);
	Font numberFont = new Font("Segoe UI", Font.BOLD, 20);

	User user;

	String imagePath;

	ProfileSettingsController controller;

	JScrollPane scrollPane;
	JLabel profilePicture;
	JLabel logoLabel, textLabel;

	JTextField name, surname, username, email;
	JPasswordField pwd, newPwd;

	JButton saveChanges, addImageButton, logOut;

	JCheckBox showPWDBox;

	Image image;

	public CenterViewProfileSettings(User user, WindowFrameController windowController, WindowFrame window) {
		super(new GridLayout());
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		this.setPreferredSize(new Dimension(1200, 1500));
		this.setBackground(Color.white);
		this.setOpaque(true);

		this.user = user;

		controller = new ProfileSettingsController(user, this, windowController, window);

		initCheckBoxes();
		initJlabels();
		initJTextField();
		initJButtons();

		this.add(createScrollPanel());
	}

	private void initCheckBoxes() {
		showPWDBox = new JCheckBox(rb.getString("show_pwd_text"));
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

	private void initJlabels() {

		profilePicture = new JLabel();
		profilePicture.setIcon(new ImageIcon(user.getProfilePic().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));

		logoLabel = new JLabel();
		logoLabel.setBackground(bgColor);
		logoLabel.setIcon(new ImageIcon("resources/menuIcons/bchef_icon.png"));

		textLabel = new JLabel(rb.getString("edit_bchef_acc_text"));
		textLabel.setFont(new Font("Segoe UI", Font.PLAIN, 25));
		textLabel.setBackground(Color.white);
		textLabel.setForeground(Color.gray);

	}

	private void initJTextField() {
		username = new RoundedTextField(DEFAULT_USERNAME_TEXT);
		username.setPreferredSize(new Dimension(280, 40));
		username.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		username.setText(user.getUsername());
		username.setForeground(Color.black);
		username.setOpaque(false);

		name = new RoundedTextField(DEFAULT_NAME_TEXT);
		name.setPreferredSize(new Dimension(280, 40));
		name.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		name.setText(user.getName());
		name.setForeground(Color.black);
		name.setOpaque(false);

		surname = new RoundedTextField(DEFAULT_SURNAME_TEXT);
		surname.setPreferredSize(new Dimension(280, 40));
		surname.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		surname.setText(user.getSurname());
		surname.setForeground(Color.black);
		surname.setOpaque(false);

		email = new RoundedTextField(DEFAULT_EMAIL_TEXT);
		email.setPreferredSize(new Dimension(280, 40));
		email.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		email.setText(user.getEmail());
		email.setForeground(Color.black);
		email.setOpaque(false);

		pwd = new RoundedJPasswordFieldShow(DEFAULT_PWD_TEXT, showPWDBox);
		pwd.setPreferredSize(new Dimension(280, 40));
		pwd.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		pwd.setText(DEFAULT_PWD_TEXT);
		pwd.setEchoChar((char) 0);
		pwd.setForeground(Color.gray);

		newPwd = new RoundedJPasswordFieldShow(DEFAULT_NEWPWD_TEXT, showPWDBox);
		newPwd.setPreferredSize(new Dimension(280, 40));
		newPwd.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		newPwd.setText(DEFAULT_NEWPWD_TEXT);
		newPwd.setEchoChar((char) 0);
		newPwd.setForeground(Color.gray);
	}

	private void initJButtons() {
		addImageButton = new JButton(rb.getString("add_img_text"));
		addImageButton.setPreferredSize(new Dimension(125, 45));
		addImageButton.setBackground(bgColor);
		addImageButton.setForeground(new Color(28, 162, 243));
		addImageButton.setFont(textFont);
		addImageButton.setFocusable(false);
		addImageButton.setUI(new UIRoundButton(addImageButton, 30, bgColor, new Color(234, 246, 254),
				new Color(210, 236, 252), new Color(28, 162, 243), new Font("Segoe UI", Font.BOLD, 15), controller,
				ProfileSettingsControllerAC.UPLOAD_IMAGE, rb.getString("add_img_text"), rb.getString("add_img_text")));
		addImageButton.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
				BorderFactory.createEmptyBorder(60, 40, 60, 40)));

		saveChanges = new JButton(rb.getString("save_changes_text"));
		saveChanges.setPreferredSize(new Dimension(150, 35));
		saveChanges.setBackground(new Color(28, 162, 243));
		saveChanges.setForeground(Color.white);
		saveChanges.setFont(textFont);
		saveChanges.setBorder(BorderFactory.createEmptyBorder());
		saveChanges.setFocusable(false);
		saveChanges.setUI(new UIRoundButton(saveChanges, 30, new Color(28, 162, 243), Color.white,
				new Font("Segoe UI", Font.BOLD, 13), controller, ProfileSettingsControllerAC.SAVE_CHANGES));

		logOut = new JButton(rb.getString("logout_text"));
		logOut.setPreferredSize(new Dimension(150, 40));
		logOut.setBackground(bgColor);
		logOut.setForeground(new Color(28, 162, 243));
		logOut.setFont(textFont);
		logOut.setFocusable(false);
		logOut.setUI(new UIRoundButton(logOut, 30, bgColor, new Color(255, 217, 217), new Color(196, 35, 93),
				new Font("Segoe UI", Font.BOLD, 13), controller, ProfileSettingsControllerAC.LOG_OUT,
				rb.getString("logout_text"), rb.getString("logout_text")));
		logOut.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(196, 35, 93)),
				BorderFactory.createEmptyBorder(60, 40, 60, 40)));
	}

	private Component createScrollPanel() {
		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(bgColor);
		scrollPane.setOpaque(true);

		scrollPane.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setViewportView(createBoxPanel());

		return scrollPane;
	}

	private Component createBoxPanel() {
		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.add(createFlow());
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.setBackground(bgColor);
		verticalBox.setOpaque(true);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.add(Box.createHorizontalGlue());
		horizontalBox.add(verticalBox);
		horizontalBox.add(Box.createHorizontalGlue());
		horizontalBox.setBackground(bgColor);
		horizontalBox.setOpaque(true);

		return horizontalBox;
	}

	private Component createFlow() {
		JPanel flowPanel = new JPanel(new FlowLayout());
		flowPanel.setBackground(bgColor);

		flowPanel.add(createMainPanel());

		return flowPanel;
	}

	private JPanel createMainPanel() {
		JPanel contentPanel = new JPanel(new GridLayout(1, 1));
		contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		contentPanel.setBackground(bgColor);
		contentPanel.setOpaque(true);

		contentPanel.add(createNorthPanel());

		return contentPanel;
	}

	private JPanel createNorthPanel() {
		JPanel northPanel = new JPanel(new BorderLayout(5, 5));
		northPanel.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
				BorderFactory.createEmptyBorder(60, 40, 60, 40)));
		northPanel.setBackground(bgColor);
		northPanel.setOpaque(true);

		northPanel.add(createImagePanel(), BorderLayout.WEST);
		northPanel.add(createInfoPanel(), BorderLayout.CENTER);
		northPanel.add(createTittlePanel(), BorderLayout.NORTH);

		return northPanel;
	}

	private JPanel createInfoPanel() {
		JPanel contentPanel = new JPanel(new GridBagLayout());

		contentPanel.setBackground(bgColor);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.gridy = 0;
		constraints.gridx = 0;

		contentPanel.add(createNameSurnamePanel(), constraints);
		constraints.gridy = 1;
		contentPanel.add(createEmailPanel(), constraints);
		constraints.gridy = 2;
		contentPanel.add(createUsername(), constraints);
		constraints.gridy = 3;
		contentPanel.add(createPwdPanel(), constraints);
		constraints.gridy = 4;
		contentPanel.add(createShowPwdPanel(), constraints);
		constraints.gridy = 5;
		contentPanel.add(createButtonPanel(), constraints);

		return contentPanel;
	}

	private Box createTittlePanel() {
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
		pwdPanel.add(newPwd);

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

		buttonPanel.add(saveChanges, BorderLayout.EAST);
		buttonPanel.add(logOut, BorderLayout.WEST);
		return buttonPanel;
	}

	private Component createImagePanel() {
		Box imagePanel = Box.createVerticalBox();

		imagePanel.add(createProfilePicPanel());
		imagePanel.add(Box.createRigidArea(new Dimension(10, 0)));
		imagePanel.add(createAddButonPanel());
		imagePanel.add(Box.createVerticalStrut(33));

		return imagePanel;
	}

	private Component createProfilePicPanel() {
		JPanel profilepicPanel = new JPanel(new FlowLayout());
		profilepicPanel.setBackground(bgColor);

		profilepicPanel.add(profilePicture);

		return profilepicPanel;
	}

	private Component createAddButonPanel() {
		JPanel butonPanel = new JPanel(new FlowLayout());
		butonPanel.setBackground(bgColor);

		butonPanel.add(addImageButton);

		return butonPanel;
	}

	public JPanel getPanel() {
		return this;
	}

	public void updateView() {
		profilePicture.setIcon(new ImageIcon(user.getProfilePic().getScaledInstance(125, 125, Image.SCALE_SMOOTH)));
		username.setText(user.getUsername());
		name.setText(user.getName());
		surname.setText(user.getSurname());
		email.setText(user.getEmail());
		pwd.setText(DEFAULT_PWD_TEXT);
		pwd.setEchoChar((char) 0);
		newPwd.setText(DEFAULT_NEWPWD_TEXT);
		newPwd.setEchoChar((char) 0);
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return image;
	}

	public String getPwd() {
		return String.valueOf(pwd.getPassword());
	}

	public JLabel getProfilePicture() {
		return profilePicture;
	}

	public String getName() {
		return name.getText();
	}

	public String getSurname() {
		return surname.getText();
	}

	public String getUsername() {
		return username.getText();
	}

	public String getEmail() {
		return email.getText();
	}

	public String getNewPwd() {
		return String.valueOf(newPwd.getPassword());
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void changePwdState() {
		if (pwd.getEchoChar() == (char) 0) {
			pwd.setEchoChar('*');
		} else if (pwd.getEchoChar() == '*') {
			pwd.setEchoChar((char) 0);
		}
	}

	public void changenewPwdState() {
		if (newPwd.getEchoChar() == (char) 0) {
			newPwd.setEchoChar('*');
		} else if (newPwd.getEchoChar() == '*') {
			newPwd.setEchoChar((char) 0);
		}
	}
}