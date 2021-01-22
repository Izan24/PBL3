package eus.healthit.bchef.core;

import eus.healthit.bchef.core.controllers.BChefController;
import eus.healthit.bchef.core.controllers.CommandController;
import eus.healthit.bchef.core.controllers.implementations.KitchenController;
import eus.healthit.bchef.core.controllers.input.AudioInputController;
import eus.healthit.bchef.core.controllers.interfaces.IKitchenController;
import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.util.StringParser;

import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import eus.healthit.bchef.core.controllers.implementations.KitchenController;
import eus.healthit.bchef.core.view.WindowFrame;

public class Principal {

	WindowActions actions;
	WindowFrame windowFrame;

	public Principal() {
		windowFrame = new WindowFrame();
	}

	public static void main(String[] args) {
		//BChefController.getInstance().startVoiceRecon();
		Principal principal = new Principal();
		//IKitchenController = new KitchenController();
//		CommandController commandController = CommandController.getInstance();
//		String cmdString = "quita de la lista buscar 2 psic�logos".toLowerCase();
//		VoiceCommand command = StringParser.parseCommand(cmdString);
//		cmdString = StringParser.deleteCommandWords(cmdString, command);
//		commandController.selectCommand(command, cmdString);

//		String string = StringParser.stripSpaces("bchef buscame una receta de heiko moro");
//		CommandController.getInstance().selectCommand(StringParser.parseCommand(string), string);

//		AudioInputController inputController = AudioInputController.getInstance();
//		inputController.start();
//		inputController.startRecon();

//		KitchenController controller = new KitchenController();
//		controller.setFire(0, 0);

		// Separador

//		Principal principal = new Principal();
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
