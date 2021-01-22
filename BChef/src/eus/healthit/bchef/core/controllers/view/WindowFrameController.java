package eus.healthit.bchef.core.controllers.view;

import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.PrincipalView;
import eus.healthit.bchef.core.view.WindowFrame;
import eus.healthit.bchef.core.view.panels.CreateAccountView;
import eus.healthit.bchef.core.view.panels.LoginView;

public class WindowFrameController {

	PrincipalView principalView;
	LoginView loginView;
	CreateAccountView createAccountView;
	User user;

	WindowFrame window;

	public WindowFrameController(WindowFrame window) {
		this.window = window;

		initViews();
		setStartView();
	}

	private void initViews() {
		loginView = new LoginView(this, window);
		createAccountView = new CreateAccountView(this, window);
	}

	private void setStartView() {
		setLoginView();
		//setAppView(new User(0, "Izan", "izan", "izan@gmail.com", "Izan24", "aaaa"));
		window.setVisible(true);
	}

	public void setLoginView() {
		loginView = new LoginView(this, window);
		window.setContentPane(loginView);
		window.repaint();
		window.revalidate();
	}

	public void setCreateAccountView() {
		createAccountView = new CreateAccountView(this, window);
		window.setContentPane(createAccountView);
		window.repaint();
		window.revalidate();
	}

	public void setAppView(User user) {
		principalView = new PrincipalView(user, this, window);
		window.setContentPane(principalView);
		window.repaint();
		window.revalidate();
	}

}
