package eus.healthit.bchef.core.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.view.PrincipalView;

public class CenterViewController implements ActionListener {

	PrincipalView principalView;

	public CenterViewController(PrincipalView principalView) {

		this.principalView = principalView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case CenterControllerAC.HOME:
		case CenterControllerAC.LIST:
		case CenterControllerAC.PROFILE:
		case CenterControllerAC.BCHEF:
			principalView.changeCenterView(e.getActionCommand());
			break;
		}
	}

}
