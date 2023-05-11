package juego;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
    double x, y, velocidad, angulo, escala, tamanio;
	Image imgMisil;
	Entorno e;
	
	public Proyectil(Entorno e, double x, double y, double velocidad, double angulo) {
		this.x = x;
		this.y = y;
		this.e = e;
		this.velocidad = velocidad;
		this.angulo = angulo;
		this.imgMisil = Herramientas.cargarImagen("disparo.png");    //ACÁ VA EL .PNG DEL PROYECTIL
		this.escala = 0.1;
		this.tamanio = escala * imgMisil.getWidth(null);
	}
	
    //hace avanzar el proyectil sobre el eje y
	public void avanzar() {
		this.y -= this.velocidad;
	}

    //llama al dibujador para dibujar la nave en pantalla cargada en imgMisil
    public void dibujarMisil() {
		e.dibujarImagen(imgMisil, x, y, angulo, escala);
	}

	public boolean estaEnPantalla(){
		return Funciones.estaEnPantalla(e, x, y);
	}

}
