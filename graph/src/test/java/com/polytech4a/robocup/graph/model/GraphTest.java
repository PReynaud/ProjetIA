package com.polytech4a.robocup.graph.model;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dimitri on 11/05/2015.
 */
public class GraphTest {
    private Graph graph;

    @Before
    public void setUp() throws Exception {
        graph = new Graph();
    }

    @After
    public void tearDown() throws Exception {
        graph = null;
    }

    @Test
    public void testAddNode() throws Exception {
        Node node = new Node(0, 0, 0, NodeType.PLAT);
        graph.addNode(node);
        assertEquals("Test add Node", 1, graph.getNumberOfNodes());

    }

    @Test
    public void testAddEdge() throws Exception {
        Node node1 = new Node(0, 0, 0, NodeType.PLAT), node2 = new Node(1, 0, 0, NodeType.PLAT);
        Edge edge = new Edge(0, 1, EdgeType.ESCARPE);
        graph.addEdge(edge);
        assertEquals("Test add Edge", 1, graph.getEdges().size());

    }

    @Test
    public void testAddEdgeWithTwoNodes() throws Exception {
        Node node1 = new Node(0, 0, 0, NodeType.PLAT), node2 = new Node(1, 0, 0, NodeType.PLAT);
        graph.addEdge(node1, node2, EdgeType.ESCARPE);
        assertEquals("Test add Edge", 1, graph.getEdges().size());

    }

    @Test
    public void testGetNode() throws Exception {
        Node node0 = new Node(0, 0, 0, NodeType.PLAT);
        graph.addNode(node0);
        for (int i = 1; i < 10; i++) {
            graph.addNode(new Node(i, 0, 0, NodeType.PLAT));
        }
        assertEquals("Test get Node", node0, graph.getNode(0));
    }

    @Test
    public void testGetEdge() throws Exception {
        Node node0 = new Node(0,0,0,NodeType.PLAT),
                node1 = new Node(1,0,0,NodeType.PLAT);
        Edge edge0 = new Edge(0, 1,EdgeType.ESCARPE);
        graph.addNode(node0);
        graph.addEdge(edge0);
        for (int i = 2; i < 10; i++) {
            graph.addNode(new Node(i,0,0,NodeType.PLAT));
            graph.addEdge(new Edge(i - 1, i,EdgeType.ESCARPE));
        }

        assertEquals("Test get Edge", edge0, graph.getEdge(node0, node1));
    }

    @Test
    public void testGetNodes() throws Exception {

    }

    @Test
    public void testGetEdges() throws Exception {

    }

    @Test
    public void testGetNeighboursFromNode() throws Exception {

    }

    @Test
    public void testGetEdgesFromNode() throws Exception {

    }

    @Test
    public void testGetNodesFromEdge() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {
        Graph graph2 = new Graph(),
                graph3 = new Graph();

        graph.addNode(new Node(0,0,0,NodeType.PLAT));
        graph.addNode(new Node(1,0,0,NodeType.PLAT));
        graph.addEdge(new Edge(0,1,EdgeType.ESCARPE));

        graph2.addNode(new Node(1,0,0,NodeType.PLAT));
        graph2.addNode(new Node(0,0,0,NodeType.PLAT));
        graph2.addEdge(new Edge(0, 1,EdgeType.ESCARPE));

        graph3.addNode(new Node(1,0,0,NodeType.PLAT));

        assertEquals("Test Graph equals", true, graph.equals(graph2));
        assertEquals("Test Graph equals 2", false, graph.equals(graph3));
    }

    @Test
    public void testClone() throws Exception {
        graph.addNode(new Node(0,0,0,NodeType.PLAT));
        graph.addNode(new Node(1,0,0,NodeType.PLAT));
        graph.addEdge(new Edge(0, 1,EdgeType.ESCARPE));
        Graph graph2 = graph.clone();
        System.out.printf(graph2.toString());
        assertEquals("Test cloning", true, graph2.equals(graph));
    }
}