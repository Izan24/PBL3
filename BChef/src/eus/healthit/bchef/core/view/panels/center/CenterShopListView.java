package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import eus.healthit.bchef.core.controllers.implementations.ShopListController;
import eus.healthit.bchef.core.controllers.view.ShopListButtonController;
import eus.healthit.bchef.core.controllers.view.ShopListControllerAC;
import eus.healthit.bchef.core.models.Item;
import eus.healthit.bchef.core.models.User;
import eus.healthit.bchef.core.view.items.ItemList;
import eus.healthit.bchef.core.view.items.ItemRenderer;

public class CenterShopListView extends JPanel {

	JTextField newElementField;
	User user;

	JList<Item> itemList;
	ItemList listModel;
	ItemRenderer renderer;

	ShopListButtonController buttonController;
	ShopListController listController;

	public CenterShopListView(User user) {
		super(new BorderLayout(10, 10));
		this.setBackground(Color.white);
		this.user = user;

		buttonController = new ShopListButtonController(this);

		listController = ShopListController.getShopListController();
		listController.setShopListView(this);

		/*
		 * AQUI TE TIENEN QUE PASAR EL USER E INICIAR LA LISTA DE ITEMS DE LA LISTA QUE
		 * TIENE EL USER
		 */
		initList();

		this.add(createNorthPanel(), BorderLayout.NORTH);
		this.add(createCenterPanel(), BorderLayout.CENTER);
		this.add(createSouthPanel(), BorderLayout.SOUTH);
	}

	private void initList() {
		itemList = new JList<>();

		listModel = new ItemList();
		// listModel.setList(user.getShopList());

		renderer = new ItemRenderer(buttonController);

		itemList.setModel(listModel);
		itemList.setCellRenderer(renderer);
	}

	private JPanel createNorthPanel() {
		JPanel northPanel = new JPanel(new FlowLayout());
		northPanel.setBackground(Color.white);
		northPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		newElementField = new JTextField();
		newElementField.setText("");
		newElementField.setToolTipText("Nuevo elemento");
		newElementField.setPreferredSize(new Dimension(300, 40));

		JButton buttonAdd = new JButton();
		buttonAdd.setIcon(new ImageIcon("resources/menuIcons/add_icon.png"));
		buttonAdd.setActionCommand(ShopListControllerAC.ADD);
		buttonAdd.addActionListener(buttonController);
		buttonAdd.setFocusable(false);
		buttonAdd.setPreferredSize(new Dimension(40, 40));
		buttonAdd.setBackground(new Color(224, 224, 224));
		/*
		 * Crea el controlador del panel de la lista de la compra
		 */
//		buttonAdd.addActionListener(listener);

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

		centerPanel.add(scrollPane);

		return centerPanel;
	}

	private JPanel createSouthPanel() {
		JPanel southPanel = new JPanel(new FlowLayout());
		southPanel.setBackground(Color.white);

		JButton deleteButton = new JButton("Eliminar");
		deleteButton.setBackground(new Color(202, 0, 0));
		deleteButton.setActionCommand(ShopListControllerAC.REMOVE);
		deleteButton.addActionListener(buttonController);
		deleteButton.setForeground(Color.white);
		deleteButton.setFocusable(false);

		southPanel.add(deleteButton);

		return southPanel;
	}

	public void addElement() {
		String spaceless = newElementField.getText().trim();

		if (!spaceless.equals("")) {
			listController.addElement(newElementField.getText());
			newElementField.setText("");
		}
	}

	public void removeElement() {
		try {
			listModel.deleteElement(itemList.getSelectedValue());
		} catch (IndexOutOfBoundsException e) {
		}
	}

	public ItemList getListModel() {
		return listModel;
	}

}
