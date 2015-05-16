package com.polytech4a.robocup.graph.robocup;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.robocup.exceptions.NotFoundTypeException;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Antoine CARON on 11/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class Graph extends com.polytech4a.robocup.graph.model.Graph {

    public Graph() {
        super();
    }

    /**
     * Get the List of Nodes in the closed area of an input node
     * @param node center of the linked nodes
     * @return Linked Nodes
     */
    public ArrayList<Node> getNeighboursFromNode(Node node) {
        return (ArrayList<Node>)(ArrayList<?>)super.getNeighboursFromNode(node);
    }


    public ArrayList<Edge> getEdgesFromNode(Node node) {
        return (ArrayList<Edge>)(ArrayList<?>)super.getNeighboursFromNode(node);
    }

    public ArrayList<Node> getNeighboursFromNodeWithParam(Node node, NodeType type){
        return (ArrayList<Node>) getNeighboursFromNode(node)
                .parallelStream().filter(s -> {
            try {
                return s.getType().equals(type);
            } catch (NotFoundTypeException e) {
                return false;
            }
        }).collect(Collectors.toList());
    }

    public ArrayList<Edge> getEdgesFromNodeWithParam (Node node, EdgeType type){
        return (ArrayList<Edge>) getEdgesFromNode(node).parallelStream().filter(s -> {
            try {
                return s.getType().equals(type);
            } catch (NotFoundTypeException e) {
                return false;
            }
        }).collect(Collectors.toList());
    }

    public ArrayList<Node> getNeighboursFromNodeWithoutParam(Node node, NodeType type){
        return (ArrayList<Node>) getNeighboursFromNode(node).parallelStream().filter(s -> {
            try {
                return !s.getType().equals(type);
            } catch (NotFoundTypeException e) {
                return false;
            }
        }).collect(Collectors.toList());
    }

    public ArrayList<Edge> getEdgesFromNodeWithoutParam (Node node, EdgeType type){
        return (ArrayList<Edge>) getEdgesFromNode(node).parallelStream().filter(s -> {
            try {
                return !s.getType().equals(type);
            } catch (NotFoundTypeException e) {
                return false;
            }
        }).collect(Collectors.toList());
    }
}
