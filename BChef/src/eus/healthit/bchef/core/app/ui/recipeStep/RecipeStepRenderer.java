package eus.healthit.bchef.core.app.ui.recipeStep;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.font.ImageGraphicAttribute;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import eus.healthit.bchef.core.enums.RecipeStepActions;
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
		JLabel actionIcon = new JLabel();
		JLabel action = new JLabel(value.getAction().toString());

		ingredientText.setFont(textFont);
		ingredientText.setHorizontalAlignment(JLabel.LEFT);

		action.setFont(textFont);
		action.setHorizontalAlignment(JLabel.RIGHT);

		ingredientPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20),
				BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY)));
		ingredientText.setOpaque(true);
		action.setOpaque(true);

		if (isSelected) {
			ingredientPanel.setBackground(selectedColor);
			ingredientText.setBackground(selectedColor);
			action.setBackground(selectedColor);
		} else {
			ingredientPanel.setBackground(bgColor);
			ingredientText.setBackground(bgColor);
			action.setBackground(bgColor);
		}

		if (value.getAction().equals(RecipeStepActions.OVEN)) {
			action.setIcon(new ImageIcon("resources/menuIcons/oven.png"));
		} else if (value.getAction().equals(RecipeStepActions.STOVE)) {
			action.setIcon(new ImageIcon("resources/menuIcons/bchef_icon.png"));
		} else if (value.getAction().equals(RecipeStepActions.TIMER)) {
			action.setIcon(new ImageIcon("resources/menuIcons/bchef_icon.png"));
		}

		ingredientPanel.add(ingredientText);
		ingredientPanel.add(action);

		return ingredientPanel;
	}

}
