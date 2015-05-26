package com.polytech4a.robocup.firebot;

import com.polytech4a.robocup.firebot.robots.Firebot;
import com.polytech4a.robocup.firebot.robots.RobotManager;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import junit.framework.TestCase;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

/**
 * Created by Adrien CHAUSSENDE on 11/05/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 */
public class RobotManagerTest extends TestCase {

    private RobotManager manager;

    private ArrayList<Firebot> managerTeam;

    private Graph graph;

    private Node destinationNode;

    @Override
    protected void setUp() {
        MockitoAnnotations.initMocks(this);
        graph = Mockito.mock(Graph.class);
        managerTeam = new ArrayList<>();
        destinationNode = Mockito.mock(Node.class);
        for(int i = 0 ; i < 3; i++) {
            Firebot robot = Mockito.mock(Firebot.class);
            managerTeam.add(robot);
            Mockito.when(robot.getCurrentNode()).thenReturn(new Node(i, (double) i, (double) i, NodeType.INCENDIE));
            Mockito.when(robot.computeDistance(destinationNode)).thenReturn((double) 100 * (i + 1));
            Mockito.when(robot.isAvailable()).thenReturn(true && i%2 == 0);
        }
        manager = new RobotManager(managerTeam, graph);
    }

    @Override
    protected void tearDown() {
        manager = null;
        managerTeam = null;
        graph = null;
        destinationNode = null;
    }

    public void testAskAvailability() {
        ArrayList<Firebot> availableTeam = manager.askAvailability();
        assertEquals(2, availableTeam.size());
    }

    public void testAskDistancesToNode() {
        Firebot firebot = manager.askDistancesToNode(manager.askAvailability(), destinationNode);
        assertEquals(0, firebot.getCurrentNode().getId());
    }
}
