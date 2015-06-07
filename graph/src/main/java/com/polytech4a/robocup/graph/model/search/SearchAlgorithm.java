package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.MissingParameterException;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;
import org.apache.log4j.Logger;

import java.util.*;

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
    protected Collection<Node> coveredNodes;

    /**
     * Node still to process
     */
    protected Collection<Node> openNodes;

    /**
     * Associate a node to its parent
     */
    protected HashMap<Node, Node> parentNodes;


    /**
     * Constructor for the search algorithm
     */
    public SearchAlgorithm() {
        this.parentNodes = new HashMap<>();
    }


    public Way wayToNodeWithParam(Graph graph, Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes) throws SearchException{
        return findBestWay(graph,begin,end,nodeTypes,edgeTypes,true);
    }

    public Way wayToNodeWithoutParam(Graph graph, Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes) throws SearchException{
        return findBestWay(graph,begin,end,nodeTypes,edgeTypes,false);
    }

    /**
     * Get the path from the source node to the input node
     *
     * @param node node to get the path to
     * @return the path to the input node
     */
    protected Way recoverPath(Node node) {
        ArrayList<Node> result = new ArrayList<>();
        Node currentNode = node;
        Double distance = new Double(0);
        try {
            Node temp = parentNodes.get(currentNode);
            while (temp != null) {
                result.add(0, currentNode);
                distance += temp.getEuclidianSpace(currentNode);
                currentNode = temp;
                temp = parentNodes.get(currentNode);
            }
        } catch (MissingParameterException e) {
            logger.trace("Euclidian Space Failed", e);
        }
        Way way=new Way(distance,result);
        return way;
    }

    /**
     * Clear all the attributes of the search
     */
    protected void clearAll() {
        openNodes.clear();
        coveredNodes.clear();
        parentNodes.clear();
    }

    /**
     * Test if currentNode is the target Node and return
     * the Way to access it.
     * @param currentNode actual Node
     * @param end target Node
     * @return the way from the begin node to the target or empty Way
     */
    protected Way endTest(Node currentNode, Node end){
        if (currentNode.equals(end)) {
            Way resultPath = recoverPath(currentNode);
            clearAll();
            return resultPath;
        }
        return null;
    }

    /**
     * Initialize the search in adding the first node to openNodes
     * @param begin first Node of the search
     * @throws SearchException
     */
    protected void initialisation(Node begin) throws SearchException {
        openNodes.add(begin);
    }

    /**
     * Return the next node in openNode to process
     * @return node to process
     */
    protected abstract Node getNextNode();

    /**
     * Function that add the current node to the openNodes and
     * update its parent.
     * @param parent parent Node
     * @param neighbour neighbour Node
     * @throws SearchException
     */
    protected void processNeighbour(Node parent, Node neighbour) throws SearchException {
        parentNodes.put(neighbour, parent);
        openNodes.add(neighbour);
    }

    /**
     * Search the way from the node begin to the node end matching the parameters
     *
     * @param graph     graph of the search
     * @param begin     start node
     * @param end       objective node
     * @param nodeTypes parameters for the nodes
     * @param edgeTypes parameters for the edges
     * @param whiteList boolean that tells if the parameters are used as
     *                  whiteList (true) or blackList (false)
     * @return the way found or empty way
     */
    protected Way findBestWay (Graph graph, Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes, boolean whiteList) throws SearchException {
        Graph clonedGraph = graph.clone();
        initialisation(begin);
        while (!this.openNodes.isEmpty()) {
            Node currentNode = getNextNode();
            Way result = endTest(currentNode, end);
            if (!(result ==null)){
                return result;
            }
            coveredNodes.add(currentNode);

            if (whiteList){
                for (Node node : clonedGraph.getNeighboursFromNodeWithParam(currentNode, nodeTypes, edgeTypes)) {
                    if (!coveredNodes.contains(node)) {
                        processNeighbour(currentNode,node);
                    }
                }
            }else{
                for (Node node : clonedGraph.getNeighboursFromNodeWithoutParam(currentNode, nodeTypes, edgeTypes)) {
                    if (!coveredNodes.contains(node)) {
                        processNeighbour(currentNode,node);
                    }
                }
            }
        }
        clearAll();
        Way result = new Way();
        result.setDistance(Double.NEGATIVE_INFINITY);
        return result;
    }
}
