package eus.healthit.bchef.core.app.ui;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import eus.healthit.bchef.core.app.controllers.windowFrame.WindowFrameController;
import eus.healthit.bchef.core.assistant.BChefController;

public class WindowFrame extends JFrame {

	private static final long serialVersionUID = 7864734956122358906L;
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

		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				BChefController.getInstance().close();
				e.getWindow().dispose();
			}
		});
	}

	public void setContentPane(PrincipalView principalView, WindowFrameController windowFrameController) {
	}
}
