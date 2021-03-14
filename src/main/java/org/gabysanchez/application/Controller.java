package org.gabysanchez.application;

import org.gabysanchez.App;
import org.gabysanchez.ui.scenes.SceneTablero;
import org.gabysanchez.ui.scenes.SceneTableroColocar;
import org.gabysanchez.ui.scenes.SceneTableroCombate;
import org.gabysanchez.ui.tablero.EstadoUiTablero;

import java.io.IOException;

public class Controller {

    private static Controller controller;
    private Partida partida;
    private SceneTablero scene;


    public Controller() {
        this.partida=new Partida();
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

    public SceneTablero getScene() {
        return scene;
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

    }
    public void combate() throws IOException {
        if (partida.getJ1().getnBarcos()==0&&partida.getJ2().getnBarcos()==0){
            System.out.println("Yes");
            App.setRoot("combate");
            Controller.getInstance().getPartida().getJ2().setTableroAtaque(Controller.getInstance().getPartida().getJ1().getTableroDefensa());
            scene = new SceneTableroCombate(Controller.getInstance().getPartida().getJ1());
            scene.crearUiTablero(scene.getUiTableroDefensa());
            scene.crearUiTablero(scene.getUiTableroAtaque());
            scene.getUiTableroDefensa().redraw();
            scene.getUiTableroAtaque().setEstado(EstadoUiTablero.USE);
        }
    }
}
