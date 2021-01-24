package eus.healthit.bchef.core.app.controllers;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextArea;

public class DefaultTextAreaController implements FocusListener {

	JTextArea area;
	String defaultText;

	public DefaultTextAreaController(JTextArea area, String defaultText) {
		this.area = area;
		this.defaultText = defaultText;
	}

	public void focusGained(FocusEvent e) {
		if (area.getText().equals(defaultText)) {
			area.setText("");
			area.replaceRange("", 0, area.getText().length());
			area.setForeground(Color.BLACK);						
		}
	}

	public void focusLost(FocusEvent e) {
		if (area.getText().equals("")) {
			area.setText(defaultText);
			area.setForeground(Color.gray);						
		}
	}
}
