package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import base.Principal;
import modelos.BtnPersonalizado;
import modelos.ObjetoComboBox;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;

public class VentanaBuscarF extends JFrame implements ActionListener {

	private JPanel contentPane;
	public static BtnPersonalizado btnBuscarJugadores;
	public static BtnPersonalizado btnOK;
	public JComboBox comboBox;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JTextArea textArea_2;
	JLabel label2 = new JLabel("");

	public static JTable table;

	Image img, img2;
	Icon iconobtn1;
	private JTextField textField_dni;

	/**
	 * Create the frame.
	 */
	public VentanaBuscarF() {
		setTitle("Buscar jugadores o jugadoras");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogueo.class.getResource("/imagenes/IconoBalon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1604, 882);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);

		JLabel lblInserteDni = new JLabel("Inserte dni:");
		lblInserteDni.setFont(new Font("Verdana", Font.BOLD, 20));
		panel.add(lblInserteDni);

		textField_dni = new JTextField();
		textField_dni.setFont(new Font("Verdana", Font.PLAIN, 20));
		panel.add(textField_dni);
		textField_dni.setColumns(10);

		try {
			img = ImageIO.read(getClass().getResource("/imagenes/button_buscar-jugadores.png"));
		} catch (IOException e) {
			System.out.println("Error al cargar la imagen del boton buscar jugadores");
		}

		btnBuscarJugadores = new BtnPersonalizado();
		panel.add(btnBuscarJugadores);
		btnBuscarJugadores.setBorderPainted(false);
		btnBuscarJugadores.setContentAreaFilled(false);
		btnBuscarJugadores.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		btnBuscarJugadores.setIcon(new ImageIcon(img));
		btnBuscarJugadores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarJugadores.addActionListener(this);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 22, 1057, 336);
		panel_1.add(scrollPane);

		table = new JTable();
		// escuchador de la tabla
		table.addMouseListener(new MouseAdapter() {
			@Override
			// acción de clic en una fila concreta
			public void mouseClicked(MouseEvent arg0) {
				// int seleccionar = table.rowAtPoint(arg0.getPoint());
				int fila = table.getSelectedRow();
				// recoge el valor de la fila selecciona y la primera columna (que es la que
				// corresponde al dni PK)
				String dni = (String) table.getValueAt(fila, 0);
				String alergias = buscarDetallesJug("fut.alergias", dni);
				textArea.setText(alergias);
				String lesiones = buscarDetallesJug("fut.lesiones", dni);
				textArea_1.setText(lesiones);
				String caract = buscarDetallesJug("fut.caracteristicas", dni);
				textArea_2.setText(caract);
				String urlImagen = buscarDetallesJug("fut.urlImagen", dni);

				Image img3 = null;
				try {
					img3 = ImageIO.read(getClass().getResource(urlImagen));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				label2.setIcon(new ImageIcon(img3));
			}

			// método para buscar informacion del jugador seleccionado de la tabla e
			// imprimirlo en sus respectivas areas
			private String buscarDetallesJug(String campoAbuscar, String dni) {
				try {
					Statement sentencia = Principal.conexion.createStatement();
					ResultSet resultado = sentencia.executeQuery(
							"select " + campoAbuscar + " from futbolista fut where fut.dniFutbolista='" + dni + "';");

					while (resultado.next()) {
						String busqueda = resultado.getString(1);
						System.out.println(busqueda);
						return busqueda;

					}

				} catch (SQLException e) {
					System.out.println("Error al hacer la búsqueda de alergias de un futbolista" + e);
				}
				return "";
			}
		});

		table.setFont(new java.awt.Font("Tahoma", 0, 16));

		table.setBounds(5, 5, 0, 0);

		// la hemos añadido al scroll
		scrollPane.setViewportView(table);

		JLabel lblAlergias = new JLabel("Alergias");
		lblAlergias.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblAlergias.setBounds(157, 400, 106, 25);
		panel_1.add(lblAlergias);

		JLabel lblLesiones = new JLabel("Lesiones");
		lblLesiones.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblLesiones.setBounds(527, 400, 106, 25);
		panel_1.add(lblLesiones);

		JLabel lblCaractersticas = new JLabel("Características");
		lblCaractersticas.setFont(new Font("Verdana", Font.PLAIN, 20));
		lblCaractersticas.setBounds(1014, 400, 178, 25);
		panel_1.add(lblCaractersticas);

		textArea = new JTextArea();
		textArea.setFont(new Font("Verdana", Font.PLAIN, 18));
		textArea.setBounds(56, 435, 326, 207);
		Border border = BorderFactory.createLineBorder(Color.black);
		textArea.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		panel_1.add(textArea);

		textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		textArea_1.setBounds(423, 435, 326, 207);
		Border border_1 = BorderFactory.createLineBorder(Color.black);
		textArea_1.setBorder(
				BorderFactory.createCompoundBorder(border_1, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		panel_1.add(textArea_1);

		textArea_2 = new JTextArea();
		textArea_2.setFont(new Font("Verdana", Font.PLAIN, 18));
		textArea_2.setBounds(790, 435, 615, 207);
		Border border_2 = BorderFactory.createLineBorder(Color.black);
		textArea_2.setBorder(
				BorderFactory.createCompoundBorder(border_2, BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		panel_1.add(textArea_2);

		label2.setBounds(1166, 39, 393, 264);
		label2.setBorder(border_2);
		panel_1.add(label2);

		// Icono de alergias
		JLabel label_iconoAlergias = new JLabel("");
		Image img4 = null;
		try {
			img4 = ImageIO.read(getClass().getResource("/imagenes/alergias.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		label_iconoAlergias.setIcon(new ImageIcon(img4));
		label_iconoAlergias.setBounds(117, 394, 30, 31);
		panel_1.add(label_iconoAlergias);

		// Icono de lesiones
		JLabel label_iconoLesiones = new JLabel("");
		Image img5 = null;
		try {
			img5 = ImageIO.read(getClass().getResource("/imagenes/ambulancia.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		label_iconoLesiones.setIcon(new ImageIcon(img5));
		label_iconoLesiones.setBounds(487, 394, 35, 35);
		panel_1.add(label_iconoLesiones);

		// Icono de caracteristicas
		JLabel label_iconoCaract = new JLabel("");
		Image img6 = null;
		try {
			img6 = ImageIO.read(getClass().getResource("/imagenes/caract.png"));
		} catch (IOException e1) {
			System.out.println("Error al cargar el icono de las caracteristicas" + e1);
		}

		label_iconoCaract.setIcon(new ImageIcon(img6));
		label_iconoCaract.setBounds(969, 394, 35, 35);
		panel_1.add(label_iconoCaract);

		try {
			img2 = ImageIO.read(getClass().getResource("/imagenes/button_ok.png"));
		} catch (IOException e1) {

			System.out.println("Error al cargar la imagen del boton ok");
		}

		// panel 3 con boton de ok
		JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.SOUTH);

		// boton para confirmar la búsqueda
		btnOK = new BtnPersonalizado();
		panel_3.add(btnOK);
		btnOK.setBorderPainted(false);
		btnOK.setContentAreaFilled(false);
		btnOK.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		btnOK.setIcon(new ImageIcon(img2));
		btnOK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOK.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnBuscarJugadores) {
			String dniIntroducido = textField_dni.getText();
			if (!dniIntroducido.isEmpty()) {
				busquedaMuestraJugadoresXdni(dniIntroducido);
				// si pulsa X que se cierre solo esa ventana

			} else {
				JOptionPane.showMessageDialog(null, "Introduce el dni del futbolista", "Error al buscar futbolista",
						JOptionPane.ERROR_MESSAGE);
				System.out.println("no se ha introducido dni para buscar jugador");
				textField_dni.setBackground(Color.red);
			}
		}

		if (e.getSource() == this.btnOK) {
			this.dispose();
		}
	}

	private void busquedaMuestraJugadoresXdni(String dniIntroducido) {

		// hacer búsqueda
		try {
			Statement sentencia = Principal.conexion.createStatement();
			ResultSet resultado = sentencia.executeQuery(
					"select fut.dniFutbolista, fut.estado,fut.nombre, fut.primerApellido, fut.segundoApellido, fut.sexo, fut.fechadeNacimiento, \r\n"
							+ "lat.predominio, pos.nombrePos, fut.centroEscolar\r\n"
							+ "from categorias cat, futbolista fut, lateralidad lat, posiciones pos\r\n"
							+ "where cat.idCat= fut.idCategoria\r\n"
							+ "and lat.idPiernaDominante=fut.idPiernaDominante\r\n"
							+ "and pos.idPosicion=fut.idPosicion\r\n" + "and fut.dniFutbolista='" + dniIntroducido
							+ "';");

			DefaultTableModel modelo = establecerModeloTabla();
			establecerAnchoColumnas();
			centrarTextoColumnas();

			String[] dato = new String[13];
			while (resultado.next()) {
				dato[0] = resultado.getString(1);
				dato[1] = resultado.getString(2);
				dato[2] = resultado.getString(3);
				dato[3] = resultado.getString(4);
				dato[4] = resultado.getString(5);
				dato[5] = resultado.getString(6);
				dato[6] = resultado.getString(7);
				dato[7] = resultado.getString(8);
				dato[8] = resultado.getString(9);
				dato[9] = resultado.getString(10);

				modelo.addRow(dato);
			}

		} catch (SQLException e) {
			System.out.println("Error al realizar la conexión con la base de datos" + e);
		}
	}

	private DefaultTableModel establecerModeloTabla() {
		DefaultTableModel modelo = new DefaultTableModel() {
			// método para que los campos de las jtables no sean editables
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		modelo.addColumn("DNI");
		modelo.addColumn("Estado");
		modelo.addColumn("Nombre");
		modelo.addColumn("1Apellido");
		modelo.addColumn("2Apellido");
		modelo.addColumn("Sexo");
		modelo.addColumn("Fecha Nac");
		modelo.addColumn("Lateralidad");
		modelo.addColumn("Posicion");
		modelo.addColumn("Colegio");

		VentanaBuscarF.table.setModel(modelo);
		return modelo;
	}

	private void establecerAnchoColumnas() {
		TableColumn columna0 = table.getColumn("DNI");
		columna0.setPreferredWidth(40);
		TableColumn columna1 = table.getColumn("Estado");
		columna1.setPreferredWidth(8);
		TableColumn columna2 = table.getColumn("Nombre");
		columna2.setPreferredWidth(40);
		TableColumn columna3 = table.getColumn("1Apellido");
		columna3.setPreferredWidth(60);
		TableColumn columna4 = table.getColumn("2Apellido");
		columna4.setPreferredWidth(60);
		TableColumn columna5 = table.getColumn("Sexo");
		columna5.setPreferredWidth(30);
		TableColumn columna6 = table.getColumn("Fecha Nac");
		columna6.setPreferredWidth(50);
		TableColumn columna7 = table.getColumn("Lateralidad");
		columna7.setPreferredWidth(30);
		TableColumn columna8 = table.getColumn("Posicion");
		columna8.setPreferredWidth(80);
		TableColumn columna9 = table.getColumn("Colegio");
		columna9.setPreferredWidth(200);
	}

	private void centrarTextoColumnas() {
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
		modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		table.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		table.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
		table.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
		table.getColumnModel().getColumn(4).setCellRenderer(modelocentrar);
		table.getColumnModel().getColumn(5).setCellRenderer(modelocentrar);
		table.getColumnModel().getColumn(6).setCellRenderer(modelocentrar);
		table.getColumnModel().getColumn(7).setCellRenderer(modelocentrar);
		table.getColumnModel().getColumn(8).setCellRenderer(modelocentrar);
		table.getColumnModel().getColumn(9).setCellRenderer(modelocentrar);
	}

}
