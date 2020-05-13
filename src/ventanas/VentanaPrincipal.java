package ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import base.BtnPersonalizado;
import base.Principal;
import modelos.ObjetoComboBox;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaPrincipal extends JFrame implements ActionListener {

	private JPanel contentPane;
	JMenuItem MenuItemBuscarFutbolista;
	JMenuItem MenuItemInsertarFutbolista;
	JMenuItem MenuItemModificarFutbolista;
	JMenuItem MenuItemBorrarFutbolista;
	JMenuItem menuItemBuscarTutor;
	JMenuItem menuItemInsertarTutor;
	JMenuItem menuItemModificarTutor;
	JMenuItem menuItemBorrarTutor;
	JMenuItem menuItemBuscarEntrenamiento;
	JMenuItem menuItemInsertarEntrenamiento;
	JMenuItem menuItemModifEntrenamiento;
	JMenuItem menuItemBorrarEntrenamiento;
	JMenuItem menuItemBuscarCat;
	JMenuItem menuItemInsertarCat;
	JMenuItem menuItemModifCat;
	JMenuItem menuItemBorrarCat;
	JMenu menuOtros;

	static BtnPersonalizado btnNuevoFut;
	static BtnPersonalizado btnBuscarCategoria;

	// FondoPanel fondo = new FondoPanel();

	public Image imagenFondo;
	public URL fondo;

	Image img;
	Icon iconobtn1;

	/**
	 * Create the frame.
	 */
	/**
	 * 
	 */
	public JPanel panel = new JPanel() {
		public void paint(Graphics g) {

			g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}
	};
	private JMenu MenuParen;
	private JMenuItem menuItemInsertarParen;
	private JMenuItem menuItemBorrarParen;
	private JMenu MenuPos;
	private JMenuItem menuItemInsertarPos;
	private JMenuItem menuItemBorrarPos;
	private JMenu MenuLat;
	private JMenuItem menuItemInsertarLat;
	private JMenuItem menuItemBorrarLat;

	public VentanaPrincipal(String tipoUser) {

		setTitle("Base de datos Unión Campestre F.C.");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogueo.class.getResource("/imagenes/IconoBalon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1153, 815);
		setLocationRelativeTo(null);
		// this.setContentPane(fondo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// fondo de ventana
		fondo = this.getClass().getResource("/imagenes/fondoRojo.jpg");
		imagenFondo = new ImageIcon(fondo).getImage();
		Container contenedor = getContentPane();
		contenedor.add(panel);
		panel.setLayout(null);

		// botones
		btnNuevoFut = new BtnPersonalizado();
		btnNuevoFut.setBounds(417, 172, 303, 66);
		btnNuevoFut.setBorderPainted(false);
		btnNuevoFut.setContentAreaFilled(false);
		btnNuevoFut.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		try {
			img = ImageIO.read(getClass().getResource("/imagenes/button_nuevo-futbolista (3).png"));
			btnNuevoFut.setIcon(new ImageIcon(img));
		} catch (IOException e) {

			System.out.println("Error al cargar la imagen del boton nuevo futbolista");
		}
		btnNuevoFut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNuevoFut.addActionListener(this);
		panel.add(btnNuevoFut);

		btnBuscarCategoria = new BtnPersonalizado();
		btnBuscarCategoria.setBounds(417, 251, 303, 66);
		btnBuscarCategoria.setBorderPainted(false);
		btnBuscarCategoria.setContentAreaFilled(false);
		btnBuscarCategoria.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		try {
			img = ImageIO.read(getClass().getResource("/imagenes/button_buscar-categoria.png"));
			btnBuscarCategoria.setIcon(new ImageIcon(img));
		} catch (IOException e) {

			System.out.println("Error al cargar la imagen del boton buscar categoria");
		}
		btnBuscarCategoria.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscarCategoria.addActionListener(this);

		panel.add(btnBuscarCategoria);

		// barra de menú
		JMenuBar menuBar = new JMenuBar();
		contentPane.add(menuBar, BorderLayout.NORTH);

		JMenu MenuFutbolistas = new JMenu("  Futbolistas   ");
		MenuFutbolistas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/player70.png")));
		MenuFutbolistas.setFont(new Font("Verdana", Font.BOLD, 18));
		MenuFutbolistas.setHorizontalAlignment(SwingConstants.CENTER);
		MenuFutbolistas.setMnemonic('f');
		menuBar.add(MenuFutbolistas);

		MenuItemBuscarFutbolista = new JMenuItem("Buscar");
		MenuItemBuscarFutbolista.setHorizontalAlignment(SwingConstants.LEFT);
		MenuItemBuscarFutbolista.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/buscar2.png")));
		MenuItemBuscarFutbolista.setFont(new Font("Verdana", Font.PLAIN, 18));
		MenuFutbolistas.add(MenuItemBuscarFutbolista);

		MenuItemInsertarFutbolista = new JMenuItem("Insertar");
		MenuItemInsertarFutbolista.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/agregar2.png")));
		MenuItemInsertarFutbolista.setFont(new Font("Verdana", Font.PLAIN, 20));
		MenuFutbolistas.add(MenuItemInsertarFutbolista);

		MenuItemModificarFutbolista = new JMenuItem("Modificar");
		MenuItemModificarFutbolista.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/pencil.png")));
		MenuItemModificarFutbolista.setFont(new Font("Verdana", Font.PLAIN, 20));
		MenuFutbolistas.add(MenuItemModificarFutbolista);

		MenuItemBorrarFutbolista = new JMenuItem("Borrar");
		MenuItemBorrarFutbolista.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/borrar2.png")));
		MenuItemBorrarFutbolista.setFont(new Font("Verdana", Font.PLAIN, 20));
		MenuFutbolistas.add(MenuItemBorrarFutbolista);

		JMenu MenuTutor = new JMenu("   Tutor      ");
		MenuTutor.setHorizontalAlignment(SwingConstants.CENTER);
		MenuTutor.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/mother70.png")));
		MenuTutor.setFont(new Font("Verdana", Font.BOLD, 18));
		MenuTutor.setMnemonic('t');
		menuBar.add(MenuTutor);

		menuItemBuscarTutor = new JMenuItem("Buscar");
		menuItemBuscarTutor.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/buscar2.png")));
		menuItemBuscarTutor.setFont(new Font("Verdana", Font.PLAIN, 18));
		MenuTutor.add(menuItemBuscarTutor);

		menuItemInsertarTutor = new JMenuItem("Insertar");
		menuItemInsertarTutor.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/agregar2.png")));
		menuItemInsertarTutor.setFont(new Font("Verdana", Font.PLAIN, 18));
		MenuTutor.add(menuItemInsertarTutor);

		menuItemModificarTutor = new JMenuItem("Modificar");
		menuItemModificarTutor.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/pencil.png")));
		menuItemModificarTutor.setFont(new Font("Verdana", Font.PLAIN, 18));
		MenuTutor.add(menuItemModificarTutor);

		menuItemBorrarTutor = new JMenuItem("Borrar");
		menuItemBorrarTutor.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/borrar2.png")));
		menuItemBorrarTutor.setFont(new Font("Verdana", Font.PLAIN, 18));
		MenuTutor.add(menuItemBorrarTutor);

		JMenu MenuEntrenamientos = new JMenu("Entrenamientos     ");
		MenuEntrenamientos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/trainer70.png")));
		MenuEntrenamientos.setFont(new Font("Verdana", Font.BOLD, 18));
		MenuEntrenamientos.setMnemonic('e');
		menuBar.add(MenuEntrenamientos);

		menuItemBuscarEntrenamiento = new JMenuItem("Buscar");
		menuItemBuscarEntrenamiento.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/buscar2.png")));
		menuItemBuscarEntrenamiento.setFont(new Font("Verdana", Font.PLAIN, 18));
		MenuEntrenamientos.add(menuItemBuscarEntrenamiento);

		menuItemInsertarEntrenamiento = new JMenuItem("Insertar");
		menuItemInsertarEntrenamiento
				.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/agregar2.png")));
		menuItemInsertarEntrenamiento.setFont(new Font("Verdana", Font.PLAIN, 18));
		MenuEntrenamientos.add(menuItemInsertarEntrenamiento);

		menuItemModifEntrenamiento = new JMenuItem("Modificar");
		menuItemModifEntrenamiento.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/pencil.png")));
		menuItemModifEntrenamiento.setFont(new Font("Verdana", Font.PLAIN, 18));
		MenuEntrenamientos.add(menuItemModifEntrenamiento);

		menuItemBorrarEntrenamiento = new JMenuItem("Borrar");
		menuItemBorrarEntrenamiento.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/borrar2.png")));
		menuItemBorrarEntrenamiento.setFont(new Font("Verdana", Font.PLAIN, 18));
		MenuEntrenamientos.add(menuItemBorrarEntrenamiento);

		JMenu menuCat = new JMenu("  Categorías   ");
		menuCat.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/categoria70.png")));
		menuCat.setFont(new Font("Verdana", Font.BOLD, 18));
		menuBar.add(menuCat);
		menuCat.setMnemonic('c');

		menuItemBuscarCat = new JMenuItem("Buscar");
		menuItemBuscarCat.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/buscar2.png")));
		menuItemBuscarCat.setFont(new Font("Verdana", Font.PLAIN, 20));
		menuCat.add(menuItemBuscarCat);

		menuItemInsertarCat = new JMenuItem("Insertar");
		menuItemInsertarCat.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/agregar2.png")));
		menuItemInsertarCat.setFont(new Font("Verdana", Font.PLAIN, 20));
		menuCat.add(menuItemInsertarCat);

		menuItemModifCat = new JMenuItem("Modificar");
		menuItemModifCat.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/pencil.png")));
		menuItemModifCat.setFont(new Font("Verdana", Font.PLAIN, 20));
		menuCat.add(menuItemModifCat);

		menuItemBorrarCat = new JMenuItem("Borrar");
		menuItemBorrarCat.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/borrar2.png")));
		menuItemBorrarCat.setFont(new Font("Verdana", Font.PLAIN, 20));
		menuCat.add(menuItemBorrarCat);

		menuOtros = new JMenu("   Otros          ");
		menuOtros.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/imagenes/extra-time70.png")));
		menuOtros.setFont(new Font("Verdana", Font.BOLD, 18));
		menuOtros.setMnemonic('o');
		menuBar.add(menuOtros);

		MenuParen = new JMenu("Parentesco");
		MenuParen.setFont(new Font("Verdana", Font.PLAIN, 20));
		menuOtros.add(MenuParen);

		menuItemInsertarParen = new JMenuItem("Insertar");
		menuItemInsertarParen.setFont(new Font("Verdana", Font.PLAIN, 20));
		MenuParen.add(menuItemInsertarParen);

		menuItemBorrarParen = new JMenuItem("Borrar");
		menuItemBorrarParen.setFont(new Font("Verdana", Font.PLAIN, 20));
		MenuParen.add(menuItemBorrarParen);

		MenuPos = new JMenu("Posiciones");
		MenuPos.setFont(new Font("Verdana", Font.PLAIN, 20));
		menuOtros.add(MenuPos);

		menuItemInsertarPos = new JMenuItem("Insertar");
		menuItemInsertarPos.setFont(new Font("Verdana", Font.PLAIN, 20));
		MenuPos.add(menuItemInsertarPos);

		menuItemBorrarPos = new JMenuItem("Borrar");
		menuItemBorrarPos.setFont(new Font("Verdana", Font.PLAIN, 20));
		MenuPos.add(menuItemBorrarPos);

		MenuLat = new JMenu("Lateralidad");
		MenuLat.setFont(new Font("Verdana", Font.PLAIN, 20));
		menuOtros.add(MenuLat);

		menuItemInsertarLat = new JMenuItem("Insertar");
		menuItemInsertarLat.setFont(new Font("Verdana", Font.PLAIN, 20));
		MenuLat.add(menuItemInsertarLat);

		menuItemBorrarLat = new JMenuItem("Borrar");
		menuItemBorrarLat.setFont(new Font("Verdana", Font.PLAIN, 20));
		MenuLat.add(menuItemBorrarLat);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);

		if (tipoUser.contentEquals("usuario")) {
			MenuItemBorrarFutbolista.setVisible(false);
			MenuItemInsertarFutbolista.setVisible(false);
			MenuItemModificarFutbolista.setVisible(false);

			menuItemInsertarTutor.setVisible(false);
			menuItemModificarTutor.setVisible(false);
			menuItemBorrarTutor.setVisible(false);

			menuItemInsertarEntrenamiento.setVisible(false);
			menuItemModifEntrenamiento.setVisible(false);
			menuItemBorrarEntrenamiento.setVisible(false);

			menuItemInsertarCat.setVisible(false);
			menuItemModifCat.setVisible(false);
			menuItemBorrarCat.setVisible(false);

			menuOtros.setVisible(false);

		}

	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		VentanaBuscarPorCat v2;
		VentanaInsertarFut v3;
		System.out.println("ha entrado en el evento");
		if (evento.getSource() == this.btnBuscarCategoria) {
			System.out.println("Has pulsado el boton de buscar por categoría");
			// abrir ventana nueva
			// que muestre un jcombo box para que el usuario seleccione una opcion

			v2 = new VentanaBuscarPorCat();
			rellenarComboBoxCat(v2);
			v2.setVisible(true);
		}

		if (evento.getSource() == this.btnNuevoFut) {
			System.out.println("Has pulsado el boton de nuevo futbolista");
			// abrir ventana nueva
			// que muestre un jcombo box para que el usuario seleccione una opcion

			v3 = new VentanaInsertarFut();
			rellenarComboBoxsInsertarJug(v3);
			v3.setVisible(true);
		}
	}

	private void rellenarComboBoxCat(VentanaBuscarPorCat v2) {
		try {
			Statement sentencia = Principal.conexion.createStatement();
			ResultSet resultado = sentencia.executeQuery("SELECT nombreCat FROM categorias");
			while (resultado.next()) {

				v2.comboBox.addItem(resultado.getString(1));
			}
		} catch (SQLException e1) {
			System.out.println("Error al cargar el comboBox de las categoría" + e1);
		}
	}

	private void rellenarComboBoxsInsertarJug(VentanaInsertarFut v3) {
		try {
			Statement sentencia = Principal.conexion.createStatement();
			ResultSet resultado = sentencia.executeQuery("SELECT predominio FROM lateralidad");
			while (resultado.next()) {

				v3.comboBoxLat.addItem(resultado.getString(1));
			}
			
			
			ResultSet resultado2=sentencia.executeQuery("SELECT nombreCat, idCat FROM categorias ");
			while (resultado2.next()) {
				
				v3.comboBoxCat.addItem(new ObjetoComboBox (resultado2.getString(1),resultado2.getInt(2)));
			}
			
			ResultSet resultado3=sentencia.executeQuery("SELECT nombrePos FROM posiciones ");
			while (resultado3.next()) {

				v3.comboBoxPos.addItem(resultado3.getString(1));
			}
			
			ResultSet resultado4=sentencia.executeQuery("SELECT tipo FROM parentesco ");
			while (resultado4.next()) {

				v3.comboBox_parentesco.addItem(resultado4.getString(1));
			}
			
		} catch (SQLException e1) {
			System.out.println("Error al cargar el comboBoxs" + e1);
		}
	}
}
