package juego;
import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;


public class AstroShip extends InterfaceJuego{
    int x, y;
	double angulo;
	Image imgAstroship, imgAura;
	boolean aura;
	Entorno e;
    boolean astroAura = false;
	
	public AstroShip(Entorno e, double angulo) {
		this.x = 400;
		this.y = 500;
		this.e = e;
        this.angulo = 1/2*Math.PI;
		imgAstroship = Herramientas.cargarImagen("nave.png"); //ACÁ VA EL .PNG DE LA NAVE
		imgAura = Herramientas.cargarImagen("aura1.png");   //ACÁ VA EL .PNG DEL AURA (LAURA NO :v AURA)
	}

	//metodo para desplazar la nave
	public void desplazar(int d, double angulo) {
		this.x+= d;
		this.angulo+= angulo;
		
		if(this.angulo > 1.5) {
			this.angulo = 1.5;
		}
		if(this.angulo < -1.5) {
			this.angulo = -1.5;
		}
		if(this.x > 750) {
			this.x = 750;
		}
		if (this.x < 50) {
			this.x = 50;
		}	
	}
	
	//apunta para arriba la nave poniendo el angulo en 0
	public void apuntarArriba() {
		this.angulo = 0;
	}
	
    //pone una barra de vida sobre la nave
	public void vidaDeLaNave(boolean colision, int cont) {
		if(cont == 0) {
			Color colorLetra = new Color(0, 128, 0);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.dibujarRectangulo(this.x, this.y-50, 50, 5, 0, colorLetra); //<--!!!!hay que corregir la posicion segun la imagen de la nave!!!
			//e.escribirTexto("[|||||]", this.x-60, this.y - 50); <-    ESTA FUE LA PRIMER VERSION DE LAS VIDAS DE LA NAVE (ya no se usa)
		}
		if(colision && cont == 1) {
			Color colorLetra = new Color(0, 128, 0);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.dibujarRectangulo(this.x, this.y-50, 40, 5, 0, colorLetra);
			//e.escribirTexto("[||||]",  this.x-60, this.y - 50); <-    ESTA FUE LA PRIMER VERSION DE LAS VIDAS DE LA NAVE (ya no se usa)
		}
		if(colision && cont == 2) {
			Color colorLetra = new Color(255, 255, 0);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.dibujarRectangulo(this.x, this.y-50, 30, 5, 0, colorLetra);
			//e.escribirTexto("[|||]",  this.x-60, this.y - 50); <-    ESTA FUE LA PRIMER VERSION DE LAS VIDAS DE LA NAVE (ya no se usa)
		}
		if(colision && cont == 3) {
			Color colorLetra = new Color(255, 255, 0);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.dibujarRectangulo(this.x, this.y-50, 20, 5, 0, colorLetra);
			//e.escribirTexto("[||]",  this.x-60, this.y - 50); <-    ESTA FUE LA PRIMER VERSION DE LAS VIDAS DE LA NAVE (ya no se usa)
		}
		if(colision && cont == 4) {
			Color colorLetra = new Color(255, 0, 0);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.dibujarRectangulo(this.x, this.y-50, 10, 5, 0, colorLetra);
			//e.escribirTexto("[|]",  this.x-60, this.y - 50); <-    ESTA FUE LA PRIMER VERSION DE LAS VIDAS DE LA NAVE (ya no se usa)
		}
		if(colision && cont == 5) {
			Color colorLetra = new Color(255, 0, 0);
			e.cambiarFont("Verdana", 18, colorLetra);
			e.dibujarRectangulo(this.x, this.y-50, 1, 5, 0, colorLetra);
			//e.escribirTexto("[ CURATE!!! ]",  this.x-60, this.y - 50); <-    ESTA FUE LA PRIMER VERSION DE LAS VIDAS DE LA NAVE (ya no se usa)
		}
	}

    //devuelve booleano capturado por if en el código para saber si el aura está activada
    public boolean activarAura() {
		if(e.estaPresionada(e.TECLA_SHIFT)) {
			e.dibujarImagen(imgAura, x, y, angulo, 0.3);
			
			return astroAura = true;
		}else{
			return astroAura = false;
		}	
	}

    //metodo moverse llama a desplazar cuando se toca la tecla der o izq para mover la nave
    public void moverNave() {
		if(e.estaPresionada(e.TECLA_DERECHA)) {
			desplazar(7, 0.1);
		}
		//APRETAR TECLA IZQUIERDA
		else if(e.estaPresionada(e.TECLA_IZQUIERDA)) {
			desplazar(-7, -0.1);
		}else{
			apuntarArriba();
		}
	}
	
    //llama al dibujador para dibujar la nave en pantalla cargada en imgAstroship
	public void dibujarAstroship() {
		e.dibujarImagen(imgAstroship, x, y, angulo, 0.05);
	}

}
