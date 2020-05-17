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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import base.Principal;
import modelos.BtnPersonalizado;
import modelos.ObjetoComboBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class VentanaBuscarTutor extends JFrame implements ActionListener {

	private JPanel contentPane;
	public static BtnPersonalizado btnOK;
	BtnPersonalizado btnBuscarTutor;
	Image img, img2;
	private JLabel lblNombreDelFutbolista;
	private JTextField textField_nombreJ;
	private JPanel panel_central;
	private static JTable table;
	private JTextField textField_dni;
	private JTextField textField_nombre;
	private JTextField textField_ape1;
	private JTextField textField_ape2;
	private JTextField textField_dire;
	private JTextField textField_email;
	private JTextField textField_tlf1;
	private JTextField textField_tlf2;
	private JTextField textField_paren;

	/**
	 * Create the frame.
	 */
	public VentanaBuscarTutor() {
		setTitle("Buscar Tutor");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogueo.class.getResource("/imagenes/IconoBalon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1153, 815);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel_superior = new JPanel();
		contentPane.add(panel_superior, BorderLayout.NORTH);

		lblNombreDelFutbolista = new JLabel("Nombre del futbolista: ");
		lblNombreDelFutbolista.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel_superior.add(lblNombreDelFutbolista);

		textField_nombreJ = new JTextField();
		textField_nombreJ.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel_superior.add(textField_nombreJ);
		textField_nombreJ.setColumns(10);

		try {
			img = ImageIO.read(getClass().getResource("/imagenes/button_buscar-tutor-rojo.png"));
		} catch (IOException e) {

			System.out.println("Error al cargar la imagen del boton buscar tutor");
		}

		JLabel lblNewLabel = new JLabel("          ");
		panel_superior.add(lblNewLabel);
		btnBuscarTutor = new BtnPersonalizado();
		panel_superior.add(btnBuscarTutor);
		btnBuscarTutor.setBorderPainted(false);
		btnBuscarTutor.setContentAreaFilled(false);
		btnBuscarTutor.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		btnBuscarTutor.setIcon(new ImageIcon(img));
		btnBuscarTutor.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarTutor.addActionListener(this);

		panel_central = new JPanel();
		contentPane.add(panel_central, BorderLayout.CENTER);
		panel_central.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 94, 485, 332);
		panel_central.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			// acción de clic en una fila concreta
			public void mouseClicked(MouseEvent arg0) {
				// int seleccionar = table.rowAtPoint(arg0.getPoint());
				int fila = table.getSelectedRow();
				// al hacer click en la fila correspondiente busca el dni del tutor segun el dni del futbolista
				String dni = (String) table.getValueAt(fila, 0);
				String dniT="";
				String paren="";
				try {
					Statement sentencia = Principal.conexion.createStatement();
					ResultSet resultado = sentencia.executeQuery("select txf.dniResponsable, p.tipo from tutorxfutbolista txf, parentesco p where p.idParentesco = txf.idParentesco and dniFutbolista='" + dni + "';");
					
					while (resultado.next()) {
						dniT = resultado.getString(1);
						paren = resultado.getString(2);
						System.out.println(dniT);
					}
				} catch (SQLException e) {
					System.out.println("Error al hacer la búsqueda del dni del tutor/a " + e);
				}
				
				textField_dni.setText(dniT);
				textField_paren.setText(paren);
				//buscar detalles del tutor e imprimir en sus texfields correspondientes
				
				String nombreT = "";
				String apeT1="";
				String apeT2="";
				String dire ="";
				String email="";
				String tlf1="";
				String tlf2="";
				
				try {
					Statement sentencia = Principal.conexion.createStatement();
					ResultSet resultado = sentencia.executeQuery("select nombreTutor, primerApTutor, segundoApTutor, direccionPostal, email, tlf1, tlf2 from tutor where dniResponsable='" + dniT + "';");
					
					while (resultado.next()) {
						nombreT = resultado.getString(1);
						System.out.println(nombreT);
						apeT1 = resultado.getString(2);
						apeT2 = resultado.getString(3);
						dire = resultado.getString(4);
						email = resultado.getString(5);
						tlf1 = resultado.getString(6);
						tlf2 = resultado.getString(7);
					}
				} catch (SQLException e) {
					System.out.println("Error al hacer la búsqueda del dni del tutor/a " + e);
				}
				//rellenamos texfield con los datos encontrados a raiz del dni del tutor
				textField_nombre.setText(nombreT);
				textField_ape1.setText(apeT1);
				textField_ape2.setText(apeT2);
				textField_dire.setText(dire);
				textField_email.setText(email);
				textField_tlf1.setText(tlf1);
				textField_tlf2.setText(tlf2);
				
			}
		});
		
		table.setFont(new java.awt.Font("Tahoma", 0, 16));
		table.setBounds(0, 0, 1, 1);
		// panel_central.add(table);
		scrollPane.setViewportView(table);

		JLabel lblDatosDelTutor = new JLabel("Datos del tutor o tutora:");
		lblDatosDelTutor.setFont(new Font("Verdana", Font.BOLD, 18));
		lblDatosDelTutor.setBounds(628, 40, 254, 33);
		panel_central.add(lblDatosDelTutor);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblDni.setBounds(628, 109, 54, 18);
		panel_central.add(lblDni);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNombre.setBounds(628, 149, 70, 18);
		panel_central.add(lblNombre);

		JLabel lblPrimerApellido = new JLabel("Primer apellido:");
		lblPrimerApellido.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblPrimerApellido.setBounds(628, 193, 131, 18);
		panel_central.add(lblPrimerApellido);

		JLabel lblSegundoApellido = new JLabel("Segundo apellido:");
		lblSegundoApellido.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblSegundoApellido.setBounds(628, 235, 143, 18);
		panel_central.add(lblSegundoApellido);

		JLabel lblDireccin = new JLabel("Dirección:");
		lblDireccin.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblDireccin.setBounds(628, 281, 101, 18);
		panel_central.add(lblDireccin);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEmail.setBounds(628, 327, 70, 18);
		panel_central.add(lblEmail);

		JLabel lblTelfonos = new JLabel("Teléfonos:");
		lblTelfonos.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblTelfonos.setBounds(628, 374, 99, 18);
		panel_central.add(lblTelfonos);

		textField_dni = new JTextField();
		textField_dni.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_dni.setBounds(830, 98, 186, 29);
		panel_central.add(textField_dni);
		textField_dni.setColumns(10);

		textField_nombre = new JTextField();
		textField_nombre.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_nombre.setColumns(10);
		textField_nombre.setBounds(830, 139, 186, 29);
		panel_central.add(textField_nombre);

		textField_ape1 = new JTextField();
		textField_ape1.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_ape1.setColumns(10);
		textField_ape1.setBounds(830, 182, 186, 29);
		panel_central.add(textField_ape1);

		textField_ape2 = new JTextField();
		textField_ape2.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_ape2.setColumns(10);
		textField_ape2.setBounds(830, 224, 186, 29);
		panel_central.add(textField_ape2);

		textField_dire = new JTextField();
		textField_dire.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_dire.setColumns(10);
		textField_dire.setBounds(830, 270, 186, 29);
		panel_central.add(textField_dire);

		textField_email = new JTextField();
		textField_email.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_email.setColumns(10);
		textField_email.setBounds(830, 316, 186, 29);
		panel_central.add(textField_email);

		textField_tlf1 = new JTextField();
		textField_tlf1.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_tlf1.setColumns(10);
		textField_tlf1.setBounds(830, 363, 186, 29);
		panel_central.add(textField_tlf1);

		textField_tlf2 = new JTextField();
		textField_tlf2.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_tlf2.setColumns(10);
		textField_tlf2.setBounds(830, 403, 186, 29);
		panel_central.add(textField_tlf2);
		
		textField_paren = new JTextField();
		textField_paren.setFont(new Font("Verdana", Font.PLAIN, 16));
		textField_paren.setBounds(904, 41, 112, 29);
		panel_central.add(textField_paren);
		textField_paren.setColumns(10);

		JPanel panel_inferior = new JPanel();
		contentPane.add(panel_inferior, BorderLayout.SOUTH);

		// boton para confirmar la búsqueda
		try {
			img2 = ImageIO.read(getClass().getResource("/imagenes/button_ok.png"));
		} catch (IOException e) {

			System.out.println("Error al cargar la imagen del boton ok" + e);
		}
		btnOK = new BtnPersonalizado();
		panel_inferior.add(btnOK);
		btnOK.setBorderPainted(false);
		btnOK.setContentAreaFilled(false);
		btnOK.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		btnOK.setIcon(new ImageIcon(img2));
		btnOK.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOK.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == this.btnBuscarTutor) {
			// mostrar en la tabla todos los jugadores que lleven ese nombre (+dni, 1ape,
			// 2ape, fechanac)
			// al hacer doble click en la fila mostrar datos del tutor/a

			String nombreJIntroducido = textField_nombreJ.getText();
			if (nombreJIntroducido.isEmpty()) {
				System.out.println("el campo de nombre de futbolista está vacío");
				JOptionPane.showMessageDialog(null,
						"Pon el nombre del futbolista para encontrar información de su tutor",
						"Error al buscar tutor o tutora", JOptionPane.ERROR_MESSAGE);
				textField_nombreJ.setBackground(Color.red);
			} else {
				// hacer búsqueda y mostrarla en tabla
				mostrarDatosenTabla(nombreJIntroducido);
			}

		}
		
		if (evento.getSource() == this.btnOK) {
			this.dispose();
		}

	}

	private void mostrarDatosenTabla(String nombreJIntroducido) {
		try {
			Statement sentencia = Principal.conexion.createStatement();
			ResultSet resultado = sentencia.executeQuery(
					"select fut.dniFutbolista,fut.nombre, fut.primerApellido, fut.segundoApellido,fut.fechadeNacimiento from futbolista fut where fut.nombre='"
							+ nombreJIntroducido + "';");

			DefaultTableModel modelo = establecerModeloTabla();
			establecerAnchoColumnas();
			centrarTextoColumnas();

			String[] dato = new String[6];
			while (resultado.next()) {
				dato[0] = resultado.getString(1);
				dato[1] = resultado.getString(2);
				dato[2] = resultado.getString(3);
				dato[3] = resultado.getString(4);
				dato[4] = resultado.getString(5);

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
		modelo.addColumn("Nombre");
		modelo.addColumn("1Apellido");
		modelo.addColumn("2Apellido");
		modelo.addColumn("Fecha Nac");

		VentanaBuscarTutor.table.setModel(modelo);
		return modelo;
	}

	private void establecerAnchoColumnas() {
		TableColumn columna0 = table.getColumn("DNI");
		columna0.setPreferredWidth(40);
		TableColumn columna2 = table.getColumn("Nombre");
		columna2.setPreferredWidth(40);
		TableColumn columna3 = table.getColumn("1Apellido");
		columna3.setPreferredWidth(60);
		TableColumn columna4 = table.getColumn("2Apellido");
		columna4.setPreferredWidth(60);
		TableColumn columna6 = table.getColumn("Fecha Nac");
		columna6.setPreferredWidth(50);
	}

	private void centrarTextoColumnas() {
		DefaultTableCellRenderer modelocentrar = new DefaultTableCellRenderer();
		modelocentrar.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(modelocentrar);
		table.getColumnModel().getColumn(1).setCellRenderer(modelocentrar);
		table.getColumnModel().getColumn(2).setCellRenderer(modelocentrar);
		table.getColumnModel().getColumn(3).setCellRenderer(modelocentrar);
		table.getColumnModel().getColumn(4).setCellRenderer(modelocentrar);

	}
}
