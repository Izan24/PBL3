package eus.healthit.bchef.core;

import java.io.IOException;
import java.util.UUID;

import org.json.JSONObject;

import eus.healthit.bchef.core.api.API;
import eus.healthit.bchef.core.view.WindowFrame;

public class Principal {

	WindowActions actions;
	WindowFrame windowFrame;

	public Principal() {
		windowFrame = new WindowFrame();
	}

	public static void main(String[] args) throws IOException {
		//API.searchRecipe("Test", 0);
		//API.searchIngredient("Patata");
		//API.getPage(0).toString();
		//API.auth(new JSONObject().put("username", "Rkolay").put("password", "mutriku123"));
		//API.getUserName(1);
		//API.getUserById(1);
		//API.rate(new JSONObject().put("id_user", 1).put("uuid_recipe", "796f459f-dcd3-46f7-b659-06240653a116").put("rating", 8));
		//API.updateUserConfig(new JSONObject());
	}
}
