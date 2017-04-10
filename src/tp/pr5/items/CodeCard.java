package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Street;

/**
 * A CodeCard can open or close the door placed in the streets. The card
 * contains a code that must match the street code in order to perform the
 * action.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class CodeCard extends Item {

	private final String secretCode;

	/**
	 * Code card constructor
	 * 
	 * @param id
	 *            - Code card name
	 * @param description
	 *            - Code card description
	 * @param code
	 *            - Secret code stored in the code card
	 */

	public CodeCard(java.lang.String id, java.lang.String description,
			java.lang.String code) {
		super(id, description);
		secretCode = code;
	}

	/**
	 * A code card always can be used. Since the robot has the code card it
	 * never loses it
	 */

	public boolean canBeUsed() {
		return true;
	}

	/**
	 * Gets the code stored in the code card
	 * 
	 * @return A String that represents the code
	 */

	public java.lang.String getCode() { /* Gets the code stored in the code card */
		return secretCode;
	}

	/**
	 * The method to use a code card. If the robot is in a place which contains
	 * a street in the direction he is looking at, then the street is opened or
	 * closed if the street code and the card code match.
	 */

	public boolean use(RobotEngine r, NavigationModule nav) {

		boolean result = false;
		Street st = nav.getHeadingStreet();

		if (st == null)
			result = false;

		else {
			if (st.getCode().equalsIgnoreCase(secretCode)) {
				if (st.isOpen())
					result = st.close(this);
				else
					result = st.open(this);
			}
		}

		return result;
	}

}
