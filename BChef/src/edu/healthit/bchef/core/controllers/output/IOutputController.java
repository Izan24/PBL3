package edu.healthit.bchef.core.controllers.output;

public interface IOutputController {

	public abstract void send(String text);
	
	public abstract void soundAlarm();
	
}
