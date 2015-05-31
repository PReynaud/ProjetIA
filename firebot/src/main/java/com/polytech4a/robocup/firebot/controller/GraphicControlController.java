package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.ui.GraphicControlPanel;

import javax.swing.*;
import java.awt.*;
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
        graphicControlPanel.getAddRobotButton().addAction(new AddRobotAction(mainController));
        graphicControlPanel.getAddFireButton().addAction(new AddFireAction(mainController));
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
        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        graphicControlPanel.resetColorOfButtons();
        graphicControlPanel.getAddNodeButton().setBackground(Color.MAGENTA);
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
        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        graphicControlPanel.resetColorOfButtons();
        if(graphicControlPanel.getSelectionEdge().getSelectedIndex() == 1) {
            this.mainController.setSelectionMode(EnumSelection.ADD_STEEP_EDGE);
            graphicControlPanel.getAddEdgeButton().setBackground(Color.GREEN);
        } else {
            this.mainController.setSelectionMode(EnumSelection.ADD_EDGE);
            graphicControlPanel.getAddEdgeButton().setBackground(Color.MAGENTA);
        }
    }
}

/**
 * Action for the class that add a robot. We change the selection mode in the main controller.
 */
class AddRobotAction extends AbstractAction{
    MainController mainController;

    public AddRobotAction(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        graphicControlPanel.resetColorOfButtons();
        if(graphicControlPanel.getSelectionRobot().getSelectedIndex() == 0){
            graphicControlPanel.getAddRobotButton().setBackground(Color.GREEN);
            this.mainController.setSelectionMode(EnumSelection.ADD_CROSS_COUNTRY_FIREBOT);
        }
        if(graphicControlPanel.getSelectionRobot().getSelectedIndex() == 1){
            graphicControlPanel.getAddRobotButton().setBackground(Color.YELLOW);
            this.mainController.setSelectionMode(EnumSelection.ADD_TRACKED_FIREBOT);
        }
        if(graphicControlPanel.getSelectionRobot().getSelectedIndex() == 2){
            graphicControlPanel.getAddRobotButton().setBackground(Color.BLUE);
            this.mainController.setSelectionMode(EnumSelection.ADD_LEGGED_FIREBOT);
        }
    }
}

/**
 * Action for the class that add a fire. We change the selection mode in the main controller.
 */
class AddFireAction extends AbstractAction{
    MainController mainController;

    public AddFireAction(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        graphicControlPanel.resetColorOfButtons();
        graphicControlPanel.getAddFireButton().setBackground(Color.RED);
        this.mainController.setSelectionMode(EnumSelection.ADD_FIRE_NODE);
    }
}