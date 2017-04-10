package tp.pr5.gui;

import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.*;

/**
 * The controller employed when the application is configured as a swing
 * application. It is responsible for requesting the robot theEngine start and
 * it redirects the actions performed by the user on the window to the robot
 * theEngine.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class GUIController extends tp.pr5.Controller {

	/**
	 * Constructor that uses the model
	 * 
	 * @param robot
	 *            - The reference to the model
	 */

	public GUIController(RobotEngine robot) {
		super(robot);
	}

	/**
	 * Starts the GUIController
	 */

	public void startController() {
		theEngine.getNavigation().requestInitNavigationModule();
		theEngine.requestStart();
	}

	/**
	 * Creates the move instruction and communicates it to the robot
	 */

	public void moveInstruction() {
		Instruction moveInstruction = new MoveInstruction();
		theEngine.communicateRobot(moveInstruction);
	}

	/**
	 * Creates the turn instruction and communicates it to the robot
	 * 
	 * @param rotation
	 *            - the rotation
	 */

	public void turnInstruction(Rotation rotation) {
		Instruction turnInstruction = new TurnInstruction(rotation);
		theEngine.communicateRobot(turnInstruction);
	}

	/**
	 * Creates the drop instruction and communicates it to the robot
	 * 
	 * @param id
	 *            - The id of the item to drop
	 */

	public void dropInstruction(String id) {
		Instruction dropInstruction = new DropInstruction(id);
		theEngine.communicateRobot(dropInstruction);
	}

	/**
	 * Creates the operate instruction and communicates it to the robot
	 * 
	 * @param id
	 *            - The id of the item to operate
	 */

	public void operateInstruction(String id) {
		Instruction operateInstruction = new OperateInstruction(id);
		theEngine.communicateRobot(operateInstruction);
	}

	/**
	 * Creates the pick instruction and communicates it to the robot
	 * 
	 * @param id
	 *            - The id of the item to pick
	 */

	public void pickInstruction(String id) {
		Instruction pickInstruction = new PickInstruction(id);
		theEngine.communicateRobot(pickInstruction);
	}

	/**
	 * Creates the quit instruction and communicates it to the robot
	 */

	public void quitInstruction() {
		Instruction quitInstruction = new QuitInstruction();
		theEngine.communicateRobot(quitInstruction);
	}

}
