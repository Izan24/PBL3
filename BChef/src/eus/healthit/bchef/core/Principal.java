package eus.healthit.bchef.core;

import eus.healthit.bchef.core.view.WindowFrame;

public class Principal {

	WindowFrame windowFrame;

	public Principal() {
		windowFrame = new WindowFrame();
	}

	public static void main(String[] args) throws InterruptedException {
		Principal principal = new Principal();
	}
}
