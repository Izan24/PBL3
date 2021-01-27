package eus.healthit.bchef.core;

import eus.healthit.bchef.core.app.ui.WindowFrame;
import eus.healthit.bchef.core.assistant.BChefController;

public class Principal {

	WindowFrame windowFrame;

	public Principal() {
		windowFrame = new WindowFrame();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException {
		BChefController.getInstance();
		Principal principal = new Principal();
	}
}
