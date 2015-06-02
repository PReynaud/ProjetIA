package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.robots.CrossCountryFirebot;
import com.polytech4a.robocup.firebot.robots.Firebot;
import com.polytech4a.robocup.firebot.robots.LeggedFirebot;
import com.polytech4a.robocup.firebot.robots.TrackedFirebot;
import com.polytech4a.robocup.firebot.ui.GraphicViewPanel;
import com.polytech4a.robocup.firebot.ui.graphic.models.GraphView;
import com.polytech4a.robocup.firebot.ui.graphic.models.NodeView;
import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Edge;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.search.AStar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import static com.polytech4a.robocup.firebot.controller.EnumSelection.ADD_EDGE;
import static com.polytech4a.robocup.firebot.controller.EnumSelection.ADD_STEEP_EDGE;

/**
 * Created by Pierre on 16/05/2015.
 * Controller of the mouse and the different actions we can do with it
 */
public class MouseController implements MouseListener, MouseMotionListener {
    private MainController mainController;

    public MouseController(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GraphicViewPanel graphicViewPanel = (GraphicViewPanel) mainController.getView().getGraphicViewPanel();
        GraphView graph = graphicViewPanel.getGraph();
        Graph graphModel = this.mainController.getGraph();

        switch (mainController.getSelectionMode()) {
            case ADD_NODE:
                int id = mainController.getGraph().addNewNode(e.getX(), e.getY(), NodeType.NORMAL);
                graph.addNode(e.getX(), e.getY(), id);
                break;
            case ADD_FIRE_NODE:
                NodeView clickedNode = clickOnANode(graph.getNodes(), e.getX(), e.getY());
                if (clickedNode != null) {
                    //TODO ajouter au modèle
                    id = clickedNode.getId();
                    graph.deleteNode(clickedNode.getId());
                    graph.addFireNode(clickedNode.getX(), clickedNode.getY(), id);
                }
                break;
            case ADD_EDGE:
                clickedNode = clickOnANode(graph.getNodes(), e.getX(), e.getY());
                if (clickedNode != null) {
                    if (mainController.getLastClickedNode() == null) {
                        mainController.setLastClickedNode(clickedNode);
                    } else {
                        mainController.getGraph().addEdge(new Edge(mainController.getLastClickedNode().getId(), clickedNode.getId(), EdgeType.PLAT));
                        graph.addEdge(mainController.getLastClickedNode(), clickedNode);
                        mainController.setLastClickedNode(null);
                    }
                }
                break;
            case ADD_STEEP_EDGE:
                clickedNode = clickOnANode(graph.getNodes(), e.getX(), e.getY());
                if (clickedNode != null) {
                    if (mainController.getLastClickedNode() == null) {
                        mainController.setLastClickedNode(clickedNode);
                    } else {
                        mainController.getGraph().addEdge(new Edge(mainController.getLastClickedNode().getId(), clickedNode.getId(), EdgeType.ESCARPE));
                        graph.addSteepEdge(mainController.getLastClickedNode(), clickedNode);
                        mainController.setLastClickedNode(null);
                    }
                }
                break;
            case ADD_CROSS_COUNTRY_FIREBOT:
                Firebot bot = new CrossCountryFirebot(mainController.getModel().generateId(), graphModel, 100, new AStar());
                NodeView nView = addFireBot(bot, graph, e);
                if (nView != null) {
                    graph.addCrossCountryFirebot(nView, bot.getId());
                }
                break;
            case ADD_LEGGED_FIREBOT:
                bot = new LeggedFirebot(mainController.getModel().generateId(), graphModel, 100, new AStar());
                nView = addFireBot(bot, graph, e);
                if (nView != null) {
                    graph.addLeggedFirebot(nView, bot.getId());
                }
                break;
            case ADD_TRACKED_FIREBOT:
                bot = new TrackedFirebot(mainController.getModel().generateId(), graphModel, 100, new AStar());
                nView = addFireBot(bot, graph, e);
                if (nView != null) {
                    graph.addTrackedFirebot(nView, bot.getId());
                }
                break;
            default:
                break;
        }
    }

    /**
     * Add a Firebot to the mainController.
     *
     * @param firebot firebot of the specific type.
     */
    private NodeView addFireBot(Firebot firebot, GraphView graph, MouseEvent e) {
        NodeView clickedNode = clickOnANode(graph.getNodes(), e.getX(), e.getY());
        if (clickedNode != null) {
            Node clickedNodeModel = mainController.getModel().getGraph().getNode(clickedNode.getId());
            firebot.setCurrentNode(clickedNodeModel);
            firebot.addControllerObserver(mainController);
            this.mainController.getModel().getRobotTeam().add(firebot);
        }
        return clickedNode;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (mainController.getSelectionMode().equals(ADD_EDGE) ||
                mainController.getSelectionMode().equals(ADD_STEEP_EDGE)) {
            if (mainController.getLastClickedNode() != null) {
                GraphicViewPanel graphicViewPanel = (GraphicViewPanel) mainController.getView().getGraphicViewPanel();
                graphicViewPanel.paintComponentWithCustomEdge(graphicViewPanel.getGraphics(), mainController.getLastClickedNode().getX(),
                        mainController.getLastClickedNode().getY(), e.getX(), e.getY(), mainController.getSelectionMode());
            }
        }
    }

    private NodeView clickOnANode(List<NodeView> listOfNodes, int x, int y) {
        for (NodeView node : listOfNodes) {
            double dist = Math.sqrt(Math.pow((node.getX() - x), 2) + Math.pow((node.getY() - y), 2));
            if (dist < node.getSize()) {
                return node;
            }
        }
        return null;
    }
}
