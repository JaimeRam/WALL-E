package tp.pr5.instructions;

import tp.pr5.Interpreter;
import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * This instruction drops an Item from the current place and puts it the robot
 * inventory. This instruction works if the user writes DROP or SOLTAR
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class DropInstruction extends java.lang.Object implements Instruction {

	private String id;
	private RobotEngine theEngine;
	private NavigationModule navigationModule;
	private ItemContainer objectsContainer;

	public DropInstruction() {
	}

	public DropInstruction(String Id) {
		id = Id;
	}

	/**
	 * Parses the String returning an instance of DropInstruction or throwing a
	 * WrongInstructionFormatException()
	 */

	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String words[] = cad.split(" ");

		if (words[0].equalsIgnoreCase("DROP")
				|| words[0].equalsIgnoreCase("SOLTAR"))
			if (words.length == 2)
				return new DropInstruction(words[1]);
			else
				throw new WrongInstructionFormatException(
						"Instrucción no válida");
		else
			throw new WrongInstructionFormatException("Instrucción no válida");
	}

	/**
	 * Instruction help
	 */

	public String getHelp() {
		return "DROP|SOLTAR <id>";
	}

	/**
	 * Fixes the current context, i.e., the robot engine and the navigation
	 * module
	 */

	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		theEngine = engine;
		navigationModule = navigation;
		objectsContainer = robotContainer;
	}

	/**
	 * The robot drops an Item from its inventory in the current place, if the
	 * item exists
	 */

	public void execute() throws InstructionExecutionException {
		Item it = objectsContainer.getItem(id);

		if (it == null || navigationModule.findItemAtCurrentPlace(id))
			throw new InstructionExecutionException("You do not have any " + id
					+ ".");
		else {
			// Ejecutamos los cambios en el modelo
			objectsContainer.pickItem(id);
			navigationModule.dropItemAtCurrentPlace(it);
			// Avisamos a la vista de que el modelo ha cambiado
			navigationModule.requestPlaceHasChanged();
			objectsContainer.requestInventoryChange();
			theEngine.saySomething("WALL·E says: Great! I have dropped "
					+ it.getId() + Interpreter.LINE_SEPARATOR);
		}
	}
}
