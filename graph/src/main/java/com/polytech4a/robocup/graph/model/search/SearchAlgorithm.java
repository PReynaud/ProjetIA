package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Edge;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.MissingParameterException;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dimitri on 16/05/2015.
 */
public abstract class SearchAlgorithm implements ISearch {

    /**
     * Graph for the search
     */
    protected Graph graph;

    /**
     * Nodes covered by the algorithm so far
     */
    protected ArrayList<Node> coveredNodes;

    /**
     * Node still to process
     */
    protected ArrayList<Node> openNodes;

    /**
     * Associate a node to its parent
     */
    protected HashMap<Node, Node> parentNodes;

    /**
     * Associate a node to its cost
     */
    protected HashMap<Node, Double> costs;

    /**
     * Constructor for the search algorithm
     *
     * @param graph Graph to test
     */
    public SearchAlgorithm(Graph graph) {
        this.graph = graph;
        this.openNodes = new ArrayList<>();
        this.coveredNodes = new ArrayList<>();
        this.parentNodes = new HashMap<>();
        this.costs = new HashMap<>();
    }


    public abstract ArrayList<Node> wayToNodeWithParam(Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes);

    public abstract ArrayList<Node> wayToNodeWithoutParam(Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes);

    /**
     * Heuristic function for graph search
     *
     * @param node   current node
     * @param target objective node
     * @return value of the heuristic function
     * @throws MissingParameterException
     * @throws SearchException
     */
    protected abstract double getHeuristicValue(Node node, Node target) throws MissingParameterException, SearchException;

    /**
     * Get the cost associate with the type a node and its incoming edge
     *
     * @param edgeType type of the edge
     * @param nodeType type of the node
     * @return cost of going to the node
     */
    protected abstract double getCostSwitchTypes(EdgeType edgeType, NodeType nodeType);

    /**
     * Cost function for the graph search
     *
     * @param node node to test
     * @return cost of going to the node
     * @throws MissingParameterException
     * @throws SearchException
     */
    protected abstract double getCostValue(Node node) throws MissingParameterException, SearchException;

    /**
     * Get the cost of moving from a node to its neighbour
     * Function that depends on the heuristic and cost functions
     *
     * @param node      current node
     * @param neighbour objective node
     * @return cost of the move
     * @throws MissingParameterException
     * @throws SearchException
     */
    protected abstract double getFitnessValue(Node node, Node neighbour) throws MissingParameterException, SearchException;

    /**
     * Check if the node has all the required parameters
     *
     * @param node       node to test
     * @param parameters parameters the node should have
     * @return true if the node has every parameter required
     */
    protected boolean hasNodeParams(Node node, ArrayList<NodeType> parameters) {
        for (NodeType parameter : parameters) {
            if (!node.getParameters().get("type").equals(parameter.name())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the node has none of the parameters
     *
     * @param node       node to test
     * @param parameters parameters the node should not have
     * @return true if the node has none of the parameters
     */
    protected boolean hasNotNodeParams(Node node, ArrayList<NodeType> parameters) {
        for (NodeType parameter : parameters) {
            if (node.getParameters().get("type").equals(parameter.name())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the edge has all the required parameters
     *
     * @param edge       edge to test
     * @param parameters parameters the edge should have
     * @return true if the edge has every parameter required
     */
    protected boolean hasEdgeParams(Edge edge, ArrayList<EdgeType> parameters) {
        for (EdgeType parameter : parameters) {
            if (!edge.getParameters().get("type").equals(parameter.name())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if the edge has none of the parameters
     *
     * @param edge       edge to test
     * @param parameters parameters the edge should not have
     * @return true if the edge has none of the parameters
     */
    protected boolean hasNotEdgeParams(Edge edge, ArrayList<EdgeType> parameters) {
        for (EdgeType parameter : parameters) {
            if (edge.getParameters().get("type").equals(parameter.name())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get the path from the source node to the input node
     *
     * @param node node to get the path to
     * @return the path to the input node
     */
    protected ArrayList<Node> recoverPath(Node node) {
        ArrayList<Node> result = new ArrayList<>();
        Node currentNode = parentNodes.get(node);
        result.add(node);
        while (currentNode != null) {
            result.add(currentNode);
            currentNode = parentNodes.get(currentNode);
        }
        return result;
    }
}
