package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;

/**
 * Created by Pierre on 16/05/2015.
 */
public class FloodedEdgeView extends EdgeView {
    public FloodedEdgeView(NodeView firstNode, NodeView secondNode) {
        super(firstNode, secondNode);
        this.color = Color.BLUE;
    }
}
