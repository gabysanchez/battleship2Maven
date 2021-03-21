package org.gabysanchez.entities;


import org.gabysanchez.application.Controller;
import org.gabysanchez.application.Dificultad;
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
        setNombre("Skynet");
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
    //Inteligencia//

    public Casilla disparo(){
        Casilla casilla = null;
        if (Controller.getInstance().getPartida().getDificultad().equals(Dificultad.FACIL)){
            casilla = random();
        }else if (Controller.getInstance().getPartida().getDificultad().equals(Dificultad.NORMAL)){
            casilla = random();
            casillasVetada.add(casilla);
        }else if (Controller.getInstance().getPartida().getDificultad().equals(Dificultad.DIFICIL)){
            casilla = memoryAtac();
        }else if (Controller.getInstance().getPartida().getDificultad().equals(Dificultad.INFERNAL)){
            casilla = infernal();
            casillasVetada.add(casilla);
        }
        return casilla;
    }

    public Casilla memoryAtac(){
        Casilla casilla = null;
        if (disparo!=null){
            barcoPosicion = disparo.getBarco().getPosicion();
            if (barcoPosicion.equals(Posicion.VERTICAL)&&disparo.getY()<(disparo.getBarco().getY()+disparo.getBarco().getLongitud()-1)) {
                casilla = getTableroAtaque().getCasillas()[disparo.getX()][disparo.getY()+1];
                disparo = casilla;
                almacenarCasillas(casilla);
            }else if (barcoPosicion.equals(Posicion.HORIZONTAL)&&disparo.getX()<(disparo.getBarco().getX()+disparo.getBarco().getLongitud()-1)) {
                casilla = getTableroAtaque().getCasillas()[disparo.getX() + 1][disparo.getY()];
                disparo = casilla;
                almacenarCasillas(casilla);
            }else {
                disparo=null;
                casilla=random();
            }
        }else {
           casilla=random();
        }
        return casilla;
    }
    public Casilla random(){
        Random random = new Random();
        Casilla casilla = null;
        while (true){
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            casilla = getTableroAtaque().getCasillas()[x][y];
            if (casilla.getEstado().equals(EstadoCasilla.AGUA)&&!casillasVetada.contains(casilla)){
                    if (casilla.getBarco()!=null){
                        if (casilla.getBarco().getLongitud()>1&&Controller.getInstance().getPartida().getDificultad().equals(Dificultad.DIFICIL)){
                            casilla=getTableroAtaque().getCasillas()[casilla.getBarco().getX()][casilla.getBarco().getY()];
                        }
                        disparo=casilla;
                        almacenarCasillas(casilla);
                    }else {
                        disparo=null;
                }
                break;
            }
        }
        return casilla;

    }
    public void almacenarCasillas(Casilla casilla){
        for (int i = casilla.getX() - 1; i <= casilla.getX() + 1; i++) {
            if (i >= 0 && i <getTableroAtaque().getCasillas().length) {
                for (int j = casilla.getY() - 1; j <= casilla.getY() + 1; j++) {
                    if (j >= 0 && j <getTableroAtaque().getCasillas().length) {
                        Casilla casilla2 = getTableroAtaque().getCasillas()[i][j];
                        if (!casillasVetada.contains(casilla2)&&casilla2.getBarco()==null){
                            casillasVetada.add(casilla2);
                        }
                    }
                }
            }
        }
    }
    public Casilla infernal(){
        Random random = new Random();
        Casilla casilla = null;
        while (true) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            casilla = getTableroAtaque().getCasillas()[x][y];
            if (casilla.getBarco()!=null&&!casillasVetada.contains(casilla)){
                break;
            }
        }
        return casilla;
    }
}
