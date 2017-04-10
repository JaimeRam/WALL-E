package tp.pr5.gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import tp.pr5.*;

/**
 * This class creates the window for the Swing interface. The MainWindow
 * contains the following components: A menu with the QUIT action An Action
 * panel with several buttons to perform MOVE, TURN, OPERATE, PICK, and DROP
 * actions. Additionally it has a combo box of turn rotations and a text field
 * to write the name of the item that the robot wants to pick from the current
 * place A RobotPanel that displays the robot information (fuel and recycled
 * material) and the robot inventory, which shows a table with item names and
 * descriptions that the robot carries. The user can select the items contained
 * in the inventory in order to DROP or OPERATE an item A CityPanel that
 * represents the city. It shows the places that the robot has visited and an
 * icon that represents the robot heading. The robot heading is updated when the
 * user performs a TURN action. The visible places are updated when the robot
 * performs a MOVE action. Once a place is visited, the user can click on it in
 * order to display the place description (similar to the RADAR command).
 * 
 * @author Jaime Ramos [Grupo_53]
 * 
 */

public class MainWindow extends java.lang.Object implements RobotEngineObserver {

	// Paneles y Ventanas principales
	private JFrame ventanaPrincipal; // Ventana principal del juego
	private JPanel panelPrincipal; // Panel principal de la aplicación
	private JMenuBar menuBar; // Barra del menú superior del juego

	// Otros paneles secundarios
	private JSplitPane panelRobot; // Panel de instrucciones e inventario
	private JPanel m_panelInstructions; // Panel con las instrucciones del robot
	private RobotPanel m_RobotPanel; // Panel de estado e inventario del robot
	private JPanel panelNavegacion; // Panel global de navegación
	private NavigationPanel m_NavigationPanel; // CityMap, WALLE·E y JTextArea
	private InfoPanel m_InfoPanel; // Panel con las notificaciones de WALL·E

	// Botones y otros componentes
	private JButton btnMove;
	private JButton btnQuit;
	private JButton btnTurn;
	private JComboBox<Rotation> comboBoxTurn;
	private JButton btnPick;
	private JButton btnDrop;
	private JButton btnOperate;
	private JTextField textFieldItem;

	// Otras clases
	private GUIController m_GUIController; // Controlador de la aplicación GUI

	/**
	 * Creates the window and the panels using Swing Components. It stores a
	 * reference to the RobotEngine object and provides the panels to the robot
	 * engine in order to communicate the simulation events.
	 * 
	 * @param gameController
	 *            - The RobotEngine that receives the instructions performed by
	 *            the action panel
	 */

	public MainWindow(GUIController gameController) {
		// Inicializamos los paneles principales
		m_GUIController = gameController;
		ventanaPrincipal = new JFrame("WALL·E The garbage collector");
		m_NavigationPanel = new NavigationPanel();
		m_InfoPanel = new InfoPanel();
		m_RobotPanel = new RobotPanel();
		buildPanelInstructions();
		panelRobot = new JSplitPane();

		// Añadimos los observadores
		m_GUIController.addRobotEngineObserver(this);
		m_GUIController.addRobotEngineObserver(m_RobotPanel);
		m_GUIController.addRobotEngineObserver(m_InfoPanel);
		m_GUIController.addNavigationObserver(m_NavigationPanel);
		m_GUIController.addNavigationObserver(m_InfoPanel);
		m_GUIController.addInventoryObserver(m_RobotPanel);
		m_GUIController.addInventoryObserver(m_InfoPanel);

		// Construimos la ventana principal
		buildVentanaPrincipal();
	}

	/**
	 * Trata de centrar el JFrame en la pantalla, fijando una resolución fija
	 */

	private void centrarJFrame() {
		ventanaPrincipal.setSize(1000, 730); // Define la resolución
		ventanaPrincipal.setResizable(false); // Impide cambiar el tamaño
		ventanaPrincipal.setLocationRelativeTo(null); // Centra la ventana
		ventanaPrincipal.setVisible(true); // Muestra la ventana
	}

	/**
	 * Construye la ventana principal de la aplicación
	 */

	private void buildVentanaPrincipal() {
		panelPrincipal = new JPanel(new BorderLayout());

		menuBar = new JMenuBar();
		ventanaPrincipal.setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		// Repartimos los paneles por la ventana principal

		panelPrincipal.add(panelRobot, BorderLayout.CENTER);
		panelRobot.setLeftComponent(m_panelInstructions);
		panelRobot.setRightComponent(m_RobotPanel);
		panelNavegacion = new JPanel(new BorderLayout());
		panelNavegacion.add(m_NavigationPanel, BorderLayout.CENTER);
		panelNavegacion.add(m_InfoPanel, BorderLayout.SOUTH);
		panelPrincipal.add(panelNavegacion, BorderLayout.SOUTH);
		ventanaPrincipal.getContentPane().add(panelPrincipal);

		ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		centrarJFrame();
	}

	/**
	 * Construye el JPanel de istrucciones con sus botones y correspondientes
	 * Listeners
	 */

	private void buildPanelInstructions() {
		GridLayout gl_panelInstructions = new GridLayout(4, 2);
		gl_panelInstructions.setVgap(5); // Fija el ancho y alto de separación
											// entre los elementos
		gl_panelInstructions.setHgap(5);
		m_panelInstructions = new JPanel(gl_panelInstructions);
		m_panelInstructions.setBorder(new TitledBorder("Instructions"));

		// Insertamos botones
		btnTurn = new JButton("TURN");
		btnTurn.addActionListener(new ListenerTurn());
		m_panelInstructions.add(btnTurn);

		comboBoxTurn = new JComboBox<Rotation>();
		comboBoxTurn.addItem(Rotation.LEFT);
		comboBoxTurn.addItem(Rotation.RIGHT);
		m_panelInstructions.add(comboBoxTurn);

		btnPick = new JButton("PICK");
		btnPick.addActionListener(new ListenerPick());
		m_panelInstructions.add(btnPick);

		textFieldItem = new JTextField();
		m_panelInstructions.add(textFieldItem);
		textFieldItem.setColumns(10);

		btnDrop = new JButton("DROP");
		btnDrop.addActionListener(new ListenerDrop());
		m_panelInstructions.add(btnDrop);

		btnOperate = new JButton("USE");
		btnOperate.addActionListener(new ListenerOperate());
		m_panelInstructions.add(btnOperate);

		btnMove = new JButton("MOVE");
		btnMove.addActionListener(new ListenerMove());
		m_panelInstructions.add(btnMove);

		btnQuit = new JButton("QUIT");
		btnQuit.addActionListener(new ListenerQuit());
		m_panelInstructions.add(btnQuit);
	}

	/**
	 * Implementa el ActionListener Move
	 * 
	 * @author Jaime
	 * 
	 */

	class ListenerMove implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			m_GUIController.moveInstruction();
		}
	}

	/**
	 * Implementa el ActionListener Quit
	 * 
	 * @author Jaime
	 * 
	 */

	class ListenerQuit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int option = JOptionPane.showOptionDialog(null,
					"¿Seguro que quieres salir?", "¿Salir?",
					JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					null, null, null);

			if (option == JOptionPane.YES_OPTION)
				m_GUIController.quitInstruction();
		}
	}

	/**
	 * Implementa el ActionListener Turn
	 * 
	 * @author Jaime
	 * 
	 */

	class ListenerTurn implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Rotation rot = (Rotation) comboBoxTurn.getSelectedItem();
			m_GUIController.turnInstruction(rot);
		}
	}

	/**
	 * Implementa el ActionListener Pick
	 * 
	 * @author Jaime
	 * 
	 */

	class ListenerPick implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String sItem = textFieldItem.getText();
			m_GUIController.pickInstruction(sItem);
			textFieldItem.setText(""); // Borramos el texto escrito
		}
	}

	/**
	 * Implementa el ActionListener Drop
	 * 
	 * @author Jaime
	 * 
	 */

	class ListenerDrop implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int fila = m_RobotPanel.getTableItems().getSelectedRow();
			if (fila == -1)
				JOptionPane.showMessageDialog(null,
						"No has seleccionado nada para soltar", "Warning",
						JOptionPane.WARNING_MESSAGE);

			else {
				String sItem = (String) m_RobotPanel.getTableItems()
						.getValueAt(fila, 0);
				m_GUIController.dropInstruction(sItem);
			}
		}
	}

	/**
	 * Implementa el ActionListener Operate
	 * 
	 * @author Jaime
	 * 
	 */

	class ListenerOperate implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int fila = m_RobotPanel.getTableItems().getSelectedRow();
			if (fila == -1)
				JOptionPane.showMessageDialog(null,
						"No has seleccionado nada para usar", "Warning",
						JOptionPane.WARNING_MESSAGE);

			else {
				String sItem = (String) m_RobotPanel.getTableItems()
						.getValueAt(fila, 0);
				m_GUIController.operateInstruction(sItem);
			}
		}
	}

	@Override
	public void raiseError(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void communicationHelp(String help) {
		JOptionPane.showMessageDialog(null, help, "Help",
				JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void engineOff(boolean atShip) {
		if (atShip)
			JOptionPane.showMessageDialog(null,
					"WALL·E says: I am at my space ship. Bye Bye", "THE END",
					JOptionPane.WARNING_MESSAGE);
		else
			JOptionPane
					.showMessageDialog(
							null,
							"WALL·E says: I run out of fuel. I cannot move. Shutting down...",
							"WARNING", JOptionPane.WARNING_MESSAGE);
		System.exit(0);
	}

	@Override
	public void communicationCompleted() {
		JOptionPane.showMessageDialog(null,
				"WALL·E says: I have communication problems. Bye bye",
				"WARNING", JOptionPane.WARNING_MESSAGE);
		System.exit(0);
	}

	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		m_InfoPanel.robotUpdate(fuel, recycledMaterial);
	}

	@Override
	public void robotSays(String message) {
		m_InfoPanel.robotSays(message);
	}

}