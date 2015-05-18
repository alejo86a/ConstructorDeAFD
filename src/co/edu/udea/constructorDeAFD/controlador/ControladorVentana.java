/**
 * 
 */
package co.edu.udea.constructorDeAFD.controlador;

import java.util.Vector;

import co.edu.udea.constructorDeAFD.modelo.AutomataFinito;
import co.edu.udea.constructorDeAFD.vista.VentanaPrincipal;

/**
 * @author alejandro & Martin
 *
 */
public class ControladorVentana {

	
	private static ControladorVentana instancia = new ControladorVentana();
	
	
	private VentanaPrincipal guiPrincipal;

	private AutomataFinito aFUsuario;
	private AutomataFinito aFSimplicado;
	private int[][] pasoApaso;

	
	public ControladorVentana(){
		
	}
		
	
	public static ControladorVentana getInstance(){
		return instancia;
	}

	public void simplificar(Vector<String> estados,
			String[][] matrizTransiciones, Vector<String> simbolos,
			Vector<Boolean> aceptaciones) {
		aFUsuario = new AutomataFinito(estados, matrizTransiciones, simbolos,
				aceptaciones);
		aFSimplicado = aFUsuario;
		aFSimplicado.Simplifique();
	
	}

	public Vector<String> getSimbolos() {
		return aFSimplicado.getSimbolos();
	}

	public Vector<String> getAceptaciones() {
		return aFSimplicado.getAceptaciones();
	}

	public String[][] getTransiciones() {
		return aFSimplicado.getTransiciones();
 }

	public Vector<Integer> getPaso(int p) {
		Vector<Integer> fila = null;
		return fila;
	}

	public Vector<String> getEstados() {
		return aFSimplicado.getEstados();
	}
	public void funciona(){
		System.out.println("funciono");
	}
	public String[] getHileras(){
		String[] h =aFSimplicado.generarHilera(); 
		return h;
	}
	
	public Vector validaHilera(String s){
		return aFSimplicado.validarHilera1(s);
	}
}
