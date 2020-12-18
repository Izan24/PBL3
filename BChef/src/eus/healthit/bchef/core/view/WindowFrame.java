package eus.healthit.bchef.core.view;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import eus.healthit.bchef.core.WindowActions;

public class WindowFrame extends JFrame {

	PrincipalView view;
	WindowActions actions;

	public WindowFrame() {
		super("Ventanita uwu");

		actions = new WindowActions(this);
		view = new PrincipalView();

		setWindow();
	}

	private void setWindow() {
		this.setSize(1250, 750);
		this.setLocation(300, 150);
		this.setResizable(true);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(view);

		this.setVisible(true);
		
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//				| UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		}
	}
}
