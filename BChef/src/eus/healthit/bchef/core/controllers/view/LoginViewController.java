package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.Query;

import eus.healthit.bchef.core.api.JSONParser;
import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.WindowFrame;
import eus.healthit.bchef.core.view.dialogs.CreationErrorDialog;
import eus.healthit.bchef.core.view.panels.LoginView;

public class LoginViewController implements IRoundButtonListener {

	LoginView loginView;
	WindowFrameController windowFrameController;
	WindowFrame window;

	public LoginViewController(LoginView loginView, WindowFrameController windowFrameController, WindowFrame window) {
		this.loginView = loginView;
		this.windowFrameController = windowFrameController;
		this.window = window;
	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {
		case LoginViewControllerAC.LOGIN:
			User user = JSONParser.authenticate(loginView.getUsername(), loginView.getPassword());
			//User user = JSONParser.getUserById(6);

//			User user = lo que te devuelve jorge
			
			 if(user == null) new CreationErrorDialog(window, "Inicio de sesión incorrecto", true, "El usuario o contrasña introducidos no son correctos.");
			 else windowFrameController.setAppView(user);
			 
			break;
		case LoginViewControllerAC.CREATE_ACCOUNT:
//			loginView.resetAllFields();
			windowFrameController.setCreateAccountView();
			break;
		}
	}

}
