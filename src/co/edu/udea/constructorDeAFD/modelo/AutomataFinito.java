/**
 * 
 */
package co.edu.udea.constructorDeAFD.modelo;

import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.omg.CORBA.portable.RemarshalException;

/**
 * @author alejandro & Martin
 * 
 */
public class AutomataFinito {

	private Vector<String> estados = new Vector<String>();// su longitud es la
															// cantidad de
															// estados
	private Vector<Vector> transiciones = new Vector<Vector>();// su longitud es
																// la cantid de
																// simbolos
	private Vector<String> simbolos = new Vector<String>();// su longitud es la
															// cantid de
															// simbolos
	private Vector<Boolean> aceptaciones = new Vector<Boolean>();// su longitud
																	// es la
																	// cantidad
																	// de
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
			Vector<String> aux = new Vector<String>();
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
		imprimirAF();
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
	 * Metodo implementado en el metodo "Simplifique",su funcion es eliminar los
	 * posibles estados extraï¿½os que se puedan encontrar en el Af ingresado
	 * por el usuario.
	 */

	private void eliminarExtranos() {
		Vector aux = new Vector();
		for (int i = 0; i < estados.size(); i++) {
			aux.addElement(estados.elementAt(i));
		}

		boolean[] banderas = new boolean[estados.size()];
		for (int i = 0; i < banderas.length; i++) {
			banderas[i] = false;
		}
		banderas[0] = true;
		int veces = 0;
		while (veces < estados.size()) {
			for (int i = 1; i < aux.size(); i++) {
				if (!buscaSE((String) aux.elementAt(i), banderas)) {
					banderas[i] = false;
				} else {
					banderas[i] = true;
				}

			}
			veces = veces + 1;
		}
		Vector estadosExtraños = new Vector();
		for (int i = 0; i < estados.size(); i++) {
			if (banderas[i] == false) {
				estadosExtraños.addElement(estados.elementAt(i));
			}
		}

		int lugarEstado;
		for (int i = 0; i < estadosExtraños.size(); i++) {
			lugarEstado = buscarEstado((String) estadosExtraños.elementAt(i));
			aceptaciones.removeElementAt(lugarEstado);
			for (int j = 0; j < transiciones.size(); j++) {
				transiciones.elementAt(j).removeElementAt(lugarEstado);
			}
			estados.removeElementAt(lugarEstado);
		}

	}

	/**
	 * Convierte un automata finito no deterministico en un automata finito
	 * determinisco.
	 */
	private void convierteAAFD() {
		// TODO Auto-generated method stub

	}

	public int[][] validarHilera(String hilera) {
		int[][] pasoApaso = null;

		return pasoApaso;
	}

	public Vector<String> getEstados() {
		Vector<String> aux = new Vector();
		for (int i = 0; i < estados.size(); i++) {
			aux.addElement((String) estados.elementAt(i));
		}
		return aux;
	}

	public Vector<String> getSimbolos() {
		return simbolos;
	}

	public Vector<String> getAceptaciones() {
		Vector<String> aceptacionesEnCerosYUNos = new Vector();
		for (int i = 0; i < aceptaciones.size(); i++) {
			if (aceptaciones.elementAt(i) == false) {
				aceptacionesEnCerosYUNos.addElement("0");
			} else {
				aceptacionesEnCerosYUNos.addElement("1");
			}
		}
		return aceptacionesEnCerosYUNos;
	}

	public String[][] getTransiciones() {
		String[][] matrizTransiciones = new String[transiciones.size()][transiciones
				.elementAt(0).size()];
		;

		for (int i = 0; i < transiciones.size(); i++) {
			for (int j = 0; j < transiciones.elementAt(0).size(); j++) {
				matrizTransiciones[i][j] = (String) transiciones.elementAt(i)
						.elementAt(j);
			}
		}
		return matrizTransiciones;
	}

	public void imprimirAF() {

		System.out.print("  ");
		for (int i = 0; i < simbolos.size(); i++) {
			System.out.print(simbolos.elementAt(i) + " ");
		}
		System.out.println();
		for (int i = 0; i < transiciones.size(); i++) {
			System.out.print(estados.elementAt(i) + " ");
			for (int j = 0; j < transiciones.elementAt(0).size(); j++) {
				System.out.print(transiciones.elementAt(i).elementAt(j) + " ");
			}
			if (aceptaciones.elementAt(i)) {
				System.out.println("1");
			} else {
				System.out.println("0");
			}
		}
	}

	public String[] generarHilera() {
		String[] h = new String[5];
		String[][] n = new String[transiciones.size()][transiciones
				.elementAt(0).size()];

		for (int i = 0; i < h.length; i++) {
			h[i] = "";
		}

		for (int i = 0; i < transiciones.size(); i++) {
			for (int j = 0; j < transiciones.elementAt(0).size(); j++) {
				n[i][j] = (String) transiciones.elementAt(i).elementAt(j);
			}
		}

		for (int i = 0; i < h.length; i++) {

			int veces = 0;
			int estadoActual = 0;
			Random y = new Random();

			int x = y.nextInt(simbolos.size());
			if (esError(x)) {
				
			
			while (esError(x)) {
				 x = y.nextInt(simbolos.size());
				
			}
			}
			boolean b = false;
			String aux = "";

			while (b == false) {
				String auxEstado = "";



System.out.println(auxEstado = n[estadoActual][x]);
				auxEstado = n[estadoActual][x];

				int e = buscarEstado(auxEstado);
				if (esError(e)) {
					x = y.nextInt(simbolos.size());
				} else {
					aux = aux + buscarSimbolo(x);
					estadoActual = e;
					x = y.nextInt(simbolos.size());
					
					
					
				}

				if (esAceptacion(estadoActual)) {
					if (estaEnH(aux, h)) {
						b = false;
					} else {

						h[i] = aux;
						b = true;
					}

				} else {
					veces = veces + 1;
					if (veces > 5) {
						boolean b1 = vaAceptacion(estadoActual);
						if (b1 = true) {
							int lacepte = buscarSimboloAcepte(estadoActual);
							h[i] = aux + simbolos.elementAt(lacepte);
							b = true;
						}
					}

				}

			}

		}
		for (int i = 0; i < h.length; i++) {
			System.out.println(h[i]);
		}

		return h;
	}

	private int buscarSimboloAcepte(int estadoActual) {
		int pos = 0;
		int aux = 0;
		for (int i = 0; i < transiciones.size(); i++) {
			aux = buscarEstado((String) transiciones.elementAt(i).elementAt(
					estadoActual));
			if (esAceptacion(aux)) {
				pos = i;
				i = (transiciones.size() - 1);
			}
		}
		return pos;
	}

	private boolean vaAceptacion(int estadoActual) {
		boolean b = false;
		for (int i = 0; i < transiciones.size(); i++) {
			int aux;
			aux = buscarEstado((String) transiciones.elementAt(i).elementAt(
					estadoActual));
			if (esAceptacion(aux)) {
				return true;

			}
		}
		return false;
	}

	private boolean estaEnH(String aux, String[] h) {
		boolean b = false;
		for (int i = 0; i < h.length; i++) {

			if (h[i].compareTo(aux) == 0) {
				return true;
			} else {
				if (h[i] == null) {
					b = false;
				}
			}

		}
		return b;
	}

	private boolean esAceptacion(int estadoActual) {
		boolean b = false;
		for (int i = 0; i < aceptaciones.size(); i++) {
			if (estadoActual == i) {
				b = aceptaciones.elementAt(i);
			}
		}
		return b;
	}

	private String buscarSimbolo(int x) {
		String a = "";
		for (int i = 0; i < simbolos.size(); i++) {
			if (i == x) {
				a = simbolos.elementAt(i);
			}
		}
		return a;
	}

	private boolean esError(int e) {
		
		String simbolo = estados.elementAt(e);
		boolean b = false;
		String[][] n = new String[transiciones.size()][transiciones
			                               				.elementAt(0).size()];
			for (int i = 0; i < transiciones.size(); i++) {
				for (int j = 0; j < transiciones.elementAt(0).size(); j++) {
					n[i][j] = (String) transiciones.elementAt(i).elementAt(j);
				}
			}
		if (aceptaciones.elementAt(e) == true) {
			return false;
		} else {
			for (int i = 0; i < n.length; i++) {
				String aux = n[e][i];
				if ((simbolo.compareTo(aux) == 0)) {
					b = true;
				} else {
					return false;
				}
			}
		}

		return b;
	}

	private int buscarEstado(String auxEstado) {
		int lugar = 0;
		int i = 0;
		while (i < estados.size()) {
			if (auxEstado.compareTo(estados.elementAt(i)) == 0) {
				return i;
			} else {
				i = i + 1;
			}
		}

		return lugar;
	}

	public Vector validarHilera1(String h) {
		String[] rutaS;
		String[][] n = new String[transiciones.size()][transiciones
		                               				.elementAt(0).size()];
		for (int i = 0; i < transiciones.size(); i++) {
			for (int j = 0; j < transiciones.elementAt(0).size(); j++) {
				n[i][j] = (String) transiciones.elementAt(i).elementAt(j);
			}
		}
		Vector ruta = new Vector();
		ruta.addElement(estados.elementAt(0));
		String via = "";
		String estadoActual = estados.elementAt(0);

		for (int i = 0; i < h.length(); i++) {
			int x = buscarSimboloPorLetra(h.charAt(i));
			ruta.addElement(n[buscarEstado(estadoActual)][x]);
			estadoActual = n[buscarEstado(estadoActual)][x];

		}
		rutaS=new String[ruta.size()];
		for (int i = 0; i < ruta.size(); i++) {
			rutaS[i]=(String) ruta.elementAt(i);
		}

		via = via + "Estado Inicial ---->> " + estados.elementAt(0);

		for (int i = 1; i < ruta.size(); i++) {
			via = via + "\n" + "Se ingresa al estado " + "---->> "
					+ ruta.elementAt(i) + " Con el simbolo " + h.charAt(i - 1);

			System.out.println("Se ingresa al estado " + ruta.elementAt(i)
					+ " Con el simbolo " + h.charAt(i - 1));

		}

		int evaluar = buscarEstado( rutaS[rutaS.length-1]);
		if (esAceptacion(evaluar)) {
			via = via + "\n" + "La hilera  " + h
					+ " es ACEPTADA  Por el automata :D ";

			System.out.println("La hilera  " + h
					+ " es Aceptada Por el automata ");
		} else {
			via=via + "\n" +"La hilera  " + h
					+ "   es RECHAZADA Por el automata :( ";
			
			System.out.println("La hilera  " + h
					+ "  NO es Aceptada Por el automata ");
		}
		JOptionPane.showMessageDialog(null, via);
		return ruta;
	}

	public int buscarSimboloPorLetra(char s) {
		int aux = 0;
		String provicional = "" + s;
		for (int i = 0; i < simbolos.size(); i++) {

			if (provicional.compareTo(simbolos.elementAt(i)) == 0) {
				aux = i;
				i = simbolos.size();
			}
		}
		return aux;
	}

	private boolean buscaSE(String a, boolean[] banderas) {
		int aux = buscarEstado(a);
		String[][] n = new String[transiciones.size()][transiciones
				.elementAt(0).size()];
		for (int i = 0; i < transiciones.size(); i++) {
			for (int j = 0; j < transiciones.elementAt(0).size(); j++) {
				n[i][j] = (String) transiciones.elementAt(i).elementAt(j);
			}
		}

		for (int i = 0; i < n.length; i++) {
			if (aux != i && banderas[i] == true) {
				for (int j = 0; j < n[0].length; j++) {
					if (n[i][j].compareTo(a) == 0) {
						return true;
					}
				}
			}

		}

		return false;
	}

}
