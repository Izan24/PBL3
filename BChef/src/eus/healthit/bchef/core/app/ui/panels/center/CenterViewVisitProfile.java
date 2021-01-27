package eus.healthit.bchef.core.app.ui.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eus.healthit.bchef.core.app.controllers.DoubleClickListener;
import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;
import eus.healthit.bchef.core.app.controllers.profile.visit.ProfileVisitController;
import eus.healthit.bchef.core.app.controllers.profile.visit.ProfileVisitControllerAC;
import eus.healthit.bchef.core.app.ui.components.CustomScrollbarUI;
import eus.healthit.bchef.core.app.ui.components.UIRoundButton;
import eus.healthit.bchef.core.app.ui.recipes.RecipesList;
import eus.healthit.bchef.core.app.ui.recipes.RendererRecipes;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.User;

public class CenterViewVisitProfile extends JPanel {

	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");

	Color bgColor = Color.white;
	Color textColor = new Color(129, 145, 160);
	Color selectedColor = new Color(30, 170, 255);

	Font textFont = new Font("Segoe UI", Font.PLAIN, 20);
	Font numberFont = new Font("Segoe UI", Font.BOLD, 20);
	Font buttonFont = new Font("Segoe UI", Font.BOLD, 15);

	ProfileVisitController controller;

	User user, visitUser;
	JScrollPane scrollPane;

	JLabel profilePicture, username, recipesText, followingText, followersText, recipes, following, followers,
			recipesTextList;

	JPanel followPanel;

	JButton followButton, unfollowButton;

	JList<Recipe> uploaded;
	RecipesList uploadedModel;
	RendererRecipes renderer;

	public CenterViewVisitProfile(User user, CenterViewController centerController) {
		super(new GridLayout(1, 1, 100, 100));
		this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		this.setBackground(Color.white);
		this.setOpaque(true);

		this.user = user;

		controller = new ProfileVisitController(this, centerController);

		initButtonPanel();
		initJlabels();
		initJButtons();
		initJlist();

		this.add(createContent());
	}

	private void initButtonPanel() {
		followPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		followPanel.setBackground(bgColor);
	}

	private void initJlabels() {

		profilePicture = new JLabel();
		profilePicture.setBackground(Color.white);

		username = new JLabel();
		username.setFont(new Font("Segoe UI", Font.PLAIN, 35));
		username.setBackground(Color.white);

		recipesText = new JLabel(rb.getString("recipes_text"));
		recipesText.setFont(textFont);
		recipesText.setForeground(textColor);
		recipesText.setHorizontalAlignment(JLabel.CENTER);

		followersText = new JLabel(rb.getString("followers_text"));
		followersText.setFont(textFont);
		followersText.setForeground(textColor);
		followersText.setHorizontalAlignment(JLabel.CENTER);

		followingText = new JLabel(rb.getString("following_text"));
		followingText.setFont(textFont);
		followingText.setForeground(textColor);
		followingText.setHorizontalAlignment(JLabel.CENTER);

		recipes = new JLabel();
		recipes.setFont(numberFont);
		recipes.setForeground(new Color(15, 20, 25));
		recipes.setHorizontalAlignment(JLabel.CENTER);

		following = new JLabel();
		following.setFont(numberFont);
		following.setHorizontalAlignment(JLabel.CENTER);

		followers = new JLabel();
		followers.setFont(numberFont);
		followers.setHorizontalAlignment(JLabel.CENTER);

		recipesTextList = new JLabel(rb.getString("recipes_text"));
		recipesTextList.setFont(textFont);
		recipesTextList.setForeground(selectedColor);
		recipesTextList.setHorizontalAlignment(JLabel.CENTER);
		recipesTextList.setBackground(bgColor);
		recipesTextList.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, selectedColor));

	}

	private void initJButtons() {
		followButton = new JButton(rb.getString("follow_text"));
		followButton.setPreferredSize(new Dimension(150, 35));
		followButton.setBackground(new Color(28, 162, 243));
		followButton.setForeground(Color.white);
		followButton.setFont(textFont);
		followButton.setBorder(BorderFactory.createEmptyBorder());
		followButton.setFocusable(false);
		followButton.setUI(new UIRoundButton(followButton, 30, new Color(28, 162, 243), Color.white, buttonFont,
				controller, ProfileVisitControllerAC.FOLLOW));

		unfollowButton = new JButton(rb.getString("following_text"));
		unfollowButton.setPreferredSize(new Dimension(150, 35));
		unfollowButton.setBackground(new Color(28, 162, 243));
		unfollowButton.setForeground(Color.white);
		unfollowButton.setFont(textFont);
		unfollowButton.setBorder(BorderFactory.createEmptyBorder());
		unfollowButton.setFocusable(false);
		unfollowButton.setUI(new UIRoundButton(unfollowButton, 30, new Color(28, 162, 243), new Color(196, 35, 93),
				Color.white, buttonFont, controller, ProfileVisitControllerAC.UNFOLLOW, rb.getString("following_text"),
				rb.getString("unfollow_text")));
	}

	private void initJlist() {
		DoubleClickListener clickListener = new DoubleClickListener(controller);

		uploadedModel = new RecipesList();

		renderer = new RendererRecipes();

		uploaded = new JList<>();
		uploaded.setModel(uploadedModel);
		uploaded.setCellRenderer(renderer);
		uploaded.addMouseListener(clickListener);

	}

	private JScrollPane createContent() {
		JScrollPane scroll = new JScrollPane();
		scroll.setBackground(bgColor);

		scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setBorder(BorderFactory.createEmptyBorder());
		scroll.setBackground(bgColor);
		scroll.setOpaque(false);

		scroll.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		scroll.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		scroll.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		scroll.setViewportView(createMainPanel());

		return scroll;
	}

	private JPanel createMainPanel() {
		JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
		contentPanel.setBackground(Color.white);
		contentPanel.setOpaque(true);

		contentPanel.add(createNorthPanel(), BorderLayout.NORTH);
		contentPanel.add(createCenterPanel(), BorderLayout.CENTER);

		return contentPanel;
	}

	private JPanel createNorthPanel() {

		JPanel northPanel = new JPanel(new BorderLayout(20, 20));
		northPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
		northPanel.setBackground(Color.white);
		northPanel.setOpaque(true);

		JPanel imagePanel = new JPanel(new GridLayout(1, 1, 0, 0));
		imagePanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 23));
		imagePanel.setBackground(bgColor);
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

	private Box createTopPanel() {
		Box topPanel = Box.createHorizontalBox();
		topPanel.setBackground(Color.white);
		topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.setBackground(bgColor);

		namePanel.add(username);

		topPanel.add(namePanel);
		topPanel.add(Box.createHorizontalGlue());
		topPanel.add(followPanel);

		return topPanel;
	}

	private JPanel createInfoPanel() {
		JPanel infoPanel = new JPanel(new GridLayout());
		infoPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		infoPanel.setBackground(bgColor);

		infoPanel.add(createRecipesPanel());
		infoPanel.add(createFollowingPanel());
		infoPanel.add(createFollowerPanel());

		return infoPanel;
	}

	public JPanel createRecipesPanel() {
		JPanel recipePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		recipePanel.setBackground(bgColor);

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

		scrollPane.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setViewportView(uploaded);

		return scrollPane;
	}

	public JPanel getPanel() {
		return this;
	}

	public void setVisitUser(User visitUser) {
		this.visitUser = visitUser;
		updateView();
	}

	public void updateView() {
		profilePicture
				.setIcon(new ImageIcon(visitUser.getProfilePic().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		username.setText(visitUser.getUsername());
		recipes.setText(String.valueOf(visitUser.getPublishedNumber()));
		following.setText(String.valueOf(visitUser.getFollowedNumber()));
		followers.setText(String.valueOf(visitUser.getFollowersNumber()));
		uploadedModel.setList(visitUser.getPublished());
		scrollPane.repaint();
		scrollPane.revalidate();
		changeButtonStatus();

		this.repaint();
		this.revalidate();
	}

	public void changeButtonStatus() {
		System.out.println();
		if (user.getFollowed().contains(visitUser.getId())) {
			followPanel.removeAll();
			followPanel.add(unfollowButton);
		} else {
			followPanel.removeAll();
			followPanel.add(followButton);
		}

		followPanel.repaint();
		followPanel.revalidate();
		this.repaint();
		this.revalidate();
	}

	public Recipe getSelectedRecipe() {
		return uploaded.getSelectedValue();
	}

	public User getVisitUser() {
		return visitUser;
	}

	public User getUser() {
		return user;
	}
}
