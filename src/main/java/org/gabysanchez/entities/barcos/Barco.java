package org.gabysanchez.entities.barcos;


public class Barco {
    private Integer x;
    private Integer y;
    private ParteBarco [] partes;
    private Integer puntos;
    private int longitud;
    private Posicion posicion;

    public Barco(int x, int y, Posicion _posicion, int longitud) {
        this.x=x;
        this.y=y;
        this.puntos = longitud;
        this.longitud = longitud;
        this.partes = new ParteBarco[longitud];
        this.posicion = _posicion;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public ParteBarco[] getPartes() {
        return partes;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void definirPartes(){
        if (posicion== Posicion.VERTICAL){
            for (int i=0;i<longitud;i++){
                partes[i] = new ParteBarco(x,y+i);
            }
        }else {
            for (int i=0;i<longitud;i++){
                partes[i] = new ParteBarco(x+i,y);
            }
        }
    }

    public int getLongitud() {
        return longitud;
    }
}
