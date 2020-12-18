package eus.healthit.bchef.core.view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.view.recipes.RecipesList;

public class CenterView extends JPanel {
	
	/*
	 * HAY QUE DARLE EL JLIST AL CONTROLADOR, DE MOMENTO ESTÁ AQUI PORQUE NO ESTA EL CONTROLADOR
	 */
	
	JList<Recipe> recipes;
	RecipesList listModel;

	public CenterView() {
		super(new FlowLayout(10, 10, 10));
		this.setSize(new Dimension(90, 20));

		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY),
				BorderFactory.createEmptyBorder(65, 100, 100, 20)));
		
		addScrollPane();

	}

	private void addScrollPane() {
		JScrollPane scrollPane = new JScrollPane();
		
		recipes = new JList<>();
		listModel = new RecipesList();
		recipes.setModel(listModel);
		
		scrollPane.setViewportView(recipes);
		this.add(scrollPane);
	}
}
