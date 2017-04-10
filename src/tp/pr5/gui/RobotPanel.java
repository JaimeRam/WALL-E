package tp.pr5.gui;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;

/**
 * RobotPanel displays information about the robot and its inventory. More
 * specifically, it contains labels to show the robot fuel and the weight of
 * recycled material and a table that represents the robot inventory. Each row
 * displays information about an item contained in the inventory.
 * 
 * @author Jaime
 * 
 */

public class RobotPanel extends javax.swing.JPanel implements
		RobotEngineObserver, InventoryObserver {

	private static final long serialVersionUID = 1L;
	private JTable tableItems;
	private static final JLabel lblStaticFuel = new JLabel("Fuel:");
	private JLabel lblFuel;
	private static final JLabel lblStaticRecycled = new JLabel("Recycled:");
	private JLabel lblRecycled;
	private DefaultTableModel m_DefaultTableModel; // Tabla de items para vista
	private List<Item> m_itemContainer; // Lista que contiene los Items

	/**
	 * Default constructor
	 */

	public RobotPanel() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Robot info"));

		// Añadimos un panel con los items
		JPanel panelItems = new JPanel();
		add(panelItems, BorderLayout.CENTER);
		panelItems.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();
		panelItems.add(scrollPane, BorderLayout.CENTER);

		tableItems = new JTable();
		m_DefaultTableModel = new DefaultTableModel(new String[][] {},
				new String[] { "Id", "Description" });
		tableItems.setModel(m_DefaultTableModel);
		scrollPane.setViewportView(tableItems);

		// Añadimos un panel con el estado de WALL·E
		JPanel panelEstado = new JPanel();
		add(panelEstado, BorderLayout.NORTH);
		panelEstado.add(lblStaticFuel);
		lblFuel = new JLabel("");
		panelEstado.add(lblFuel);
		panelEstado.add(lblStaticRecycled);
		lblFuel.setText("100");

		lblRecycled = new JLabel("");
		panelEstado.add(lblRecycled);
		lblRecycled.setText("0");
		m_itemContainer = new ArrayList<Item>();
	}

	/**
	 * Devuelve la tabla de Items
	 * 
	 * @return the item table
	 */

	public JTable getTableItems() {
		return tableItems;
	}

	/**
	 * Actualiza el Panel el contenedor de Items
	 * 
	 * @param inventory
	 */

	private void printInventory() {

		int n = m_DefaultTableModel.getRowCount(); // Número de filas

		// Borramos todas las filas
		for (int i = 0; i < n; i++)
			m_DefaultTableModel.removeRow(0);

		String[] sItem = new String[2];

		java.util.Iterator<Item> i = m_itemContainer.iterator();

		// Repintamos la tabla
		while (i.hasNext()) {
			Item it = i.next();
			sItem[0] = it.getId();
			sItem[1] = it.getDescription();
			m_DefaultTableModel.addRow(sItem);
		}

	}

	/**
	 * Notifies that the container has changed
	 * 
	 * @param inventory
	 *            - New inventory
	 */

	@Override
	public void inventoryChange(List<Item> inventory) {
		m_itemContainer.clear(); // Borramos nuestro inventario de la vista
		m_itemContainer.addAll(inventory); // Obtenemos una copia del inventario
		printInventory(); // Mostramos el inventario
	}

	@Override
	public void inventoryScanned(String inventoryDescription) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemScanned(String description) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemEmpty(String itemName) {
		// TODO Auto-generated method stub

	}

	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub

	}

	@Override
	public void engineOff(boolean atShip) {
		// TODO Auto-generated method stub

	}

	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub

	}

	/**
	 * The robot engine informs that the fuel and/or the amount of recycled
	 * material has changed
	 * 
	 * @param fuel
	 *            - Current amount of fuel
	 * 
	 * @param recycledMaterial
	 *            - Current amount of recycled material
	 */

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		lblFuel.setText(Integer.toString(fuel));
		lblRecycled.setText(Integer.toString(recycledMaterial));
	}

	@Override
	public void robotSays(String message) {
	}
}
