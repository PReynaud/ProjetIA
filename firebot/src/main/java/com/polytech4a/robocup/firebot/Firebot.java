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
 *          Abstract class representing a firefighter robot.
 */
public abstract class Firebot {
    /**
     * Current graph of the situation.
     */
    private Graph graph;

    /**
     * Current node where the robot is.
     */
    private Node currentNode;

    /**
     * Task node where the robot has to go.
     */
    private Node destinationNode;

    /**
     * Way to the destination node.
     */
    private ArrayList<Node> wayToDestination = new ArrayList<Node>();

    /**
     * Capacity of the robot to fight fire.
     */
    private int capacity;

    /**
     * A robot is available when this parameter is true.
     */
    private boolean availability;

    /**
     * Constraints on edges of the graph for the Firebot where he can't go by.
     */
    private ArrayList<EdgeType> edgeConstraints = new ArrayList<EdgeType>();

    /**
     * Constraints on the nodes of the graph for the Firebot where he can't go by.
     */
    private ArrayList<NodeType> nodeConstraints = new ArrayList<NodeType>();

    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public Node getDestinationNode() {
        return destinationNode;
    }

    public void setDestinationNode(Node destinationNode) {
        this.destinationNode = destinationNode;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public ArrayList<EdgeType> getEdgeConstraints() {
        return edgeConstraints;
    }

    public ArrayList<NodeType> getNodeConstraints() {
        return nodeConstraints;
    }

    public Firebot(Graph graph, int capacity, ArrayList<EdgeType> edgeConstraints, ArrayList<NodeType> nodeConstraints) {
        this.graph = graph;
        this.capacity = capacity;
        this.edgeConstraints = edgeConstraints;
        this.nodeConstraints = nodeConstraints;
        this.availability = true;
        this.edgeConstraints = new ArrayList<EdgeType>();
        this.nodeConstraints = new ArrayList<NodeType>();

    }

    /**
     * Method to compute time needed for the robot to extinguish the fire.
     * @return Time to do the task, in milliseconds.
     */
    public abstract long computeTime();

    /**
     * Get robot's availability for a task.
     * @return True if the robot is available, else returns false.
     */
    public boolean isAvailable() {
        return availability;
    }

    /**
     * Compute distance to destination node. Return this distance.
     * @param destination destination node.
     * @return computed distance.
     */
    public double computeDistance(Node destination) {
        //TODO : call graph method to compute shortest distance to destination.
        return 0.0;
    }

    public void goToNextNode() {
        if(wayToDestination.size() > 0) {
            currentNode = wayToDestination.get(0);
            wayToDestination.remove(0);
        }
    }

}
