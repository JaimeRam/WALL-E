package tp.pr5;

import tp.pr5.Interpreter;
import tp.pr5.gui.PlaceCell;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * It represents a place in the city. Places are connected by streets according
 * to the 4 compass directions, North, East, South and West. Every place has a
 * name and a textual description about itself. This description is displayed
 * when the robot arrives at the place. A place can represent the spaceship
 * where the robot is safe. When the robot arrives at this place, the
 * application is over.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class Place extends java.lang.Object implements PlaceInfo {

	private String placeName; // Nombre de la plaza
	private String placeDescription; // Descripción de la plaza
	private boolean spaceShip; // Indica si está la nave de regreso
	private ItemContainer itemContainer; // Contenedor de items de la plaza
	private PlaceCell placeCell; // Celda que representa la Place en swing

	/**
	 * Creates the place
	 * 
	 * @param name
	 * @param isSpaceShip
	 *            - Is it a spaceship?
	 * @param description
	 *            - Place description
	 */

	public Place(java.lang.String name, boolean isSpaceShip,
			java.lang.String description) {
		placeName = name;
		spaceShip = isSpaceShip;
		placeDescription = description;
		itemContainer = new ItemContainer();
	}

	/**
	 * Is this place the space ship?
	 * 
	 * @return true if the place represents a space ship
	 */

	public boolean isSpaceship() {
		return spaceShip;
	}

	/**
	 * Overrides toString method. Returns the place name, its description and
	 * the list of items contained in the place
	 */

	public java.lang.String toString() {
		String str = null;

		if (itemContainer.numberOfItems() == 0)
			str = placeName + Interpreter.LINE_SEPARATOR + placeDescription
					+ Interpreter.LINE_SEPARATOR
					+ "The place is empty. There are no objects to pick";

		else
			str = placeName + Interpreter.LINE_SEPARATOR + placeDescription
					+ Interpreter.LINE_SEPARATOR
					+ "The place contains these objects:"
					+ Interpreter.LINE_SEPARATOR + itemContainer.toString();
		return str;
	}

	/**
	 * public boolean existItem(java.lang.String id)
	 * 
	 * @param id
	 *            - Identifier of an item
	 * @return true if and only if the place contains the item identified by id
	 */

	public boolean existItem(java.lang.String id) {
		return itemContainer.containsItem(id);
	}

	/**
	 * Tries to pick an item characterized by a given identifier from the place.
	 * If the action was completed the item must be removed from the place.
	 * 
	 * @param id
	 *            - The identifier of the item
	 * @return
	 */

	public Item pickItem(java.lang.String id) {
		return itemContainer.pickItem(id);
	}

	/**
	 * Tries to add an item to the place. The operation can fail (if the place
	 * already contains an item with the same name)
	 * 
	 * @param it
	 * @return true if and only if the item can be added to the place, i.e., the
	 *         place does not contain an item with the same name
	 */

	public boolean addItem(Item it) {
		return itemContainer.addItem(it);
	}

	/**
	 * Drop an item in this place. The operation can fail, returning false
	 * 
	 * @param item
	 *            - The name of the item to be dropped.
	 * @return true if and only if the item is dropped in the place, i.e., an
	 *         item with the same identifier does not exists in the place
	 */

	public boolean dropItem(Item it) {
		return addItem(it);
	}

	/**
	 * Return the place name
	 * 
	 * @return The place name
	 */

	public java.lang.String getName() {
		return placeName;
	}

	// Métodos propios

	/**
	 * Método que cambia el color de la PlaceCell si ha sido visitado (Gris)
	 */

	public void visitedPlace() {
		if (placeCell != null) {
			placeCell.cambiarColorVisitado();
		}
	}

	/**
	 * Método que Inicializa la PlaceCell, fijando el nombre de la Place y el
	 * color de NoVisitado (Verde)
	 * 
	 * @param pc
	 *            - the actual place cell
	 */

	public void currentPlace(PlaceCell pc) {
		placeCell = pc;
		if (placeCell != null) {
			placeCell.cambiarTextoBtn();
			placeCell.cambiarColorNoVisitado();
		}
	}

	/**
	 * Returns the place description
	 */

	public String getDescription() {
		return placeDescription + Interpreter.LINE_SEPARATOR
				+ itemContainer.toString();
	}

	/**
	 * Gets the associated PlaceCell Place
	 * 
	 * @return the PlaceCell of Place
	 */

	public PlaceCell getPlaceCell() {
		return placeCell;
	}

}
