package modelos;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class BtnPersonalizado extends JButton {

	private float opacity = 0.5f;
	// dos contructores

	public BtnPersonalizado() {
		super();
		addMouseListener(new EventoBoton());
	}

	public BtnPersonalizado(String texto) {
		super(texto);
	}

//get y set
	public float getOpacity() {
		return opacity;
		
	}

	public void setOpacity(float opacity) {
		this.opacity = opacity;
		repaint();
	}

	// sobreescribir clase más abstracta que jbutton

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
		super.paintComponent(g2);
	}

	public class EventoBoton extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			efectoHover(1f, 0.8f, 0.03f, 10, false);
		}

		// Los parametros hacen referencia a:
		//opacidad del boton, 
		//hasta que opacidad quiero que llegue,
		//cuanto quiero que se le sume de la primera opacidad a la segunda en cada interacción,
		//milisegundos que se demora el efecto, 
		//true=sumar valores
		@Override
		public void mouseEntered(MouseEvent e) {
			efectoHover(0.5f, 1f, 0.03f, 10, true);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			efectoHover(1f, 0.8f, 0.03f, 10, false);
		}

		private void efectoHover(float index, float range, float cont, int sleep, boolean event) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					//operadores ternarios
					//si i es menor que el rango, si es verdadero i será menor que rango si i es falso i es mayor que rango
					for (float i = index; (event) ? i <= range : i >= range; i = (event) ? i + cont : i - cont) {
						setOpacity(i);

						try {
							Thread.sleep(sleep);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							System.out.println("Error en la hover del raton");
						}
					}

				}

			}).start();
		}

	}

}
