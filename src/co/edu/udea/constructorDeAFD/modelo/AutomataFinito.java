/**
 * 
 */
package co.edu.udea.constructorDeAFD.modelo;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;
import java.util.StringTokenizer;
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
		//eliminarExtranos();
		imprimirAF();
		hallarEquivalentes();
	}

	/**
	 * Metodo implementado en el metodo "Simplifique",su operacion es hallar
	 * estados equivalentes y eliminar estos.
	 */
	/**
	 * Metodo implementado en el metodo "Simplifique",su operacion es hallar
	 * estados equivalentes y eliminar estos.
	 */
	private void hallarEquivalentes() {
		String[][] p = new String[transiciones.size()][transiciones.size()];
		//primera partición,condificion de compatibilidad
		int ultimoParticionCero =0, ultimoParticionUno=0;
		for (int i = 0; i < estados.size(); i++) {
			if(aceptaciones.elementAt(i)){
				p[1][ultimoParticionUno]=estados.elementAt(i);
				ultimoParticionUno++;
			}else{
				p[0][ultimoParticionCero]=estados.elementAt(i);
				ultimoParticionCero++;
			}
		}
		
		//particiones condición de propagación
		boolean huboParticiones=false;
		while(!huboParticiones){
			huboParticiones=false;
			//recorro filas
			for (int i= 0;  i< p.length; i++) {
				//verifico trasiciones segun cada simbolo
				for (int j = 0; j < simbolos.size(); j++) {
					String[][] auxParticiones = new String[getUltimaPos(p[i])][getUltimaPos(p[i])];
					//reviso transiciones de caja elemento de una partición
					for (int k = 0; k < getUltimaPos(p[i]); k++) {
						//verifico donde insertar dentro de la matriz auxiliar de particiones
						if(getUltimaParticion(auxParticiones)==0){
							auxParticiones[0][0]=p[i][k];
						}
						for (int k2 = 0; k2 < auxParticiones.length; k2++) {
							//if estan en la misma posicin inserta en ultimaPOr+1 sino avanza, si termino y con todos es diferente inserta en ultimaparticion+1
						}
					}
					//fusionar auxparticiones con p, la primera fila de aux particiones la reemplaza con la fila actual de p las demas despues de la ultima
					
					if(getUltimaParticion(auxParticiones)>0){
						huboParticiones = true;
					}
				}
			}
		}
		
		//reemplazo el automata actual por el nuevo: fusiono los estados que esten en las mismas particiones, fusiono sus respectivas transiciones y estados de aceptacion
		

		//imprime matriz de particiones
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p[i].length; j++) {
				System.out.print(p[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * metodo que devuelve la posicion del ultimo elemento en una fila
	 * 
	 * @param array
	 * @return
	 */
	public int getUltimaPos(String[] array){
		int auxPos=0;
		while(array[auxPos]!=null){
			auxPos++;
		}
		return (auxPos<=0)?0:auxPos-1;
	}
	/**
	 * Me retorna en que particion se encuenta un estado que llega por parametro
	 * 
	 * @param p
	 * @param estado
	 * @return
	 */
	public int getParticionDeLaTrancision(String[][] p, String estado){
		int pos=0;
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p[0].length; j++) {
				if(estado.equals(p[i][j])){
					pos=i;
				}
			}
		}
		return pos;
	}
	
	/**
	 * Me retorna cual es la ultima partición
	 * @param p
	 * @return
	 */
	public int getUltimaParticion(String[][] p){
		int pos=0;
		for (int i = 0; i < p.length; i++) {
			if(p[i][0]==null){
				pos=i;
			}
		}
		return pos;
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
		convertinD();

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
//Imprime en consola un AF, se usa con fines de pruebas.
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
//  Genera 5 hileras que son reconocidas por el AF
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
//Busca en un estado si una transicion va  a aceptacion
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
// Predice si un estrado va hacia aceptacion
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
// Su funcion es verificar si el elemento aux esta en el vector h
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
//Verifica si un estado es de aceptacion o no 
	private boolean esAceptacion(int estadoActual) {
		boolean b = false;
		for (int i = 0; i < aceptaciones.size(); i++) {
			if (estadoActual == i) {
				b = aceptaciones.elementAt(i);
			}
		}
		return b;
	}
//Retorna el simbolo 
	private String buscarSimbolo(int x) {
		String a = "";
		for (int i = 0; i < simbolos.size(); i++) {
			if (i == x) {
				a = simbolos.elementAt(i);
			}
		}
		return a;
	}
//se verifica si el estado es de error: Sus transiciones son hacia si mismo y 
	//no es un estado de aceptacion
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
//Retorna la posicion de un estado entrago como String
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
// Valida un string  generando una ruta por la que hace transicion la hilera.
	//Si el ultimo elemento del vector ruta es de aceptacion, el AF acepta la hilera.
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
		rutaS = new String[ruta.size()];
		for (int i = 0; i < ruta.size(); i++) {
			rutaS[i] = (String) ruta.elementAt(i);
		}

		via = via + "Estado Inicial ---->> " + estados.elementAt(0);

		for (int i = 1; i < ruta.size(); i++) {
			via = via + "\n" + "Se ingresa al estado " + "---->> "
					+ ruta.elementAt(i) + " Con el simbolo " + h.charAt(i - 1);

			System.out.println("Se ingresa al estado " + ruta.elementAt(i)
					+ " Con el simbolo " + h.charAt(i - 1));

		}

		int evaluar = buscarEstado(rutaS[rutaS.length - 1]);
		if (esAceptacion(evaluar)) {
			via = via + "\n" + "La hilera  " + h
					+ " es ACEPTADA  Por el automata :D ";

			System.out.println("La hilera  " + h
					+ " es Aceptada Por el automata ");
		} else {
			via = via + "\n" + "La hilera  " + h
					+ "   es RECHAZADA Por el automata :( ";

			System.out.println("La hilera  " + h
					+ "  NO es Aceptada Por el automata ");
		}
		JOptionPane.showMessageDialog(null, via);
		return ruta;
	}
//Busca la posicion en vector de un simbolo
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
//Busca si un estado es Estraño
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
//Convierte un AFND en AFD
	public void convertinD() {
		Vector estadosD = new Vector();
		String[][] n = new String[transiciones.size()][transiciones
				.elementAt(0).size()];
		for (int i = 0; i < transiciones.size(); i++) {
			for (int j = 0; j < transiciones.elementAt(0).size(); j++) {
				n[i][j] = (String) transiciones.elementAt(i).elementAt(j);
			}
		}

		for (int i = 0; i < n.length; i++) {
			for (int j = 0; j < n[0].length; j++) {
				if (n[i][j].length() > 1) {
					String aux = n[i][j];
					aux = ordena(aux);
					aux = borraComas(aux);
                    aux=eliminarR(aux);
                    if(!buscaSiEsta(aux, estadosD)){
                    	estadosD.addElement(aux);
                    }
					

				}

			}

		}
		Vector aux = estadosD;
		Vector aux2 = new Vector();
		
		for (int i = 0; i < estadosD.size(); i++) {
			aux2 = unirEstados((String) estadosD.elementAt(i), n);

			estadosD=agregar(estadosD, aux2);
		}
		
		
       String[][] matrizNoD=new String[estadosD.size()][simbolos.size()];
       matrizNoD = llenarMatrizNoD(matrizNoD,n,estadosD);
		
		Vector<Vector> transicionesNuevas= new Vector<Vector>();
		Vector<String> estadosNuevos = new Vector<String>();
		Vector<Boolean> aceptacionesNuevas = new Vector<Boolean>();
		transicionesNuevas.setSize(simbolos.size());
		estadosNuevos.addElement(estados.elementAt(0));
		for (int i = 0; i < transicionesNuevas.size(); i++) {
			transicionesNuevas.elementAt(i).addElement(n[0][i]);		}
	
		boolean bandera=false;
		while(bandera==true){
			if(faltaAñadirEstado(estadosNuevos,transicionesNuevas)){
				
			}
	
		}	
	}

	private boolean faltaAñadirEstado(Vector<String> estadosNuevos, Vector<Vector> transicionesNuevas) {
		for (int i = 0; i <transicionesNuevas.size() ; i++) {
			for (int j = 0; j < transicionesNuevas.elementAt(0).size(); j++) {
				String aux = (String)transicionesNuevas.elementAt(i).elementAt(j);
			    if (!buscaSiEsta(aux, estadosNuevos)) {
					return true;
				}
				
			}
			
		}
		return false;
	}

	//Llena una matriz con las transiciones respectivas de los No deterministicos
	//Esto tambien incluye dado X caso estados que no sean deterministicos.
	//En la aplicacion esto no afecta el funcionamiento , ni el proposito.
	private String[][] llenarMatrizNoD(String[][] matrizNoD, String[][] n, Vector estadosD) {
		Vector aux = new Vector();
		for (int i = 0; i < matrizNoD.length; i++) {
			String s = (String)estadosD.elementAt(i);
			for (int j = 0; j <  matrizNoD[0].length; j++) {
				matrizNoD[i][j]=unirPorSimbolo(s,n,j);
			}
		}
		
		
		
		
		
		
		return matrizNoD;
	}

	private String unirPorSimbolo(String s, String[][] n, int sim) {
		String aux ="";
		for (int i = 0; i < s.length(); i++) {
			String c1=Character.toString(s.charAt(i));
			int c = buscarEstado(c1);
			aux=aux+ n[c][sim];
			
		}
		aux=borraComas(aux);
		aux=eliminarR(aux);
		return aux;
	}

	//Busca si en un  vector esta el string aux.
	private boolean buscaSiEsta(String aux, Vector estadosD) {
		boolean b = false;
		for (int i = 0; i < estadosD.size(); i++) {
			String s =(String)estadosD.elementAt(i);
			if(s.compareTo(aux)==0){
				return true;
			}
		}
		return b;
	}
//Agraga los elementos de un vector b  a otro vector a, retorna a.
	private Vector agregar(Vector a, Vector b) {
		
		for (int i = 0; i < b.size(); i++) {
			String s =(String)b.elementAt(i);
			if (!buscaSiEsta(s, a)) {
				s=eliminarR(s);
				a.addElement(s);
			}
			
		}
		return a;
	}
//Combina los estados de ser el automata No Deterministico
	private Vector unirEstados(String s, String[][] n) {
		Vector aux = new Vector();

		for (int i = 0; i < simbolos.size(); i++) {
			String s1 = "";
			for (int j = 0; j < s.length(); j++) {
				String c1 = Character.toString(s.charAt(j));
				int a = buscarEstado(c1);
				
					s1 = s1 + n[a][i] + ",";
				
				

			}
			s1 = ordena(s1);

			s1 = borraComas(s1);
			s1=eliminarR(s1);
			if (!buscaSiEsta(s1, aux)) {
				aux.addElement(s1);	
			}
				
			

		}
		return aux;
	}
//Metodo borra las comas para hacer conjunto determinisco
	
	private String borraComas(String texto) {
		texto = texto.replaceAll(",", "");

		return texto;
	}
//Orderna una hilera ingresada
	private String ordena(String texto) {
		StringTokenizer auxTexto = new StringTokenizer(texto, ",");
		String[] auxVectexto = new String[auxTexto.countTokens()];
		for (int i = 0; auxTexto.hasMoreTokens(); i++) {
			auxVectexto[i] = auxTexto.nextToken();
		}
		Arrays.sort(auxVectexto);
		texto = "";
		for (int i = 0; i < auxVectexto.length; i++) {
			texto += auxVectexto[i] + ",";
			System.out.println(auxVectexto[i]);
		}
		return texto;
	}
	
	
//Busca en el vector h si existe el String aux
	private boolean estaEnH(String aux, Vector h) {
		boolean b = false;
		for (int i = 0; i < h.size(); i++) {
			String auxS = (String) h.elementAt(i);

			if (auxS.compareTo(aux) == 0) {
				return true;

			}

		}
		return b;
	}
	//Elimina las palabras repetidas en un String s
	public String eliminarR(String s){
		String aux = "";
		Vector v =new Vector();
		for (int i = 0; i <s.length(); i++) {
			String c = Character.toString(s.charAt(i));
 			v.addElement(c);
		}
		Vector limpio = new Vector();
		while(!v.isEmpty()){
			limpio.addElement(v.firstElement());
			v.removeElement(v.firstElement());
			if(!v.isEmpty()){
				String a = (String)v.firstElement(); 
				String b = (String)limpio.lastElement();
			if(a.compareTo(b)==0){
				
				while(a.compareTo(b)==0){
					
					if (!v.isEmpty()) {
						v.removeElement(v.firstElement());
					}else{
						break;
					}
					if (!v.isEmpty()) {
						 a = (String)v.firstElement(); 
							
					}
					
				}
			}
			}
		}
		
		for (int i = 0; i < limpio.size(); i++) {
			aux=aux + (String)limpio.elementAt(i);
		}
		
		
		
		return aux;
	}
	
	private int buscarEstadoND(String auxEstado,Vector s) {
		int lugar = 0;
		int i = 0;
		while (i < s.size()) {
			if (auxEstado.compareTo((String) s.elementAt(i)) == 0) {
				return i;
			} else {
				i = i + 1;
			}
		}

		return lugar;
	}

}
