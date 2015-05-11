package com.polytech4a.robocup.firebot.controller;

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
        simulationPanel.getPlayButton().addAction(new PlayAction());
        simulationPanel.getPauseButton().addAction(new PauseAction());
        simulationPanel.getStopButton().addAction(new StopAction());
    }
}

class PlayAction extends AbstractAction{
    public PlayAction() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class PauseAction extends AbstractAction{
    public PauseAction() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class StopAction extends AbstractAction{
    public StopAction() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
