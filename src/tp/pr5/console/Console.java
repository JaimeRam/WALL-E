package tp.pr5.console;

import java.util.List;

import tp.pr5.Direction;
import tp.pr5.Interpreter;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

/**
 * The view that displays the application on the System.out. It implements all
 * the observer interfaces in order to be notified about every event that occur
 * when the robot is running.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class Console extends java.lang.Object implements NavigationObserver,
		RobotEngineObserver, InventoryObserver {

	/**
	 * Notifies that the container has changed
	 */

	public void inventoryChange(List<Item> inventory) {
		// System.out.println("WALL·E says: " + inventory.toString());
	}

	/**
	 * Notifies that the user requests a SCAN instruction over the inventory.
	 */

	public void inventoryScanned(String inventoryDescription) {
		System.out.println("WALL·E says: I am carrying the following items"
				+ Interpreter.LINE_SEPARATOR + inventoryDescription
				+ Interpreter.LINE_SEPARATOR);
	}

	/**
	 * Notifies that the user wants to scan an item allocated in the inventory
	 */

	public void itemScanned(String description) {
		System.out.println("WALL·E says: " + description);
	}

	/**
	 * Notifies that an item is empty and it will be removed from the container.
	 * An invocation to the inventoryChange method will follow.
	 */

	public void itemEmpty(String itemName) {
		System.out.println("You do not have any " + itemName
				+ " in my inventory" + Interpreter.LINE_SEPARATOR);
	}

	/**
	 * The robot engine informs that it has raised an error
	 */

	public void raiseError(String msg) {
		System.out.println(msg);
	}

	/**
	 * The robot engine informs that the help has been requested
	 */

	public void communicationHelp(String help) {
		System.out.println(help);
	}

	/**
	 * The robot engine informs that the robot has shut down (because it has
	 * arrived at the spaceship or it has run out of fuel)
	 */

	public void engineOff(boolean atShip) {
		if (atShip)
			System.out.println("WALL·E says: I am at my spaceship. Bye bye");
		else
			System.out
					.println("WALL·E says: I run out of fuel. I cannot move. Shutting down...");

		System.exit(0);
	}

	/**
	 * The robot engine informs that the communication is over.
	 */

	public void communicationCompleted() {
		System.out
				.println("WALL·E says: I have communication problems. Bye bye");
		System.exit(0);
	}

	/**
	 * The robot engine informs that the fuel and/or the amount of recycled
	 * material has changed
	 */

	public void robotUpdate(int fuel, int recycledMaterial) {
		System.out.println("      * My power is " + fuel
				+ Interpreter.LINE_SEPARATOR
				+ "      * My recycled material is " + recycledMaterial);
	}

	/**
	 * The robot engine informs that the robot wants to say something
	 */

	public void robotSays(String message) {
		System.out.print(message);
	}

	/**
	 * Notifies that the robot heading has changed
	 */

	public void headingChanged(Direction newHeading) {
		System.out.println("WALL·E is looking at direction " + newHeading);
	}

	/**
	 * Notifies that the navigation module has been initialized
	 */

	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		System.out.println(initialPlace.toString() + Interpreter.LINE_SEPARATOR
				+ Interpreter.LINE_SEPARATOR
				+ "WALL·E is looking at direction " + heading);
	}

	/**
	 * Notifies that the robot has arrived at a place
	 */

	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		System.out.println("WALL·E says: Moving in direction " + heading
				+ Interpreter.LINE_SEPARATOR + place.toString()
				+ Interpreter.LINE_SEPARATOR);
	}

	/**
	 * Notifies that the user requested a RADAR instruction
	 */

	public void placeScanned(PlaceInfo placeDescription) {
		System.out.println(placeDescription.getDescription()
				+ Interpreter.LINE_SEPARATOR);
	}

	/**
	 * Notifies that the place where the robot stays has changed (because the
	 * robot picked or dropped an item)
	 */

	public void placeHasChanged(PlaceInfo placeDescription) {
		/*
		 * System.out.println(placeDescription.toString() +
		 * Interpreter.LINE_SEPARATOR);
		 */
	}

}
