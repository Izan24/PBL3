package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import eus.healthit.bchef.core.controllers.interfaces.IClickable;
import eus.healthit.bchef.core.controllers.view.CenterViewController;
import eus.healthit.bchef.core.controllers.view.DoubleClickListener;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;

public class CenterViewRecipe extends JPanel implements IClickable {

	DoubleClickListener listener;

	CenterViewController centerController;
	
	Recipe recipe;
	
	JScrollPane scrollPane;

	JLabel titleLabel;
	JLabel authorLabel;
	JPanel starPanel;
	JLabel imageLabel;

	JPanel fullIngredientPanel;
	JPanel ingredientPanel;

	JPanel fullStepPanel;
	JPanel stepPanel;

	Font textFont = new Font("Gill Sans MT", Font.PLAIN, 20);

	public CenterViewRecipe(CenterViewController centerController) {
		super(new GridLayout());
		this.setBackground(Color.white);
		this.centerController = centerController;

		listener = new DoubleClickListener(this);

		this.add(createContentPanel());
	}

	private Component createContentPanel() {
		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(Color.white);
		scrollPane.setOpaque(true);

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

		dataPanel.setBackground(Color.white);
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
		principalPanel.setBackground(Color.white);

		JPanel northPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		northPanel.setBackground(Color.white);

		titleLabel = new JLabel();
		titleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 50));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);

		JPanel authorratingPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		authorratingPanel.setBackground(Color.white);
		authorLabel = new JLabel();
		authorLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 23));
		authorLabel.setHorizontalAlignment(JLabel.CENTER);
		authorLabel.addMouseListener(listener);

		starPanel = new JPanel();
		starPanel.setBackground(Color.white);

		authorratingPanel.add(authorLabel);
		authorratingPanel.add(starPanel);

		imageLabel = new JLabel();
		imageLabel.setHorizontalAlignment(JLabel.CENTER);

		northPanel.add(titleLabel);
		northPanel.add(authorratingPanel);

		principalPanel.add(northPanel, BorderLayout.NORTH);
		principalPanel.add(imageLabel, BorderLayout.CENTER);

		return principalPanel;
	}

	private Component createIngredientsPanel() {
		fullIngredientPanel = new JPanel(new BorderLayout(10, 10));
		fullIngredientPanel.setBackground(Color.white);
		fullIngredientPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 50, 0));

		JLabel ingredientTitleLabel = new JLabel("Ingredientes");
		ingredientTitleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 40));
		ingredientTitleLabel.setForeground(Color.darkGray);
		ingredientTitleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		ingredientTitleLabel.setHorizontalAlignment(JLabel.CENTER);

		ingredientPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		ingredientPanel.setBackground(Color.white);

		fullIngredientPanel.add(ingredientTitleLabel, BorderLayout.NORTH);
		fullIngredientPanel.add(ingredientPanel, BorderLayout.CENTER);

		return fullIngredientPanel;
	}

	private Component createElaborationPanel() {
		fullStepPanel = new JPanel(new BorderLayout(10, 10));
		fullStepPanel.setBackground(Color.white);

		JLabel elaborationTitleLabel = new JLabel("Elaboración");
		elaborationTitleLabel.setFont(new Font("Gill Sans MT", Font.PLAIN, 40));
		elaborationTitleLabel.setForeground(Color.darkGray);
		elaborationTitleLabel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.lightGray));
		elaborationTitleLabel.setHorizontalAlignment(JLabel.CENTER);

		stepPanel = new JPanel(new GridLayout(2, 1, 0, 0));
		stepPanel.setBackground(Color.white);

		fullStepPanel.add(elaborationTitleLabel, BorderLayout.NORTH);
		fullStepPanel.add(stepPanel, BorderLayout.CENTER);

		return fullStepPanel;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
		updateView(recipe);
		this.repaint();
	}

	private void updateView(Recipe recipe) {
		titleLabel.setText(recipe.getName());
		authorLabel.setText("Autor: " + recipe.getAuthor());
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
			imageLabel.setIcon(new ImageIcon(ImageIO.read(new URL(recipe.getImageURL()))));
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
		ingredientPanel.setBackground(Color.white);
		ingredientPanel.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 75));

		fullIngredientPanel.add(ingredientPanel, BorderLayout.CENTER);

		try {
			for (Ingredient ingr : recipe.getIngredients()) {
				JPanel tmpIngPanel = new JPanel(new GridLayout(1, 2, 0, 0));
				tmpIngPanel.setBackground(Color.white);

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
			System.out.println(e.getMessage());
		}
	}

	private void setSteps(Recipe recipe) {

		stepPanel = new JPanel(new GridLayout(recipe.getStepNumber(), 1, 20, 20));
		stepPanel.setBackground(Color.white);
		stepPanel.setBorder(BorderFactory.createEmptyBorder(5, 100, 5, 75));

		fullStepPanel.add(stepPanel, BorderLayout.CENTER);

		try {
			for (RecipeStep step : recipe.getSteps()) {
				JPanel tmpStepPanel = new JPanel(new GridLayout(1, 1, 0, 0));
				tmpStepPanel.setBackground(Color.white);

				JLabel stepText = new JLabel(step.getText());

				stepText.setFont(new Font("Gill Sans MT", Font.PLAIN, 18));
				stepText.setHorizontalAlignment(JLabel.CENTER);

				stepPanel.add(stepText);
			}
		} catch (NullPointerException e) {

		}
	}

	@Override
	public void clicked() {
		centerController.setVisitProfileView(recipe.getFullAuthor());
		System.out.println(
				"Tiene sque crear el profile view y llamar a un metodo del controlador que cambie a un perfil en concreto y "
						+ "\n"
						+ "le pases el perfil que ha seleccionado, en este caso seia buscar en la database el nombre del author");
	}
}
