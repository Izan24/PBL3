package eus.healthit.bchef.core;

import eus.healthit.bchef.core.controllers.BChefController;
import eus.healthit.bchef.core.view.WindowFrame;

public class Principal {

	WindowFrame windowFrame;

	public Principal() {
		windowFrame = new WindowFrame();
	}

	public static void main(String[] args) throws InterruptedException {
		BChefController.getInstance();
		Principal principal = new Principal();
	}
}
