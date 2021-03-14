package org.gabysanchez.entities;


import org.gabysanchez.application.Controller;
import org.gabysanchez.entities.barcos.Barco;
import org.gabysanchez.service.ServicioColocarBarco;
import org.gabysanchez.ui.tablero.EstadoUiTablero;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Jugador {
    protected FlotaBulider flota;
    protected List<Barco> barcos;
    private Tablero tableroDefensa;
    private Tablero tableroAtaque;
    private String nombre;
    private int id;
    private Integer nBarcos;
    protected HashMap<Class<? extends Barco>,List<Barco>> mapBarcos;
    protected Barco nextSelectedBarco;
    protected Casilla disparo = null;


    public Jugador(int id) {
        this.flota = new FlotaBulider();
        this.tableroDefensa = new Tablero();
        this.tableroAtaque = new Tablero();
        this.id=id;
        this.mapBarcos=new HashMap<>();
        this.barcos= new ArrayList<>(Arrays.asList(flota.dameFlota()));
        this.nextSelectedBarco = flota.dameFlota()[0];
        this.nBarcos = flota.dameFlota().length;
    }

    public int getId() {
        return id;
    }

    public HashMap<Class<? extends Barco>, List<Barco>> getMapBarcos() {
        return mapBarcos;
    }

    public FlotaBulider getFlota() {
        return flota;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tablero getTableroDefensa() {
        return tableroDefensa;
    }

    public Tablero getTableroAtaque() {
        return tableroAtaque;
    }

    public List<Barco> getBarcos() {
        return barcos;
    }

    public void addBarcos(){
        for (Barco barco: flota.dameFlota()){
            Class<? extends Barco> clase = barco.getClass();
            if (!mapBarcos.containsKey(clase)){
                List<Barco> tipoBarco = new ArrayList<>();
                tipoBarco.add(barco);
                mapBarcos.put(barco.getClass(),tipoBarco);
            }else {
                List<Barco> tipoBarco = mapBarcos.get(clase);
                tipoBarco.add(barco);
                mapBarcos.put(clase,tipoBarco);
            }
        }
    }

    public void removeBarco(Barco barco){
        Boolean todosBarcos = true;
        Class<? extends Barco> clase = barco.getClass();
        List<Barco> tipoBarco = mapBarcos.get(clase);
        tipoBarco.remove(0);
        if (tipoBarco.size()>0){
            nextSelectedBarco = tipoBarco.get(0);
        }
        else {
             for (Class<? extends Barco> key : mapBarcos.keySet()){
                 if (mapBarcos.get(key).size()>0){
                     tipoBarco=mapBarcos.get(key);
                     nextSelectedBarco=tipoBarco.get(0);
                     todosBarcos = true;
                     break;
                 }else {
                     nextSelectedBarco=null;
                     todosBarcos = false;
                 }
             }
        }
        if (nBarcos==0){
            try {
                Controller.getInstance().getScene().getUiTableroDefensa().setEstado(EstadoUiTablero.VIEW);
                Controller.getInstance().combate();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public Barco getNextSelectedBarco() {
        return nextSelectedBarco;
    }

    public void setNextSelectedBarco(Barco nextSelectedBarco) {
        this.nextSelectedBarco = nextSelectedBarco;
    }

    public void setBarco(Barco barco){
        ServicioColocarBarco.getInstance().colocarBarco(barco,this);
    }


    public Integer getnBarcos() {
        return nBarcos;
    }

    public void setnBarcos(Integer nBarcos) {
        this.nBarcos = nBarcos;
    }

    public void setTableroAtaque(Tablero tableroAtaque) {
        this.tableroAtaque = tableroAtaque;
    }
    public Casilla memoryAtac(){
        return null;
    }

    public void setTableroDefensa(Tablero tableroDefensa) {
        this.tableroDefensa = tableroDefensa;
    }

    public Casilla getDisparo() {
        return disparo;
    }
    /*
    public  void dibujar(){
        // ServicioDibujarBarco sc = new ServicioDibujarBarco();
        ServicioDibujar.getInstance().dibujarFlota(this);
    }


     */
}
