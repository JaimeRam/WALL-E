package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * The execution of this instruction shows the information of the inventory of
 * the robot or the complete description about the item with identifier id
 * contained in the inventory This Instruction works if the player writes SCAN
 * or ESCANEAR (id is not mandatory)
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class ScanInstruction extends java.lang.Object implements Instruction {

	private String id;
	@SuppressWarnings("unused")
	private RobotEngine theEngine;
	@SuppressWarnings("unused")
	private NavigationModule navigationModule;
	private ItemContainer objectsContainer;

	public ScanInstruction() {
		id = "";
	}

	public ScanInstruction(String Id) {
		id = Id;
	}

	/**
	 * Parses the String returning a ScanInstruction instance or throwing a
	 * WrongInstructionFormatException()
	 */

	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String words[] = cad.split(" ");

		if (words[0].equalsIgnoreCase("SCAN")
				|| words[0].equalsIgnoreCase("ESCANEAR"))
			if (words.length == 2)
				return new ScanInstruction(words[1]);
			else
				return new ScanInstruction();
		else
			throw new WrongInstructionFormatException();
	}

	/**
	 * Returns a description of the Instruction syntax. The string does not end
	 * with the line separator. It is up to the caller adding it before
	 * printing.
	 */

	public String getHelp() {
		return "SCAN | ESCANEAR [id]";
	}

	/**
	 * Set the execution context. The method receives the entire engine (engine,
	 * navigation and the robot container) even though the actual implementation
	 * of execute() may not require it.
	 */

	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		theEngine = engine;
		navigationModule = navigation;
		objectsContainer = robotContainer;

	}

	/**
	 * Prints the description of a concrete item or the complete inventory of
	 * the robot
	 */

	public void execute() throws InstructionExecutionException {
		if (!id.isEmpty()) {
			Item it = objectsContainer.getItem(id);
			if (it != null)
				objectsContainer.requestScanItem(it);
			else
				throw new InstructionExecutionException("The '" + id
						+ "' item is not in my inventory");
		}

		else if (objectsContainer.numberOfItems() > 0)
			objectsContainer.requestScanInventory();

		else
			throw new InstructionExecutionException("My inventory is empty");
	}
}
