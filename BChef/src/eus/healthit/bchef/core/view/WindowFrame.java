package eus.healthit.bchef.core.view;

import javax.swing.JFrame;

import eus.healthit.bchef.core.WindowActions;
import eus.healthit.bchef.core.models.User;

public class WindowFrame extends JFrame {

	PrincipalView view;
	WindowActions actions;
	User user;

	public WindowFrame() {
		super("B-Chef");
		
		
		// AQUI HAY QUE HACER QUE NOS SALGA LA LOGIN SCREEN Y QUE TENGAS QUE CREAR O LOGEAR CON UN USER SI O SI SI QUIERES USAR LA APP.
		//TENEMOS QUE PASAR TODO ESTO DEL LOGIN A UN LoginView para que aqui no haya nada de codigo.
		//user = new LoginScreen();
		
		//Esto es provisional hasta que se haya creado el login y funcione
		user = new User(0, "Test", "TestUser777", "test.user@gmail.com", "User", "User");

		actions = new WindowActions(this);
		view = new PrincipalView(user);

		setWindow();
	}

	private void setWindow() {
		this.setSize(1250, 750);
		this.setLocation(300, 150);
		this.setResizable(true);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(view);

		this.setVisible(true);
		
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//				| UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		}
	}
}
