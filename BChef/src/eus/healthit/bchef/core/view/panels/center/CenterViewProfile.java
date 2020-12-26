package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eus.healthit.bchef.core.controllers.view.ProfileController;
import eus.healthit.bchef.core.controllers.view.ProfileControllerAC;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.recipes.RecipesList;
import eus.healthit.bchef.core.view.recipes.RendererRecipes;

public class CenterViewProfile extends JPanel {
	
	/*
	 * METELE EL SELECTIONLISTENER A LOS DOS JLIST Y HAZ QUE SE VAYA A LA MISMA VISTA QUE TIENES QUE 
	 * CREAR DE UNA RECETA EN GRANDE
	 */

	User user;
	JScrollPane scrollPane;

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

		this.add(createContent());
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
		Font textFont = new Font("Gill Sans MT", Font.PLAIN, 20);

		JPanel northPanel = new JPanel(new BorderLayout(20, 20));
		northPanel.setBackground(Color.white);
		northPanel.setOpaque(true);

		JPanel imagePanel = new JPanel(new GridLayout(1, 1, 10, 10));
		JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));

		imagePanel.setBackground(Color.white);
		imagePanel.setOpaque(true);

		infoPanel.setBackground(Color.white);
		infoPanel.setOpaque(true);

		// -------------------------------------------------------------
		JLabel profilePicture = new JLabel();
		profilePicture.setIcon(user.getProfilePic());
		imagePanel.add(profilePicture);
		// -------------------------------------------------------------

		JLabel username = new JLabel(user.getUsername());
		username.setFont(textFont);
		infoPanel.add(username);

		JPanel followerPanel = new JPanel(new GridLayout(2, 3));
		followerPanel.setBackground(Color.white);
		followerPanel.setOpaque(true);

		JLabel recipesText = new JLabel("Recipes");
		JLabel followingText = new JLabel("Following");
		JLabel followersText = new JLabel("Followers");
		JLabel recipes = new JLabel(String.valueOf(user.getPublishedNumber()));
		JLabel following = new JLabel(String.valueOf(user.getFollowedNumber()));
		JLabel followers = new JLabel(String.valueOf(user.getFollowersNumber()));
		
		// -------------------------------------------------------------

		recipesText.setFont(textFont);
		followingText.setFont(textFont);
		followersText.setFont(textFont);
		recipes.setFont(textFont);
		following.setFont(textFont);
		followers.setFont(textFont);

		// -------------------------------------------------------------
		
		recipesText.setHorizontalAlignment(JLabel.CENTER);
		followingText.setHorizontalAlignment(JLabel.CENTER);
		followersText.setHorizontalAlignment(JLabel.CENTER);
		recipes.setHorizontalAlignment(JLabel.CENTER);
		following.setHorizontalAlignment(JLabel.CENTER);
		followers.setHorizontalAlignment(JLabel.CENTER);

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

		Font textFont = new Font("Gill Sans MT", Font.PLAIN, 20);
		
		JButton uploadedButton = new JButton("Mis Recetas");
		JButton savedButton = new JButton("Recetas Guardadas");
		
		uploadedButton.setFont(textFont);
		savedButton.setFont(textFont);

		uploadedButton.setBackground(Color.white);
		savedButton.setBackground(Color.white);
		
		uploadedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));
		savedButton.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.DARK_GRAY));

		uploadedButton.setActionCommand(ProfileControllerAC.UPLOADED);
		savedButton.setActionCommand(ProfileControllerAC.SAVED);

		uploadedButton.addActionListener(controller);
		savedButton.addActionListener(controller);

		uploadedButton.setFocusable(false);
		savedButton.setFocusable(false);

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

		renderer = new RendererRecipes();
		uploaded = new JList<>();
		saved = new JList<>();
		uploadedModel = new RecipesList();
		savedModel = new RecipesList();
		uploadedModel.setList(user.getPublished());
		savedModel.setList(user.getSaved());

		uploaded.setModel(uploadedModel);
		saved.setModel(savedModel);

		uploaded.setCellRenderer(renderer);
		saved.setCellRenderer(renderer);

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

	public JPanel getPanel() {
		return this;
	}

}
