package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.SearchException;
import com.polytech4a.robocup.graph.utils.Load;
import com.polytech4a.robocup.graph.utils.MalformGraphException;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @author Dimitri on 27/05/2015.
 * @version 1.0
 */

@Ignore
public abstract class SearchAlgorithmTest {
    protected static Graph graph;
    protected SearchAlgorithm searchAlgorithm;

    @Before
    public void setUp() throws Exception {
        setGraph();
    }

    public static void setGraph() {
        File file = FileUtils.getFile("src", "test", "resources", "test.xml");
        Load l = new Load();
        try {
            SearchAlgorithmTest.graph = l.loadGraph(file);
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

    @Test(expected = SearchException.class)
    public void testCorruptedNodeType() throws Exception {
        Node begin = graph.getNode(1);
        graph.getNode(0).getParameters().put("type", "BLABLA");
        searchAlgorithm.wayToNodeWithoutParam(graph, begin, graph.getNode(7), null, null);
    }
}