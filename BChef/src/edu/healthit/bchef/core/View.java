package edu.healthit.bchef.core;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class View extends JPanel{
	
	public View() {
		super(new BorderLayout());
		setContent();
	}

	private void setContent() {
		
		this.add(createMenuBar());
	}

	private Component createMenuBar() {
		return null;
	}

}