package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;

/**
 * Created by Pierre on 16/05/2015.
 * <p/>
 * View of a Edge.
 */
public class EdgeView {
    /**
     * Color.
     */
    protected Color color;

    /**
     * First NodeView on which is connected Edge.
     */
    private NodeView node1;

    /**
     * Second NodeView on which is connected Edge.
     */
    private NodeView node2;

    public EdgeView(NodeView firstNode, NodeView secondNode) {
        this.node1 = firstNode;
        this.node2 = secondNode;
        this.color = Color.MAGENTA;
    }

    public NodeView getNode1() {
        return node1;
    }

    public NodeView getNode2() {
        return node2;
    }

    /**
     * Draw the Edge on the graphics.
     *
     * @param g graphics.
     */
    public void drawEdge(Graphics g) {
        g.setColor(this.color);
        g.drawLine(node1.getX(), node1.getY(), node2.getX(), node2.getY());
    }
}
