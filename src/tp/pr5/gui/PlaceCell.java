package tp.pr5.gui;

import javax.swing.*;
import tp.pr5.PlaceInfo;

/**
 * Represents a Place in the city on the Swing interface. It is a button, which
 * name is the place name.
 * 
 * A PlaceCell needs to store a reference to the place that it represents.
 * However, this place should not be modified by the PlaceCell
 * 
 * When the user clicks on a PlaceCell the CityPanel will show the place
 * description if the Place was previously visited.
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class PlaceCell extends javax.swing.JButton {

	private static final long serialVersionUID = 1L;
	private int fila; // Fila que representa en el mapa
	private int columna; // Columna que representa en el mapa
	private PlaceInfo place; // Información de la Place que representa

	/**
	 * Default constructor
	 * 
	 * @param i
	 *            fila
	 * @param j
	 *            columna
	 * @param b
	 *            botón
	 */

	public PlaceCell(int i, int j) {
		fila = i;
		columna = j;
	}

	/**
	 * Devuelve el JButton de la celda actual
	 * 
	 * @return celda
	 */

	public JButton getCell() {
		return this;
	}

	/**
	 * Devuelve la fila de la celda
	 * 
	 * @return fila
	 */

	public int getFila() {
		return fila;
	}

	/**
	 * Fija la fila de la celda
	 * 
	 * @param row
	 */

	public void setFila(int row) {
		fila = row;
	}

	/**
	 * Deuelve la columna de la celda
	 * 
	 * @return columna
	 */

	public int getColumna() {
		return columna;
	}

	/**
	 * Fija la columna de la celda
	 * 
	 * @param column
	 */

	public void setColumna(int column) {
		columna = column;
	}

	/**
	 * Formatea el texto que representa el botón en la aplicación visual
	 */

	public void cambiarTextoBtn() {
		setText(place.getName());
		setVisible(true);
	}

	/**
	 * Cambia el color del PlaceCell a gris, una vez que ha sido visitado
	 */

	public void cambiarColorVisitado() {
		setBackground(java.awt.Color.gray);
		setBackground(java.awt.Color.gray);
	}

	/**
	 * Cambia el color del PlaceCell a verde, cuando se está visitando en el
	 * momento actual
	 */

	public void cambiarColorNoVisitado() {
		setBackground(java.awt.Color.green);
		setBackground(java.awt.Color.green);
	}

	/**
	 * Devuelve la descripción de una place que corresponde al PlaceCell
	 * 
	 * @return descripción de la place
	 */

	public String getPlaceDescription() {
		return place.getDescription();
	}

	/**
	 * Fija la place que le corresponde a un PlaceCell
	 * 
	 * @param currentPlace
	 *            - una place del mapa
	 */

	public void setPlace(PlaceInfo currentPlace) {
		place = currentPlace;
	}

	/**
	 * Devuelve la PlaceInfo asociada al PlaceCell
	 * 
	 * @return a Place of CityMap
	 */

	public PlaceInfo getPlace() {
		return place;
	}

}
