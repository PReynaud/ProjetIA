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
     * Graph.
     */
    protected Graph graph;

    /**
     * Constructor for the search algorithm
     *
     */
    public SearchAlgorithm(Graph graph) {
        this.openNodes = new ArrayList<>();
        this.coveredNodes = new ArrayList<>();
        this.parentNodes = new HashMap<>();
        this.costs = new HashMap<>();
        this.graph = graph;
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
