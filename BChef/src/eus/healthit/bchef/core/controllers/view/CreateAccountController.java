package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.validator.routines.EmailValidator;

import eus.healthit.bchef.core.api.JSONCalls;
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
				JSONCalls.registerUser(createAccountView.getName(), createAccountView.getSurname(),
						createAccountView.getEmail(), createAccountView.getUsername(), createAccountView.getPwd(),
						"default");
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

	@SuppressWarnings({ "static-access" })
	private boolean verifyParams() {
		EmailValidator validator = EmailValidator.getInstance();
		if (createAccountView.getName().trim().equals("")
				|| createAccountView.getName().equals(CreateAccountView.DEFAULT_NAME_TEXT)) {
			new CreationErrorDialog(window, "Invalid name", true, "El nombre introducido no es valido");
			return false;
		} else if (createAccountView.getSurname().trim().equals("")
				|| createAccountView.getSurname().equals(createAccountView.DEFAULT_SURNAME_TEXT)) {
			new CreationErrorDialog(window, "Invalid surname", true, "El apellido introducido no es valido");
			return false;
		} else if (createAccountView.getEmail().trim().equals("")
				|| createAccountView.getEmail().equals(createAccountView.DEFAULT_EMAIL_TEXT)
				|| !validator.isValid(createAccountView.getEmail())) {
			new CreationErrorDialog(window, "Invalid email", true, "El email introducido no es valido");
			return false;
		} else if (!checkUsername(createAccountView.getUsername())) {
			new CreationErrorDialog(window, "Invalid username", true, "El nombre de usuario introducido ya existe");
			return false;
		} else if (!passwordVerify()) {
			new CreationErrorDialog(window, "Invalid password", true, "Las contraseñas no coinciden");
			return false;
		}
		return true;
	}

	private boolean checkUsername(String username) {
		System.out.println(JSONCalls.checkUser(username));
		return JSONCalls.checkUser(username);
	}

	private boolean passwordVerify() {
		if (!createAccountView.getPwd().equals(createAccountView.DEFAULT_PWD_TEXT)
				&& !createAccountView.getPwdConfirm().equals(createAccountView.DEFAULT_CONFPWD_TEXT)
				&& createAccountView.getPwd().equals(createAccountView.getPwdConfirm())) {
			return true;
		} else {
			return false;
		}
	}
}
