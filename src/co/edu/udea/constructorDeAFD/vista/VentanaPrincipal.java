/**
 * Paquete que contiene todas las vistas de la aplicación 
 */
package co.edu.udea.constructorDeAFD.vista;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
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
		this.Paso=0;
		//Iniciar los atributos basicos del jframe
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		
		//iniciar los atributos basicos del panel principal
		contentPane = new JPanel();
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setName("panelP");
		System.out.println(getContentPane().getName());
		
		initLadoIzquierdo();
		initLadoDerecho();
	}

	private void initLadoIzquierdo() {
		JtfdEstadosPorUsuario = new JFormattedTextField[2];
		JtfdEstadosPorUsuario[0] = new JFormattedTextField();
		JtfdEstadosPorUsuario[0].setBounds(20, 20, 40, 20);
		contentPane.add(JtfdEstadosPorUsuario[0]);
		
		
		contentPane.add(new JTextField());
		
		
		JcbMatrizTransicionesPorUsuario = new JComboBox[2][2];
	}

	private void initLadoDerecho() {
		JlblEstadosPorUsuario = new JLabel[2];
		JlblEstadosPorUsuario[0]= new JLabel("Hola");
		JlblEstadosPorUsuario[0].setBounds(100, 100, 40, 20);
		this.getContentPane().add(JlblEstadosPorUsuario[0]);
		System.out.println("hola");
		
	}
}
