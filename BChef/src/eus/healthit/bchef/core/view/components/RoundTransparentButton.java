package eus.healthit.bchef.core.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicButtonUI;

public class RoundTransparentButton extends BasicButtonUI implements MouseListener, KeyListener{
	  

	    private Border m_borderRaised = new TransparentRoundBackgroundBorder(200);

	    private Color m_backgroundNormal = new Color(0, 0, 0, 0);
	    
	    private Color font_Color_normal = new Color(255, 255, 255, 255);
	    
	    private Color font_Color_active = new Color(29,161,242, 255);

	    private Color font_Color;

	    private Color m_backgroundPressed = new Color(0, 0, 0, 0);

	    private Color m_foregroundNormal = new Color(0, 0, 0, 0);

	    private Color m_foregroundActive = new Color(84, 201, 227, 50);
	    
	    //private Color m_focusBorder = new Color(0, 0, 0, 0);

	    public RoundTransparentButton(JComponent c) {
	    	c.repaint();
	    	c.setBorder(m_borderRaised);
	    	c.setForeground(m_foregroundNormal);
	    	this.font_Color = font_Color_normal;
	    	c.addMouseListener(this);
	        c.addKeyListener(this);
		}	

	    public void paint(Graphics g, JComponent c) {
	        AbstractButton b = (AbstractButton) c;
	        Dimension d = b.getSize();
	        
	        Graphics2D g2 = (Graphics2D) g;
	    	g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));
	    	
	        g2.setFont(c.getFont());
	        FontMetrics fm = g.getFontMetrics();

	        g2.setColor(font_Color);
	        String caption = b.getText();
	        
	        int dimx = (d.width - fm.stringWidth(caption)) / 2;
	        int dimy = (d.height + fm.getHeight()/2) / 2;

	        
	        g2.drawString(caption, dimx, dimy);

	    }

	    public Dimension getPreferredSize(JComponent c) {
	        Dimension d = super.getPreferredSize(c);
	        if (m_borderRaised != null) {
	            Insets ins = m_borderRaised.getBorderInsets(c);
	            d.setSize(d.width + ins.left + ins.right, d.height + ins.top
	                    + ins.bottom);
	        }
	        return d;
	    }

	    public void mouseClicked(MouseEvent e) {
	    }

	    public void mousePressed(MouseEvent e) {
	        JComponent c = (JComponent) e.getComponent();
	        //c.setBorder(new EmptyBorder(0, 0, 0,0));
	        //c.setBackground(m_backgroundPressed);
	        //c.repaint();
	    }

	    public void mouseReleased(MouseEvent e) {
	        JComponent c = (JComponent) e.getComponent();
	        //c.repaint();
	    }

	    public void mouseEntered(MouseEvent e) {
	        JComponent c = (JComponent) e.getComponent();
	        this.font_Color = font_Color_active;
	        c.setForeground(m_foregroundActive);
	        c.setBorder(m_borderRaised);
	        //c.repaint();
	    }

	    public void mouseExited(MouseEvent e) {
	        JComponent c = (JComponent) e.getComponent();
	        this.font_Color = font_Color_normal;
	        c.setForeground(m_foregroundNormal);
	        c.setBorder(new EmptyBorder(10, 10, 10, 10));
	        //c.repaint();
	    }

	    public void keyTyped(KeyEvent e) {
	    }

	    public void keyPressed(KeyEvent e) {
	        int code = e.getKeyCode();
	        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
	            JComponent c = (JComponent) e.getComponent();
	            c.setBorder(new EmptyBorder(0,0,0,0));
	            c.setBackground(m_backgroundPressed);
	        }
	    }

	    public void keyReleased(KeyEvent e) {
	        int code = e.getKeyCode();
	        if (code == KeyEvent.VK_ENTER || code == KeyEvent.VK_SPACE) {
	            JComponent c = (JComponent) e.getComponent();
	            c.setBorder(m_borderRaised);
	            c.setBackground(m_backgroundNormal);
	        }
	    }
	
}
