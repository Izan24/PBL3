package eus.healthit.bchef.core.view.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import eus.healthit.bchef.core.controllers.view.CenterControllerAC;
import eus.healthit.bchef.core.view.components.ButtonFactory;

public class LeftMenuView extends JPanel {

	JButton buttonHome, buttonProfile, buttonList, buttonChef;
	ActionListener listener;

	public LeftMenuView(ActionListener listener) {
		super(new FlowLayout(10, 10, 10));
		this.setBackground(Color.white);
		this.setOpaque(true);

		this.setSize(new Dimension(90, 20));
		

		this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY),
				BorderFactory.createEmptyBorder(65, 100, 100, 20)));

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

		Font font = new Font("Arial", Font.BOLD, 12);

		buttonHome = ButtonFactory.createRoundedButton("Home", CenterControllerAC.HOME, listener,
				new ImageIcon("resources/menuIcons/home_icon.png"), Color.white, Color.black, font);

		buttonProfile = ButtonFactory.createRoundedButton("Profile", CenterControllerAC.PROFILE, listener,
				new ImageIcon("resources/menuIcons/profile_icon.png"), Color.white, Color.black, font);

		buttonList = ButtonFactory.createRoundedButton("Shopping List", CenterControllerAC.LIST, listener,
				new ImageIcon("resources/menuIcons/list_icon.png"), Color.white, Color.black, font);

		buttonChef = ButtonFactory.createRoundedButton("B-Chef", CenterControllerAC.BCHEF, listener,
				new ImageIcon("resources/menuIcons/bchef_icon.png"), Color.cyan, Color.white, font);
	}

	private JPanel createMenuBar() {
		JPanel leftMenu = new JPanel(new GridLayout(4, 1, 10, 10));
		leftMenu.setSize(new Dimension(90, 20));

		leftMenu.add(buttonHome);
		leftMenu.add(buttonProfile);
		leftMenu.add(buttonList);
		leftMenu.add(buttonChef);

		this.add(leftMenu, BorderLayout.NORTH);

		return leftMenu;
	}
}
