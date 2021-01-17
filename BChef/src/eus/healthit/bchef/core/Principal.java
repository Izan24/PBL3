package eus.healthit.bchef.core;

import java.time.Duration;

import eus.healthit.bchef.core.controllers.BChefController;
import eus.healthit.bchef.core.view.WindowFrame;

public class Principal {

	WindowActions actions;
	WindowFrame windowFrame;

	public Principal() {
		windowFrame = new WindowFrame();
	}


	public static void main(String[] args) {
		//OutputController.getOutputController().send("1 2 y luego 3");
//		CommandController commandController = CommandController.getInstance();
//		String cmdString = "BChef alarma para dentro de 10 minutos".toLowerCase();
//		VoiceCommand command = StringParser.parseCommand(cmdString);
//		//cmdString = CommandController.deleteCommandWords(cmdString, command);
//		commandController.selectCommand(command, cmdString);
		
		//Separador
		
		
		Principal principal = new Principal();
//		List<Ingredient> ings = new ArrayList<>();
//		ings.add(new Ingredient("Zanahoria", "Carb", "3 3 YUJU"));
//		List<RecipeStep> steps = new ArrayList<>();
//		steps.add(new RecipeStep(RecipeStepActions.FURNACE, 5,
//				"https://pbs.twimg.com/media/EqbU529XMAEl8xm?format=png&name=240x240", "xD", 0l));
//		RecipeRepository.insert(
//				new Recipe(UUID.randomUUID(), "Test", "Urko", 10, new Timestamp(2198312312412l), new Time(23123421424l),
//						ings, steps, "https://i1.wp.com/img.pixfans.com/2010/04/silbar.gif?resize=240%2C195"));

		// RecipeRepository.search("Test");
		// Recipe rep = RecipeRepository.get("2a961214-ae24-49ec-a350-f0582c8a1e9e");
		// RecipeRepository.delete(rep);
		// QueryCon.closeConn();

	}
}
