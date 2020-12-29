package eus.healthit.bchef.core.view.panels.center;

import javax.swing.JPanel;

import eus.healthit.bchef.core.models.Recipe;

public class CenterViewRecipe extends JPanel{
	
	Recipe recipe;
	
	public CenterViewRecipe() {
		super();
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
