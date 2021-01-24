package eus.healthit.bchef.core.controllers.view.profileView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.view.panels.center.CenterViewProfile;

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
