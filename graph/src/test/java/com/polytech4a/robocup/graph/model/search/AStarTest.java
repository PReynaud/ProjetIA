package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.utils.Load;
import com.polytech4a.robocup.graph.utils.MalformGraphException;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Antoine CARON on 19/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class AStarTest {

    private static Graph graph;

    public static void setGraph() {
        File file = FileUtils.getFile("src", "test", "resources", "test.xml");
        Load l = new Load();
        try {
            AStarTest.graph =l.loadGraph(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (MalformGraphException e) {
            e.printStackTrace();
        }
    }

    private AStar aStar;

    @Before
    public void setUp() throws Exception {
        this.aStar = new AStar();
        setGraph();
    }

    @Test
    public void testWayToNodeWithParam() throws Exception {
        ArrayList<NodeType> restrictedNodes = new ArrayList<>();
        ArrayList<EdgeType> restrictedEdges = new ArrayList<>();
        restrictedNodes.add(NodeType.NORMAL);
        restrictedEdges.add(EdgeType.PLAT);
        Way path= aStar.wayToNodeWithParam(graph,graph.getNode(2),graph.getNode(3),restrictedNodes,restrictedEdges);
        assertEquals(9, path.getDistance(), 0.001);
        assertEquals(4, path.getNodes().size());
    }

    @Test
    public void testWayToNotAcceptableNode() throws Exception {
        ArrayList<NodeType> restrictedNodes = new ArrayList<>();
        ArrayList<EdgeType> restrictedEdges = new ArrayList<>();
        restrictedNodes.add(NodeType.NORMAL);
        restrictedEdges.add(EdgeType.PLAT);
        Way path= aStar.wayToNodeWithParam(graph,graph.getNode(2),graph.getNode(4),restrictedNodes,restrictedEdges);
        assertEquals(0,path.getDistance(),0.001);
        assertEquals(0,path.getNodes().size());
    }

    @Test
    public void testNotFoundWay() throws Exception {
        ArrayList<NodeType> restrictedNodes = new ArrayList<>();
        ArrayList<EdgeType> restrictedEdges = new ArrayList<>();
        restrictedNodes.add(NodeType.NORMAL);
        restrictedEdges.add(EdgeType.PLAT);
        Way path= aStar.wayToNodeWithParam(graph,graph.getNode(7),graph.getNode(8),restrictedNodes,restrictedEdges);
        assertEquals(0,path.getDistance(),0.001);
        assertEquals(0,path.getNodes().size());
    }

    @Test
    public void testNullWhiteList() throws Exception {
        Way path= aStar.wayToNodeWithParam(graph,graph.getNode(2),graph.getNode(0),null,null);
        assertEquals(3, path.getDistance(), 0.001);
        assertEquals(2, path.getNodes().size());
    }


    @Test
    public void testWayToNodeWithoutParam() throws Exception {
        ArrayList<NodeType> restrictedNodes = new ArrayList<>();
        ArrayList<EdgeType> restrictedEdges = new ArrayList<>();
        restrictedNodes.add(NodeType.INCENDIE);
        restrictedEdges.add(EdgeType.ESCARPE);
        Way path= aStar.wayToNodeWithoutParam(graph,graph.getNode(5),graph.getNode(3),restrictedNodes,restrictedEdges);
        assertEquals(3,path.getNodes().size());
        assertFalse(path.getNodes().contains(graph.getNode(4)));
    }

    @Test
    public void testNoWay() throws Exception {
        Way path= aStar.wayToNodeWithParam(graph,graph.getNode(7),graph.getNode(8),null,null);
        assertEquals(0,path.getDistance(),0.001);
        assertEquals(0,path.getNodes().size());
    }
}