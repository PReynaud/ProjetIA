package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.ui.FilePanel;
import com.polytech4a.robocup.firebot.ui.GraphicControlPanel;
import com.polytech4a.robocup.firebot.ui.SimulationPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Pierre on 11/05/2015.
 */
public class SimulationController {
    private MainController mainController;

    public SimulationController(MainController mainController){
        this.mainController = mainController;

        SimulationPanel simulationPanel = (SimulationPanel) mainController.getView().getSimulationPanel();
        simulationPanel.getPlayButton().addAction(new PlayAction(mainController));
        simulationPanel.getPauseButton().addAction(new PauseAction(mainController));
        simulationPanel.getPauseButton().setEnabled(false);
        simulationPanel.getResetButton().addAction(new ResetAction(mainController));
    }
}

class PlayAction extends AbstractAction{
    MainController mainController;

    public PlayAction(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainController.getTimeController().setRunning(true);
        SimulationPanel simulationPanel = (SimulationPanel) mainController.getView().getSimulationPanel();
        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        FilePanel filePanel = (FilePanel) mainController.getView().getFilePanel();

        simulationPanel.getPlayButton().setEnabled(false);
        simulationPanel.getPauseButton().setEnabled(true);
        simulationPanel.getResetButton().setEnabled(false);

        graphicControlPanel.getAddEdgeButton().setEnabled(false);
        graphicControlPanel.getAddNodeButton().setEnabled(false);

        filePanel.getLoadGraphButton().setEnabled(false);
        filePanel.getLoadImageButton().setEnabled(false);
        filePanel.getSaveGraphButton().setEnabled(false);
    }
}

class PauseAction extends AbstractAction{
    MainController mainController;

    public PauseAction(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainController.getTimeController().setRunning(false);
        SimulationPanel simulationPanel = (SimulationPanel) mainController.getView().getSimulationPanel();
        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        FilePanel filePanel = (FilePanel) mainController.getView().getFilePanel();

        simulationPanel.getPlayButton().setEnabled(true);
        simulationPanel.getPauseButton().setEnabled(false);
        simulationPanel.getResetButton().setEnabled(true);

        graphicControlPanel.getAddEdgeButton().setEnabled(true);
        graphicControlPanel.getAddNodeButton().setEnabled(true);

        filePanel.getLoadGraphButton().setEnabled(true);
        filePanel.getLoadImageButton().setEnabled(true);
        filePanel.getSaveGraphButton().setEnabled(true);
    }
}

class ResetAction extends AbstractAction{
    MainController mainController;

    public ResetAction(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainController.getView().resetView();
        mainController.setLastClickedNode(null);
        mainController.setSelectionMode(EnumSelection.NOTHING);

        //TODO reset le modèle
    }
}
