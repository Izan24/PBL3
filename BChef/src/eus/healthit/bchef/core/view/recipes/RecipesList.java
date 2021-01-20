package eus.healthit.bchef.core.view.recipes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;

import eus.healthit.bchef.core.models.Recipe;

public class RecipesList extends AbstractListModel<Recipe> {

	List<Recipe> list;

	public RecipesList() {
		list = new ArrayList<>();
		//initList();
	}

	private void initList() {
		try {
			list.add(new Recipe(UUID.randomUUID(), "Prueba", "Rkolay", 2, "REceta de prueba woo", 10, null, null, null,
					null, ImageIO.read(new File("resources/recipeIcons/recetaBonita.jpg"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setList(List<Recipe> list) {
		System.out.println("seteando la lista:");

		for (Recipe r : list) {
			System.out.println(r.toString());
		}
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
