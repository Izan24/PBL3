package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.view.borders.RoundedBorder;
import eus.healthit.bchef.core.view.borders.SearchBorder;

public class CenterStepView extends JPanel {

	Font textFont = new Font("Segoe UI", Font.PLAIN, 20);

	Color bgColor = Color.white;

	JTextArea instruction;
	JLabel imageLabel;

	public CenterStepView() {
		super(new GridLayout(1, 1));
		this.setSize(new Dimension(90, 20));
		this.setBackground(bgColor);

		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

		initJLabels();
		initTextAreas();

		this.add(createBoxPanel());
	}

	private void initJLabels() {
		imageLabel = new JLabel();
		imageLabel.setBackground(bgColor);
	}

	private void initTextAreas() {
		instruction = new JTextArea();
		instruction.setFont(textFont);
		instruction.setForeground(Color.black);
		instruction.setMargin(new Insets(20, 20, 20, 20));
		instruction.setPreferredSize(new Dimension(314, 79));
		instruction.setLineWrap(true);
		instruction.setWrapStyleWord(true);
		instruction.setFocusable(false);
	}

	private Component createBoxPanel() {
		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.add(createFlow());
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.setBackground(bgColor);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.add(Box.createHorizontalGlue());
		horizontalBox.add(verticalBox);
		horizontalBox.add(Box.createHorizontalGlue());
		horizontalBox.setBackground(bgColor);

		return horizontalBox;
	}

	private Component createFlow() {
		JPanel flowPanel = new JPanel(new FlowLayout());
		flowPanel.setBackground(bgColor);

		flowPanel.add(createContent());

		return flowPanel;
	}

	public Component createContent() {
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(bgColor);
		mainPanel.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
				BorderFactory.createEmptyBorder(60, 40, 60, 40)));

		mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
		mainPanel.add(createCounterPanel(), BorderLayout.SOUTH);

		return mainPanel;
	}

	private Component createCenterPanel() {
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(bgColor);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 50, 0, 50);
		constraints.gridx = 0;
		constraints.gridy = 0;

		centerPanel.add(createImagePanel(), constraints);
		constraints.gridx = 1;
		centerPanel.add(createStepTextPanel(), constraints);

		return centerPanel;
	}

	private Component createImagePanel() {
		JPanel imagePanel = new JPanel(new GridLayout());
		imagePanel.setBackground(bgColor);

		imagePanel.add(imageLabel);

		return imagePanel;
	}

	private Component createStepTextPanel() {
		JPanel stepTextPanel = new JPanel(new GridLayout());
		stepTextPanel.setBackground(bgColor);
		stepTextPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10),
				new SearchBorder(20, new Color(200, 200, 200), false)));

		stepTextPanel.add(instruction);

		return stepTextPanel;
	}

	private Component createCounterPanel() {
		JPanel southPanel = new JPanel(new GridLayout());

		return southPanel;
	}

	public void setStep(RecipeStep step) {
		updateView(step);
		System.out.println("Update view");
	}

	private void updateView(RecipeStep step) {
		instruction.setText(step.getText());
		imageLabel.setIcon(new ImageIcon(step.getImage()));

//		if (step.getDuration().toMillis() != 0) {
//			// Crear un panel de timer con el tiempo
//		}
	}
}
