package eus.healthit.bchef.core.models;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;

import javax.swing.Timer;

import eus.healthit.bchef.core.controllers.implementations.OutputController;
import eus.healthit.bchef.core.enums.KitchenUtil;

public class KitchenAlarm implements ActionListener {

	Duration time;
	
	KitchenUtil util;
	int utilId;
	boolean finished = false;
	
	LocalTime startTime;
	LocalTime endTime;
	PropertyChangeSupport connector;
	Timer timer;
	
	public KitchenAlarm(KitchenUtil util, int utilId, Duration time, PropertyChangeListener listener) {
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
			endTime = startTime.plus(time);
			timer = new Timer(1000, this);
			timer.start();
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		if(endTime.isAfter(LocalTime.now())) {
			Duration res = Duration.between(LocalTime.now(), endTime);
			connector.firePropertyChange("time", null, res);
		}
		else{
			OutputController.getOutputController().soundAlarm();
			timer.stop();
			finished = true;
			connector.firePropertyChange("finish", null, this);
		}
	}
	
}
