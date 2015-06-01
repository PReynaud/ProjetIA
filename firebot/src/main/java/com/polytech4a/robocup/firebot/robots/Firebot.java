package com.polytech4a.robocup.firebot.robots;

import com.polytech4a.robocup.firebot.observers.Observable;
import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.MissingParameterException;
import com.polytech4a.robocup.graph.model.exceptions.NotFoundTypeException;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;
import com.polytech4a.robocup.graph.model.search.ISearch;
import com.polytech4a.robocup.graph.model.search.Way;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Adrien CHAUSSENDE on 06/05/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Abstract class representing a firefighter robot.
 */
public abstract class Firebot extends com.polytech4a.robocup.firebot.observers.Observable implements Runnable {

    /**
     * Identifier of the Firebot.
     */
    public int id;

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

    /**
     * Average speed of the robot.
     */
    private double speed;

    /**
     * Boolean that tells if the robot has to shut down or not. If True, robot is shutted down.
     */
    private boolean shutdown = false;

    /**
     * If the robot is in movement between to nodes, it is true. Else false.
     */
    private boolean inMovement = false;

    public Firebot(int id, Graph graph, int capacity, ArrayList<EdgeType> edgeConstraints, ArrayList<NodeType> nodeConstraints, double speed, ISearch searchAlgorithm) {
        this.id = id;
        this.graph = graph;
        this.capacity = capacity;
        this.edgeConstraints = edgeConstraints;
        this.nodeConstraints = nodeConstraints;
        this.speed = speed;
        this.searchAlgorithm = searchAlgorithm;
        this.availability = true;
    }

    public int getId() {
        return id;
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

    public double getSpeed() {
        return speed;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    public ISearch getSearchAlgorithm() {
        return searchAlgorithm;
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
                return -1;
            }
        }
        return 0.0;
    }

    /**
     * Resets all the values of the firebot in a delete purpose.
     */
    public void reset() {
        shutdown = true;
        graph = null;
        currentNode = null;
        destinationNode = null;
        wayToDestination = null;
        availability = false;
    }

    /**
     * Move this to next node depending on wayToDestination calculate.
     */
    public void goToNextNode() {
        ArrayList<Node> wayNodes = wayToDestination.getNodes();
        if (!isAvailable() && !wayNodes.isEmpty())
            currentNode = wayNodes.remove(0);
    }

    /**
     * Method for robot extinguishing the fire.
     */
    public void extinguishFire() {
        new Thread() {
            public void run() {
                long i = 0, limit = computeTime();
                new Timer("Extinguishing fire ...").schedule(new TimerTask() {
                    @Override
                    public void run() {
                        setAvailability(true);
                        currentNode.setType(NodeType.NORMAL);
                        Random rdm = new Random();
                        graph.getEdgesFromNode(currentNode).stream().forEach(e -> {
                            if (rdm.nextInt(10) > 5) {
                                e.setType(EdgeType.INONDEE);
                            }
                        });
                    }
                }, limit);
            }
        }.start();
    }

    @Override
    public void run() {
        logger.info("Model: Robot " + getId() + " is running");
        while (!shutdown) {
            try {
                if (destinationNode != null && currentNode.equals(destinationNode) && destinationNode.getType().equals(NodeType.INCENDIE)) {
                    extinguishFire();
                } else if (!inMovement && !wayToDestination.getNodes().isEmpty()) {
                    inMovement = true;
                    long time = (long) (currentNode.getEuclidianSpace(wayToDestination.getNodes().get(0)) / speed * 1000);

                    fireUpdateRobotMovement(this, currentNode, wayToDestination.getNodes().get(0), time);

                    new Timer("Firebot in movement").schedule(new TimerTask() {
                        @Override
                        public void run() {
                            goToNextNode();
                            inMovement = false;
                        }
                    }, time);
                }
            } catch (NotFoundTypeException e) {
                e.printStackTrace();
            } catch (MissingParameterException e) {
                logger.error("In run() from Firebot.java, can't calculate time to go to next node.", e);
            }
        }
    }

}
