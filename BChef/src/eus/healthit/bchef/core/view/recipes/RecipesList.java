package eus.healthit.bchef.core.view.recipes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import eus.healthit.bchef.core.models.Ingredient;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.repository.implementations.RecipeRepository;

public class RecipesList extends AbstractListModel<Recipe> {

	List<Recipe> list;

	public RecipesList() {
		list = new ArrayList<>();
		initList();
	}

	private void initList() {

		list = RecipeRepository.getN(10);

		for (int i = 0; i < 2; i++) {
			List<Ingredient> ingredients = new ArrayList<>();
			ingredients.add(new Ingredient("Pan", "2 barras"));
			ingredients.add(new Ingredient("Cebolla", "2"));
			ingredients.add(new Ingredient("Leche", "2 Litros"));

			List<RecipeStep> steps = new ArrayList<>();
			steps.add(new RecipeStep("Prepara un pollo", 12, null));
			steps.add(new RecipeStep("Metelo al horno durante 30 minutos mm que rico", 12, null));
			steps.add(new RecipeStep("Echale mayonesa al pollo", 12, null));
			steps.add(new RecipeStep("Llevalo a bielorusia", 12, null));
			steps.add(new RecipeStep("Comete el Pollo Lesgogogo", 12, null));

			list.add(new Recipe("Pollo a la bielorrusia " + i, "Izan " + i, (int) (Math.random() * 10) + 1, null, null,
					ingredients, steps, new ImageIcon("resources/recipeIcons/pollo-asado.jpg")));
		}
	}

	public void setList(List<Recipe> list) {
		this.list = list;
	}

	public void addElement(Recipe elemento) {
		if (elemento != null) {
			list.add(elemento);
			this.fireContentsChanged(this, 0, list.size() - 1);
		}
	}

	public void deleteElement(Recipe elemento) throws IndexOutOfBoundsException {
		if (elemento != null) {
			list.remove(list.indexOf(elemento));
			this.fireContentsChanged(this, 0, list.size() - 1);
		}
	}

	@Override
	public Recipe getElementAt(int index) {
		return list.get(index);
	}

	@Override
	public int getSize() {
		return list.size();
	}
}
