package eus.healthit.bchef.core;


import eus.healthit.bchef.core.controllers.CommandController;
import eus.healthit.bchef.core.controllers.implementations.KitchenController;
import eus.healthit.bchef.core.controllers.interfaces.IKitchenController;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.WindowFrame;

public class Principal {

	WindowActions actions;
	WindowFrame windowFrame;

	public Principal() {
		windowFrame = new WindowFrame();
	}

	public static void main(String[] args) {
		IKitchenController kitchenController = new KitchenController();

		String str = "bchef hola ke tal io te doi el numero magico que es 197ekl 199 y 289 el ";

		System.out.println(CommandController.parseCommand(str).toString());
		
		Principal principal = new Principal();
	}
}
