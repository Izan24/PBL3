package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.view.panels.LoginView;

public class LoginViewController implements ActionListener {

	LoginView loginView;

	public LoginViewController(LoginView loginView) {
		this.loginView = loginView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case LoginViewControllerAC.LOGIN:

			break;
		case LoginViewControllerAC.CREATE_ACCOUNT:

			break;
		}
	}

}
