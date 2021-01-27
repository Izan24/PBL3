package eus.healthit.bchef.core.app.controllers.profile.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.Printable;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

import org.apache.commons.validator.routines.EmailValidator;

import eus.healthit.bchef.core.api.ImageRepository;
import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.app.controllers.account.register.CreateAccountControllerAC;
import eus.healthit.bchef.core.app.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.app.controllers.windowFrame.WindowFrameController;
import eus.healthit.bchef.core.app.ui.WindowFrame;
import eus.healthit.bchef.core.app.ui.dialogs.CreationErrorDialog;
import eus.healthit.bchef.core.app.ui.dialogs.FileChooser;
import eus.healthit.bchef.core.app.ui.panels.center.CenterViewProfileSettings;
import eus.healthit.bchef.core.models.User;

public class ProfileSettingsController implements ActionListener, IRoundButtonListener {

	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");

	String DEFAULT_NAME_TEXT = rb.getString("name_text");
	String DEFAULT_SURNAME_TEXT = rb.getString("surname_text");
	String DEFAULT_EMAIL_TEXT = rb.getString("email_text");
	String DEFAULT_USERNAME_TEXT = rb.getString("username_text");
	String DEFAULT_PWD_TEXT = rb.getString("pwd_text");
	String DEFAULT_NEWPWD_TEXT = rb.getString("pwd_confirm_text");

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
			FileChooser file = null;
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				file = new FileChooser();
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e) {
				System.out.println("Windows lookAndFeel error");
			}
			try {
				try {
					settingsView.setImagePath(file.getSelectedFile().getPath());
					settingsView.setImage(ImageIO.read(file.getSelectedFile()));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
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
				user.setProfilePic(settingsView.getImage());
				user.setEmail(settingsView.getEmail());
				user.setUsername(settingsView.getUsername());
				String pathString = settingsView.getImagePath();
				user.setImgString((pathString != null) ? pathString : "nochange");
				JSONCalls.updateUser(user);
				settingsView.updateView();
			}
			break;

		case ProfileSettingsControllerAC.LOG_OUT:
			windowController.setLoginView();
			break;
		}
	}

	private boolean verifyParameters() {
		EmailValidator validator = EmailValidator.getInstance();
		if (settingsView.getName().trim().equals("") || settingsView.getName().equals(DEFAULT_NAME_TEXT)) {
			new CreationErrorDialog(window, rb.getString("invalid_name_title"), true,
					rb.getString("invalid_name_text"));
			return false;
		} else if (settingsView.getSurname().trim().equals("")
				|| settingsView.getSurname().equals(DEFAULT_SURNAME_TEXT)) {
			new CreationErrorDialog(window, rb.getString("invalid_surname_title"), true,
					rb.getString("invalid_surname_text"));
			return false;
		} else if (settingsView.getEmail().trim().equals("") || settingsView.getEmail().equals(DEFAULT_EMAIL_TEXT)
				|| !validator.isValid(settingsView.getEmail())) {
			new CreationErrorDialog(window, rb.getString("invalid_email_title"), true,
					rb.getString("invalid_email_text"));
			return false;
		} else if (!settingsView.getUsername().equals(user.getUsername())) {
			if (!JSONCalls.checkUser(settingsView.getUsername())) {
				new CreationErrorDialog(window, rb.getString("invalid_username_title"), true,
						rb.getString("invalid_username_text"));
				return false;
			}
		} else if (!passwordVerify()) {
			new CreationErrorDialog(window, rb.getString("invalid_password_title"), true,
					rb.getString("invalid_password_text"));
			return false;
		}
		return true;
	}

	private boolean passwordVerify() {
		if (!settingsView.getPwd().trim().equals("") && !settingsView.getPwd().equals(DEFAULT_PWD_TEXT)) {
			if (JSONCalls.reauth(user.getUsername(), settingsView.getPwd())) {
				if (!settingsView.getNewPwd().trim().equals("")
						&& !settingsView.getNewPwd().equals(DEFAULT_NEWPWD_TEXT)) {
					user.setPassword(settingsView.getNewPwd());
					return true;
				} else {
					user.setPassword(settingsView.getPwd());
					return true;
				}
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

	private void changePasswordFieldState() {
		String pwd = settingsView.getPwd();
		String pwdConfirm = settingsView.getNewPwd();

		if (!pwd.equals(DEFAULT_PWD_TEXT)) {
			settingsView.changePwdState();
		}
		if (!pwdConfirm.equals(DEFAULT_NEWPWD_TEXT)) {
			settingsView.changenewPwdState();
		}
	}

}
