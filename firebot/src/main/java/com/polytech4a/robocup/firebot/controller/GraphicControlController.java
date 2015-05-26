package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.ui.GraphicControlPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Pierre on 11/05/2015.
 * @version 1.0
 *          <p/>
 *          Control class for the buttons that manipulate the graphic interface
 */
public class GraphicControlController {
    private MainController mainController;

    public GraphicControlController(MainController mainController){
        this.mainController = mainController;

        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        graphicControlPanel.getAddNodeButton().addAction(new AddNodeAction(mainController));
        graphicControlPanel.getAddEdgeButton().addAction(new AddEdgeAction(mainController));
        graphicControlPanel.getAddRobotButton().addAction(new AddRobotAction());
        graphicControlPanel.getAddFireButton().addAction(new AddFireAction());
        graphicControlPanel.getAddEscarpeEdge().addAction(new AddEdgeEscarpeAction());
    }
}

/**
 * Action for the button that add a node. We change the selection mode in the main controller.
 */
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

/**
 * Action for the class that add an edge. We change the selection mode in the main controller.
 */
class AddEdgeAction extends AbstractAction{
    private MainController mainController;

    public AddEdgeAction(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainController.setSelectionMode(EnumSelection.ADD_EDGE);
    }
}

/**
 * Action for the class that add a robot. We change the selection mode in the main controller.
 */
class AddRobotAction extends AbstractAction{
    public AddRobotAction(){}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

/**
 * Action for the class that add a fire. We change the selection mode in the main controller.
 */
class AddFireAction extends AbstractAction{
    public AddFireAction(){}

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

/**
 * Action launch to add an Edge escarpé.
 */
class AddEdgeEscarpeAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}