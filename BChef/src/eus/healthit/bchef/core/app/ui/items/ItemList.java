package eus.healthit.bchef.core.app.ui.items;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import eus.healthit.bchef.core.models.Item;

public class ItemList extends AbstractListModel<Item> {

	private static final long serialVersionUID = 1132372113184744871L;
	List<Item> list;

	public ItemList() {
		list = new ArrayList<>();
	}

	public void setList(List<Item> list) {
		this.list = list;
	}

	public void addElement(Item elemento) {
		if (elemento != null) {
			list.add(elemento);
			this.fireContentsChanged(this, 0, list.size() - 1);
		}
	}

	public void deleteElement(Item elemento) throws IndexOutOfBoundsException {
		if (elemento != null) {
			list.remove(list.indexOf(elemento));
			this.fireContentsChanged(this, 0, list.size() - 1);
		}
	}

	@Override
	public Item getElementAt(int index) {
		return list.get(index);
	}

	@Override
	public int getSize() {
		return list.size();
	}
}
