package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

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

	JButton addImageButton;
	JButton addStepButton, removeStepButton;
	JButton addIngredientButton, removeIngredientButton;

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

		initButtons();
		initTextFields();
		addComboData();

		this.add(createContent());
	}

	private void initButtons() {

		addStepButton = new JButton("Añadir");
		addStepButton.setBackground(greenButtonColor);
		addStepButton.setForeground(Color.white);
		addStepButton.setFont(textFont);
		addStepButton.setFocusable(false);
		addStepButton.setActionCommand(RecipeCreationControlerAC.ADD_STEP);
		addStepButton.addActionListener(controler);

		removeStepButton = new JButton("Eliminar");
		removeStepButton.setBackground(redButtonColor);
		removeStepButton.setForeground(Color.white);
		removeStepButton.setFont(textFont);
		removeStepButton.setFocusable(false);
		removeStepButton.setActionCommand(RecipeCreationControlerAC.REMOVE_STEP);
		removeStepButton.addActionListener(controler);

		addImageButton = new JButton("Añadir imagen");
		addImageButton.setBackground(Color.white);
		addImageButton.setForeground(Color.DARK_GRAY);
		addImageButton.setFocusable(false);

		addIngredientButton = new JButton("Añadir");
		addIngredientButton.setBackground(greenButtonColor);
		addIngredientButton.setForeground(Color.white);
		addIngredientButton.setFont(textFont);
		addIngredientButton.setFocusable(false);
		addIngredientButton.setActionCommand(RecipeCreationControlerAC.ADD_INGREDIENT);
		addIngredientButton.addActionListener(controler);

		removeIngredientButton = new JButton("Eliminar");
		removeIngredientButton.setBackground(redButtonColor);
		removeIngredientButton.setForeground(Color.white);
		removeIngredientButton.setFont(textFont);
		removeIngredientButton.setFocusable(false);
		removeIngredientButton.setActionCommand(RecipeCreationControlerAC.REMOVE_INGREDIENT);
		removeIngredientButton.addActionListener(controler);
	}

	private void initTextFields() {

		title = new JTextField();
		title.setFont(textFont);
		title.setText(TITLE_DEFAULT_TEXT);
		title.addFocusListener(new DefaultTextController(title, TITLE_DEFAULT_TEXT));
		title.setForeground(Color.gray);

		description = new JTextField();
		description.setFont(textFont);
		description.setText(DESCRIPTION_DEFAULT_TEXT);
		description.addFocusListener(new DefaultTextController(description, DESCRIPTION_DEFAULT_TEXT));
		description.setForeground(Color.gray);

		instruction = new JTextArea();
		instruction.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		instruction.setFont(textFont);
		instruction.setText(STEP_DEFAULT_TEXT);
		instruction.addFocusListener(new DefaultTextAreaController(instruction, STEP_DEFAULT_TEXT));
		instruction.setForeground(Color.gray);
		instruction.setMargin(new Insets(20, 20, 20, 20));

		ingredient = new JTextField();
		ingredient.setFont(textFont);
		ingredient.setText(INGREDIENT_DEFAULT_TEXT);
		ingredient.addFocusListener(new DefaultTextController(ingredient, INGREDIENT_DEFAULT_TEXT));
		ingredient.setForeground(Color.gray);

		quantity = new JTextField();
		quantity.setFont(textFont);
		quantity.setText(QUANTITY_DEFAULT_TEXT);
		quantity.addFocusListener(new DefaultTextController(quantity, QUANTITY_DEFAULT_TEXT));
		quantity.setForeground(Color.gray);

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

		stepButtonPanel.add(addStepButton);
		stepButtonPanel.add(removeStepButton);

		return stepButtonPanel;
	}

	private Component createWestPanel() {
		JPanel westPanel = new JPanel(new BorderLayout(10, 10));
		westPanel.setBackground(Color.white);
		westPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 50, Color.white));

		westPanel.add(createIngredientPanel(), BorderLayout.CENTER);
		westPanel.add(addImageButton, BorderLayout.NORTH);
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

		northPanel.add(titleLabel);
		northPanel.add(ingredient);
		northPanel.add(quantity);
		northPanel.add(addIngredientButton);
		northPanel.add(removeIngredientButton);

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
