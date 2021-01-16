package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.view.WindowFrame;
import eus.healthit.bchef.core.view.dialogs.CreationErrorDialog;
import eus.healthit.bchef.core.view.panels.CreateAccountView;


public class CreateAccountController implements IRoundButtonListener, ActionListener {

	CreateAccountView createAccountView;
	WindowFrameController windowFrameController;
	WindowFrame window;

	public CreateAccountController(CreateAccountView createAccountView, WindowFrameController windowFrameController,
			WindowFrame window) {
		this.createAccountView = createAccountView;
		this.windowFrameController = windowFrameController;
		this.window = window;
	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {
		case CreateAccountControllerAC.CREATEACC:
			if (verifyParams()) {
				// confirm dialog
				windowFrameController.setLoginView();
			} else {
				// error dialog diciendo que es lo que ha pasado, tambien lo puedes hacer desde
				// verify params
			}
			break;

		case CreateAccountControllerAC.GOTO_LOGIN:
//			createAccountView.resetAllFields();
			windowFrameController.setLoginView();
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case CreateAccountControllerAC.SHOW_PWD:
			changePasswordFieldState();
			break;
		}
	}

	@SuppressWarnings("static-access")
	private void changePasswordFieldState() {
		String pwd = createAccountView.getPwd();
		String pwdConfirm = createAccountView.getPwdConfirm();

		if (!pwd.equals(createAccountView.DEFAULT_PWD_TEXT)) {
			createAccountView.changePwdState();
		}
		if (!pwdConfirm.equals(createAccountView.DEFAULT_CONFPWD_TEXT)) {
			createAccountView.changePwdConfirmState();
		}
	}

	@SuppressWarnings("static-access")
	private boolean verifyParams() {
		/*
		 * Mira que todos los textfields esten rellenos, que las dos passwords sean
		 * iguales y que el email tenga un @ y despues del @ minimo un .
		 * 
		 * true si valid, false si null
		 */
		if (createAccountView.getName().trim().equals("") || createAccountView.getName().equals(createAccountView.DEFAULT_NAME_TEXT)) {
			new CreationErrorDialog(window, "Invalid name", true, "El nombre introducido no es valido");
			return false;
		} else if (createAccountView.getSurname().trim().equals("") || createAccountView.getSurname().equals(createAccountView.DEFAULT_SURNAME_TEXT)) {
			new CreationErrorDialog(window, "Invalid surname", true, "El apellido introducido no es valido");
			return false;
		}else if (createAccountView.getEmail().trim().equals("") || createAccountView.getEmail().equals(createAccountView.DEFAULT_EMAIL_TEXT) || !emailValid(createAccountView.getEmail())) {
			new CreationErrorDialog(window, "Invalid email", true, "El email introducido no es valido");
			return false;
		}	

		return true;
	}

	private boolean emailValid(String email) {
		return false;
	}

}
