package eus.healthit.bchef.core.app.ui.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;
import javax.swing.SwingConstants;

import eus.healthit.bchef.core.app.controllers.DoubleClickListener;
import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;
import eus.healthit.bchef.core.app.controllers.interfaces.IClickable;
import eus.healthit.bchef.core.app.controllers.profile.view.ProfileViewController;
import eus.healthit.bchef.core.app.controllers.profile.view.ProfileViewControllerAC;
import eus.healthit.bchef.core.app.ui.components.CustomScrollbarUI;
import eus.healthit.bchef.core.app.ui.recipes.RecipesList;
import eus.healthit.bchef.core.app.ui.recipes.RendererRecipes;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.User;

public class CenterViewProfile extends JPanel implements IClickable {

	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");

	CenterViewController centerController;

	User user;
	JScrollPane scrollPane;

	JLabel profilePicture, username, recipesText, followingText, followersText, recipes, following, followers;

	JButton uploadedButton, savedButton;

	ProfileViewController controller;

	JList<Recipe> saved, uploaded;
	RecipesList savedModel, uploadedModel;
	RendererRecipes renderer;

//	Color bgColor = new Color(244, 249, 255);
	Color bgColor = Color.white;

	Color textColor = new Color(129, 145, 160);
	Color selectedColor = new Color(30, 170, 255);

	Font textFont = new Font("Segoe UI", Font.PLAIN, 20);
	Font numberFont = new Font("Segoe UI", Font.BOLD, 20);

	public CenterViewProfile(User user, CenterViewController centerController) {
		super(new GridLayout(1, 1, 100, 100));
		this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		this.setBackground(Color.white);
		this.setOpaque(true);

		this.user = user;
		this.centerController = centerController;

		controller = new ProfileViewController(this);

		initJlabels();
		initJButtons();
		initJlist();

		this.add(createContent());
	}

	private void initJlabels() {

		profilePicture = new JLabel();
		profilePicture.setIcon(new ImageIcon(user.getProfilePic().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

		username = new JLabel(user.getUsername());
		username.setFont(new Font("Segoe UI", Font.PLAIN, 35));

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

	private void initJButtons() {
		uploadedButton = new JButton(rb.getString("my_recipes_text"));
		uploadedButton.setFont(textFont);
		uploadedButton.setBackground(bgColor);
		uploadedButton.setForeground(selectedColor);
		uploadedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, selectedColor));
		uploadedButton.setActionCommand(ProfileViewControllerAC.UPLOADED);
		uploadedButton.addActionListener(controller);
		uploadedButton.setFocusable(false);

		savedButton = new JButton(rb.getString("saved_recipes_text"));
		savedButton.setFont(textFont);
		savedButton.setBackground(bgColor);
		savedButton.setBorder(BorderFactory.createEmptyBorder());
		savedButton.setActionCommand(ProfileViewControllerAC.SAVED);
		savedButton.addActionListener(controller);
		savedButton.setFocusable(false);

	}

	private void initJlist() {
		DoubleClickListener clickListener = new DoubleClickListener(this);

		uploadedModel = new RecipesList();
		uploadedModel.setList(user.getPublished());

		savedModel = new RecipesList();
		savedModel.setList(user.getSaved());

		renderer = new RendererRecipes();

		saved = new JList<>();
		saved.setModel(savedModel);
		saved.setCellRenderer(renderer);
		saved.addMouseListener(clickListener);

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

		centerPanel.add(createButtonPannel(), BorderLayout.NORTH);
		centerPanel.add(createSlidePannel(), BorderLayout.CENTER);

		return centerPanel;
	}

	private JPanel createButtonPannel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		buttonPanel.setBackground(bgColor);

		buttonPanel.add(uploadedButton);
		buttonPanel.add(savedButton);

		return buttonPanel;
	}

	private JScrollPane createSlidePannel() {
		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBackground(bgColor);
		scrollPane.setOpaque(false);

		scrollPane.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		scrollPane.setViewportView(uploaded);

		return scrollPane;
	}

	public void changeListView(String selection) {
		switch (selection) {
		case ProfileViewControllerAC.UPLOADED:
			uploadedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, selectedColor));
			savedButton.setBorder(BorderFactory.createEmptyBorder());

			uploadedButton.setForeground(selectedColor);
			savedButton.setForeground(Color.black);

			scrollPane.setViewportView(uploaded);
			break;

		case ProfileViewControllerAC.SAVED:
			uploadedButton.setBorder(BorderFactory.createEmptyBorder());
			savedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, selectedColor));

			uploadedButton.setForeground(Color.black);
			savedButton.setForeground(selectedColor);

			scrollPane.setViewportView(saved);
			break;
		}
	}

	public void openSelectedRecipe() {
		JViewport viewport = scrollPane.getViewport();
		JList<Recipe> tmp = (JList<Recipe>) viewport.getView();

		try {
			centerController.setRecipeView(tmp.getSelectedValue());
		} catch (Exception e) {
		}
	}

	public JPanel getPanel() {
		return this;
	}

	public void updateView() {
		initJlist();

		following.setText(String.valueOf(user.getFollowedNumber()));
		followers.setText(String.valueOf(user.getFollowersNumber()));
		recipes.setText(String.valueOf(user.getPublishedNumber()));
		profilePicture.setIcon(new ImageIcon(user.getProfilePic().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
		username.setText(user.getUsername());

		changeListView(ProfileViewControllerAC.UPLOADED);
	}

	@Override
	public void clicked() {
		openSelectedRecipe();
	}
}
