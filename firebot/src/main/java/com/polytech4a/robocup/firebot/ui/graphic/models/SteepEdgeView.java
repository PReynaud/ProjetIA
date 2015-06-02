package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;

/**
 * Created by Pierre on 28/05/2015.
 * <p/>
 * View of a Steep Edge.
 */
public class SteepEdgeView extends EdgeView {
    public SteepEdgeView(NodeView firstNode, NodeView secondNode) {
        super(firstNode, secondNode);
        this.color = Color.GREEN;
    }
}