package base;

import java.sql.*;

import javax.swing.UIManager;

import mdlaf.MaterialLookAndFeel;
import mdlaf.themes.MaterialOceanicTheme;
import ventanas.VentanaLogueo;
/*
 * @autor Lucia  Martinez
 * @version 1.0
 * */
public class Principal {
	public static Connection conexion;
	public static VentanaLogueo ventanaLogin;
	public static void main(String[] args) {
		if (UIManager.getLookAndFeel() instanceof MaterialLookAndFeel){
		     MaterialLookAndFeel.changeTheme(new MaterialOceanicTheme());
		 }

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager
					.getConnection("jdbc:mysql://localhost/clubfutbol?serverTimezone=UTC", "root", "chios");
			System.out.println("Se ha realizado la conexión");
			/*VentanaPrincipal frame = new VentanaPrincipal();
			frame.setVisible(true);*/
			
			ventanaLogin = new VentanaLogueo();
			ventanaLogin.setVisible(true);
			
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		
	}

}
