package eus.healthit.bchef.core.app.controllers.account.register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import org.apache.commons.validator.routines.EmailValidator;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.app.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.app.controllers.windowFrame.WindowFrameController;
import eus.healthit.bchef.core.app.ui.WindowFrame;
import eus.healthit.bchef.core.app.ui.dialogs.CreationErrorDialog;
import eus.healthit.bchef.core.app.ui.panels.CreateAccountView;

public class CreateAccountController implements IRoundButtonListener, ActionListener {

	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");
	String DEFAULT_NAME_TEXT = rb.getString("name_text");
	String DEFAULT_SURNAME_TEXT = rb.getString("surname_text");
	String DEFAULT_EMAIL_TEXT = rb.getString("email_text");
	String DEFAULT_USERNAME_TEXT = rb.getString("username_text");
	String DEFAULT_PWD_TEXT = rb.getString("pwd_text");
	String DEFAULT_CONFPWD_TEXT = rb.getString("pwd_confirm_text");

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
				new CreationErrorDialog(window, rb.getString("acc_created_title"), true,
						rb.getString("acc_created_text"));
				windowFrameController.setLoginView();
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

	private void changePasswordFieldState() {
		String pwd = createAccountView.getPwd();
		String pwdConfirm = createAccountView.getPwdConfirm();

		if (!pwd.equals(DEFAULT_PWD_TEXT)) {
			createAccountView.changePwdState();
		}
		if (!pwdConfirm.equals(DEFAULT_CONFPWD_TEXT)) {
			createAccountView.changePwdConfirmState();
		}
	}

	private boolean verifyParams() {
		EmailValidator validator = EmailValidator.getInstance();
		if (createAccountView.getName().trim().equals("") || createAccountView.getName().equals(DEFAULT_NAME_TEXT)) {
			new CreationErrorDialog(window, rb.getString("invalid_name_title"), true,
					rb.getString("invalid_name_text"));
			return false;
		} else if (createAccountView.getSurname().trim().equals("")
				|| createAccountView.getSurname().equals(DEFAULT_SURNAME_TEXT)) {
			new CreationErrorDialog(window, rb.getString("invalid_surname_title"), true,
					rb.getString("invalid_surname_text"));
			return false;
		} else if (createAccountView.getEmail().trim().equals("")
				|| createAccountView.getEmail().equals(DEFAULT_EMAIL_TEXT)
				|| !validator.isValid(createAccountView.getEmail())) {
			new CreationErrorDialog(window, rb.getString("invalid_email_title"), true,
					rb.getString("invalid_email_text"));
			return false;
		} else if (!checkUsername(createAccountView.getUsername())) {
			new CreationErrorDialog(window, rb.getString("invalid_username_title"), true,
					rb.getString("invalid_username_text"));
			return false;
		} else if (!passwordVerify()) {
			new CreationErrorDialog(window, rb.getString("invalid_password_title"), true,
					rb.getString("invalid_password_text"));
			return false;
		}
		return true;
	}

	private boolean checkUsername(String username) {
		System.out.println(JSONCalls.checkUser(username));
		return JSONCalls.checkUser(username);
	}

	private boolean passwordVerify() {
		if (!createAccountView.getPwd().equals(DEFAULT_PWD_TEXT)
				&& !createAccountView.getPwdConfirm().equals(DEFAULT_CONFPWD_TEXT)
				&& createAccountView.getPwd().equals(createAccountView.getPwdConfirm())) {
			return true;
		} else {
			return false;
		}
	}
}
