package edu.healthit.bchef.core;

import javax.swing.JFrame;

import edu.healthit.bchef.core.controllers.CommandController;
import edu.healthit.bchef.core.controllers.implementations.KitchenController;
import edu.healthit.bchef.core.controllers.interfaces.IKitchenController;
import edu.healthit.bchef.core.enums.VoiceCommand;

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

		//TODO: Funcion de cerrar con guardado (windowlistener)
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(view);

		this.setVisible(true);
	}

	public static void main(String[] args) {
		IKitchenController kitchenController = new KitchenController();
		
		String str = "bchef hola ke tal io te doi el numero magico que es 197ekl 199 y 289 el ";
		
		System.out.println(CommandController.parseCommand(str).toString());
		
		//Principal view = new Principal();
	}
}
