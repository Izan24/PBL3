package eus.healthit.bchef.core.models;

import java.util.ArrayList;
import java.util.List;

public class Kitchen {

	List<Stove> stoves;
	List<Oven> ovens;
	
	public Kitchen() {
		stoves = new ArrayList<>();
		ovens = new ArrayList<>();		
	}
	
	public boolean setFire(int id, int value) {
		if(value < 0) return false;
		stoves.get(id).setState((value) > 0 ? true : false);
		stoves.get(id).setPower(value);
		return true;
	}
	
	public boolean setOven(int id, int value) {
		if(value < 0) return false;
		ovens.get(id).setState((value) > 0 ? true : false);
		ovens.get(id).setTemp(value);
		return true;
	}
	
}
