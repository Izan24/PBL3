package eus.healthit.bchef.core.view.panels.center;

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
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.recipes.RecipesList;
import eus.healthit.bchef.core.view.recipes.RendererRecipes;

public class CenterView extends JPanel {

	CenterViewList listView;
	CenterViewProfile profileView;
	CenterViewRecipe recipeView;
	CenterBchefView bchefView;
	CenterShopListView shopListView;
	

	public CenterView(User user) {
		super(new GridLayout(1,1));
		this.setSize(new Dimension(90, 20));

		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		initViews(user);
		
		setView(CenterControllerAC.HOME);
	}
	
	private void initViews(User user) {
		listView = new CenterViewList();
		profileView = new CenterViewProfile(user);
		recipeView = new CenterViewRecipe();
		bchefView = new CenterBchefView();
		shopListView = new CenterShopListView(user);
	}

	public void setView(String selection) {

		switch (selection) {
		case CenterControllerAC.HOME:
			this.removeAll();
			this.add(listView.getScrollPane());
			break;

		case CenterControllerAC.LIST:
			this.removeAll();
			this.add(shopListView);
			break;

		case CenterControllerAC.PROFILE:
			this.removeAll();
			this.add(profileView.getPanel());
			break;

		case CenterControllerAC.BCHEF:
			this.removeAll();
			this.add(bchefView);
			break;
		}
		
		this.revalidate();
		this.repaint();
	}
}
