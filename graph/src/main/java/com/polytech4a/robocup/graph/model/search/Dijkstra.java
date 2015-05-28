package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;

import java.util.ArrayList;

/**
 * Created by Antoine CARON on 27/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public final class Dijkstra extends SearchAlgorithm {


    public Dijkstra() {
        super();
    }

    @Override
    public Way wayToNodeWithParam(Graph graph, Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes) throws SearchException {
        openNodes.add(begin);

        while (!openNodes.isEmpty()) {
            Node n = openNodes.remove(0);
            coveredNodes.add(n);
            if (n.equals(end)) {
                Way way = recoverPath(n);
                clearAll();
                return way;
            }
            ArrayList<Node> neighbours = graph.getNeighboursFromNodeWithParam(n, nodeTypes, edgeTypes);
            for (Node nei : neighbours) {
                if (!coveredNodes.contains(nei)) {
                    openNodes.add(nei);
                    parentNodes.put(nei, n);
                }
            }
        }
        clearAll();
        Way result = new Way();
        result.setDistance(Double.NEGATIVE_INFINITY);
        return result;
    }

    @Override
    public Way wayToNodeWithoutParam(Graph graph, Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes) throws SearchException {
        openNodes.add(begin);

        while (!openNodes.isEmpty()) {
            Node n = openNodes.remove(0);
            coveredNodes.add(n);
            if (n.equals(end)) {
                Way way = recoverPath(n);
                clearAll();
                return way;
            }
            ArrayList<Node> neighbours = graph.getNeighboursFromNodeWithoutParam(n, nodeTypes, edgeTypes);
            for (Node nei : neighbours) {
                if (!coveredNodes.contains(nei)) {
                    openNodes.add(nei);
                    parentNodes.put(nei, n);
                }
            }
        }
        clearAll();
        Way result = new Way();
        result.setDistance(Double.NEGATIVE_INFINITY);
        return result;
    }
}
