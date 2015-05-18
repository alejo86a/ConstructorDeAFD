/**
 * 
 */
package co.edu.udea.constructorDeAFD.util;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

/**
 * @author alejandro
 *
 */
public class Validar {
	
	private static Validar instancia = new Validar();
	
	private String[] SimbolosValidos;
	
	private Validar(){
		SimbolosValidos = new String[0];
	}

	public static Validar getInstancia() {
		return instancia;
	}
	
	public void setSimbolosValidos(JFormattedTextField[] simbolos){
		SimbolosValidos= new String[simbolos.length];
		for (int i = 0; i < simbolos.length; i++) {
			SimbolosValidos[i] = simbolos[i].getText();
		}
	}

	public void validaTransicionTyped(JFormattedTextField caja,
			KeyEvent e) {
		String texto = caja.getText();
		if(e.getKeyChar()>96 && e.getKeyChar()<123){
			caja.setText(texto+((char) (e.getKeyChar()-32)));
			e.consume();
		}
		if(!((e.getKeyChar()>64 && e.getKeyChar()<91)) && e.getKeyChar()!=','){
			e.consume();
		}
		if(texto.endsWith(",") && e.getKeyChar()==','){
			e.consume();
		}
		if(texto.isEmpty() && e.getKeyChar()==','){
			e.consume();
		}
		
	}

	public void validaTransicionFocus(JFormattedTextField caja,
			FocusEvent e) {
		boolean sigueEnError = false;
		String texto = caja.getText();
		if(texto.endsWith(",")){
			caja.setText(texto.substring(0, texto.length()-1));
		}
		StringTokenizer tokensComa = new StringTokenizer(texto, ",");
		String aux;
		while(tokensComa.hasMoreTokens()){
			aux = tokensComa.nextToken();
			texto = buscaYElimina(texto,aux);
			if(!estadoExite(aux)){
					sigueEnError = true;
			}
		}
		texto = ordena(texto);
		if(texto.endsWith(",")){
			texto = texto.substring(0, texto.length()-1);
		}
		caja.setText(texto);
		
		if(sigueEnError){
			caja.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		}else{
			caja.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
		}
	}

	private String ordena(String texto) {
		StringTokenizer auxTexto = new StringTokenizer(texto,",");
		String[] auxVectexto = new String[auxTexto.countTokens()];	
		for(int i=0;auxTexto.hasMoreTokens();i++){
			auxVectexto[i] = auxTexto.nextToken();
		}
		Arrays.sort(auxVectexto);
		texto = "";
		for (int i = 0; i < auxVectexto.length; i++) {
			texto +=auxVectexto[i]+",";
			System.out.println(auxVectexto[i]);
		}
		return texto;
	}

	private boolean estadoExite(String aux) {
		boolean existe = false;
		for (int i = 0; i < SimbolosValidos.length; i++) {
			if(aux.equals(SimbolosValidos[i])){
				existe = true;
			}
		}
		return existe;
	}

	private String buscaYElimina(String texto, String aux) {
		texto=texto.replaceAll(aux+",","");
		texto=texto.replaceAll(aux,"");
		texto+=aux+",";
		return texto;
	}
	
	

}
