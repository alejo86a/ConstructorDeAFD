/**
 * Paquete que contiene todos los controladores de la aplicación
 */
package co.edu.udea.constructorDeAFD.modelo;

import co.edu.udea.constructorDeAFD.vista.VentanaPrincipal;

/**
 * Clase que contiene el metodo main e instancia los demas controladores
 * 
 * @author alejandro & Martin
 *
 */
public class ControladorPrincipal {
	
	private ControladorVentana ctrlPrincipal;
	private VentanaPrincipal guiPrincipal;

	/**
	 * 
	 */
	public ControladorPrincipal() {
		guiPrincipal = new VentanaPrincipal();
		ctrlPrincipal = new ControladorVentana(guiPrincipal);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ControladorPrincipal ctrlP = new ControladorPrincipal();
	}

}
