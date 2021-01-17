package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import eus.healthit.bchef.core.controllers.view.ProfileController;
import eus.healthit.bchef.core.controllers.view.ProfileControllerAC;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.borders.SearchBorder;
import eus.healthit.bchef.core.view.components.RoundedTextField;

public class CenterViewProfileSettings extends JPanel {

	public static final String DEFAULT_NAME_TEXT = "Nombre";
	public static final String DEFAULT_SURNAME_TEXT = "Apellido";
	public static final String DEFAULT_EMAIL_TEXT = "Correo electronico";
	public static final String DEFAULT_USERNAME_TEXT = "Nombre de usuario";
	public static final String DEFAULT_PWD_TEXT = "Contraseña";
	public static final String DEFAULT_CONFPWD_TEXT = "Confirma la contraseña";

	User user;

	JScrollPane scrollPane;

	JLabel profilePicture, recipesText, followingText, followersText, recipes, following, followers;
	JTextField username, email, pwd, confPwd;

	JButton uploadedButton, savedButton;

	ProfileController controller;

	Color bgColor = Color.white;

	Color textColor = new Color(129, 145, 160);
	Color selectedColor = new Color(30, 170, 255);

	Font textFont = new Font("Segoe UI", Font.PLAIN, 20);
	Font numberFont = new Font("Segoe UI", Font.BOLD, 20);

	public CenterViewProfileSettings(User user) {
		super(new GridLayout(1, 1, 100, 100));
		this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		this.setBackground(Color.white);
		this.setOpaque(true);

		this.user = user;

		initJlabels();
		initJTextField();
		initJButtons();

		this.add(createContent());
	}

	private void initJlabels() {

		profilePicture = new JLabel();
		profilePicture.setIcon(
				new ImageIcon(user.getProfilePic().getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

		recipesText = new JLabel("Recipes");
		recipesText.setFont(textFont);
		recipesText.setForeground(textColor);
		recipesText.setHorizontalAlignment(JLabel.CENTER);

		followersText = new JLabel("Followers");
		followersText.setFont(textFont);
		followersText.setForeground(textColor);
		followersText.setHorizontalAlignment(JLabel.CENTER);

		followingText = new JLabel("Following");
		followingText.setFont(textFont);
		followingText.setForeground(textColor);
		followingText.setHorizontalAlignment(JLabel.CENTER);

		recipes = new JLabel(String.valueOf(user.getPublishedNumber()));
		recipes.setFont(numberFont);
		recipes.setForeground(new Color(15, 20, 25));
		recipes.setHorizontalAlignment(JLabel.CENTER);

		following = new JLabel(String.valueOf(user.getFollowedNumber()));
		following.setFont(numberFont);
		following.setHorizontalAlignment(JLabel.CENTER);

		followers = new JLabel(String.valueOf(user.getFollowersNumber()));
		followers.setFont(numberFont);
		followers.setHorizontalAlignment(JLabel.CENTER);

	}

	private void initJTextField() {
		username = new RoundedTextField(DEFAULT_USERNAME_TEXT);
		username.setPreferredSize(new Dimension(350, 40));
		username.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		username.setText(user.getUsername());
		username.setForeground(Color.black);
		username.setOpaque(false);
	}

	private void initJButtons() {
		uploadedButton = new JButton("Mis Recetas");
		uploadedButton.setFont(textFont);
		uploadedButton.setBackground(bgColor);
		uploadedButton.setForeground(selectedColor);
		uploadedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, selectedColor));
		uploadedButton.setActionCommand(ProfileControllerAC.UPLOADED);
		uploadedButton.addActionListener(controller);
		uploadedButton.setFocusable(false);

		savedButton = new JButton("Recetas Guardadas");
		savedButton.setFont(textFont);
		savedButton.setBackground(bgColor);
		savedButton.setBorder(BorderFactory.createEmptyBorder());
		savedButton.setActionCommand(ProfileControllerAC.SAVED);
		savedButton.addActionListener(controller);
		savedButton.setFocusable(false);

	}

	private JScrollPane createContent() {
		JScrollPane scroll = new JScrollPane();
		scroll.setBackground(bgColor);

		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setBackground(bgColor);
		scroll.setOpaque(false);

		scroll.setViewportView(createMainPanel());

		return scroll;
	}

	private JPanel createMainPanel() {
		JPanel contentPanel = new JPanel(new BorderLayout(20, 0));
		contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		contentPanel.setBackground(bgColor);
		contentPanel.setOpaque(true);

		contentPanel.add(createNorthPanel(), BorderLayout.NORTH);
		contentPanel.add(createCenterPanel(), BorderLayout.CENTER);

		return contentPanel;
	}

	private JPanel createNorthPanel() {
		JPanel northPanel = new JPanel(new BorderLayout(5, 5));
		northPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
		northPanel.setBackground(bgColor);
		northPanel.setOpaque(true);

		JPanel imagePanel = new JPanel(new GridLayout(1, 1, 0, 0));
		imagePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 23));
		imagePanel.setBackground(bgColor);
		imagePanel.setOpaque(true);
		imagePanel.add(profilePicture);

		northPanel.add(imagePanel, BorderLayout.WEST);
		northPanel.add(createInfoPanel(), BorderLayout.CENTER);

		return northPanel;
	}

	private JPanel createInfoPanel() {
		JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		infoPanel.setBackground(bgColor);

		infoPanel.add(createUsernamePanel(), BorderLayout.NORTH);
		infoPanel.add(createUserDataPanel(), BorderLayout.SOUTH);

		return infoPanel;
	}

	private Component createUsernamePanel() {
		JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		usernamePanel.setBackground(bgColor);

		usernamePanel.add(username);

		return usernamePanel;
	}

	public JPanel createUserDataPanel() {
		JPanel userData = new JPanel(new GridLayout());
		userData.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		userData.setBackground(bgColor);

		userData.add(createRecipesPanel());
		userData.add(createFollowingPanel());
		userData.add(createFollowerPanel());

		return userData;
	}

	public JPanel createRecipesPanel() {
		JPanel recipePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		recipePanel.setBackground(bgColor);

		recipes.setVerticalAlignment(SwingConstants.BOTTOM);

		recipePanel.add(recipes);
		recipePanel.add(recipesText);

		return recipePanel;
	}

	public JPanel createFollowingPanel() {
		JPanel followingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		followingPanel.setBackground(bgColor);

		followingPanel.add(following);
		followingPanel.add(followingText);

		return followingPanel;
	}

	public JPanel createFollowerPanel() {
		JPanel followerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		followerPanel.setBackground(bgColor);

		followerPanel.add(followers);
		followerPanel.add(followersText);

		return followerPanel;
	}

	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(bgColor);
//		centerPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY));

		centerPanel.add(createButtonPannel(), BorderLayout.NORTH);
//		centerPanel.add(createSlidePannel(), BorderLayout.CENTER);

		return centerPanel;
	}

	private JPanel createButtonPannel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
//		buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
		buttonPanel.setBackground(bgColor);

		buttonPanel.add(uploadedButton);
		buttonPanel.add(savedButton);

		return buttonPanel;
	}

	public JPanel getPanel() {
		return this;
	}

	public void updateView() {
		recipes.setText(String.valueOf(user.getPublishedNumber()));
		following.setText(String.valueOf(user.getFollowedNumber()));
		followers.setText(String.valueOf(user.getFollowersNumber()));
	}

}