/**
 * Paquete que contiene todas las vistas de la aplicación 
 */
package co.edu.udea.constructorDeAFD.vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

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
	public VentanaPrincipal(ControladorVentana ctrlVentana) {
		super("Constructor de Automatas Finitos Deterministicos");
		this.ctrlVentana = ctrlVentana;
		this.Paso = 0;
		// Iniciar los atributos basicos del jframe;
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, java.awt.GraphicsEnvironment
				.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,
				java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getMaximumWindowBounds().height);

		// iniciar los atributos basicos del panel principal
		contentPane = new JPanel();
		this.setContentPane(contentPane);
		contentPane.setLayout(null);

		initLadoIzquierdo();
		initLadoDerecho();
		this.setVisible(true);
	}

	private void initLadoIzquierdo() {
		JtfdEstadosPorUsuario = new JFormattedTextField[2];
		JtfdEstadosPorUsuario[0] = new JFormattedTextField();
		JtfdEstadosPorUsuario[0].setBounds(20, 20, 40, 20);
		contentPane.add(JtfdEstadosPorUsuario[0]);
		JtfdEstadosPorUsuario[1] = new JFormattedTextField();
		JtfdEstadosPorUsuario[1].setBounds(20, 40, 40, 20);
		contentPane.add(JtfdEstadosPorUsuario[1]);
		agregarEventoEstadosUsuario();

		JtfdSimbolosPorUsuario = new JFormattedTextField[2];
		JtfdSimbolosPorUsuario[0] = new JFormattedTextField();
		JtfdSimbolosPorUsuario[0].setBounds(60, 0, 60, 20);
		contentPane.add(JtfdSimbolosPorUsuario[0]);
		JtfdSimbolosPorUsuario[1] = new JFormattedTextField();
		JtfdSimbolosPorUsuario[1].setBounds(120, 0, 60, 20);
		contentPane.add(JtfdSimbolosPorUsuario[1]);

		JcbMatrizTransicionesPorUsuario = new JComboBox[2][2];
		int x = 60, y = 20;
		for (int i = 0; i < JcbMatrizTransicionesPorUsuario.length; i++) {
			for (int j = 0; j < JcbMatrizTransicionesPorUsuario[0].length; j++) {
				JcbMatrizTransicionesPorUsuario[i][j] = new JComboBox();
				JcbMatrizTransicionesPorUsuario[i][j].setBounds(x, y, 60, 20);
				contentPane.add(JcbMatrizTransicionesPorUsuario[i][j]);
				x += 60;
			}
			x = 60;
			y = y + 20;
		}
		llenarOpcionesMatrizTransiciones();

		JtbtnAceptacionesUsuario = new JToggleButton[2];
		JtbtnAceptacionesUsuario[0] = new JToggleButton();
		JtbtnAceptacionesUsuario[0].setBounds(180, 20, 60, 20);
		JtbtnAceptacionesUsuario[0].setText("0");
		contentPane.add(JtbtnAceptacionesUsuario[0]);
		JtbtnAceptacionesUsuario[1] = new JToggleButton();
		JtbtnAceptacionesUsuario[1].setBounds(180, 40, 60, 20);
		JtbtnAceptacionesUsuario[1].setText("0");
		contentPane.add(JtbtnAceptacionesUsuario[1]);
		agregrEventoAlVectorAceptaciones();

		JbtnNuevaFila = new JButton();
		JbtnNuevaFila.setBounds(40, 120, 80, 20);
		JbtnNuevaFila.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				agregarFila();
			}
		});
		JbtnNuevaFila.setText("+ Fila");
		contentPane.add(JbtnNuevaFila);
		JbtnNuevaColumna = new JButton();
		JbtnNuevaColumna.setBounds(140, 120, 80, 20);
		JbtnNuevaColumna.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				agregarColumna();
			}
		});
		JbtnNuevaColumna.setText("+ Columna");
		contentPane.add(JbtnNuevaColumna);
		JbtnSimplifcar = new JButton();
		JbtnSimplifcar.setBounds(80, 160, 80, 20);
		JbtnSimplifcar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ObtenerAFD();
			}

		});
		JbtnSimplifcar.setText("Simplificar");
		contentPane.add(JbtnSimplifcar);
	}

	private void initLadoDerecho() {
		JlblEstadosPorUsuario = new JLabel[2];
		JlblEstadosPorUsuario[0] = new JLabel("Hola");
		JlblEstadosPorUsuario[0].setBounds(100, 100, 40, 20);
		// this.getContentPane().add(JlblEstadosPorUsuario[0]);

	}

	private void agregrEventoAlVectorAceptaciones() {
		for (int i = 0; i < JtbtnAceptacionesUsuario.length; i++) {
			final int p = i;
			JtbtnAceptacionesUsuario[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					if (JtbtnAceptacionesUsuario[p].isSelected()) {
						JtbtnAceptacionesUsuario[p].setText("1");
					} else {
						JtbtnAceptacionesUsuario[p].setText("0");
					}
				}
			});
		}
	}

	private void llenarOpcionesMatrizTransiciones() {
		String[] auxCombinaciones = generarCombinaciones();
		for (int i = 0; i < JcbMatrizTransicionesPorUsuario.length; i++) {
			for (int j = 0; j < JcbMatrizTransicionesPorUsuario[0].length; j++) {
				JcbMatrizTransicionesPorUsuario[i][j].removeAllItems();
				for (int k = 0; k < auxCombinaciones.length; k++) {
					JcbMatrizTransicionesPorUsuario[i][j]
							.addItem(auxCombinaciones[k]);

				}
			}
		}
	}

	private String[] generarCombinaciones() {
		// metodo martin
		return null;
	}

	private void agregarEventoEstadosUsuario() {
		for (int i = 0; i < JtfdEstadosPorUsuario.length; i++) {
			JtfdEstadosPorUsuario[i].addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
					llenarOpcionesMatrizTransiciones();

				}

				@Override
				public void keyPressed(KeyEvent e) {
				}
			});
		}
	}

	private void agregarFila() {
		agregarEstadoPorUsuario();
		agregarFilaDeTranscionesPorUsuario();
		agregarAceptacionPorUsuario();
		moverBotonesDeAgregar();

	}

	private void agregarAceptacionPorUsuario() {
		JToggleButton[] aux = new JToggleButton[JtbtnAceptacionesUsuario.length];
		aux = JtbtnAceptacionesUsuario;
		JtbtnAceptacionesUsuario = new JToggleButton[JtbtnAceptacionesUsuario.length + 1];
		for (int i = 0; i < aux.length; i++) {
			JtbtnAceptacionesUsuario[i] = aux[i];
		}
		JtbtnAceptacionesUsuario[JtbtnAceptacionesUsuario.length - 1] = new JToggleButton();
		JtbtnAceptacionesUsuario[JtbtnAceptacionesUsuario.length - 1]
				.setBounds(
						JtbtnAceptacionesUsuario[JtbtnAceptacionesUsuario.length - 2]
								.getX(),
						JtbtnAceptacionesUsuario[JtbtnAceptacionesUsuario.length - 2]
								.getY()
								+ JtbtnAceptacionesUsuario[JtbtnAceptacionesUsuario.length - 2]
										.getHeight(),
						JtbtnAceptacionesUsuario[JtbtnAceptacionesUsuario.length - 2]
								.getWidth(),
						JtbtnAceptacionesUsuario[JtbtnAceptacionesUsuario.length - 2]
								.getHeight());
		JtbtnAceptacionesUsuario[JtbtnAceptacionesUsuario.length - 1]
				.setText("0");
		contentPane
				.add(JtbtnAceptacionesUsuario[JtbtnAceptacionesUsuario.length - 1]);
		agregrEventoAlVectorAceptaciones();

		contentPane.repaint();

	}

	private void agregarFilaDeTranscionesPorUsuario() {
		JComboBox<String>[][] aux = new JComboBox[JcbMatrizTransicionesPorUsuario.length][JcbMatrizTransicionesPorUsuario[0].length];
		aux = JcbMatrizTransicionesPorUsuario;
		JcbMatrizTransicionesPorUsuario = new JComboBox[JcbMatrizTransicionesPorUsuario.length + 1][JcbMatrizTransicionesPorUsuario[0].length];
		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux[0].length; j++) {
				JcbMatrizTransicionesPorUsuario[i][j] = aux[i][j];
			}
		}
		for (int i = 0; i < JcbMatrizTransicionesPorUsuario[0].length; i++) {
			JcbMatrizTransicionesPorUsuario[JcbMatrizTransicionesPorUsuario.length - 1][i] = new JComboBox();
			JcbMatrizTransicionesPorUsuario[JcbMatrizTransicionesPorUsuario.length - 1][i]
					.setBounds(
							JcbMatrizTransicionesPorUsuario[JcbMatrizTransicionesPorUsuario.length - 2][i]
									.getX(),
							JcbMatrizTransicionesPorUsuario[JcbMatrizTransicionesPorUsuario.length - 2][i]
									.getY()
									+ JcbMatrizTransicionesPorUsuario[JcbMatrizTransicionesPorUsuario.length - 2][i]
											.getHeight(),
							JcbMatrizTransicionesPorUsuario[JcbMatrizTransicionesPorUsuario.length - 2][i]
									.getWidth(),
							JcbMatrizTransicionesPorUsuario[JcbMatrizTransicionesPorUsuario.length - 2][i]
									.getHeight());
			contentPane
					.add(JcbMatrizTransicionesPorUsuario[JcbMatrizTransicionesPorUsuario.length - 1][i]);
		}
		llenarOpcionesMatrizTransiciones();
		contentPane.repaint();
	}

	private void agregarEstadoPorUsuario() {
		JFormattedTextField[] aux = new JFormattedTextField[JtfdEstadosPorUsuario.length];
		aux = JtfdEstadosPorUsuario;
		JtfdEstadosPorUsuario = new JFormattedTextField[JtfdEstadosPorUsuario.length + 1];
		for (int i = 0; i < aux.length; i++) {
			JtfdEstadosPorUsuario[i] = aux[i];
		}
		JtfdEstadosPorUsuario[JtfdEstadosPorUsuario.length - 1] = new JFormattedTextField();
		JtfdEstadosPorUsuario[JtfdEstadosPorUsuario.length - 1]
				.setBounds(
						JtfdEstadosPorUsuario[JtfdEstadosPorUsuario.length - 2]
								.getX(),
						JtfdEstadosPorUsuario[JtfdEstadosPorUsuario.length - 2]
								.getY()
								+ JtfdEstadosPorUsuario[JtfdEstadosPorUsuario.length - 2]
										.getHeight(),
						JtfdEstadosPorUsuario[JtfdEstadosPorUsuario.length - 2]
								.getWidth(),
						JtfdEstadosPorUsuario[JtfdEstadosPorUsuario.length - 2]
								.getHeight());
		contentPane
				.add(JtfdEstadosPorUsuario[JtfdEstadosPorUsuario.length - 1]);
		agregarEventoEstadosUsuario();
		contentPane.repaint();
	}

	private void moverBotonesDeAgregar() {
		JbtnNuevaFila
				.setBounds(
						JbtnNuevaFila.getX(),
						JbtnNuevaFila.getY()
								+ JtfdEstadosPorUsuario[JtfdEstadosPorUsuario.length - 2]
										.getHeight(), JbtnNuevaFila.getWidth(),
						JbtnNuevaFila.getHeight());
		JbtnNuevaColumna
				.setBounds(
						JbtnNuevaColumna.getX(),
						JbtnNuevaColumna.getY()
								+ JtfdEstadosPorUsuario[JtfdEstadosPorUsuario.length - 2]
										.getHeight(),
						JbtnNuevaColumna.getWidth(),
						JbtnNuevaColumna.getHeight());
		JbtnSimplifcar
				.setBounds(
						JbtnSimplifcar.getX(),
						JbtnSimplifcar.getY()
								+ JtfdEstadosPorUsuario[JtfdEstadosPorUsuario.length - 2]
										.getHeight(),
						JbtnSimplifcar.getWidth(), JbtnSIguiente.getHeight());

	}

	private void agregarColumna() {
		agregarSimboloPorUsuario();
		agregarColumnaDeTransicionesDeUsuario();
		moverArrayDeAeptaciones();
	}

	private void moverArrayDeAeptaciones() {
		for (int i = 0; i < JtbtnAceptacionesUsuario.length; i++) {
			JtbtnAceptacionesUsuario[i]
					.setBounds(
							JtbtnAceptacionesUsuario[0].getX()
									+ JcbMatrizTransicionesPorUsuario[JcbMatrizTransicionesPorUsuario.length - 1][i]
											.getWidth(),
							JtbtnAceptacionesUsuario[0].getY(),
							JtbtnAceptacionesUsuario[0].getWidth(),
							JtbtnAceptacionesUsuario[0].getHeight());
		}
		contentPane.repaint();
	}

	private void agregarColumnaDeTransicionesDeUsuario() {
		JComboBox<String>[][] aux = new JComboBox[JcbMatrizTransicionesPorUsuario.length][JcbMatrizTransicionesPorUsuario[0].length];
		aux = JcbMatrizTransicionesPorUsuario;
		JcbMatrizTransicionesPorUsuario = new JComboBox[JcbMatrizTransicionesPorUsuario.length][JcbMatrizTransicionesPorUsuario[0].length + 1];
		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux[0].length; j++) {
				JcbMatrizTransicionesPorUsuario[i][j] = aux[i][j];
			}
		}
		for (int i = 0; i < JcbMatrizTransicionesPorUsuario.length; i++) {
			JcbMatrizTransicionesPorUsuario[i][JcbMatrizTransicionesPorUsuario.length - 1] = new JComboBox();
			JcbMatrizTransicionesPorUsuario[i][JcbMatrizTransicionesPorUsuario.length - 1]
					.setBounds(
							JcbMatrizTransicionesPorUsuario[i][JcbMatrizTransicionesPorUsuario.length - 2]
									.getX(),
							JcbMatrizTransicionesPorUsuario[i][JcbMatrizTransicionesPorUsuario.length - 2]
									.getY()
									+ JcbMatrizTransicionesPorUsuario[i][JcbMatrizTransicionesPorUsuario.length - 2]
											.getHeight(),
							JcbMatrizTransicionesPorUsuario[i][JcbMatrizTransicionesPorUsuario.length - 2]
									.getWidth(),
							JcbMatrizTransicionesPorUsuario[i][JcbMatrizTransicionesPorUsuario.length - 2]
									.getHeight());
			contentPane
					.add(JcbMatrizTransicionesPorUsuario[i][JcbMatrizTransicionesPorUsuario.length - 1]);
		}
		llenarOpcionesMatrizTransiciones();
		contentPane.repaint();
	}

	private void agregarSimboloPorUsuario() {
		JFormattedTextField[] aux = new JFormattedTextField[JtfdSimbolosPorUsuario.length];
		aux = JtfdSimbolosPorUsuario;
		JtfdSimbolosPorUsuario = new JFormattedTextField[JtfdSimbolosPorUsuario.length + 1];
		for (int i = 0; i < aux.length; i++) {
			JtfdSimbolosPorUsuario[i] = aux[i];
		}
		JtfdSimbolosPorUsuario[JtfdSimbolosPorUsuario.length - 1] = new JFormattedTextField();
		JtfdSimbolosPorUsuario[JtfdSimbolosPorUsuario.length - 1]
				.setBounds(
						JtfdSimbolosPorUsuario[JtfdSimbolosPorUsuario.length - 2]
								.getX()
								+ JtfdSimbolosPorUsuario[JtfdSimbolosPorUsuario.length - 2]
										.getWidth(),
						JtfdSimbolosPorUsuario[JtfdSimbolosPorUsuario.length - 2]
								.getY(),
						JtfdSimbolosPorUsuario[JtfdSimbolosPorUsuario.length - 2]
								.getWidth(),
						JtfdSimbolosPorUsuario[JtfdSimbolosPorUsuario.length - 2]
								.getHeight());
		contentPane
				.add(JtfdSimbolosPorUsuario[JtfdSimbolosPorUsuario.length - 1]);
		contentPane.repaint();
	}

	private void ObtenerAFD() {
		Vector<Boolean> aceptaciones = null;
		for (int i = 0; i < JtbtnAceptacionesUsuario.length; i++) {
			if(JtbtnAceptacionesUsuario[i].getText().equals("0")){
				aceptaciones.add(false);
			}else{
				aceptaciones.add(true);
			}
		}
		Vector<String> estados = null;
		Vector<String> simbolos = null;
		String[][] matrizTransiciones = null;
		ctrlVentana.simplificar(estados, matrizTransiciones, simbolos, aceptaciones);

	}
}
