package eus.healthit.bchef.core.controllers.implementations;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import eus.healthit.bchef.core.controllers.interfaces.IBoardController;
import eus.healthit.bchef.core.enums.KitchenUtil;
import eus.healthit.bchef.core.models.Kitchen;
import eus.healthit.bchef.core.models.Oven;
import eus.healthit.bchef.core.models.Stove;
import eus.healthit.bchef.core.util.StringParser;

public class BoardController implements SerialPortDataListener, IBoardController {
	private static final String STOVE_CHAR = "f";
	private static final String OVEN_CHAR = "h";

	SerialPort serialPort;
	String save;
	PropertyChangeSupport connector;

	public BoardController() {
		connector = new PropertyChangeSupport(this);

		SerialPort puertosDisponibles[] = SerialPort.getCommPorts();
		for (SerialPort s : puertosDisponibles) {
			if (s.getDescriptivePortName().contains("Prolific") && !s.isOpen()) {
				serialPort = s;
				break;
			}
		}
		if (serialPort != null) {

			serialPort.setBaudRate(115200);
			serialPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
			serialPort.setParity(SerialPort.NO_PARITY);
			serialPort.setNumDataBits(8);

			serialPort.openPort();
			save = new String("");
			serialPort.addDataListener(this);
		}
	}

	public String serialRead() {
		byte[] buffer = new byte[1];
		serialPort.readBytes(buffer, 1);
		String letra = new String(buffer);

		return letra;
	}

	public void serialWrite(String caracter) {
		byte[] carac = caracter.getBytes();
		serialPort.writeBytes(carac, carac.length);
	}

	@Override
	public int getListeningEvents() {
		return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
	}

	@Override
	public void serialEvent(SerialPortEvent arg0) {
		if (arg0.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
			int i = serialPort.bytesAvailable();

			while (i > 0) {
				String toPrint = serialRead();
				// System.out.println(toPrint);
				if (toPrint != null) {
					save += toPrint;
					if (save.contains("<")) {
						String[] datos = save.split("[<]");
						parseInfo(datos[0]);
						save = "";
					}
				}
				i--;
			}
		}
	}

	public void parseInfo(String string) {
		List<Oven> ovens = new ArrayList<>();
		List<Stove> stoves = new ArrayList<>();
		boolean corrupted = false;
		System.out.println(string);

		for (String instance : string.split("_")) {
			String[] stats = instance.split("(?<=\\G....)");
			try {
				String id = stats[0];
				KitchenUtil util;
				switch (StringParser.stripSpaces(stats[1])) {
				case "h":
					util = KitchenUtil.OVEN;
					break;
				case "f":
					util = KitchenUtil.STOVE;
					break;
				default:
					util = KitchenUtil.MISUNDERSTOOD;
					break;
				}
				boolean status = (StringParser.stripSpaces(stats[2]).equals("on"));
				int value = Integer.parseInt(stats[3]);
				if (util.equals(KitchenUtil.OVEN))
					ovens.add(new Oven(id, status, value));
				else if (util.equals(KitchenUtil.STOVE))
					stoves.add(new Stove(id, status, value));
			} catch (Exception e) {
				e.printStackTrace();
				corrupted = true;
			}
		}

		if (stoves.size() > 0)
			connector.firePropertyChange("UPDATE_STOVES", null, stoves);
		if (ovens.size() > 0)
			connector.firePropertyChange("UPDATE_OVENS", null, ovens);
		if (corrupted)
			connector.firePropertyChange("UPDATE_BOARD", null, null);
	}

	public void allLetters(String frase) {
		if(serialPort == null) return;
		for (int i = 0; i < frase.length(); i++) {
			String s = Character.toString(frase.charAt(i));
			serialWrite(s);
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateKitchen(Kitchen kitchen) {
		List<Stove> stoves = kitchen.getStoves();
		List<Oven> ovens = kitchen.getOvens();

		List<String> instances = new ArrayList<>();
		for (Stove stove : stoves)
			instances.add(String.join("_", stove.getId(), STOVE_CHAR, (stove.getState() ? "on" : "off"),
					String.format("%04d", stove.getPower())));
		for (Oven oven : ovens)
			instances.add(String.join("_", oven.getId(), OVEN_CHAR, (oven.getState() ? "on" : "off"),
					String.format("%04d", oven.getTemp())));

		String msg = String.join("+", instances) + "<";
		System.out.println(msg);
		allLetters(msg);
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		connector.addPropertyChangeListener(listener);
	}

}
