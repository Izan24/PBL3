package eus.healthit.bchef.core.app.ui.components;

import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.Duration;

import javax.swing.JLabel;

import eus.healthit.bchef.core.models.KitchenAlarm;

public class CustomTimer extends JLabel {

	public static final String COLON = ":";

	private Color red = Color.red;
	private Color gray = Color.DARK_GRAY;
	private boolean tick = true;

	KitchenAlarm alarm;

	public CustomTimer(KitchenAlarm alarm, Font font) {
		super(durationToString(alarm.getResTime()));
		this.alarm = alarm;
		this.setFont(font);
		this.setForeground(gray);
	}
//
//	@Override
//	public void propertyChange(PropertyChangeEvent evt) {
//		Duration newDuration = ((KitchenAlarm) evt.getNewValue()).getResTime();
//		String timeToShow = durationToString(newDuration);
//		System.out.println("Pi: "+timeToShow);
//		if (newDuration.getSeconds() <= 0) {
//			this.setText(durationToString(Duration.ZERO));
//			if (tick) {
//				this.setForeground(red);
//				tick = false;
//			} else {
//				this.setForeground(gray);
//				tick = true;
//			}
//		} else {
//			this.setText(timeToShow);
//		}
//
//	}

	public static String durationToString(Duration duration) {
		long seconds = duration.getSeconds();
		long absSeconds = Math.abs(seconds);
		String positive = String.format("%d:%02d:%02d", absSeconds / 3600, (absSeconds % 3600) / 60, absSeconds % 60);
		return seconds < 0 ? "-" + positive : positive;
	}

	public KitchenAlarm getAlarm() {
		return alarm;
	}

	public void updateTime(Duration duration) {
		this.setText(durationToString(duration));
	}

}
