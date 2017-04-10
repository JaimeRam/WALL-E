package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.Place;
import tp.pr5.PlaceInfo;

import javax.swing.ScrollPaneConstants;

/**
 * This class is in charge of the panel that displays the information about the
 * robot heading and the city that is traversing. It contains the grid that
 * represents the city in the Swing interface, a text area to show the place
 * descriptions, and a label with an icon which represents the robot heading
 * 
 * The 11x11 grid contains PlaceCell objects and the first place starts at
 * (5,5). This panel will update the visited places when the robot moves from
 * one place to another. Additionally it will show the place description on a
 * text area if the user clicks on a visited place.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class NavigationPanel extends javax.swing.JPanel implements
		NavigationObserver {

	private static final long serialVersionUID = 1L;
	private JPanel panelMapa; // JPanel con el mapa y descripcion
	private JPanel panelCityMap; // JPanel con el mapa de la ciudad
	private JPanel panelLog; // JPanel con las descripciones de los lugares
	private JTextArea textAreaInfoPlace; // texto con la información de la Place
	private ImageIcon imagenWallE; // Imagen de WALL·E, representa la dirección
	private JLabel lblWallE; // Label que almacena la imagen de WALL·E
	private PlaceCell[][] placeCell; // Array matricial con los lugares del mapa
	private PlaceCell actualPlaceCell; // PlaceCell actual
	// Mapa constante para que almacena las imágenes de WALL·E
	private static final Map<Direction, ImageIcon> m_mapIcons = loadIcons();

	/**
	 * Default constructor
	 */

	public NavigationPanel() {
		setLayout(new BorderLayout());

		panelMapa = new JPanel();
		panelMapa.setLayout(new BorderLayout(0, 0));
		add(panelMapa, BorderLayout.NORTH);

		panelCityMap = new JPanel();
		panelCityMap.setBorder(new TitledBorder("City Map"));
		panelCityMap.setLayout(new GridLayout(11, 11));
		panelCityMap.setPreferredSize(new Dimension(0, 375));

		lblWallE = new JLabel();
		imagenWallE = new ImageIcon("headingIcons/walleNorth.png");
		lblWallE.setIcon(imagenWallE);
		lblWallE.setSize(100, 120);
		panelMapa.add(panelCityMap, BorderLayout.CENTER);
		panelMapa.add(lblWallE, BorderLayout.WEST);

		panelLog = new JPanel();
		panelLog.setBorder(new TitledBorder("Log"));
		add(panelLog, BorderLayout.CENTER);
		panelLog.setLayout(new BorderLayout());
		panelLog.setPreferredSize(new Dimension(0, 150));

		textAreaInfoPlace = new JTextArea();
		textAreaInfoPlace.setTabSize(5);
		textAreaInfoPlace.setRows(5);
		textAreaInfoPlace.setEditable(false);
		JScrollPane scroll = new JScrollPane(textAreaInfoPlace);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelLog.add(scroll, BorderLayout.CENTER);

		loadIcons();
		loadCityMap();
	}

	/**
	 * Iniciapliza el panel de celdas y añede los Listeners
	 */

	private void loadCityMap() {

		placeCell = new PlaceCell[11][11];

		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				PlaceCell pc = new PlaceCell(i, j);
				pc.addActionListener(new ListenerPlaceDescription(pc));
				panelCityMap.add(pc);
				placeCell[i][j] = pc;
			}
		}
	}

	/**
	 * Carga las imágenes con los posibles rumbos de WALL·E, en un Mapa de clave
	 * <Direction> (dirección que toma WALL·E), y valor <ImageIcon> (imagen
	 * asociada a la dirección)
	 */

	private static Map<Direction, ImageIcon> loadIcons() {
		Map<Direction, ImageIcon> map = new HashMap<Direction, ImageIcon>();
		map.put(Direction.NORTH, new ImageIcon("headingIcons/walleNorth.png"));
		map.put(Direction.EAST, new ImageIcon("headingIcons/walleEast.png"));
		map.put(Direction.SOUTH, new ImageIcon("headingIcons/walleSouth.png"));
		map.put(Direction.WEST, new ImageIcon("headingIcons/walleWest.png"));
		return map;
	}

	/**
	 * Cambia la Imagen de WALL·E cada vez que se produce una rotación
	 * 
	 * @param dir
	 *            - la dirección a la que mira WALL·E
	 */

	public void changeImage(Direction dir) {
		lblWallE.setIcon(m_mapIcons.get(dir));
	}

	/**
	 * Incializa la primera celda del mapa en la Plaza incial (SOL)
	 * 
	 * @param initPlace
	 *            - plaza inicial donde comienza el juego
	 */

	public void paintInitialPlace(PlaceInfo initPlace) {
		actualPlaceCell = placeCell[5][5];
		actualPlaceCell.setPlace(initPlace);
		actualPlaceCell.cambiarTextoBtn();
		actualPlaceCell.cambiarColorNoVisitado();
		textAreaInfoPlace.setText(initPlace.getDescription());
	}

	/**
	 * Devuelve la Plaza actual donde se encuentra WALL·E
	 * 
	 * @return plaza actual
	 */

	public PlaceCell getActualPlaceCell() {
		return actualPlaceCell;
	}

	/**
	 * Fija la Place actual donde se ecneutra WALL·E
	 * 
	 * @param actualPlaceCell
	 *            - donde se encuentra WALL·E
	 */

	public void setActualPlaceCell(PlaceCell actualPlaceCell) {
		this.actualPlaceCell = actualPlaceCell;
	}

	/**
	 * Dibuja en el mapa la place a la que avanza WALL·E
	 * 
	 * @param currentPlace
	 *            - plaza actual
	 * @param i
	 *            nº de fila
	 * @param j
	 *            nº de columna
	 */

	public void paintCurrentPlace(PlaceInfo currentPlace, int i, int j) {
		actualPlaceCell = placeCell[i][j];
		actualPlaceCell.setPlace(currentPlace);
		actualPlaceCell.cambiarTextoBtn();
		actualPlaceCell.cambiarColorNoVisitado();
		textAreaInfoPlace.setText(currentPlace.getDescription());
	}

	/**
	 * Calcula la direccion visualmente de la celda a dónde se desplaza WALL·E
	 * 
	 * @param dir
	 *            - direeción actual
	 * @param pc
	 *            - celda actual
	 * @param place
	 *            - plaza actual
	 */

	public void avanza(Direction dir, Place place) {
		PlaceCell pc = actualPlaceCell;
		pc.cambiarColorVisitado();
		if (dir.equals(Direction.NORTH))
			paintCurrentPlace(place, pc.getFila() - 1, pc.getColumna());
		else if (dir.equals(Direction.SOUTH))
			paintCurrentPlace(place, pc.getFila() + 1, pc.getColumna());
		else if (dir.equals(Direction.EAST))
			paintCurrentPlace(place, pc.getFila(), pc.getColumna() + 1);
		else if (dir.equals(Direction.WEST))
			paintCurrentPlace(place, pc.getFila(), pc.getColumna() - 1);
	}

	/**
	 * Implementación del Listerer PlaceDescription
	 * 
	 * @author Jaime
	 * 
	 */

	class ListenerPlaceDescription implements ActionListener {

		private PlaceCell placeCell;

		/**
		 * Default Constructor
		 * 
		 * @param pc
		 *            - The PlaceCell
		 */

		public ListenerPlaceDescription(PlaceCell pc) {
			setPlaceCell(pc);
		}

		/**
		 * Muestra la información de una Place según se pincha en su PlaceCell
		 */

		public void actionPerformed(ActionEvent e) {
			PlaceInfo pi = placeCell.getPlace();
			if (pi != null)
				placeScanned(pi);
		}

		/**
		 * Devuelve la PlaceCell
		 * 
		 * @return the PlaceCell
		 */

		public void setPlaceCell(PlaceCell pc) {
			placeCell = pc;
		}

	}

	/**
	 * Notifies that the robot heading has changed
	 * 
	 * @param newHeading
	 *            - New robot heading
	 */

	@Override
	public void headingChanged(Direction newHeading) {
		changeImage(newHeading);
	}

	/**
	 * Notifies that the navigation module has been initialized
	 * 
	 * @param initialPlace
	 *            - The place where the robot starts the simulation
	 * 
	 * @param heading
	 *            - The initial robot heading
	 */

	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		paintInitialPlace(initialPlace);
	}

	/**
	 * Notifies that the robot has arrived at a place
	 * 
	 * @param heading
	 *            - The robot movement direction
	 * 
	 * @param place
	 *            - The place where the robot arrives
	 */

	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		avanza(heading, (Place) place);
	}

	/**
	 * Notifies that the user requested a RADAR instruction
	 * 
	 * @param placeDescription
	 *            - Information with the current place
	 */

	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		textAreaInfoPlace.setText(placeDescription.getDescription());
	}

	/**
	 * Notifies that the place where the robot stays has changed (because the
	 * robot picked or dropped an item)
	 * 
	 * @param placeDescription
	 *            -
	 */

	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		textAreaInfoPlace.setText(placeDescription.getDescription());
	}

}
