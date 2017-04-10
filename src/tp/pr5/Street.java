package tp.pr5;

import tp.pr5.items.CodeCard;

/**
 * A street links two places A and B in one direction. If it is defined as
 * Street(A,NORTH,B) it means that Place B is at NORTH of Place A. Streets are
 * two-way streets, i.e. if B is at NORTH of A then A is at SOUTH of B. Some
 * streets are closed and a code (contained in a code card) is needed to open
 * them
 * 
 * @author Jaime
 * 
 */

public class Street {

	private Place sourcePlace; // Place de origen
	private Place targetPlace; // Place de destino
	private Direction direction; // Dirección a seguir
	private boolean open; // Indica si la calle esta abierta o cerrada
	private String secretCode; // Código secreto para abrir la calle

	/**
	 * Creates an open street and it have not any code to open or close it
	 * 
	 * @param source
	 *            - Source place
	 * @param direction
	 *            - Represents how is placed the target place with respect to
	 *            the source place.
	 * @param target
	 *            - Target place
	 */

	public Street(Place source, Direction direction, Place target) {
		sourcePlace = source;
		this.direction = direction;
		targetPlace = target;
		open = true;
	}

	/**
	 * Creates a street that has a code to open and close it
	 * 
	 * @param source
	 *            - Source place
	 * @param direction
	 *            - Represents how is placed the target place with respect to
	 *            the source place.
	 * @param target
	 *            - Target place
	 * @param isOpen
	 *            - Determines is the street is opened or closed
	 * @param code
	 *            - The code that opens and closes the street
	 */

	public Street(Place source, Direction direction, Place target,
			boolean isOpen, java.lang.String code) {
		sourcePlace = source;
		this.direction = direction;
		targetPlace = target;
		open = isOpen;
		secretCode = code;
	}

	/**
	 * Checks if the street comes out from a place in a given direction.
	 * Remember that streets are two-way
	 * 
	 * @param place
	 *            - The place to check
	 * @param whichDirection
	 *            - Direction used
	 * @return Returns true if the street comes out from the input Place.
	 */

	public boolean comeOutFrom(Place place, Direction whichDirection) {
		// Basado en la implementación de Purificación Arenas Sánchez
		boolean b = false;
		if (targetPlace == place)
			b = direction == whichDirection.getOposite(whichDirection);
		else if (sourcePlace == place)
			b = direction == whichDirection;
		return b;
	}

	/**
	 * Returns the place of the other side from the place whereAmI. This method
	 * does not consider whether the street is open or closed.
	 * 
	 * @param whereAmI
	 *            - The place where I am.
	 * @return It returns the Place at the other side of the street (even if the
	 *         street is closed). Returns null if whereAmI does not belong to
	 *         the street.
	 */

	public Place nextPlace(Place whereAmI) {
		Place p = null;
		if (sourcePlace == whereAmI)
			p = targetPlace;
		else if (targetPlace == whereAmI)
			p = sourcePlace;
		return p;
	}

	/**
	 * Checks if the street is open or closed
	 * 
	 * @return true, if the street is open, and false when the street is closed
	 */

	public boolean isOpen() {
		return open;
	}

	/**
	 * Tries to open a street using a code card. Codes must match in order to
	 * complete this action
	 * 
	 * @param card
	 *            - A code card to open the street
	 * @return true if the action has been completed
	 */

	public boolean open(CodeCard card) {
		boolean result = false;

		if (secretCode.equals(card.getCode()))
			open = result = true;

		return result;
	}

	/**
	 * Tries to close a street using a code card. Codes must match in order to
	 * complete this action
	 * 
	 * @param card
	 *            - A code card to close the street
	 * @return true if the action has been completed
	 */

	public boolean close(CodeCard card) {
		boolean b = false;

		if (secretCode == card.getCode()) {
			open = false;
			b = true;
		}

		return b;

	}

	/**
	 * Get the secret code of code card
	 * 
	 * @return the code of code card
	 */

	public String getCode() {
		return secretCode;
	}
}
