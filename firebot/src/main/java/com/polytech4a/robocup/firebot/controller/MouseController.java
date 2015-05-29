package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.ui.GraphicViewPanel;
import com.polytech4a.robocup.firebot.ui.graphic.models.GraphView;
import com.polytech4a.robocup.firebot.ui.graphic.models.NodeView;
import com.polytech4a.robocup.graph.enums.NodeType;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

/**
 * Created by Pierre on 16/05/2015.
 */
public class MouseController implements MouseListener, MouseMotionListener {
    private MainController mainController;

    public MouseController(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GraphicViewPanel graphicViewPanel = (GraphicViewPanel) mainController.getView().getGraphicViewPanel();
        GraphView graph = graphicViewPanel.getGraph();

        /* Draw a node */
        if(mainController.getSelectionMode().equals(EnumSelection.ADD_NODE) ||
                mainController.getSelectionMode().equals(EnumSelection.ADD_FIRE_NODE)){
            if(mainController.getSelectionMode().equals(EnumSelection.ADD_NODE)){
                int id = mainController.getGraph().addNewNode(e.getX(), e.getY(), NodeType.NORMAL);
                graph.addNode(e.getX(), e.getY(), id);
            }
            if(mainController.getSelectionMode().equals(EnumSelection.ADD_FIRE_NODE)){
                NodeView clickedNode = clickOnANode(graph.getNodes(), e.getX(), e.getY());
                if(clickedNode != null){
                    int id = mainController.getGraph().addNewNode(e.getX(), e.getY(), NodeType.INCENDIE);
                    graph.addFireNode(e.getX(), e.getY(), clickedNode.getId());
                    graph.deleteNode(clickedNode.getId());
                }
            }
        }

        /* Draw an edge */
        if(mainController.getSelectionMode().equals(EnumSelection.ADD_EDGE) ||
                mainController.getSelectionMode().equals(EnumSelection.ADD_STEEP_EDGE)){
            //We verify that we click on a node
            NodeView clickedNode = clickOnANode(graph.getNodes(), e.getX(), e.getY());
            if(clickedNode != null){
                if(mainController.getLastClickedNode() == null){
                    mainController.setLastClickedNode(clickedNode);
                }
                else{
                    //TODO ajouter au modele
                    if(mainController.getSelectionMode().equals(EnumSelection.ADD_EDGE)){
                        graph.addEdge(mainController.getLastClickedNode(), clickedNode);
                    }
                    if(mainController.getSelectionMode().equals(EnumSelection.ADD_STEEP_EDGE)){
                        graph.addSteepEdge(mainController.getLastClickedNode(), clickedNode);
                    }
                    mainController.setLastClickedNode(null);
                }
            }
        }

        /* Draw a robot */
        if(mainController.getSelectionMode().equals(EnumSelection.ADD_ROBOT)){
            NodeView clickedNode = clickOnANode(graph.getNodes(), e.getX(), e.getY());
            if(clickedNode != null){
                //TODO ajouter au modèle
                graph.addRobot(clickedNode);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(mainController.getSelectionMode().equals(EnumSelection.ADD_EDGE) ||
                mainController.getSelectionMode().equals(EnumSelection.ADD_STEEP_EDGE)) {
            if (mainController.getLastClickedNode() != null) {
                GraphicViewPanel graphicViewPanel = (GraphicViewPanel) mainController.getView().getGraphicViewPanel();
                graphicViewPanel.paintComponentWithCustomEdge(graphicViewPanel.getGraphics(), mainController.getLastClickedNode().getX(),
                        mainController.getLastClickedNode().getY(), e.getX(), e.getY(), mainController.getSelectionMode());
            }
        }
    }

    private NodeView clickOnANode(List<NodeView> listOfNodes, int x, int y){
        for(NodeView node: listOfNodes){
            double dist = Math.sqrt(Math.pow((node.getX() - x), 2) + Math.pow((node.getY() - y), 2));
            if(dist < node.getSize()){
                return node;
            }
        }
        return null;
    }
}
