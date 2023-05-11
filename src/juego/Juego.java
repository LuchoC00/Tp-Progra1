package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno e;
	Image fondo;
	Asteroide [] asteroides;

	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.e = new Entorno(this, "Lost Galaxian - Grupo 3 - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		fondo = Herramientas.cargarImagen("fondo.jpeg");
		asteroides = new Asteroide[5];
		// Inicia el juego!
		this.e.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y por lo
	 * tanto es el método más importante de esta clase. Aquí se debe actualizar el
	 * estado interno del juego para simular el paso del tiempo (ver el enunciado
	 * del TP para mayor detalle).
	 */
	public void tick() {

		e.dibujarImagen(fondo, e.ancho()/2, e.alto()/2, 0, 0.5);

		for (int i = 0; i < asteroides.length; i++) {
			if(asteroides[i] != null){
				asteroides[i].dibujar();
				asteroides[i].mover();
				if(asteroides[i].seFueDePantalla()){
					asteroides[i] = null;
				}
				else if(asteroides[i].chocoBorde()){
					asteroides[i].cambiarDireccion();
				}
			}
			else{
				asteroides[i] = new Asteroide(e);
			}
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
