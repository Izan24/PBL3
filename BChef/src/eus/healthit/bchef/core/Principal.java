package eus.healthit.bchef.core;

import javax.swing.JFrame;

import eus.healthit.bchef.core.controllers.CommandController;
import eus.healthit.bchef.core.controllers.implementations.KitchenController;
import eus.healthit.bchef.core.controllers.interfaces.IKitchenController;
import eus.healthit.bchef.core.enums.VoiceCommand;

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
		
		
		//StringBuilder str = new StringBuilder("bchef busca una receta con naranjas y  hasta tomates y tomates y tomates versus tomates");
		//StringBuilder str = new StringBuilder("bchef quiero hacer una receta de arroz con pollo");
		StringBuilder str = new StringBuilder("bchef quiero macarrones con tomate");
		//System.out.println(CommandController.parseCommand(str).toString());
		CommandController controlador = new CommandController();
		VoiceCommand command = CommandController.parseCommand(str);
		//System.out.println(command.toString());
		controlador.selectCommand(command, str.toString());		
		
		//Principal view = new Principal();
	}
}
