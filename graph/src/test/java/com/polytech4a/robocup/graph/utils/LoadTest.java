package com.polytech4a.robocup.graph.utils;

import com.polytech4a.robocup.graph.model.Graph;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.File;

/**
 * Created by Antoine CARON on 11/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class LoadTest {

    @Test
    public void testLoadGraph() throws Exception {
        File file = FileUtils.getFile("src", "test", "resources", "mapsixieme.xml");
        Graph g = Load.loadGraph(file);
        TestCase.assertEquals(26, g.getNodes().size());
        TestCase.assertEquals(39, g.getEdges().size());
    }

    @Test(expected = MalformGraphException.class)
    public void testFailOnNodeId() throws Exception {
        File file = FileUtils.getFile("src", "test", "resources", "mapsixiemefailnode.xml");
        Graph g = Load.loadGraph(file);
    }

    @Test(expected = MalformGraphException.class)
    public void testFailOnEdgeId() throws Exception {
        File file = FileUtils.getFile("src", "test", "resources", "mapsixiemefailedge.xml");
        Graph g = Load.loadGraph(file);
    }

    @Test(expected = SAXException.class)
    public void testFailOnXml() throws Exception {
        File file = FileUtils.getFile("src", "test", "resources", "mapsixiemeMalformeXml.xml");
        Graph g = Load.loadGraph(file);
    }
}