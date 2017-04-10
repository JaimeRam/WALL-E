package tp.pr5.gui;

import javax.swing.JLabel;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

public class InfoPanel extends javax.swing.JPanel implements
		RobotEngineObserver, NavigationObserver, InventoryObserver {

	private static final long serialVersionUID = 1L;
	private JLabel labelInfoPanel; // Etiqueta para mostrar las notificaciones

	public InfoPanel() {
		labelInfoPanel = new JLabel();
		labelInfoPanel.setText("Robot attributes has been updated: (100, 0)");
		add(labelInfoPanel);
	}

	/**
	 * Notifies that the robot heading has changed
	 * 
	 * @param newHeading
	 *            - New robot heading
	 */

	public void headingChanged(Direction newHeading) {

	}

	/**
	 * Notifies that the navigation module has been initialized
	 * 
	 * @param initialPlace
	 *            - The place where the robot starts the simulation
	 * 
	 * @param heading
	 *            - The initial robot heading
	 */

	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {

	}

	/**
	 * The robot engine informs that it has raised an error
	 * 
	 * @param msg
	 *            - Error message
	 */

	public void raiseError(java.lang.String msg) {

	}

	/**
	 * The robot engine informs that the help has been requested
	 * 
	 * @param help
	 *            - A string with information help
	 */

	public void communicationHelp(java.lang.String help) {

	}

	/**
	 * The robot engine informs that the robot has shut down (because it has
	 * arrived at the spaceship or it has run out of fuel)
	 * 
	 * @param atShip
	 *            - true if the robot shuts down because it has arrived at the
	 *            spaceship or false if it has run out of fuel
	 */

	public void engineOff(boolean atShip) {

	}

	/**
	 * The robot engine informs that the communication is over.
	 */

	public void communicationCompleted() {

	}

	/**
	 * Notifies that the robot has arrived at a place
	 * 
	 * @param heading
	 *            - The robot movement direction
	 * 
	 * @param place
	 *            - The place where the robot arrives
	 */

	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {

	}

	/**
	 * The robot engine informs that the fuel and/or the amount of recycled
	 * material has changed
	 * 
	 * @param fuel
	 *            - Current amount of fuel
	 * 
	 * @param recycledMaterial
	 *            - Current amount of recycled material
	 */

	public void robotUpdate(int fuel, int recycledMaterial) {
		labelInfoPanel.setText("Robot attributes has been updated: (" + fuel
				+ ", " + recycledMaterial + ")");
	}

	/**
	 * Notifies that the user requested a RADAR instruction
	 * 
	 * @param placeDescription
	 *            - Information with the current place
	 */

	public void placeScanned(PlaceInfo placeDescription) {

	}

	/**
	 * Notifies that the container has changed
	 * 
	 * @param inventory
	 *            - New inventory
	 */

	public void inventoryChange(java.util.List<Item> inventory) {

	}

	/**
	 * Notifies that the user requests a SCAN instruction over the inventory.
	 * 
	 * @param collectionDescription
	 *            - Information about the inventory
	 */

	public void inventoryScanned(java.lang.String collectionDescription) {

	}

	/**
	 * Notifies that the user wants to scan an item allocated in the inventory
	 * 
	 * @param description
	 *            - Item description
	 */

	public void itemScanned(java.lang.String description) {

	}

	/**
	 * Notifies that an item is empty and it will be removed from the container.
	 * An invocation to the inventoryChange method will follow.
	 * 
	 * @param itemName
	 *            - Name of the empty item
	 */

	public void itemEmpty(java.lang.String itemName) {

	}

	/**
	 * The robot engine informs that the robot wants to say something
	 * 
	 * @param message
	 *            - The robot message
	 */

	public void robotSays(java.lang.String message) {
		labelInfoPanel.setText(message);
	}

	/**
	 * Notifies that the place where the robot stays has changed (because the
	 * robot picked or dropped an item)
	 * 
	 * @param placeDescription
	 *            - the description of the place
	 */

	public void placeHasChanged(PlaceInfo placeDescription) {

	}

}
