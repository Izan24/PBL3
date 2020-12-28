package eus.healthit.bchef.core.repository.interfaces;

import java.util.List;
import java.util.UUID;

import eus.healthit.bchef.core.models.Ingredient;

public interface IIngredientRepository {
	public Ingredient getIngredientById(UUID uuid);

	public List<Ingredient> searchIngredients(String keyword);

	public boolean insertIngredient(Ingredient recipe);

	public boolean deleteIngredient(Ingredient recipe);

	public boolean deleteIngredient(UUID uuid);

}
