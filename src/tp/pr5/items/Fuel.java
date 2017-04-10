package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * An item that represents fuel. This item can be used at least once and it
 * provides power energy to the robot. When the item is used the configured
 * number of times, then it must be removed from the robot inventory
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class Fuel extends Item {

	private int power; // Capacidad de combustible
	private int times; // Número de veces que se puede utilizar

	/**
	 * Fuel constructor
	 * 
	 * @param id
	 *            - Item id
	 * @param description
	 *            - Item description
	 * @param power
	 *            - The amount of power energy that this item provides the robot
	 * @param times
	 *            - Number of times the robot can use the item
	 */

	public Fuel(java.lang.String id, java.lang.String description, int power,
			int times) {
		super(id, description);
		this.power = power;
		this.times = times;
	}

	/**
	 * Fuel can be used as many times as it was configured
	 */

	public boolean canBeUsed() {
		return times > 0;
	}

	/**
	 * Using the fuel provides energy to the robot (if it can be used)
	 */

	public boolean use(RobotEngine r, NavigationModule nav) {

		boolean result = false;

		if (canBeUsed()) {
			r.addFuel(power);
			times--;
			result = true;
		}

		return result;
	}

	/**
	 * Generates a String with the Item description
	 */

	public java.lang.String toString() {
		return description + "//" + " power = " + power + "," + " times =  "
				+ times;
	}
}
