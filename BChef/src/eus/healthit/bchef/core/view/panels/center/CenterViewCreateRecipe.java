package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;

public class CenterViewCreateRecipe extends JPanel {

	Font textFont = new Font("Gill Sans MT", Font.PLAIN, 20);
	User user;

	Color greenButtonColor = new Color(51, 212, 51);
	Color redButtonColor = new Color(240, 71, 55);

	JTextField title, description;
	JTextField ingredient, quantity;
	JTextField instruction;

	JComboBox<RecipeStepActions> actions;
	JComboBox<Integer> values, time;

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

		centerPanel.add(createTitlePanel(), BorderLayout.NORTH);
		centerPanel.add(createStepPanel(), BorderLayout.CENTER);
		return centerPanel;
	}

	private Component createTitlePanel() {
		JPanel titlePanel = new JPanel(new GridLayout(2, 1, 15, 15));
		titlePanel.setBackground(Color.white);

		title = new JTextField();
		title.setFont(textFont);

		description = new JTextField();
		description.setFont(textFont);

		titlePanel.add(title);
		titlePanel.add(description);

		return titlePanel;
	}

	private Component createStepPanel() {
		JPanel stepsPanel = new JPanel(new GridLayout(2, 1, 3, 3));
		stepsPanel.setBackground(Color.white);

		stepsPanel.add(createInfoPanel());
		stepsPanel.add(createAddedPanel());

		return stepsPanel;
	}

	private Component createInfoPanel() {
		JPanel infoPanel = new JPanel(new BorderLayout(10, 10));
		infoPanel.setBackground(Color.white);

		JLabel titleLabel = new JLabel("Pasos");
		titleLabel.setFont(textFont);
		titleLabel.setHorizontalAlignment(JLabel.CENTER);

		instruction = new JTextField();
		instruction.setFont(textFont);

		infoPanel.add(createComboBoxPanel(), BorderLayout.EAST);
		infoPanel.add(instruction, BorderLayout.CENTER);
		infoPanel.add(titleLabel, BorderLayout.NORTH);

		return infoPanel;
	}

	private Component createComboBoxPanel() {
		JPanel comboPanel = new JPanel(new GridLayout(3, 1, 5, 5));
		comboPanel.setBackground(Color.white);

		actions = new JComboBox<>();
		values = new JComboBox<>();
		time = new JComboBox<>();

		comboPanel.add(actions);
		comboPanel.add(values);
		comboPanel.add(time);

		return comboPanel;
	}

	private Component createAddedPanel() {
		JPanel addedPanel = new JPanel(new BorderLayout(5, 5));

		addedPanel.add(createStepButtonPanel(), BorderLayout.NORTH);
		addedPanel.add(createStepsScroll(), BorderLayout.CENTER);

		return addedPanel;
	}

	private Component createStepsScroll() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(Color.white);
		scrollPane.setOpaque(true);

		scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		steps = new JList<>();
//		ingredients = new RecipesList();
//		ingredients.setModel(stepsModel);
//		ingredients.setCellRenderer(renderer);

		scrollPane.setViewportView(steps);

		return scrollPane;
	}

	private Component createStepButtonPanel() {
		JPanel stepButtonPanel = new JPanel(new GridLayout(1, 2, 5, 5));

		JButton addButton = new JButton("Añadir");
		addButton.setBackground(greenButtonColor);
		addButton.setForeground(Color.white);
		addButton.setFont(textFont);
		addButton.setFocusable(false);
//		addButton.setActionCommand(STEPLISTCONTROLERAC.ADD);
//		addButton.addActionListener(STEPLISTCONTROLER);

		JButton removeButton = new JButton("Eliminar");
		removeButton.setBackground(redButtonColor);
		removeButton.setForeground(Color.white);
		removeButton.setFont(textFont);
		removeButton.setFocusable(false);
//		removeButton.setActionCommand(STEPLISTCONTROLERAC.ADD);

		stepButtonPanel.add(addButton);
		stepButtonPanel.add(removeButton);

		return stepButtonPanel;
	}

	private Component createWestPanel() {
		JPanel westPanel = new JPanel(new BorderLayout(10, 10));
		westPanel.setBackground(Color.white);
		westPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 50, Color.white));

		JButton addButton = new JButton("Añadir imagen");
		addButton.setBackground(Color.white);
		addButton.setForeground(Color.DARK_GRAY);
		addButton.setFocusable(false);

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
		JPanel northPanel = new JPanel(new GridLayout(5, 1, 7, 7));
		northPanel.setBackground(Color.white);
		northPanel.setBorder(BorderFactory.createMatteBorder(100, 0, 0, 0, Color.white));

		JLabel titleLabel = new JLabel("Ingredientes");
		titleLabel.setFont(textFont);

		ingredient = new JTextField();
		ingredient.setFont(textFont);

		quantity = new JTextField();
		quantity.setFont(textFont);

		JButton addButton = new JButton("Añadir");
		addButton.setBackground(greenButtonColor);
		addButton.setForeground(Color.white);
		addButton.setFont(textFont);
		addButton.setFocusable(false);
//		addButton.setActionCommand(INGREDIENTLISTCONTROLERAC.ADD);
//		addButton.addActionListener(INGREDIENTLISTCONTROLER);

		JButton removeButton = new JButton("Eliminar");
		removeButton.setBackground(redButtonColor);
		removeButton.setForeground(Color.white);
		removeButton.setFont(textFont);
		removeButton.setFocusable(false);
//		removeButton.setActionCommand(INGREDIENTLISTCONTROLERAC.ADD);
//		removeButton.addActionListener(INGREDIENTLISTCONTROLER);

		northPanel.add(titleLabel);
		northPanel.add(ingredient);
		northPanel.add(quantity);
		northPanel.add(addButton);
		northPanel.add(removeButton);

		return northPanel;
	}

	private Component createIngredientScroll() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(Color.white);
		scrollPane.setOpaque(true);

		scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		ingredients = new JList<>();
//		ingredients = new RecipesList();
//		ingredients.setModel(ingredientModel);
//		ingredients.setCellRenderer(renderer);

		scrollPane.setViewportView(ingredients);

		return scrollPane;
	}

}
