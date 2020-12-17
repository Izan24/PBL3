package edu.healthit.bchef.core;

import javax.swing.JFrame;

import edu.healthit.bchef.core.controllers.implementations.KitchenController;
import edu.healthit.bchef.core.controllers.interfaces.IKitchenController;

public class Principal extends JFrame {

	View view;
	WindowActions actions;

	public Principal() {
		super("Ventanita uwu");
		
		actions = new WindowActions(this);
		view = new View();
		
		setWindow();
	}

	private void setWindow() {
		this.setSize(1250, 750);
		this.setLocation(300, 150);
		this.setResizable(true);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(view);

		this.setVisible(true);
	}

	public static void main(String[] args) {
		IKitchenController kitchenController = new KitchenController();
		Principal view = new Principal();
	}
}
