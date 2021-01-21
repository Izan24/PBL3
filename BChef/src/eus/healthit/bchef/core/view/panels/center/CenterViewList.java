package eus.healthit.bchef.core.view.panels.center;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JScrollPane;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.controllers.interfaces.IClickable;
import eus.healthit.bchef.core.controllers.view.CenterListController;
import eus.healthit.bchef.core.controllers.view.CenterViewController;
import eus.healthit.bchef.core.controllers.view.DoubleClickListener;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.view.recipes.RecipesList;
import eus.healthit.bchef.core.view.recipes.RendererRecipes;
import eus.healthit.bchef.core.view.components.CustomScrollbarUI;

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

		super(new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
		this.setBackground(Color.white);
		this.setOpaque(true);

		this.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		this.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		this.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		this.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		this.centerController = centerController;

		listener = new DoubleClickListener(this);
		listController = new CenterListController(this);

		this.setBorder(BorderFactory.createEmptyBorder());

		initJList();

		this.setViewportView(recipes);
	}

	private void initJList() {
		recipes = new JList<>();
		listModel = new RecipesList();
		listModel.setList(JSONCalls.getPage(0));
		renderer = new RendererRecipes();

		recipes.setModel(listModel);
		recipes.setCellRenderer(renderer);
		recipes.addMouseListener(listener);
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
