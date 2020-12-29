package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;

public class CenterViewCreateRecipe extends JPanel {

	Font textFont = new Font("Gill Sans MT", Font.PLAIN, 20);
	User user;
	
	JList<Ingredient> ingredients;
	JList<RecipeStep> steps;

	public CenterViewCreateRecipe(User user) {
		super(new GridLayout(1, 1, 100, 100));
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.setBackground(Color.white);
		this.setOpaque(true);

		this.user = user;

		this.add(createContent());

	}

	private Component createContent() {
		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBackground(Color.white);

		contentPanel.add(createCenterPanel(), BorderLayout.CENTER);
		contentPanel.add(createWestPanel(), BorderLayout.WEST);

		return contentPanel;
	}

	private Component createCenterPanel() {
		JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
		centerPanel.setBackground(Color.white);
		return centerPanel;
	}

	private Component createWestPanel() {
		JPanel westPanel = new JPanel(new BorderLayout(10, 10));
		westPanel.setBackground(Color.white);

		JButton addButton = new JButton("Añadir imagen");
		addButton.setBackground(Color.white);
		addButton.setForeground(Color.DARK_GRAY);
		addButton.setFocusable(false);
//		addButton.setBorder(BorderFactory.createEmptyBorder());

		westPanel.add(createIngredientPanel(), BorderLayout.CENTER);
		westPanel.add(addButton, BorderLayout.NORTH);
		return westPanel;
	}

	private Component createIngredientPanel() {
		JPanel ingredientPanel = new JPanel(new BorderLayout(5, 5));
		ingredientPanel.setBackground(Color.white);


		ingredientPanel.add(createIngredientNorthPanel(), BorderLayout.NORTH);
		ingredientPanel.add(createIngredientScroll(), BorderLayout.CENTER);
		return ingredientPanel;
	}

	private Component createIngredientNorthPanel() {
		JPanel northPanel = new JPanel(new GridLayout(5, 1, 5, 5));
		northPanel.setBackground(Color.white);
		
		/*
		 * AQUI VAN EL TITULO INGREDIENTES LOS DOS JTEXTFIELDS Y LOS DOS BOTONES
		 */
		
		return northPanel;
	}

	private Component createIngredientScroll() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(Color.white);
		scrollPane.setOpaque(true);

		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		ingredients = new JList<>();
//		ingredients = new RecipesList();
//		ingredients.setModel(ingredientModel);
//		ingredients.setCellRenderer(renderer);

		scrollPane.setViewportView(ingredients);

		return scrollPane;		
	}

}
