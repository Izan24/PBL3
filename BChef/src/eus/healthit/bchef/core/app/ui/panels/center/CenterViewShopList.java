package eus.healthit.bchef.core.app.ui.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ResourceBundle;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.app.controllers.DefaultTextController;
import eus.healthit.bchef.core.app.controllers.DoubleClickListener;
import eus.healthit.bchef.core.app.controllers.interfaces.IClickable;
import eus.healthit.bchef.core.app.controllers.shoplist.ShopListController;
import eus.healthit.bchef.core.app.controllers.shoplist.ShopListControllerAC;
import eus.healthit.bchef.core.app.ui.borders.SearchBorder;
import eus.healthit.bchef.core.app.ui.components.CustomScrollbarUI;
import eus.healthit.bchef.core.app.ui.components.UIRoundButton;
import eus.healthit.bchef.core.app.ui.items.ItemList;
import eus.healthit.bchef.core.app.ui.items.ItemRenderer;
import eus.healthit.bchef.core.models.Item;
import eus.healthit.bchef.core.models.User;

public class CenterViewShopList extends JPanel implements IClickable {

	private static final long serialVersionUID = 8838096527575742391L;

	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");

	String DEFAULT_ADD_ELEMENT_TEXT = rb.getString("add_element_text");

	User user;

	JButton buttonAdd, deleteButton;

	JList<Item> itemList;
	ItemList listModel;
	ItemRenderer renderer;

	JTextField newElementField;

	ShopListController controller;
	DoubleClickListener listener;

	Font textFont = new Font("Roboto", Font.PLAIN, 20);

	public CenterViewShopList(User user) {
		super(new BorderLayout(10, 10));
		this.setBackground(Color.white);
		this.user = user;

		controller = ShopListController.getShopListController();
		controller.setShopListView(this);

		initButtons();
		initTextFields();
		initList();

		this.add(createNorthPanel(), BorderLayout.NORTH);
		this.add(createCenterPanel(), BorderLayout.CENTER);
		this.add(createSouthPanel(), BorderLayout.SOUTH);
	}

	private void initButtons() {
		buttonAdd = new JButton();
		buttonAdd.setIcon(new ImageIcon("resources/menuIcons/add_icon_default32.png"));
		buttonAdd.setActionCommand(ShopListControllerAC.ADD);
		buttonAdd.addActionListener(controller);
		buttonAdd.setFocusable(false);
		buttonAdd.setBorder(BorderFactory.createEmptyBorder());
		buttonAdd.setBackground(Color.white);
		buttonAdd.setForeground(Color.white);
		buttonAdd.setPreferredSize(new Dimension(40, 40));
		buttonAdd.setBackground(new Color(224, 224, 224));

		deleteButton = new JButton(rb.getString("remove_text"));
		deleteButton.setPreferredSize(new Dimension(150, 35));
		deleteButton.setBackground(new Color(243, 69, 65));
		deleteButton.setForeground(Color.white);
		deleteButton.setFont(textFont);
		deleteButton.setBorder(BorderFactory.createEmptyBorder());
		deleteButton.setFocusable(false);
		deleteButton.setUI(new UIRoundButton(deleteButton, 30, new Color(243, 69, 65), Color.white,
				new Font("Segoe UI", Font.BOLD, 15), controller, ShopListControllerAC.REMOVE));

	}

	private void initTextFields() {
		newElementField = new JTextField();
		newElementField.setText(rb.getString("new_element_text"));
		newElementField.setForeground(Color.GRAY);
		newElementField.addFocusListener(new DefaultTextController(newElementField, DEFAULT_ADD_ELEMENT_TEXT));
		newElementField.setPreferredSize(new Dimension(300, 40));
		newElementField.setBorder(new SearchBorder(20, Color.GRAY, false));
		newElementField.setFont(textFont);
	}

	private void initList() {
		itemList = new JList<>();
		listModel = new ItemList();
		listModel.setList(user.getShopList());

		renderer = new ItemRenderer();
		listener = new DoubleClickListener(this);

		itemList.setModel(listModel);
		itemList.setCellRenderer(renderer);
		itemList.addMouseListener(listener);
	}

	private JPanel createNorthPanel() {
		JPanel northPanel = new JPanel(new FlowLayout());
		northPanel.setBackground(Color.white);
		northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		northPanel.add(newElementField);
		northPanel.add(buttonAdd);

		return northPanel;
	}

	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel(new GridLayout());
		centerPanel.setBorder(BorderFactory.createEmptyBorder());
		centerPanel.setBackground(Color.white);

		JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setViewportView(itemList);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		scrollPane.getVerticalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
		scrollPane.getHorizontalScrollBar().setUI(new CustomScrollbarUI());
		scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 10));

		centerPanel.add(scrollPane);

		return centerPanel;
	}

	private JPanel createSouthPanel() {
		JPanel southPanel = new JPanel(new FlowLayout());
		southPanel.setBackground(Color.white);

		southPanel.add(deleteButton);

		return southPanel;
	}

	public ItemList getListModel() {
		return listModel;
	}

	public String getNewElementname() {
		return newElementField.getText();
	}

	public int getUserID() {
		return user.getId();
	}

	public Item getSelectedItem() {
		return itemList.getSelectedValue();
	}

	@Override
	public void clicked() {
		Item selectedItem = itemList.getSelectedValue();
		selectedItem.flipBought();
		JSONCalls.shoplistTicked(selectedItem);
		this.repaint();
	}

	public void resetText() {
		newElementField.setText("");
	}

}
