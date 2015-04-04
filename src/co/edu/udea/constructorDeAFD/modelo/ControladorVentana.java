/**
 * 
 */
package co.edu.udea.constructorDeAFD.modelo;

import java.util.Vector;

import co.edu.udea.constructorDeAFD.controlador.AutomataFinito;
import co.edu.udea.constructorDeAFD.vista.VentanaPrincipal;

/**
 * @author alejandro & Martin
 *
 */
public class ControladorVentana {

	private VentanaPrincipal guiPrincipal;
	
	private AutomataFinito aFUsuario;
	private AutomataFinito aFSimplicado;
	private int[][] pasoApaso;
	
	/**
	 * Recibe la instancia unica de la ventana principal
	 * @param guiPrincipal
	 */
	public ControladorVentana(VentanaPrincipal guiPrincipal) {
		this.guiPrincipal = guiPrincipal;
	}
	
	public void simplificar(Vector<String> estados,String[][] matrizTransiciones,Vector<String> simbolos,
			Vector<Boolean> aceptaciones){
		aFUsuario = new AutomataFinito(estados, matrizTransiciones, simbolos, aceptaciones);
		aFSimplicado = aFUsuario;
		aFSimplicado.Simplifique();
	}
	
	public Vector<String> getSimbolos(){
		return aFSimplicado.getAceptaciones();
	}
	
	public Vector<String> getAceptaciones(){
		return aFSimplicado.getAceptaciones();
	}
	
	public String[][] getTransiciones(){
		return aFSimplicado.getTransiciones();
	}
	
	public Vector<Integer> getPaso(int p){
		Vector<Integer> fila=null;
		return fila;
	}
}
