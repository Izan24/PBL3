package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.dialogs.FileChooser;
import eus.healthit.bchef.core.view.panels.center.CenterViewProfileSettings;

public class ProfileSettingsController implements ActionListener, IRoundButtonListener {

	User user;
	CenterViewProfileSettings settingsView;
	WindowFrameController windowController;

	public ProfileSettingsController(User user, CenterViewProfileSettings settingsView,
			WindowFrameController windowController) {
		this.user = user;
		this.settingsView = settingsView;
		this.windowController = windowController;
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
				User newUser = new User(user.getId(), settingsView.getName(), settingsView.getSurname(),
						settingsView.getImage(), settingsView.getEmail(), settingsView.getUsername(), "pwd",
						user.getFollowed(), user.getFollowers(), user.getPublished(), user.getSaved(),
						user.getShopList(), user.getHistory());

				user = newUser;
				System.out.println(user);
				
			}

			break;

		case ProfileSettingsControllerAC.LOG_OUT:
			System.out.println("Envia el user entero a la database antes de logout");
			windowController.setLoginView();
			break;
		}
	}

	private boolean verifyParameters() {

		return true;
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
		String pwdConfirm = settingsView.getnewPwd();

		if (!pwd.equals(settingsView.DEFAULT_PWD_TEXT)) {
			settingsView.changePwdState();
		}
		if (!pwdConfirm.equals(settingsView.DEFAULT_CONFPWD_TEXT)) {
			settingsView.changenewPwdState();
		}
	}

}
