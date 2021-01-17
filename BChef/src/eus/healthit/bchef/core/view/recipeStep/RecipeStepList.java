package eus.healthit.bchef.core.view.recipeStep;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ImageIcon;

import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;

public class RecipeStepList extends AbstractListModel<RecipeStep> {

	List<RecipeStep> list;

	public RecipeStepList() {
		list = new ArrayList<>();
	}

	public void setList(List<RecipeStep> list) {
		this.list = list;
	}

	public void addElement(RecipeStep elemento) {
		if (elemento != null) {
			list.add(elemento);
			this.fireContentsChanged(this, 0, list.size() - 1);
		}
	}

	public void deleteElement(RecipeStep elemento) throws IndexOutOfBoundsException {
		if (elemento != null) {
			list.remove(list.indexOf(elemento));
			this.fireContentsChanged(this, 0, list.size() - 1);
		}
	}

	@Override
	public RecipeStep getElementAt(int index) {
		return list.get(index);
	}

	@Override
	public int getSize() {
		return list.size();
	}

	public List<RecipeStep> getList() {
		return list;
	}
}
