package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.MissingParameterException;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Dimitri on 16/05/2015.
 * @version 1.0
 *          <p/>
 *          Path finding abstract class
 */
public abstract class SearchAlgorithm implements ISearch {

    private static final Logger logger = Logger.getLogger(SearchAlgorithm.class);
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
     * Constructor for the search algorithm
     */
    public SearchAlgorithm() {
        this.openNodes = new ArrayList<>();
        this.coveredNodes = new ArrayList<>();
        this.parentNodes = new HashMap<>();
    }


    public abstract Way wayToNodeWithParam(Graph graph, Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes) throws SearchException;

    public abstract Way wayToNodeWithoutParam(Graph graph, Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes) throws SearchException;

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

    /**
     * Get the path from the source node to the input node
     *
     * @param node node to get the path to
     * @return the path to the input node
     */
    protected Way recoverPath(Node node) {
        ArrayList<Node> result = new ArrayList<>();
        Double distance = new Double(0);
        result.add(node);
        Node currentNode = parentNodes.get(node);
        try {
            distance += node.getEuclidianSpace(currentNode);
            while (currentNode != null) {
                Node temp= parentNodes.get(currentNode);
                result.add(0, currentNode);
                if(temp!=null){
                    distance = distance + temp.getEuclidianSpace(currentNode);
                }
                currentNode =temp;
            }
        } catch (MissingParameterException e) {
            logger.trace("Euclidian Space Failed", e);
        }

        Way way=new Way(distance,result);
        return way;
    }

    /**
     * Clear all the array attributes of the search
     */
    protected void clearAll() {
        openNodes.clear();
        coveredNodes.clear();
        parentNodes.clear();
    }
}
