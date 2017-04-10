package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * Its execution request the robot to finish the simulation This Instruction
 * works if the user writes QUIT or SALIR
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class QuitInstruction extends java.lang.Object implements Instruction {

	private RobotEngine theEngine;
	@SuppressWarnings("unused")
	private NavigationModule navigationModule;
	@SuppressWarnings("unused")
	private ItemContainer objectsContainer;

	/**
	 * Parsers the String returning an instance of QuitInstruction or throwing a
	 * WrongInstructionFormatException()
	 */

	public Instruction parse(String cad) throws WrongInstructionFormatException {
		if (cad.equalsIgnoreCase("QUIT") || cad.equalsIgnoreCase("SALIR"))
			return new QuitInstruction();
		else
			throw new WrongInstructionFormatException("Instrucci�n no v�lida");
	}

	/**
	 * Returns a description of the Instruction syntax. The string does not end
	 * with the line separator. It is up to the caller adding it before
	 * printing.
	 */

	public String getHelp() {
		return "QUIT|SALIR";
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
	 * Request the robot to stop the simulation
	 */

	public void execute() throws InstructionExecutionException {
		theEngine.requestQuit();
	}

}
