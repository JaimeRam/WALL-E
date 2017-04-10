package tp.pr5;

import tp.pr5.items.InventoryObserver;

/**
 * Clase abstracta que maneja el controlador del RobotEngine del juego
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public abstract class Controller {

	protected RobotEngine theEngine;

	/**
	 * Default constructor
	 * 
	 * @param game
	 *            - the RobotEngine game
	 */

	public Controller(RobotEngine game) {
		theEngine = game;
	}

	/**
	 * Abstract method that runs the game. This method is different whether the
	 * application runs the game on console or on a Swing Window
	 */

	public abstract void startController();

	/**
	 * Adds RobotEngine Observer to the robot
	 * 
	 * @param engineObserver
	 *            - observer for robot
	 */

	public void addRobotEngineObserver(RobotEngineObserver engineObserver) {
		theEngine.addEngineObserver(engineObserver);
	}

	/**
	 * Adds Navigation Observer to the robot
	 * 
	 * @param navigationObserver
	 *            - observer for robot
	 * 
	 */

	public void addNavigationObserver(NavigationObserver navigationObserver) {
		theEngine.addNavigationObserver(navigationObserver);
	}

	/**
	 * Adds Inventory Observer to the robot
	 * 
	 * @param containerObserver
	 *            - observer for robot
	 */

	public void addInventoryObserver(InventoryObserver containerObserver) {
		theEngine.addItemContainerObserver(containerObserver);
	}

}
