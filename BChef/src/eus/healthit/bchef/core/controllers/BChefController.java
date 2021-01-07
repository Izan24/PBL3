package eus.healthit.bchef.core.controllers;

import eus.healthit.bchef.core.controllers.interfaces.IBoardController;
import eus.healthit.bchef.core.controllers.interfaces.IInputController;
import eus.healthit.bchef.core.controllers.interfaces.IKitchenController;
import eus.healthit.bchef.core.controllers.interfaces.IOutputController;
import eus.healthit.bchef.core.controllers.interfaces.IViewController;
import eus.healthit.bchef.core.enums.KitchenUtil;

public class BChefController {

	BChefController bChefController;

    static BChefController obj = new BChefController();

    private BChefController() {
    }

    public static BChefController getBChefController() {
    	
        return obj;
    }
	
	IBoardController boardController;
	IInputController inputController;
	IOutputController outputController;
	IViewController viewController;
	IKitchenController kitchenController;
	CommandController commandController;
	
	public void notifyMisunderstood() {
		//TODO: Selector random de mensajes (Evitar repetir la misma frase)
		outputController.send("No se ha entendido");
	}

	public void switchKitchen(KitchenUtil util, Integer[] nums) {
		//
		
		switch(util) {
		case FURNACE:
			break;
		case STOVE:
			break;
		case LIGHT:
			break;
		case VENT:
			break;
		case MICROWAVE:
			break;
		case MISUNDERSTOOD:
			break;
		}
		
	}
	
	
	
}
