package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.view.panels.center.CenterViewVisitProfile;

public class ProfileVisitController implements ActionListener{
	
	CenterViewVisitProfile visitProfile;
	CenterViewController controller;
	
	public ProfileVisitController() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case ProfileVisitControllerAC.FOLLOW:
			System.out.println("Follow owo");
			break;
		}
	}

}
