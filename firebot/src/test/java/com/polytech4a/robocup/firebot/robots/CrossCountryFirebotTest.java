package com.polytech4a.robocup.firebot.robots;

import com.polytech4a.robocup.graph.utils.MalformGraphException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by Adrien CHAUSSENDE on 11/05/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 */
public class CrossCountryFirebotTest extends FirebotTest {
    @Override
    public void setUp() throws ParserConfigurationException, MalformGraphException, SAXException, IOException {
        super.setUp();
        setFirebot(new CrossCountryFirebot(getGraph(), 100));
    }

    @Override
    public void testComputeTime() {
        assertEquals(((long)100 * 1000 / 100), getFirebot().computeTime());
    }

    @Override
    public void testComputeDistance() {
        getFirebot().setCurrentNode(getGraph().getNode(2));
        assertEquals(11.708, getFirebot().computeDistance(getGraph().getNode(4)), 0.001);
        getFirebot().setCurrentNode(getGraph().getNode(5));
        assertEquals(3.0, getFirebot().computeDistance(getGraph().getNode(6)), 0.001);
    }
}
