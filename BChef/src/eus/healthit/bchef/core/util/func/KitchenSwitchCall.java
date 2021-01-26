package eus.healthit.bchef.core.util.func;

import eus.healthit.bchef.core.assistant.BChefController;
import eus.healthit.bchef.core.enums.KitchenUtil;

public class KitchenSwitchCall implements FunctionCall {

	public static final String ID_STRING = "KITCHEN_SWITCH";
	
	KitchenUtil util;
	int index;
	int value;
	
	public KitchenSwitchCall(KitchenUtil util, Integer index, int value) {
		this.util = util;
		this.index = index;
		this.value = value;
	}
	
	
	@Override
	public void executeCall() {
		BChefController.getInstance().switchKitchen(util, index, value);
	}


	@Override
	public String getId() {
		return ID_STRING;
	}

}
