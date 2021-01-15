package eus.healthit.bchef.core.view;

import java.awt.Color;

import javax.swing.JFrame;

import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.panels.LoginView;

public class WindowFrame extends JFrame {

	PrincipalView principalView;
	LoginView loginView;
	User user;

	public WindowFrame() {
		super("B-Chef");

		// AQUI HAY QUE HACER QUE NOS SALGA LA LOGIN SCREEN Y QUE TENGAS QUE CREAR O
		// LOGEAR CON UN USER SI O SI SI QUIERES USAR LA APP.
		// TENEMOS QUE PASAR TODO ESTO DEL LOGIN A UN LoginView para que aqui no haya
		// nada de codigo.
		// user = new LoginScreen();

		// Esto es provisional hasta que se haya creado el login y funcione
		user = new User(0, "Test", "TestUser777", "test.user@gmail.com", "User", "User");

		setWindow();

		
		/*
		 * CREA UN CONTROLADOR DE ESTA VENTANA PARA PODER SWAPEAR ENTRE LOGIN Y CREATEUSER
		 */
//		loginWindow();
		createUserWIndow();
		appWindow();
	}


	private void loginWindow() {
		loginView = new LoginView();
		this.setContentPane(loginView);
		this.setVisible(true);
	}

	private void createUserWIndow() {
		// TODO Auto-generated method stub
		
	}
	private void appWindow() {
		principalView = new PrincipalView(user);
		this.setContentPane(principalView);
		this.setVisible(true);
	}

	private void setWindow() {
		this.setSize(1250, 750);
		this.setLocation(300, 150);
		this.setResizable(true);
		this.setBackground(Color.white);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

//		this.setUndecorated(true);
	}
}
