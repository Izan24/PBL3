package creadorIngredientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Principal extends JFrame implements ActionListener {

	JTextField nameField;
	JComboBox<String> ingredientBox;

	public Principal() {
		setWindow();
		this.setContentPane(appWindow());
		this.setVisible(true);
	}

	private JPanel appWindow() {
		JPanel panel = new JPanel(new BorderLayout(50, 50));
		panel.setBackground(new Color(21,32,43));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 200, 10));

		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(200, 100));
		nameField.setFont(new Font("Arial",Font.PLAIN, 20));


		JButton botonAñadir = new JButton("LEs GOOO! uwu");
		botonAñadir.setPreferredSize(new Dimension(200, 100));
		botonAñadir.setActionCommand("añadir");
		botonAñadir.addActionListener(this);

		ingredientBox = new JComboBox<>();
		ingredientBox.setPreferredSize(new Dimension(200, 100));
		ingredientBox.setFont(new Font("Arial",Font.PLAIN, 20));
		ingredientBox.addItem("Aceites");
		ingredientBox.addItem("Cereales");
		ingredientBox.addItem("Frutos secos");
		ingredientBox.addItem("Legumbres");
		ingredientBox.addItem("Frutas");
		ingredientBox.addItem("Verduras");
		ingredientBox.addItem("Huevos");
		ingredientBox.addItem("Pescados");
		ingredientBox.addItem("Lacteos");
		ingredientBox.addItem("Carnes");
		ingredientBox.addItem("Grasas");
		ingredientBox.addItem("Dulces");

		JPanel panel2 = new JPanel(new FlowLayout());
		panel2.setBorder(BorderFactory.createEmptyBorder(100, 0, 100, 0));
		panel2.setBackground(new Color(21,32,43));


		panel2.add(nameField);
		panel2.add(ingredientBox);
		panel2.add(botonAñadir);

		panel.add(panel2, BorderLayout.CENTER);
		return panel;
	}

	private void setWindow() {
		this.setSize(1250, 600);
		this.setLocation(300, 150);
		this.setResizable(true);
		this.setBackground(Color.white);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

//		this.setUndecorated(true);
	}

	public static void main(String[] args) {
		Principal programa = new Principal();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "añadir":
			if (!nameField.getText().equals("") || !nameField.getText().equals(" ")) {
				IngredientRepository
						.insert(new Ingredient(nameField.getText(), (String) ingredientBox.getSelectedItem()));
				System.out.println("añadido");
				nameField.setText("");
			}else {
				System.out.println("no añadido");
			}
			
			break;
		default:
			System.out.println("Mal");
			break;
		}
	}

}
