package com.polytech4a.robocup.firebot;

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
    private RobotGraph graph;

    /**
     * Current node where the robot is.
     */
    private Node currentNode;

    /**
     * Task node where the robot has to go.
     */
    private Node destinationNode;

    /**
     * Capacity of the robot to fight fire.
     */
    int capacity;

    /**
     * A robot is available when this parameter is true.
     */
    boolean availability;

    public void setDestinationNode(Node destinationNode) {
        this.destinationNode = destinationNode;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Firebot(RobotGraph graph, Node currentNode, Node destinationNode, int capacity) {
        this.graph = graph;
        this.currentNode = currentNode;
        this.destinationNode = destinationNode;
        this.capacity = capacity
        this.availability = true;
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

}
