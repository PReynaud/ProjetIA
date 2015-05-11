package com.polytech4a.robocup.firebot;

/**
 * Created by Adrien CHAUSSENDE on 11/05/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 */
public class LeggedFirebotTest extends FirebotTest {

    @Override
    public void setUp() {
        super.setUp();
        setFirebot(new LeggedFirebot(getGraph(), getCurrentNode(), getDestinationNode(), 100));
    }

    @Override
    public void testComputeTime() {

    }

}
