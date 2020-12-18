package eus.healthit.bchef.core.view.recipes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import eus.healthit.bchef.core.models.Recipe;

public class RendererRecipes implements ListCellRenderer<Recipe> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Recipe> list, Recipe value, int index,
			boolean isSelected, boolean cellHasFocus) {

		JPanel panelList = new JPanel(new BorderLayout());
		panelList.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		JLabel recipeName = new JLabel(value.getName());
		recipeName.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		recipeName.setHorizontalTextPosition(JLabel.LEFT);

		JLabel image = new JLabel(value.getImage());

		JLabel text = new JLabel("Pollo a la bielorrusa es un pollo a la Bielorrusa");

		panelList.add(recipeName, BorderLayout.NORTH);
		panelList.add(image, BorderLayout.WEST);
		panelList.add(text, BorderLayout.CENTER);

		return panelList;
	}

}
