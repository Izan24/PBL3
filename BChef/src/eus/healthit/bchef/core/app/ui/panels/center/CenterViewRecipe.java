package eus.healthit.bchef.core.app.ui.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import eus.healthit.bchef.core.app.controllers.DoubleClickListener;
import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;
import eus.healthit.bchef.core.app.controllers.recipe.view.RecipeViewController;
import eus.healthit.bchef.core.app.controllers.recipe.view.RecipeViewControllerAC;
import eus.healthit.bchef.core.app.ui.borders.RoundedBorder;
import eus.healthit.bchef.core.app.ui.components.CustomScrollbarUI;
import eus.healthit.bchef.core.app.ui.components.UIRoundButton;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.util.TextFormatter;

public class CenterViewRecipe extends JPanel {

	private static final long serialVersionUID = -3365102984794740342L;

	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");

	DoubleClickListener listener;

	CenterViewController centerController;
	RecipeViewController controller;

	Recipe recipe;

	JScrollPane scrollPane;

	JLabel titleLabel;
	JLabel authorLabel;
	JPanel starPanel;
	JLabel imageLabel;

	JLabel ingredientTitleLabel;
	JLabel elaborationTitleLabel;

	JPanel fullIngredientPanel;
	JPanel ingredientPanel;

	JPanel fullStepPanel;
	JPanel stepPanel;

	JButton saveRecipe, startRecipe;
	User user;

	Font textFont = new Font("Segoe UI", Font.PLAIN, 20);
	Color bgColor = Color.white;

	public CenterViewRecipe(CenterViewController centerController, User user) {
		super(new GridLayout());
		this.setBackground(bgColor);
		this.centerController = centerController;
		this.user = user;

		controller = new RecipeViewController(this, user, centerController);
		listener = new DoubleClickListener(controller);

		initJlabels();
		initJPanels();
		initJbuttons();

		this.add(createContentPanel());
	}

	private void initJlabels() {

		titleLabel = new JLabel();
		titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 50));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);

		authorLabel = new JLabel();
		authorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		authorLabel.setHorizontalAlignment(JLabel.CENTER);
		authorLabel.addMouseListener(listener);

		imageLabel = new JLabel();
		imageLabel.setHorizontalAlignment(JLabel.CENTER);

		ingredientTitleLabel = new JLabel(rb.getString("ingredients_text"));
		ingredientTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		ingredientTitleLabel.setForeground(Color.darkGray);
		ingredientTitleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		ingredientTitleLabel.setHorizontalAlignment(JLabel.CENTER);

		elaborationTitleLabel = new JLabel(rb.getString("elaboration_text"));
		elaborationTitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 40));
		elaborationTitleLabel.setForeground(Color.darkGray);
		elaborationTitleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		elaborationTitleLabel.setHorizontalAlignment(JLabel.CENTER);

	}

	private void initJPanels() {
		starPanel = new JPanel();
		starPanel.setBackground(bgColor);
	}

	private void initJbuttons() {
		saveRecipe = new JButton(rb.getString("save_text"));
		saveRecipe.setPreferredSize(new Dimension(150, 40));
		saveRecipe.setBackground(bgColor);
		saveRecipe.setForeground(new Color(28, 162, 243));
		saveRecipe.setFont(textFont);
		saveRecipe.setFocusable(false);
		saveRecipe.setUI(new UIRoundButton(saveRecipe, 30, bgColor, new Color(234, 246, 254), new Color(210, 236, 252),
				new Color(28, 162, 243), new Font("Segoe UI", Font.BOLD, 15), controller, RecipeViewControllerAC.SAVE,
				"Guardar"));
		saveRecipe.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
				BorderFactory.createEmptyBorder(60, 40, 60, 40)));

		startRecipe = new JButton(rb.getString("start_recipe_text"));
		startRecipe.setPreferredSize(new Dimension(150, 35));
		startRecipe.setBackground(new Color(28, 162, 243));
		startRecipe.setForeground(bgColor);
		startRecipe.setFont(textFont);
		startRecipe.setBorder(BorderFactory.createEmptyBorder());
		startRecipe.setFocusable(false);
		startRecipe.setUI(new UIRoundButton(startRecipe, 30, new Color(28, 162, 243), Color.white,
				new Font("Segoe UI", Font.PLAIN, 15), controller, RecipeViewControllerAC.START));
	}

	private Component createContentPanel() {
		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(bgColor);
		scrollPane.setOpaque(true);
		scrollPane.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getVerticalScrollBar().setUnitIncrement(8);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setViewportView(createRecipeView());

		return scrollPane;
	}

	private Component createRecipeView() {
		JPanel dataPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 80, 0, 80);
		constraints.gridx = 0;
		constraints.gridy = 0;

		dataPanel.setBackground(bgColor);
		dataPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

		dataPanel.add(createTitlePanel(), constraints);
		constraints.gridy = 1;
		dataPanel.add(createIngredientsPanel(), constraints);
		constraints.gridy = 2;
		dataPanel.add(createElaborationPanel(), constraints);
		constraints.gridy = 3;
		dataPanel.add(createButtonPanel(), constraints);

		return dataPanel;
	}

	private Component createTitlePanel() {
		JPanel principalPanel = new JPanel(new BorderLayout(5, 5));
		principalPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
		principalPanel.setBackground(bgColor);

		JPanel northPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		northPanel.setBackground(bgColor);

		JPanel authorratingPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		authorratingPanel.setBackground(bgColor);
		authorratingPanel.add(authorLabel);
		authorratingPanel.add(starPanel);

		northPanel.add(titleLabel);
		northPanel.add(authorratingPanel);

		principalPanel.add(northPanel, BorderLayout.NORTH);
		principalPanel.add(imageLabel, BorderLayout.CENTER);

		return principalPanel;
	}

	private Component createIngredientsPanel() {
		fullIngredientPanel = new JPanel(new BorderLayout(10, 10));
		fullIngredientPanel.setBackground(bgColor);
		fullIngredientPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

		ingredientPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		ingredientPanel.setBackground(bgColor);

		fullIngredientPanel.add(ingredientTitleLabel, BorderLayout.NORTH);
		fullIngredientPanel.add(ingredientPanel, BorderLayout.CENTER);

		return fullIngredientPanel;
	}

	private Component createElaborationPanel() {
		fullStepPanel = new JPanel(new BorderLayout(10, 10));
		fullStepPanel.setBackground(bgColor);

		stepPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		stepPanel.setBackground(bgColor);

		fullStepPanel.add(elaborationTitleLabel, BorderLayout.NORTH);
		fullStepPanel.add(stepPanel, BorderLayout.CENTER);

		return fullStepPanel;
	}

	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel(new BorderLayout());
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		buttonPanel.setBackground(bgColor);

		buttonPanel.add(startRecipe, BorderLayout.EAST);
		buttonPanel.add(saveRecipe, BorderLayout.WEST);

		return buttonPanel;

	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
		updateView(recipe);
		this.repaint();
	}

	public void updateView(Recipe recipe) {
		titleLabel.setText(recipe.getName());
		authorLabel.setText(rb.getString("author_text") + ": " + recipe.getAuthor());
		setImage(recipe);
		setRating(recipe);
		setIngredients(recipe);
		setSteps(recipe);
		setSaveButton(recipe);

		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMinimum());

		this.repaint();
		this.revalidate();
	}

	private void setImage(Recipe recipe) {
		imageLabel.removeAll();
		imageLabel.revalidate();
		try {
			imageLabel.setIcon(new ImageIcon(recipe.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH)));
		} catch (Exception e) {
		}
	}

	private void setRating(Recipe recipe) {
		starPanel.removeAll();
		starPanel.revalidate();

		JLabel halfStar = new JLabel();
		halfStar.setIcon(new ImageIcon("resources/recipeIcons/halfStar.png"));

		int i = recipe.getRating();

		while ((i - 2) >= 0) {
			JLabel fullStar = new JLabel();
			fullStar.setIcon(new ImageIcon("resources/recipeIcons/fullStar.png"));
			starPanel.add(fullStar);
			i = i - 2;
		}
		if (i == 1) {
			starPanel.add(halfStar);
		}

	}

	private void setIngredients(Recipe recipe) {
		ingredientPanel = new JPanel(new GridLayout(recipe.getIngredientNumber(), 1, 20, 20));
		ingredientPanel.setBackground(bgColor);
		ingredientPanel.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 75));

		fullIngredientPanel.add(ingredientPanel, BorderLayout.CENTER);

		try {
			for (Ingredient ingr : recipe.getIngredients()) {
				JPanel tmpIngPanel = new JPanel(new GridLayout(1, 2, 0, 0));
				tmpIngPanel.setBackground(bgColor);

				JLabel ingrName = new JLabel(ingr.getName());
				JLabel ingrQuantity = new JLabel(ingr.getQuantity());

				ingrName.setFont(new Font("Segoe UI", Font.PLAIN, 18));

				ingrQuantity.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				ingrQuantity.setHorizontalAlignment(JLabel.RIGHT);

				tmpIngPanel.add(ingrName);
				tmpIngPanel.add(ingrQuantity);

				ingredientPanel.add(tmpIngPanel);
			}
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
	}

	private void setSteps(Recipe recipe) {

		stepPanel = new JPanel(new GridLayout(recipe.getStepCount(), 1, 20, 20));
		stepPanel.setBackground(Color.white);

		stepPanel.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 75));

		fullStepPanel.add(stepPanel, BorderLayout.CENTER);

		try {
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weighty = 1;
			constraints.weightx = 1;
			constraints.insets = new Insets(15, 0, 15, 0);
			constraints.gridx = 0;
			constraints.gridy = 0;

			int i = 1;
			for (RecipeStep step : recipe.getSteps()) {
				JPanel tmpStepPanel = new JPanel(new GridBagLayout());
				tmpStepPanel.setBackground(bgColor);

				// JLabel stepText = new JLabel(TextFormatter.format(step.getText(), 40));

				JTextPane stepText = new JTextPane();
				stepText.setText(TextFormatter.format(step.getText(), 40));
				stepText.setForeground(Color.DARK_GRAY);
				stepText.setAlignmentX(JTextPane.CENTER_ALIGNMENT);
				stepText.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				stepText.setBorder(BorderFactory.createEmptyBorder());
				// stepText.setFocusable(false);
				stepText.setEditable(false);
				tmpStepPanel.add(stepText);

				constraints.gridx = i;
				stepPanel.add(tmpStepPanel, constraints);
				i++;
			}
		} catch (NullPointerException e) {

		}
	}

	private void setSaveButton(Recipe recipe) {
		if (user.getSaved().contains(recipe)) {
			saveRecipe.setText(rb.getString("unsave_text"));
		} else {
			saveRecipe.setText(rb.getString("save_text"));
		}
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setScrollPaneTop() {
		scrollPane.getHorizontalScrollBar().setValue(0);
	}
}
