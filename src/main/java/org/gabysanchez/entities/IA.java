package org.gabysanchez.entities;


import org.gabysanchez.entities.barcos.Barco;
import org.gabysanchez.entities.barcos.Posicion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IA extends Jugador {


    private Posicion barcoPosicion = Posicion.VERTICAL;
    private List<Casilla> casillasVetada = new ArrayList<>();



    public IA(int id) {
        super(id);
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
        for (Barco barco : getFlota().dameFlota()) {
            Random random = new Random();
            barco.setX(random.nextInt(10));
            barco.setY(random.nextInt(10));
            barco.setPosicion(Posicion.getRandomPosition());
            setBarco(barco);
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
    }
    public Casilla memoryAtac(){
        Random random = new Random();
        Casilla casilla = null;
        if (disparo!=null){
            barcoPosicion = disparo.getBarco().getPosicion();
            if (barcoPosicion.equals(Posicion.VERTICAL)&&disparo.getY()<9){
                casilla=getTableroAtaque().getCasillas()[disparo.getX()][disparo.getY()+1];
            }else if (barcoPosicion.equals(Posicion.HORIZONTAL)&&disparo.getX()<9){
                casilla=getTableroAtaque().getCasillas()[disparo.getX()+1][disparo.getY()];
            }
            for (int i = casilla.getX() - 1; i <= casilla.getX() + 1; i++) {
                if (i >= 0 && i <getTableroAtaque().getCasillas().length) {
                    for (int j = casilla.getY() - 1; j <= casilla.getY() + 1; j++) {
                        if (j >= 0 && j <getTableroAtaque().getCasillas().length) {
                            Casilla casilla2 = getTableroAtaque().getCasillas()[i][j];
                            casillasVetada.add(casilla2);
                        }
                    }
                }
            }

        }else {
            while (true){
                int x = random.nextInt(10);
                int y = random.nextInt(10);
                casilla = getTableroAtaque().getCasillas()[x][y];
                if (casilla.getEstado().equals(EstadoCasilla.AGUA)){
                    if (!casillasVetada.contains(casilla)){
                        break;
                    }
                }
            }
        }
        if (casilla.getBarco()!=null&&casilla.getBarco().getLongitud()>1){
            if (disparo==null){
                casilla = getTableroAtaque().getCasillas()[casilla.getBarco().getX()][casilla.getBarco().getY()];
            }
            disparo=casilla;
        }else {
            disparo = null;
        }
        return casilla;
    }
}
