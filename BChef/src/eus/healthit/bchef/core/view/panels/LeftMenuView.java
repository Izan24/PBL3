package eus.healthit.bchef.core.view.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import eus.healthit.bchef.core.controllers.view.CenterControllerAC;
import eus.healthit.bchef.core.view.components.CustomButton;

public class LeftMenuView extends JPanel {

	CustomButton buttonHome, buttonProfile, buttonList, buttonChef, buttonCreateRecipe, buttonSettings;
	ActionListener listener;

	public LeftMenuView(ActionListener listener) {
		super(new GridLayout(1, 1, 10, 10));
		this.setBackground(Color.white);
		this.setOpaque(true);

		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY),
				BorderFactory.createEmptyBorder(10, 10, 10, 1)));

		this.listener = listener;
		initButtons();

		this.add(createMenuBar());
	}

	/*
	 * NOTA CREO QUE PODRIAMOS PONER LOS ICONOS EN UNA CLASE Y QUE SEAN ESTATICOS O
	 * QUE TENGA METODOS PARA PEDIRLOS POR SI ALGUN DIA QUEREMOS CAMBIARLOS NO TENER
	 * QUE IR BUSCANDO DONDE SE CREAN LOS BOTONES O POEDER ESCALARLO MAS FACIL
	 */
	private void initButtons() {

		Font font = new Font("Segoe UI", Font.PLAIN, 18);

		buttonHome = new CustomButton("Home", "resources\\menuIcons\\home_normal_64.png",
				"resources\\menuIcons\\home_active_64.png", new Color(15, 20, 25), new Color(29, 161, 242),
				new Color(0, 0, 0, 0), new Color(232, 245, 254), font);
		buttonHome.setPreferredSize(new Dimension(180, 40));
		buttonHome.addActionListener(listener);
		buttonHome.setActionCommand(CenterControllerAC.HOME);

		buttonProfile = new CustomButton("Perfil", "resources\\menuIcons\\profile_normal_642.png",
				"resources\\menuIcons\\profile_active_642.png", new Color(15, 20, 25), new Color(29, 161, 242),
				new Color(0, 0, 0, 0), new Color(232, 245, 254), font);
		buttonProfile.setPreferredSize(new Dimension(180, 40));
		buttonProfile.addActionListener(listener);
		buttonProfile.setActionCommand(CenterControllerAC.PROFILE);

		buttonList = new CustomButton("Lista", "resources\\menuIcons\\list_normal_64.png",
				"resources\\menuIcons\\list_active_64.png", new Color(15, 20, 25), new Color(29, 161, 242),
				new Color(0, 0, 0, 0), new Color(232, 245, 254), font);
		buttonList.setPreferredSize(new Dimension(180, 40));
		buttonList.addActionListener(listener);
		buttonList.setActionCommand(CenterControllerAC.LIST);

		buttonCreateRecipe = new CustomButton("Crear Receta", "resources\\menuIcons\\create_recipe_64.png",
				"resources\\menuIcons\\create_recipe_active_64.png", new Color(15, 20, 25), new Color(29, 161, 242),
				new Color(0, 0, 0, 0), new Color(232, 245, 254), font);
		buttonCreateRecipe.setPreferredSize(new Dimension(180, 40));
		buttonCreateRecipe.addActionListener(listener);
		buttonCreateRecipe.setActionCommand(CenterControllerAC.CREATE_RECIPE);

		buttonChef = new CustomButton("BChef", "resources\\menuIcons\\logo.png", "resources\\menuIcons\\logo.png",
				Color.white, Color.white, new Color(30, 170, 255), new Color(29, 154, 231),
				new Font("Segoe UI", Font.TYPE1_FONT, 18), CustomButton.CENTER_ALIGN);
		buttonChef.setPreferredSize(new Dimension(200, 40));
		buttonChef.addActionListener(listener);
		buttonChef.setActionCommand(CenterControllerAC.BCHEF);

		buttonSettings = new CustomButton("Ajustes", "resources\\menuIcons\\settings_normal_64.png",
				"resources\\menuIcons\\settings_active_64.png", new Color(15, 20, 25), new Color(29, 161, 242),
				new Color(0, 0, 0, 0), new Color(232, 245, 254), font);
		buttonSettings.setPreferredSize(new Dimension(200, 40));
		buttonSettings.addActionListener(listener);
		buttonSettings.setActionCommand(CenterControllerAC.SETTINGS);
	}

	private JPanel createMenuBar() {
		JPanel leftMenu = new JPanel();
		leftMenu.setLayout(new BoxLayout(leftMenu, BoxLayout.Y_AXIS));
		leftMenu.setBackground(Color.white);
		// leftMenu.setSize(new Dimension(4000, 800));

		leftMenu.add(Box.createVerticalStrut(30));
		leftMenu.add(buttonHome);
		leftMenu.add(Box.createVerticalStrut(30));
		leftMenu.add(buttonCreateRecipe);
		leftMenu.add(Box.createVerticalStrut(30));
		leftMenu.add(buttonList);
		leftMenu.add(Box.createVerticalStrut(30));
		leftMenu.add(buttonProfile);
		leftMenu.add(Box.createVerticalStrut(30));
		leftMenu.add(buttonChef);
		leftMenu.add(Box.createVerticalGlue());
		leftMenu.add(buttonSettings);

		return leftMenu;
	}
}
