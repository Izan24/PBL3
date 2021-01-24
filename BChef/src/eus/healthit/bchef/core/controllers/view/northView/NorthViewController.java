package eus.healthit.bchef.core.controllers.view.northView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.omg.PortableServer.THREAD_POLICY_ID;

import eus.healthit.bchef.core.api.JSONCalls;
import eus.healthit.bchef.core.controllers.view.CenterListController;
import eus.healthit.bchef.core.controllers.view.centerView.CenterViewController;
import eus.healthit.bchef.core.models.Recipe;
import eus.healthit.bchef.core.view.panels.NorthView;
import eus.healthit.bchef.core.view.panels.center.CenterView;

public class NorthViewController implements ActionListener {

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
			if (!view.getText().equals(NorthView.DEFAULT_SEARCH_TEXT_STRING)) {
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
