package eus.healthit.bchef.core.app.ui.recipes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import eus.healthit.bchef.core.models.Recipe;

public class RecipesList extends AbstractListModel<Recipe> {

	private static final long serialVersionUID = -3331148318284416695L;
	List<Recipe> list;

	public RecipesList() {
		list = new ArrayList<>();
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

	public List<Recipe> getList() {
		return list;
	}
}
