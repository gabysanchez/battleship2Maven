package org.gabysanchez.service;

import javafx.scene.control.Button;
import org.gabysanchez.entities.Jugador;
import org.gabysanchez.entities.barcos.Barco;

import java.util.List;

public class ServicioSelectBarco {

    private static ServicioSelectBarco servicioSelectBarco;

    public static ServicioSelectBarco getInstance() {

        if (servicioSelectBarco == null) {
            servicioSelectBarco = new ServicioSelectBarco();
        }
        return servicioSelectBarco;
    }

    public void selectedBarco(Button bt, Jugador jugador, Class<? extends Barco> clave){

        List<Barco> tipoBarco = jugador.getMapBarcos().get(clave);

        if (tipoBarco.size()>0){
            jugador.setNextSelectedBarco(tipoBarco.get(0));
        }else {
            System.out.println("No tienes mas");
        }
    }
}
