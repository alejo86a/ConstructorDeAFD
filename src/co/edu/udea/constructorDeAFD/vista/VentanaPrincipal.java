/**
 * Paquete que contiene todas las vistas de la aplicaci�n 
 */
package co.edu.udea.constructorDeAFD.vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.table.DefaultTableModel;

import co.edu.udea.constructorDeAFD.controlador.ControladorVentana;
import co.edu.udea.constructorDeAFD.util.Validar;

/**
 * Jframe que contiene los componenetes graficos de la aplicaci�n
 * 
 * @author alejandro & Martin
 *
 */
public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JPanel secondPane;
	private int paso;
	/**
	 * Lado izquierdo
	 */
	private JFormattedTextField[][] JftdMatrizTransicionesPorUsuario;
	private JFormattedTextField[] JtfdEstadosPorUsuario;
	private JFormattedTextField[] JtfdSimbolosPorUsuario;
	private JToggleButton[] JtbtnAceptacionesUsuario;
	private JButton JbtnSimplifcar;
	private JButton JbtnReiniciar;
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
	 * Contantes
	 */
	private int XINICIAL;
	private int YINICIAL;
	private int WIDTHELEMENTO;
	private int WIDTHCOMBO;
	private int HEIGHTELEMENTO;
	private Font FUENTE = new Font("Arial", 0, 36);

	/**
	 * 
	 * @param ctrlVentana
	 */
	public VentanaPrincipal() {
		super("Constructor de Automatas Finitos Deterministicos");
		// ctrlVentana.funciona();
		this.paso = 0;
		// Iniciar los atributos basicos del jframe;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(0, 0, java.awt.GraphicsEnvironment
				.getLocalGraphicsEnvironment().getMaximumWindowBounds().width,
				java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment()
						.getMaximumWindowBounds().height);

		// iniciar los atributos basicos del panel principal
		contentPane = new JPanel();
		// this.setContentPane(contentPane);
		JScrollPane scroller = new JScrollPane(contentPane);
		this.getContentPane().add(scroller, BorderLayout.CENTER);
		this.getContentPane().setBounds(this.getContentPane().getX(),
				this.getContentPane().getY(),
				this.getContentPane().getWidth() - 10,
				this.getContentPane().getHeight() - 10);
		contentPane.setLayout(null);

		iniciarConstantesRelativas();
		initLadoIzquierdo();

		this.setVisible(true);

	}

	private void iniciarConstantesRelativas() {
		XINICIAL = (int) Math.floor(1.389 * this.getWidth() / 100);
		YINICIAL = (int) Math.floor(9.142857143 * this.getHeight() / 100);
		WIDTHELEMENTO = (int) Math.floor(5.208333333 * this.getWidth() / 100);
		WIDTHCOMBO = (int) Math.floor(6.597222222 * this.getWidth() / 100);
		HEIGHTELEMENTO = (int) Math.floor(6.285714286 * this.getHeight() / 100);
	}

	private void initLadoIzquierdo() {
		// Agrego primeros textfield de estados
		JtfdEstadosPorUsuario = new JFormattedTextField[2];
		JtfdEstadosPorUsuario[0] = new JFormattedTextField();
		JtfdEstadosPorUsuario[0].setBounds(XINICIAL, YINICIAL + HEIGHTELEMENTO,
				WIDTHELEMENTO, HEIGHTELEMENTO);
		JtfdEstadosPorUsuario[0].setFont(FUENTE);
		contentPane.add(JtfdEstadosPorUsuario[0]);

		JtfdEstadosPorUsuario[1] = new JFormattedTextField();
		JtfdEstadosPorUsuario[1].setBounds(
				XINICIAL,
				JtfdEstadosPorUsuario[0].getY()
						+ JtfdEstadosPorUsuario[0].getHeight(), WIDTHELEMENTO,
				HEIGHTELEMENTO);
		JtfdEstadosPorUsuario[1].setFont(FUENTE);
		contentPane.add(JtfdEstadosPorUsuario[1]);

		agregarEventoEstadosUsuario();
		// Agrego primeros textfield de simbolos
		JtfdSimbolosPorUsuario = new JFormattedTextField[2];
		JtfdSimbolosPorUsuario[0] = new JFormattedTextField();
		JtfdSimbolosPorUsuario[0].setBounds(XINICIAL + WIDTHELEMENTO, YINICIAL,
				WIDTHCOMBO, HEIGHTELEMENTO);
		JtfdSimbolosPorUsuario[0].setFont(FUENTE);
		contentPane.add(JtfdSimbolosPorUsuario[0]);

		JtfdSimbolosPorUsuario[1] = new JFormattedTextField();
		JtfdSimbolosPorUsuario[1].setBounds(JtfdSimbolosPorUsuario[0].getX()
				+ WIDTHCOMBO, YINICIAL, WIDTHCOMBO, HEIGHTELEMENTO);
		JtfdSimbolosPorUsuario[1].setFont(FUENTE);
		contentPane.add(JtfdSimbolosPorUsuario[1]);
		// Agrego primeros combobox de transiciones
		JftdMatrizTransicionesPorUsuario = new JFormattedTextField[2][2];
		int x = XINICIAL + WIDTHELEMENTO, y = YINICIAL + HEIGHTELEMENTO;
		for (int i = 0; i < JftdMatrizTransicionesPorUsuario.length; i++) {
			for (int j = 0; j < JftdMatrizTransicionesPorUsuario[0].length; j++) {
				JftdMatrizTransicionesPorUsuario[i][j] = new JFormattedTextField();
				JftdMatrizTransicionesPorUsuario[i][j].setBounds(x, y,
						WIDTHCOMBO, HEIGHTELEMENTO);
				JftdMatrizTransicionesPorUsuario[i][j].setFont(FUENTE);
				contentPane.add(JftdMatrizTransicionesPorUsuario[i][j]);
				x += WIDTHCOMBO;
			}
			x = XINICIAL + WIDTHELEMENTO;
			y = y + HEIGHTELEMENTO;
		}

		agregarEventoMatrizTransiciones();
		// Agrego primeros toggleButton de aceptaciones
		JtbtnAceptacionesUsuario = new JToggleButton[2];
		JtbtnAceptacionesUsuario[0] = new JToggleButton();
		JtbtnAceptacionesUsuario[0].setBounds(XINICIAL + WIDTHELEMENTO
				+ WIDTHCOMBO * 2, YINICIAL + HEIGHTELEMENTO, WIDTHCOMBO,
				HEIGHTELEMENTO);
		JtbtnAceptacionesUsuario[0].setText("0");
		JtbtnAceptacionesUsuario[0].setFont(FUENTE);
		contentPane.add(JtbtnAceptacionesUsuario[0]);

		JtbtnAceptacionesUsuario[1] = new JToggleButton();
		JtbtnAceptacionesUsuario[1].setBounds(XINICIAL + WIDTHELEMENTO
				+ WIDTHCOMBO * 2, JtbtnAceptacionesUsuario[0].getY()
				+ HEIGHTELEMENTO, WIDTHCOMBO, HEIGHTELEMENTO);
		JtbtnAceptacionesUsuario[1].setText("0");
		JtbtnAceptacionesUsuario[1].setFont(FUENTE);
		contentPane.add(JtbtnAceptacionesUsuario[1]);

		agregrEventoAlVectorAceptaciones();
		// Agrego boton de anadir fila
		JbtnNuevaFila = new JButton();
		JbtnNuevaFila.setBounds(40, 30, 120, 20);
		JbtnNuevaFila.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				agregarFila();
			}
		});
		JbtnNuevaFila.setText("+ Fila");
		contentPane.add(JbtnNuevaFila);

		JbtnNuevaColumna = new JButton();
		JbtnNuevaColumna.setBounds(180, 30, 120, 20);
		JbtnNuevaColumna.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				agregarColumna();
			}
		});
		JbtnNuevaColumna.setText("+ Columna");
		contentPane.add(JbtnNuevaColumna);

		JbtnSimplifcar = new JButton();
		JbtnSimplifcar.setBounds(320, 30, 120, 20);
		JbtnSimplifcar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				obtenerAFD();
			}

		});
		JbtnSimplifcar.setText("Simplificar");
		contentPane.add(JbtnSimplifcar);

		JbtnReiniciar = new JButton();
		JbtnReiniciar.setBounds(460, 30, 120, 20);
		JbtnReiniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				reiniciarAF();
			}
		});
		JbtnReiniciar.setText("Reiniciar AF");
		contentPane.add(JbtnReiniciar);
	}

	private void agregarEventoMatrizTransiciones() {
		for (int i = 0; i < JftdMatrizTransicionesPorUsuario.length; i++) {
			for (int j = 0; j < JftdMatrizTransicionesPorUsuario[i].length; j++) {
				final int m=i,n=j;
				JftdMatrizTransicionesPorUsuario[i][j].addKeyListener(new KeyListener() {
									
					@Override
					public void keyTyped(KeyEvent e) {
						Validar.getInstancia().setSimbolosValidos(JtfdSimbolosPorUsuario);
						Validar.getInstancia().validaTransicionTyped(JftdMatrizTransicionesPorUsuario[m][n],e);						
					}
					
					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void keyPressed(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				JftdMatrizTransicionesPorUsuario[i][j].addFocusListener(new FocusListener() {
					
					@Override
					public void focusLost(FocusEvent e) {
						Validar.getInstancia().setSimbolosValidos(JtfdSimbolosPorUsuario);
						Validar.getInstancia().validaTransicionFocus(JftdMatrizTransicionesPorUsuario[m][n], e);
						
					}
					
					@Override
					public void focusGained(FocusEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			}
		}
		
	}

	private void initLadoDerecho() {

	}

	private void llenarLadoDerecho() {
		Vector<String> auxEstados = ControladorVentana.getInstance()
				.getEstados();
		JlblEstadosPorUsuario[0] = new JLabel();
		JlblEstadosPorUsuario[0].setText(auxEstados.elementAt(0));
		JlblEstadosPorUsuario[0].setBounds(contentPane.getWidth() - 400, 20,
				40, 20);
		contentPane.add(JlblEstadosPorUsuario[0]);
		for (int i = 1; i < auxEstados.size(); i++) {
			JlblEstadosPorUsuario[i] = new JLabel();
			JlblEstadosPorUsuario[i].setText(auxEstados.elementAt(i));
			JlblEstadosPorUsuario[i].setBounds(
					JlblEstadosPorUsuario[i - 1].getX(),
					JlblEstadosPorUsuario[i - 1].getY()
							+ JlblEstadosPorUsuario[i - 1].getHeight(),
					JlblEstadosPorUsuario[i - 1].getWidth(),
					JlblEstadosPorUsuario[i - 1].getHeight());
			contentPane.add(JlblEstadosPorUsuario[i]);
		}

		Vector<String> auxSimbolos = ControladorVentana.getInstance()
				.getSimbolos();
		JlblSimbolosPorUsuario[0] = new JLabel();
		JlblSimbolosPorUsuario[0].setText(auxSimbolos.elementAt(0));
		JlblSimbolosPorUsuario[0].setBounds(
				JlblEstadosPorUsuario[0].getX()
						+ JlblEstadosPorUsuario[0].getWidth(),
				JlblEstadosPorUsuario[0].getY()
						- JlblEstadosPorUsuario[0].getHeight(),
				JlblEstadosPorUsuario[0].getWidth(),
				JlblEstadosPorUsuario[0].getHeight());
		contentPane.add(JlblSimbolosPorUsuario[0]);
		for (int i = 0; i < auxSimbolos.size(); i++) {
			JlblSimbolosPorUsuario[i] = new JLabel();
			JlblSimbolosPorUsuario[i].setText(auxSimbolos.elementAt(i));
			JlblSimbolosPorUsuario[i].setBounds(
					JlblSimbolosPorUsuario[i - 1].getX()
							+ JlblSimbolosPorUsuario[i - 1].getWidth(),
					JlblSimbolosPorUsuario[i - 1].getY(),
					JlblSimbolosPorUsuario[i - 1].getWidth(),
					JlblSimbolosPorUsuario[i - 1].getHeight());
			contentPane.add(JlblSimbolosPorUsuario[i]);
		}

		String[][] auxTransiciones = ControladorVentana.getInstance()
				.getTransiciones();
		JtableTransicionesPorUsuario = new JTable(auxTransiciones, null);
		JtableTransicionesPorUsuario.setBounds(JlblEstadosPorUsuario[0].getX()
				+ JlblEstadosPorUsuario[0].getWidth(),
				JlblEstadosPorUsuario[0].getY(),
				JlblEstadosPorUsuario[0].getWidth() * auxTransiciones.length,
				JlblEstadosPorUsuario[0].getHeight()
						* auxTransiciones[0].length);
		contentPane.add(JtableTransicionesPorUsuario);

		contentPane.repaint();
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

	private void agregarEventoEstadosUsuario() {
		for (int i = 0; i < JtfdEstadosPorUsuario.length; i++) {
			JtfdEstadosPorUsuario[i].addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(KeyEvent e) {
				}

				@Override
				public void keyReleased(KeyEvent e) {
					Validar.getInstancia().setSimbolosValidos(JtfdSimbolosPorUsuario);
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}
			});
		}
	}

	private void agregarFila() {
		// if(JtfdEstadosPorUsuario.length<12){
		agregarEstadoPorUsuario();
		agregarFilaDeTranscionesPorUsuario();
		agregarAceptacionPorUsuario();
		// }else{
		// JOptionPane.showMessageDialog(null,
		// "Cantidad maxima de estados alcanzada");
		// }
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
		JtbtnAceptacionesUsuario[JtbtnAceptacionesUsuario.length - 1]
				.setFont(FUENTE);
		contentPane
				.add(JtbtnAceptacionesUsuario[JtbtnAceptacionesUsuario.length - 1]);
		agregrEventoAlVectorAceptaciones();

		contentPane.repaint();

	}

	private void agregarFilaDeTranscionesPorUsuario() {
		JFormattedTextField[][] aux = new JFormattedTextField[JftdMatrizTransicionesPorUsuario.length][JftdMatrizTransicionesPorUsuario[0].length];
		aux = JftdMatrizTransicionesPorUsuario;
		JftdMatrizTransicionesPorUsuario = new JFormattedTextField[JftdMatrizTransicionesPorUsuario.length + 1][JftdMatrizTransicionesPorUsuario[0].length];
		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux[0].length; j++) {
				JftdMatrizTransicionesPorUsuario[i][j] = aux[i][j];
			}
		}
		for (int i = 0; i < JftdMatrizTransicionesPorUsuario[0].length; i++) {
			JftdMatrizTransicionesPorUsuario[JftdMatrizTransicionesPorUsuario.length - 1][i] = new JFormattedTextField();
			JftdMatrizTransicionesPorUsuario[JftdMatrizTransicionesPorUsuario.length - 1][i]
					.setBounds(
							JftdMatrizTransicionesPorUsuario[JftdMatrizTransicionesPorUsuario.length - 2][i]
									.getX(),
							JftdMatrizTransicionesPorUsuario[JftdMatrizTransicionesPorUsuario.length - 2][i]
									.getY()
									+ JftdMatrizTransicionesPorUsuario[JftdMatrizTransicionesPorUsuario.length - 2][i]
											.getHeight(),
							JftdMatrizTransicionesPorUsuario[JftdMatrizTransicionesPorUsuario.length - 2][i]
									.getWidth(),
							JftdMatrizTransicionesPorUsuario[JftdMatrizTransicionesPorUsuario.length - 2][i]
									.getHeight());
			JftdMatrizTransicionesPorUsuario[JftdMatrizTransicionesPorUsuario.length - 1][i]
					.setFont(FUENTE);
			contentPane
					.add(JftdMatrizTransicionesPorUsuario[JftdMatrizTransicionesPorUsuario.length - 1][i]);
		}
		agregarEventoMatrizTransiciones();
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
		JtfdEstadosPorUsuario[JtfdEstadosPorUsuario.length - 1].setFont(FUENTE);
		contentPane
				.add(JtfdEstadosPorUsuario[JtfdEstadosPorUsuario.length - 1]);
		agregarEventoEstadosUsuario();
		contentPane.repaint();
	}

	private void agregarColumna() {
		if (JtfdSimbolosPorUsuario.length < 5) {
			agregarSimboloPorUsuario();
			moverArrayDeAeptaciones();
			agregarColumnaDeTransicionesDeUsuario();
		} else {
			JOptionPane.showMessageDialog(null,
					"Cantidad maxima de simbolos alcanzada");
		}
	}

	private void moverArrayDeAeptaciones() {
		for (int i = 0; i < JtbtnAceptacionesUsuario.length; i++) {
			JtbtnAceptacionesUsuario[i].setBounds(
					JtbtnAceptacionesUsuario[i].getX()
							+ JftdMatrizTransicionesPorUsuario[0][0].getWidth(),
					JtbtnAceptacionesUsuario[i].getY(),
					JtbtnAceptacionesUsuario[i].getWidth(),
					JtbtnAceptacionesUsuario[i].getHeight());
		}
		contentPane.repaint();
	}

	private void agregarColumnaDeTransicionesDeUsuario() {
		JFormattedTextField[][] aux = new JFormattedTextField[JftdMatrizTransicionesPorUsuario.length][JftdMatrizTransicionesPorUsuario[0].length];
		aux = JftdMatrizTransicionesPorUsuario;
		JftdMatrizTransicionesPorUsuario = new JFormattedTextField[JftdMatrizTransicionesPorUsuario.length][JftdMatrizTransicionesPorUsuario[0].length + 1];
		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux[0].length; j++) {
				JftdMatrizTransicionesPorUsuario[i][j] = aux[i][j];
			}
		}
		for (int i = 0; i < JftdMatrizTransicionesPorUsuario.length; i++) {
			System.out.println(i + ","
					+ JftdMatrizTransicionesPorUsuario[0].length);
			JftdMatrizTransicionesPorUsuario[i][JftdMatrizTransicionesPorUsuario.length - 1] = new JFormattedTextField();
			JftdMatrizTransicionesPorUsuario[i][JftdMatrizTransicionesPorUsuario.length - 1]
					.setBounds(
							JftdMatrizTransicionesPorUsuario[i][JftdMatrizTransicionesPorUsuario.length - 2]
									.getX() + WIDTHCOMBO, YINICIAL
									+ HEIGHTELEMENTO + i * HEIGHTELEMENTO,
							WIDTHCOMBO, HEIGHTELEMENTO);
			JftdMatrizTransicionesPorUsuario[i][JftdMatrizTransicionesPorUsuario.length - 1]
					.setFont(FUENTE);
			contentPane
					.add(JftdMatrizTransicionesPorUsuario[i][JftdMatrizTransicionesPorUsuario.length - 1]);

		}
		agregarEventoMatrizTransiciones();
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
		JtfdSimbolosPorUsuario[JtfdSimbolosPorUsuario.length - 1]
				.setFont(FUENTE);
		contentPane
				.add(JtfdSimbolosPorUsuario[JtfdSimbolosPorUsuario.length - 1]);
		contentPane.repaint();
	}

	private void reiniciarAF() {
		contentPane.removeAll();
		contentPane.repaint();
		initLadoIzquierdo();
	}

	private void obtenerAFD() {
		// contentPane.setVisible(false);
		// agregarSegundoPanel();
		Vector<Boolean> aceptaciones = new Vector<>();
		for (int i = 0; i < JtbtnAceptacionesUsuario.length; i++) {
			if (JtbtnAceptacionesUsuario[i].getText().equals("0")) {
				aceptaciones.add(false);
			} else {
				aceptaciones.add(true);
			}
		}
		Vector<String> estados = new Vector<>();
		for (int i = 0; i < JtfdEstadosPorUsuario.length; i++) {
			estados.add(JtfdEstadosPorUsuario[i].getText());
		}
		Vector<String> simbolos = new Vector<>();
		for (int i = 0; i < JtfdSimbolosPorUsuario.length; i++) {
			simbolos.add(JtfdSimbolosPorUsuario[i].getText());
		}
		String[][] matrizTransiciones = new String[JftdMatrizTransicionesPorUsuario.length][JftdMatrizTransicionesPorUsuario[0].length];
		for (int i = 0; i < JftdMatrizTransicionesPorUsuario.length; i++) {
			for (int j = 0; j < JftdMatrizTransicionesPorUsuario[0].length; j++) {
				matrizTransiciones[i][j] = JftdMatrizTransicionesPorUsuario[i][j]
						.getText();
			}
		}
		ControladorVentana.getInstance().simplificar(estados,
				matrizTransiciones, simbolos, aceptaciones);
		// initLadoDerecho();
	}

	private void agregarSegundoPanel() {

		secondPane = new JPanel();
		this.setContentPane(secondPane);
		int x = 30;
		int y = 40;
		secondPane.setLayout(null);
		JTextField[][] matrizNueva = new JTextField[5][5];
		for (int i = 0; i < matrizNueva.length; i++) {
			for (int j = 0; j < matrizNueva.length; j++) {
				matrizNueva[i][j].setText("Hola");
				matrizNueva[i][j].setBounds(x, y, 30, 40);
				secondPane.add(matrizNueva[i][j]);
				x = x + 30;
				y = y + 40;

			}
		}
		secondPane.setVisible(true);
		repaint();

	}

	public static void main(String args[]) {
		VentanaPrincipal vp = new VentanaPrincipal();
	}
}
