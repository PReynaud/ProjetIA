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
 *          <p/>
 *          Observable of Robocup model.
 */
public class Observable {

    private List<ControllerObserver> controllerObservers;

    private List<ManagerObserver> managerObservers;

    public Observable() {
        this.controllerObservers = new LinkedList<>();
    }

    public void addControllerObserver(ControllerObserver observer) {
        controllerObservers.add(observer);
    }

    public void removeControllerObserver(ControllerObserver observer) {
        controllerObservers.remove(observer);
    }

    public void removeControllerObservers() {
        controllerObservers.clear();
    }

    public void addManagerObserver(ManagerObserver observer) {
        managerObservers.add(observer);
    }

    public void removeManagerObserver(ManagerObserver observer) {
        managerObservers.remove(observer);
    }

    public void removeManagerObservers() {
        managerObservers.clear();
    }

    synchronized public void fireUpdateEdgeType(Edge edge, EdgeType type) {
        for (ControllerObserver observer : controllerObservers) {
            observer.updateEdgeType(edge, type);
        }
    }

    synchronized public void fireUpdateNodeType(Node node, NodeType type) {
        for (ControllerObserver observer : controllerObservers) {
            observer.updateNodeType(node, type);
        }
    }

    public void fireUpdateRobotMovement(Firebot firebot, Node currentNode, Node nextNode, long time) {
        for (ControllerObserver observer : controllerObservers) {
            observer.updateRobotMovement(firebot, currentNode, nextNode, time);
        }
    }

    public void fireUpdateActivity(Firebot firebot) {
        for (ManagerObserver observer : managerObservers) {
            observer.updateActivity(firebot);
        }
    }
}
