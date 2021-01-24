package eus.healthit.bchef.core.app.ui.recipes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import eus.healthit.bchef.core.models.Recipe;

public class RendererRecipes implements ListCellRenderer<Recipe> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Recipe> list, Recipe value, int index,
			boolean isSelected, boolean cellHasFocus) {

//		Color bgColor = new Color(244, 249, 255);
		Color bgColor = Color.white;

		JPanel panelList = new JPanel(new BorderLayout());
		JPanel textPanel = new JPanel(new GridLayout());
		JPanel panelImage = new JPanel(new GridLayout(1, 1, 20, 20));
		JPanel dataPanel = new JPanel(new BorderLayout(20, 20));
		JPanel starPanel = new JPanel(new FlowLayout());
		JPanel southPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		starPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		// --------------------------------------------------------------------------
		// The panel
		// --------------------------------------------------------------------------

		panelList.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		panelList.setOpaque(true);

		if (!isSelected) {
			panelList.setBackground(bgColor);
			textPanel.setBackground(bgColor);
			panelImage.setBackground(bgColor);
			dataPanel.setBackground(bgColor);
			southPanel.setBackground(bgColor);
			starPanel.setBackground(bgColor);
		} else {
			panelList.setBackground(new Color(225, 252, 254));
			textPanel.setBackground(new Color(225, 252, 254));
			panelImage.setBackground(new Color(225, 252, 254));
			dataPanel.setBackground(new Color(225, 252, 254));
			southPanel.setBackground(new Color(225, 252, 254));
			starPanel.setBackground(new Color(225, 252, 254));
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
		JLabel image;

		try {
			image = new JLabel(new ImageIcon(value.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		} catch (Exception e) {
			image = new JLabel();
		}

		panelImage.add(image);

		// --------------------------------------------------------------------------
		// The Recipe info panel
		// --------------------------------------------------------------------------

		String description = value.getDescription();
		if (description.length() > 50) {
			description = value.getDescription().substring(0, 100) + "...";
		}
		JLabel text = new JLabel(description);

		// --------------------------------------------------------------------------
		// The bottom part of the panel
		// (author + rating + button)
		// --------------------------------------------------------------------------

		JLabel author = new JLabel("Author: " + value.getAuthor());
		southPanel.add(author);

		JLabel halfStar = new JLabel();
		halfStar.setIcon(new ImageIcon("resources/recipeIcons/halfStar.png"));

		int i = value.getRating();
		int emptyStars = Math.round(10 - i);

		while ((i - 2) >= 0) {
			JLabel fullStar = new JLabel();
			fullStar.setIcon(new ImageIcon("resources/recipeIcons/fullStar.png"));
			starPanel.add(fullStar);
			i = i - 2;
		}
		if (i == 1) {
			starPanel.add(halfStar);
		}
		if (emptyStars != 0) {
			while (emptyStars - 2 >= 0) {
				JLabel emptyStar = new JLabel();
				emptyStar.setIcon(new ImageIcon("resources/recipeIcons/emptyStar.png"));
				emptyStar.setHorizontalAlignment(JLabel.CENTER);

				starPanel.add(emptyStar);
				emptyStars = emptyStars - 2;
			}
		}

		southPanel.add(starPanel);

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
