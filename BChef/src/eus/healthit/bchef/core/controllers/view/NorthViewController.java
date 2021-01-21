package eus.healthit.bchef.core.controllers.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.models.Recipe;
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

			if (!view.getText().equals(NorthView.DEFAULT_SEARCH_TEXT_STRING)) {
				List<Recipe> list = JSONCalls.search(view.getText(), 0);
				CenterListController.setShowList(list);
			}
			break;
		default:
			break;
		}
	}

}
