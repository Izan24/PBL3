package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eus.healthit.bchef.core.view.panels.NorthView;

public class NorthViewController implements ActionListener {

	NorthView view;

	public NorthViewController(NorthView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case NorthViewControllerAC.SEARCH:

			if (!view.getText().equals(view.DEFAULT_SEARCH_TEXT_STRING)) {
				System.out.println(view.getText());
				// aqui abajo le tienes que pasar lo que te devuelve el query de urko
				// CenterListController.setShowList();
			}
			break;
		default:
			break;
		}
	}

}
