package com.polytech4a.robocup.firebot.robots;

import com.polytech4a.robocup.firebot.observers.ManagerObserver;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Adrien CHAUSSENDE on 06/05/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Class representing the manager of the robot team, their task's affectation, and
 */
public class RobotManager implements Runnable, ManagerObserver {
    private static final Logger logger = Logger.getLogger(RobotManager.class);

    /**
     * Team of robots managed by the manager.
     */
    private ArrayList<Firebot> robotTeam;

    /**
     * Current graph of the situation
     */
    private Graph graph;

    /**
     * Boolean that tells if the manager has to shut down or not. If True, manager is shutted down.
     */
    private boolean shutdown = false;

    public ArrayList<Firebot> getRobotTeam() {
        return robotTeam;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public void setShutdown(boolean shutdown) {
        this.shutdown = shutdown;
    }

    public RobotManager(ArrayList<Firebot> robotTeam, Graph graph) {
        this.robotTeam = robotTeam;
        this.graph = graph;
    }

    /**
     * First step of protocole between the manager and the robots.
     *
     * @return ArrayList of Firebots that are available to do a task.
     */
    public ArrayList<Firebot> askAvailability() {
        ArrayList<Firebot> availableRobots = new ArrayList<>();
        for (Firebot f : robotTeam) {
            if (f.isAvailable())
                availableRobots.add(f);
        }
        return availableRobots;
    }

    /**
     * Asks to each robot the distance separating them from destination. Returns the closest robot.
     *
     * @param availableRobots List of Firebots that are available.
     * @param destination     Destination node.
     * @return Closest robot from the node.
     */
    public Firebot askDistancesToNode(ArrayList<Firebot> availableRobots, Node destination) {
        HashMap<Firebot, Double> dic = new HashMap<>();
        for (Firebot f : availableRobots) {
            dic.put(f, 0.0);
        }
        // Compute distance for each robot
        dic.keySet().stream().forEach(k -> dic.put(k, k.computeDistance(destination) / k.getSpeed()));
        Optional<Map.Entry<Firebot, Double>> mapEntry = dic.entrySet().stream().filter(f -> f.getValue() >= 0).min((o1, o2) -> o1.getValue().compareTo(o2.getValue()));
        return mapEntry.isPresent() ? mapEntry.get().getKey() : null;
    }

    /**
     * Method to properly generate integer identifier for a robot.
     *
     * @return Integer
     */
    public int generateId() {
        if (robotTeam.isEmpty())
            return 0;
        return robotTeam.stream().mapToInt(f -> f.getId()).max().getAsInt() + 1;
    }

    /**
     * Resets all the values of the manager in a delete purpose.
     */
    public void reset() {
        shutdown = true;
        for (Firebot f : robotTeam) {
            f.reset();
        }
        robotTeam = null;
        graph = null;
    }

    /**
     * Main protocole function. Entry point of the protocole.
     */
    public void distributeTasks() {
        ArrayList<Firebot> availableRobots = askAvailability();
        ArrayList<NodeType> types = new ArrayList<>();
        types.add(NodeType.INCENDIE);
        List<Node> destinationNodes = graph.getNodes().stream().filter(n -> n.isNodeFromType(types)).collect(Collectors.toList());
        if (destinationNodes.size() > 0) {
            for (Node n : destinationNodes) {
                if (!availableRobots.isEmpty()) {
                    Firebot assignedBot = askDistancesToNode(availableRobots, n);
                    if (assignedBot != null) {
                        assignedBot.setDestinationNode(n);
                        assignedBot.setAvailability(false);
                        assignedBot.setAbleToMove(true);
                        availableRobots.remove(assignedBot);
                    }
                } else {
                    break;
                }
            }
        }
    }

    @Override
    public void run() {
        logger.info("Manager is running");
        while (!shutdown) {
            distributeTasks();
        }
    }

    @Override
    public void updateActivity(Firebot firebot) {
        firebot.setAbleToMove(false);
    }
}
