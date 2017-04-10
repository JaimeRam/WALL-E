package tp.pr5.instructions;

import tp.pr5.Interpreter;
import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.CodeCard;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * The Instruction for using an item. This Instruction works if the user writes
 * OPERATE id or OPERAR id
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class OperateInstruction extends java.lang.Object implements Instruction {

	private String id;
	private RobotEngine theEngine;
	private NavigationModule navigationModule;
	private ItemContainer objectsContainer;

	public OperateInstruction() {
	}

	public OperateInstruction(String Id) {
		id = Id;
	}

	/**
	 * Parses the String returning an instance of OperateInstruction or throwing
	 * a WrongInstructionFormatException()
	 */

	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String words[] = cad.split(" ");

		if (words[0].equalsIgnoreCase("OPERATE")
				|| words[0].equalsIgnoreCase("OPERAR"))
			if (words.length == 2)
				return new OperateInstruction(words[1]);
			else
				throw new WrongInstructionFormatException(
						"Instrucción no válida");
		else
			throw new WrongInstructionFormatException("Instrucción no válida");
	}

	/**
	 * Returns a description of the Instruction syntax. The string does not end
	 * with the line separator. It is up to the caller adding it before
	 * printing.
	 */

	public String getHelp() {
		return "OPERATE|OPERAR <ID>";
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
	 * The robot uses the requested item.
	 */

	public void execute() throws InstructionExecutionException {
		Item it = objectsContainer.getItem(id);

		if (it != null && it.canBeUsed()) {
			if (!it.use(theEngine, navigationModule))
				throw new InstructionExecutionException(
						"I could not use the item '" + id + "'");
			if (!it.canBeUsed()) {
				objectsContainer.pickItem(it.getId());
				theEngine
						.saySomething("WALL·E says: What a pity! I have no more "
								+ id
								+ " in my inventory"
								+ Interpreter.LINE_SEPARATOR);
				// Avisamos a la vista de que ha cambiado
				objectsContainer.requestInventoryChange();
			}
		} else
			throw new InstructionExecutionException("I can not use the item '"
					+ id + "'");

		// Avisamos a la vista de los cambios
		if (it.getClass() != CodeCard.class)
			theEngine.requestRobotUpdate();
	}
}
