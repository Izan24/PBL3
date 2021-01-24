package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.management.Query;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.controllers.BChefController;
import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.WindowFrame;
import eus.healthit.bchef.core.view.dialogs.CreationErrorDialog;
import eus.healthit.bchef.core.view.panels.CreateAccountView;
import eus.healthit.bchef.core.view.panels.LoginView;

public class LoginViewController implements IRoundButtonListener, KeyListener, ActionListener {

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
			login();

			break;
		case LoginViewControllerAC.CREATE_ACCOUNT:
//			loginView.resetAllFields();
			windowFrameController.setCreateAccountView();
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case LoginViewControllerAC.SHOW_PWD:
			changePasswordFieldState();
			break;

		}
	}

	private void changePasswordFieldState() {
		String pwd = loginView.getPassword();

		if (!pwd.equals(CreateAccountView.DEFAULT_PWD_TEXT)) {
			loginView.changePwdState();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("KEYPRESS: " + e.getKeyCode());
		switch (e.getKeyCode()) {
		case KeyEvent.VK_ENTER:
			login();
			break;

		default:
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void login() {
		User user = JSONCalls.authenticate(loginView.getUsername(), loginView.getPassword());

		if (user == null) {
			new CreationErrorDialog(window, "Inicio de sesión incorrecto", true,
					"El usuario o contrasña introducidos no son correctos.");

		} else {
			BChefController.getInstance().setUser(user);
			windowFrameController.setAppView(user);

		}
	}
}
