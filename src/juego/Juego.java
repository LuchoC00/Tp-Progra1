package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego {
	// El objeto Entorno que controla el tiempo y otros
	private Entorno e;
	Image fondo;
	Asteroide [] asteroides;
	AstroShip astroship;
	Proyectil misil;
	int cont = 0;
	int score = 0;
	// Variables y métodos propios de cada grupo
	// ...

	Juego() {
		// Inicializa el objeto entorno
		this.e = new Entorno(this, "Lost Galaxian - Grupo 3 - v1", 800, 600);
		
		// Inicializar lo que haga falta para el juego
		// ...
		fondo = Herramientas.cargarImagen("fondo.jpeg");
		asteroides = new Asteroide[5];
		astroship = new AstroShip(e, 0);
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

		astroship.vidaDeLaNave(colision, cont);		//faltan las colisiones
		astroship.moverNave();
		dibujarTodo();			//para dibujar todo dentro de un metodo tienen qe ponerlo dentro del for
		dispararNave();			//DISPAROS DE LA NAVE
		misilFueraDePantalla();	


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
				else if(misil != null && asteroides[i].colisiono(misil)){
					asteroides[i] = null;
					misil = null;

					score++;		//esto es parte de la vida de la nave y el score
					if(cont > 0) {	//" "
						cont--;		//" "
					}				//" "
				}
			}
			else{
				asteroides[i] = new Asteroide(e);
			}

			todasLasColisiones(i);	//COLISIONES
		}

	}

	
//====================METODOS DE JUEGO===========================
	public void dispararNave() {				//DISPAROS DE LA NAVE
		//APRETAR TECLA ESPACIO
		if(misil == null && e.estaPresionada(e.TECLA_ESPACIO)) {
			misil = new Proyectil(this.e, this.astroship.x, this.astroship.y, 10, 2);
			Herramientas.cargarSonido("lazer2.wav").start();
		}
	}

	public void dibujarTodo() {	//si lo meten en el for creen los destructores destructor[i].dibujarDestructor(); acá adentro y agreguen public void dibujarTodo(i)
		astroship.dibujarAstroship();
	}

	public void misilFueraDePantalla() {		//DISPAROS DE LA NAVE SE VAN DE PANTALLA
		//MISIL SALE DE PANTALLA
		if(misil != null && misil.y < 0 ) {		
			misil = null;
		}
	}

	public void todasLasColisiones(int i) {		//<- esto lo puse dentro del for para dibujar todo junto dentro de un método fuera del tick pero se puede cambiar
		colisionesAstroship(i);
	}

	public void colisionesAstroship(int i) {	//COLISIONES DE ASTROSHIP
		//COLISION DE ASTROSHIP CON DISPAROS DE DESTRUCTORES	"NO HACE DAÑO Y BAJA SCORE"
		if(astroship.activarAura() == false && this.misilEnemigo != null && astroship != null && colision(this.misilEnemigo.x, this.misilEnemigo.y, this.astroship.x, this.astroship.y, 50)) {
			this.misilEnemigo = null;
			misilEnemigo = new ProyectilEnemigo(this.entorno, this.destructor[i].x, this.destructor[i].y, 0, this.destructor[i].velocidad);
			score--;
			colision = true;
			cont++;
			astroship.vidaDeLaNave(colision, cont);
		}	//misilEnemigo = Ion, score = puntos, destructor = nave enemiga eso hay qe cambiarlo cuando hagan esas clases por los respectivos valores

	public boolean controlarPuntos() {			//CONTROLAR PUNTOS DEVUELVE UN BOOLEANO TRUE IF SCORE=3000 SINO FALSE
		if(score == 3000) {						// Score ++ debe estar puesto en colisiones de disparos etc
			Color colorLetra = new Color(64, 64, 64);
			Color colorLetra2 = new Color(0, 0, 0);
			double x = 400;
			double y = 570; 
			double ancho = 120;
			double alto = 20;
			double angulo = 0; 
			Color colorBorde = new Color(255, 255, 255);
			Color colorRelleno = new Color(224, 224, 224);		
			e.dibujarRectangulo(x, y, ancho, alto, angulo, colorBorde);
			e.dibujarRectangulo(x + 1, y + 1, ancho - 1, alto - 1, angulo, colorRelleno);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.escribirTexto("WIN" + score,  351, 578);
			e.cambiarFont("Verdana", 18, colorLetra2);
			e.escribirTexto("WIN" + score,  350, 579);
			return  true;	
		}else{
			
			Color colorLetra = new Color(64, 64, 64);
			Color colorLetra2 = new Color(0, 0, 0);
			double x = 400;
			double y = 570; 
			double ancho = 120;
			double alto = 20;
			double angulo = 0; 
			Color colorBorde = new Color(255, 255, 255);
			Color colorRelleno = new Color(224, 224, 224);		
			e.dibujarRectangulo(x, y, ancho, alto, angulo, colorBorde);
			e.dibujarRectangulo(x + 1, y + 1, ancho - 1, alto - 1, angulo, colorRelleno);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.escribirTexto("SCORE:" + score,  351, 578);
			e.cambiarFont("Verdana", 18, colorLetra2);
			e.escribirTexto("SCORE:" + score,  350, 579);
			return false;
		}
	}

	public static void main(String[] args) {
		Juego juego = new Juego();
	}
}
