package eus.healthit.bchef.core.app.controllers.northView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ResourceBundle;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.app.controllers.CenterListController;
import eus.healthit.bchef.core.app.controllers.centerView.CenterViewController;
import eus.healthit.bchef.core.app.ui.panels.NorthView;
import eus.healthit.bchef.core.models.Recipe;

public class NorthViewController implements ActionListener {

	private static ResourceBundle rb = ResourceBundle.getBundle("MessagesBundle");

	NorthView view;
	CenterViewController centerController;

	public NorthViewController(NorthView view, CenterViewController centerController) {
		this.view = view;
		this.centerController = centerController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case NorthViewControllerAC.SEARCH:
			if (!view.getText().equals(rb.getString("north_search_text"))) {
				List<Recipe> list = JSONCalls.search(view.getText(), 0);
				CenterListController.setShowList(list);
				centerController.setListView();
			}
			break;
		default:
			break;
		}
	}

}
