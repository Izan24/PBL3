package eus.healthit.bchef.core.view.panels.center;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;

import eus.healthit.bchef.core.controllers.view.SelectionListener;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.view.recipes.RecipesList;
import eus.healthit.bchef.core.view.recipes.RendererRecipes;

public class CenterViewList extends JScrollPane {

	/*
	 * HAY QUE DARLE EL JLIST AL CONTROLADOR, DE MOMENTO ESTÁ AQUI PORQUE NO ESTA EL
	 * CONTROLADOR
	 */

	JList<Recipe> recipes;
	RecipesList listModel;
	RendererRecipes renderer;
	SelectionListener listener;

	public CenterViewList() {

		super(new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		this.setBackground(Color.white);
		this.setOpaque(true);
		listener = new SelectionListener();

		this.setBorder(BorderFactory.createEmptyBorder());

		recipes = new JList<>();
		listModel = new RecipesList();
		renderer = new RendererRecipes();

		recipes.setModel(listModel);
		recipes.setCellRenderer(renderer);
		recipes.addMouseListener(listener);

		this.setViewportView(recipes);
	}

	public JScrollPane getScrollPane() {
		return this;
	}

}
