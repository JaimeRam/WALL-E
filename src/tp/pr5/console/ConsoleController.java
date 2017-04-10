package tp.pr5.console;

import java.util.Scanner;

import tp.pr5.Interpreter;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;

/**
 * The controller employed when the application is configured as a console
 * application. It contains the simulation loop that executes the instructions
 * written by the user on the console.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class ConsoleController extends tp.pr5.Controller {

	/**
	 * Constructor of the controller. It receives the model main class.
	 * 
	 * @param game
	 *            - Game that is being played.
	 */

	public ConsoleController(RobotEngine game) {
		super(game);
	}

	/**
	 * It starts the robot engine. Basically, it reads a line, the interpreter
	 * generates the corresponding instruction and executes it. The simulation
	 * finishes when the robot arrives at the space ship, the user types "QUIT",
	 * or the robot runs out of fuel
	 */

	public void startController() {

		theEngine.requestStart();
		Scanner keyboard = new Scanner(System.in);

		while (!theEngine.isOver()) {
			theEngine.saySomething("WALL·E> ");
			String line = keyboard.nextLine();
			try {
				Instruction ins = Interpreter.generateInstruction(line);
				theEngine.communicateRobot(ins);
			} catch (WrongInstructionFormatException e) {
				theEngine.requestError(e.getMessage());
			}
		}

		keyboard.close();
	}

}
