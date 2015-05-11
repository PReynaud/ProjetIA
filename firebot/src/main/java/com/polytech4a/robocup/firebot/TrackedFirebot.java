package com.polytech4a.robocup.firebot;

import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;

/**
 * Created by Adrien CHAUSSENDE on 06/05/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Class representing a firebot using tracks to move.
 */
public class TrackedFirebot extends Firebot {
    
    public TrackedFirebot(Graph graph, Node currentNode, Node destinationNode, int capacity) {
        super(graph, currentNode, destinationNode, capacity);
    }

    @Override
    public long computeTime() {
        return 0;
    }
}
