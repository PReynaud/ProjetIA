package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.MissingParameterException;
import com.polytech4a.robocup.graph.model.exceptions.NotFoundTypeException;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dimitri on 16/05/2015.
 * @version 1.0
 *
 * Astar path finder
 */
public class AStar extends SearchAlgorithm {

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
            e.printStackTrace();
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
        return new Way();
    }

    @Override
    public Way wayToNodeWithoutParam(Graph graph, Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes) throws SearchException {
        //Initialisation
        openNodes.add(begin);
        costs.put(begin, 0.0);
        try {
            fitness.put(begin, begin.getEuclidianSpace(end));
        } catch (MissingParameterException e) {
            e.printStackTrace();
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
        return new Way();
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
    protected double getCostSwitchTypes(EdgeType edgeType, NodeType nodeType) {
        //TODO : definir le cout de passage d un noeud a l autre en fonction des types.
        return 0;
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

    private void updateFitness(Node node, Node currentNode, Node end, Graph graph) throws SearchException {
        Node parent = parentNodes.get(node);
        Double oldCost = costs.get(node), newCost;
        try {
            newCost = costs.get(currentNode) + getCostSwitchTypes(graph.getEdge(node, currentNode).getType(), node.getType()) + currentNode.getEuclidianSpace(node);
        } catch (NotFoundTypeException e) {
            throw new SearchException("AStar.updateFitness : The node " + node.toString() + " has an unhandledParameter : \n" + e.getMessage());
        } catch (MissingParameterException e) {
            throw new SearchException("AStar.updateFitness : The node " + node.toString() + " has no parameter type : \n" + e.getMessage());
        }
        if (!openNodes.contains(node) || (parent != null && oldCost != null && oldCost > newCost)) {
            parentNodes.put(node, currentNode);
            costs.put(node, newCost);
            fitness.put(node, getCostValue(node) + getHeuristicValue(node, end));
            if (!openNodes.contains(node)) {
                openNodes.add(node);
            }
        }
    }
}
