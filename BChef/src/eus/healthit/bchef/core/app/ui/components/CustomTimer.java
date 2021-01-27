package eus.healthit.bchef.core.app.ui.components;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.Duration;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.models.KitchenAlarm;

public class CustomTimer extends JPanel {

	private static final long serialVersionUID = 4561279417489111936L;
	public static final String COLON = ":";
	private Color gray = Color.DARK_GRAY;

	KitchenAlarm alarm;

	JLabel timerLabel, timerIcon;

	public CustomTimer(KitchenAlarm alarm, Font font) {
		super(new FlowLayout());
		this.alarm = alarm;
		this.setBackground(Color.white);

		initJLabels(font);

		this.add(timerIcon);
		this.add(timerLabel);
	}

	private void initJLabels(Font font) {
		timerLabel = new JLabel(durationToString(alarm.getResTime()));
		timerLabel.setFont(font);
		timerLabel.setForeground(gray);

		timerIcon = new JLabel();

		if (alarm.getUtil() == null) {
			timerIcon.setIcon(new ImageIcon("resources/menuIcons/timer.png"));
		} else if (alarm.getUtil().equals(KitchenUtil.OVEN)) {
			timerIcon.setIcon(new ImageIcon("resources/menuIcons/oven.png"));
		} else if (alarm.getUtil().equals(KitchenUtil.STOVE)) {
			timerIcon.setIcon(new ImageIcon("resources/menuIcons/stove.png"));
		}
	}

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
		timerLabel.setText(durationToString(duration));
	}

}
