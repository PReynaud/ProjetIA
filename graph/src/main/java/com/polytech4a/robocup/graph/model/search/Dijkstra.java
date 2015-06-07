package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;

import java.util.LinkedList;

/**
 * Created by Antoine CARON on 27/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public final class Dijkstra extends SearchAlgorithm {


    public Dijkstra() {
        super();
        this.openNodes = new LinkedList<>();
        this.coveredNodes = new LinkedList<>();
    }

    @Override
    protected Node getNextNode() {
        return ((LinkedList<Node>) openNodes).poll();
    }
}
