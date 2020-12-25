package eus.healthit.bchef.core.view.recipes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;

import eus.healthit.bchef.core.models.Recipe;

public class RecipesList extends AbstractListModel<Recipe> {

	List<Recipe> list;

	public RecipesList() {
		list = new ArrayList<>();
		initList();
	}

	private void initList() {

		for (int i = 0; i < 20; i++) {
			list.add(new Recipe("Pollo Sentao " + i, String.valueOf(i), (int) (Math.random() * 10) + 1, null, null,
					null, null, new ImageIcon("resources/recipeIcons/pollo-sentao.jpg")));
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
