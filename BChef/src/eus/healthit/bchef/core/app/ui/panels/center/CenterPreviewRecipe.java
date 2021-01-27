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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import eus.healthit.bchef.core.app.ui.components.CustomScrollbarUI;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.util.TextFormatter;

public class CenterPreviewRecipe extends JPanel {

	private static final long serialVersionUID = 7192275620000066495L;
	Recipe recipe;
	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");

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

	Font textFont = new Font("Segoe UI", Font.PLAIN, 20);
	Color bgColor = Color.white;

	public CenterPreviewRecipe() {
		super(new GridLayout());
		this.setBackground(bgColor);

		initJlabels();
		initJPanels();

		this.add(createContentPanel());
	}

	private void initJlabels() {

		titleLabel = new JLabel();
		titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 50));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);

		authorLabel = new JLabel();
		authorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 23));
		authorLabel.setHorizontalAlignment(JLabel.CENTER);

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

	private Component createContentPanel() {
		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(bgColor);
		scrollPane.setOpaque(true);

		scrollPane.getVerticalScrollBar().setUI(new CustomScrollbarUI());
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
		constraints.insets = new Insets(0, 140, 0, 140);
		constraints.gridx = 0;
		constraints.gridy = 0;

		dataPanel.setBackground(bgColor);
		dataPanel.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));

		dataPanel.add(createTitlePanel(), constraints);
		constraints.gridy = 1;
		dataPanel.add(createIngredientsPanel(), constraints);
		constraints.gridy = 2;
		dataPanel.add(createElaborationPanel(), constraints);

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

		JScrollBar vertical = scrollPane.getVerticalScrollBar();
		vertical.setValue(vertical.getMinimum());
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

				ingrName.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));

				ingrQuantity.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
				ingrQuantity.setHorizontalAlignment(JLabel.RIGHT);

				tmpIngPanel.add(ingrName);
				tmpIngPanel.add(ingrQuantity);

				ingredientPanel.add(tmpIngPanel);
			}
		} catch (NullPointerException e) {
		}
	}

	private void setSteps(Recipe recipe) {
		stepPanel = new JPanel(new GridLayout(recipe.getStepCount(), 1, 20, 20));
		stepPanel.setBackground(bgColor);
		stepPanel.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 75));

		fullStepPanel.add(stepPanel, BorderLayout.CENTER);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(15, 0, 15, 0);
		constraints.gridx = 0;
		constraints.gridy = 0;

		int i = 1;
		try {
			for (RecipeStep step : recipe.getSteps()) {
				JPanel tmpStepPanel = new JPanel(new GridBagLayout());
				tmpStepPanel.setBackground(bgColor);

				JTextPane stepText = new JTextPane();
				stepText.setText(TextFormatter.format(step.getText(), 40));
				stepText.setForeground(Color.DARK_GRAY);
				stepText.setAlignmentX(JTextPane.CENTER_ALIGNMENT);
				stepText.setFont(new Font("Segoe UI", Font.PLAIN, 18));
				stepText.setBorder(BorderFactory.createEmptyBorder());
				stepText.setEditable(false);
				tmpStepPanel.add(stepText);

				constraints.gridx = i;
				stepPanel.add(tmpStepPanel, constraints);
				i++;
			}
		} catch (NullPointerException e) {

		}
	}

}
