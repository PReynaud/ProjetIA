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
     * Capacity of the robot to fight fire.
     */
    int capacity;

    /**
     * Method to compute time needed for the robot to extinguish the fire.
     * @return time in milliseconds.
     */
    public abstract long computeTime();


}
