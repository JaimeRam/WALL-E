package tp.pr5;

import java.io.*;

import tp.pr5.cityLoader.CityLoaderFromTxtFile;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.console.Console;
import tp.pr5.console.ConsoleController;
import tp.pr5.gui.GUIController;
import tp.pr5.gui.MainWindow;

import org.apache.commons.cli.*;

public class Main {

	/**
	 * Application entry-point. The application admits a parameter -m | --map
	 * with the name of the map file to be used, a parameter -i | --interface
	 * with the type of interface (console, swing or both) and a parameter -h |
	 * --help to show a help message about how to use this application.
	 * 
	 * If no arg is specified (or more than one file is given), it prints an
	 * error message (in System.err) and the application finishes with an error
	 * code (-1).
	 * 
	 * If the map file cannot be read (or it does not exist), the application
	 * ends with a different error code (-2).
	 * 
	 * If the interface arg is not correct (console, swing or both) the
	 * application prints a message and the application finishes with an error
	 * code (-3). Otherwise, the simulation starts and eventually the
	 * application will end normally (return code 0).
	 * 
	 * @param args
	 * @throws WrongCityFormatException
	 */

	public static void main(String[] args) throws WrongCityFormatException {

		CityLoaderFromTxtFile fileLoader = null;
		City cityMap = null;
		CommandLineParser cmdParser = null;
		CommandLine cmdLine = null;

		try {
			cmdParser = new BasicParser();
			cmdLine = cmdParser.parse(getOptions(), args);

			if (cmdLine.hasOption("h")) { // Help Option
				System.out
						.println("Execute this assignment with these parameters:");
				new HelpFormatter().printHelp(Main.class.getCanonicalName()
						+ " [-h] [-i <type>] [-m <mapfile>]", getOptions());
			}

			else {

				String interfaceMode = cmdLine.getOptionValue("i"), mapFile = cmdLine
						.getOptionValue("m");

				if (mapFile != null && interfaceMode != null) {

					try {

						if (!interfaceMode.equalsIgnoreCase("swing")
								&& !interfaceMode.equalsIgnoreCase("console")
								&& !interfaceMode.equalsIgnoreCase("both")) {
							System.err.println("Wrong type of interface");
							System.exit(3);
						}

						else {
							InputStream file = new FileInputStream(mapFile);
							fileLoader = new CityLoaderFromTxtFile();
							try {
								cityMap = fileLoader.loadCity(file);
							} catch (WrongCityFormatException e) {
								System.err.println(e.getMessage());
								System.exit(2);
							}

							RobotEngine robot = new RobotEngine(cityMap,
									fileLoader.getInitialPlace(),
									Direction.NORTH);
							if (interfaceMode.equalsIgnoreCase("console"))
								startConsoleMode(robot);
							else if (interfaceMode.equalsIgnoreCase("swing"))
								startSwingMode(robot);
							else
								startBothMode(robot);
						}

					} catch (IOException e) {
						System.err.println("Error reading the map file: "
								+ mapFile
								+ " (No existe el fichero o el directorio)");
						System.exit(2);
					}
				} else {
					if (mapFile != null && interfaceMode == null)
						System.err.println("Interface not specified");
					else
						System.err.println("Map file not specified");
					System.exit(1);
				}

			}

		} catch (org.apache.commons.cli.ParseException e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * Método que añade las opciones que podremos ejecutar como argumentos
	 * 
	 * @return opciones de uso
	 */

	private static Options getOptions() {
		Options options = new Options();

		Option help = new Option("h", "help", false, "Shows this help message");

		Option interfaces = new Option("i", "interface", true,
				"The type of interface: console, swing or both");
		Option maps = new Option("m", "map", true,
				"File with the description of the city");
		interfaces.setArgName("type");
		maps.setArgName("mapfile");

		options.addOption(help);
		options.addOption(interfaces);
		options.addOption(maps);

		return options;
	}

	/**
	 * Mátodo para arrancar el juego desde el modo consola
	 * 
	 * @param robot
	 *            - the RobotEngine
	 */

	private static void startConsoleMode(RobotEngine robot) {
		ConsoleController consoleController = new ConsoleController(robot);
		Console console = new Console();
		robot.addEngineObserver(console);
		robot.addNavigationObserver(console);
		robot.addItemContainerObserver(console);
		consoleController.startController();
	}

	/**
	 * Método para arrancar el juego desde el modo gráfico (swing)
	 * 
	 * @param robot
	 *            - the RobotEngine
	 */

	private static void startSwingMode(RobotEngine robot) {
		GUIController guiController = new GUIController(robot);
		new MainWindow(guiController);
		guiController.startController();
	}

	/**
	 * Método para arrancar el juego desde consola y ventana gráfica (swing)
	 * 
	 * @param robot
	 *            - the RobotEngine
	 */

	private static void startBothMode(RobotEngine robot) {
		GUIController guiController = new GUIController(robot);
		Console console = new Console();
		robot.addEngineObserver(console);
		robot.addNavigationObserver(console);
		robot.addItemContainerObserver(console);
		new MainWindow(guiController);
		guiController.startController();
	}

}
