package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author Dimitri on 27/05/2015.
 * @version 1.0
 */
@Ignore
public abstract class HeuristicCostSearchTest extends SearchAlgorithmTest {
    @Test(expected = SearchException.class)
    public void testCorruptedBegin() throws Exception {
        Node begin = graph.getNode(0);
        graph.getNode(0).getParameters().remove("x");
        searchAlgorithm.wayToNodeWithoutParam(graph, begin, graph.getNode(7), null, null);
    }

    @Test(expected = SearchException.class)
    public void testCorruptedNodePosition() throws Exception {
        Node begin = graph.getNode(1);
        graph.getNode(0).getParameters().remove("x");
        searchAlgorithm.wayToNodeWithoutParam(graph, begin, graph.getNode(7), null, null);
    }
}