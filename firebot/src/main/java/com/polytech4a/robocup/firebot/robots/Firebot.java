package com.polytech4a.robocup.firebot.robots;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;
import com.polytech4a.robocup.graph.model.search.AStar;
import com.polytech4a.robocup.graph.model.search.ISearch;
import com.polytech4a.robocup.graph.model.search.Way;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Queue;

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
     * Logger.
     */
    private static final Logger logger = Logger.getLogger(Firebot.class);

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
    private Way wayToDestination = new Way();

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

    /**
     * Search Algorithm for finding way to a node and
     */
    private ISearch searchAlgorithm;

    public Firebot(Graph graph, int capacity, ArrayList<EdgeType> edgeConstraints, ArrayList<NodeType> nodeConstraints) {
        this.graph = graph;
        this.capacity = capacity;
        this.edgeConstraints = edgeConstraints;
        this.nodeConstraints = nodeConstraints;
        this.availability = true;
        this.edgeConstraints = edgeConstraints;
        this.nodeConstraints = nodeConstraints;
        this.searchAlgorithm = new AStar();
    }

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

    /**
     * Method to compute time needed for the robot to extinguish the fire.
     *
     * @return Time to do the task, in milliseconds.
     */
    public long computeTime() {
        return (long) 1000 * capacity / 100;
    }

    /**
     * Get robot's availability for a task.
     *
     * @return True if the robot is available, else returns false.
     */
    public boolean isAvailable() {
        return availability;
    }

    /**
     * Compute distance to destination node. Return this distance.
     *
     * @param destination destination node.
     * @return computed distance.
     */
    public double computeDistance(Node destination) {
        if (currentNode != null && destination != null) {
            try {
                wayToDestination = searchAlgorithm.wayToNodeWithoutParam(graph, currentNode, destination, nodeConstraints, edgeConstraints);
                return wayToDestination.getDistance();
            } catch (SearchException e) {
                logger.trace("Error within algorithm execution", e);
            }
        }
        return 0.0;
    }

    /**
     * Move this to next node depending on wayToDestination calculate.
     */
    public void goToNextNode() {
        Queue<Node> queueNodeList = (Queue<Node>) wayToDestination.getNodes();
        Node n = queueNodeList.poll();
        if (n != null) {
            currentNode = n;
        }
    }

}
