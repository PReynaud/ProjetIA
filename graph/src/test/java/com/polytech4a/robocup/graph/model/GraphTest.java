package com.polytech4a.robocup.graph.model;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.utils.Load;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Dimitri on 11/05/2015.
 */
public class GraphTest {
    private Graph graph;
    private Graph loadedGraph;

    @Before
    public void setUp() throws Exception {
        graph = new Graph();
        File file = FileUtils.getFile("src", "test", "resources", "test.xml");
        Load l = new Load();
        loadedGraph = l.loadGraph(file);
    }

    @After
    public void tearDown() throws Exception {
        graph = null;
        loadedGraph = null;
    }

    @Test
    public void testAddNode() throws Exception {
        Node node = new Node(0, 0, 0, NodeType.NORMAL);
        graph.addNode(node);
        assertEquals("Test add Node", 1, graph.getNodes().size());

    }

    @Test
    public void testAddEdge() throws Exception {
        Node node1 = new Node(0, 0, 0, NodeType.NORMAL), node2 = new Node(1, 0, 0, NodeType.NORMAL);
        Edge edge = new Edge(0, 1, EdgeType.ESCARPE);
        graph.addEdge(edge);
        assertEquals("Test add Edge", 1, graph.getEdges().size());

    }

    @Test
    public void testAddEdgeWithTwoNodes() throws Exception {
        Node node1 = new Node(0, 0, 0, NodeType.NORMAL), node2 = new Node(1, 0, 0, NodeType.NORMAL);
        graph.addEdge(new Edge(0, 1, EdgeType.ESCARPE));
        assertEquals("Test add Edge", 1, graph.getEdges().size());

    }

    @Test
    public void testGetNode() throws Exception {
        Node node0 = new Node(0, 0, 0, NodeType.NORMAL);
        graph.addNode(node0);
        for (int i = 1; i < 10; i++) {
            graph.addNode(new Node(i, 0, 0, NodeType.NORMAL));
        }
        assertEquals("Test get Node", node0, graph.getNode(0));
    }

    @Test
    public void testGetEdge() throws Exception {
        Node node0 = new Node(0, 0, 0, NodeType.NORMAL),
                node1 = new Node(1, 0, 0, NodeType.NORMAL);
        Edge edge0 = new Edge(0, 1, EdgeType.ESCARPE);
        graph.addNode(node0);
        graph.addEdge(edge0);
        for (int i = 2; i < 10; i++) {
            graph.addNode(new Node(i, 0, 0, NodeType.NORMAL));
            graph.addEdge(new Edge(i - 1, i, EdgeType.ESCARPE));
        }

        assertEquals("Test get Edge", edge0, graph.getEdge(node0, node1));
    }

    @Test
    public void testGetNodes() throws Exception {
        Node node0 = new Node(0, 0, 0, NodeType.NORMAL),
                node1 = new Node(1, 0, 0, NodeType.NORMAL);
        graph.addNode(node0);
        graph.addNode(node1);
        ArrayList<Node> n = graph.getNodes();
        assertTrue(n.get(0) != node0);
        assertTrue(n.get(1) != node1);
    }

    @Test
    public void testGetEdges() throws Exception {
        Node node0 = new Node(0, 0, 0, NodeType.NORMAL),
                node1 = new Node(1, 0, 0, NodeType.NORMAL),
                node2 = new Node(2, 0, 0, NodeType.NORMAL);
        Edge edge0 = new Edge(0, 1, EdgeType.ESCARPE);
        Edge edge1 = new Edge(0, 2, EdgeType.ESCARPE);
        graph.addNode(node0);
        graph.addNode(node1);
        graph.addEdge(edge0);
        graph.addEdge(edge1);
        assertTrue(edge0 != graph.getEdges().get(0));
        assertTrue(edge1 != graph.getEdges().get(1));
    }

    @Test
    public void testGetNeighboursFromNode() throws Exception {
        ArrayList<Node> nodes = loadedGraph.getNeighboursFromNode(loadedGraph.getNode(3));
        assertEquals(4, nodes.size());
    }

    @Test
    public void testGetEdgesFromNode() throws Exception {
        ArrayList<Edge> edges = loadedGraph.getEdgesFromNode(loadedGraph.getNode(3));
        assertEquals(4, edges.size());
    }

    @Test
    public void testGetNodesFromEdge() throws Exception {
        Node node1 = loadedGraph.getNode(3), node2 = loadedGraph.getNode(2);
        ArrayList<Node> nodes = loadedGraph.getNodesFromEdge(loadedGraph.getEdge(node1, node2));
        assertEquals(2, nodes.size());
        assertTrue(nodes.contains(node1));
        assertTrue(nodes.contains(node2));
    }

    @Test
    public void testEquals() throws Exception {
        Graph graph2 = new Graph(),
                graph3 = new Graph();

        graph.addNode(new Node(0, 0, 0, NodeType.NORMAL));
        graph.addNode(new Node(1, 0, 0, NodeType.NORMAL));
        graph.addEdge(new Edge(0, 1, EdgeType.ESCARPE));

        graph2.addNode(new Node(1, 0, 0, NodeType.NORMAL));
        graph2.addNode(new Node(0, 0, 0, NodeType.NORMAL));
        graph2.addEdge(new Edge(0, 1, EdgeType.ESCARPE));

        graph3.addNode(new Node(1, 0, 0, NodeType.NORMAL));

        assertEquals("Test Graph equals", true, graph.equals(graph2));
        assertEquals("Test Graph equals 2", false, graph.equals(graph3));
    }

    @Test
    public void testClone() throws Exception {
        graph.addNode(new Node(0, 0, 0, NodeType.NORMAL));
        graph.addNode(new Node(1, 0, 0, NodeType.NORMAL));
        graph.addEdge(new Edge(0, 1, EdgeType.ESCARPE));
        Graph graph2 = graph.clone();
        assertEquals("Test cloning", true, graph2.equals(graph));
    }

    @Test
    public void testUpdateNode() throws Exception {
        Node n = loadedGraph.getNode(0).clone();
        Node n2 = new Node(0, 0, 0, NodeType.INCENDIE);
        loadedGraph.addNode(n2);
        Node n3 = loadedGraph.getNode(0);
        assertTrue(n.getId() == n3.getId());
        assertEquals(0, n3.getX(), 0.0001);
        assertEquals(0, n3.getY(), 0.0001);
        assertEquals(NodeType.INCENDIE,n3.getType());
    }

    @Test
    public void testUpdateEdge() throws Exception {
        Edge e=loadedGraph.getEdge(loadedGraph.getNode(0),loadedGraph.getNode(1)).clone();
        Edge e1=new Edge(0,1,EdgeType.INONDEE);
        loadedGraph.addEdge(e1);
        assertEquals(EdgeType.INONDEE,e1.getType());
    }
}