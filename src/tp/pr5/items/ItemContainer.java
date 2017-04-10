package tp.pr5.items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import tp.pr5.*;

/**
 * A container of items. It can be employed by any class that stores items. A
 * container cannot store two items with the same identifier
 * 
 * It provides methods to add new items, access them and remove them from the
 * container.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class ItemContainer extends tp.pr5.Observable<InventoryObserver> {

	private List<Item> itemContainer; // Lista de items

	/**
	 * Creates the empty container
	 */

	public ItemContainer() {
		itemContainer = new ArrayList<Item>();
	}

	/**
	 * Add an item to the container. The operation can fail, returning false
	 * 
	 * @param item
	 *            - The name of the item to be added.
	 * @return true if and only if the item is added, i.e., an item with the
	 *         same identifier does not exists in the container
	 */

	public boolean addItem(Item item) {

		int pos = Collections.binarySearch(itemContainer, item.getId());

		if (pos < 0) { // Si pos < 0, el item no está en el itemContainer
			int i = (-(pos) - 1); // Fórmula para obtener el índice de la lista
			itemContainer.add(i, item);
		}

		return pos < 0; // Si pos < 0, el item ha sido añadido porque no exist�a
	}

	/**
	 * Returns the item from the container according to the item name
	 * 
	 * @param id
	 *            - Item name
	 * @return Item with that name or null if the container does not store an
	 *         item with that name.
	 */

	public Item getItem(java.lang.String id) {
		Item item = null;
		int pos = Collections.binarySearch(itemContainer, id);

		if (pos >= 0)
			item = itemContainer.get(pos);

		return item;
	}

	/**
	 * Returns the container's size
	 * 
	 * @return The number of items in the container
	 */

	public int numberOfItems() {
		return itemContainer.size();
	}

	/**
	 * Returns and deletes an item from the inventory. This operation can fail,
	 * returning null
	 * 
	 * @param id
	 *            - Name of the item
	 * @return An item if and only if the item identified by id exists in the
	 *         inventory. Otherwise it returns null
	 */

	public Item pickItem(java.lang.String id) {
		Item it = null;

		if (containsItem(id)) {
			it = getItem(id);
			itemContainer.remove(it);
		}

		return it;
	}

	/**
	 * Generates a String with information about the items contained in the
	 * container. Note that the items must appear sorted but the item name.
	 */

	public java.lang.String toString() {

		String str = "";

		java.util.Iterator<Item> i = itemContainer.iterator();

		while (i.hasNext()) {
			str = str + "   " + i.next().getId();
			if (i.hasNext())
				str = str + Interpreter.LINE_SEPARATOR;
		}

		return str;
	}

	/**
	 * Checks if the Item with this id exists in the container.
	 * 
	 * @param id
	 *            - Name of the item.
	 * @return true if the container as an item with that name.
	 */

	public boolean containsItem(java.lang.String id) {
		return Collections.binarySearch(itemContainer, id) >= 0;
	}

	/**
	 * Use an item, removing the item from the inventory if the item can not be
	 * used any more
	 * 
	 * @param item
	 *            - to be used
	 */

	public void useItem(Item item) {
		itemContainer.remove(item);
	}

	/**
	 * Notifica que el inventario ha cambiado, bien por añadir o eliminar items
	 */

	public void requestInventoryChange() {
		java.util.Iterator<InventoryObserver> i = iterator();
		while (i.hasNext())
			i.next().inventoryChange(itemContainer);
	}

	/**
	 * Notifica que se está escaneando el inventario
	 */

	public void requestScanInventory() {
		java.util.Iterator<InventoryObserver> i = iterator();
		while (i.hasNext())
			i.next().inventoryScanned(toString());
	}

	/**
	 * Notifica que se va a escanear la descripción de un Item
	 * 
	 * @param it
	 *            - el item a escanear
	 */

	public void requestScanItem(Item it) {
		java.util.Iterator<InventoryObserver> i = iterator();
		while (i.hasNext())
			i.next().itemScanned(it.toString());
	}

}
