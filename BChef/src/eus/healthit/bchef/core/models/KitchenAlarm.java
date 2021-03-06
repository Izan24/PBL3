package eus.healthit.bchef.core.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeSupport;
import java.time.Duration;
import java.time.LocalTime;

import javax.swing.Timer;

import eus.healthit.bchef.core.assistant.BChefController;
import eus.healthit.bchef.core.enums.KitchenUtil;

public class KitchenAlarm implements ActionListener {

	Duration time;

	KitchenUtil util;
	int utilIndex;
	boolean rung = false;

	LocalTime startTime;
	LocalTime endTime;
	Duration resTime;
	PropertyChangeSupport connector;
	Timer timer;
	

	public KitchenAlarm(KitchenUtil util, int utilId, Duration time) {
		this.util = util;
		this.utilIndex = utilId;
		this.time = time;
		this.timer = null;
		connector = new PropertyChangeSupport(this);
		connector.addPropertyChangeListener(BChefController.getInstance());
		startTime = null;
		endTime = null;
	}

	public void start() {
		if (!rung && startTime == null) {
			startTime = LocalTime.now();
			endTime = startTime.plus(time);
			timer = new Timer(1000, this);
			timer.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if (endTime.isAfter(LocalTime.now()) && !rung) {
			resTime = Duration.between(LocalTime.now(), endTime);
			connector.firePropertyChange("ALARM_UPDATE", null, this);
		} else if (!rung && timer != null) {
			resTime = Duration.ZERO;
			timer.stop();
			timer = null;
			rung = true;
			connector.firePropertyChange("ALARM_FINISH", null, this);
		}
	}

	public KitchenUtil getUtil() {
		return util;
	}

	public int getUtilIndex() {
		return utilIndex;
	}
	
	public Duration getResTime() {
		return resTime;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null)  return false;
		if(!(obj instanceof KitchenAlarm)) return false;
		KitchenAlarm alarm = (KitchenAlarm) obj;
		if(alarm.hashCode() == this.hashCode()) return true;
		return false;
	}

	@Override
	public int hashCode() {
		return ("" + util + utilIndex + time.toString() + startTime.toString()).hashCode();
	}
	

}
