package eus.healthit.bchef.core.controllers.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.view.panels.center.CenterViewCreateRecipe;

public class RecipeCreationControler implements ActionListener {

	JFrame framePreview;
	CenterViewCreateRecipe createRecipeView;

	public RecipeCreationControler(CenterViewCreateRecipe createRecipeView) {
		this.createRecipeView = createRecipeView;

		createPreviewWindow();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case RecipeCreationControlerAC.ADD_INGREDIENT:
			System.out.println("ADD_INGREDIENT");
			createRecipeView.addIngredient();
			break;

		case RecipeCreationControlerAC.REMOVE_INGREDIENT:
			System.out.println("REMOVE_INGREDIENT");
			createRecipeView.removeIngredient();
			break;

		case RecipeCreationControlerAC.ADD_STEP:
			System.out.println("ADD_STEP");
			break;

		case RecipeCreationControlerAC.REMOVE_STEP:
			System.out.println("REMOVE_STEP:");
			break;

		case RecipeCreationControlerAC.CREATE_RECIPE:
			System.out.println(
					"Create recipe, hay que comrpobar que tenga toddos los campos puesto, para" + "eso haz un metodo:");
			break;

		case RecipeCreationControlerAC.PREVIEW:
			openPreviewWindow();
			break;
		}
	}

	private void openPreviewWindow() {
		framePreview.setVisible(true);
	}

	private void createRecipe() {
//		Recipe recipe = new Recipe(createRecipeView.getName(), createRecipeView.getAuthor(), 5,
//				createRecipeView.getIngredients(), createRecipeView.getSteps(), createRecipeView.getImage());
		System.out.println("Crear receta");
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
