package eus.healthit.bchef.core.controllers.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.view.WindowFrame;
import eus.healthit.bchef.core.view.dialogs.CreationErrorDialog;
import eus.healthit.bchef.core.view.dialogs.FileChooser;
import eus.healthit.bchef.core.view.panels.center.CenterPreviewRecipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewCreateRecipe;

public class RecipeCreationController implements IRoundButtonListener, ActionListener {

	JFrame framePreview;
	CenterViewCreateRecipe createRecipeView;
	WindowFrame window;

	public RecipeCreationController(CenterViewCreateRecipe createRecipeView, WindowFrame window) {
		this.createRecipeView = createRecipeView;
		this.window = window;

		createPreviewWindow();
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
			System.out.println("ADD IMAGE");
			FileChooser file = new FileChooser();
			try {
				try {
					createRecipeView.setImage(ImageIO.read(file.getSelectedFile()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (NullPointerException e) {
			}
			break;

		case RecipeCreationControllerAC.ADD_INGREDIENT:
			createRecipeView.addIngredient();
			createRecipeView.resetIngredientFields();
			break;

		case RecipeCreationControllerAC.REMOVE_INGREDIENT:
			createRecipeView.removeIngredient();
			break;

		case RecipeCreationControllerAC.ADD_STEP:
			System.out.println("ADD_STEP");
			createRecipeView.resetStepFIelds();
			break;

		case RecipeCreationControllerAC.REMOVE_STEP:
			System.out.println("REMOVE_STEP:");
			break;

		case RecipeCreationControllerAC.CREATE_RECIPE:
//			System.out.println(
//					"Create recipe, hay que comrpobar que tenga toddos los campos puesto, para" + "eso haz un metodo:");
			System.out.println(createRecipeView.getImage());
			if (recipeValid()) {

			}
			break;

		case RecipeCreationControllerAC.PREVIEW:
			openPreviewWindow();
			break;
		}
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

		Recipe recipe = new Recipe(title, createRecipeView.getAuthor(), 2, description, 5,
				createRecipeView.getIngredients(), createRecipeView.getSteps(), createRecipeView.getImage());
		System.out.println("Crear receta");
		return (recipe);
//		return new Recipe("Prueba Prev", "Izan owo", 6, null, null, null);
	}

	private void createPreviewWindow() {
		framePreview = new JFrame("Preview de receta");
		framePreview.setSize(1250, 750);
		framePreview.setLocation(400, 75);
		framePreview.setResizable(true);
		framePreview.setBackground(Color.white);

		framePreview.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void addIngredient(String name, String quantity) {

		if (!name.equals(CenterViewCreateRecipe.INGREDIENT_DEFAULT_TEXT)
				&& !quantity.equals(CenterViewCreateRecipe.QUANTITY_DEFAULT_TEXT)) {
			Ingredient ingredient = new Ingredient(name, "uwu", quantity);
			createRecipeView.getIngredientListModel().addElement(ingredient);
		}

	}

	public void addStep(String text, int value, RecipeStepActions action, String imageURL, int num) {
		RecipeStep step = new RecipeStep(action, value, imageURL, text, num);

		createRecipeView.getStepListModel().addElement(step);
	}

	public void removeIngredient(Ingredient ingredient) {
		createRecipeView.getIngredientListModel().deleteElement(ingredient);
	}

	public void removeStep(RecipeStep step) {
		createRecipeView.getStepListModel().deleteElement(step);
	}
}
