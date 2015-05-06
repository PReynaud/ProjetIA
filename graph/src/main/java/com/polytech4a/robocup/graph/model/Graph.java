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

    public void addEdge(Edge edge){
        edges.add(edge);
    }

    public void removeEdge (Edge edge){
        edges.remove(edge);
    }

    public Node getNode (int idNode){
        return nodes.get(idNode);
    }

    public Edge getEdge (Node node1, Node node2){
        int idN1 =  node1.getId(), idN2= node2.getId();
        return edges.parallelStream()
                .filter(e ->
                        (e.getNode1() == idN1 && e.getNode2() == idN2) ||
                                (e.getNode1() == idN2 && e.getNode2() == idN1))
                .findAny()
                .get();
    }

    public ArrayList<Node> getNodes (){
        ArrayList<Node> clonedNodes = new ArrayList<Node>();
        for (Node node : nodes){
            clonedNodes.add(node.clone());
        }
        return clonedNodes;
    }

    public ArrayList<Edge> getEdges (){
        ArrayList<Edge> clonedEdges = new ArrayList<Edge>();
        for (Edge edge : edges){
            clonedEdges.add(edge.clone());
        }
        return clonedEdges;
    }


}
