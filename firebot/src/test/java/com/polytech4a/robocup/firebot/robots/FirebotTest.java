package com.polytech4a.robocup.firebot.robots;

import com.polytech4a.robocup.firebot.robots.Firebot;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.utils.Load;
import com.polytech4a.robocup.graph.utils.MalformGraphException;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Adrien CHAUSSENDE on 11/05/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 */
public abstract class FirebotTest extends TestCase {

    private Graph graph;

    private Node currentNode;

    private Node destinationNode;

    private Firebot firebot;

    public Firebot getFirebot() {
        return firebot;
    }

    public void setFirebot(Firebot firebot) {
        this.firebot = firebot;
    }

    public Graph getGraph() {
        return graph;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public Node getDestinationNode() {
        return destinationNode;
    }

    @Override
    public void setUp() throws ParserConfigurationException, MalformGraphException, SAXException, IOException {
        MockitoAnnotations.initMocks(this);
        File file = FileUtils.getFile("src", "test", "resources", "test.xml");
        Load l = new Load();
        graph = l.loadGraph(file);
        currentNode = Mockito.mock(Node.class);
        destinationNode = Mockito.mock(Node.class);
    }

    @Override
    public void tearDown() {
        graph = null;
        currentNode = null;
        destinationNode = null;
        firebot = null;
    }

    public void testIsAvailable() {
        assertTrue(getFirebot().isAvailable());
        firebot.setAvailability(false);
        assertFalse(getFirebot().isAvailable());
    }

    public abstract void testComputeTime();

    public abstract void testComputeDistance();
}
