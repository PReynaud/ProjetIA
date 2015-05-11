package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.ui.MainForm;

/**
 * Created by Pierre on 11/05/2015.
 */
public class MainController {
    private MainForm view;
    //TODO Modèle avec ses getters

    private FileController fileController;
    private GraphicControlController graphicControlController;
    private SimulationController simulationController;

    public MainController(){
    }

    //TODO Constructeur avec en passage le modèle en plus
    public  MainController(MainForm mainForm){
        this.view = mainForm;

        this.fileController = new FileController(this);
        this.graphicControlController = new GraphicControlController(this);
        this.simulationController = new SimulationController(this);

    }

    public MainForm getView() {
        return view;
    }
}
