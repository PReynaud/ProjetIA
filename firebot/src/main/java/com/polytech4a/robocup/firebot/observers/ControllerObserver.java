package com.polytech4a.robocup.firebot.observers;

import com.polytech4a.robocup.firebot.robots.Firebot;
import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Edge;
import com.polytech4a.robocup.graph.model.Node;

/**
 * Created by Adrien CHAUSSENDE on 01/06/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 */
public interface ControllerObserver {

    public void updateEdgeType(Edge edge, EdgeType type);

    public void updateNodeType(Node node, NodeType type);

    public void updateRobotMovement(Firebot firebot, Node currentNode, Node nextNode, long time);
}
