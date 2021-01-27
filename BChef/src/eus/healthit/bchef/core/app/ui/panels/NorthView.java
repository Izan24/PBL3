package eus.healthit.bchef.core.app.ui.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;
import eus.healthit.bchef.core.app.controllers.northView.NorthViewController;
import eus.healthit.bchef.core.app.controllers.northView.NorthViewControllerAC;
import eus.healthit.bchef.core.app.ui.borders.SearchBorder;
import eus.healthit.bchef.core.app.ui.components.RoundedTextField;

public class NorthView extends JPanel {

	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");

	RoundedTextField searchBar;
	NorthViewController controller;

	String DEFAULT_SEARCH_TEXT_STRING = rb.getString("north_search_text");

	public NorthView(CenterViewController centerController) {
		super(new BorderLayout(50, 50));
		this.setBackground(Color.white);
		this.setOpaque(true);

		controller = new NorthViewController(this, centerController);

		this.add(createSearchBar(), BorderLayout.CENTER);
		this.add(createIconPanel(), BorderLayout.WEST);
		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
				BorderFactory.createEmptyBorder(10, 20, 10, 20)));
	}

	private Component createIconPanel() {
		JPanel iconPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		iconPanel.setBackground(Color.white);
		JLabel iconLabel = new JLabel();
		iconLabel.setBackground(Color.white);
		JLabel title = new JLabel(" BChef");
		title.setFont(new Font("Segoe UI", Font.BOLD, 30));
		title.setBackground(Color.white);
		
		try {
			iconLabel.setIcon(new ImageIcon(ImageIO.read(new File("resources/menuIcons/bchef_icon.png"))));
		} catch (Exception e) {
		}
		iconPanel.add(iconLabel);
		iconPanel.add(title);
		
		return iconPanel;
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
