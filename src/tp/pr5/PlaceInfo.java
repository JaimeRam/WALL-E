package tp.pr5;

/**
 * PlaceInfo defines a non-modifiable interface over a Place. It is employed by
 * the classes that need to access the information contained in the place but
 * that cannot modify the place itself.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public interface PlaceInfo {

	/**
	 * Return the place name
	 * 
	 * @return The place name
	 */

	java.lang.String getName();

	/**
	 * Return the place description
	 * 
	 * @return The place description
	 */

	java.lang.String getDescription();

	/**
	 * Is this place the space ship?
	 * 
	 * @return true if the place represents a space ship
	 */

	boolean isSpaceship();

}
