/**
 * Paquete que contiene todas las vistas de la aplicación 
 */
package co.edu.udea.constructorDeAFD.vista;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToggleButton;

import co.edu.udea.constructorDeAFD.modelo.ControladorVentana;

/**
 * Jframe que contiene los componenetes graficos de la aplicación
 * 
 * @author alejandro & Martin
 *
 */
public class VentanaPrincipal extends JFrame {

	
	private ControladorVentana ctrlVentana;
	/**
	 * 
	 */
	private JPanel contentPane;
	private JScrollPane JscrollIzquierdo;
	private int Paso;
	/**
	 * Lado izquierdo
	 */
	private JComboBox<String>[][] JcbMatrizTransicionesPorUsuario;
	private JFormattedTextField[] JtfdEstadosPorUsuario;
	private JFormattedTextField[] JtfdSimbolosPorUsuario;
	private JToggleButton[] JtbtnAceptacionesUsuario;
	private JButton JbtnSimplifcar;
	private JButton JbtnNuevaFila;
	private JButton JbtnNuevaColumna;
	/**
	 * Lado derecho
	 */
	private JLabel[] JlblEstadosPorUsuario;
	private JLabel[] JlblSimbolosPorUsuario;
	private JLabel[] JlblAceptacionesPorUsuario;
	private JTable JtableTransicionesPorUsuario;
	private JButton JbtnAnterior;
	private JButton JbtnSIguiente;
	private JButton JbtnSolucionado;
	private JFormattedTextField JtfdHilera;
	private JLabel JlblRespuesta;
	
	/**
	 * 
	 * @param ctrlVentana
	 */
	public VentanaPrincipal(ControladorVentana ctrlVentana){
		super("Constructor de Automatas Finitos Deterministicos");
		this.ctrlVentana = ctrlVentana;
		//Iniciar los atributos basicos del jframe
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		this.setContentPane(contentPane);
		
		//iniciar los atributos basicos del panel principal
		JscrollIzquierdo.setViewportView(contentPane);
		contentPane.setLayout(null);
		
		initLadoIzquierdo();
		initLadoDerecho();
	}

	private void initLadoIzquierdo() {
		// TODO Auto-generated method stub
		
	}

	private void initLadoDerecho() {
		// TODO Auto-generated method stub
		
	}
}
