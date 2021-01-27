package eus.healthit.bchef.core.assistant.interfaces;

import java.beans.PropertyChangeListener;

import eus.healthit.bchef.core.models.Kitchen;

public interface IBoardController {

	public void updateKitchen(Kitchen kitchen);
	
	public void addPropertyChangeListener(PropertyChangeListener listener);
	
	public void close();
	
}
