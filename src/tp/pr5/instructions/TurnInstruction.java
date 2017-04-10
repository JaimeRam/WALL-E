package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * Its execution rotates the robot This Instruction works if the robot writes
 * TURN LEFT or RIGHT or GIRAR LEFT or RIGHT
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class TurnInstruction extends java.lang.Object implements Instruction {

	private Rotation rotation;
	private RobotEngine theEngine;
	private NavigationModule navigationModule;
	@SuppressWarnings("unused")
	private ItemContainer objectsContainer;

	public TurnInstruction() {
	}

	public TurnInstruction(Rotation rot) {
		rotation = rot;
	}

	/**
	 * Parses the String returning a TurnInstruction instance or throwing a
	 * WrongInstructionFormatException()
	 */

	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String words[] = cad.split(" ");
		Instruction ins = null;

		if (words[0].equalsIgnoreCase("TURN")
				|| words[0].equalsIgnoreCase("GIRAR")) {

			if (words.length == 2) {
				if (words[1].equalsIgnoreCase("RIGHT")
						|| words[1].equalsIgnoreCase("DERECHA"))
					ins = new TurnInstruction(Rotation.RIGHT);

				else if ((words[1].equalsIgnoreCase("LEFT") || words[1]
						.equalsIgnoreCase("IZQUIERDA")))
					ins = new TurnInstruction(Rotation.LEFT);
				else
					throw new WrongInstructionFormatException();
			}

			else
				throw new WrongInstructionFormatException();

		} else
			throw new WrongInstructionFormatException();
		return ins;
	}

	/**
	 * Returns a description of the Instruction syntax. The string does not end
	 * with the line separator. It is up to the caller adding it before
	 * printing.
	 */

	public String getHelp() {
		return "TURN | GIRAR < LEFT|RIGHT >";
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
	 * Turns the robot left or right
	 */

	public void execute() throws InstructionExecutionException {
		// Modificamos el modelo
		theEngine.addFuel(-5);
		navigationModule.rotate(rotation);
		// Actualizamos la vista
		navigationModule.requestHeadingChanged();
		theEngine.requestRobotUpdate();
		// Preguntamos si aún tenemos fuel
		if (theEngine.getFuel() == 0)
			theEngine.requestQuit();
	}
}
