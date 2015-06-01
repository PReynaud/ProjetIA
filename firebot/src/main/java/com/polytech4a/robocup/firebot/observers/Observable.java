package com.polytech4a.robocup.firebot.observers;

import com.polytech4a.robocup.firebot.robots.Firebot;
import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Edge;
import com.polytech4a.robocup.graph.model.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adrien CHAUSSENDE on 01/06/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 */
public class Observable {

    private List<ControllerObserver> observers;

    public Observable() {
        this.observers = new LinkedList<>();
    }

    public void addControllerObserver(ControllerObserver observer) {
        observers.add(observer);
    }

    public void removeControllerObserver(ControllerObserver observer) {
        observers.remove(observer);
    }

    public void removeControllerObservers() {
        observers.clear();
    }

    synchronized public void fireUpdateEdgeType(Edge edge, EdgeType type) {
        for(ControllerObserver observer : observers) {
            observer.updateEdgeType(edge, type);
        }
    }

    synchronized public void fireUpdateNodeType(Node node, NodeType type) {
        for(ControllerObserver observer : observers) {
            observer.updateNodeType(node, type);
        }
    }

    public void fireUpdateRobotMovement(Firebot firebot, Node currentNode, Node nextNode, long time) {
        for(ControllerObserver observer : observers) {
            observer.updateRobotMovement(firebot, currentNode, nextNode, time);
        }
    }
}
