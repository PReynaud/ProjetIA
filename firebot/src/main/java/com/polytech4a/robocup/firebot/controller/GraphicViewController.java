package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.robots.Firebot;
import com.polytech4a.robocup.firebot.ui.GraphicViewPanel;
import com.polytech4a.robocup.firebot.ui.graphic.models.FirebotView;
import com.polytech4a.robocup.firebot.ui.graphic.models.GraphView;
import com.polytech4a.robocup.firebot.ui.graphic.models.NodeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pierre on 31/05/2015.
 */
public class GraphicViewController {
    private MainController mainController;

    public GraphicViewController(MainController mainController) {
        this.mainController = mainController;
    }

    public void animateFirebots() {
        GraphicViewPanel graphicViewPanel = (GraphicViewPanel) mainController.getView().getGraphicViewPanel();
        List<FirebotView> robotList = graphicViewPanel.getGraph().getRobots();

        for (Firebot bot : mainController.getModel().getRobotTeam()) {
            if (bot.getDestinationNode() != null) {
                FirebotView botView = robotList
                        .stream()
                        .filter(o -> o.getId() == bot.getId())
                        .findFirst()
                        .get();

                if (botView.isMoving()) {
                    botView.moveBot();
                }
            }
        }
    }

    private boolean isOnNode(NodeView node, int x, int y) {
        double dist = Math.sqrt(Math.pow((node.getX() - x), 2) + Math.pow((node.getY() - y), 2));
        return dist < node.getSize();
    }
}
