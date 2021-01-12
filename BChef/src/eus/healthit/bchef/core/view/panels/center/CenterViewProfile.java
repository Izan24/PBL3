package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
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
import eus.healthit.bchef.core.controllers.view.ProfileController;
import eus.healthit.bchef.core.controllers.view.ProfileControllerAC;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.recipes.RecipesList;
import eus.healthit.bchef.core.view.recipes.RendererRecipes;

public class CenterViewProfile extends JPanel implements IClickable {

	/*
	 * METELE EL SELECTIONLISTENER A LOS DOS JLIST Y HAZ QUE SE VAYA A LA MISMA
	 * VISTA QUE TIENES QUE CREAR DE UNA RECETA EN GRANDE
	 */

	Font textFont = new Font("Gill Sans MT", Font.PLAIN, 20);

	User user;
	JScrollPane scrollPane;

	JLabel profilePicture, username, recipesText, followingText, followersText, recipes, following, followers;

	JButton uploadedButton, savedButton;

	ProfileController controller;

	JList<Recipe> saved, uploaded;
	RecipesList savedModel, uploadedModel;
	RendererRecipes renderer;

	public CenterViewProfile(User user) {
		super(new GridLayout(1, 1, 100, 100));
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.setBackground(Color.white);
		this.setOpaque(true);

		controller = new ProfileController(this);

		this.user = user;

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
		recipesText.setHorizontalAlignment(JLabel.CENTER);

		followersText = new JLabel("Followers");
		followersText.setFont(textFont);
		followersText.setHorizontalAlignment(JLabel.CENTER);

		followingText = new JLabel("Following");
		followingText.setFont(textFont);
		followingText.setHorizontalAlignment(JLabel.CENTER);

		recipes = new JLabel(String.valueOf(user.getPublishedNumber()));
		recipes.setFont(textFont);
		recipes.setHorizontalAlignment(JLabel.CENTER);

		following = new JLabel(String.valueOf(user.getFollowedNumber()));
		following.setFont(textFont);
		following.setHorizontalAlignment(JLabel.CENTER);

		followers = new JLabel(String.valueOf(user.getFollowersNumber()));
		followers.setFont(textFont);
		followers.setHorizontalAlignment(JLabel.CENTER);

	}

	private void initJButtons() {
		uploadedButton = new JButton("Mis Recetas");
		uploadedButton.setFont(textFont);
		uploadedButton.setBackground(Color.white);
		uploadedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		uploadedButton.setActionCommand(ProfileControllerAC.UPLOADED);
		uploadedButton.addActionListener(controller);
		uploadedButton.setFocusable(false);

		savedButton = new JButton("Recetas Guardadas");
		savedButton.setFont(textFont);
		savedButton.setBackground(Color.white);
		savedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		savedButton.setActionCommand(ProfileControllerAC.SAVED);
		savedButton.addActionListener(controller);
		savedButton.setFocusable(false);

	}

	private void initJlist() {

		uploadedModel = new RecipesList();
		uploadedModel.setList(user.getPublished());

		savedModel = new RecipesList();
		savedModel.setList(user.getSaved());

		renderer = new RendererRecipes();

		saved = new JList<>();
		saved.setModel(savedModel);
		saved.setCellRenderer(renderer);

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
		infoPanel.add(username);

		JPanel followerPanel = new JPanel(new GridLayout(2, 3));
		followerPanel.setBackground(Color.white);
		followerPanel.setOpaque(true);
		followerPanel.add(recipesText);
		followerPanel.add(followingText);
		followerPanel.add(followersText);
		followerPanel.add(recipes);
		followerPanel.add(following);
		followerPanel.add(followers);

		infoPanel.add(followerPanel);

		northPanel.add(imagePanel, BorderLayout.WEST);
		northPanel.add(infoPanel, BorderLayout.CENTER);

		return northPanel;
	}

	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel(new BorderLayout());

		centerPanel.add(createButtonPannel(), BorderLayout.NORTH);
		centerPanel.add(createSlidePannel(), BorderLayout.CENTER);

		return centerPanel;
	}

	private JPanel createButtonPannel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		buttonPanel.setBackground(Color.white);

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

		tmp.getSelectedValue();

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
	}
}
