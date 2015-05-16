package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.ui.GraphicControlPanel;
import com.polytech4a.robocup.firebot.ui.GraphicViewPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Pierre on 11/05/2015.
 */
public class GraphicControlController {
    private MainController mainController;

    public GraphicControlController(MainController mainController){
        this.mainController = mainController;

        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        graphicControlPanel.getAddNodeButton().addAction(new AddNodeAction(mainController));
        graphicControlPanel.getAddEdgeButton().addAction(new AddEdgeAction());
        graphicControlPanel.getAddRobotButton().addAction(new AddRobotAction());
        graphicControlPanel.getAddFireButton().addAction(new AddFireAction());
    }
}

class AddNodeAction extends AbstractAction{
    private MainController mainController;

    public AddNodeAction(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainController.setSelectionMode(EnumSelection.ADD_NODE);
    }
}

class AddEdgeAction extends AbstractAction{
    public AddEdgeAction(){}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class AddRobotAction extends AbstractAction{
    public AddRobotAction(){}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class AddFireAction extends AbstractAction{
    public AddFireAction(){}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}