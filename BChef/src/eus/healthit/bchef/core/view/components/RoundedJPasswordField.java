package eus.healthit.bchef.core.view.components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JPasswordField;

public class RoundedJPasswordField extends JPasswordField implements FocusListener {

	private static final long serialVersionUID = -5175905329740849910L;
	private final String hint;
	private boolean showingHint;

	public RoundedJPasswordField(String hint) {
		super(hint);
		this.hint = hint;
		this.showingHint = true;
		super.setMargin(new Insets(4, 28, 4, 8));
		super.addFocusListener(this);
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText("");
			super.setEchoChar('*');
			super.setForeground(Color.black);
			showingHint = false;
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (this.getText().isEmpty()) {
			super.setText(hint);
			super.setEchoChar((char) 0);
			showingHint = true;
			super.setForeground(Color.GRAY);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getText() {
		return showingHint ? "" : super.getText();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}