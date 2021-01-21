package eus.healthit.bchef.core.models;

import java.util.ArrayList;
import java.util.List;

public class Kitchen {

	static final int NUM_STOVES = 4;
	static final int NUM_OVENS = 1;
	
	List<Stove> stoves;
	List<Oven> ovens;
	
	public Kitchen() {
		stoves = new ArrayList<>();
		for(int i = 0; i < NUM_STOVES; i++) stoves.add(new Stove());
		ovens = new ArrayList<>();
		for(int i = 0; i < NUM_OVENS; i++) ovens.add(new Oven());
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
