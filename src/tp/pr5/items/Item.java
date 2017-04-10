package tp.pr5.items;

import tp.pr5.*;

/**
 * The superclass of every type of item. It contains the common information for
 * all the items and it defines the interface that the items must match
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public abstract class Item extends java.lang.Object implements
		Comparable<String> {

	protected String id; // Nombre del item
	protected String description; // Descripción del item

	/**
	 * Builds an item from a given identifier and description
	 * 
	 * @param id
	 *            - Item identifier
	 * @param description
	 *            - Item description
	 */

	protected Item(java.lang.String id, java.lang.String description) {
		this.id = id;
		this.description = description;
	}

	/**
	 * Checks if the item can be used. Subclasses must override this method
	 * 
	 * @return true if the item can be used
	 */

	public abstract boolean canBeUsed();

	/**
	 * Try to use the item with a robot in a given place. It returns whether the
	 * action was completed. Subclasses must override this method
	 * 
	 * @param r
	 *            - The robot that uses the item
	 * @param p
	 *            - The Place where the item is used
	 * @return
	 */

	public abstract boolean use(RobotEngine r, NavigationModule nav);

	/**
	 * Return the item identifier
	 * 
	 * @return The item identifier
	 */

	public java.lang.String getId() {
		return id;
	}

	/**
	 * Generates a String with the Item description
	 */

	public java.lang.String toString() {
		return getDescription();
	}

	/**
	 * Compares its two arguments for order. Returns a negative integer, zero,
	 * or a positive integer as the first argument is less than, equal to, or
	 * greater than the second.
	 * 
	 * @param id
	 * @return - a negative int if this < id, 0 if this == id, a positive int if
	 *         this > id
	 */

	public int compareTo(String id) { // Compara el item por el id
		return getId().compareTo(id);
	}

	/**
	 * Indicates whether some other object is "equal to" this comparator. This
	 * method must obey the general contract of Object.equals(Object).
	 * 
	 * @param id
	 *            - the item to compare
	 * @return If the items are equal
	 */

	public boolean equals(String id) { // Iguala los items por la id
		return getId().equalsIgnoreCase(id);
	}

	/**
	 * Return a description about the item
	 * 
	 * @return the item description
	 */

	public java.lang.String getDescription() {
		return description;
	}

}
