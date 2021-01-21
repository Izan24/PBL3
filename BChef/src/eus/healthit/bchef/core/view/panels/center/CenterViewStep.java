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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.models.RecipeStep;
import eus.healthit.bchef.core.view.borders.RoundedBorder;
import eus.healthit.bchef.core.view.borders.SearchBorder;
import eus.healthit.bchef.core.view.components.CustomScrollbarUI;
import eus.healthit.bchef.core.view.components.CustomTimer;

public class CenterViewStep extends JPanel {

	Font textFont = new Font("Segoe UI", Font.PLAIN, 25);

	Color bgColor = Color.white;

	JPanel southPanel;
	JTextArea instruction;
	JLabel imageLabel, titleLabel, logoLabel;

	public CenterViewStep() {
		super(new GridLayout());
		this.setBackground(bgColor);

		this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

		initJLabels();
		initTextAreas();
		initJpanel();

		this.add(createScrollPanel());
	}

	private void initJpanel() {
		southPanel = new JPanel(new FlowLayout());
		southPanel.setBackground(bgColor);
	}

	private void initJLabels() {
		imageLabel = new JLabel();
		imageLabel.setBackground(bgColor);

		titleLabel = new JLabel();
		titleLabel.setBackground(bgColor);

		logoLabel = new JLabel();
		logoLabel.setBackground(bgColor);
		logoLabel.setIcon(new ImageIcon("resources/menuIcons/bchef_icon.png"));

		titleLabel = new JLabel();
		titleLabel.setFont(textFont);
		titleLabel.setBackground(Color.white);
		titleLabel.setForeground(Color.gray);
	}

	private void initTextAreas() {
		instruction = new JTextArea();
		instruction.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		instruction.setForeground(Color.black);
		instruction.setMargin(new Insets(20, 20, 20, 20));
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

		scrollPane.setViewportView(createBoxPanel());

		return scrollPane;
	}

	private Component createBoxPanel() {
		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.add(createFlow());
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.setBackground(bgColor);
		verticalBox.setOpaque(true);

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.add(Box.createHorizontalGlue());
		horizontalBox.add(verticalBox);
		horizontalBox.add(Box.createHorizontalGlue());
		horizontalBox.setBackground(bgColor);
		horizontalBox.setOpaque(true);

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
				BorderFactory.createEmptyBorder(55, 35, 55, 35)));

		mainPanel.add(createTitlePanel(), BorderLayout.NORTH);
		mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
		mainPanel.add(createCounterPanel(), BorderLayout.SOUTH);

		return mainPanel;
	}

	private Box createTitlePanel() {
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		horizontalBox.add(logoLabel);
		horizontalBox.add(Box.createRigidArea(new Dimension(6, 0)));
		horizontalBox.add(titleLabel);

		return horizontalBox;
	}

	private Component createCenterPanel() {
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(bgColor);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 1;
		constraints.weightx = 1;
		constraints.insets = new Insets(0, 35, 0, 35);
		constraints.gridx = 0;
		constraints.gridy = 0;

		centerPanel.add(createStepTextPanel(), constraints);
		constraints.gridx = 1;
//		constraints.gridwidth = GridBagConstraints.REMAINDER;
		centerPanel.add(createImagePanel(), constraints);

		return centerPanel;
	}

	private Component createImagePanel() {
		JPanel imagePanel = new JPanel(new GridLayout());
		imagePanel.setBackground(bgColor);

		imagePanel.add(imageLabel);

		return imagePanel;
	}

	private Component createStepTextPanel() {
		JPanel scrollPanel = new JPanel(new FlowLayout());
		scrollPanel.setBackground(bgColor);

		scrollPanel.add(createStepTextSlide());

		return scrollPanel;
	}

	private Component createStepTextSlide() {
		JScrollPane slide = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		slide.setBackground(bgColor);
		slide.setOpaque(true);
		slide.setPreferredSize(new Dimension(500, 225));
		slide.setBorder(new SearchBorder(20, new Color(200, 200, 200), false));

		slide.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		slide.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		slide.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		slide.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		slide.setViewportView(createInstructionPanel());

		return slide;
	}

	private Component createInstructionPanel() {
		JPanel instructionPanel = new JPanel(new GridLayout());
		instructionPanel.setBackground(bgColor);

		instructionPanel.add(instruction);

		return instructionPanel;
	}

	private Component createCounterPanel() {
		return southPanel;
	}

	public void setRecipe(Recipe recipe) {
		updateView(recipe.getSteps().get(0));
	}

	public void setStep(RecipeStep step) {
		updateView(step);
		System.out.println("Update view");
	}

	private void updateView(RecipeStep step) {
		instruction.setText(step.getText());
		imageLabel.setIcon(new ImageIcon(step.getImage()));
		titleLabel.setText("Paso Nº " + step.getId());

		try {
			if (step.getDuration().toMillis() != 0) {
				southPanel.add(new CustomTimer(step.getDuration(), textFont));
			}
		} catch (Exception e) {
			System.out.println("no tiene owo");
		}
	}
}
