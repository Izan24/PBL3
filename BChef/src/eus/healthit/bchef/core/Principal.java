package eus.healthit.bchef.core;

import eus.healthit.bchef.core.stt.Test;
import eus.healthit.bchef.core.view.WindowFrame;

public class Principal {

	WindowActions actions;
	WindowFrame windowFrame;
	Test test;

	public Principal() {
//		windowFrame = new WindowFrame();
		test = new Test();
	}
	
	public void listen() {
		test.startListening();
	}


	public static void main(String[] args) {
//		IKitchenController kitchenController = new KitchenController();
		Principal principal = new Principal();
		
		while (true) {
			principal.listen();
		}
	}
}
