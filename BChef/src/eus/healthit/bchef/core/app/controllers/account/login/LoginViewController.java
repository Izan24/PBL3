package eus.healthit.bchef.core.app.controllers.account.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ResourceBundle;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.app.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.app.controllers.windowFrame.WindowFrameController;
import eus.healthit.bchef.core.app.ui.WindowFrame;
import eus.healthit.bchef.core.app.ui.dialogs.CreationErrorDialog;
import eus.healthit.bchef.core.app.ui.panels.LoginView;
import eus.healthit.bchef.core.assistant.BChefController;
import eus.healthit.bchef.core.models.User;

public class LoginViewController implements IRoundButtonListener, KeyListener, ActionListener {

	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");

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

		if (!pwd.equals(rb.getString("default_password_text"))) {
			loginView.changePwdState();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
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
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public void login() {
		User user = JSONCalls.authenticate(loginView.getUsername(), loginView.getPassword());

		if (user == null) {
			new CreationErrorDialog(window, rb.getString("incorrect_login_title"), true,
					rb.getString("incorrect_login_text"));

		} else {
			BChefController.getInstance().setUser(user);
			windowFrameController.setAppView(user);
		}
	}
}
