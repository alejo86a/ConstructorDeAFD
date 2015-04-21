/**
 * 
 */
package co.edu.udea.constructorDeAFD.modelo;

import java.util.Vector;

/**
 * @author alejandro & Martin
 *
 */
public class AutomataFinito {

	private Vector<String> estados;// su longitud es la cantidad de estados
	private Vector<Vector> transiciones;// su longitud es la cantid de simbolos
	Vector<String> simbolos;// su longitud es la cantid de simbolos
	private Vector<Boolean> aceptaciones;// su longitud es la cantidad de
											// estados
	private int posicion;// su valor maximo es la cantidad de estados

	/**
	 * 
	 * Constructor que recibe como parametros la informacion necesaria para
	 * construir un automata finito. Usamos esta estructura que consta de: Un
	 * vector de estados,donde se encuentra cada estado del AF. Una matriz de
	 * transiciones. Un vector booleano , el cual cada posicion representa si
	 * los estados son de aceptacion o de rechazo. Un vector de simbolos.
	 *
	 * El vector transiciones , es un vector de cabezas en donde se guardan las
	 * columnas de la matrizTransiciones.
	 * 
	 * @param estados
	 * @param matrizTransiciones
	 * @param simbolos
	 * @param aceptaciones
	 */
	public AutomataFinito(Vector<String> estados,
			String[][] matrizTransiciones, Vector<String> simbolos,
			Vector<Boolean> aceptaciones) {
		this.estados = estados;
		this.simbolos = simbolos;
		this.aceptaciones = aceptaciones;
		this.posicion = 0;

		for (int i = 0; i < matrizTransiciones[0].length; i++) {
			Vector<String> aux = null;
			transiciones.add(i, aux);
			for (int j = 0; j < matrizTransiciones.length; j++) {
				aux.add(j, matrizTransiciones[i][j]);
			}
		}
	}

	/**
	 * Simplifica el AF ingresado por el usuario haciendo las operaciones
	 * correspondientes.
	 *
	 */

	public void Simplifique() {
		convierteAAFD();
		eliminarExtranos();
		hallarEquivalentes();
	}

	/**
	 * Metodo implementado en el metodo "Simplifique",su operacion es hallar
	 * estados equivalentes y eliminar estos.
	 */
	private void hallarEquivalentes() {
		// TODO Auto-generated method stub

	}
	
	/**
	 * Metodo implementado en el metodo "Simplifique",su funcion es eliminar los posibles estados 
	 * extra�os que se puedan encontrar en el Af ingresado por el usuario.
	 */

	private void eliminarExtranos() {
		// TODO Auto-generated method stub

	}

	/**
	 * Convierte un automata finito no deterministico en
	 * un automata finito determinisco.
	 */
	private void convierteAAFD() {
		// TODO Auto-generated method stub

	}

	public int[][] validarHilera(String hilera) {
		int[][] pasoApaso = null;

		return pasoApaso;
	}

	public Vector<String> getEstados() {
		return estados;
	}

	public Vector<String> getSimbolos() {
		return simbolos;
	}

	public Vector<String> getAceptaciones() {
		Vector<String> aceptacionesEnCerosYUNos = null;
		return aceptacionesEnCerosYUNos;
	}

	public String[][] getTransiciones() {
		String[][] matrizTransiciones = null;
		return matrizTransiciones;
	}

}
