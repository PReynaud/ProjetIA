package com.polytech4a.robocup.firebot;

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
public class RobotManager {
    /**
     * Team of robots managed by the manager.
     */
    private ArrayList<Firebot> robotTeam;

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
        HashMap<Firebot, Double> dic = new HashMap<Firebot, Double>();
        for (Firebot f : availableRobots) {
            dic.put(f, 0.0);
        }
        dic.keySet().parallelStream().forEach(k -> dic.put(k, k.computeDistance(destination)));
        return Collections.min(dic.entrySet(), (o1, o2) -> o1.getValue().compareTo(o2.getValue())).getKey();
    }

}
