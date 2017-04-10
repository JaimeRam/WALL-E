package tp.pr5;

import java.util.Iterator;

import tp.pr5.instructions.*;
import tp.pr5.instructions.exceptions.*;
import tp.pr5.items.*;

/**
 * This class represents the robot engine. It controls robot movements by
 * processing the instructions provided by the controllers. The engine stops
 * when the robot arrives at the space ship, runs out of fuel or receives a quit
 * instruction.
 * 
 * The robot engine is also responsible for updating the fuel level and the
 * recycled material according to the actions that the robot performs in the
 * city.
 * 
 * The robot engine contains an inventory, where the robot stores the items that
 * it collects from the city
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class RobotEngine extends tp.pr5.Observable<RobotEngineObserver> {

	@SuppressWarnings("unused")
	private City cityMap;
	private int power;
	private int recycledMaterial;
	private NavigationModule navigation;
	private ItemContainer objectsContainer;
	private boolean stopEngine;

	/**
	 * Creates the robot engine in an initial place, facing an initial direction
	 * and with a city map. Initially the robot has not any items or recycled
	 * material but it has an initial amount of fuel (100).
	 * 
	 * @param cityMap
	 *            - The city where the robot wanders
	 * @param initialPlace
	 *            - The place where the robot starts
	 * @param direction
	 *            - The initial direction where the robot is facing.
	 */

	public RobotEngine(City cityMap, Place initialPlace, Direction direction) {
		this.cityMap = cityMap;
		power = 100;
		recycledMaterial = 0;
		navigation = new NavigationModule(cityMap, initialPlace);
		objectsContainer = new ItemContainer();
		stopEngine = false;
	}

	/**
	 * It executes an instruction. The instruction must be configured with the
	 * context before executing it. It controls the end of the simulation. If
	 * the execution of the instruction throws an exception, then the
	 * corresponding message is printed
	 * 
	 * @param c
	 *            - The instruction to be executed
	 */

	public void communicateRobot(Instruction c) {
		c.configureContext(this, navigation, objectsContainer);
		try {
			c.execute();
		} catch (InstructionExecutionException e) {
			requestError(e.getMessage());
		}
	}

	/**
	 * Checks if the simulation is finished
	 * 
	 * @return true if the game has finished
	 */

	public boolean isOver() {
		return power == 0 || navigation.atSpaceship() || stopEngine;
	}

	/**
	 * Increases the amount of recycled material of the robot
	 * 
	 * @param weight
	 *            - Amount of recycled material
	 */

	public void addRecycledMaterial(int weight) {
		recycledMaterial = recycledMaterial + weight;
	}

	/**
	 * Adds an amount of fuel to the robot (it can be negative)
	 * 
	 * @param fuel
	 *            - Amount of fuel added to the robot
	 */

	public void addFuel(int fuel) {
		power = power + fuel;
		if (power < 0)
			power = 0; // Para evitar números negativos
	}

	/**
	 * Returns the current fuel level of the robot. This method is mandatory FOR
	 * TESTING PURPOSES
	 * 
	 * @return The current fuel level of the robot
	 */

	public int getFuel() {
		return power;
	}

	/**
	 * Returns the current weight of recycled material that the robot carries.
	 * This method is mandatory FOR TESTING PURPOSES
	 * 
	 * @return The current recycled material of the robot
	 */

	public int getRecycledMaterial() {
		return recycledMaterial;
	}

	/**
	 * Requests the engine to inform the observers that the simulation starts
	 */

	public void requestStart() {
		navigation.requestInitNavigationModule();
		Iterator<RobotEngineObserver> i = iterator();
		while (i.hasNext())
			i.next().robotUpdate(power, recycledMaterial);
	}

	/**
	 * Requests the end of the simulation
	 */

	public void requestQuit() {
		Iterator<RobotEngineObserver> i = iterator();

		if (navigation.atSpaceship()) // Ha llegado al destino final
			while (i.hasNext())
				i.next().engineOff(true);

		else if (power == 0) // Se ha quedado sin fuel
			while (i.hasNext())
				i.next().engineOff(false);

		else
			// Han seleccionado la opción QUIT
			while (i.hasNext())
				i.next().communicationCompleted();
	}

	/**
	 * Requests the engine to inform about all possible instructions
	 */

	public void requestHelp() {
		Iterator<RobotEngineObserver> i = iterator();
		while (i.hasNext())
			i.next().communicationHelp(Interpreter.interpreterHelp());
	}

	/**
	 * Requests the engine to inform that an error has been raised
	 * 
	 * @param msg
	 *            - the String message error
	 */

	public void requestError(java.lang.String msg) {
		Iterator<RobotEngineObserver> i = iterator();
		while (i.hasNext())
			i.next().raiseError(msg);
	}

	/**
	 * Request the engine to say something
	 * 
	 * @param message
	 *            - The message to say
	 */

	public void saySomething(java.lang.String message) {
		Iterator<RobotEngineObserver> i = iterator();
		while (i.hasNext())
			i.next().robotSays(message);
	}

	/**
	 * Request for changes in the attributes of the robot (fuel and recycled
	 * material)
	 */

	public void requestRobotUpdate() {
		Iterator<RobotEngineObserver> i = iterator();
		while (i.hasNext())
			i.next().robotUpdate(power, recycledMaterial);
	}

	/**
	 * Register a NavigationObserver to the model
	 * 
	 * @param robotObserver
	 *            - The observer that wants to be registered
	 */

	public void addNavigationObserver(NavigationObserver robotObserver) {
		navigation.addObserver(robotObserver);
	}

	/**
	 * Registers an EngineObserver to the model
	 * 
	 * @param observer
	 *            - The observer that wants to be registered
	 */

	public void addEngineObserver(RobotEngineObserver observer) {
		addObserver(observer);
	}

	/**
	 * Registers an ItemContainerObserver to the model
	 * 
	 * @param observer
	 *            - The observer that wants to be registered
	 */

	public void addItemContainerObserver(InventoryObserver observer) {
		objectsContainer.addObserver(observer);
	}

	/**
	 * Prints the state of the robot
	 */

	public void printRobotState() {
		System.out.println("      * My power is " + power
				+ Interpreter.LINE_SEPARATOR
				+ "      * My reclycled material is " + recycledMaterial);
	}

	/**
	 * Gets the NavigationModule
	 * 
	 * @return the NavigationModule
	 */

	public NavigationModule getNavigation() {
		return navigation;
	}

}
