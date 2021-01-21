package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSpinner.DateEditor;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.JTextComponent;

import org.jdesktop.swingx.SwingXUtilities;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import eus.healthit.bchef.core.controllers.view.DefaultTextAreaController;
import eus.healthit.bchef.core.controllers.view.DefaultTextController;
import eus.healthit.bchef.core.controllers.view.RecipeCreationController;
import eus.healthit.bchef.core.controllers.view.RecipeCreationControllerAC;
import eus.healthit.bchef.core.controllers.view.StepViewController;
import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.WindowFrame;
import eus.healthit.bchef.core.view.borders.RoundedBorder;
import eus.healthit.bchef.core.view.borders.SearchBorder;
import eus.healthit.bchef.core.view.components.AutoCompleteTextField;
import eus.healthit.bchef.core.view.components.CustomScrollbarUI;
import eus.healthit.bchef.core.view.components.UIRoundButton;
import eus.healthit.bchef.core.view.ingredients.IngredientList;
import eus.healthit.bchef.core.view.ingredients.IngredientRenderer;
import eus.healthit.bchef.core.view.recipeStep.RecipeStepList;
import eus.healthit.bchef.core.view.recipeStep.RecipeStepRenderer;

public class CenterViewCreateRecipe extends JPanel {

	public final static String TITLE_DEFAULT_TEXT = "Introduzca titulo";
	public final static String DESCRIPTION_DEFAULT_TEXT = "Descripcion de la receta";
	public final static String INGREDIENT_DEFAULT_TEXT = "Ingrediente";
	public final static String QUANTITY_DEFAULT_TEXT = "Cantidad";
	public final static String STEP_DEFAULT_TEXT = "Instruccion del paso";

	Font titleFont = new Font("Segoe UI", Font.PLAIN, 20);
	Font textFont = new Font("Segoe UI", Font.PLAIN, 20);
	Color greenButtonColor = new Color(56, 186, 125);
	Color redButtonColor = new Color(243, 69, 65);
	Color bgColor = Color.white;

	User user;
	RecipeCreationController controller;

	String imagePath, stepImagePath;
	Image image, stepImage;

	JButton addImageButton, addStepImageButton;
	JButton addStepButton, removeStepButton;
	JButton addIngredientButton, removeIngredientButton;
	JButton createButton, previewButton;

	JLabel recipeCreationTitle, ingredientTitle, stepsTitleJLabel;

	JTextField title, description;
	AutoCompleteTextField ingredient;
	JTextField quantity;
	JTextArea instruction;

	JComboBox<RecipeStepActions> actions;
	JSpinner values, time;
	SpinnerNumberModel valueSpinnerModel;
	SpinnerDateModel timeSpinnerModel;

	JList<Ingredient> ingredients;
	JList<RecipeStep> steps;

	IngredientList ingredientModel;
	RecipeStepList stepModel;

	IngredientRenderer ingredientRenderer;
	RecipeStepRenderer stepRenderer;

	public CenterViewCreateRecipe(User user, WindowFrame window) {
		super(new GridLayout());
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.setBackground(bgColor);
		this.setOpaque(true);

		this.user = user;

		controller = new RecipeCreationController(this, window, user);

		initButtons();
		initJLabels();
		initJLists();
		initTextFields();
		initJSpinners();
		initJComboBoxes();

		this.add(createMainScrollPanel());
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

		addStepImageButton = new JButton("Añadir imagen");
		addStepImageButton.setPreferredSize(new Dimension(150, 40));
		addStepImageButton.setBackground(bgColor);
		addStepImageButton.setForeground(new Color(28, 162, 243));
		addStepImageButton.setFont(textFont);
		addStepImageButton.setFocusable(false);
		addStepImageButton.setUI(new UIRoundButton(addStepImageButton, 30, bgColor, new Color(234, 246, 254),
				new Color(210, 236, 252), new Color(28, 162, 243), new Font("Segoe UI", Font.BOLD, 15), controller,
				RecipeCreationControllerAC.ADD_IMAGE_STEP, "Añadir imagen", "Añadir imagen"));
		addStepImageButton.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
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

	private void initJLabels() {
		recipeCreationTitle = new JLabel("Crea tu propia receta");
		recipeCreationTitle.setFont(titleFont);
		recipeCreationTitle.setBackground(bgColor);

		ingredientTitle = new JLabel("Ingredientes");
		ingredientTitle.setFont(titleFont);
		ingredientTitle.setBackground(bgColor);

		stepsTitleJLabel = new JLabel("Pasos");
		stepsTitleJLabel.setFont(titleFont);
		stepsTitleJLabel.setBackground(bgColor);
	}

	private void initJLists() {
		ingredients = new JList<>();
		ingredientModel = new IngredientList();
		ingredientRenderer = new IngredientRenderer();

		ingredients.setModel(ingredientModel);
		ingredients.setCellRenderer(ingredientRenderer);

		steps = new JList<>();
		stepModel = new RecipeStepList();
		stepRenderer = new RecipeStepRenderer();

		steps.setModel(stepModel);
		steps.setCellRenderer(stepRenderer);
	}

	private void initTextFields() {
		title = new JTextField();
		title.setFont(textFont);
		title.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		title.setText(TITLE_DEFAULT_TEXT);
		title.addFocusListener(new DefaultTextController(title, TITLE_DEFAULT_TEXT));
		title.setForeground(Color.gray);
		title.setPreferredSize(new Dimension(544, 31));

		description = new JTextField();
		description.setFont(textFont);
		description.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		description.setText(DESCRIPTION_DEFAULT_TEXT);
		description.addFocusListener(new DefaultTextController(description, DESCRIPTION_DEFAULT_TEXT));
		description.setForeground(Color.gray);
		description.setPreferredSize(new Dimension(544, 31));

		instruction = new JTextArea();
		instruction.setFont(textFont);
//		instruction.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		instruction.setText(STEP_DEFAULT_TEXT);
		instruction.addFocusListener(new DefaultTextAreaController(instruction, STEP_DEFAULT_TEXT));
		instruction.setForeground(Color.gray);
		instruction.setMargin(new Insets(20, 20, 20, 20));
		instruction.setLineWrap(true);
		instruction.setWrapStyleWord(true);

		ingredient = new AutoCompleteTextField();
		ingredient.setFont(textFont);
		ingredient.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		ingredient.setText(INGREDIENT_DEFAULT_TEXT);
		ingredient.addFocusListener(new DefaultTextController(ingredient, INGREDIENT_DEFAULT_TEXT));
		ingredient.setForeground(Color.gray);
		ingredient.setPreferredSize(new Dimension(258, 37));
		ingredient.addKeyListener(controller);

		quantity = new JTextField();
		quantity.setFont(textFont);
		quantity.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
		quantity.setText(QUANTITY_DEFAULT_TEXT);
		quantity.addFocusListener(new DefaultTextController(quantity, QUANTITY_DEFAULT_TEXT));
		quantity.setForeground(Color.gray);
	}

	private void initJComboBoxes() {
		actions = new JComboBox<>();
		actions.setPreferredSize(new Dimension(100, 10));
		actions.setBackground(bgColor);
		actions.setBorder(new RoundedBorder(20, new Color(200, 200, 200)));
		actions.setFocusable(false);
		actions.addActionListener(controller);
		actions.setActionCommand(RecipeCreationControllerAC.ACTION_CHANGE);

		actions.addItem(RecipeStepActions.OVEN);
		actions.addItem(RecipeStepActions.STOVE);
		actions.addItem(RecipeStepActions.TIMER);
	}

	private void initJSpinners() {
		values = new JSpinner();
		values.setPreferredSize(new Dimension(100, 10));
		values.setBackground(bgColor);
		values.setBorder(new RoundedBorder(20, new Color(200, 200, 200)));

		valueSpinnerModel = new SpinnerNumberModel(0, 0, 10, 1);
		values.setModel(valueSpinnerModel);

		time = new JSpinner();
		time.setPreferredSize(new Dimension(125, 10));
		time.setBackground(bgColor);
		time.setBorder(new RoundedBorder(20, new Color(200, 200, 200)));

		timeSpinnerModel = new SpinnerDateModel(new Date(0), null, null, Calendar.MINUTE);

		time.setModel(timeSpinnerModel);

		DateEditor editor = new DateEditor(time, "HH:mm:ss");
		time.setEditor(editor);

	}

	public void setAutoCompleteList(List<Ingredient> list) {
		List<String> strings = list.stream().map(object -> Objects.toString(object, null)).collect(Collectors.toList());

		ingredient.setPossibilities(strings);
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

	private Component createMainScrollPanel() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(bgColor);
		scrollPane.setOpaque(true);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		scrollPane.setViewportView(createMainPanel());

		return scrollPane;
	}

	private Component createBoxPanel() {
		Box verticalBox = Box.createVerticalBox();

		verticalBox.add(Box.createVerticalStrut(50));
		verticalBox.add(createFlow());
		verticalBox.add(Box.createVerticalStrut(50));
		verticalBox.setBackground(bgColor);
		verticalBox.setOpaque(true);

		Box horizontalBox = Box.createHorizontalBox();

		horizontalBox.add(Box.createVerticalStrut(50));
		horizontalBox.add(verticalBox);
		horizontalBox.add(Box.createVerticalStrut(50));
		horizontalBox.setBackground(bgColor);
		horizontalBox.setOpaque(true);

		return horizontalBox;
	}

	private Component createFlow() {
		JPanel flowPanel = new JPanel(new FlowLayout());
		flowPanel.setBackground(bgColor);

		flowPanel.add(createMainPanel());

		return flowPanel;
	}

	private Component createMainPanel() {
		JPanel mainPanel = new JPanel(new GridBagLayout());
		mainPanel.setBackground(bgColor);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 10, 0, 10);
		constraints.gridx = 0;
		constraints.gridy = 0;

		mainPanel.add(createLeftPanel(), constraints);
		constraints.gridx = 1;
		mainPanel.add(createRightPanel(), constraints);

		return mainPanel;
	}

	private Component createLeftPanel() {
		JPanel leftPanel = new JPanel(new GridBagLayout());
		leftPanel.setBackground(bgColor);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(5, 0, 5, 0);
		constraints.gridx = 0;
		constraints.gridy = 0;

		leftPanel.add(createAddImagePanel(), constraints);
		constraints.gridy = 1;
		leftPanel.add(createIngredientFieldsPanel(), constraints);
		constraints.gridy = 2;
		leftPanel.add(createIngredientButtonPanel(), constraints);
		constraints.gridy = 3;
		leftPanel.add(createIngredientListPanel(), constraints);
		constraints.gridy = 4;
		leftPanel.add(createPreviewButtonPanel(), constraints);

		return leftPanel;
	}

	private Component createAddImagePanel() {
		JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		imagePanel.setBackground(bgColor);

		imagePanel.add(addImageButton);

		return imagePanel;
	}

	private Component createIngredientFieldsPanel() {
		JPanel ingredientFielsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
		ingredientFielsPanel.setBackground(bgColor);

		ingredientFielsPanel.add(createIngredientsTextPanel());
		ingredientFielsPanel.add(ingredient);
		ingredientFielsPanel.add(quantity);

		return ingredientFielsPanel;
	}

	private Component createIngredientsTextPanel() {
		JPanel ingredientTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		ingredientTextPanel.setBackground(bgColor);

		ingredientTextPanel.add(ingredientTitle);

		return ingredientTextPanel;
	}

	private Component createIngredientButtonPanel() {
		JPanel ingredientButtonPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		ingredientButtonPanel.setBackground(bgColor);

		ingredientButtonPanel.add(addIngredientButton);
		ingredientButtonPanel.add(removeIngredientButton);

		return ingredientButtonPanel;
	}

	private Component createIngredientListPanel() {
		JScrollPane ingredientListPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ingredientListPanel.setBackground(bgColor);
		ingredientListPanel.setOpaque(true);
		ingredientListPanel.setPreferredSize(new Dimension(258, 130));
		ingredientListPanel.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		ingredientListPanel.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		ingredientListPanel.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		ingredientListPanel.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		ingredientListPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		ingredientListPanel.setViewportView(ingredients);

		return ingredientListPanel;
	}

	private Component createPreviewButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		buttonPanel.setBackground(bgColor);

		buttonPanel.add(previewButton);

		return buttonPanel;
	}

	private Component createRightPanel() {
		JPanel rightPanel = new JPanel(new GridBagLayout());
		rightPanel.setBackground(bgColor);
		rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 18, 0));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(5, 0, 5, 0);
		constraints.gridx = 0;
		constraints.gridy = 0;

		rightPanel.add(createTitleDesciptionPanel(), constraints);
		constraints.gridy = 1;
		rightPanel.add(createStepFieldsPanel(), constraints);
		constraints.gridy = 2;
		rightPanel.add(createStepButtonPanel(), constraints);
		constraints.gridy = 3;
		rightPanel.add(createStepListPanel(), constraints);
		constraints.gridy = 4;
		rightPanel.add(createCreateRecipeButtonPanel(), constraints);

		return rightPanel;
	}

	private Component createTitleDesciptionPanel() {
		JPanel titleDescriptionPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		titleDescriptionPanel.setBackground(bgColor);

		titleDescriptionPanel.add(title);
		titleDescriptionPanel.add(description);

		return titleDescriptionPanel;
	}

	private Component createStepFieldsPanel() {
		JPanel stepFields = new JPanel(new BorderLayout());
		stepFields.setBackground(bgColor);

		stepFields.add(createStepTextPanel(), BorderLayout.NORTH);
		stepFields.add(createInstructionTextPanel(), BorderLayout.CENTER);
		stepFields.add(createComboBoxPanel(), BorderLayout.EAST);

		return stepFields;
	}

	private Component createStepTextPanel() {
		JPanel stepTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		stepTextPanel.setBackground(bgColor);

		stepTextPanel.add(stepsTitleJLabel);

		return stepTextPanel;
	}

	private Component createInstructionTextPanel() {
		JScrollPane slide = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		slide.setBackground(bgColor);
		slide.setOpaque(true);
		slide.setPreferredSize(new Dimension(314, 79));
		slide.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));

		slide.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		slide.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		slide.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		slide.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		slide.setViewportView(instruction);

		return slide;
	}

	private Component createStepButtonPanel() {
		JPanel stepButtonPanel = new JPanel(new BorderLayout());

		stepButtonPanel.add(createAddRemoveStepPanel(), BorderLayout.CENTER);
		stepButtonPanel.add(createAddStepImagePanel(), BorderLayout.EAST);

		return stepButtonPanel;
	}

	private Component createAddRemoveStepPanel() {
		JPanel addRemoveStepPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		addRemoveStepPanel.setBackground(bgColor);

		addRemoveStepPanel.add(addStepButton);
		addRemoveStepPanel.add(removeStepButton);

		return addRemoveStepPanel;
	}

	private Component createAddStepImagePanel() {
		JPanel addStepImagePanel = new JPanel(new GridLayout());
		addStepImagePanel.setBackground(bgColor);

		addStepImagePanel.add(addStepImageButton);

		return addStepImagePanel;
	}

	private Component createStepListPanel() {
		JScrollPane stepListPanel = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		stepListPanel.setBackground(bgColor);
		stepListPanel.setOpaque(true);
		stepListPanel.setPreferredSize(new Dimension(439, 130));

		stepListPanel.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		stepListPanel.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		stepListPanel.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		stepListPanel.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		stepListPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		stepListPanel.setViewportView(steps);

		return stepListPanel;
	}

	private Component createCreateRecipeButtonPanel() {
		JPanel buttonPanel = new JPanel(new GridLayout(1, 1, 5, 5));
		buttonPanel.setBackground(bgColor);

		buttonPanel.add(createButton);

		return buttonPanel;
	}

	private Component createButtonPannel() {
		JPanel buttonPanel = new JPanel(new BorderLayout(20, 20));
		buttonPanel.setBackground(bgColor);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

		buttonPanel.add(previewButton, BorderLayout.WEST);
		buttonPanel.add(createButton, BorderLayout.EAST);

		return buttonPanel;
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
		titleLabel.setHorizontalAlignment(JLabel.LEFT);

		infoPanel.add(createComboBoxPanel(), BorderLayout.EAST);
		infoPanel.add(instruction, BorderLayout.CENTER);
		infoPanel.add(titleLabel, BorderLayout.NORTH);

		return infoPanel;
	}

	private Component createComboBoxPanel() {
		JPanel comboPanel = new JPanel(new GridLayout(3, 1, 5, 5));
		comboPanel.setBackground(bgColor);

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

		scrollPane.setViewportView(steps);

		return scrollPane;
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

		scrollPane.setViewportView(ingredients);

		return scrollPane;
	}

//	public void addIngredient() {
//		controller.addIngredient(ingredient.getText(), quantity.getText());
//	}
//	

//	public void addStep(String imageURL, int num) {
//		controller.addStep(instruction.getText(), (int) values.getValue(),
//				(RecipeStepActions) actions.getSelectedItem(), imageURL, num);
//	}

	public void removeIngredient() {
		try {
			controller.removeIngredient(ingredients.getSelectedValue());
		} catch (IndexOutOfBoundsException e) {
		}
	}

	public void removeStep() {
		try {
			controller.removeStep(steps.getSelectedValue());
		} catch (IndexOutOfBoundsException e) {
		}
	}

	public RecipeStepList getStepListModel() {
		return stepModel;
	}

	public IngredientList getIngredientListModel() {
		return ingredientModel;
	}

	public String getTitle() {
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

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getStepImage() {
		return stepImage;
	}

	public void setStepImage(Image stepImage) {
		this.stepImage = stepImage;
	}

	public String getTime() {
		return String.valueOf(timeSpinnerModel.getValue());
	}

	public RecipeStepActions getAction() {
		return (RecipeStepActions) actions.getSelectedItem();
	}

	public int getValue() {
		return (int) values.getValue();
	}

	public String getInstruction() {
		return instruction.getText();
	}

	public void changeValueLimits() {
		if (actions.getSelectedItem().equals(RecipeStepActions.OVEN)) {
			valueSpinnerModel.setMinimum(0);
			valueSpinnerModel.setMaximum(300);
			valueSpinnerModel.setStepSize(1);
			valueSpinnerModel.setValue(0);

			values.setEnabled(true);

		} else if (actions.getSelectedItem().equals(RecipeStepActions.STOVE)) {
			valueSpinnerModel.setMinimum(0);
			valueSpinnerModel.setMaximum(10);
			valueSpinnerModel.setStepSize(1);
			valueSpinnerModel.setValue(0);

			values.setEnabled(true);

		} else if (actions.getSelectedItem().equals(RecipeStepActions.TIMER)) {
			values.setEnabled(false);
		}
	}

	public void resetIngredientFields() {
		if (ingredient.hasFocus()) {
			ingredient.setText("");
		} else {
			ingredient.setText(INGREDIENT_DEFAULT_TEXT);
			ingredient.setForeground(Color.gray);
		}

		if (quantity.hasFocus()) {
			quantity.setText("");
		} else {
			quantity.setText(QUANTITY_DEFAULT_TEXT);
			quantity.setForeground(Color.gray);
		}

	}

	public void resetStepFIelds() {
		if (instruction.hasFocus()) {
			instruction.setText("");
		} else {
			instruction.setText(STEP_DEFAULT_TEXT);
			instruction.setForeground(Color.gray);
		}
	}

	public String getImagePath() {
		return imagePath;
	}

	public String getStepImagePath() {
		return stepImagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public void setStepImagePath(String stepImagePath) {
		this.stepImagePath = stepImagePath;
	}

	public String getIngredientName() {
		return ingredient.getText();
	}

	public String getIngredientQuantity() {
		return quantity.getText();
	}
}
