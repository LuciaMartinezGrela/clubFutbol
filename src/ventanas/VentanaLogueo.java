package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import base.Principal;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JSeparator;

public class VentanaLogueo extends JFrame implements ActionListener {

	private JPanel contentPane;
	private static JTextField textField;
	private static JPasswordField passwordField;
	private static JButton btnEntrar;

	/**
	 * Launch the application.
	 * 
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { VentanaLogueo frame = new
	 * VentanaLogueo(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 * 
	 * /** Create the frame.
	 */
	public VentanaLogueo() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogueo.class.getResource("/imagenes/IconoBalon.png")));
		setBackground(Color.WHITE);
		setTitle("Unión Campestre F.C.");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 450, 300);
		setSize(586, 300);// tamaño de la ventana
		setLocationRelativeTo(null);// centramos la ventana en la pantalla
		// setIconImage = new ImageIcon;
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("INICIAR SESIÓN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(265, 22, 142, 30);
		contentPane.add(lblNewLabel);

		JLabel lblUsuarioUc = new JLabel("Usuario UC: ");
		lblUsuarioUc.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsuarioUc.setBounds(217, 87, 89, 13);
		contentPane.add(lblUsuarioUc);

		JLabel lblContrasea = new JLabel("Contraseña: ");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblContrasea.setBounds(217, 122, 104, 13);
		contentPane.add(lblContrasea);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(376, 84, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(376, 119, 95, 19);
		// le añadimos al botón el escuchador de teclado
		passwordField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					iniciarSesion();
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

		});
		contentPane.add(passwordField);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setMnemonic('e');
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEntrar.setBounds(287, 205, 85, 21);
		btnEntrar.setBackground(new Color(221, 28, 28));// color personalizado RojoCampestre
		btnEntrar.setForeground(Color.white);
		contentPane.add(btnEntrar);
		btnEntrar.addActionListener(this);

		JLabel lblNewLabel_escudo = new JLabel("New label");
		ImageIcon escudo = new ImageIcon("src/imagenes/escudo100x122.png");

		lblNewLabel_escudo.setBounds(43, 55, 116, 136);

		lblNewLabel_escudo.setIcon(new ImageIcon(escudo.getImage().getScaledInstance(lblNewLabel_escudo.getWidth(),
				lblNewLabel_escudo.getHeight(), Image.SCALE_SMOOTH)));
		contentPane.add(lblNewLabel_escudo);

		JSeparator separator = new JSeparator();
		separator.setBounds(386, 122, 1, 2);
		contentPane.add(separator);

	}

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnEntrar) {
			System.out.println("Has pulsado el boton de entrar del login");
			// si usuario y contraseña son correctos entonces muestra la pantalla principal
			// de la app

			iniciarSesion();

		}
	}
	
	public void iniciarSesion() {

		char[] clave = passwordField.getPassword();
		String claveFinal = new String(clave);
		String usuario = null;
		String contrasena = null;
		String tipoUser = null;
		String userIntroducido = textField.getText();
		if (!userIntroducido.isEmpty() && !claveFinal.isEmpty()) {

			try {
				Statement sentencia = Principal.conexion.createStatement();
				ResultSet resultado = sentencia.executeQuery(
						"select usuario, clave, tipoUser from rolesUsuarios where usuario='" + userIntroducido + "';");

				while (resultado.next()) {
					usuario = resultado.getString(1);
					contrasena = resultado.getString(2);
					tipoUser = resultado.getString(3);

				}

			} catch (SQLException e) {
				System.out.println("Error al realizar la conexión con la base de datos" + e);
			}

			if (userIntroducido.contentEquals(usuario) && claveFinal.equals(contrasena)) {
				Principal.ventanaLogin.dispose();

				VentanaPrincipal v1 = new VentanaPrincipal(tipoUser);
				// abrir ventana para introducir dniº
				v1.setVisible(true);

				// si algo es incorrecto muestra ventana de error, borra lo escrito y fija el
				// cursor en el texfield usuario
			} else {
				JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos", "Error de inicio de sesión",
						JOptionPane.ERROR_MESSAGE);
				textField.setText("");
				passwordField.setText("");
				textField.requestFocus();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Usuario o contraseña están vacíos", "Error de inicio de sesión",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
