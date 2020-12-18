package eus.healthit.bchef.core.view.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.view.recipes.RecipesList;
import eus.healthit.bchef.core.view.recipes.RendererRecipes;

public class CenterView extends JPanel {

	/*
	 * HAY QUE DARLE EL JLIST AL CONTROLADOR, DE MOMENTO ESTÁ AQUI PORQUE NO ESTA EL
	 * CONTROLADOR
	 */

	JList<Recipe> recipes;
	RecipesList listModel;
	RendererRecipes renderer;

	public CenterView() {
		super(new FlowLayout(10, 10, 10));
		this.setSize(new Dimension(90, 20));

		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		addScrollPane();

	}

	private void addScrollPane() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		recipes = new JList<>();
		listModel = new RecipesList();
		renderer = new RendererRecipes();

		recipes.setModel(listModel);
		recipes.setCellRenderer(renderer);

		scrollPane.setViewportView(recipes);
		this.add(scrollPane);
	}
}
