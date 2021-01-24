package eus.healthit.bchef.core.app.ui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.metal.MetalScrollBarUI;

public class CustomScrollbarUI extends MetalScrollBarUI {
	public static final Integer RADIUS = 10;

	public CustomScrollbarUI() {

	}

	@Override
	protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
		Graphics2D g2 = (Graphics2D) g;
		// CustomScrollBar csb = (CustomScrollBar)c;
		// System.out.println("X: "+ csb.getBounds().getX()+ " Y:" +
		// csb.getBounds().getY());
		g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g2.translate(thumbBounds.getX(), thumbBounds.getY());
		g2.setColor(Color.LIGHT_GRAY);

		g2.fillRoundRect(0, 0, (int) (thumbBounds.getWidth()), (int) (thumbBounds.getHeight()), RADIUS, RADIUS);

		// System.out.println(String.format("x=%f, y=%f, w=%f, h=%f, %s",
		// thumbBounds.getX(), thumbBounds.getY(), thumbBounds.getWidth(),
		// thumbBounds.getHeight(),csb.isHorizontal() ));

	}

	@Override
	protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
		g2.setColor(Color.GRAY);
		g2.translate(trackBounds.getX(), trackBounds.getY());
		g2.fillRoundRect(0, 0, (int) (trackBounds.getWidth()), (int) (trackBounds.getHeight()), RADIUS, RADIUS);
		// g2.translate(-trackBounds.getX(), -trackBounds.getY());
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
		jbutton.setOpaque(false);
		jbutton.setForeground(new Color(0, 0, 0, 0));
		jbutton.setBackground(new Color(0, 0, 0, 0));
		return jbutton;
	}

}
