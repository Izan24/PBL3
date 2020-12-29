package eus.healthit.bchef.core.view;

import java.awt.BorderLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import eus.healthit.bchef.core.controllers.view.CenterViewController;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.panels.LeftMenuView;
import eus.healthit.bchef.core.view.panels.NorthView;
import eus.healthit.bchef.core.view.panels.center.CenterView;

@SuppressWarnings("serial")
public class PrincipalView extends JPanel {

	LeftMenuView leftMenuView;
	NorthView northView;
	CenterView centerView;
	
	CenterViewController centerController;

	public PrincipalView(User user) {
		super(new BorderLayout());
		
		centerView = new CenterView();
		
		centerController = new CenterViewController(this,centerView,user);
		centerController.setStartView();
		
		leftMenuView = new LeftMenuView(centerController);
		northView = new NorthView();

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