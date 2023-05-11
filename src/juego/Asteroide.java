package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Asteroide {
    double x, y, velocidad, escala, imagenAngulo, direccion, tamanio;    
    Image imagen;
    Entorno e;
    boolean apareceCostado, arriba;
    
    public Asteroide(Entorno e){
        this.e = e;

        apareceCostado = Math.random() > 0.5;
        if(apareceCostado){
            boolean derecha = Math.random() > 0.5;
            if(derecha){
                this.direccion = Math.PI*5/4;
                this.x = e.ancho();
            }
            else{
                this.direccion = -Math.PI*1/4;
                this.x = 0;
            }
            this.y = Math.random()*e.alto()/3;
        }
        else{
            this.x = Math.random()*e.ancho();
            this.y = 0;
            this.direccion = Math.random() > 0.5 ? -Math.PI*3/4 : -Math.PI *1/4 ;  // -Math.PI*3/4 == -135°   -Math.PI*1/4 == -45°
        }

        this.velocidad = 2;
        this.escala = 0.2;
        this.imagenAngulo = 0;
        this.imagen = Herramientas.cargarImagen("asteroide.png");
        this.tamanio = imagen.getWidth(null) * escala;
    }

    public void dibujar(){
        e.dibujarImagen(imagen, x, y, imagenAngulo, escala);
    }

    public void mover(){
        this.x += velocidad*Math.cos(direccion);
        this.y -= velocidad*Math.sin(direccion);
        
    }

    public void cambiarDireccion(){
        this.direccion += Math.PI/2;
    }

    public boolean seFueDePantalla(){
        return !Funciones.estaEnRango(this.y, -25, e.alto()+25);
    }

    public boolean chocoBorde(){
        return !Funciones.estaEnRango(this.x, 0, e.ancho()) ;
    }

    public boolean colisiono(Proyectil misil){
        return Funciones.colisiono(x, y, tamanio, misil.x, misil.y, 0); // el tamanio es 0 porque la imagen de misil no esta encuadrada
    }

    
}
