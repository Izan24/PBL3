package eus.healthit.bchef.core.controllers.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.view.panels.center.CenterPreviewRecipe;
import eus.healthit.bchef.core.view.panels.center.CenterViewCreateRecipe;

public class RecipeCreationController implements ActionListener, IRoundButtonListener {

	JFrame framePreview;
	CenterViewCreateRecipe createRecipeView;

	public RecipeCreationController(CenterViewCreateRecipe createRecipeView) {
		this.createRecipeView = createRecipeView;

		createPreviewWindow();
	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {
		case RecipeCreationControllerAC.ADD_INGREDIENT:
			System.out.println("ADD_INGREDIENT");
			createRecipeView.addIngredient();
			break;

		case RecipeCreationControllerAC.REMOVE_INGREDIENT:
			System.out.println("REMOVE_INGREDIENT");
			createRecipeView.removeIngredient();
			break;

		case RecipeCreationControllerAC.ADD_STEP:
			System.out.println("ADD_STEP");
			break;

		case RecipeCreationControllerAC.REMOVE_STEP:
			System.out.println("REMOVE_STEP:");
			break;

		case RecipeCreationControllerAC.CREATE_RECIPE:
			System.out.println(
					"Create recipe, hay que comrpobar que tenga toddos los campos puesto, para" + "eso haz un metodo:");
			break;

		case RecipeCreationControllerAC.PREVIEW:
			openPreviewWindow();
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case RecipeCreationControllerAC.ADD_INGREDIENT:
			System.out.println("ADD_INGREDIENT");
			createRecipeView.addIngredient();
			break;

		case RecipeCreationControllerAC.REMOVE_INGREDIENT:
			System.out.println("REMOVE_INGREDIENT");
			createRecipeView.removeIngredient();
			break;

		case RecipeCreationControllerAC.ADD_STEP:
			System.out.println("ADD_STEP");
			break;

		case RecipeCreationControllerAC.REMOVE_STEP:
			System.out.println("REMOVE_STEP:");
			break;

		case RecipeCreationControllerAC.CREATE_RECIPE:
			System.out.println(
					"Create recipe, hay que comrpobar que tenga toddos los campos puesto, para" + "eso haz un metodo:");
			break;

		case RecipeCreationControllerAC.PREVIEW:
			openPreviewWindow();
			break;
		}
	}

	private void openPreviewWindow() {
		CenterPreviewRecipe preview = new CenterPreviewRecipe();
		preview.setRecipe(createRecipe());

		framePreview.setContentPane(preview);
		framePreview.setLocation(400, 75);
		framePreview.setVisible(true);
	}

	public Recipe createRecipe() {
		String title;
		String description;

		/*
		 * PASA ESTO A UN METODO
		 */

		if (createRecipeView.getName().equals(CenterViewCreateRecipe.TITLE_DEFAULT_TEXT)) {
			title = "";
		} else {
			title = createRecipeView.getName();
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
