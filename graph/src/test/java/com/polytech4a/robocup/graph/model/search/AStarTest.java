package com.polytech4a.robocup.graph.model.search;

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

import static org.junit.Assert.*;

/**
 * Created by Antoine CARON on 19/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class AStarTest {

    private static Graph graph;

    public static void setGraph() {
        File file = FileUtils.getFile("src", "test", "resources", "mapsixieme.xml");
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
        this.aStar=new AStar(graph);
        setGraph();
    }

    @Test
    public void testWayToNodeWithParam() throws Exception {

    }

    @Test
    public void testWayToNodeWithoutParam() throws Exception {

    }

    @Test
    public void testGetHeuristicValue() throws Exception {

    }

    @Test
    public void testGetCostSwitchTypes() throws Exception {

    }

    @Test
    public void testGetCostValue() throws Exception {

    }

    @Test
    public void testGetFitnessValue() throws Exception {

    }
}