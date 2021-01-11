package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import eus.healthit.bchef.core.controllers.view.DefaultTextAreaController;
import eus.healthit.bchef.core.controllers.view.DefaultTextController;
import eus.healthit.bchef.core.controllers.view.RecipeCreationControler;
import eus.healthit.bchef.core.controllers.view.RecipeCreationControlerAC;
import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.ingredients.IngredientList;
import eus.healthit.bchef.core.view.recipeStep.RecipeStepList;

public class CenterViewCreateRecipe extends JPanel {

	/*
	 * FALTA HACER EL ADDCOMBODATA, AÑADIR LOS STEPS AL JLIST Y EL BOTON DE CREAR
	 * RECETA
	 */

	public final static String TITLE_DEFAULT_TEXT = "Introduzca titulo";
	public final static String DESCRIPTION_DEFAULT_TEXT = "Descripcion de la receta";
	public final static String INGREDIENT_DEFAULT_TEXT = "Ingrediente";
	public final static String QUANTITY_DEFAULT_TEXT = "Cantidad";
	public final static String STEP_DEFAULT_TEXT = "Instruccion del paso";

	Font textFont = new Font("Gill Sans MT", Font.PLAIN, 20);
	User user;

	RecipeCreationControler controler;

	Color greenButtonColor = new Color(51, 212, 51);
	Color redButtonColor = new Color(240, 71, 55);

	JTextField title, description;
	JTextField ingredient, quantity;
	JTextArea instruction;

	JComboBox<RecipeStepActions> actions;
	JComboBox<Integer> values;
	JComboBox<Integer> time;

	JList<Ingredient> ingredients;
	JList<RecipeStep> steps;

	IngredientList ingredientModel;
	RecipeStepList stepModel;

	public CenterViewCreateRecipe(User user) {
		super(new GridLayout(1, 1, 100, 100));
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.setBackground(Color.white);
		this.setOpaque(true);

		this.user = user;

		controler = new RecipeCreationControler(this);

		addComboData();

		this.add(createContent());
	}

	private void addComboData() {
	}

	private Component createContent() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(Color.white);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBackground(Color.white);

		contentPanel.add(createCenterPanel(), BorderLayout.CENTER);
		contentPanel.add(createWestPanel(), BorderLayout.WEST);

		scrollPane.setViewportView(contentPanel);

		return scrollPane;
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
		title.setText(TITLE_DEFAULT_TEXT);
		title.addFocusListener(new DefaultTextController(title, TITLE_DEFAULT_TEXT));

		description = new JTextField();
		description.setFont(textFont);
		description.setText(DESCRIPTION_DEFAULT_TEXT);
		description.addFocusListener(new DefaultTextController(description, DESCRIPTION_DEFAULT_TEXT));

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

		instruction = new JTextArea();
		instruction.setFont(textFont);
		instruction.setText(STEP_DEFAULT_TEXT);
		instruction.addFocusListener(new DefaultTextAreaController(instruction, STEP_DEFAULT_TEXT));

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
		stepModel = new RecipeStepList();
		steps.setModel(stepModel);

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
		addButton.setActionCommand(RecipeCreationControlerAC.ADD_STEP);
		addButton.addActionListener(controler);

		JButton removeButton = new JButton("Eliminar");
		removeButton.setBackground(redButtonColor);
		removeButton.setForeground(Color.white);
		removeButton.setFont(textFont);
		removeButton.setFocusable(false);
		removeButton.setActionCommand(RecipeCreationControlerAC.REMOVE_STEP);
		removeButton.addActionListener(controler);

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
		ingredient.setText(INGREDIENT_DEFAULT_TEXT);
		ingredient.addFocusListener(new DefaultTextController(ingredient, INGREDIENT_DEFAULT_TEXT));

		quantity = new JTextField();
		quantity.setFont(textFont);
		quantity.setText(QUANTITY_DEFAULT_TEXT);
		quantity.addFocusListener(new DefaultTextController(quantity, QUANTITY_DEFAULT_TEXT));

		JButton addButton = new JButton("Añadir");
		addButton.setBackground(greenButtonColor);
		addButton.setForeground(Color.white);
		addButton.setFont(textFont);
		addButton.setFocusable(false);
		addButton.setActionCommand(RecipeCreationControlerAC.ADD_INGREDIENT);
		addButton.addActionListener(controler);

		JButton removeButton = new JButton("Eliminar");
		removeButton.setBackground(redButtonColor);
		removeButton.setForeground(Color.white);
		removeButton.setFont(textFont);
		removeButton.setFocusable(false);
		removeButton.setActionCommand(RecipeCreationControlerAC.REMOVE_INGREDIENT);
		removeButton.addActionListener(controler);

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
		ingredientModel = new IngredientList();
		ingredients.setModel(ingredientModel);

		scrollPane.setViewportView(ingredients);

		return scrollPane;
	}

	public RecipeStepList getStepListModel() {
		return stepModel;
	}

	public IngredientList getIngredientListModel() {
		return ingredientModel;
	}

	public void addIngredient() {
		controler.addIngredient(ingredient.getText(), quantity.getText());
	}

	public void addStep(String imageURL, int num) {
		controler.addStep(instruction.getText(), (int) values.getSelectedItem(),
				(RecipeStepActions) actions.getSelectedItem(), imageURL, num);
	}

	public void removeIngredient() {
		try {
			controler.removeIngredient(ingredients.getSelectedValue());
		} catch (IndexOutOfBoundsException e) {
		}

	}

	public void removeStep() {
		if (steps.getSelectedValue() != null) {
			try {
				controler.removeStep(steps.getSelectedValue());
			} catch (IndexOutOfBoundsException e) {
			}
		}
	}

}
