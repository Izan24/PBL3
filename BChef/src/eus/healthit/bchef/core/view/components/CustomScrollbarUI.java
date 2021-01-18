package eus.healthit.bchef.core.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalScrollBarUI;

class CustomScrollbarUI extends MetalScrollBarUI {
	public static final Integer RADIUS = 10;

	public CustomScrollbarUI() {

	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		Graphics2D g2 = (Graphics2D) g;
		System.out.println("X:" + thumbBounds.x + ", Y:" + thumbBounds.y);
		g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g2.translate(thumbBounds.x, thumbBounds.y);
		g2.setColor(new Color(152, 188, 245));
		CustomScrollBar cs = (CustomScrollBar) c;
		if (!cs.isHorizontal()) {
			if (cs.isExpanded()) {
				System.out.println("Exp");
				g2.fillRoundRect(0, 0, (int) (thumbBounds.getWidth()), (int) (thumbBounds.getHeight()), RADIUS, RADIUS);
			} else {
				System.out.println("NExp");
				g2.fillRect((int) thumbBounds.getWidth() - 2, 0, 2, (int) thumbBounds.getHeight());
			}
		} else {
			if (cs.isExpanded()) {
				System.out.println("Exp");
				g2.fillRoundRect(0, 0, (int) (thumbBounds.getWidth()), (int) (thumbBounds.getHeight()), RADIUS, RADIUS);
			} else {
				System.out.println("NExp");
				g2.fillRect(0, (int) thumbBounds.getHeight() - 2, (int) thumbBounds.getWidth(), 2);
			}
		}
	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g2.setColor(Color.white);
		g2.translate(trackBounds.x, trackBounds.y);
		CustomScrollBar cs = (CustomScrollBar) c;
		if (!cs.isHorizontal()) {
			if (cs.isExpanded()) {
				g2.fillRect(0, 0, (int) (trackBounds.getWidth()), (int) (trackBounds.getHeight()));
			} else {
				g2.fillRect((int) trackBounds.getWidth() - 2, 0, 2, (int) (trackBounds.getHeight()));
			}
		} else {
			if (cs.isExpanded()) {
				g2.fillRect(0, 0, (int) (trackBounds.getWidth()), (int) (trackBounds.getHeight()));
			} else {
				g2.fillRect(0, (int) trackBounds.getHeight() - 2, (int) trackBounds.getWidth(), 2);

			}
		}
		g2.translate(-trackBounds.x, -trackBounds.y);
	}

	@Override
	protected JButton createDecreaseButton(int orientation) {
		return createZeroButton();
	}

	@Override
	protected JButton createIncreaseButton(int orientation) {
		return createZeroButton();
	}

	private JButton createZeroButton() {
		JButton jbutton = new JButton();
		jbutton.setPreferredSize(new Dimension(0, 0));
		jbutton.setMinimumSize(new Dimension(0, 0));
		jbutton.setMaximumSize(new Dimension(0, 0));
		return jbutton;
	}

}
