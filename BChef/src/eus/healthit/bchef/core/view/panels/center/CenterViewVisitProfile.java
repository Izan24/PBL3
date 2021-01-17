package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eus.healthit.bchef.core.controllers.view.ProfileVisitController;
import eus.healthit.bchef.core.controllers.view.ProfileVisitControllerAC;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.components.UIRoundButton;
import eus.healthit.bchef.core.view.recipes.RecipesList;
import eus.healthit.bchef.core.view.recipes.RendererRecipes;

public class CenterViewVisitProfile extends JPanel {

	User user, visiUser;
	JScrollPane scrollPane;

	JLabel profilePicture, username, recipesText, followingText, followersText, recipes, following, followers,
			recipesTextList;

	JButton followButton;

	ProfileVisitController controller;

	JList<Recipe> uploaded;
	RecipesList uploadedModel;
	RendererRecipes renderer;

	Color bgColor = Color.white;
	Color textColor = new Color(129, 145, 160);
	Color selectedColor = new Color(30, 170, 255);

	Font textFont = new Font("Segoe UI", Font.PLAIN, 20);
	Font numberFont = new Font("Segoe UI", Font.BOLD, 20);

	public CenterViewVisitProfile(User user) {
		super(new GridLayout(1, 1, 100, 100));
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.setBackground(Color.white);
		this.setOpaque(true);

		controller = new ProfileVisitController();

		this.user = user;

		initJlabels();
		initJButtons();
		initJlist();

		this.add(createContent());
	}

	private void initJlabels() {

		profilePicture = new JLabel();
		profilePicture.setIcon(user.getProfilePic());
		profilePicture.setBackground(Color.white);

		username = new JLabel(user.getUsername());
		username.setFont(textFont);
		username.setBackground(Color.white);

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

		recipesTextList = new JLabel("Recetas creadas");
		recipesTextList.setFont(textFont);
		recipesTextList.setForeground(selectedColor);
		recipesTextList.setHorizontalAlignment(JLabel.CENTER);
		recipesTextList.setBackground(bgColor);
		recipesTextList.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, selectedColor));

	}

	private void initJButtons() {
		followButton = new JButton("Seguir");
		followButton.setPreferredSize(new Dimension(150, 35));
		followButton.setBackground(new Color(28, 162, 243));
		followButton.setForeground(Color.white);
		followButton.setFont(textFont);
		followButton.setBorder(BorderFactory.createEmptyBorder());
		followButton.setFocusable(false);
		followButton.setUI(new UIRoundButton(followButton, 30, new Color(28, 162, 243), Color.white,
				new Font("Roboto", Font.PLAIN, 15), controller, ProfileVisitControllerAC.FOLLOW));
	}

	private void initJlist() {
		uploadedModel = new RecipesList();
		uploadedModel.setList(user.getPublished());

		renderer = new RendererRecipes();

		uploaded = new JList<>();
		uploaded.setModel(uploadedModel);
		uploaded.setCellRenderer(renderer);

	}

	private JPanel createContent() {
		JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
		contentPanel.setBackground(Color.white);
		contentPanel.setOpaque(true);

		contentPanel.add(createNorthPanel(), BorderLayout.NORTH);
		contentPanel.add(createCenterPanel(), BorderLayout.CENTER);

		return contentPanel;
	}

	private JPanel createNorthPanel() {

		JPanel northPanel = new JPanel(new BorderLayout(20, 20));
		northPanel.setBackground(Color.white);
		northPanel.setOpaque(true);

		JPanel imagePanel = new JPanel(new GridLayout(1, 1, 10, 10));
		imagePanel.setBackground(Color.white);
		imagePanel.setOpaque(true);
		imagePanel.add(profilePicture);

		JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		infoPanel.setBackground(Color.white);
		infoPanel.setOpaque(true);

		infoPanel.add(createTopPanel());
		infoPanel.add(createInfoPanel());

		northPanel.add(imagePanel, BorderLayout.WEST);
		northPanel.add(infoPanel, BorderLayout.CENTER);

		return northPanel;
	}

	private JPanel createTopPanel() {
		JPanel topPanel = new JPanel(new BorderLayout(20, 20));
		topPanel.setBackground(Color.white);
		topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		buttonPanel.setBackground(Color.white);
		buttonPanel.add(followButton);

		topPanel.add(username, BorderLayout.WEST);
		topPanel.add(buttonPanel, BorderLayout.EAST);

		return topPanel;
	}

	private JPanel createInfoPanel() {
		JPanel infoPanel = new JPanel(new GridLayout());
		infoPanel.setBackground(bgColor);
		infoPanel.setPreferredSize(new Dimension(100, 100));

		infoPanel.add(createRecipesPanel());
		infoPanel.add(createFollowingPanel());
		infoPanel.add(createFollowerPanel());

		return infoPanel;
	}

	public JPanel createRecipesPanel() {
		JPanel recipePanel = new JPanel(new FlowLayout());
		recipePanel.setBackground(bgColor);

		recipePanel.add(recipes);
		recipePanel.add(recipesText);

		return recipePanel;
	}

	public JPanel createFollowingPanel() {
		JPanel followingPanel = new JPanel(new FlowLayout());
		followingPanel.setBackground(bgColor);

		followingPanel.add(following);
		followingPanel.add(followingText);

		return followingPanel;
	}

	public JPanel createFollowerPanel() {
		JPanel followerPanel = new JPanel(new FlowLayout());
		followerPanel.setBackground(bgColor);

		followerPanel.add(followers);
		followerPanel.add(followersText);

		return followerPanel;
	}

	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel(new BorderLayout());

		centerPanel.add(createRecipeTextPanel(), BorderLayout.NORTH);
		centerPanel.add(createSlidePannel(), BorderLayout.CENTER);

		return centerPanel;
	}

	private JPanel createRecipeTextPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 1, 10, 10));
		buttonPanel.setBackground(Color.white);
		buttonPanel.add(recipesTextList);

		return buttonPanel;
	}

	private JScrollPane createSlidePannel() {

		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(Color.white);
		scrollPane.setOpaque(true);

		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setViewportView(uploaded);

		return scrollPane;
	}

	public JPanel getPanel() {
		return this;
	}

	public void setVisitUser(User visitUser) {
		this.visiUser = visitUser;

		/*
		 *el user. ... hay que cambiarlo por visitUser cuando tengamos las peticiones a la base de datos
		 */
		initJlist();
		profilePicture.setIcon(user.getProfilePic());
		username = new JLabel(user.getUsername());
		recipes = new JLabel(String.valueOf(user.getPublishedNumber()));
		following = new JLabel(String.valueOf(user.getFollowedNumber()));
		followers = new JLabel(String.valueOf(user.getFollowersNumber()));

		this.revalidate();
		this.repaint();

		/*
		 * CAMBIA EL USUARIO QUE TIENE QUE MOSTRAR Y ACTUALIZA TODOS LOS DATOS COMO EN
		 * EL RECIPEVIEW Y HACES REPAINT Y REVALIDATE
		 */
	}
}
