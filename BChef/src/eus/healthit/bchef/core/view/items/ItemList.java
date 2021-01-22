package eus.healthit.bchef.core.view.items;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import eus.healthit.bchef.core.models.Item;

public class ItemList extends AbstractListModel<Item>{

	List<Item> list;

	
	/*
	 * EN EL CONSTRUCTOR LE TENEMOS QUE PASAR LA LISTA DEL USUARIO O SI ES NULL CREAR UNA Y AÑADIRSELA AL USUARIO Y A ESTO
	 */
	public ItemList() {
		list = new ArrayList<>();
//		initList();
	}

//	private void initList() {
//		list.add(new Item("Azucar"));
//		list.add(new Item("Pan"));
//		list.add(new Item("Sal"));
//		list.add(new Item("Porvoraaah"));
//		list.add(new Item("Arroz"));
//	}
	
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
