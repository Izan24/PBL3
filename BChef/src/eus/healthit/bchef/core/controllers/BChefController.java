package eus.healthit.bchef.core.controllers;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.util.List;

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
import eus.healthit.bchef.core.models.KitchenAlarm;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.util.TextBuilder;
import eus.healthit.bchef.core.util.func.FunctionCall;
import eus.healthit.bchef.core.util.func.KitchenSwitchCall;
import eus.healthit.bchef.core.util.func.NewAlarmCall;
import eus.healthit.bchef.core.util.func.NextRecipeCall;

public class BChefController implements PropertyChangeListener {

	PropertyChangeSupport connector;
	

    private static BChefController instance = new BChefController();

    private BChefController() {
    	outputController = OutputController.getInstance();
    	recipeAssitantController = new RecipeAssistantController();
    	kitchenController = new KitchenController();
    	connector = new PropertyChangeSupport(this);
    	System.out.println("inic");
    }

    public static BChefController getInstance() {
        return instance;
    }
	
	IBoardController boardController;
	IInputController inputController;
	IOutputController outputController;
	IViewController viewController;
	IKitchenController kitchenController;
	//CommandController commandController;
	IRecipeAssistantController recipeAssitantController;
	
	
	List<Recipe> searchedRecipes;
	int searchedRecipesIndex;
	FunctionCall nextFunction;
	

	public void switchKitchen(KitchenUtil util, Integer index, Integer value) {
		switch(util) {
		case OVEN:
			if(value == null) {
				errorMessage("MISSUNDERSTOOD");
				break;
			}
			if(index == null) kitchenController.setOven(0, value);
			else kitchenController.setOven(index, value);
			break;
		case STOVE:
			if(value == null) {
				errorMessage("MISSUNDERSTOOD");
				break;
			}
			if(index == null) kitchenController.setFire(0, value);
			else kitchenController.setFire(index, value);
			break;
		case MISUNDERSTOOD:
			errorMessage("MISSUNDERSTOOD");
			break;
		}	
	}

	public void nextStep() {
		if(recipeAssitantController.getRecipe() == null) {
			errorMessage("MISSING_RECIPE");
			return;
		}
		
		RecipeStep nextStep = recipeAssitantController.nextStep();
		if(nextStep == null) {
			errorMessage("MISSING_NEXTSTEP");
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
			errorMessage("MISSING_RECIPE");
			return;
		}
		
		RecipeStep prevStep = recipeAssitantController.prevStep();
		if(prevStep == null) {
			errorMessage("MISSING_PREVSTEP");
			return;
		}
	}

	public void startRecipe(Recipe recipe) {
		recipeAssitantController.setRecipe(recipe);
		recipeAssitantController.nextStep();
	}

	public void nextRecipe() {
		if(searchedRecipesIndex < searchedRecipes.size()) {
			outputController.send(TextBuilder.recipeFoundMessage(searchedRecipes.get(++searchedRecipesIndex)));
		}
		else errorMessage("NO_MORE_RECIPES");
	}

	public void setAlarm(KitchenUtil util, Integer index, Duration time) {
		recipeAssitantController.setAlarm(util, index, time);
		outputController.send(TextBuilder.newAlarmMessage(time, util, index));
	}
	
	public void confirmCall() {
		if(nextFunction != null)
			switch (nextFunction.getId()) {
			case NextRecipeCall.ID_STRING:
				startRecipe(searchedRecipes.get(searchedRecipesIndex));
				break;
			default:
				break;
			}
		else errorMessage("MISSUNDERSTOOD");
	}

	public void cancellCall() {
		if(nextFunction != null) {
			switch (nextFunction.getId()) {
			case NextRecipeCall.ID_STRING:
				nextFunction.executeCall();
				break;
			default:
				break;
			}
			nextFunction = null;
		}
		else errorMessage("MISSUNDERSTOOD");
	}

	public void errorMessage(String reason) {
		outputController.send(TextBuilder.errorMessage(reason));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		switch (evt.getPropertyName()) {
		case "ALARM_FINISH":
			System.out.println(evt.getSource());
			outputController.soundAlarm();
			KitchenAlarm alarm = (KitchenAlarm) evt.getNewValue();
			if(alarm.getUtil() != null)
				outputController.send(TextBuilder.alarmDeactivateMessage(alarm.getUtil(), alarm.getUtilIndex()));
			break;
	
		default:
			break;
		}
	}

	public void addPropertyChangeListner(PropertyChangeListener listener) {
		connector.addPropertyChangeListener(listener);
	}
	
	
}
