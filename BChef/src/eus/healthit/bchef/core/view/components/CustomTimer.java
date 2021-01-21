package eus.healthit.bchef.core.view.components;

import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;

import javax.swing.JLabel;

public class CustomTimer extends JLabel implements PropertyChangeListener {

	public static final String COLON = ":";

	private Color red = Color.red;
	private Color gray = Color.DARK_GRAY;
	private boolean tick = true;

	public CustomTimer(Duration duration, Font font) {
		super(durationToString(duration));
		this.setFont(font);
		this.setForeground(gray);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		Duration newDuration = (Duration) evt.getNewValue();
		String timeToShow = durationToString(newDuration);
		System.out.println("Pi: "+timeToShow);
		if (newDuration.getSeconds() <= 0) {
			this.setText(durationToString(Duration.ZERO));
			if (tick) {
				this.setForeground(red);
				tick = false;
			} else {
				this.setForeground(gray);
				tick = true;
			}
		} else {
			this.setText(timeToShow);
		}

	}

	public static String durationToString(Duration duration) {
		long seconds = duration.getSeconds();
		long absSeconds = Math.abs(seconds);
		String positive = String.format("%d:%02d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60, absSeconds % 60);
		return seconds < 0 ? "-" + positive : positive;
	}

}
