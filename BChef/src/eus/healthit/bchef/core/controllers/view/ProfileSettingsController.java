package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.validator.routines.EmailValidator;

import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.WindowFrame;
import eus.healthit.bchef.core.view.dialogs.CreationErrorDialog;
import eus.healthit.bchef.core.view.dialogs.FileChooser;
import eus.healthit.bchef.core.view.panels.center.CenterViewProfileSettings;

public class ProfileSettingsController implements ActionListener, IRoundButtonListener {

	User user;
	CenterViewProfileSettings settingsView;
	WindowFrameController windowController;
	WindowFrame window;

	public ProfileSettingsController(User user, CenterViewProfileSettings settingsView,
			WindowFrameController windowController, WindowFrame window) {
		this.user = user;
		this.settingsView = settingsView;
		this.windowController = windowController;
		this.window = window;
	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {
		case ProfileSettingsControllerAC.UPLOAD_IMAGE:
			FileChooser file = new FileChooser();
			try {
				try {
					settingsView.setImage(ImageIO.read(file.getSelectedFile()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (NullPointerException e) {
			}
			break;

		case ProfileSettingsControllerAC.SAVE_CHANGES:
			if (verifyParameters()) {
				System.out.println("Put del nuevo user");
				user.setName(settingsView.getName());
				user.setSurname(settingsView.getSurname());
//				user.setProfilePic(settingsView.getImage());
				user.setEmail(settingsView.getEmail());
				user.setUsername(settingsView.getUsername());
			}
			break;

		case ProfileSettingsControllerAC.LOG_OUT:
			System.out.println("Envia el user entero a la database antes de logout");
			windowController.setLoginView();
			break;
		}
	}

	@SuppressWarnings("static-access")
	private boolean verifyParameters() {
		EmailValidator validator = EmailValidator.getInstance();
		if (settingsView.getName().trim().equals("") || settingsView.getName().equals(settingsView.DEFAULT_NAME_TEXT)) {
			new CreationErrorDialog(window, "Invalid name", true, "El nombre introducido no es valido");
			return false;
		} else if (settingsView.getSurname().trim().equals("")
				|| settingsView.getSurname().equals(settingsView.DEFAULT_SURNAME_TEXT)) {
			new CreationErrorDialog(window, "Invalid surname", true, "El apellido introducido no es valido");
			return false;
		} else if (settingsView.getEmail().trim().equals("")
				|| settingsView.getEmail().equals(settingsView.DEFAULT_EMAIL_TEXT)
				|| !validator.isValid(settingsView.getEmail())) {
			new CreationErrorDialog(window, "Invalid email", true, "El email introducido no es valido");
			return false;
		}
//		else if (checkUsername(createAccountView.getUsername())) { en chekusername mira que no sea "" con trim y que no exista
//			new CreationErrorDialog(window, "Invalid username", true, "El nombre de usuario introducido ya existe");
//			return false;
//			PETICION A LA DATABASE PARA QUE MIRE SI EXISTE
//		}
		else if (passwordVerify()) {
			new CreationErrorDialog(window, "Invalid password", true, "Las contraseñas no coinciden");
			return false;
		}
		return true;
	}

	@SuppressWarnings("static-access")
	private boolean passwordVerify() {
		if (!settingsView.getNewPwd().trim().equals("")
				&& !settingsView.getNewPwd().equals(settingsView.DEFAULT_CONFPWD_TEXT)) {
			if (!settingsView.getPwd().trim().equals("")
					&& !settingsView.getPwd().equals(settingsView.DEFAULT_PWD_TEXT)) {
				// quety de verificacción retur true/false
			}
		}
		return false;
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
		String pwd = settingsView.getPwd();
		String pwdConfirm = settingsView.getNewPwd();

		if (!pwd.equals(settingsView.DEFAULT_PWD_TEXT)) {
			settingsView.changePwdState();
		}
		if (!pwdConfirm.equals(settingsView.DEFAULT_CONFPWD_TEXT)) {
			settingsView.changenewPwdState();
		}
	}

}
