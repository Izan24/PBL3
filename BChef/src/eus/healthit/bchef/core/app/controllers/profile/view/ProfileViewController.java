package eus.healthit.bchef.core.app.controllers.profile.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.app.ui.panels.center.CenterViewProfile;

public class ProfileViewController implements ActionListener {

	CenterViewProfile profileView;

	public ProfileViewController(CenterViewProfile profileView) {
		this.profileView = profileView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case ProfileViewControllerAC.UPLOADED:
			profileView.changeListView(ProfileViewControllerAC.UPLOADED);
			break;
		case ProfileViewControllerAC.SAVED:
			profileView.changeListView(ProfileViewControllerAC.SAVED);
			break;
		}
	}

}
