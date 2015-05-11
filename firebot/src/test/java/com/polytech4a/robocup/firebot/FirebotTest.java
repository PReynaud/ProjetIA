package com.polytech4a.robocup.firebot;

import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import junit.framework.TestCase;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        graph = Mockito.mock(Graph.class);
        currentNode = Mockito.mock(Node.class);
        destinationNode = Mockito.mock(Node.class);
        firebot = Mockito.mock(Firebot.class);
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

    public void testComputeDistance() {
        assertEquals(0.0, firebot.computeDistance(destinationNode));
    }
}
