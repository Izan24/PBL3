package eus.healthit.bchef.core.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import eus.healthit.bchef.core.view.panels.center.CenterShopListView;

public class ShopListController implements ActionListener, MouseListener {

	CenterShopListView listView;

	public ShopListController(CenterShopListView listView) {
		this.listView = listView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case ShopListControllerAC.ADD:
			listView.addElement();
			break;
		case ShopListControllerAC.REMOVE:
			listView.removeElement();
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		e.getSource();
		System.out.println("hola");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("entered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("exited");

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("pressed");
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("releaseado");
	}
}
