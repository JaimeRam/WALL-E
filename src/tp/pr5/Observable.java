package tp.pr5;

/**
 * 
 * Clase para almacenar en una Lista de tipo <T> los observadores del juego
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 * @param <T>
 *            - Type observer
 */

public class Observable<T> implements Iterable<T> {

	protected java.util.List<T> observers; // List of Observers

	/**
	 * Default constructor
	 */

	public Observable() {
		observers = new java.util.ArrayList<T>();
	}

	/**
	 * Adds an observer to the list of observers
	 * 
	 * @param observer
	 *            - the observer to add
	 */

	public void addObserver(T observer) {
		if (!observers.contains(observer))
			observers.add(observer);
	}

	/**
	 * Removes an observer to the list of observers
	 * 
	 * @param observer
	 *            - the observer to eliminate
	 */

	public void removeObserver(T observer) {
		if (observers.contains(observer))
			observers.remove(observer);
	}

	/**
	 * Generates an iterator pointing to the beginning of the list of observers
	 */

	public java.util.Iterator<T> iterator() {
		return observers.iterator();
	}

}
