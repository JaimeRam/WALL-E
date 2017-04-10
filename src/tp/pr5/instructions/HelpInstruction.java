package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * Shows the game help with all the instructions that the robot can execute.
 * This instruction works if the user writes HELP or AYUDA
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class HelpInstruction extends java.lang.Object implements Instruction {

	private RobotEngine theEngine;
	@SuppressWarnings("unused")
	private NavigationModule navigationModule;
	@SuppressWarnings("unused")
	private ItemContainer objectsContainer;

	/**
	 * Parses the String returning a HelpInstruction instance or throwing a
	 * WrongInstructionFormatException()
	 */

	public Instruction parse(String cad) throws WrongInstructionFormatException {
		if (cad.equalsIgnoreCase("HELP") || cad.equalsIgnoreCase("AYUDA"))
			return new HelpInstruction();
		else
			throw new WrongInstructionFormatException("Instrucción no v�lida");
	}

	/**
	 * Help syntax
	 */

	public String getHelp() {
		return "HELP|AYUDA";
	}

	/**
	 * Configuration of the context for this instruction
	 */

	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		theEngine = engine;
		navigationModule = navigation;
		objectsContainer = robotContainer;
	}

	/**
	 * Prints the help string of every instruction. It delegates to the
	 * Interpreter class.
	 */

	public void execute() throws InstructionExecutionException {
		theEngine.requestHelp();
	}
}
