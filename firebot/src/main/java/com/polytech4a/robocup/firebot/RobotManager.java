package com.polytech4a.robocup.firebot;

import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Adrien CHAUSSENDE on 06/05/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Class representing the manager of the robot team, their task's affectation, and
 */
public class RobotManager {
    /**
     * Team of robots managed by the manager.
     */
    private ArrayList<Firebot> robotTeam;

    /**
     * Current graph of the situation
     */
    private Graph graph;

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
     * @param destination Destination node.
     * @return Closest robot from the node.
     */
    public Firebot askDistancesToNode(ArrayList<Firebot> availableRobots, Node destination) {
        HashMap<Firebot, Double> dic = new HashMap<>();
        for (Firebot f : availableRobots) {
            dic.put(f, 0.0);
        }
        dic.keySet().parallelStream().forEach(k -> dic.put(k, k.computeDistance(destination)));
        return Collections.min(dic.entrySet(), (o1, o2) -> o1.getValue().compareTo(o2.getValue())).getKey();
    }

    /**
     * Main protocole function. Entry point of the protocole.
     */
    public void distributeTasks() {
        ArrayList<Firebot> availableRobots = askAvailability();
        ArrayList<Node> destinationNodes = new ArrayList<>();
        //destinationNodes = graph.getDestinationNodes();
        ArrayList<Node> leftoverNodes = new ArrayList<>();
        if(destinationNodes.size() > 0) {
            for(Node n : destinationNodes) {
                Firebot assignedBot = askDistancesToNode(availableRobots, n);
                if(assignedBot != null) {
                    assignedBot.setDestinationNode(n);
                    assignedBot.setAvailability(false);
                    availableRobots.remove(assignedBot);
                }
            }
        }
    }

}
