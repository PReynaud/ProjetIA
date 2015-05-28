package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.MissingParameterException;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Dimitri on 16/05/2015.
 * @version 1.0
 *
 * Astar path finder
 */
public class AStar extends HeuristicCostSearch {

    /**
     * Associate a node to its cost
     */
    protected HashMap<Node, Double> costs;

    /**
     * Associate a node to its fitness
     */
    protected HashMap<Node, Double> fitness;

    /**
     * Constructor for the AStar class
     */
    public AStar() {
        super();
        this.fitness = new HashMap<>();
        this.costs = new HashMap<>();
    }

    @Override
    public Way wayToNodeWithParam(Graph graph, Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes) throws SearchException {
        //Initialisation
        openNodes.add(begin);
        costs.put(begin, 0.0);
        try {
            fitness.put(begin, begin.getEuclidianSpace(end));
        } catch (MissingParameterException e) {
            throw new SearchException("AStar.wayToNodeWithParam : error with the begin and end nodes :" + e.getMessage());
        }

        while (!this.openNodes.isEmpty()) {
            Node currentNode = openNodes.remove(0);

            //End test
            if (currentNode.equals(end)) {
                Way resultPath = recoverPath(currentNode);
                clearAll();
                return resultPath;
            }
            coveredNodes.add(currentNode);

            //Get the neighbours, update Costs, Fitness and Parents
            for (Node node : graph.getNeighboursFromNodeWithParam(currentNode, nodeTypes, edgeTypes)) {
                if (!coveredNodes.contains(node)) {
                    updateFitness(node, currentNode, end, graph);
                }
            }

            //Sort new openList
            openNodes.sort((s1, s2) -> Double.compare(getFitnessValue(s1), getFitnessValue(s2)));
        }
        clearAll();
        Way result = new Way();
        result.setDistance(Double.NEGATIVE_INFINITY);
        return result;
    }

    @Override
    public Way wayToNodeWithoutParam(Graph graph, Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes) throws SearchException {
        //Initialisation
        openNodes.add(begin);
        costs.put(begin, 0.0);
        try {
            fitness.put(begin, begin.getEuclidianSpace(end));
        } catch (MissingParameterException e) {
            throw new SearchException("AStar.wayToNodeWithParam : error with the begin and end nodes :" + e.getMessage());
        }

        while (!this.openNodes.isEmpty()) {
            Node currentNode = openNodes.remove(0);

            //End test
            if (currentNode.equals(end)) {
                Way resultPath = recoverPath(currentNode);
                clearAll();
                return resultPath;
            }
            coveredNodes.add(currentNode);

            //Get the neighbours, update Costs, Fitness and Parents
            for (Node node : graph.getNeighboursFromNodeWithoutParam(currentNode, nodeTypes, edgeTypes)) {
                if (!coveredNodes.contains(node)) {
                    updateFitness(node, currentNode, end, graph);
                }
            }

            //Sort new openList
            openNodes.sort((s1, s2) -> Double.compare(getFitnessValue(s1), getFitnessValue(s2)));
        }
        clearAll();
        Way result = new Way();
        result.setDistance(Double.NEGATIVE_INFINITY);
        return result;
    }

    @Override
    protected double getHeuristicValue(Node node, Node target) throws SearchException {
        try {
            return node.getEuclidianSpace(target);
        } catch (MissingParameterException e) {
            throw new SearchException("AStar.getHeuristicValue : " + e.getMessage());
        }
    }

    @Override
    protected double getCostValue(Node node) {
        return costs.get(node);
    }

    @Override
    protected double getFitnessValue(Node node) {
        return fitness.get(node);
    }

    @Override
    protected void clearAll() {
        super.clearAll();
        fitness.clear();
        costs.clear();
    }

    /**
     * Update the fitness value of a node.
     * Corresponds to the sum of the heuristic value and
     * the cost of going to the node from its parent
     *
     * @param node       current node
     * @param parentNode parent node
     * @param targetNode objective node
     * @param graph      graph of the search
     * @throws SearchException
     */
    private void updateFitness(Node node, Node parentNode, Node targetNode, Graph graph) throws SearchException {
        Node parent = parentNodes.get(node);
        Double oldCost = costs.get(node), newCost;
        try {
            newCost = costs.get(parentNode) + parentNode.getEuclidianSpace(node);
        } catch (MissingParameterException e) {
            throw new SearchException("AStar.updateFitness : The node " + node.toString() + " has no parameter type : \n" + e.getMessage());
        }
        if (!openNodes.contains(node) || (parent != null && oldCost != null && oldCost > newCost)) {
            parentNodes.put(node, parentNode);
            costs.put(node, newCost);
            fitness.put(node, getCostValue(node) + getHeuristicValue(node, targetNode));
            if (!openNodes.contains(node)) {
                openNodes.add(node);
            }
        }
    }
}
