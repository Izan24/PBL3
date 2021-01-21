package eus.healthit.bchef.core.view.dialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eus.healthit.bchef.core.controllers.interfaces.IRoundButtonListener;
import eus.healthit.bchef.core.view.components.UIRoundButton;

public class CreationErrorDialog extends JDialog implements IRoundButtonListener {

	final String DISPOSE_COMAND = "dispose";

	JLabel textlabel, iconLabel;
	JButton confirmButton;
	String text;

//	Color bgColor = new Color(235, 235, 235);
	Color bgColor = Color.white;

	public CreationErrorDialog(JFrame frame, String title, boolean mode, String text) {
		super(frame, title, mode);

		this.text = text;
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		initJbuttons();
		initJlabels();

		this.setContentPane(createContent());

		this.pack();
		this.setLocation(frame.getX() + (frame.getWidth() / 2) - (this.getWidth() / 2),
				frame.getY() + (frame.getHeight() / 2) - (this.getHeight() / 2));

		this.setVisible(true);
	}

	private void initJbuttons() {
		confirmButton = new JButton("Aceptar");
		confirmButton.setPreferredSize(new Dimension(80, 30));
		confirmButton.setBackground(new Color(28, 162, 243));
		confirmButton.setForeground(Color.white);
		confirmButton.setBorder(BorderFactory.createEmptyBorder());
		confirmButton.setFocusable(false);
		confirmButton.setUI(new UIRoundButton(confirmButton, 30, new Color(28, 162, 243), Color.white,
				new Font("Roboto", Font.PLAIN, 15), this, DISPOSE_COMAND));
	}

	private void initJlabels() {
		textlabel = new JLabel();
//		textlabel.setSize(new Dimension(100, 90));
		textlabel.setFont(new Font("Roboto", Font.PLAIN, 15));
		textlabel.setBackground(Color.white);
		textlabel.setFocusable(false);
		textlabel.setText(text);

		iconLabel = new JLabel();
		iconLabel.setBackground(Color.white);
		iconLabel.setFocusable(false);
		iconLabel.setIcon(new ImageIcon("resources/menuIcons/bchef_icon.png"));
	}

	private Container createContent() {
		JPanel errorPanel = new JPanel(new BorderLayout());
//		errorPanel.setPreferredSize(new Dimension(320, 110));
		errorPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		errorPanel.setBackground(bgColor);

		errorPanel.add(createCenterPanel(), BorderLayout.CENTER);
		errorPanel.add(createButtonPanel(), BorderLayout.SOUTH);
		return errorPanel;
	}

	private Component createCenterPanel() {
		JPanel centerPanel = new JPanel(new FlowLayout());
		centerPanel.setBackground(bgColor);

		centerPanel.add(iconLabel);
		centerPanel.add(textlabel);

		return centerPanel;
	}

	private Component createButtonPanel() {
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.setBackground(bgColor);

		buttonPanel.add(confirmButton);

		return buttonPanel;
	}

	@Override
	public void actionPerformed(String command) {
		switch (command) {
		case DISPOSE_COMAND:
			this.dispose();
			break;
		}
	}

}
