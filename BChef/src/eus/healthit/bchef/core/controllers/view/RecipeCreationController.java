package eus.healthit.bchef.core.controllers.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.WindowFrame;
import eus.healthit.bchef.core.view.dialogs.CreationErrorDialog;
import eus.healthit.bchef.core.view.dialogs.FileChooser;
import eus.healthit.bchef.core.view.panels.center.CenterPreviewRecipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewCreateRecipe;

public class RecipeCreationController implements IRoundButtonListener, ActionListener, KeyListener {

	JFrame framePreview;
	CenterViewCreateRecipe createRecipeView;
	WindowFrame window;
	User user;

	String oldIngredientName;
	List<Ingredient> suggestionIngredientList;

	public RecipeCreationController(CenterViewCreateRecipe createRecipeView, WindowFrame window, User user) {
		this.createRecipeView = createRecipeView;
		this.window = window;
		this.user = user;

		oldIngredientName = "";

		suggestionIngredientList = new ArrayList<>();

		createPreviewWindow();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("keypressed");
		if (!createRecipeView.getIngredientName().equals(oldIngredientName)) {

			suggestionIngredientList = JSONCalls.ingredientLike(createRecipeView.getIngredientName());

			createRecipeView.setAutoCompleteList(suggestionIngredientList);

			System.out.println("query");
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case RecipeCreationControllerAC.ACTION_CHANGE:
			createRecipeView.changeValueLimits();
			break;

		default:
			break;
		}
	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {

		case RecipeCreationControllerAC.ADD_IMAGE:
			FileChooser file = null;
			System.out.println("ADD IMAGE");
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				file = new FileChooser();
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				Image img = ImageIO.read(file.getSelectedFile());
				createRecipeView.setImage(img);
				createRecipeView.setImagePath(file.getSelectedFile().getAbsolutePath());
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case RecipeCreationControllerAC.ADD_IMAGE_STEP:
			FileChooser fileImage = null;
			System.out.println("ADD IMAGE");
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				fileImage = new FileChooser();
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				Image img = ImageIO.read(fileImage.getSelectedFile());
				createRecipeView.setStepImage(img);
				createRecipeView.setStepImagePath(fileImage.getSelectedFile().getAbsolutePath());
			} catch (Exception e) {
			}

			break;

		case RecipeCreationControllerAC.ADD_INGREDIENT:

			if (checkIngredient()) {
				Ingredient ingredient = ingredientExists();
				if (ingredient == null) {
					new CreationErrorDialog(window, "Invalid ingredient", true,
							"El ingrediente introducido no está en la base de datos");
				} else {
					System.out.println();
					ingredient.setQuantity(createRecipeView.getIngredientQuantity());
					addIngredient(ingredient);
					createRecipeView.resetIngredientFields();
				}
			}
			break;

		case RecipeCreationControllerAC.REMOVE_INGREDIENT:
			createRecipeView.removeIngredient();
			break;

		case RecipeCreationControllerAC.ADD_STEP:
			System.out.println("ADD_STEP");
			if (checkStep()) {
				addStep();
				createRecipeView.resetStepFIelds();
			}
			break;

		case RecipeCreationControllerAC.REMOVE_STEP:
			System.out.println("REMOVE_STEP:");
			createRecipeView.removeStep();
			break;

		case RecipeCreationControllerAC.CREATE_RECIPE:
//			System.out.println(
//					"Create recipe, hay que comrpobar que tenga toddos los campos puesto, para" + "eso haz un metodo:");
			if (recipeValid()) {
				addStepNumbers();
				System.out.println(createRecipeView.getDescription());
				Recipe recipe = new Recipe(UUID.randomUUID(),createRecipeView.getTitle(), createRecipeView.getAuthor(), user.getId(),
						createRecipeView.getDescription(), createRecipeView.getIngredientListModel().getList(),
						createRecipeView.getStepListModel().getList(), createRecipeView.getImagePath());
				
				JSONCalls.addRecipe(recipe);
				user.addPublication(recipe);
			}
			break;

		case RecipeCreationControllerAC.PREVIEW:
			openPreviewWindow();
			break;
		}
	}

	private void addStepNumbers() {
		List<RecipeStep> ls = createRecipeView.getStepListModel().getList();
		for (int i = 0; i < ls.size(); i++) {
			ls.get(i).setNum(i + 1);
		}
		// createRecipeView.getStepListModel().setList(ls);
	}

	private void addIngredient(Ingredient ingredient) {
		//createRecipeView.getIngredientListModel().getList().add(ingredient);
		createRecipeView.getIngredientListModel().addElement(ingredient);
	}

	private boolean recipeValid() {
		if (createRecipeView.getTitle().trim().equals("")
				|| createRecipeView.getTitle().equals(CenterViewCreateRecipe.TITLE_DEFAULT_TEXT)) {
			new CreationErrorDialog(window, "Invalid title", true, "El titulo introducido no es valido");
			return false;
		} else if (createRecipeView.getDescription().trim().equals("")
				|| createRecipeView.getDescription().equals(CenterViewCreateRecipe.DESCRIPTION_DEFAULT_TEXT)) {
			new CreationErrorDialog(window, "Invalid description", true, "La descripción no es valida");
			return false;
		} else if (createRecipeView.getIngredientListModel().getSize() == 0) {
			new CreationErrorDialog(window, "Invalid ingredients", true, "Introduce minimo un ingrediente");
			return false;
		} else if (createRecipeView.getStepListModel().getSize() == 0) {
			new CreationErrorDialog(window, "Invalid step", true, "Introduce minimo un paso");
			return false;
		}
		return true;
	}

	private void openPreviewWindow() {
		CenterPreviewRecipe preview = new CenterPreviewRecipe();
		preview.setRecipe(createRecipe());

		framePreview.setContentPane(preview);
		framePreview.setSize(1250, 750);
		framePreview.setLocation(400, 75);
		framePreview.setVisible(true);
	}

	public Recipe createRecipe() {
		String title;
		String description;

		/*
		 * PASA ESTO A UN METODO
		 */

		if (createRecipeView.getTitle().equals(CenterViewCreateRecipe.TITLE_DEFAULT_TEXT)) {
			title = "";
		} else {
			title = createRecipeView.getTitle();
		}

		if (createRecipeView.getDescription().equals(CenterViewCreateRecipe.DESCRIPTION_DEFAULT_TEXT)) {
			description = "";
		} else {
			description = createRecipeView.getName();
		}

		Recipe recipe = new Recipe(title, createRecipeView.getAuthor(), 2, description, 0,
				createRecipeView.getIngredients(), createRecipeView.getSteps(), createRecipeView.getImage());

		return (recipe);
	}

	private void createPreviewWindow() {
		framePreview = new JFrame("Preview de receta");
		framePreview.setSize(1250, 750);
		framePreview.setLocation(400, 75);
		framePreview.setResizable(true);
		framePreview.setBackground(Color.white);

		framePreview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private boolean checkIngredient() {
		if (createRecipeView.getIngredientName().trim().equals("")
				|| createRecipeView.getIngredientName().equals(CenterViewCreateRecipe.INGREDIENT_DEFAULT_TEXT)) {
			new CreationErrorDialog(window, "Invalid ingredient", true, "El ingrediente introducido no es valido");
			return false;
		} else if (createRecipeView.getIngredientQuantity().trim().equals("")
				|| createRecipeView.getIngredientQuantity().equals(CenterViewCreateRecipe.QUANTITY_DEFAULT_TEXT)) {
			new CreationErrorDialog(window, "Invalid quantity", true, "La cantidad introducida no es valida");
			return false;
		}
		return true;
	}

	private Ingredient ingredientExists() {
		if (suggestionIngredientList == null) {
			return null;
		}

		for (Ingredient i : suggestionIngredientList) {
			if (i.getName().toUpperCase().equals(createRecipeView.getIngredientName().toUpperCase())) {
				return i;
			}
		}
		return null;
	}

	public void addStep() {
		RecipeStep step = null;

		step = new RecipeStep(createRecipeView.getAction(), createRecipeView.getValue(),
				createRecipeView.getStepImage(), createRecipeView.getInstruction(), createRecipeView.getValue());

		step.setImagePath(createRecipeView.getStepImagePath());

		createRecipeView.getStepListModel().addElement(step);

		createRecipeView.setStepImage(null);
		createRecipeView.setStepImagePath(null);
	}

	public boolean checkStep() {
		if (createRecipeView.getInstruction().trim().equals("")
				|| createRecipeView.getInstruction().equals(CenterViewCreateRecipe.STEP_DEFAULT_TEXT)) {
			new CreationErrorDialog(window, "Invalid instruction", true, "La instrucción no es valida");
			return false;
		} else {
			if (createRecipeView.getAction().equals(RecipeStepActions.TIMER)) {
				Duration duration = parseDuration();
				if (duration.toNanos() == 0) {
					new CreationErrorDialog(window, "Invalid time", true, "El tiempo no puede ser 0");
					return false;
				}
			} else {
				if (createRecipeView.getValue() == 0) {
					new CreationErrorDialog(window, "Invalid value", true, "El valor no puede ser 0");
					return false;
				}
			}
		}
		return true;
	}

	private Duration parseDuration() {
		String fullDuration = createRecipeView.getTime();

		System.out.println(fullDuration);

		String[] params = fullDuration.split(" ");
		String[] time = params[3].split(":");

		try {
			time[0] = String.valueOf(Integer.parseInt(time[0]));
		} catch (NumberFormatException e) {
			System.out.println("String does not contain a valid number");
		}
		try {
			time[1] = String.valueOf(Integer.parseInt(time[1]));
		} catch (NumberFormatException e) {
			System.out.println("String does not contain a valid number");
		}
		try {
			time[2] = String.valueOf(Integer.parseInt(time[2]));
		} catch (NumberFormatException e) {
			System.out.println("String does not contain a valid number");
		}

		Duration duration = Duration.ofSeconds(Long.valueOf(time[2]));
		duration = duration.plusMinutes(Long.valueOf(time[1]));
		duration = duration.plusHours(Long.valueOf(time[0]));

		System.out.println(duration.toMinutes());

		return duration;
	}

	public void removeIngredient(Ingredient ingredient) {
		createRecipeView.getIngredientListModel().deleteElement(ingredient);
	}

	public void removeStep(RecipeStep step) {
		createRecipeView.getStepListModel().deleteElement(step);
	}
}
