package eus.healthit.bchef.core.controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;
import java.time.LocalTime;

import eus.healthit.bchef.core.controllers.implementations.KitchenController;
import eus.healthit.bchef.core.controllers.implementations.OutputController;
import eus.healthit.bchef.core.controllers.implementations.RecipeAssistantController;
import eus.healthit.bchef.core.controllers.interfaces.IBoardController;
import eus.healthit.bchef.core.controllers.interfaces.IInputController;
import eus.healthit.bchef.core.controllers.interfaces.IKitchenController;
import eus.healthit.bchef.core.controllers.interfaces.IOutputController;
import eus.healthit.bchef.core.controllers.interfaces.IRecipeAssistantController;
import eus.healthit.bchef.core.controllers.interfaces.IViewController;
import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.models.RecipeStep;

public class BChefController implements PropertyChangeListener {

	BChefController bChefController;

    static BChefController obj = new BChefController();

    private BChefController() {
    	outputController = OutputController.getOutputController();
    	recipeAssitantController = new RecipeAssistantController();
    	kitchenController = new KitchenController();
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
	IRecipeAssistantController recipeAssitantController;
	
	
	
	public void notifyMisunderstood() {
		//TODO: Selector random de mensajes (Evitar repetir la misma frase)
		outputController.send("Perdona, no te he entendido.");
	}

	public void switchKitchen(KitchenUtil util, Integer[] nums) {
		//
		
		switch(util) {
		case OVEN:
			if(nums.length == 0) {
				notifyMisunderstood();
				break;
			}
			if(nums.length > 1) kitchenController.setOven(nums[0], nums[1]);
			else kitchenController.setOven(0, nums[0]);
			break;
		case STOVE:
			if(nums.length == 0) {
				notifyMisunderstood();
				break; 
			}
			if(nums.length > 1) kitchenController.setFire(nums[0], nums[1]);
			else kitchenController.setFire(0, nums[1]);
			break;
		case MISUNDERSTOOD:
			notifyMisunderstood();
			break;
		}
		
	}

	public void nextStep() {
		if(recipeAssitantController.getRecipe() == null) {
			outputController.send("Lo siento, aún no has empezado una receta.");
			return;
		}
		
		RecipeStep nextStep = recipeAssitantController.nextStep();
		if(nextStep == null) {
			outputController.send("Ya has completado la receta.");
			return;
		}
		
		outputController.send(nextStep.getText());
		switch(nextStep.getAction()) {
		case FURNACE:
			
			break;
		case SET_FIRE:
			break;
		default:
			break;
		}
	}
	
	public void prevStep() {
		if(recipeAssitantController.getRecipe() == null) {
			outputController.send("Lo siento, aun no has empezado una receta.");
			return;
		}
		
		RecipeStep prevStep = recipeAssitantController.prevStep();
		if(prevStep == null) {
			outputController.send("Estas en el primer paso.");
			return;
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void setAlarm(KitchenUtil util, int index, Duration time) {
		recipeAssitantController.setAlarm(util, index, time);
	}
	
	
}
