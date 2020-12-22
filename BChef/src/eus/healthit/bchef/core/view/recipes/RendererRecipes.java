package eus.healthit.bchef.core.view.recipes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.view.components.ButtonFactory;

public class RendererRecipes implements ListCellRenderer<Recipe> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Recipe> list, Recipe value, int index,
			boolean isSelected, boolean cellHasFocus) {

		JPanel panelList = new JPanel(new BorderLayout());
		JPanel textPanel = new JPanel(new GridLayout());
		JPanel panelImage = new JPanel(new GridLayout(1, 1, 20, 20));
		JPanel dataPanel = new JPanel(new BorderLayout(20, 20));
		JPanel starPanel = new JPanel(new FlowLayout());
		JPanel southPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		starPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		// --------------------------------------------------------------------------
		// The panel
		// --------------------------------------------------------------------------

		panelList.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		panelList.setOpaque(true);

		if (!isSelected) {
			panelList.setBackground(Color.white);
			textPanel.setBackground(Color.white);
			panelImage.setBackground(Color.white);
			dataPanel.setBackground(Color.white);
			southPanel.setBackground(Color.white);
			starPanel.setBackground(Color.white);
		} else {
			panelList.setBackground(new Color(232, 232, 232));
			textPanel.setBackground(new Color(232, 232, 232));
			panelImage.setBackground(new Color(232, 232, 232));
			dataPanel.setBackground(new Color(232, 232, 232));
			southPanel.setBackground(new Color(232, 232, 232));
			starPanel.setBackground(new Color(232, 232, 232));
		}

		// --------------------------------------------------------------------------
		// The Recipe name panel
		// --------------------------------------------------------------------------

		JLabel recipeName = new JLabel(value.getName());
		recipeName.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		recipeName.setHorizontalTextPosition(JLabel.LEFT);

		textPanel.add(recipeName);

		// --------------------------------------------------------------------------
		// The Image panel
		// --------------------------------------------------------------------------

		panelImage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 30));
		JLabel image = new JLabel(value.getImage());
		panelImage.add(image);

		// --------------------------------------------------------------------------
		// The Recipe info panel
		// --------------------------------------------------------------------------

		JLabel text = new JLabel("Pollo a la bielorrusa es un pollo a la Bielorrusa");

		// --------------------------------------------------------------------------
		// The bottom part of the panel
		// (author + rating + button)
		// --------------------------------------------------------------------------
				
		JLabel author = new JLabel("Author: " + value.getAuthor());
		southPanel.add(author);

		JLabel halfStar = new JLabel();
		halfStar.setIcon(new ImageIcon("resources/recipeIcons/halfStar.png"));

		int i = value.getRating();

		while ((i - 2) >= 0) {
			JLabel fullStar = new JLabel();
			fullStar.setIcon(new ImageIcon("resources/recipeIcons/fullStar.png"));
			starPanel.add(fullStar);
			i = i - 2;
		}
		if (i == 1) {
			starPanel.add(halfStar);
		}
		
		southPanel.add(starPanel);

		JButton buttonRecipe = ButtonFactory.createRoundedButton("Ver receta", value.getName(), null, null, Color.cyan,
				Color.black, new Font("Arial", Font.BOLD, 12));
		southPanel.add(buttonRecipe);

		dataPanel.add(text, BorderLayout.CENTER);
		dataPanel.add(southPanel, BorderLayout.SOUTH);

		// --------------------------------------------------------------------------
		// Add everything to the main panel
		// --------------------------------------------------------------------------

		panelList.add(textPanel, BorderLayout.NORTH);
		panelList.add(panelImage, BorderLayout.WEST);
		panelList.add(dataPanel, BorderLayout.CENTER);

		return panelList;
	}

}
