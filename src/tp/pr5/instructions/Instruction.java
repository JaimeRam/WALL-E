package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * This interface represents an instruction supported by the application. Every
 * instruction that the robot may perform implements this interface. It forces
 * instructions to provide with the implementation of four different methods:
 * Parse method tries to parse a string with the information of the instruction
 * the class represents Help method returns a string with an explanation of the
 * kind of expression that the parse method supports ConfigureContext method
 * establishes the context needed to execute the instruction Execute method
 * performs the actual work of the instruction, executing it. The execute method
 * does not have any parameter. Therefore the context of execution must be given
 * to the instruction object prior to its invocation using the configureContext
 * method. Note that the actual context depends on the subclass because the
 * information needed varies between instructions.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public interface Instruction {

	/**
	 * Parses the String returning an instance its corresponding subclass if the
	 * string fits the instruction's syntax. Otherwise it throws an
	 * WrongInstructionFormatException. Each non abstract subclass must
	 * implement its corresponding parse.
	 * 
	 * @param cad
	 *            - Text String
	 * @return Instruction Reference pointing to an instance of a Instruction
	 *         subclass, if it is corresponding to the String cad
	 * @throws WrongInstructionFormatException
	 *             - When the String cad does not fit the Instruction syntax.
	 */

	Instruction parse(java.lang.String cad)
			throws WrongInstructionFormatException;

	/**
	 * Returns a description of the Instruction syntax. The string does not end
	 * with the line separator. It is up to the caller adding it before
	 * printing.
	 * 
	 * @return The Instruction's syntax.
	 */

	java.lang.String getHelp();

	/**
	 * Set the execution context. The method receives the entire engine (engine,
	 * navigation and the robot container) even though the actual implementation
	 * of execute() may not require it.
	 * 
	 * @param engine
	 *            - The robot engine
	 * @param navigation
	 *            - The information about the game, i.e., the places, current
	 *            direction and current heading to navigate
	 * @param robotContainer
	 *            - The inventory of the robot
	 */

	void configureContext(RobotEngine engine, NavigationModule navigation,
			ItemContainer robotContainer);

	/**
	 * Executes the Instruction It must be implemented in every non abstract
	 * subclass.
	 * 
	 * @throws InstructionExecutionException
	 *             - if there exist any execution error.
	 */

	void execute() throws InstructionExecutionException;
}