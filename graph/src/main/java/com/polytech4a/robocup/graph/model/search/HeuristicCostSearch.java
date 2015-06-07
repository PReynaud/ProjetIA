package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Dimitri on 27/05/2015.
 * @version 1.0
 */
public abstract class HeuristicCostSearch extends SearchAlgorithm {

    /**
     * Associate a node to its cost
     */
    protected HashMap<Node, Double> costs;

    /**
     * Associate a node to its fitness
     */
    protected HashMap<Node, Double> fitness;


    protected Node targetNode;
    /**
     * Constructor for Heuristic Cost Search
     */
    public HeuristicCostSearch() {
        super();
        this.fitness = new HashMap<>();
        this.costs = new HashMap<>();
        this.targetNode = new Node(null);
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
     * Get the cost of moving from a node to its neighbour
     * Function that depends on the heuristic and cost functions
     *
     * @param node current node
     * @return cost of the move
     */
    protected double getFitnessValue(Node node){
        return fitness.get(node);
    }

    protected void initialisation (Node begin) throws SearchException {
        super.initialisation(begin);
        costs.put(begin, 0.0);
        fitness.put(begin, getHeuristicValue(begin,targetNode));
    }

    @Override
    protected Way findBestWay (Graph graph, Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes, boolean whiteList) throws SearchException {
        targetNode = end;
        return super.findBestWay(graph,begin,end,nodeTypes,edgeTypes,whiteList);
    }

    @Override
    protected void clearAll() {
        super.clearAll();
        fitness.clear();
        costs.clear();
    }
}
