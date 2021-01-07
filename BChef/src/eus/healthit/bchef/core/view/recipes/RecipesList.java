package eus.healthit.bchef.core.view.recipes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.repository.implementations.RecipeRepository;

public class RecipesList extends AbstractListModel<Recipe> {

	List<Recipe> list;

	public RecipesList() {
		list = new ArrayList<>();
		initList();
	}


	public void setList(List<Recipe> list) {
		this.list = list;
	}

	private void initList() {
		list = RecipeRepository.getN(10);
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
