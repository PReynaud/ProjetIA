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
    private MouseController mouseController;

    private EnumSelection selectionMode;

    public MainController(){
    }

    //TODO Constructeur avec en passage le modèle en plus
    public  MainController(MainForm mainForm){
        this.view = mainForm;

        this.fileController = new FileController(this);
        this.graphicControlController = new GraphicControlController(this);
        this.simulationController = new SimulationController(this);
        this.mouseController = new MouseController(this);
        this.view.getGraphicViewPanel().addMouseListener(this.mouseController);
        this.selectionMode = EnumSelection.NOTHING;
    }

    public MainForm getView() {
        return view;
    }
    public EnumSelection getSelectionMode() {return selectionMode;}
    public void setSelectionMode(EnumSelection newMode){this.selectionMode = newMode;}
}

enum EnumSelection {
    NOTHING,
    ADD_NODE,
    ADD_EDGE
}
