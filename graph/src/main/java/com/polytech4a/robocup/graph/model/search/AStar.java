package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.MissingParameterException;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;

import java.util.*;

/**
 * @author Dimitri on 16/05/2015.
 * @version 1.0
 *
 * Astar path finder
 */
public class AStar extends HeuristicCostSearch {

    /**
     * Constructor for the AStar class
     */
    public AStar() {
        super();
        this.openNodes = new LinkedList<>();
        this.coveredNodes = new LinkedList<>();
    }

    @Override
    protected Node getNextNode(){
        return ((LinkedList<Node>) openNodes).poll();
    }

    @Override
    protected double getHeuristicValue(Node node, Node target) throws SearchException {
        try {
            return node.getEuclidianSpace(target);
        } catch (MissingParameterException e) {
            throw new SearchException("AStar.getHeuristicValue : " + e.getMessage());
        }
    }

    /**
     * Update the fitness value of a node.
     * Corresponds to the sum of the heuristic value and
     * the cost of going to the node from its parent
     * Also sort the openNodes to be ready for the next poll
     *
     * @param neighbour current node
     * @param parent    parent node
     * @throws SearchException
     */
    @Override
    protected void processNeighbour(Node parent, Node neighbour) throws SearchException {
        Node parentNode1 = parentNodes.get(neighbour);
        Double oldCost = costs.get(neighbour), newCost;
            newCost = costs.get(parent) + getHeuristicValue(parent,neighbour);
        if (!openNodes.contains(neighbour) || (parentNode1 != null && oldCost != null && oldCost > newCost)) {
            parentNodes.put(neighbour, parent);
            costs.put(neighbour, newCost);
            Double newfitness =  newCost + getHeuristicValue(neighbour, targetNode);
            fitness.put(neighbour,newfitness);
            if (!openNodes.contains(neighbour)) {
                openNodes.add(neighbour);
                ((LinkedList<Node>)openNodes).sort((n1,n2)->(int)(getFitnessValue(n1)-getFitnessValue(n2)));
            }
        }
    }
}
