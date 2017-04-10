package tp.pr5;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tp.pr5.Interpreter;
import tp.pr5.instructions.DropInstruction;
import tp.pr5.instructions.HelpInstruction;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.MoveInstruction;
import tp.pr5.instructions.OperateInstruction;
import tp.pr5.instructions.PickInstruction;
import tp.pr5.instructions.QuitInstruction;
import tp.pr5.instructions.RadarInstruction;
import tp.pr5.instructions.ScanInstruction;
import tp.pr5.instructions.TurnInstruction;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

/**
 * The interpreter is in charge of converting the user input in an instruction
 * for the robot. Up to now, the valid instructions are: MOVE TURN { LEFT |
 * RIGHT } PICK <ITEM> SCAN [ <ITEM> ] OPERATE <ITEM> RADAR DROP <ITEM> HELP
 * QUIT
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class Interpreter extends java.lang.Object {

	public static final String LINE_SEPARATOR = System
			.getProperty("line.separator");
	public static final List<Instruction> listaInstrucciones = getListInstruction();

	/**
	 * Generates a new instruction according to the user input
	 * 
	 * @param line
	 *            - A string with the user input
	 * @return The instruction read from the given line. If the instruction is
	 *         not correct, then it throws an exception.
	 * @throws WrongInstructionFormatException
	 */

	public static Instruction generateInstruction(java.lang.String line)
			throws WrongInstructionFormatException {

		Instruction ret = null;
		Iterator<Instruction> i = listaInstrucciones.iterator();
		boolean isValid = false;
		while (i.hasNext() && !isValid) {
			isValid = true;
			ret = i.next();
			try {
				ret = ret.parse(line);
			} catch (WrongInstructionFormatException e) {
				isValid = false;
			}
		}

		if (ret == null || !isValid)
			throw new WrongInstructionFormatException(
					"WALL·E says: I do not understand. Please repeat");
		return ret;
	}

	/**
	 * It returns information about all the instructions that the robot
	 * understands
	 * 
	 * @return A string with the information about all the available
	 *         instructions
	 */

	public static java.lang.String interpreterHelp() {
		String str = "";

		for (Iterator<Instruction> i = listaInstrucciones.iterator(); i
				.hasNext();) {
			if (i.hasNext())
				str = str + "     " + i.next().getHelp()
						+ Interpreter.LINE_SEPARATOR;
			else
				str = str + "     " + i.next().getHelp();
		}

		return str;
	}

	/**
	 * Método auxiliar para guardar todas las instrucciones en una lista y
	 * mostrar su ayuda
	 * 
	 * @return lista final con las instrucciones disponibles en el juego
	 */

	private static List<Instruction> getListInstruction() {
		List<Instruction> l = new ArrayList<Instruction>();
		l.add(new MoveInstruction());
		l.add(new TurnInstruction());
		l.add(new PickInstruction());
		l.add(new DropInstruction());
		l.add(new ScanInstruction());
		l.add(new RadarInstruction());
		l.add(new OperateInstruction());
		l.add(new HelpInstruction());
		l.add(new QuitInstruction());
		return l;
	}
}