package eus.healthit.bchef.core.view.items;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import eus.healthit.bchef.core.controllers.view.ShopListButtonController;
import eus.healthit.bchef.core.models.Item;

public class ItemRenderer implements ListCellRenderer<Item> {

	ShopListButtonController controller;

	public ItemRenderer(ShopListButtonController controller) {
		this.controller = controller;
	}
	
	/*
	 * QUIERO QUE ME DETECTE SI LE DOY AL JLABEL DE LA IMAGENB PERO NO ME LO DETECTA, HE PROBADO A PONER UN BOTON Y NO VA
	 * Y TAMBIEN HE PROBADO A PONER UN MOUSELISTENER PERO NO VA (LO HE PUESTO DENTRO Y FUERA PERO NO LO DETECTA).
	 * 
	 * TENEMOS QUE PENSAR ALGO PARA HACER QUE FUNCIONES PORQUE ESTO NO VA
	 */

	@Override
	public Component getListCellRendererComponent(JList<? extends Item> list, Item value, int index, boolean isSelected,
			boolean cellHasFocus) {

		JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 20));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 250, 5, 350));

		JLabel boughtLabel = new JLabel();
		JLabel labelName = new JLabel(value.getName());
		labelName.setFont(new Font("Gill Sans MT", Font.PLAIN, 19));

		if (isSelected) {
			mainPanel.setBackground(Color.lightGray);
			boughtLabel.setBackground(Color.lightGray);
		} else {
			mainPanel.setBackground(Color.white);
			boughtLabel.setBackground(Color.white);
		}

		if (value.isBought()) {
			boughtLabel.setIcon(new ImageIcon("resources/menuIcons/bought_icon.png"));
		} else {
			boughtLabel.setIcon(new ImageIcon("resources/menuIcons/notBought_icon.png"));
		}

		mainPanel.add(boughtLabel);
		mainPanel.add(labelName);

		return mainPanel;
	}
}
