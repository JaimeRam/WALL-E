package tp.pr5;

import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.items.Item;

/**
 * This class is in charge of the robot navigation features. It contains the
 * city where the robot looks for garbage, the current place where the robot is,
 * and the current direction of the robot. It contains methods to handle the
 * different robot movements and to pick and drop items at the current place.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class NavigationModule extends tp.pr5.Observable<NavigationObserver> {

	private City cityMap; // Mapa de plazas
	private Place currentPlace; // Plaza actual
	private Direction currentDirection; // Dirección actual

	/**
	 * Navigation module constructor. It needs the city map and the initial
	 * place
	 * 
	 * @param aCity
	 *            - A city map
	 * @param initialPlace
	 *            - An initial place for the robot
	 */

	public NavigationModule(City aCity, Place initialPlace) {
		cityMap = aCity;
		currentPlace = initialPlace;
		currentDirection = Direction.NORTH;
	}

	/**
	 * Checks if the robot has arrived at a spaceship
	 * 
	 * @return true if an only if the current place is the spaceship
	 */

	public boolean atSpaceship() {
		return currentPlace.isSpaceship();
	}

	/**
	 * Updates the current direction of the robot according to the rotation
	 * 
	 * @param rotation
	 *            - left or right
	 */

	public void rotate(Rotation rotation) {
		if (rotation == Rotation.RIGHT) {
			if (currentDirection == Direction.NORTH) {
				currentDirection = Direction.EAST;
			} else if (currentDirection == Direction.EAST) {
				currentDirection = Direction.SOUTH;
			} else if (currentDirection == Direction.SOUTH) {
				currentDirection = Direction.WEST;
			} else
				currentDirection = Direction.NORTH;

		} else if (rotation == Rotation.LEFT) {
			if (currentDirection == Direction.NORTH) {
				currentDirection = Direction.WEST;
			} else if (currentDirection == Direction.EAST) {
				currentDirection = Direction.NORTH;
			} else if (currentDirection == Direction.SOUTH) {
				currentDirection = Direction.EAST;
			} else
				currentDirection = Direction.SOUTH;

		} else
			currentDirection = Direction.UNKNOWN;
	}

	/**
	 * The method tries to move the robot following the current direction. If
	 * the movement is not possible because there is no street, or there is a
	 * street which is closed, then it throws an exception. Otherwise the
	 * current place is updated according to the movement
	 * 
	 * @throws InstructionExecutionException
	 *             - An exception with a message about the encountered problem
	 */

	public void move() throws InstructionExecutionException {

		Street st = cityMap.lookForStreet(currentPlace, currentDirection);

		if (st != null && st.isOpen()) { // La calle existe y está abierta
			currentPlace = st.nextPlace(currentPlace);
			requestRobotArrivesAtPlace();
		} else if (st != null && !st.isOpen()) // La calle existe y está cerrada
			throw new InstructionExecutionException("The street is closed");
		else
			// La calle no existe
			throw new InstructionExecutionException(
					"No es posible tomar esa dirección");
	}

	/**
	 * Tries to pick an item characterized by a given identifier from the
	 * current place. If the action was completed the item is removed from the
	 * current place.
	 * 
	 * @param id
	 *            - The identifier of the item
	 * @return The item of identifier id if it exists in the place. Otherwise
	 *         the method returns null
	 */

	public Item pickItemFromCurrentPlace(java.lang.String id) {
		return currentPlace.pickItem(id);
	}

	/**
	 * Drop an item in the current place. It assumes that there is no other item
	 * with the same name/id there. Otherwise, the behaviour is undefined.
	 * 
	 * @param item
	 *            - The name of the item to be dropped.
	 */

	public void dropItemAtCurrentPlace(Item it) {
		if (!currentPlace.existItem(it.getId()))
			currentPlace.dropItem(it);
	}

	/**
	 * Checks if there is an item with a given id in this place
	 * 
	 * @param id
	 *            - Identifier of the item we are looking for
	 * @return true if and only if an item with this id is in the current place
	 */

	public boolean findItemAtCurrentPlace(java.lang.String id) {
		return currentPlace.existItem(id);
	}

	/**
	 * Initializes the current heading according to the parameter
	 * 
	 * @param heading
	 *            - New direction for the robot
	 */

	public void initHeading(Direction heading) {
		currentDirection = heading;
	}

	/**
	 * Returns the street opposite the robot
	 * 
	 * @return The street which the robot is facing, or null, if there is not
	 *         any street in this direction
	 */

	public Street getHeadingStreet() {
		return cityMap.lookForStreet(currentPlace, currentDirection);
	}

	/**
	 * Returns the robot heading
	 * 
	 * @return The direction where the robot is facing to.
	 */

	public Direction getCurrentHeading() {
		return currentDirection;
	}

	/**
	 * Returns the current place where the robot is (for testing purposes)
	 * 
	 * @return The current place
	 */

	public Place getCurrentPlace() {
		return currentPlace;
	}

	/**
	 * Provides the observers with the information (description + inventory) of
	 * the current place
	 */

	public void scanCurrentPlace() {
		java.util.Iterator<NavigationObserver> i = iterator();
		while (i.hasNext())
			i.next().placeScanned(currentPlace);
	}

	/**
	 * Notifies that the navigation module has been initialized
	 */

	public void requestInitNavigationModule() {
		java.util.Iterator<NavigationObserver> i = iterator();
		while (i.hasNext())
			i.next().initNavigationModule(currentPlace, currentDirection);
	}

	/**
	 * Notifies that the robot heading has changed
	 */

	public void requestHeadingChanged() {
		java.util.Iterator<NavigationObserver> i = iterator();
		while (i.hasNext())
			i.next().headingChanged(currentDirection);
	}

	/**
	 * Notifies that the place where the robot stays has changed (because the
	 * robot picked or dropped an item)
	 */

	public void requestPlaceHasChanged() {
		java.util.Iterator<NavigationObserver> i = iterator();
		while (i.hasNext())
			i.next().placeHasChanged(currentPlace);
	}

	/**
	 * Notifies that the robot has arrived at a place
	 */

	public void requestRobotArrivesAtPlace() {
		java.util.Iterator<NavigationObserver> i = iterator();
		while (i.hasNext())
			i.next().robotArrivesAtPlace(currentDirection, currentPlace);
	}

}
