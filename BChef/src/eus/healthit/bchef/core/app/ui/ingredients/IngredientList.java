package eus.healthit.bchef.core.app.ui.ingredients;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import eus.healthit.bchef.core.models.Ingredient;

public class IngredientList extends AbstractListModel<Ingredient> {

	private static final long serialVersionUID = -8570767743467757165L;
	List<Ingredient> list;

	public IngredientList() {
		list = new ArrayList<>();
	}

	public void setList(List<Ingredient> list) {
		this.list = list;
	}

	public void addElement(Ingredient elemento) {
		if (elemento != null) {
			list.add(elemento);
			this.fireContentsChanged(this, 0, list.size() - 1);
		}
	}

	public void deleteElement(Ingredient elemento) throws IndexOutOfBoundsException {
		if (elemento != null) {
			list.remove(list.indexOf(elemento));
			this.fireContentsChanged(this, 0, list.size() - 1);
		}
	}

	@Override
	public Ingredient getElementAt(int index) {
		return list.get(index);
	}

	@Override
	public int getSize() {
		return list.size();
	}

	public List<Ingredient> getList() {
		return list;
	}

	public void removeAllList() {
//		list.removeAll(list);
		list = new ArrayList<>();
	}
}
