package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.ImageCapabilities;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eus.healthit.bchef.core.models.RecipeStep;

public class CenterStepView extends JPanel {

	JLabel instructionPanel;
	JLabel imageLabel;

	public CenterStepView() {
		super(new GridLayout(1, 1));
		this.setSize(new Dimension(90, 20));

		this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
	}

	public Component createContent() {
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));

		mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
		mainPanel.add(createSouthPanel(), BorderLayout.SOUTH);

		return mainPanel;
	}

	private Component createCenterPanel() {
		JPanel centerPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 140, 0, 140);
		constraints.gridx = 0;
		constraints.gridy = 0;

		instructionPanel = new JLabel();
		imageLabel = new JLabel();

		centerPanel.add(instructionPanel);
		centerPanel.add(imageLabel);

		return centerPanel;
	}

	private Component createSouthPanel() {
		JPanel southPanel = new JPanel(new GridLayout());

		return southPanel;
	}

	public void setStep(RecipeStep step) {
		updateView(step);
		System.out.println("Update view");
		this.repaint();
	}

	private void updateView(RecipeStep step) {
		instructionPanel.setText(step.getText());
		imageLabel.setText(step.getImagePath());
	}
}
