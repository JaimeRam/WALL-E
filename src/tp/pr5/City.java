package tp.pr5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the city where the robot is wandering. It contains
 * information about the streets and the places in the city
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class City extends java.lang.Object {

	private List<Street> city; // Array que contiene las calles del juego

	/**
	 * Default constructor. Needed for testing purposes
	 */

	public City() {
		city = new ArrayList<Street>();
	}

	/**
	 * Creates a city using an array of streets
	 * 
	 * @param cityMap
	 */

	public City(Street[] cityMap) {
		city = new ArrayList<Street>(Arrays.asList(cityMap));
	}

	/**
	 * Looks for the street that starts from the given place in the given
	 * direction.
	 * 
	 * @param currentPlace
	 *            - The place where to look for the street
	 * @param currentHeading
	 *            - The direction to look for the street
	 * @return the street that stars from the given place in the given
	 *         direction. It returns null if there is not any street in this
	 *         direction from the given place
	 */

	public Street lookForStreet(Place currentPlace, Direction currentHeading) {

		Street ret = null;

		for (Street s : city) {
			if (s.comeOutFrom(currentPlace, currentHeading)) {
				ret = s;
				break; // Ha encontrado una calle, interrumpimos la búsqueda
			}
		}

		return ret;
	}

	/**
	 * Adds an street to the city
	 * 
	 * @param street
	 *            - The street to be added
	 */

	public void addStreet(Street street) {
		city.add(street);
	}

}