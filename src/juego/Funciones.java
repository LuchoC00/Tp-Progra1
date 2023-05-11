package juego;

import entorno.Entorno;

public class Funciones {

    public static boolean estaEnPantalla(Entorno e, double x, double y){
        boolean estaEnX = estaEnRango(x,0,e.ancho());
        boolean estaEnY = estaEnRango(y,0,e.ancho());
        return estaEnX && estaEnY;
    }

    public static boolean estaEnRango(double valor, double desde, double hasta) {
        return desde <= valor && valor <= hasta;
    }

    public static boolean colisiono(double x1, double y1, double tamanio1, double x2, double y2, double tamanio2){
        return (x1-x2)*(x1-x2)+(y1-y2)*(y1-y2) <= (tamanio1/2 + tamanio2/2) * (tamanio1/2 + tamanio2/2);
    }
}
