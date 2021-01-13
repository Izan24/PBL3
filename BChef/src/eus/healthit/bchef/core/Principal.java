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


	public static void main(String[] args) throws MalformedURLException {
		//IKitchenController kitchenController = new KitchenController();
		//Principal principal = new Principal();
		Map<String, String> map = new HashMap<>();
		map.put("name", "Urko");
		map.put("surname", "Agirregomezkorta");
		map.put("profilepic", ImageCoder.encodeImage("proba2.png"));
		map.put("email","u.agirregomezkort@alumni.mondragon.edu");
		map.put("username", "Rkolay");
		map.put("password", "mutriku123");
		Client cliente = new Client(new URL("http://localhost/api/user"));
		cliente.postUser(map);
	}
}
