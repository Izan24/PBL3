package eus.healthit.bchef.core.util.func;

import java.time.Duration;

import eus.healthit.bchef.core.assistant.BChefController;
import eus.healthit.bchef.core.enums.KitchenUtil;

public class NewAlarmCall implements FunctionCall {

	public static final String ID_STRING = "NEW_ALARM";
	
	KitchenUtil util;
	Integer index;
	Duration time;
	
	public NewAlarmCall(KitchenUtil util, Integer index, Duration time) {
		this.util = util;
		this.index = index;
		this.time = time;
	}
	
	@Override
	public void executeCall() {
		BChefController.getInstance().setAlarm(util, index, time);;
	}

	@Override
	public String getId() {
		return ID_STRING;
	}
	
}
