package eus.healthit.bchef.core;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.synth.SynthScrollPaneUI;

public class ActionManager extends AbstractAction {

	String text;
	JFrame view;

	public ActionManager(String text, Icon image, String descrip, Integer shorcut, JFrame view) {
		super(text, image);
		this.text = text;
		this.putValue(Action.SHORT_DESCRIPTION, descrip);
		this.putValue(Action.MNEMONIC_KEY, shorcut);
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (text) {
		case "Home":
			
			break;

		case "Profile":

			break;

		case "Shop List":
			
			break;

		case "B-Chef":
			
			break;
		}
	}

}
