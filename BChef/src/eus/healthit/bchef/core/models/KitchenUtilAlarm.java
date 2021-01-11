package eus.healthit.bchef.core.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

import javax.swing.Timer;

import eus.healthit.bchef.core.enums.KitchenUtil;

public class KitchenUtilAlarm implements ActionListener {

	Time time;
	
	KitchenUtil util;
	int utilId;
	boolean finished = false;
	
	LocalTime startTime;
	LocalTime endTime;
	PropertyChangeSupport connector;
	Timer timer;
	
	public KitchenUtilAlarm(KitchenUtil util, int utilId, Time time, PropertyChangeListener listener) {
		this.util = util;
		this.utilId = utilId;
		this.time = time;
		this.timer = null;
		connector = new PropertyChangeSupport(this);
		connector.addPropertyChangeListener(listener);
		startTime = null;
		endTime = null;
	}
	
	public void start() {
		if(!finished && startTime == null) {
			startTime = LocalTime.now();
			endTime = startTime.plusSeconds((time.getTime() / 1000));
			timer = new Timer(1000, this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(endTime.isAfter(LocalTime.now())) {
			Duration res = Duration.between(LocalTime.now(), endTime);
			connector.firePropertyChange("time", null, res);
		}
		else{
			timer.stop();
			finished = true;
			connector.firePropertyChange("finish", null, this);
		}
	}
	
}
