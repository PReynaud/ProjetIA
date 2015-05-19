package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.MissingParameterException;
import com.polytech4a.robocup.graph.model.exceptions.NotFoundTypeException;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Created by Dimitri on 16/05/2015.
 */
public class AStar extends SearchAlgorithm {

    /**
     * Constructor for the AStar class
     *
     * @param graph Graph to test
     */
    public AStar(Graph graph) {
        super(graph);
    }

    @Override
    public ArrayList<Node> wayToNodeWithParam(Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes) {
        openNodes.add(begin);
        costs.put(begin, 0.0);


        while (!this.openNodes.isEmpty()) {
            Node currentNode = openNodes.remove(0);
            if (currentNode.equals(end)) {
                return recoverPath(currentNode);
            }
            coveredNodes.add(currentNode);

            openNodes.addAll(graph.getNeighboursFromNode(currentNode)
                    .parallelStream()
                    .filter(n -> (!coveredNodes.contains(n)
                            && n.isNodeFromType(nodeTypes)
                            && graph.getEdge(currentNode, n).isEdgeFromType(edgeTypes)))
                    .map(s -> parentNodes.put(currentNode, s))
                    .sorted((s1, s2) -> {
                        try {
                            if (getFitnessValue(currentNode, s2) - getFitnessValue(currentNode, s1) >= 0) return 1;
                            return -1;
                        } catch (SearchException e) {
                            //TODO : Catch error
                            return 1;
                        }
                    })
                    .distinct()
                    .collect(Collectors.toList()));

        }
        return new ArrayList<>();
    }

    @Override
    public ArrayList<Node> wayToNodeWithoutParam(Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes) {
        openNodes.add(begin);
        costs.put(begin, 0.0);


        while (!this.openNodes.isEmpty()) {
            Node currentNode = openNodes.remove(0);
            if (currentNode.equals(end)) {
                return recoverPath(currentNode);
            }
            coveredNodes.add(currentNode);

            openNodes.addAll(graph.getNeighboursFromNode(currentNode)
                    .parallelStream()
                    .filter(n -> (!coveredNodes.contains(n)
                            && !n.isNodeFromType(nodeTypes)
                            && !graph.getEdge(currentNode, n).isEdgeFromType(edgeTypes)))
                    .map(s -> parentNodes.put(currentNode, s))
                    .sorted((s1, s2) -> {
                        try {
                            if (getFitnessValue(currentNode, s2) - getFitnessValue(currentNode, s1) >= 0) return 1;
                            return -1;
                        } catch (SearchException e) {
                            //TODO : Catch error
                            return 1;
                        }
                    })
                    .distinct()
                    .collect(Collectors.toList()));

        }
        return new ArrayList<>();
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
        return 1;
    }

    @Override
    protected double getCostValue(Node node) throws SearchException {
        Node parent = parentNodes.get(node);
        if (parent != null) {
            try {
                return costs.put(node, costs.get(parent) + getCostSwitchTypes(graph.getEdge(node, parent).getType(), node.getType()));
            } catch (NoSuchElementException e) {
                e.printStackTrace();
            } catch (NotFoundTypeException e) {
                throw new SearchException("AStar.getCostValue : The node " + node.toString() + " has an unhandledParameter : \n" + e.getMessage());
            } catch (MissingParameterException e) {
                throw new SearchException("AStar.getCostValue : The node " + node.toString() + " has no parameter type : \n" + e.getMessage());
            }
        }
        throw new SearchException("AStar.getCostValue : The node " + node.toString() + " should have a parent");
    }

    @Override
    protected double getFitnessValue(Node node, Node neighbour) throws SearchException {
        return getCostValue(node) + getHeuristicValue(node, neighbour) + getHeuristicValue(node, neighbour);
    }
}
