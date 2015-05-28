package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by Antoine CARON on 19/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class AStarTest extends HeuristicCostSearchTest {

    @Before
    public void setUp() throws Exception {
        setGraph();
        this.searchAlgorithm = new AStar();
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
    public void testWayToNotAcceptableNode() throws Exception {

        ArrayList<NodeType> restrictedNodes = new ArrayList<>();
        ArrayList<EdgeType> restrictedEdges = new ArrayList<>();
        restrictedNodes.add(NodeType.NORMAL);
        restrictedEdges.add(EdgeType.PLAT);
        Way path = searchAlgorithm.wayToNodeWithParam(graph, graph.getNode(2), graph.getNode(4), restrictedNodes, restrictedEdges);
        assertEquals(Double.NEGATIVE_INFINITY, path.getDistance(), 0.001);
        assertEquals(0, path.getNodes().size());
    }

    @Test
    public void testNotFoundWay() throws Exception {
        ArrayList<NodeType> restrictedNodes = new ArrayList<>();
        ArrayList<EdgeType> restrictedEdges = new ArrayList<>();
        restrictedNodes.add(NodeType.NORMAL);
        restrictedEdges.add(EdgeType.PLAT);
        Way path = searchAlgorithm.wayToNodeWithParam(graph, graph.getNode(7), graph.getNode(8), restrictedNodes, restrictedEdges);
        assertEquals(Double.NEGATIVE_INFINITY, path.getDistance(), 0.001);
        assertEquals(0,path.getNodes().size());
    }

    @Test
    public void testNullWhiteList() throws Exception {
        Way path = searchAlgorithm.wayToNodeWithParam(graph, graph.getNode(2), graph.getNode(0), null, null);
        assertEquals(3, path.getDistance(), 0.001);
        assertEquals(1, path.getNodes().size());
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

    @Test
    public void testNoWay() throws Exception {
        Way path = searchAlgorithm.wayToNodeWithParam(graph, graph.getNode(7), graph.getNode(8), null, null);
        assertEquals(Double.NEGATIVE_INFINITY, path.getDistance(), 0.001);
        assertEquals(0, path.getNodes().size());
    }

    @Test
    public void testFirstNode() throws Exception {
        Way path = searchAlgorithm.wayToNodeWithParam(graph, graph.getNode(7), graph.getNode(7), null, null);
        assertEquals(0,path.getDistance(),0.001);
        assertEquals(0, path.getNodes().size());
    }
}