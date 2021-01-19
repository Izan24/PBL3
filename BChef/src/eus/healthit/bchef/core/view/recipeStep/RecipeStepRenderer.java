package eus.healthit.bchef.core.view.recipeStep;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import eus.healthit.bchef.core.models.RecipeStep;

public class RecipeStepRenderer implements ListCellRenderer<RecipeStep> {

	@Override
	public Component getListCellRendererComponent(JList<? extends RecipeStep> list, RecipeStep value, int index,
			boolean isSelected, boolean cellHasFocus) {

		Font textFont = new Font("Segoe UI", Font.PLAIN, 15);
		Color bgColor = Color.white;
		Color selectedColor = new Color(221, 238, 255);

		JPanel ingredientPanel = new JPanel(new GridLayout(1, 2, 0, 0));
		JLabel ingredientText = new JLabel(value.getText());
		JLabel ingredient = new JLabel(value.getAction().toString());

		ingredientPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20),
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)));
		ingredientText.setOpaque(true);
		ingredient.setOpaque(true);

		if (isSelected) {
			ingredientPanel.setBackground(selectedColor);
			ingredientText.setBackground(selectedColor);
			ingredient.setBackground(selectedColor);
		} else {
			ingredientPanel.setBackground(bgColor);
			ingredientText.setBackground(bgColor);
			ingredient.setBackground(bgColor);
		}

		ingredientText.setFont(textFont);
		ingredientText.setHorizontalAlignment(JLabel.LEFT);

		ingredient.setFont(textFont);
		ingredient.setHorizontalAlignment(JLabel.RIGHT);

		ingredientPanel.add(ingredientText);
		ingredientPanel.add(ingredient);

		return ingredientPanel;
	}

}
