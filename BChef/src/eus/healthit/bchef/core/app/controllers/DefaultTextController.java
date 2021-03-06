package eus.healthit.bchef.core.app.controllers;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class DefaultTextController implements FocusListener {

	JTextField field;
	String defaultText;

	public DefaultTextController(JTextField field, String defaultText) {
		this.field = field;
		this.defaultText = defaultText;
	}


	public void focusGained(FocusEvent e) {
		if (field.getText().equals(defaultText)) {
			field.setText("");
			field.setForeground(Color.BLACK);			
		}
	}

	public void focusLost(FocusEvent e) {
		if (field.getText().equals("")) {
			field.setText(defaultText);
			field.setForeground(Color.gray);			
		}
	}
}
