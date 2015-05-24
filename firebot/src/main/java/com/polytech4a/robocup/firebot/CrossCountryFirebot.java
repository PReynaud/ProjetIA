package com.polytech4a.robocup.firebot;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;

import java.util.ArrayList;

/**
 * Created by Adrien CHAUSSENDE on 06/05/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Class representing a cross country firebot.
 */
public class CrossCountryFirebot extends Firebot {

    public CrossCountryFirebot(Graph graph, int capacity, ArrayList<EdgeType> edgeConstraints, ArrayList<NodeType> nodeConstraints) {
        super(graph, capacity, edgeConstraints, nodeConstraints);
    }

    @Override
    public long computeTime() {
        return 0;
    }
}
