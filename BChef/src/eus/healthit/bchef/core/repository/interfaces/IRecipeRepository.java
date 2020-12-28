package eus.healthit.bchef.core.repository.interfaces;

import java.util.List;

import eus.healthit.bchef.core.models.Recipe;

public interface IRecipeRepository {
	public Recipe getRecipeById(String uuid);

	public List<Recipe> searchRecipes(String keyword);

	public boolean insertRecipe(Recipe recipe);

	public boolean deleteRecipe(Recipe recipe);

	public boolean deleteRecipe(String uuid);
}