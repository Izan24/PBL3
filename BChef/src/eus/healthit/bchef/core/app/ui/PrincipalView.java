package eus.healthit.bchef.core.app.ui;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;
import eus.healthit.bchef.core.app.controllers.windowFrame.WindowFrameController;
import eus.healthit.bchef.core.app.ui.panels.LeftMenuView;
import eus.healthit.bchef.core.app.ui.panels.NorthView;
import eus.healthit.bchef.core.app.ui.panels.center.CenterView;
import eus.healthit.bchef.core.models.User;

@SuppressWarnings("serial")
public class PrincipalView extends JPanel {

	LeftMenuView leftMenuView;
	NorthView northView;
	CenterView centerView;

	CenterViewController centerController;

	public PrincipalView(User user, WindowFrameController windowFrameController, WindowFrame window) {
		super(new BorderLayout());

		centerView = new CenterView();

		centerController = new CenterViewController(this, centerView, user, windowFrameController, window);
		centerController.setStartView();

		leftMenuView = new LeftMenuView(centerController);
		northView = new NorthView(centerController);

		setContent();
	}

	private void setContent() {
		this.add(leftMenuView, BorderLayout.WEST);
		this.add(northView, BorderLayout.NORTH);
		this.add(centerView, BorderLayout.CENTER);
	}

	public void changeCenterView(JComponent component) {
		centerView.setView(component);
	}
}