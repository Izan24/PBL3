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
		for (int i = 0; i < NUM_STOVES; i++)
			stoves.add(new Stove(String.format("f%03d", i)));
		ovens = new ArrayList<>();
		for (int i = 0; i < NUM_OVENS; i++)
			ovens.add(new Oven(String.format("h%03d", i)));
	}

	public boolean setStove(int index, int power) {
		if (power < 0 || index > stoves.size() || index < 0)
			return false;
		stoves.get(index).setState((power) > 0 ? true : false);
		stoves.get(index).setPower(power);
		return true;
	}

	public boolean setOven(int index, int temp) {
		if (temp < 0 || index > ovens.size() || index < 0)
			return false;
		ovens.get(index).setState((temp) > 0 ? true : false);
		ovens.get(index).setTemp(temp);
		return true;
	}

	public void setStoves(List<Stove> stoves) {
		for (Stove stove : stoves)
			for (Stove ogStove : this.stoves)
				if (ogStove.getId().equals(stove.getId())) {
					System.out.println("entre xdd");
					ogStove.setPower(stove.getPower());
					ogStove.setState(stove.getState());
				}
		for(Stove stove :  this.stoves) System.out.println(stove.getId() + stove.getState() + stove.getPower());
	}

	public void setOvens(List<Oven> ovens) {
		for (Oven oven : ovens)
			for (Oven ogOven : this.ovens)
				if (ogOven.getId().equals(oven.getId())) {
					ogOven.setTemp(oven.getTemp());
					ogOven.setState(oven.getState());
				}
		for(Oven oven :  this.ovens) System.out.println(oven.getId() + oven.getState() + oven.getTemp());
	}

	public int getStoveCount() {
		return stoves.size();
	}

	public int getOvenCount() {
		return ovens.size();
	}

	public List<Stove> getStoves() {
		return stoves;
	}

	public List<Oven> getOvens() {
		return ovens;
	}

}
