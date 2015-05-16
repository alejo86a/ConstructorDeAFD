/**
 * 
 */
package co.edu.udea.constructorDeAFD.util;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.StringTokenizer;

import javax.swing.BorderFactory;
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
		ordena(texto);
		if(texto.endsWith(",")){
			caja.setText(texto.substring(0, texto.length()-1));
		}
		caja.setText(texto);
		
		if(sigueEnError){
			caja.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
		}else{
			caja.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
		}
	}

	private void ordena(String texto) {
		// TODO Auto-generated method stub
		
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
