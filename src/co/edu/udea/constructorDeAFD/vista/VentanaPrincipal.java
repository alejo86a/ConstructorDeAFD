/**
 * Paquete que contiene todas las vistas de la aplicación 
 */
package co.edu.udea.constructorDeAFD.vista;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Jframe que contiene los componenetes graficos de la aplicación
 * 
 * @author alejandro & Martin
 *
 */
public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;

	/**
	 * 
	 */
	public VentanaPrincipal(){
		super("Constructor de Automatas Finitos Deterministicos");
		//Iniciar los atributos basicos del jframe
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		this.setContentPane(contentPane);
		
		//iniciar los atributos basicos del panel principal
		contentPane.setLayout(null);
	}
}
