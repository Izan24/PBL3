package eus.healthit.bchef.core.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import eus.healthit.bchef.core.controllers.view.NorthViewController;
import eus.healthit.bchef.core.controllers.view.NorthViewControllerAC;
import eus.healthit.bchef.core.view.borders.SearchBorder;
import eus.healthit.bchef.core.view.components.RoundedTextField;

public class NorthView extends JPanel {

	RoundedTextField searchBar;
	NorthViewController controller;

	public static final String DEFAULT_SEARCH_TEXT_STRING = "Buscar una receta";

	public NorthView() {
		super(new BorderLayout(50, 50));
		this.setBackground(Color.white);
		this.setOpaque(true);

		controller = new NorthViewController(this);

		this.add(createSearchBar(), BorderLayout.CENTER);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
				BorderFactory.createEmptyBorder(10, 20, 10, 20)));
	}

	private JPanel createSearchBar() {

		JPanel panelSearch = new JPanel(new FlowLayout());
		panelSearch.setBackground(Color.white);
		panelSearch.setOpaque(true);
		panelSearch.setSize(new Dimension(2002, 2002));

		searchBar = new RoundedTextField(DEFAULT_SEARCH_TEXT_STRING);
		// searchBar.setToolTipText("Search");
		searchBar.setPreferredSize(new Dimension(550, 35));
		searchBar.addActionListener(controller);
		searchBar.setActionCommand(NorthViewControllerAC.SEARCH);
		searchBar.setBorder(new SearchBorder(30, new Color(200, 200, 200), true));

		panelSearch.add(searchBar);

		return panelSearch;
	}

	public String getText() {
		return searchBar.getText();
	}

}
