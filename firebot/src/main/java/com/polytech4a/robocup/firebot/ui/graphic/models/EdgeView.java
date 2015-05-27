package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;

/**
 * Created by Pierre on 16/05/2015.
 */
public class EdgeView {
    protected Color color;

    private NodeView node1;
    private NodeView node2;

    public EdgeView(NodeView firstNode, NodeView secondNode){
        this.node1 = firstNode;
        this.node2 = secondNode;
        this.color = Color.MAGENTA;
    }

    public void drawEdge(Graphics g){
        g.setColor(this.color);
        g.drawLine(node1.getX(), node1.getY(), node2.getX(), node2.getY());
    }
}
