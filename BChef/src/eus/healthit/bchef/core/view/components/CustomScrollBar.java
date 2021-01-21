package eus.healthit.bchef.core.view.components;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JScrollBar;

public class CustomScrollBar extends JScrollBar implements MouseListener {

	boolean expanded;
	boolean pressed;
	boolean ontop;
	boolean isHorizontal;

	public CustomScrollBar(boolean isHorizontal) {
		super();
		this.isHorizontal = isHorizontal;
		this.setUI(new CustomScrollbarUI());
		if (isHorizontal) {
			this.setPreferredSize(new Dimension(0, 10));
		} else {
			this.setPreferredSize(new Dimension(10, 0));
		}
		this.expanded = false;
		this.addMouseListener(this);
		this.setOpaque(false);
		this.setBackground(null);
	}

	public boolean isExpanded() {
		return expanded;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.expanded = true;
		this.ontop = true;
		// this.setPreferredSize(new Dimension(8,0));

	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (!pressed)
			this.expanded = false;
		this.ontop = false;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.expanded = true;
		this.pressed = true;

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.pressed = false;
		if (!ontop) {
			this.expanded = false;
		}

	}

	public boolean isHorizontal() {
		return isHorizontal;
	}

}
