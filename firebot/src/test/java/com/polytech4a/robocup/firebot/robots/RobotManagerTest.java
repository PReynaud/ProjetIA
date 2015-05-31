package com.polytech4a.robocup.firebot.robots;

import com.polytech4a.robocup.firebot.robots.Firebot;
import com.polytech4a.robocup.firebot.robots.RobotManager;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.search.AStar;
import com.polytech4a.robocup.graph.model.search.ISearch;
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

    private ISearch searchAlg;

    @Override
    protected void setUp() throws ParserConfigurationException, MalformGraphException, SAXException, IOException {
        File file = FileUtils.getFile("src", "test", "resources", "test.xml");
        Load l = new Load();
        graph = l.loadGraph(file);
        managerTeam = new ArrayList<>();
        searchAlg = new AStar();
        managerTeam.add(new CrossCountryFirebot(graph, 100, searchAlg));
        managerTeam.add(new LeggedFirebot(graph, 200, searchAlg));
        managerTeam.add(new TrackedFirebot(graph, 250, searchAlg));
        manager = new RobotManager(managerTeam, graph);
        destinationNode = graph.getNode(3);
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
        assertEquals(3, availableTeam.size());
        managerTeam.get(0).setAvailability(false);
        availableTeam = manager.askAvailability();
        assertEquals(2, availableTeam.size());
    }

    public void testAskDistancesToNode() {
        Node[] destinationNodes = {graph.getNode(7), graph.getNode(5), graph.getNode(2)};
        for(int i = 0; i < managerTeam.size(); i++) {
            managerTeam.get(i).setCurrentNode(destinationNodes[i]);
        }
        Firebot firebot = manager.askDistancesToNode(manager.askAvailability(), destinationNode);
        assertEquals(managerTeam.get(1), firebot);
    }

    public void testDistributeTasks() {
        destinationNode = graph.getNode(4);
        Node[] destinationNodes = {graph.getNode(2), graph.getNode(0), graph.getNode(7)};
        for(int i = 0; i < managerTeam.size(); i++) {
            managerTeam.get(i).setCurrentNode(destinationNodes[i]);
        }
        manager.distributeTasks();
        assertEquals(destinationNode, managerTeam.get(1).getDestinationNode());
        assertNull(managerTeam.get(0).getDestinationNode());
        assertNull(managerTeam.get(2).getDestinationNode());
    }

    public void testDistributeTasks2() throws ParserConfigurationException, MalformGraphException, SAXException, IOException {
        File file = FileUtils.getFile("src", "test", "resources", "mapsixieme.xml");
        Load l = new Load();
        graph = l.loadGraph(file);
        managerTeam = new ArrayList<>();
        managerTeam.add(new CrossCountryFirebot(graph, 100, searchAlg));
        managerTeam.add(new LeggedFirebot(graph, 200, searchAlg));
        managerTeam.add(new TrackedFirebot(graph, 250, searchAlg));
        manager = new RobotManager(managerTeam, graph);
        Node[] currentNodes = {graph.getNode(4), graph.getNode(8), graph.getNode(7)};
        for(int i = 0; i < managerTeam.size(); i++) {
            managerTeam.get(i).setCurrentNode(currentNodes[i]);
        }
        manager.distributeTasks();
        assertEquals(graph.getNode(1), managerTeam.get(0).getDestinationNode());
        assertEquals(graph.getNode(3), managerTeam.get(1).getDestinationNode());
        assertEquals(graph.getNode(2), managerTeam.get(2).getDestinationNode());
    }
}
