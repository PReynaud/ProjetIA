package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.model.Node;

import java.util.ArrayList;

/**
 * Created by Antoine CARON on 24/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public final class Way {

    /**
     * Nodes of the way
     */
    private ArrayList<Node> nodes;

    /**
     * Total distance of the way.
     */
    private Double distance;

    public Way() {
        this.distance=new Double(0);
        this.nodes=new ArrayList<>();
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<Node> nodes) {
        this.nodes = nodes;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }


    public Way(Double distance, ArrayList<Node> nodes) {
        this.distance = distance;
        this.nodes = nodes;
    }
}
