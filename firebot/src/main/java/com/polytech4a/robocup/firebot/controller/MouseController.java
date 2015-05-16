package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.ui.GraphicViewPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Pierre on 16/05/2015.
 */
public class MouseController implements MouseListener {
    private MainController mainController;

    public MouseController(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(mainController.getSelectionMode().equals(EnumSelection.ADD_NODE)){
            GraphicViewPanel graphicViewPanel = (GraphicViewPanel) mainController.getView().getGraphicViewPanel();
            graphicViewPanel.getGraph().addNode(e.getX(), e.getY());
            graphicViewPanel.paintComponent(graphicViewPanel.getGraphics());
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
}
