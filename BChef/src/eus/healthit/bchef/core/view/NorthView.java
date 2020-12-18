package eus.healthit.bchef.core.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import eus.healthit.bchef.core.view.borders.RoundedBorder;
import eus.healthit.bchef.core.view.components.RoundedTextField;

public class NorthView extends JPanel {

	RoundedTextField searchBar;

	public NorthView() {
		super(new BorderLayout(50, 50));

		this.add(createSearchBar(), BorderLayout.CENTER);
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	}

	private JPanel createSearchBar() {

		JPanel panelSearch = new JPanel(new FlowLayout());
		panelSearch.setSize(new Dimension(2002, 2002));

		searchBar = new RoundedTextField("Busca una receta");
		searchBar.setToolTipText("Search");
		searchBar.setPreferredSize(new Dimension(550, 35));
		searchBar.setMargin(new Insets(4, 8, 4, 8));
		searchBar.setBorder(new RoundedBorder(30));

		panelSearch.add(searchBar);

		return panelSearch;
	}

}
