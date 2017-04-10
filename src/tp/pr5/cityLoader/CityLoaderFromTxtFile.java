package tp.pr5.cityLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import tp.pr5.City;
import tp.pr5.Direction;
import tp.pr5.Place;
import tp.pr5.Street;
import tp.pr5.cityLoader.cityLoaderExceptions.WrongCityFormatException;
import tp.pr5.items.CodeCard;
import tp.pr5.items.Fuel;
import tp.pr5.items.Garbage;

/**
 * City loader from a txt file The mandatory format must be:
 * 
 * BeginCity BeginPlaces place 0 Entrada
 * Estamos_en_la_entrada._Comienza_la_aventura noSpaceShip place 1 Callao
 * In_this_square... spaceShip ... EndPlaces BeginStreets street 0 place 0 south
 * place 1 open street 1 place 1 east place 2 open street 2 place 2 north place
 * 3 closed onetwothreefourfive ... EndStreets BeginItems fuel 0 Petrol
 * from_olds_heatings 10 3 place 0 fuel 1 Battery to_get_cracking -50 1 place 0
 * codecard 2 Card The_key_is_too_easy onetwothreefourfive place 1 garbage 3
 * Newspapers News_on_sport 30 place 2 ... EndItems EndCity
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class CityLoaderFromTxtFile extends java.lang.Object {

	private Place initialPlace; // Lugar inicial donde comienza el juego
	private List<Place> places; // Lista de plazas que se van añadiendo al juego
	private List<Street> streets; // Lista de calles del juego

	/**
	 * Creates the different array lists
	 */

	public CityLoaderFromTxtFile() {
		places = new ArrayList<Place>();
		streets = new ArrayList<Street>();
	}

	/**
	 * Builds a city from an input File
	 * 
	 * @param file
	 *            - The input stream where the city is stored
	 * @return The city
	 * @throws WrongCityFormatException
	 *             - When there is some format error in the file
	 *             (WrongCityFormatException) or some errors in IO operations.
	 */

	public City loadCity(java.io.InputStream file)
			throws WrongCityFormatException {
		Scanner s = new Scanner(file);
		String l = s.nextLine();
		City cityMap = new City();

		if (!l.equalsIgnoreCase("BeginCity")) {
			s.close();
			throw new WrongCityFormatException(
					"El fichero no comienza con 'BeginCity'");
		}

		readPlaces(s, places);
		readStreets(s, streets, places);
		readItems(s, places, streets);

		l = s.nextLine();

		if (!l.equalsIgnoreCase("EndCity"))
			throw new WrongCityFormatException(
					"El fichero no termina con 'EndCity'");

		initialPlace = places.get(0);

		// Conversión de ArrayList a Array, ya que la constructora de City por
		// definición sólo admite un Street[]
		Street[] aux = streets.toArray(new Street[streets.size()]);

		s.close();
		cityMap = new City(aux);

		return cityMap;

	}

	/**
	 * Returns the place where the robot will start the simulation
	 * 
	 * @return The initial place
	 */

	public Place getInitialPlace() {
		return initialPlace;
	}

	/**
	 * Evalua la información de una place dado una cadena, una lista de Place y
	 * el supuesto índice de la place
	 * 
	 * @param l
	 *            - linea completa con la información de la Place
	 * @param p
	 *            - List<Place> con los Place acumulados hasta el momento
	 * @param i
	 *            - entero que indica el número de la siguiente place esperada
	 *            por orden ascendente
	 * @throws WrongCityFormatException
	 */

	private void checkInfoPlace(String l, List<Place> p, int i)
			throws WrongCityFormatException {
		String[] line = l.split(" ");

		if (line.length == 5) {
			if (!line[0].equalsIgnoreCase("place"))
				throw new WrongCityFormatException(
						"El string <place> de la place " + i
								+ " no es correcto");
			if (!line[1].equalsIgnoreCase(Integer.toString(i)))
				throw new WrongCityFormatException("El num. de la Place "
						+ line[1] + " no corresponde con el que debería (" + i
						+ ")");
			if (!line[4].equalsIgnoreCase("spaceShip")
					&& !line[4].equalsIgnoreCase("noSpaceShip"))
				throw new WrongCityFormatException(
						"La caracteristica de <isSpaceship> de la place " + i
								+ " no es correcta");
			boolean isSpaceShip = line[4].equalsIgnoreCase("spaceShip");
			Place place = new Place(line[2], isSpaceShip, line[3].replace("_",
					" "));
			p.add(place);
		}
	}

	/**
	 * Evalua la información de una street dado una cadena, una lista de Street
	 * y Places, y el supuesto índice de la street
	 * 
	 * @param l
	 *            - línea completa con la información de la Street
	 * @param i
	 *            - entero que indica el número de la siguiente Street esperada
	 *            por orden ascendente
	 * @param s
	 *            - List<Street> acumulados hasta el momento
	 * @param p
	 *            - List<Place> acumulados hasta el momento
	 * @throws WrongCityFormatException
	 */

	private void checkInfoStreet(String l, int i, List<Street> s, List<Place> p)
			throws WrongCityFormatException {

		String[] line = l.split(" ");
		if (line.length >= 8) {
			if (!line[0].equalsIgnoreCase("street"))
				throw new WrongCityFormatException(
						"El string <street> de la calle " + i
								+ " no es correcto");
			if (!line[1].equalsIgnoreCase(Integer.toString(i)))
				throw new WrongCityFormatException("El num. de la Street "
						+ line[1] + " no corresponde con el que debería (" + i
						+ ")");
			if (!line[2].equalsIgnoreCase("place"))
				throw new WrongCityFormatException(
						"El primer string <Place> de la place " + i
								+ " no es correcto");
			if (!line[4].equalsIgnoreCase("north")
					&& line[4].equalsIgnoreCase("south")
					&& !line[4].equalsIgnoreCase("west")
					&& line[4].equalsIgnoreCase("east"))
				throw new WrongCityFormatException(
						"El string <direccion> de la calle " + i
								+ " no es correcto");
			if (!line[5].equalsIgnoreCase("place"))
				throw new WrongCityFormatException(
						"El segundo string <Place> de la calle " + i
								+ " no es correcto");
			if (!line[7].equalsIgnoreCase("open")
					&& !line[7].equalsIgnoreCase("closed"))
				throw new WrongCityFormatException(
						"La caracteristica <isOpen> de la calle " + i
								+ "no es correcta");

			int i1 = Integer.parseInt(line[3]);
			int i2 = Integer.parseInt(line[6]);

			if (i1 > p.size() || i2 > p.size())
				throw new WrongCityFormatException(
						"El número de la place no existe");

			Direction dir = null;

			if (line[4].equalsIgnoreCase("north"))
				dir = Direction.NORTH;
			else if (line[4].equalsIgnoreCase("south"))
				dir = Direction.SOUTH;
			else if (line[4].equalsIgnoreCase("west"))
				dir = Direction.WEST;
			else if (line[4].equalsIgnoreCase("east"))
				dir = Direction.EAST;

			boolean isOpen = line[7].equalsIgnoreCase("open");

			String code = null;
			if (line.length > 8)
				code = line[8];

			Street st = new Street(p.get(i1), dir, p.get(i2), isOpen, code);
			s.add(st);
		}
	}

	/**
	 * Evalua la información de un item dado una cadena, una lista de Items y el
	 * supuesto índice del item
	 * 
	 * @param l
	 *            - línea completa con la información de la Street
	 * @param i
	 *            - entero que indica el número de la siguiente Street esperada
	 *            por orden ascendente
	 * @param p
	 *            - List<Place> acumulados hasta el momento
	 * @param s
	 *            - List<Street> acumulados hasta el momento
	 * @throws WrongCityFormatException
	 */

	private void checkInfoItem(String l, int i, List<Place> p, List<Street> s)
			throws WrongCityFormatException {

		String[] line = l.split(" ");
		if ((line[0].equalsIgnoreCase("fuel") && line.length == 8)
				|| (line[0].equalsIgnoreCase("codecard") && line.length == 7)
				|| (line[0].equalsIgnoreCase("garbage") && line.length == 7)) {

			if (!line[0].equalsIgnoreCase("fuel")
					&& !line[0].equalsIgnoreCase("codecard")
					&& !line[0].equalsIgnoreCase("garbage"))
				throw new WrongCityFormatException("El Item " + i
						+ "no es del tipo fuel, codecard o garbage");
			if (!line[1].equalsIgnoreCase(Integer.toString(i)))
				throw new WrongCityFormatException("El num. del Item "
						+ line[1] + " no corresponde con el que debería (" + i
						+ ")");
			if (line[0].equalsIgnoreCase("fuel")) {
				int power = Integer.parseInt(line[4]);
				int times = Integer.parseInt(line[5]);
				if (!line[6].equalsIgnoreCase("place"))
					throw new WrongCityFormatException(
							"El string <place> del Item " + i
									+ " no es correcto");

				int n = Integer.parseInt(line[7]);

				if (n > p.size())
					throw new WrongCityFormatException("El Item fuel " + i
							+ " referencia a una place que no existe");

				p.get(n).addItem(
						new Fuel(line[2], line[3].replace("_", " "), power,
								times));

			} else if (line[0].equalsIgnoreCase("codecard")) {
				if (!line[5].equalsIgnoreCase("place"))
					throw new WrongCityFormatException("El Item codeCard " + i
							+ " no tiene asociada ninguna place");

				if (line[6].equalsIgnoreCase(null))
					throw new WrongCityFormatException("El Item codeCard " + i
							+ " no tiene asociada ninguna place");
				int n = Integer.parseInt(line[6]);

				if (n > p.size())
					throw new WrongCityFormatException("El Item codecard " + i
							+ " referencia a una place que no existe");

				String code = line[4];

				p.get(n).addItem(
						new CodeCard(line[2], line[3].replace("_", " "), code));

			} else if (line[0].equalsIgnoreCase("garbage")) {
				int power = Integer.parseInt(line[4]);
				if (!line[5].equalsIgnoreCase("place"))
					throw new WrongCityFormatException("El Item codeCard " + i
							+ " no tiene asociada ninguna place");

				int n = Integer.parseInt(line[6]);

				if (n > p.size())
					throw new WrongCityFormatException("El Item garbage " + i
							+ "referencia a una place que no existe");

				p.get(n).addItem(
						new Garbage(line[2], line[3].replace("_", " "), power));
			}
		}
	}

	/**
	 * Procedimiento encargado de leer las Places llamando a otra función
	 * auxiliar por cada Place
	 * 
	 * @param s
	 *            - Scanner para leer de nuestro fichero de texto
	 * @param p
	 *            - List<Place> en el que acumulamos las Place que vamos leyendo
	 * @throws WrongCityFormatException
	 */

	private void readPlaces(Scanner s, List<Place> p)
			throws WrongCityFormatException {
		if (!s.hasNextLine())
			throw new WrongCityFormatException(
					"El fichero no tiene mas texto en las places");
		String l = s.nextLine();
		if (!l.equalsIgnoreCase("BeginPlaces"))
			throw new WrongCityFormatException(
					"El texto de Streets no empieza con BeginPlaces");
		else {
			if (!s.hasNextLine())
				throw new WrongCityFormatException(
						"El fichero no tiene mas texto en las Places");
			l = s.nextLine();
			int i = 0;
			while (s.hasNextLine() && !l.equalsIgnoreCase("EndPlaces")) {
				checkInfoPlace(l, p, i);
				i++;
				l = s.nextLine();
			}

			if (!s.hasNextLine())
				throw new WrongCityFormatException(
						"El fichero no tiene mas texto en las places");
			if (!l.equalsIgnoreCase("EndPlaces"))
				throw new WrongCityFormatException(
						"El texto de Streets no termina con 'EndPlaces'");
		}
	}

	/**
	 * Procedimiento encargado de leer las Streets llamando a otra función
	 * auxiliar por cada Street
	 * 
	 * @param s
	 *            - Scanner para leer de nuestro fichero de texto
	 * @param st
	 *            - List<Strret> en el que acumulamos las Street que vamos
	 *            leyendo
	 * @param r
	 *            - List<Place> en el que acumulamos las Place que vamos leyendo
	 * @throws WrongCityFormatException
	 */

	private void readStreets(Scanner s, List<Street> st, List<Place> r)
			throws WrongCityFormatException {
		if (!s.hasNextLine())
			throw new WrongCityFormatException(
					"El fichero no tiene mas texto en las calles");
		String l = s.nextLine();
		if (!l.equalsIgnoreCase("BeginStreets"))
			throw new WrongCityFormatException(
					"El texto de Streets no empieza con BeginStreets");
		if (!s.hasNextLine())
			throw new WrongCityFormatException(
					"El fichero no tiene mas texto en las calles");
		else {
			l = s.nextLine();
			int i = 0;
			while (s.hasNextLine() && !l.equalsIgnoreCase("EndStreets")) {
				checkInfoStreet(l, i, st, r);
				i++;
				l = s.nextLine();
			}

			if (!s.hasNextLine())
				throw new WrongCityFormatException(
						"El fichero no tiene mas texto en las calles");
			if (!l.equalsIgnoreCase("EndStreets"))
				throw new WrongCityFormatException(
						"El texto de Streets no termina con EndStreets");
		}
	}

	/**
	 * Procedimiento encargado de leer los Items llamando a otra función
	 * auxiliar por cada Item
	 * 
	 * @param s
	 *            - Scanner para leer de nuestro fichero de texto
	 * @param pl
	 *            - ArrayList<Place>
	 * @param st
	 *            - ArrayList<Street>
	 * @throws WrongCityFormatException
	 */

	private void readItems(Scanner s, List<Place> pl, List<Street> st)
			throws WrongCityFormatException {
		if (!s.hasNextLine())
			throw new WrongCityFormatException(
					"El fichero no tiene mas texto en los Items");
		String l = s.nextLine();
		if (!l.equalsIgnoreCase("BeginItems"))
			throw new WrongCityFormatException(
					"El texto de items no empieza con BeginItems");
		if (!s.hasNextLine())
			throw new WrongCityFormatException(
					"El fichero no tiene mas texto en los Items");
		else {
			l = s.nextLine();
			int i = 0;
			while (s.hasNextLine() && !l.equalsIgnoreCase("EndItems")) {
				checkInfoItem(l, i, pl, st);
				i++;
				l = s.nextLine();
			}

			if (!s.hasNextLine())
				throw new WrongCityFormatException(
						"El fichero no tiene mas texto en los Items");
			if (!l.equalsIgnoreCase("EndItems"))
				throw new WrongCityFormatException(
						"El texto de items no termina con EndItems");
		}
	}
}
