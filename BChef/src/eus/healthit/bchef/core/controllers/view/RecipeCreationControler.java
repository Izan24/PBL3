package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.enums.RecipeStepActions;
import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.view.panels.center.CenterViewCreateRecipe;

public class RecipeCreationControler implements ActionListener {

	CenterViewCreateRecipe createRecipeView;

	public RecipeCreationControler(CenterViewCreateRecipe createRecipeView) {
		this.createRecipeView = createRecipeView;
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
		}
	}

	public void addIngredient(String name, String quantity) {
		
		if (!name.equals(CenterViewCreateRecipe.INGREDIENT_DEFAULT_TEXT) && !quantity.equals(CenterViewCreateRecipe.QUANTITY_DEFAULT_TEXT)) {
			Ingredient ingredient = new Ingredient(name, "uwu", quantity );			
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
