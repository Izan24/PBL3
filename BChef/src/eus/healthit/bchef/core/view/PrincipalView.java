package eus.healthit.bchef.core.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PrincipalView extends JPanel {

	LeftMenuView leftMenuView;
	
	public PrincipalView() {
		super(new BorderLayout());
		leftMenuView = new LeftMenuView();
		
		setContent();
	}

	private void setContent() {
		this.add(leftMenuView, BorderLayout.WEST);
	}
}