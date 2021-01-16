package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.Query;

import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.panels.LoginView;

public class LoginViewController implements IRoundButtonListener {

	LoginView loginView;
	WindowFrameController windowFrameController;

	public LoginViewController(LoginView loginView, WindowFrameController windowFrameController) {
		this.loginView = loginView;
		this.windowFrameController = windowFrameController;
	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {
		case LoginViewControllerAC.LOGIN:
			System.out.println("Pidele un verify a Jorge");

//			User user = lo que te devuelve jorge
			/*
			 * if(user == null) showErrorDialog; else
			 * windowFrameController.setAppView(user);
			 */
			break;
		case LoginViewControllerAC.CREATE_ACCOUNT:
//			loginView.resetAllFields();
			windowFrameController.setCreateAccountView();
			break;
		}
	}

}
