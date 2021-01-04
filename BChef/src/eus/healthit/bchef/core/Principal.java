package eus.healthit.bchef.core;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eus.healthit.bchef.core.controllers.implementations.KitchenController;
import eus.healthit.bchef.core.controllers.interfaces.IKitchenController;
import eus.healthit.bchef.core.view.WindowFrame;
import eus.healthit.bchef.core.view.components.RoundTransparentButton;

public class Principal {

	WindowActions actions;
	WindowFrame windowFrame;

	public Principal() {
		windowFrame = new WindowFrame();
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(1280, 720));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(crearPaneles());
		frame.setVisible(true);
	}

	private Container crearPaneles() {
		JPanel panelbobo = new JPanel(new GridLayout(1, 2, 30, 30));
		panelbobo.setBorder(new EmptyBorder(50, 50, 50, 50));
		panelbobo.setBackground(new Color(21, 32, 43));

		JPanel panelPrincipal = new JPanel(new GridLayout(2, 1, 20, 200));
		panelPrincipal.setBackground(null);

		JButton boton1 = new JButton("Izanarcos");
		boton1.setUI(new RoundTransparentButton(boton1));
		boton1.setBackground(null);
		boton1.setFont(new Font("Roboto", Font.BOLD, 48));

		JButton boton2 = new JButton("Jorgearcos");
		boton2.setUI(new RoundTransparentButton(boton2));
		boton2.setBackground(null);
		boton2.setFont(new Font("Roboto", Font.BOLD, 48));
		boton2.setSize(new Dimension(40, 40));

		JButton boton3 = new JButton();

		panelPrincipal.add(boton1);
		panelPrincipal.add(boton2);

		panelbobo.add(panelPrincipal);
		panelbobo.add(new JPanel());

		return panelbobo;
	}

	public static void main(String[] args) {
		IKitchenController kitchenController = new KitchenController();
		Principal principal = new Principal();
		// QueryCon con = new QueryCon();
//		List<Ingredient> ings = new ArrayList<>();
//		ings.add(new Ingredient("Zanahoria", "Carb", "3 3 YUJU"));
//		List<RecipeStep> steps = new ArrayList<>();
//		steps.add(new RecipeStep(RecipeStepActions.FURNACE, 5,
//				"https://pbs.twimg.com/media/EqbU529XMAEl8xm?format=png&name=240x240", "xD", 0l));
//		RecipeRepository.insert(
//				new Recipe(UUID.randomUUID(), "Test", "Urko", 10, new Timestamp(2198312312412l), new Time(23123421424l),
//						ings, steps, "https://i1.wp.com/img.pixfans.com/2010/04/silbar.gif?resize=240%2C195"));

		// RecipeRepository.search("Test");
		// Recipe rep = RecipeRepository.get("2a961214-ae24-49ec-a350-f0582c8a1e9e");
		// RecipeRepository.delete(rep);
		// QueryCon.closeConn();

	}
}
