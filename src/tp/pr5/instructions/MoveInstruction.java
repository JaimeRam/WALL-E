package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * 
 * Its execution moves the robot from one place to the next one in the current
 * direction. This instruction works if the user writes MOVE or MOVER
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class MoveInstruction extends java.lang.Object implements Instruction {

	private RobotEngine theEngine;
	private NavigationModule navigationModule;
	@SuppressWarnings("unused")
	private ItemContainer objectsContainer;

	/**
	 * Parses the String returning a MoveInstruction instance or throwing a
	 * WrongInstructionFormatException()
	 */

	public Instruction parse(String cad) throws WrongInstructionFormatException {
		if (cad.equalsIgnoreCase("MOVE") || cad.equalsIgnoreCase("MOVER"))
			return new MoveInstruction();
		else
			throw new WrongInstructionFormatException("Instrucción no válida");
	}

	/**
	 * Returns a description of the Instruction syntax. The string does not end
	 * with the line separator. It is up to the caller adding it before
	 * printing.
	 */

	public String getHelp() {
		return "MOVE|MOVER";
	}

	/**
	 * Set the execution context. The method receives the entire engine (engine,
	 * navigation and the robot container) even though the actual implementation
	 * of execute() may not require it.
	 */

	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		navigationModule = navigation;
		objectsContainer = robotContainer;
		theEngine = engine;
	}

	/**
	 * Moves from the current place to the next place on the current Direction.
	 * An opened street must exist between both places to be moved
	 */

	public void execute() throws InstructionExecutionException {
		navigationModule.move();
		theEngine.addFuel(-5);
		theEngine.requestRobotUpdate();
		if (navigationModule.getCurrentPlace().isSpaceship()
				|| theEngine.getFuel() == 0)
			theEngine.requestQuit();

	}
}
