package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.view.panels.center.CenterViewProfile;

public class ProfileController implements ActionListener {

	CenterViewProfile profileView;

	public ProfileController(CenterViewProfile profileView) {
		this.profileView = profileView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case ProfileControllerAC.UPLOADED:
			profileView.changeListView(ProfileControllerAC.UPLOADED);
			break;
		case ProfileControllerAC.SAVED:
			profileView.changeListView(ProfileControllerAC.SAVED);
			break;
		}
	}

}
