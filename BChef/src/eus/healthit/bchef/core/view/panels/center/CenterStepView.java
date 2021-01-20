package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.view.borders.RoundedBorder;
import eus.healthit.bchef.core.view.borders.SearchBorder;
import eus.healthit.bchef.core.view.components.CustomScrollbarUI;

public class CenterStepView extends JPanel {

	Font textFont = new Font("Segoe UI", Font.PLAIN, 25);

	Color bgColor = Color.white;

	JTextArea instruction;
	JLabel imageLabel;

	public CenterStepView() {
		super(new GridLayout());
		this.setBackground(bgColor);

		this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

		initJLabels();
		initTextAreas();

		this.add(createScrollPanel());
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
//		instruction.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.red));
//		instruction.setPreferredSize(new Dimension(314, 79));
		instruction.setLineWrap(true);
		instruction.setWrapStyleWord(true);
		instruction.setFocusable(false);
	}

	private Component createScrollPanel() {
		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBackground(bgColor);
		scrollPane.setOpaque(true);

		scrollPane.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.setViewportView(createFlow());

		return scrollPane;
	}

	private Component createBoxPanel() {
		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.add(createFlow());
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.setBackground(bgColor);
//		verticalBox.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.add(Box.createHorizontalGlue());
		horizontalBox.add(verticalBox);
		horizontalBox.add(Box.createHorizontalGlue());
		horizontalBox.setBackground(bgColor);
//		verticalBox.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.blue));

		return horizontalBox;
	}

	private Component createFlow() {
		JPanel flowPanel = new JPanel(new GridLayout());
		flowPanel.setBackground(bgColor);
//		flowPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.magenta));

		flowPanel.add(createContent());

		return flowPanel;
	}

	public Component createContent() {
		JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
		mainPanel.setBackground(bgColor);
		mainPanel.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
				BorderFactory.createEmptyBorder(55, 35, 55, 35)));
//		mainPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));

		mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
		mainPanel.add(createCounterPanel(), BorderLayout.SOUTH);

		return mainPanel;
	}

	private Component createCenterPanel() {
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(bgColor);
//		 centerPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.pink));

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 35, 0, 35);
		constraints.gridx = 0;
		constraints.gridy = 0;

		centerPanel.add(createImagePanel(), constraints);
		constraints.gridx = 1;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		centerPanel.add(createStepTextSlide(), constraints);

		return centerPanel;
	}

	private Component createImagePanel() {
		JPanel imagePanel = new JPanel(new GridLayout());
		imagePanel.setBackground(bgColor);

		imagePanel.add(imageLabel);

		return imagePanel;
	}

	private Component createStepTextSlide() {
		JScrollPane slide = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		slide.setBackground(bgColor);
		slide.setOpaque(true);
		slide.setPreferredSize(new Dimension(500, 300));
		slide.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));
//		slide.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.green));

		slide.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		slide.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		slide.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		slide.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		slide.setViewportView(instruction);

		return slide;
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
