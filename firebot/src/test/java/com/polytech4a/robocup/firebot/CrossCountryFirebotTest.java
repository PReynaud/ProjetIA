package com.polytech4a.robocup.firebot;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;

import java.util.ArrayList;

/**
 * Created by Adrien CHAUSSENDE on 11/05/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 */
public class CrossCountryFirebotTest extends FirebotTest {
    @Override
    public void setUp() {
        super.setUp();
        setFirebot(new LeggedFirebot(getGraph(), 100, new ArrayList<EdgeType>(), new ArrayList<NodeType>()));
    }

    @Override
    public void testComputeTime() {

    }
}
