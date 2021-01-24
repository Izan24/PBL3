package eus.healthit.bchef.core.controllers.interfaces;

import java.beans.PropertyChangeListener;

import eus.healthit.bchef.core.models.Kitchen;

public interface IBoardController {

	//TODO :D
	public void updateKitchen(Kitchen kitchen);
	
	public void addPropertyChangeListener(PropertyChangeListener listener);
	
}
