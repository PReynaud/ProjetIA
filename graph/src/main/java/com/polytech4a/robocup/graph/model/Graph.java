package com.polytech4a.robocup.graph.model;

import java.util.ArrayList;

/**
 * Created by Antoine CARON on 06/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *
 * Simple Graph Model.
 */
public class Graph {

    /**
     * Nodes of the graph.
     */
    private ArrayList<Node> nodes;

    /**
     * Edge of the graph.
     */
    private ArrayList<Edge> edges;

    public Graph() {
        this.edges=new ArrayList<Edge>();
        this.nodes=new ArrayList<Node>();
    }

    public void addNode(Node node){
        nodes.add(node);
    }

    public void removeNode(Node node){
        nodes.remove(node);
    }
}
