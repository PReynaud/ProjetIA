package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;

/**
 * @author Dimitri on 27/05/2015.
 * @version 1.0
 */
public abstract class HeuristicCostSearch extends SearchAlgorithm {

    /**
     * Constructor for Heuristic Cost Search
     */
    public HeuristicCostSearch() {
        super();
    }

    /**
     * Heuristic function for graph search
     *
     * @param node   current node
     * @param target objective node
     * @return value of the heuristic function
     * @throws SearchException
     */
    protected abstract double getHeuristicValue(Node node, Node target) throws SearchException;

    /**
     * Cost function for the graph search
     *
     * @param node node to test
     * @return cost of going to the node
     */
    protected abstract double getCostValue(Node node);

    /**
     * Get the cost of moving from a node to its neighbour
     * Function that depends on the heuristic and cost functions
     *
     * @param node current node
     * @return cost of the move
     */
    protected abstract double getFitnessValue(Node node);
}
