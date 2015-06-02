package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.robots.RobotManager;
import com.polytech4a.robocup.firebot.ui.FilePanel;
import com.polytech4a.robocup.firebot.ui.GraphicControlPanel;
import com.polytech4a.robocup.firebot.ui.SimulationPanel;
import com.polytech4a.robocup.graph.model.Graph;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        graphicControlPanel.resetColorOfButtons();

        mainController.getTimeController().setRunning(true);
        SimulationPanel simulationPanel = (SimulationPanel) mainController.getView().getSimulationPanel();
        FilePanel filePanel = (FilePanel) mainController.getView().getFilePanel();
        new Thread(mainController.getModel()).start();
        mainController.getModel().getRobotTeam().stream().forEach(o -> new Thread(o).start());

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
        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        graphicControlPanel.resetColorOfButtons();

        mainController.getTimeController().setRunning(false);
        SimulationPanel simulationPanel = (SimulationPanel) mainController.getView().getSimulationPanel();
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
        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        graphicControlPanel.resetColorOfButtons();

        mainController.getView().resetView();
        mainController.setLastClickedNode(null);
        mainController.setSelectionMode(EnumSelection.NOTHING);

        mainController.getModel().reset();
        mainController.setModel(new RobotManager(new ArrayList<>(), new Graph()));
    }
}
