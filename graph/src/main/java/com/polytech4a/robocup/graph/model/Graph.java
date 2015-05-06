package com.polytech4a.robocup.graph.model;

import java.util.ArrayList;
import java.util.NoSuchElementException;

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

    /**
     * Constructor for the Graph class
     */
    public Graph() {
        this.edges=new ArrayList<Edge>();
        this.nodes=new ArrayList<Node>();
    }

    /**
     * Add node to the graph if the node doesn't exists
     * @param node Node to add
     */
    public void addNode(Node node){
        try{
            getNode(node.getId());
        }catch (NoSuchElementException e){
            nodes.add(node);
        }
    }

    /**
     * Remove node from the graph
     * @param node Node to remove
     */
    public void removeNode(Node node){
        nodes.remove(node);
    }

    /**
     * Add edge to the graph if the edge doesn't exist yet
     * @param edge Edge to add
     */
    public void addEdge(Edge edge){
        try{
            getEdge(getNode(edge.getNode1()), getNode(edge.getNode2()));
        }catch (NoSuchElementException e){
            edges.add(edge);
        }
    }

    /**
     * Add edge to the graph between the two nodes if the
     * edge doesn't exist yet
     * @param node1 First node of the edge
     * @param node2 Second node of the edge
     */
    public void addEdge(Node node1, Node node2){
        try{
            getEdge(node1,node2);
        }catch (NoSuchElementException e){
            edges.add(new Edge(node1.getId(),node2.getId()));
        }
    }

    /**
     * Remove edge from the graph
     * @param edge Edge to remove
     */
    public void removeEdge (Edge edge){
        edges.remove(edge);
    }

    /**
     * Get the idNode node of the graph
     * @param idNode id of the Node
     * @return idNode Node
     */
    public Node getNode (int idNode){
        return nodes.parallelStream().filter(n->n.getId()==idNode).findAny().get();
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
