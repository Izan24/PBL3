package eus.healthit.bchef.core.view.items;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import eus.healthit.bchef.core.models.Item;

public class ItemRenderer implements ListCellRenderer<Item> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Item> list, Item value, int index, boolean isSelected,
			boolean cellHasFocus) {

		JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 20));
		JLabel boughtLabel = new JLabel();
		JLabel labelName = new JLabel(value.getName());
		labelName.setFont(new Font("Gill Sans MT", Font.PLAIN, 20));

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
