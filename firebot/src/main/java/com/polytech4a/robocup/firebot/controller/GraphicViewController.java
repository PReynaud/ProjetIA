package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.robots.Firebot;
import com.polytech4a.robocup.firebot.ui.GraphicViewPanel;
import com.polytech4a.robocup.firebot.ui.graphic.models.FirebotView;
import com.polytech4a.robocup.firebot.ui.graphic.models.GraphView;
import com.polytech4a.robocup.firebot.ui.graphic.models.NodeView;
import com.polytech4a.robocup.graph.model.exceptions.MissingParameterException;

import java.util.ArrayList;

/**
 * Created by Pierre on 31/05/2015.
 */
public class GraphicViewController {
    private MainController mainController;

    public GraphicViewController(MainController mainController) {
        this.mainController = mainController;
    }

    public void animateFirebots(){
        GraphicViewPanel graphicViewPanel = (GraphicViewPanel) mainController.getView().getGraphicViewPanel();
        ArrayList<FirebotView> robotList = graphicViewPanel.getGraph().getRobots();
        GraphView graphView = graphicViewPanel.getGraph();

        for(Firebot bot: mainController.getModel().getRobotTeam()){
            if(bot.getDestinationNode() != null){
                try {
                    int x = (int)bot.getCurrentNode().getX();
                    int y = (int)bot.getCurrentNode().getY();
                    FirebotView botView = robotList
                            .stream()
                            .filter(o -> o.getCurrentNode().getX() == x && o.getCurrentNode().getY() == y)
                            .findFirst()
                            .get();

                    if(botView.isMoving()){
                        if(isOnNode(botView.getDestinationNode(), botView.getX(), botView.getY())){
                            botView.setCurrentNode(botView.getDestinationNode());
                            botView.setMoving(false);
                            botView.setDestinationNode(null);
                            botView.setDirection(0, 0);
                        }
                        else{
                            botView.moveBot();
                        }
                    }
                    else {
                        botView.setDestinationNode(graphView.getNodes()
                                .stream()
                                .filter(o -> o.getId() == bot.getDestinationNode().getId())
                                .findFirst()
                                .get());
                        //TODO calculer le vecteur de déplacement en x et y (le stocker après?)
                        int movingX = 0;
                        int movingY = 0;

                        botView.setDirection(movingX, movingY);
                        botView.setMoving(true);

                        botView.moveBot();
                    }

                } catch (MissingParameterException e) {
                    //TODO gérer exception
                    e.printStackTrace();
                }
            }
        }
    }

    public void animateFirebot(Firebot bot){
        GraphicViewPanel graphicViewPanel = (GraphicViewPanel) mainController.getView().getGraphicViewPanel();
        GraphView graphView = graphicViewPanel.getGraph();

        /*if(bot.getDestinationNode() != null){
            try {
                int x = (int)bot.getCurrentNode().getX();
                int y = (int)bot.getCurrentNode().getY();
                FirebotView botView = robotList
                        .stream()
                        .filter(o -> o.getCurrentNode().getX() == x && o.getCurrentNode().getY() == y)
                        .findFirst()
                        .get();

                if(botView.isMoving()){
                    if(isOnNode(botView.getDestinationNode(), botView.getX(), botView.getY())){
                        botView.setCurrentNode(botView.getDestinationNode());
                        botView.setMoving(false);
                        botView.setDestinationNode(null);
                        botView.setDirection(0, 0);
                    }
                    else{
                        botView.moveBot();
                    }
                }
                else {
                    botView.setDestinationNode(graphView.getNodes()
                            .stream()
                            .filter(o -> o.getId() == bot.getDestinationNode().getId())
                            .findFirst()
                            .get());
                    //TODO calculer le vecteur de déplacement en x et y (le stocker après?)
                    int movingX = 0;
                    int movingY = 0;

                    botView.setDirection(movingX, movingY);
                    botView.setMoving(true);

                    botView.moveBot();
                }

            } catch (MissingParameterException e) {
                //TODO gérer exception
                e.printStackTrace();
            }
        }    */
    }

    private boolean isOnNode(NodeView node, int x, int y){
        double dist = Math.sqrt(Math.pow((node.getX() - x), 2) + Math.pow((node.getY() - y), 2));
        return dist < node.getSize();
    }
}
