package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * This Instruction shows the description of the current place and the items in
 * it. This Instruction works if the user writes RADAR
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class RadarInstruction extends java.lang.Object implements Instruction {

	@SuppressWarnings("unused")
	private RobotEngine theEngine;
	private NavigationModule navigationModule;
	@SuppressWarnings("unused")
	private ItemContainer objectsContainer;

	/**
	 * Parses the String returning an instance of RadarInstruction or throwing a
	 * WrongInstructionFormatException()
	 */

	public Instruction parse(String cad) throws WrongInstructionFormatException {
		if (cad.equalsIgnoreCase("RADAR"))
			return new RadarInstruction();
		else
			throw new WrongInstructionFormatException("Instrucción no válida");
	}

	/**
	 * Returns a description of the Instruction syntax. The string does not end
	 * with the line separator. It is up to the caller adding it before
	 * printing.
	 */

	public String getHelp() {
		return "RADAR";
	}

	/**
	 * Configure the context
	 */

	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		theEngine = engine;
		navigationModule = navigation;
		objectsContainer = robotContainer;

	}

	/**
	 * Shows the current place description
	 */

	public void execute() throws InstructionExecutionException {
		navigationModule.scanCurrentPlace();
	}
}
