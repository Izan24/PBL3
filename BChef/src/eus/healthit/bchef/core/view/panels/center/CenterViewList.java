package eus.healthit.bchef.core.view.panels.center;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;

import eus.healthit.bchef.core.controllers.interfaces.IClickable;
import eus.healthit.bchef.core.controllers.view.CenterListController;
import eus.healthit.bchef.core.controllers.view.CenterViewController;
import eus.healthit.bchef.core.controllers.view.DoubleClickListener;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.view.recipes.RecipesList;
import eus.healthit.bchef.core.view.recipes.RendererRecipes;

public class CenterViewList extends JScrollPane implements IClickable {

	/*
	 * HAY QUE DARLE EL JLIST AL CONTROLADOR, DE MOMENTO ESTÁ AQUI PORQUE NO ESTA EL
	 * CONTROLADOR
	 */

	CenterViewController centerController;
	CenterListController listController;

	JList<Recipe> recipes;
	RecipesList listModel;
	RendererRecipes renderer;
	DoubleClickListener listener;

	public CenterViewList(CenterViewController centerController) {

		super(new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		this.setBackground(Color.white);
		this.setOpaque(true);

		this.centerController = centerController;

		listener = new DoubleClickListener(this);
		listController = new CenterListController(this);

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

	public RecipesList getListModel() {
		return listModel;
	}

	@Override
	public void clicked() {
		centerController.setRecipeView(recipes.getSelectedValue());
	}

}
