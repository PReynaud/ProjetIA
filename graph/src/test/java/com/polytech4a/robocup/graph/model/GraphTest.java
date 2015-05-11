package com.polytech4a.robocup.graph.model;

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

    @Test
    public void testAddNode() throws Exception {
        Node node = new Node(0);
        graph.addNode(node);
        assertEquals("Test add Node", 1, graph.getNumberOfNodes());
        graph.removeNode(node);
    }

    @Test
    public void testAddEdge() throws Exception {
        Node node1 = new Node(0), node2 = new Node(1);
        Edge edge = new Edge(0,1);
        graph.addEdge(edge);
        assertEquals("Test add Edge", 1, graph.getEdges().size());
        graph.removeNode(node1);
        graph.removeNode(node2);
    }

    @Test
    public void testAddEdgeWithTwoNodes () throws Exception {
        Node node1 = new Node(0), node2 = new Node(1);
        graph.addEdge(node1, node2);
        assertEquals("Test add Edge", 1, graph.getEdges().size());
        graph.removeNode(node1);
        graph.removeNode(node2);
        graph.removeEdge(node1,node2);
    }

    @Test
    public void testGetNode() throws Exception {
        Node node0 = new Node(0);
        graph.addNode(node0);
        for (int i=1; i<10; i++){
            graph.addNode(new Node(i));
        }
        assertEquals("Test get Node",node0,graph.getNode(0));

        for (Node node: graph.getNodes()){
            graph.removeNode(node);
        }
    }

    @Test
    public void testGetEdge() throws Exception {
        Node node0 = new Node(0),
                node1 = new Node(1);
        Edge edge0 = new Edge(0, 1);
        graph.addNode(node0);
        graph.addEdge(edge0);
        for (int i=2; i<10; i++){
            graph.addNode(new Node(i));
            graph.addEdge(new Edge(i-1,i));
        }

        assertEquals("Test get Edge",edge0,graph.getEdge(node0, node1));

        for (Node node: graph.getNodes()){
            graph.removeNode(node);
        }
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

        graph.addNode(new Node(0));
        graph.addNode(new Node(1));
        graph.addEdge(new Edge(0, 1));

        graph2.addNode(new Node(1));
        graph2.addNode(new Node(0));
        graph2.addEdge(new Edge(0, 1));

        graph3.addNode(new Node(1));

        assertEquals("Test Graph equals", true, graph.equals(graph2));
        assertEquals("Test Graph equals 2", false, graph.equals(graph3));
    }

    @Test
    public void testClone() throws Exception {
        graph.addNode(new Node(0));
        graph.addNode(new Node(1));
        graph.addEdge(new Edge(0, 1));
        Graph graph2 = graph.clone();
        System.out.printf(graph2.toString());
        assertEquals("Test cloning", true, graph2.equals(graph));
    }
}