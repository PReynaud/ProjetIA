package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;

/**
 * Created by Pierre on 29/05/2015.
 * <p/>
 * View of a Tracked Firebot.
 */
public class TrackedFirebotView extends FirebotView {
    public TrackedFirebotView(NodeView node, int id) {
        super(id, node);
        this.color = Color.YELLOW;
    }
}
