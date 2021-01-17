package eus.healthit.bchef.core.controllers.view;

import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.view.panels.center.CenterViewVisitProfile;

public class ProfileVisitController implements IRoundButtonListener {

	CenterViewVisitProfile visitProfile;
	CenterViewController controller;

	public ProfileVisitController() {

	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {
		case ProfileVisitControllerAC.FOLLOW:
			System.out.println("Follow owo");
			break;
		}
	}

}
