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
import javax.swing.JViewport;

import eus.healthit.bchef.core.controllers.interfaces.IClickable;
import eus.healthit.bchef.core.controllers.view.CenterViewController;
import eus.healthit.bchef.core.controllers.view.DoubleClickListener;
import eus.healthit.bchef.core.controllers.view.ProfileController;
import eus.healthit.bchef.core.controllers.view.ProfileControllerAC;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.borders.RoundedBorder;
import eus.healthit.bchef.core.view.recipes.RecipesList;
import eus.healthit.bchef.core.view.recipes.RendererRecipes;

public class CenterViewProfile extends JPanel implements IClickable {

	/*
	 * METELE EL SELECTIONLISTENER A LOS DOS JLIST Y HAZ QUE SE VAYA A LA MISMA
	 * VISTA QUE TIENES QUE CREAR DE UNA RECETA EN GRANDE
	 */

	Font textFont = new Font("Segoe UI", Font.PLAIN, 20);
	Font numberFont = new Font("Segoe UI", Font.BOLD, 20);

	CenterViewController centerController;

	User user;
	JScrollPane scrollPane;

	JLabel profilePicture, username, recipesText, followingText, followersText, recipes, following, followers;

	JButton uploadedButton, savedButton;

	ProfileController controller;

	JList<Recipe> saved, uploaded;
	RecipesList savedModel, uploadedModel;
	RendererRecipes renderer;

	Color bgColor = Color.white;
	Color textColor = new Color(129, 145, 160);

	public CenterViewProfile(User user, CenterViewController centerController) {
		super(new GridLayout(1, 1, 100, 100));
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.setBackground(Color.white);
		this.setOpaque(true);

		this.user = user;
		this.centerController = centerController;

		controller = new ProfileController(this);

		initJlabels();
		initJButtons();
		initJlist();

		this.add(createContent());
	}

	private void initJlabels() {

		profilePicture = new JLabel();
		profilePicture.setIcon(user.getProfilePic());

		username = new JLabel(user.getUsername());
		username.setFont(textFont);

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

	private void initJButtons() {
		uploadedButton = new JButton("Mis Recetas");
		uploadedButton.setFont(textFont);
		uploadedButton.setBackground(bgColor);
		uploadedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		uploadedButton.setActionCommand(ProfileControllerAC.UPLOADED);
		uploadedButton.addActionListener(controller);
		uploadedButton.setFocusable(false);

		savedButton = new JButton("Recetas Guardadas");
		savedButton.setFont(textFont);
		savedButton.setBackground(bgColor);
		savedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		savedButton.setActionCommand(ProfileControllerAC.SAVED);
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

	private JPanel createContent() {
		JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
		contentPanel.setBackground(bgColor);
		contentPanel.setOpaque(true);

		contentPanel.add(createNorthPanel(), BorderLayout.NORTH);
		contentPanel.add(createCenterPanel(), BorderLayout.CENTER);

		return contentPanel;
	}

	private JPanel createNorthPanel() {

		JPanel northPanel = new JPanel(new BorderLayout(20, 20));
		northPanel.setBackground(bgColor);
		northPanel.setOpaque(true);

		JPanel imagePanel = new JPanel(new GridLayout(1, 1, 10, 10));
		imagePanel.setBackground(bgColor);
		imagePanel.setOpaque(true);
		imagePanel.add(profilePicture);

		JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		infoPanel.setBackground(bgColor);
		infoPanel.setOpaque(true);

		infoPanel.add(username);
		infoPanel.add(createInfoPanel());

		northPanel.add(imagePanel, BorderLayout.WEST);
		northPanel.add(infoPanel, BorderLayout.CENTER);

		return northPanel;
	}

	private JPanel createInfoPanel() {
		JPanel infoPanel = new JPanel(new GridLayout());
//		infoPanel.setBorder(new RoundedBorder(15, new Color(148, 204, 255)));
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
		scrollPane.setBackground(Color.white);
		scrollPane.setOpaque(true);

		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setViewportView(uploaded);

		return scrollPane;
	}

	public void changeListView(String selection) {
		switch (selection) {
		case ProfileControllerAC.UPLOADED:
			scrollPane.setViewportView(uploaded);
			break;

		case ProfileControllerAC.SAVED:
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
			System.out.println("Exception en openSelectedRecipe en CenterViewProfile");
		}
	}

	public JPanel getPanel() {
		return this;
	}

	@Override
	public void clicked() {
		/*
		 * Comprobar de que jlist ha sido. Se me ocurre mirar el boton que está
		 * presionado o que cada vez que presiones un boton cambiar una referencia a un
		 * JList y pillar de ahi el selectedValue
		 */
		openSelectedRecipe();
	}
}
