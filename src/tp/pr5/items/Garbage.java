package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;

/**
 * The garbage is a type of item that generates recycled material after using
 * it. The garbage can be used only once. After using it, it must be removed
 * from the robot inventory
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class Garbage extends Item {

	private int recycledMaterial; // Cantidad de chatarra a reclicar

	/**
	 * Garbage constructor
	 * 
	 * @param id
	 *            - Item id
	 * @param description
	 *            - Item description
	 * @param recycledMaterial
	 *            - The amount of recycled material that the item generates
	 */

	public Garbage(java.lang.String id, java.lang.String description,
			int recycledMaterial) {
		super(id, description);
		this.recycledMaterial = recycledMaterial;

	}

	/**
	 * Garbage can be used only once
	 */

	public boolean canBeUsed() {
		return recycledMaterial > 0;
	}

	/**
	 * The garbage generates recycled material for the robot that uses it
	 */

	public boolean use(RobotEngine r, NavigationModule nav) {

		boolean result = false;

		if (canBeUsed()) {
			r.addRecycledMaterial(recycledMaterial);
			recycledMaterial = 0;
			result = true;
		}

		return result;
	}

	/**
	 * Generates a String with the Item description
	 */

	public java.lang.String toString() {
		return description + "//" + " recycled material = " + recycledMaterial;
	}

}
