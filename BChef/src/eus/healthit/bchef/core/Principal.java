package eus.healthit.bchef.core;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import eus.healthit.bchef.core.controllers.implementations.KitchenController;
import eus.healthit.bchef.core.controllers.interfaces.IKitchenController;
import eus.healthit.bchef.core.view.WindowFrame;
import eus.healthit.bchef.core.view.dialogs.FileChooser;

public class Principal {

	WindowActions actions;
	WindowFrame windowFrame;

	public Principal() {
		windowFrame = new WindowFrame();
	}

	public static void main(String[] args) {
		IKitchenController kitchenController = new KitchenController();
		Principal principal = new Principal();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
}
