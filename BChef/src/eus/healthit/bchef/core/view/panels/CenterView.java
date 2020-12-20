package eus.healthit.bchef.core.view.panels;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.text.html.ListView;

import eus.healthit.bchef.core.controllers.CenterControllerAC;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.view.recipes.RecipesList;
import eus.healthit.bchef.core.view.recipes.RendererRecipes;

public class CenterView extends JPanel {

	CenterViewList listView;
	CenterViewProfile profileView;
	CenterViewRecipe recipeView;

	public CenterView() {
		super(new GridLayout());
		this.setSize(new Dimension(90, 20));

		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		listView = new CenterViewList();
		profileView = new CenterViewProfile();
		recipeView = new CenterViewRecipe();

		setView(CenterControllerAC.HOME);
	}

	public void setView(String selection) {

		switch (selection) {

		case CenterControllerAC.HOME:
			this.removeAll();
			this.add(listView.getScrollPane());
			break;

		case CenterControllerAC.LIST:
			this.removeAll();
			// this.add();
			break;

		case CenterControllerAC.PROFILE:
			this.removeAll();
			// this.add();
			break;

		case CenterControllerAC.BCHEF:
			this.removeAll();
			// this.add();
			break;
		}
		this.repaint();
	}
}
