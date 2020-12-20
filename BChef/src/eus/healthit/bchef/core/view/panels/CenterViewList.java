package eus.healthit.bchef.core.view.panels;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;

import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.view.recipes.RecipesList;
import eus.healthit.bchef.core.view.recipes.RendererRecipes;

public class CenterViewList extends JScrollPane {

	/*
	 * HAY QUE DARLE EL JLIST AL CONTROLADOR, DE MOMENTO EST� AQUI PORQUE NO ESTA EL
	 * CONTROLADOR
	 */

	JList<Recipe> recipes;
	RecipesList listModel;
	RendererRecipes renderer;

	public CenterViewList() {

		super(new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));

		this.setBorder(BorderFactory.createEmptyBorder());

		recipes = new JList<>();
		listModel = new RecipesList();
		renderer = new RendererRecipes();

		recipes.setModel(listModel);
		recipes.setCellRenderer(renderer);

		this.setViewportView(recipes);
	}

	public JScrollPane getScrollPane() {
		return this;
	}

}
