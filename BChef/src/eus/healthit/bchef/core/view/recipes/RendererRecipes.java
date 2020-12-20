package eus.healthit.bchef.core.view.recipes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
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

		//--------------------------------------------------------------------------
		//								The panel
		//--------------------------------------------------------------------------

		JPanel panelList = new JPanel(new BorderLayout());
		panelList.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		
		//--------------------------------------------------------------------------
		//								The Recipe name panel
		//--------------------------------------------------------------------------

		JPanel textPanel = new JPanel(new GridLayout());
		
		JLabel recipeName = new JLabel(value.getName());
		recipeName.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		recipeName.setHorizontalTextPosition(JLabel.LEFT);
		
		textPanel.add(recipeName);
		
		//--------------------------------------------------------------------------
		//								The Image panel
		//--------------------------------------------------------------------------

		JPanel panelImage = new JPanel(new GridLayout(1,1,20,20));
		panelImage.setBorder(BorderFactory.createEmptyBorder(10,10,10,30));
		JLabel image = new JLabel(value.getImage());
		panelImage.add(image);

		
		//--------------------------------------------------------------------------
		//								The Recipe info panel
		//--------------------------------------------------------------------------
		
		JPanel dataPanel = new JPanel(new BorderLayout(20, 20));
		JLabel text = new JLabel("Pollo a la bielorrusa es un pollo a la Bielorrusa");
		
		
		//--------------------------------------------------------------------------
		//								The bottom part of the panel
		//								(author + rating + button)
		//--------------------------------------------------------------------------

		JPanel southPanel = new JPanel(new GridLayout(1, 3, 5, 5));
		JLabel author = new JLabel("Author: " + value.getAuthor());
		southPanel.add(author);
		
		JLabel rating = new JLabel("Rating: " + value.getRating());
		southPanel.add(rating);
		
		JButton buttonRecipe = ButtonFactory.createRoundedButton("Ver receta", value.getName(), null, null, Color.cyan, 
					Color.black, new Font("Arial", Font.BOLD,12));
		southPanel.add(buttonRecipe);
		
		
		dataPanel.add(text, BorderLayout.CENTER);
		dataPanel.add(southPanel, BorderLayout.SOUTH);
		
		//--------------------------------------------------------------------------
		//								Add everithing to the main panel
		//--------------------------------------------------------------------------
		
		panelList.add(textPanel, BorderLayout.NORTH);
		panelList.add(panelImage, BorderLayout.WEST);
		panelList.add(dataPanel, BorderLayout.CENTER);

		return panelList;
	}

}
