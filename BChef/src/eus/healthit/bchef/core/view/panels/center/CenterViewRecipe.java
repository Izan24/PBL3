package eus.healthit.bchef.core.view.panels.center;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eus.healthit.bchef.core.models.Recipe;

public class CenterViewRecipe extends JPanel{
	
	Font textFont = new Font("Gill Sans MT", Font.PLAIN, 20);

	Recipe recipe;
	
	public CenterViewRecipe() {
		super();
		this.setBackground(Color.white);
		
		this.add(createContentPanel());
	}
	
	private Component createContentPanel() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(Color.white);
		scrollPane.setOpaque(true);

		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		
		scrollPane.setViewportView(createRecipeView());
		
		return scrollPane;
	}

	private Component createRecipeView() {
		JPanel dataPanel = new JPanel(new GridLayout(1,3,20,20));
		
		dataPanel.add(createTitlePanel());
		dataPanel.add(createIngredientsPanel());
		dataPanel.add(createElaborationPanel());
		
		return dataPanel;
	}

	private Component createTitlePanel() {
		return null;
	}

	private Component createIngredientsPanel() {
		return null;
	}

	private Component createElaborationPanel() {
		return null;
	}

	public static void setRecipe() {
		
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
		updateView();
		this.repaint();
	}

	private void updateView() {
		
	}

}
