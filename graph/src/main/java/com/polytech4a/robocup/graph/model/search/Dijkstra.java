package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;

import java.util.ArrayList;
import java.util.Queue;

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
        Queue<Node> opens = (Queue<Node>) openNodes;
        Queue<Node> covers = (Queue<Node>) coveredNodes;
        opens.add(begin);
        covers.add(begin);

        while (!opens.isEmpty()) {
            Node n = opens.poll();
            covers.add(n);
            if (n.equals(end)) {
                Way way = recoverPath(n);
                clearAll();
                return way;
            }
            ArrayList<Node> neighbours = graph.getNeighboursFromNodeWithParam(n, nodeTypes, edgeTypes);
            for (Node nei : neighbours) {
                if (!covers.contains(nei)) {
                    opens.add(nei);
                    parentNodes.put(nei, n);
                }
            }
        }
        return new Way();
    }

    @Override
    public Way wayToNodeWithoutParam(Graph graph, Node begin, Node end, ArrayList<NodeType> nodeTypes, ArrayList<EdgeType> edgeTypes) throws SearchException {
        Queue<Node> opens = (Queue<Node>) openNodes;
        Queue<Node> covers = (Queue<Node>) coveredNodes;
        opens.add(begin);
        covers.add(begin);

        while (!opens.isEmpty()) {
            Node n = opens.poll();
            covers.add(n);
            if (n.equals(end)) {
                Way way = recoverPath(n);
                clearAll();
                return way;
            }
            ArrayList<Node> neighbours = graph.getNeighboursFromNodeWithoutParam(n, nodeTypes, edgeTypes);
            for (Node nei : neighbours) {
                if (!covers.contains(nei)) {
                    opens.add(nei);
                    parentNodes.put(nei, n);
                }
            }
        }
        return new Way();
    }

    @Override
    protected double getHeuristicValue(Node node, Node target) throws SearchException {
        return 0;
    }


    @Override
    protected double getCostSwitchTypes(EdgeType edgeType, NodeType nodeType) {
        return 0;
    }

    @Override
    protected double getCostValue(Node node) {
        return 0;
    }

    @Override
    protected double getFitnessValue(Node node) {
        return 0;
    }
}
