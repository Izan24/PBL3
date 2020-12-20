package eus.healthit.bchef.core.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import eus.healthit.bchef.core.controllers.CenterViewController;
import eus.healthit.bchef.core.view.panels.CenterView;
import eus.healthit.bchef.core.view.panels.LeftMenuView;
import eus.healthit.bchef.core.view.panels.NorthView;

@SuppressWarnings("serial")
public class PrincipalView extends JPanel {

	LeftMenuView leftMenuView;
	NorthView northView;
	CenterView centerView;
	
	CenterViewController centerController;

	public PrincipalView() {
		super(new BorderLayout());
		
		centerController = new CenterViewController(this);
		
		leftMenuView = new LeftMenuView(centerController);
		northView = new NorthView();
		centerView = new CenterView();

		setContent();
	}

	private void setContent() {
		this.add(leftMenuView, BorderLayout.WEST);
		this.add(northView, BorderLayout.NORTH);
		this.add(centerView, BorderLayout.CENTER);
	}
	
	public void changeCenterView(String selection) {
		centerView.setView(selection);
	}
}