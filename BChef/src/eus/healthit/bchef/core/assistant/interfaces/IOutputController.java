package eus.healthit.bchef.core.assistant.interfaces;

public interface IOutputController {

	public abstract void send(String text);
	
	public abstract void soundAlarm();
	
}
