package ventanas;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.Principal;
import modelos.BtnPersonalizado;
import modelos.ObjetoComboBox;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class VentanaInsertarFut extends JFrame implements ActionListener {

	private JPanel contentPane;
	BtnPersonalizado btnInsertarJugador;
	JButton btnExaminar;
	Image img;
	private JTextField textFieldNombre;
	private JTextField textField_Apellido1;
	private JTextField textField_apellido2;
	private JLabel lblFechaDeNac;
	private JTextField textFieldCole;
	private JTextField textFieldAlergias;
	private JTextField textField_foto;
	private JTextField textFieldNombreT;
	private JTextField textFieldDNI;
	private JTextField textFieldApellido1T;
	private JTextField textFieldApellido2T;
	private JTextField textFieldDNIT;
	private JTextField textField_tlf1;
	private JTextField textField_tlf2;
	private JTextField textField_email;
	private JTextField textField_dire;

	JComboBox<ObjetoComboBox> comboBoxLat;
	JComboBox<ObjetoComboBox> comboBoxPos;
	JComboBox<ObjetoComboBox> comboBox_parentesco;
	JComboBox<ObjetoComboBox> comboBoxCat;
	JComboBox comboBoxSexo;
	JComboBox comboBoxEstado;
	JComboBox comboBoxDia;
	JComboBox comboBoxMes;
	JComboBox comboBox;

	JTextArea textAreaLesiones;
	JTextArea textAreaCaract;

	/**
	 * Create the frame.
	 */
	public VentanaInsertarFut() {
		setTitle("Insertar nuevo futbolista");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogueo.class.getResource("/imagenes/IconoBalon.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);// tamaño fijo de la ventana
		setBounds(100, 100, 1604, 882);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panelCentral = new JPanel();
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNombre.setBounds(124, 77, 87, 23);
		panelCentral.add(lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setFont(new Font("Verdana", Font.PLAIN, 18));
		textFieldNombre.setBounds(221, 77, 111, 22);
		panelCentral.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblApellido1 = new JLabel("Primer apellido: ");
		lblApellido1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblApellido1.setBounds(369, 74, 152, 29);
		panelCentral.add(lblApellido1);

		textField_Apellido1 = new JTextField();
		textField_Apellido1.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_Apellido1.setColumns(10);
		textField_Apellido1.setBounds(532, 77, 163, 23);
		panelCentral.add(textField_Apellido1);

		JLabel lblSegundoApellido = new JLabel("Segundo apellido: ");
		lblSegundoApellido.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblSegundoApellido.setBounds(761, 77, 173, 22);
		panelCentral.add(lblSegundoApellido);

		textField_apellido2 = new JTextField();
		textField_apellido2.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_apellido2.setColumns(10);
		textField_apellido2.setBounds(936, 77, 163, 23);
		panelCentral.add(textField_apellido2);

		lblFechaDeNac = new JLabel("Fecha de nac.: ");
		lblFechaDeNac.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblFechaDeNac.setBounds(1144, 78, 152, 20);
		panelCentral.add(lblFechaDeNac);

		comboBoxDia = new JComboBox();
		comboBoxDia.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBoxDia.setToolTipText("");
		comboBoxDia.setModel(new DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
						"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
		comboBoxDia.setBounds(1299, 79, 46, 21);
		panelCentral.add(comboBoxDia);

		comboBoxMes = new JComboBox();
		comboBoxMes.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBoxMes.setModel(new DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		comboBoxMes.setBounds(1355, 79, 46, 21);
		panelCentral.add(comboBoxMes);

		comboBox = new JComboBox();
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "1974", "1975", "1976", "1978", "1979", "1980",
				"1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993",
				"1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006",
				"2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019",
				"2020", "2021" }));
		comboBox.setBounds(1411, 79, 75, 21);
		panelCentral.add(comboBox);

		JLabel lblSexo = new JLabel("Sexo: ");
		lblSexo.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblSexo.setBounds(124, 151, 87, 23);
		panelCentral.add(lblSexo);

		comboBoxSexo = new JComboBox();
		comboBoxSexo.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBoxSexo.setModel(new DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));
		comboBoxSexo.setBounds(221, 151, 111, 26);
		panelCentral.add(comboBoxSexo);

		JLabel lblDni = new JLabel("DNI: ");
		lblDni.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblDni.setBounds(369, 154, 87, 23);
		panelCentral.add(lblDni);

		textFieldDNI = new JTextField();
		textFieldDNI.setBounds(532, 151, 163, 29);
		panelCentral.add(textFieldDNI);
		textFieldDNI.setFont(new Font("Verdana", Font.PLAIN, 18));
		textFieldDNI.setColumns(10);

		JLabel lblAlergias = new JLabel("Alergias:");
		lblAlergias.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblAlergias.setBounds(761, 154, 87, 23);
		panelCentral.add(lblAlergias);

		textFieldAlergias = new JTextField();
		textFieldAlergias.setFont(new Font("Verdana", Font.PLAIN, 18));
		textFieldAlergias.setColumns(10);
		textFieldAlergias.setBounds(936, 154, 550, 23);
		panelCentral.add(textFieldAlergias);

		JLabel lblLesiones = new JLabel("Lesiones: ");
		lblLesiones.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblLesiones.setBounds(124, 227, 127, 23);
		panelCentral.add(lblLesiones);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(124, 267, 208, 174);

		panelCentral.add(scrollPane);

		textAreaLesiones = new JTextArea();
		textAreaLesiones.setFont(new Font("Verdana", Font.PLAIN, 16));
		scrollPane.setViewportView(textAreaLesiones);
		textAreaLesiones.setLineWrap(true);

		JLabel lblCaractersticas = new JLabel("Características: ");
		lblCaractersticas.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblCaractersticas.setBounds(369, 226, 152, 23);
		panelCentral.add(lblCaractersticas);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(369, 267, 326, 174);
		panelCentral.add(scrollPane_1);

		textAreaCaract = new JTextArea();
		textAreaCaract.setFont(new Font("Verdana", Font.PLAIN, 16));
		scrollPane_1.setViewportView(textAreaCaract);
		textAreaCaract.setLineWrap(true);

		JLabel lblFotografa = new JLabel("Fotografía: ");
		lblFotografa.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblFotografa.setBounds(761, 222, 152, 23);
		panelCentral.add(lblFotografa);

		textField_foto = new JTextField();
		textField_foto.setBounds(936, 224, 429, 25);
		panelCentral.add(textField_foto);
		textField_foto.setColumns(10);

		btnExaminar = new JButton("Examinar");
		btnExaminar.setFont(new Font("Verdana", Font.PLAIN, 16));
		btnExaminar.setBounds(1375, 224, 111, 25);
		panelCentral.add(btnExaminar);
		btnExaminar.addActionListener(this);

		JLabel lblEstado = new JLabel("Estado: ");
		lblEstado.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEstado.setBounds(761, 287, 131, 23);
		panelCentral.add(lblEstado);

		comboBoxEstado = new JComboBox();
		comboBoxEstado.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBoxEstado.setModel(new DefaultComboBoxModel(new String[] { "Alta", "Baja" }));
		comboBoxEstado.setBounds(936, 287, 75, 26);
		panelCentral.add(comboBoxEstado);

		JLabel lblLateralidad = new JLabel("Lateralidad: ");
		lblLateralidad.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblLateralidad.setBounds(1144, 290, 131, 23);
		panelCentral.add(lblLateralidad);

		comboBoxLat = new JComboBox();
		comboBoxLat.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBoxLat.setBounds(1299, 287, 187, 26);
		panelCentral.add(comboBoxLat);

		JLabel lblCategora = new JLabel("Categoría: ");
		lblCategora.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblCategora.setBounds(761, 353, 118, 23);
		panelCentral.add(lblCategora);

		comboBoxCat = new JComboBox();
		comboBoxCat.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBoxCat.setBounds(936, 355, 163, 21);
		panelCentral.add(comboBoxCat);

		JLabel lblPosicin = new JLabel("Posición: ");
		lblPosicin.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblPosicin.setBounds(1144, 354, 131, 23);
		panelCentral.add(lblPosicin);

		comboBoxPos = new JComboBox();
		comboBoxPos.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBoxPos.setBounds(1299, 356, 187, 21);
		panelCentral.add(comboBoxPos);

		JLabel lblColegio = new JLabel("Colegio:");
		lblColegio.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblColegio.setBounds(761, 418, 87, 23);
		panelCentral.add(lblColegio);

		textFieldCole = new JTextField();
		textFieldCole.setFont(new Font("Verdana", Font.PLAIN, 18));
		textFieldCole.setColumns(10);
		textFieldCole.setBounds(936, 418, 326, 23);
		panelCentral.add(textFieldCole);

		JLabel lblDatosTutor = new JLabel("Datos Tutor");
		lblDatosTutor.setFont(new Font("Verdana", Font.BOLD, 20));
		lblDatosTutor.setBounds(124, 478, 152, 23);
		panelCentral.add(lblDatosTutor);

		JSeparator separator = new JSeparator();
		separator.setBounds(127, 499, 1, 2);
		panelCentral.add(separator);

		JLabel labelNombreT = new JLabel("Nombre: ");
		labelNombreT.setFont(new Font("Verdana", Font.PLAIN, 18));
		labelNombreT.setBounds(124, 530, 87, 23);
		panelCentral.add(labelNombreT);

		textFieldNombreT = new JTextField();
		textFieldNombreT.setFont(new Font("Verdana", Font.PLAIN, 18));
		textFieldNombreT.setColumns(10);
		textFieldNombreT.setBounds(221, 534, 111, 22);
		panelCentral.add(textFieldNombreT);

		JLabel lblPrimerApellidoT = new JLabel("Primer Apellido: ");
		lblPrimerApellidoT.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblPrimerApellidoT.setBounds(369, 534, 163, 23);
		panelCentral.add(lblPrimerApellidoT);

		textFieldApellido1T = new JTextField();
		textFieldApellido1T.setFont(new Font("Verdana", Font.PLAIN, 18));
		textFieldApellido1T.setColumns(10);
		textFieldApellido1T.setBounds(547, 534, 148, 22);
		panelCentral.add(textFieldApellido1T);

		JLabel lblSegundoApellidoT = new JLabel("Segundo apellido: ");
		lblSegundoApellidoT.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblSegundoApellidoT.setBounds(761, 530, 173, 23);
		panelCentral.add(lblSegundoApellidoT);

		textFieldApellido2T = new JTextField();
		textFieldApellido2T.setFont(new Font("Verdana", Font.PLAIN, 18));
		textFieldApellido2T.setColumns(10);
		textFieldApellido2T.setBounds(936, 530, 163, 22);
		panelCentral.add(textFieldApellido2T);

		JLabel labelDNIT = new JLabel("DNI: ");
		labelDNIT.setFont(new Font("Verdana", Font.PLAIN, 18));
		labelDNIT.setBounds(1144, 530, 87, 23);
		panelCentral.add(labelDNIT);

		textFieldDNIT = new JTextField();
		textFieldDNIT.setFont(new Font("Verdana", Font.PLAIN, 18));
		textFieldDNIT.setColumns(10);
		textFieldDNIT.setBounds(1258, 530, 163, 22);
		panelCentral.add(textFieldDNIT);

		JLabel lblTlf = new JLabel("Tlf.: ");
		lblTlf.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblTlf.setBounds(124, 585, 87, 23);
		panelCentral.add(lblTlf);

		JLabel lblTlf_1 = new JLabel("Tlf. 2: ");
		lblTlf_1.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblTlf_1.setBounds(369, 584, 87, 23);
		panelCentral.add(lblTlf_1);

		JLabel lblEmail = new JLabel("Email: ");
		lblEmail.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblEmail.setBounds(761, 585, 87, 23);
		panelCentral.add(lblEmail);

		JLabel lblDireccinPostal = new JLabel("Dirección postal: ");
		lblDireccinPostal.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblDireccinPostal.setBounds(124, 638, 178, 23);
		panelCentral.add(lblDireccinPostal);

		textField_tlf1 = new JTextField();
		textField_tlf1.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_tlf1.setColumns(10);
		textField_tlf1.setBounds(221, 585, 111, 22);
		panelCentral.add(textField_tlf1);

		textField_tlf2 = new JTextField();
		textField_tlf2.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_tlf2.setColumns(10);
		textField_tlf2.setBounds(547, 585, 148, 22);
		panelCentral.add(textField_tlf2);

		textField_email = new JTextField();
		textField_email.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_email.setColumns(10);
		textField_email.setBounds(936, 585, 326, 22);
		panelCentral.add(textField_email);

		textField_dire = new JTextField();
		textField_dire.setFont(new Font("Verdana", Font.PLAIN, 18));
		textField_dire.setColumns(10);
		textField_dire.setBounds(369, 638, 326, 22);
		panelCentral.add(textField_dire);

		JLabel lblParentesco = new JLabel("Parentesco: ");
		lblParentesco.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblParentesco.setBounds(761, 638, 152, 23);
		panelCentral.add(lblParentesco);

		comboBox_parentesco = new JComboBox();
		comboBox_parentesco.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBox_parentesco.setBounds(936, 638, 163, 26);
		panelCentral.add(comboBox_parentesco);

		JLabel lblDatosFutbolista = new JLabel("Datos Futbolista");
		lblDatosFutbolista.setFont(new Font("Verdana", Font.BOLD, 20));
		lblDatosFutbolista.setBounds(124, 25, 208, 23);
		panelCentral.add(lblDatosFutbolista);

		JPanel panelInferior = new JPanel();
		contentPane.add(panelInferior, BorderLayout.SOUTH);

		try {
			img = ImageIO.read(getClass().getResource("/imagenes/button_insertar-futbolista.png"));
		} catch (IOException e) {

			System.out.println("Error al cargar la imagen del boton buscar jugadores");
		}

		btnInsertarJugador = new BtnPersonalizado();
		panelInferior.add(btnInsertarJugador);
		btnInsertarJugador.setBorderPainted(false);
		btnInsertarJugador.setContentAreaFilled(false);
		btnInsertarJugador.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		btnInsertarJugador.setIcon(new ImageIcon(img));
		btnInsertarJugador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInsertarJugador.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent evento) {
//obtengo ruta absoluta para mostrar en el textField
		// selecciono el nombre del archivo para hacer la ruta relativa
		String urlImagen = "";

		if (evento.getSource() == this.btnExaminar) {
			// elegir ubicacion del archivo
			urlImagen = extraerRutaRelativaFoto();

		}

		if (evento.getSource() == this.btnInsertarJugador) {

			String nombreJ = textFieldNombre.getText();
			String ape1J = textField_Apellido1.getText();
			String ape2J = textField_apellido2.getText();
			String cole = textFieldCole.getText();
			String alergias = textFieldAlergias.getText();
			String dniJ = textFieldDNI.getText();
			String nombreT = textFieldNombreT.getText();
			String ape1T = textFieldApellido1T.getText();
			String ape2T = textFieldApellido2T.getText();
			String dniT = textFieldDNIT.getText();
			String tlf1 = textField_tlf1.getText();
			String tlf2 = textField_tlf2.getText();
			String email = textField_email.getText();
			String dire = textField_dire.getText();
			String sexo = (String) comboBoxSexo.getSelectedItem();
			String estado = (String) comboBoxEstado.getSelectedItem();
			int parentesco = ((ObjetoComboBox) comboBox_parentesco.getSelectedItem()).getId();
			int lat = ((ObjetoComboBox) comboBoxLat.getSelectedItem()).getId();
			int pos = ((ObjetoComboBox) comboBoxPos.getSelectedItem()).getId();
			int cat = ((ObjetoComboBox) comboBoxCat.getSelectedItem()).getId();
			String lesiones = textAreaLesiones.getText();
			String caract = textAreaCaract.getText();
			String dia = (String) comboBoxDia.getSelectedItem();
			String mes = (String) comboBoxMes.getSelectedItem();
			String ano = (String) comboBox.getSelectedItem();
			String fechaNac = ano + "-" + mes + "-" + dia;


			// insertar datos en tablas de jugadores, tutores y jugadoresXtutores
			if (!nombreJ.isEmpty() || !ape1J.isEmpty() || !ape2J.isEmpty() || !cole.isEmpty() || !alergias.isEmpty()
					|| !nombreT.isEmpty() || !dniJ.isEmpty() || !ape1T.isEmpty() || !ape2T.isEmpty() || !dniT.isEmpty()
					|| !tlf1.isEmpty() || !tlf2.isEmpty() || !email.isEmpty() || !dire.isEmpty()) {
				
				//si el dni tiene formato adecuado entonces seguimos con la inserción
				if (validarDNI(dniJ) == true) {
					// insertar en base de datos
					Statement sentencia = null;
					try {
						sentencia = Principal.conexion.createStatement();
					} catch (SQLException e1) {
						System.out.println("error al establecer las sentencia" + e1);
					}

					ResultSet resultado = null;
					PreparedStatement sentencia1;

					try {
						// comprobación de que el dni del jugador existe en la BBDD
						resultado = sentencia.executeQuery(
								"SELECT dniFutbolista FROM futbolista where dniFutbolista='" + dniJ + "'");
						if (resultado.next()) {

							JOptionPane.showMessageDialog(null,
									"¡OJO! Ese jugador ya existe, modifícalo en vez de insertarlo",
									"Insertar Futbolista", JOptionPane.WARNING_MESSAGE);
							// comprobación de que el dni del tutor existe
							resultado = sentencia.executeQuery(
									"SELECT dniResponsable FROM tutor where dniResponsable='" + dniT + "'");
							if (resultado.next()) {
								JOptionPane.showMessageDialog(null,
										"¡OJO! Ese tutor ya existe, modifícalo en vez de insertarlo",
										"Insertar Futbolista", JOptionPane.WARNING_MESSAGE);
							}

						} else {

							insertarJugadorBD(urlImagen, nombreJ, ape1J, ape2J, cole, alergias, dniJ, sexo, estado, lat,
									pos, cat, lesiones, caract, fechaNac);

							// hacer métodoo para insertar tutor en tabla Tutores
							insertarTutorBD(nombreT, ape1T, ape2T, dniT, tlf1, email, dire);

							// hacer metodo para insertar en la tabla tutorXfutbolista

							insertarJugXtut(dniJ, dniT, parentesco);

							JOptionPane.showMessageDialog(null, "Futbolista insertado correctamente",
									"Insertar Futbolista", JOptionPane.INFORMATION_MESSAGE);
						}
						
					} catch (HeadlessException e) {
						System.out.println("Error al hacer la búsqueda");
					} catch (SQLException e) {
						System.out.println("Error de mysql" + e);
					}

				} else {
					System.out.println("El dni introducido no está en el formato correcto");
					textFieldDNI.setBackground(Color.red);
					JOptionPane.showMessageDialog(null, "El formato del DNI es incorrecto (8 números + 1 letra)", "Error al insertar futbolista",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {

				// pintar de rojo esos campo vacíos
				pintarRojoCamposVacios(nombreJ, ape1J, ape2J, cole, alergias, dniJ, nombreT, ape1T, ape2T, dniT, tlf1,
						tlf2, email, dire, sexo, estado);

				// avisa de que no se ha insertado porque algún campo obligatorio está vacío
				JOptionPane.showMessageDialog(null, "Algún campo lo has dejado vacío", "Error al insertar futbolista",
						JOptionPane.ERROR_MESSAGE);
			}
		}

	}

	private void insertarJugXtut(String dniJ, String dniT, int parentesco) {
		PreparedStatement sentencia3;
		try {
			// al ser id autoincrement ya lo inserta solo. Se marca unicamente los campos
			// que queremos añadir
			sentencia3 = Principal.conexion
					.prepareStatement("insert into tutor (dniFutbolista, dniResponsable, idParentesco) values (?,?,?)");
			sentencia3.setString(1, dniJ);
			sentencia3.setString(2, dniT);
			sentencia3.setInt(3, parentesco);

			sentencia3.execute();

		} catch (SQLException e) {

			System.out.println("Error al insertar el tutor en la BD" + e);
		}
	}

	private void insertarTutorBD(String nombreT, String ape1T, String ape2T, String dniT, String tlf1, String email,
			String dire) {
		PreparedStatement sentencia2;
		try {

			sentencia2 = Principal.conexion.prepareStatement("insert into tutor values (?,?,?,?,?,?,?,?)");
			sentencia2.setString(1, dniT);
			sentencia2.setString(2, nombreT);
			sentencia2.setString(3, ape1T);
			sentencia2.setString(4, ape2T);
			sentencia2.setString(5, dire);
			sentencia2.setString(6, email);
			sentencia2.setString(7, tlf1);
			sentencia2.setString(8, tlf1);

			sentencia2.execute();

		} catch (SQLException e) {

			System.out.println("Error al insertar el tutor en la BD" + e);
		}
	}

	private void insertarJugadorBD(String urlImagen, String nombreJ, String ape1J, String ape2J, String cole,
			String alergias, String dniJ, String sexo, String estado, int lat, int pos, int cat, String lesiones,
			String caract, String fechaNac) {
		PreparedStatement sentencia1;
		try {

			sentencia1 = Principal.conexion
					.prepareStatement("insert into futbolista values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			sentencia1.setString(1, dniJ);
			sentencia1.setString(2, nombreJ);
			sentencia1.setString(3, ape1J);
			sentencia1.setString(4, ape2J);
			sentencia1.setString(5, sexo);
			sentencia1.setString(6, fechaNac);
			sentencia1.setString(7, cole);
			sentencia1.setString(8, alergias);
			sentencia1.setString(9, lesiones);
			sentencia1.setString(10, caract);
			sentencia1.setInt(11, lat);
			sentencia1.setInt(12, cat);
			sentencia1.setInt(13, pos);
			sentencia1.setString(14, estado);
			sentencia1.setString(15, urlImagen);

			sentencia1.execute();

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private String extraerRutaRelativaFoto() {
		String rutaRelativa = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(fileChooser);
		try {
			String rutaAbsoluta = fileChooser.getSelectedFile().getAbsolutePath();
			String nombreArchivo = fileChooser.getSelectedFile().getName();
			textField_foto.setText(rutaAbsoluta);
			rutaRelativa = "/imgJugador/" + nombreArchivo;

		} catch (NullPointerException e) {
			System.out.println("No se ha seleccionado ningún fichero");
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return rutaRelativa;
	}

	private void pintarRojoCamposVacios(String nombreJ, String ape1J, String ape2J, String cole, String alergias,
			String dniJ, String nombreT, String ape1T, String ape2T, String dniT, String tlf1, String tlf2,
			String email, String dire, String sexo, String estado) {
		if (nombreJ.isEmpty()) {
			textFieldNombre.setBackground(Color.red);
		}

		if (ape1J.isEmpty()) {
			textField_Apellido1.setBackground(Color.red);
		}

		if (ape2J.isEmpty()) {
			textField_apellido2.setBackground(Color.red);
		}

		if (cole.isEmpty()) {
			textFieldCole.setBackground(Color.red);
		}

		if (alergias.isEmpty()) {
			textFieldAlergias.setBackground(Color.red);
		}

		if (dniJ.isEmpty()) {
			textFieldDNI.setBackground(Color.red);
		}

		if (nombreT.isEmpty()) {
			textFieldNombreT.setBackground(Color.red);
		}

		if (ape1T.isEmpty()) {
			textFieldApellido1T.setBackground(Color.red);
		}

		if (ape2T.isEmpty()) {
			textFieldApellido2T.setBackground(Color.red);
		}

		if (dniT.isEmpty()) {
			textFieldDNIT.setBackground(Color.red);
		}

		if (tlf1.isEmpty()) {
			textField_tlf1.setBackground(Color.red);
		}

		if (tlf2.isEmpty()) {
			textField_tlf2.setBackground(Color.red);
		}

		if (email.isEmpty()) {
			textField_email.setBackground(Color.red);
		}

		if (dire.isEmpty()) {
			textField_dire.setBackground(Color.red);
		}

		if (sexo.isEmpty()) {
			comboBoxSexo.setBackground(Color.red);
		}

		if (estado.isEmpty()) {
			comboBoxEstado.setBackground(Color.red);
		}

	}

	// metodo que mira que la longitud sea de 9 digitos y que el ultimo sea una
	// letra
	public boolean validarDNI(String dni) {
		String letraMayus = "";
		if (dni.length() != 9 || Character.isLetter(dni.charAt(8)) == false) {
			return false;
		}
		letraMayus = (dni.substring(8)).toUpperCase();
		// si se cumple comprueba que los 8 primeros sean numeros y comprueba que la
		// letra sea la correcta
		if (soloNumeros(dni) == true && letraDNI(dni).equals(letraMayus)) {
			return true;
		} else {
			return false;
		}
	}

	// si el dni introducido tiene numeros en los 8 primeros caracteres devuelve
	// true
	private boolean soloNumeros(String dni) {
		int i, j = 0;
		String numero = "";
		String miDNI = "";
		String[] unoNueve = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

		for (i = 0; i < dni.length() - 1; i++) {
			numero = dni.substring(i, i + 1);
			for (j = 0; j < unoNueve.length; j++) {
				if (numero.equals(unoNueve[j])) {
					miDNI += unoNueve[j];
				}
			}
		}

		if (miDNI.length() != 8) {
			return false;
		} else {
			return true;
		}
	}

	// asgina la letra que corresponde al dni segun su numeración
	private String letraDNI(String dni) {
		int miDNI = Integer.parseInt(dni.substring(0, 8));
		int resto = 0;
		String miLetra = "";
		String[] asignacionLetra = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S",
				"Q", "V", "H", "L", "C", "K", "E" };
		resto = miDNI % 23;
		miLetra = asignacionLetra[resto];
		return miLetra;
	}
}
