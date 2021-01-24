package eus.healthit.bchef.core.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import eus.healthit.bchef.core.controllers.view.windowFrame.WindowFrameController;

public class WindowFrame extends JFrame {

	WindowFrameController windowController;

	public WindowFrame() {
		super("B-Chef");

		setWindow();

		windowController = new WindowFrameController(this);
	}

	private void setWindow() {
		this.setSize(1275, 760);
		this.setLocation(300, 150);
		this.setResizable(true);
		this.setBackground(Color.white);
		ImageIcon img = new ImageIcon("resources/menuIcons/bchef_icon.png");
		this.setIconImage(img.getImage());

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void setContentPane(PrincipalView principalView, WindowFrameController windowFrameController) {
	}
}
