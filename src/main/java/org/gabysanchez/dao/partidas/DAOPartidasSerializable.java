package org.gabysanchez.dao.partidas;

import org.gabysanchez.application.Controller;
import org.gabysanchez.application.Data;
import org.gabysanchez.application.Partida;
import org.gabysanchez.dao.DAOSerializable;

import java.util.ArrayList;
import java.util.List;

public class DAOPartidasSerializable extends DAOSerializable {

    private List<Partida> partidas;

    public DAOPartidasSerializable(String file) {
        super(file);
        partidas=new ArrayList<>();
    }
    public void setPartidas(List<Partida>partidas){
        save(partidas);
    }

    public void getPartidas(){
        partidas = (List<Partida>) load(partidas);
        Controller.getInstance().setPartidas(partidas);
    }
}
