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
	
	public boolean setFire(int index, int power) {
		if(power < 0 || index > stoves.size() || index < 0) return false;
		stoves.get(index).setState((power) > 0 ? true : false);
		stoves.get(index).setPower(power);
		return true;
	}
	
	public boolean setOven(int index, int temp) {
		if(temp < 0 || index > ovens.size() || index < 0) return false;
		ovens.get(index).setState((temp) > 0 ? true : false);
		ovens.get(index).setTemp(temp);
		return true;
	}
	
	public int getStoveCount() {
		return stoves.size();
	}
	
	public int getOvenCount() {
		return ovens.size();
	}
	
}
