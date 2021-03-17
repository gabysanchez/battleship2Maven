package org.gabysanchez.application;

import org.gabysanchez.App;
import org.gabysanchez.dao.DAOFactory;
import org.gabysanchez.ui.scenes.SceneTablero;
import org.gabysanchez.ui.scenes.SceneTableroColocar;
import org.gabysanchez.ui.scenes.SceneTableroCombate;
import org.gabysanchez.ui.tablero.EstadoUiTablero;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private static Controller controller;
    private Partida partida;
    private SceneTablero scene;
    private Data data;
    private List<Partida> partidas;


    public Controller() {

        this.partida=new Partida();
        this.data = new Data();
        this.partidas = new ArrayList<>();
    }

    public static Controller getInstance() {
        if(controller == null){
            controller = new Controller();
        }
        return controller;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public SceneTablero getScene() {
        return scene;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public List<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(List<Partida> partidas) {
        this.partidas = partidas;
    }

    public void addPartida(){
        partidas.add(partida);
    }
    public void removePartida(Partida partida){
        partidas.remove(partida);
    }

    public void colocar() throws IOException {
        Controller.getInstance().getPartida().createIA();
        Controller.getInstance().getPartida().getJ1().addBarcos();
        Controller.getInstance().getPartida().getJ2().addBarcos();
        Controller.getInstance().getPartida().getJ1().setTableroAtaque(Controller.getInstance().getPartida().getJ2().getTableroDefensa());
        App.setRoot("colocacion");
        scene = new SceneTableroColocar(partida.getJ1());
        scene.create();
        scene.crearUiTablero(scene.getUiTableroDefensa());
        scene.crearUiTablero(scene.getUiTableroAtaque());
        scene.getUiTableroDefensa().setEstado(EstadoUiTablero.USE);
        DAOFactory.getInstance().getDaoDataSerializable().getData();
        partida.setDificultad(data.getDificultad());
        partida.getJ1().setNombre(data.getNamePlayer());
        partida.setEstadoPartida(EstadoPartida.COLOCAR);
    }
    public void combate() throws IOException {
        if (partida.getJ1().getnBarcos()==0&&partida.getJ2().getnBarcos()==0){
            App.setRoot("combate");
            Controller.getInstance().getPartida().getJ2().setTableroAtaque(Controller.getInstance().getPartida().getJ1().getTableroDefensa());
            scene = new SceneTableroCombate(Controller.getInstance().getPartida().getJ1());
            scene.crearUiTablero(scene.getUiTableroDefensa());
            scene.crearUiTablero(scene.getUiTableroAtaque());
            scene.getUiTableroDefensa().redraw();
            scene.getUiTableroAtaque().setEstado(EstadoUiTablero.USE);
            partida.getJ1().setnBarcos(10);
            partida.getJ2().setnBarcos(10);
            partida.setEstadoPartida(EstadoPartida.COMBATE);
            scene.update();
        }
    }
    public void finish() throws IOException {
        App.setRoot("finalizado");
        scene.crearUiTablero(scene.getUiTableroDefensa());
        scene.crearUiTablero(scene.getUiTableroAtaque());
        scene.getUiTableroAtaque().redraw();
        scene.getUiTableroDefensa().redraw();
        scene.getUiTableroAtaque().setEstado(EstadoUiTablero.VIEW);
        scene.getUiTableroAtaque().setEstado(EstadoUiTablero.VIEW);
        partida.setEstadoPartida(EstadoPartida.FINALIZADA);
        borrar(partida);

    }
    public void cargar(Partida partidaMemoria) throws IOException {
        App.setRoot("combate");
        partida=partidaMemoria;
        Controller.getInstance().getPartida().getJ2().setTableroAtaque(Controller.getInstance().getPartida().getJ1().getTableroDefensa());
        scene = new SceneTableroCombate(Controller.getInstance().getPartida().getJ1());
        scene.crearUiTablero(scene.getUiTableroDefensa());
        scene.crearUiTablero(scene.getUiTableroAtaque());
        scene.getUiTableroAtaque().redraw();
        scene.getUiTableroDefensa().redraw();
        scene.getUiTableroAtaque().setEstado(EstadoUiTablero.USE);
        scene.create();
        partida.setEstadoPartida(EstadoPartida.COMBATE);
    }

    public void borrar(Partida partidaBorrar){
        DAOFactory.getInstance().getDaoPartidasSerializable().getPartidas();
        for (int i = 0; i < Controller.getInstance().getPartidas().size(); i++) {
            Partida partida = Controller.getInstance().getPartidas().get(i);
            if (partida.getNombre().equals(partidaBorrar.getNombre())){
                Controller.getInstance().removePartida(partida);
            }
        }
        DAOFactory.getInstance().getDaoPartidasSerializable().setPartidas(Controller.getInstance().getPartidas());
    }
}
