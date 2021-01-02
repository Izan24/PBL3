package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.Recipe;

public class CenterViewRecipe extends JPanel {

	JLabel titleLabel;
	JLabel authorLabel;
	JPanel starPanel;
	JLabel imageLabel;
	JPanel ingredientPanel;

	Font textFont = new Font("Gill Sans MT", Font.PLAIN, 20);

	Recipe recipe;

	public CenterViewRecipe() {
		super();
		this.setBackground(Color.white);

		this.add(createContentPanel());
	}

	private Component createContentPanel() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(Color.white);
		scrollPane.setOpaque(true);

		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setViewportView(createRecipeView());

		return scrollPane;
	}

	private Component createRecipeView() {
		JPanel dataPanel = new JPanel(new GridLayout(3, 1, 20, 20));
		dataPanel.setBackground(Color.white);

		dataPanel.add(createTitlePanel());
		dataPanel.add(createIngredientsPanel());
		dataPanel.add(createElaborationPanel());

		return dataPanel;
	}

	private Component createTitlePanel() {
		JPanel principalPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		principalPanel.setBackground(Color.white);

		titleLabel = new JLabel();
		titleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 50));

		JPanel authorratingPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		authorratingPanel.setBackground(Color.white);
		authorLabel = new JLabel();
		authorLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 10));

		starPanel = new JPanel();

		authorratingPanel.add(authorLabel);
		authorratingPanel.add(starPanel);

		imageLabel = new JLabel();

		principalPanel.add(titleLabel);
		principalPanel.add(authorratingPanel);
		principalPanel.add(imageLabel);

		return principalPanel;
	}

	private Component createIngredientsPanel() {
		JPanel fullIngredientPanel = new JPanel(new BorderLayout(10, 10));
		fullIngredientPanel.setBackground(Color.white);

		JLabel ingredientTitleLabel = new JLabel("Ingredientes");
		ingredientTitleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 40));
		ingredientTitleLabel.setForeground(Color.darkGray);
		ingredientTitleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		ingredientTitleLabel.setHorizontalAlignment(JLabel.CENTER);

		ingredientPanel = new JPanel(new GridLayout(1, 2));

		fullIngredientPanel.add(ingredientTitleLabel, BorderLayout.NORTH);
		fullIngredientPanel.add(ingredientPanel, BorderLayout.CENTER);

		return fullIngredientPanel;
	}

	private Component createElaborationPanel() {
		JPanel fullElaborationPanel = new JPanel(new BorderLayout(10, 10));
		fullElaborationPanel.setBackground(Color.white);

		JLabel elaborationTitleLabel = new JLabel("Elaboración");
		elaborationTitleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 40));
		elaborationTitleLabel.setForeground(Color.darkGray);
		elaborationTitleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		elaborationTitleLabel.setHorizontalAlignment(JLabel.CENTER);


		fullElaborationPanel.add(elaborationTitleLabel, BorderLayout.NORTH);
		return fullElaborationPanel;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
		updateView();
		this.repaint();
	}

	private void updateView() {
		titleLabel.setText(recipe.getName());
		authorLabel.setText("Author: " + recipe.getAuthor());
		setRating();
		setIngredients();

	}

	private void setRating() {
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

	private void setIngredients() {
		ingredientPanel.removeAll();
		ingredientPanel.revalidate();

		for (Ingredient ingr : recipe.getIngredients()) {
			JPanel tmpIngPanel = new JPanel(new GridLayout(2, 1, 10, 10));

			JLabel ingrName = new JLabel(ingr.getName());
			JLabel ingrQuantity = new JLabel(ingr.getQuantity());
			ingrName.setFont(textFont);
			ingrQuantity.setFont(textFont);
			ingrQuantity.setHorizontalAlignment(JLabel.RIGHT);

			tmpIngPanel.add(ingrName);
			tmpIngPanel.add(ingrQuantity);

			ingredientPanel.add(tmpIngPanel);
		}
	}

}
