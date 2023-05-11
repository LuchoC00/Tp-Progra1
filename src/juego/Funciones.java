package juego;

import entorno.Entorno;

public class Funciones {
    
    public static void dibujar(){
        System.err.println("hola");
    }

    public static boolean estaEnPantalla(Entorno e, double x, double y){
        boolean estaEnX = estaEnRango(x,0,e.ancho());
        boolean estaEnY = estaEnRango(y,0,e.ancho());
        return estaEnX && estaEnY;
    }

    public static boolean estaEnRango(double valor, double desde, double hasta) {
        return desde <= valor && valor <= hasta;
    }
}
