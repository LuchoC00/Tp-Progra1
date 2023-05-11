package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Proyectil extends InterfaceJuego{
    double x, y, velocidad, angulo;
	Image imgMisil;
	Entorno e;
	
	public Proyectil(Entorno e, double x, double y, double velocidad, double angulo) {
		this.x = x;
		this.y = y;
		this.e = e;
		this.velocidad = velocidad;
		this.angulo = angulo;
		imgMisil = Herramientas.cargarImagen("cohete2.png");    //ACÁ VA EL .PNG DEL PROYECTIL
	}
	
    //hace avanzar el proyectil sobre el eje y
	public void avanzar() {
		this.y -= this.velocidad;
	}

    //llama al dibujador para dibujar la nave en pantalla cargada en imgMisil
    public void dibujarMisil() {
		e.dibujarImagen(imgMisil, x, y, angulo, 0.1);
	}

}
