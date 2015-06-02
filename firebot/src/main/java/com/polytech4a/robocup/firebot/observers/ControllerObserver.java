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
 *          <p/>
 *          Type of view Controller that can be notified by observable of the model.
 */
public interface ControllerObserver {

    /**
     * Fire update type on a Edge.
     *
     * @param edge Edge updated.
     * @param type New type of the edge.
     */
    void updateEdgeType(Edge edge, EdgeType type);

    /**
     * Fire update type on a Edge.
     *
     * @param node Node updated.
     * @param type New type of the node.
     */
    void updateNodeType(Node node, NodeType type);

    /**
     * Fire update a robot movement.
     *
     * @param firebot robot which moves.
     * @param currentNode current node of the robot.
     * @param nextNode next node into move.
     * @param time time to make the movement.
     */
    void updateRobotMovement(Firebot firebot, Node currentNode, Node nextNode, long time);
}
