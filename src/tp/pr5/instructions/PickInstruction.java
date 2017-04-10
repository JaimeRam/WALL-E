package tp.pr5.instructions;

import tp.pr5.Interpreter;
import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * This instruction tries to pick an Item from the current place and puts it the
 * robot inventory. This instruction works if the user writes PICK id or COGER
 * id
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class PickInstruction extends java.lang.Object implements Instruction {

	private String id;
	private RobotEngine theEngine;
	private NavigationModule navigationModule;
	private ItemContainer objectsContainer;

	public PickInstruction() {
		id = "";
	}

	public PickInstruction(String Id) {
		id = Id;
	}

	/**
	 * Parses the String returning an instance of PickInstruction or throwing a
	 * WrongInstructionFormatException()
	 */

	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String words[] = cad.split(" ");

		if (words[0].equalsIgnoreCase("PICK")
				|| words[0].equalsIgnoreCase("COGER"))
			if (words.length == 2)
				return new PickInstruction(words[1]);
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
		return "PICK|COGER <id>";
	}

	/**
	 * Configures the context
	 */

	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		navigationModule = navigation;
		objectsContainer = robotContainer;
		theEngine = engine;
	}

	/**
	 * The robot adds an item to its inventory from the current place, if it
	 * exists
	 */

	public void execute() throws InstructionExecutionException {

		if (id.isEmpty())
			throw new InstructionExecutionException(
					"You have not selected anything to pick");

		else if (!objectsContainer.containsItem(id)
				&& navigationModule.findItemAtCurrentPlace(id)) {
			// Ejecutamos los cambios en el modelo
			Item it = navigationModule.pickItemFromCurrentPlace(id);
			objectsContainer.addItem(it);
			// Avisamos a la vista de que el modelo ha cambiado
			objectsContainer.requestInventoryChange();
			navigationModule.requestPlaceHasChanged();
			theEngine.saySomething("WALL·E says: I am happy! Now I have "
					+ it.getId() + Interpreter.LINE_SEPARATOR);
		}

		else
			throw new InstructionExecutionException("The '" + id
					+ "' item is not in this Place");

	}
}
