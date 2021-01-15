package eus.healthit.bchef.core.view.panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;

public class CreateAccountView extends JPanel {

	Color bgColor = Color.white;

	public CreateAccountView() {
		super(new GridLayout());
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.setPreferredSize(new Dimension(480, 640));
		this.setBackground(bgColor);
		this.setOpaque(true);

		this.add(createBoxPanel());
	}

	private Component createBoxPanel() {
		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.add(createFlow());
		verticalBox.add(Box.createVerticalGlue());

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.add(Box.createHorizontalGlue());
		horizontalBox.add(verticalBox);
		horizontalBox.add(Box.createHorizontalGlue());

		return horizontalBox;
	}

	private Component createFlow() {
		JPanel flowPanel = new JPanel(new FlowLayout());
		flowPanel.add(createContentPanel());
		return flowPanel;
	}

	private Component createContentPanel() {
		JPanel contentPanel = new JPanel();
		
		
		return contentPanel;
	}

}
