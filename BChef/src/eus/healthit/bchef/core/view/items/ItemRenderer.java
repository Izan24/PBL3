package eus.healthit.bchef.core.view.items;

import java.awt.BorderLayout;
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

	@Override
	public Component getListCellRendererComponent(JList<? extends Item> list, Item value, int index, boolean isSelected,
			boolean cellHasFocus) {

		JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 150, 5, 200));

		JLabel boughtLabel = new JLabel();
		JLabel labelName = new JLabel(value.getName());
		labelName.setFont(new Font("Segoe UI", Font.PLAIN, 24));
		labelName.setForeground(Color.gray);
		labelName.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));

		if (isSelected) {
			mainPanel.setBackground(new Color(225, 252, 254));
			boughtLabel.setBackground(new Color(225, 252, 254));
		} else {
			mainPanel.setBackground(Color.white);
			boughtLabel.setBackground(Color.white);
		}

		if (value.isBought()) {
			boughtLabel.setIcon(new ImageIcon("resources/menuIcons/bought_icon.png"));
		} else {
			boughtLabel.setIcon(new ImageIcon("resources/menuIcons/notBought_icon.png"));
		}

		mainPanel.add(boughtLabel, BorderLayout.WEST);
		mainPanel.add(labelName, BorderLayout.CENTER);

		return mainPanel;
	}
}
