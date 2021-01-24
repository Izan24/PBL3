package eus.healthit.bchef.core.app.controllers;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import eus.healthit.bchef.core.app.controllers.interfaces.IClickable;

public class DoubleClickListener extends MouseAdapter {
	
	IClickable component;
	 

	public DoubleClickListener(IClickable component) {
		this.component = component;
	}

	public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && !e.isConsumed()) {
            e.consume();
            component.clicked();
        }
    }
}