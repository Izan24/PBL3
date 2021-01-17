package eus.healthit.bchef.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import eus.healthit.bchef.core.api.Client;
import eus.healthit.bchef.core.api.ImageCoder;
import eus.healthit.bchef.core.view.WindowFrame;

public class Principal {

	WindowActions actions;
	WindowFrame windowFrame;

	public Principal() {
		windowFrame = new WindowFrame();
	}

	public static void main(String[] args) {
		IKitchenController kitchenController = new KitchenController();
		Principal principal = new Principal();
	}
}
