package eus.healthit.bchef.core.app.ui.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;
import eus.healthit.bchef.core.app.controllers.recipe.rating.RecipeRatingController;
import eus.healthit.bchef.core.app.controllers.recipe.rating.RecipeRatingControllerAC;
import eus.healthit.bchef.core.app.ui.borders.RoundedBorder;
import eus.healthit.bchef.core.app.ui.components.CustomScrollbarUI;
import eus.healthit.bchef.core.app.ui.components.UIRoundButton;
import eus.healthit.bchef.core.models.Recipe;

public class CenterViewRecipeRating extends JPanel {

	Font textFont = new Font("Segoe UI", Font.PLAIN, 25);

	Color bgColor = Color.white;

	RecipeRatingController controller;

	JButton rateButton, skipButton;

	JSlider slider;
	JLabel imageLabel, titleLabel, logoLabel, halfStar, fullStar;
	JPanel starPanel;

	public CenterViewRecipeRating(CenterViewController centerController) {
		super(new GridLayout());
		this.setBackground(bgColor);

		this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

		controller = new RecipeRatingController(this, centerController);

		initJLabels();
		initJSliders();
		initJPanels();
		initJButtons();
		setStarView(5);

		this.add(createScrollPanel());
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

		halfStar = new JLabel();
		halfStar.setBackground(bgColor);
		halfStar.setIcon(new ImageIcon("resources/recipeIcons/halfStar.png"));

		fullStar = new JLabel();
		fullStar.setBackground(bgColor);
		fullStar.setIcon(new ImageIcon("resources/recipeIcons/fullStarBig.png"));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initJSliders() {
		slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
		slider.setBackground(bgColor);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(1);
		slider.addChangeListener(controller);

		slider.setPaintLabels(true);

		Hashtable position = new Hashtable();
		for (int i = 0; i <= 10; i++) {
			position.put(i, new JLabel(String.valueOf(i)));
		}

		slider.setLabelTable(position);
	}

	private void initJPanels() {
		starPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		starPanel.setBackground(bgColor);
//		starPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
//		starPanel.setPreferredSize(new Dimension(572, 200));
	}

	private void initJButtons() {

		rateButton = new JButton("Enviar valoraci�n");
		rateButton.setPreferredSize(new Dimension(150, 35));
		rateButton.setBackground(new Color(28, 162, 243));
		rateButton.setForeground(Color.white);
		rateButton.setFont(textFont);
		rateButton.setBorder(BorderFactory.createEmptyBorder());
		rateButton.setFocusable(false);
		rateButton.setUI(new UIRoundButton(rateButton, 30, new Color(28, 162, 243), Color.white,
				new Font("Segoe UI", Font.BOLD, 13), controller, RecipeRatingControllerAC.SUBMIT_RATING));

		skipButton = new JButton("No quiero valorar");
		skipButton.setPreferredSize(new Dimension(150, 40));
		skipButton.setBackground(bgColor);
		skipButton.setForeground(new Color(28, 162, 243));
		skipButton.setFont(textFont);
		skipButton.setFocusable(false);
		skipButton.setUI(new UIRoundButton(skipButton, 30, bgColor, new Color(234, 246, 254), new Color(210, 236, 252),
				new Color(28, 162, 243), new Font("Segoe UI", Font.BOLD, 13), controller,
				RecipeRatingControllerAC.SKIP_RATING, "No quiero valorar", "No quiero valorar"));
		skipButton.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
				BorderFactory.createEmptyBorder(60, 40, 60, 40)));
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
		mainPanel.setPreferredSize(new Dimension(936, 457));
		mainPanel.setBorder(BorderFactory.createCompoundBorder(new RoundedBorder(30, new Color(148, 204, 255)),
				BorderFactory.createEmptyBorder(55, 35, 55, 35)));

		mainPanel.add(createTitlePanel(), BorderLayout.NORTH);
		mainPanel.add(createCenterPanel(), BorderLayout.CENTER);
		mainPanel.add(createButton(), BorderLayout.SOUTH);

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
		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(bgColor);

		centerPanel.add(createImagePanel(), BorderLayout.WEST);
		centerPanel.add(createStarPanelBox(), BorderLayout.CENTER);
		centerPanel.add(createSliderPanel(), BorderLayout.SOUTH);

		return centerPanel;
	}

	private Component createStarPanelBox() {
		Box verticalBox = Box.createVerticalBox();
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.add(createStarPanel());
		verticalBox.add(Box.createVerticalGlue());
		verticalBox.setBackground(bgColor);
		verticalBox.setOpaque(true);

		return verticalBox;
	}

	private Component createStarPanel() {
		JPanel flowStarPanel = new JPanel(new GridLayout());
		flowStarPanel.setBackground(bgColor);

		flowStarPanel.add(starPanel);

		return flowStarPanel;
	}

	private Component createImagePanel() {
		JPanel imagePanel = new JPanel(new GridLayout());
		imagePanel.setBackground(bgColor);

		imagePanel.add(imageLabel);

		return imagePanel;
	}

	private Component createSliderPanel() {
		JPanel scrollPanel = new JPanel(new GridLayout());
		scrollPanel.setBackground(bgColor);

		scrollPanel.add(slider);

		return scrollPanel;
	}

	private Component createButton() {
		JPanel buttonPanel = new JPanel(new BorderLayout(30, 30));
		buttonPanel.setBackground(bgColor);

		buttonPanel.add(rateButton, BorderLayout.EAST);
		buttonPanel.add(skipButton, BorderLayout.WEST);
		return buttonPanel;
	}

	public void setRecipe(Recipe recipe) {
		updateView(recipe);
	}

	private void updateView(Recipe recipe) {
		imageLabel.setIcon(new ImageIcon(recipe.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
		titleLabel.setText("Valoraci�n de " + recipe.getName());
	}

	public void setStarView(int rating) {
		int emptyStars = Math.round(10 - rating);

		starPanel.removeAll();
		starPanel.revalidate();

		JLabel halfStar = new JLabel();
		halfStar.setIcon(new ImageIcon("resources/recipeIcons/halfStar64.png"));
		halfStar.setHorizontalAlignment(JLabel.CENTER);

		while ((rating - 2) >= 0) {
			JLabel fullStar = new JLabel();
			fullStar.setIcon(new ImageIcon("resources/recipeIcons/fullStar64.png"));
			fullStar.setHorizontalAlignment(JLabel.CENTER);

			starPanel.add(fullStar);
			rating = rating - 2;
		}
		if (rating == 1) {
			starPanel.add(halfStar);
		}

		if (emptyStars != 0) {
			while (emptyStars - 2 >= 0) {
				JLabel emptyStar = new JLabel();
				emptyStar.setIcon(new ImageIcon("resources/recipeIcons/emptyStar64.png"));
				emptyStar.setHorizontalAlignment(JLabel.CENTER);

				starPanel.add(emptyStar);
				emptyStars = emptyStars - 2;
			}
		}

		this.repaint();
		this.revalidate();
	}
}
