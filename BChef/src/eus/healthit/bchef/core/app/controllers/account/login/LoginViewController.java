package eus.healthit.bchef.core.app.controllers.account.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.app.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.app.controllers.windowFrame.WindowFrameController;
import eus.healthit.bchef.core.app.ui.WindowFrame;
import eus.healthit.bchef.core.app.ui.dialogs.CreationErrorDialog;
import eus.healthit.bchef.core.app.ui.panels.CreateAccountView;
import eus.healthit.bchef.core.app.ui.panels.LoginView;
import eus.healthit.bchef.core.models.User;

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
			new CreationErrorDialog(window, "Inicio de sesi�n incorrecto", true,
					"El usuario o contras�a introducidos no son correctos.");

		} else {
//			BChefController.getInstance().setUser(user);
			windowFrameController.setAppView(user);

		}
	}
}
