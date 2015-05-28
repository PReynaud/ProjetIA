package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Dimitri on 27/05/2015.
 * @version 1.0
 */

public class DijkstraTest extends SearchAlgorithmTest {

    @Before
    public void setUp() throws Exception {
        setGraph();
        this.searchAlgorithm = new Dijkstra();
    }

    @Test
    public void testWayToNodeWithParam() throws Exception {
        ArrayList<NodeType> restrictedNodes = new ArrayList<>();
        ArrayList<EdgeType> restrictedEdges = new ArrayList<>();
        restrictedNodes.add(NodeType.NORMAL);
        restrictedEdges.add(EdgeType.PLAT);
        Way path = searchAlgorithm.wayToNodeWithParam(graph, graph.getNode(2), graph.getNode(3), restrictedNodes, restrictedEdges);
        assertEquals(9, path.getDistance(), 0.001);
        assertEquals(3, path.getNodes().size());
    }

    @Test
    public void testWayToNodeWithoutParam() throws Exception {
        ArrayList<NodeType> restrictedNodes = new ArrayList<>();
        ArrayList<EdgeType> restrictedEdges = new ArrayList<>();
        restrictedNodes.add(NodeType.INCENDIE);
        restrictedEdges.add(EdgeType.ESCARPE);
        Way path = searchAlgorithm.wayToNodeWithoutParam(graph, graph.getNode(5), graph.getNode(3), restrictedNodes, restrictedEdges);
        assertEquals(2, path.getNodes().size());
        assertFalse(path.getNodes().contains(graph.getNode(4)));
    }
}