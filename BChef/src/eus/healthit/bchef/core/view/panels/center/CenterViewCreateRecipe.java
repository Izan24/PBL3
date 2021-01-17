package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.io.File;
import java.util.List;

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
import eus.healthit.bchef.core.controllers.view.RecipeCreationController;
import eus.healthit.bchef.core.controllers.view.RecipeCreationControllerAC;
import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.borders.RoundedBorder;
import eus.healthit.bchef.core.view.borders.SearchBorder;
import eus.healthit.bchef.core.view.components.UIRoundButton;
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

	Font textFont = new Font("Segoe UI", Font.PLAIN, 20);
	User user;

	RecipeCreationController controller;

	Color greenButtonColor = new Color(56, 186, 125);
	Color redButtonColor = new Color(243, 69, 65);

	JButton addImageButton;
	JButton addStepButton, removeStepButton;
	JButton addIngredientButton, removeIngredientButton;
	JButton createButton, previewButton;

	JTextField title, description;
	JTextField ingredient, quantity;
	JTextArea instruction;

	String imgPath;

	JComboBox<RecipeStepActions> actions;
	JComboBox<Integer> values;
	JComboBox<Integer> time;

	JList<Ingredient> ingredients;
	JList<RecipeStep> steps;

	IngredientList ingredientModel;
	RecipeStepList stepModel;

	Color bgColor = Color.white;

	public CenterViewCreateRecipe(User user) {
		super(new BorderLayout(100, 100));
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.setBackground(bgColor);
		this.setOpaque(true);

		this.user = user;

		controller = new RecipeCreationController(this);

		initButtons();
		initTextFields();
		addComboData();

		this.add(createContent(), BorderLayout.CENTER);
	}

	private void initButtons() {

		addStepButton = new JButton("Añadir");
		addStepButton.setPreferredSize(new Dimension(150, 35));
		addStepButton.setBackground(greenButtonColor);
		addStepButton.setForeground(Color.white);
		addStepButton.setFont(textFont);
		addStepButton.setBorder(BorderFactory.createEmptyBorder());
		addStepButton.setFocusable(false);
		addStepButton.setUI(new UIRoundButton(addStepButton, 30, greenButtonColor, Color.white,
				new Font("Segoe UI", Font.BOLD, 15), controller, RecipeCreationControllerAC.ADD_STEP));

		removeStepButton = new JButton("Eliminar");
		removeStepButton.setPreferredSize(new Dimension(150, 35));
		removeStepButton.setBackground(redButtonColor);
		removeStepButton.setForeground(Color.white);
		removeStepButton.setFont(textFont);
		removeStepButton.setBorder(BorderFactory.createEmptyBorder());
		removeStepButton.setFocusable(false);
		removeStepButton.setUI(new UIRoundButton(removeStepButton, 30, redButtonColor, Color.white,
				new Font("Segoe UI", Font.BOLD, 15), controller, RecipeCreationControllerAC.REMOVE_STEP));

		addImageButton = new JButton("Añadir imagen");
		addImageButton.setPreferredSize(new Dimension(150, 40));
		addImageButton.setBackground(bgColor);
		addImageButton.setForeground(new Color(28, 162, 243));
		addImageButton.setFont(textFont);
		addImageButton.setFocusable(false);
		addImageButton.setUI(new UIRoundButton(addImageButton, 30, bgColor, new Color(234, 246, 254),
				new Color(210, 236, 252), new Color(28, 162, 243), new Font("Segoe UI", Font.BOLD, 15), controller,
				RecipeCreationControllerAC.ADD_IMAGE, "Añadir imagen", "Añadir imagen"));
		addImageButton.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
				BorderFactory.createEmptyBorder(60, 40, 60, 40)));

		addIngredientButton = new JButton("Añadir");
		addIngredientButton.setPreferredSize(new Dimension(150, 35));
		addIngredientButton.setBackground(new Color(28, 162, 243));
		addIngredientButton.setForeground(Color.white);
		addIngredientButton.setFont(textFont);
		addIngredientButton.setBorder(BorderFactory.createEmptyBorder());
		addIngredientButton.setFocusable(false);
		addIngredientButton.setUI(new UIRoundButton(addIngredientButton, 30, greenButtonColor, Color.white,
				new Font("Segoe UI", Font.BOLD, 15), controller, RecipeCreationControllerAC.ADD_INGREDIENT));

		removeIngredientButton = new JButton("Eliminar");
		removeIngredientButton.setPreferredSize(new Dimension(150, 35));
		removeIngredientButton.setBackground(new Color(28, 162, 243));
		removeIngredientButton.setForeground(Color.white);
		removeIngredientButton.setFont(textFont);
		removeIngredientButton.setBorder(BorderFactory.createEmptyBorder());
		removeIngredientButton.setFocusable(false);
		removeIngredientButton.setUI(new UIRoundButton(removeIngredientButton, 30, redButtonColor, Color.white,
				new Font("Segoe UI", Font.BOLD, 15), controller, RecipeCreationControllerAC.REMOVE_INGREDIENT));

		createButton = new JButton("Crear receta");
		createButton.setPreferredSize(new Dimension(150, 35));
		createButton.setBackground(new Color(28, 162, 243));
		createButton.setForeground(Color.white);
		createButton.setFont(textFont);
		createButton.setBorder(BorderFactory.createEmptyBorder());
		createButton.setFocusable(false);
		createButton.setUI(new UIRoundButton(createButton, 30, new Color(28, 162, 243), Color.white,
				new Font("Segoe UI", Font.BOLD, 15), controller, RecipeCreationControllerAC.CREATE_RECIPE));

		previewButton = new JButton("Vista previa");
		previewButton.setPreferredSize(new Dimension(150, 40));
		previewButton.setBackground(bgColor);
		previewButton.setForeground(new Color(28, 162, 243));
		previewButton.setFont(textFont);
		previewButton.setFocusable(false);
		previewButton.setUI(new UIRoundButton(previewButton, 30, bgColor, new Color(234, 246, 254),
				new Color(210, 236, 252), new Color(28, 162, 243), new Font("Segoe UI", Font.BOLD, 15), controller,
				RecipeCreationControllerAC.PREVIEW, "Vista previa", "Vista previa"));
		previewButton.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
				BorderFactory.createEmptyBorder(60, 40, 60, 40)));
	}

	private void initTextFields() {

		title = new JTextField();
		title.setFont(textFont);
		title.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		title.setText(TITLE_DEFAULT_TEXT);
		title.addFocusListener(new DefaultTextController(title, TITLE_DEFAULT_TEXT));
		title.setForeground(Color.gray);

		description = new JTextField();
		description.setFont(textFont);
		description.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		description.setText(DESCRIPTION_DEFAULT_TEXT);
		description.addFocusListener(new DefaultTextController(description, DESCRIPTION_DEFAULT_TEXT));
		description.setForeground(Color.gray);

		instruction = new JTextArea();
		instruction.setFont(textFont);
		instruction.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
//		instruction.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		instruction.setText(STEP_DEFAULT_TEXT);
		instruction.addFocusListener(new DefaultTextAreaController(instruction, STEP_DEFAULT_TEXT));
		instruction.setForeground(Color.gray);
		instruction.setMargin(new Insets(20, 20, 20, 20));

		ingredient = new JTextField();
		ingredient.setFont(textFont);
		ingredient.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		ingredient.setText(INGREDIENT_DEFAULT_TEXT);
		ingredient.addFocusListener(new DefaultTextController(ingredient, INGREDIENT_DEFAULT_TEXT));
		ingredient.setForeground(Color.gray);

		quantity = new JTextField();
		quantity.setFont(textFont);
		quantity.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		quantity.setText(QUANTITY_DEFAULT_TEXT);
		quantity.addFocusListener(new DefaultTextController(quantity, QUANTITY_DEFAULT_TEXT));
		quantity.setForeground(Color.gray);

	}

	private void addComboData() {
	}

	private Component createButtonPannel() {
		JPanel buttonPanel = new JPanel(new BorderLayout(20, 20));
		buttonPanel.setBackground(bgColor);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		buttonPanel.add(previewButton, BorderLayout.WEST);
		buttonPanel.add(createButton, BorderLayout.EAST);

		return buttonPanel;
	}

	private Component createContent() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(bgColor);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		JPanel contentPanel = new JPanel(new BorderLayout());
		contentPanel.setBackground(bgColor);

		contentPanel.add(createCenterPanel(), BorderLayout.CENTER);
		contentPanel.add(createWestPanel(), BorderLayout.WEST);
		contentPanel.add(createButtonPannel(), BorderLayout.SOUTH);

		scrollPane.setViewportView(contentPanel);

		return scrollPane;
	}

	private Component createCenterPanel() {
		JPanel centerPanel = new JPanel(new BorderLayout(20, 20));
		centerPanel.setBackground(bgColor);

		centerPanel.add(createTitlePanel(), BorderLayout.NORTH);
		centerPanel.add(createStepPanel(), BorderLayout.CENTER);
		return centerPanel;
	}

	private Component createTitlePanel() {
		JPanel titlePanel = new JPanel(new GridLayout(2, 1, 15, 15));
		titlePanel.setBackground(bgColor);

		titlePanel.add(title);
		titlePanel.add(description);

		return titlePanel;
	}

	private Component createStepPanel() {
		JPanel stepsPanel = new JPanel(new GridLayout(2, 1, 3, 3));
		stepsPanel.setBackground(bgColor);

		stepsPanel.add(createInfoPanel());
		stepsPanel.add(createAddedPanel());

		return stepsPanel;
	}

	private Component createInfoPanel() {
		JPanel infoPanel = new JPanel(new BorderLayout(10, 10));
		infoPanel.setBackground(bgColor);

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
		comboPanel.setBackground(bgColor);

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
		addedPanel.setBackground(bgColor);

		addedPanel.add(createStepButtonPanel(), BorderLayout.NORTH);
		addedPanel.add(createStepsScroll(), BorderLayout.CENTER);

		return addedPanel;
	}

	private Component createStepsScroll() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(bgColor);
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
		stepButtonPanel.setBackground(bgColor);

		stepButtonPanel.add(addStepButton);
		stepButtonPanel.add(removeStepButton);

		return stepButtonPanel;
	}

	private Component createWestPanel() {
		JPanel westPanel = new JPanel(new BorderLayout(10, 10));
		westPanel.setBackground(bgColor);
		westPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 50, Color.white));

		westPanel.add(createIngredientPanel(), BorderLayout.CENTER);
		westPanel.add(addImageButton, BorderLayout.NORTH);
		return westPanel;
	}

	private Component createIngredientPanel() {
		JPanel ingredientPanel = new JPanel(new BorderLayout(5, 5));
		ingredientPanel.setBackground(bgColor);

		ingredientPanel.add(createIngredientNorthPanel(), BorderLayout.NORTH);
		ingredientPanel.add(createIngredientScroll(), BorderLayout.CENTER);
		return ingredientPanel;
	}

	private Component createIngredientNorthPanel() {
		JPanel northPanel = new JPanel(new GridLayout(5, 1, 7, 7));
		northPanel.setBackground(bgColor);
		northPanel.setBorder(BorderFactory.createMatteBorder(100, 0, 0, 0, Color.white));

		JLabel titleLabel = new JLabel("Ingredientes");
		titleLabel.setFont(textFont);
		titleLabel.setBackground(bgColor);

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
		scrollPane.setBackground(bgColor);
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
		controller.addIngredient(ingredient.getText(), quantity.getText());
	}

	public void addStep(String imageURL, int num) {
		controller.addStep(instruction.getText(), (int) values.getSelectedItem(),
				(RecipeStepActions) actions.getSelectedItem(), imageURL, num);
	}

	public void removeIngredient() {
		try {
			controller.removeIngredient(ingredients.getSelectedValue());
		} catch (IndexOutOfBoundsException e) {
		}

	}

	public void removeStep() {
		if (steps.getSelectedValue() != null) {
			try {
				controller.removeStep(steps.getSelectedValue());
			} catch (IndexOutOfBoundsException e) {
			}
		}
	}

	public String getName() {
		return title.getText();
	}

	public String getDescription() {
		return description.getText();
	}

	public String getAuthor() {
		return user.getName();
	}

	public List<Ingredient> getIngredients() {
		return ingredientModel.getList();
	}

	public List<RecipeStep> getSteps() {
		return stepModel.getList();
	}

	public String getImage() {
		return imgPath;
	}

	public void setImage(String imgPath) {
		this.imgPath = imgPath;
	}
}
