package eus.healthit.bchef.core.view.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class RoundedTextField extends JTextField implements FocusListener {

	private static final long serialVersionUID = -5175905329740849910L;
	private final String hint;
	private boolean showingHint;

	public RoundedTextField(String hint) {
		super(hint);
		this.hint = hint;
		this.showingHint = true;
		super.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText("");
			super.setForeground(Color.black);
			showingHint = false;
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText(hint);
			showingHint = true;
			super.setForeground(Color.GRAY);
		}
	}

	@Override
	public String getText() {
		return showingHint ? "" : super.getText();
	}
}
