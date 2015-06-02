package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Pierre on 16/05/2015.
 * <p/>
 * View of the Graph.
 */
public class GraphView {
    /**
     * List of Node Views.
     */
    private CopyOnWriteArrayList<NodeView> nodes;

    /**
     * ThreadSafe list of Edge Views.
     */
    private CopyOnWriteArrayList<EdgeView> edges;

    /**
     * ThreadSafe list of Firebot Views.
     */
    private CopyOnWriteArrayList<FirebotView> robots;


    public GraphView() {
        nodes = new CopyOnWriteArrayList<>();
        edges = new CopyOnWriteArrayList<>();
        robots = new CopyOnWriteArrayList<>();
    }

    public CopyOnWriteArrayList<NodeView> getNodes() {
        return nodes;
    }

    public CopyOnWriteArrayList<EdgeView> getEdges() {
        return edges;
    }

    public CopyOnWriteArrayList<FirebotView> getRobots() {
        return robots;
    }

    /**
     * Draw the graph on graphics.
     *
     * @param g graphics.
     */
    public void drawGraph(Graphics g) {
        Iterator<EdgeView> iter1 = edges.iterator();

        while (iter1.hasNext()) {
            EdgeView edge = iter1.next();
            edge.drawEdge(g);
        }

        Iterator<NodeView> iter2 = nodes.iterator();
        while (iter2.hasNext()) {
            NodeView node = iter2.next();
            node.drawNode(g);
        }

        Iterator<FirebotView> iter3 = robots.iterator();
        while (iter3.hasNext()) {
            FirebotView bot = iter3.next();
            bot.draw(g);
        }
    }

    /**
     * Add NodeView.
     *
     * @param x  horizontal position.
     * @param y  vertical position.
     * @param id id of the new NodeView created.
     */
    public void addNode(int x, int y, int id) {
        this.nodes.add(new NodeView(x, y, id));
    }

    /**
     * Add new Firenode on the graph.
     *
     * @param x  horizontal position.
     * @param y  vertical position.
     * @param id id of the new FireNodeView created.
     */
    public void addFireNode(int x, int y, int id) {
        this.nodes.add(new FireNodeView(x, y, id));
    }

    /**
     * Delete a NodeView.
     *
     * @param id
     */
    public void deleteNode(int id) {
        Optional<NodeView> node = this.nodes.stream().filter(o -> o.getId() == id).findFirst();
        this.nodes.remove(node.get());
    }

    /**
     * Add new Edge between two NodeViews.
     *
     * @param n1 first NodeViews.
     * @param n2 second NodeViews.
     */
    public void addEdge(NodeView n1, NodeView n2) {
        this.edges.add(new EdgeView(n1, n2));
    }

    /**
     * Add a new SteepEdgeView between two NodeViews.
     *
     * @param n1 first NodeViews.
     * @param n2 seconde NodeViews.
     */
    public void addSteepEdge(NodeView n1, NodeView n2) {
        this.edges.add(new SteepEdgeView(n1, n2));
    }

    /**
     * Add a new CrossCountryFirebotView on a NodeView.
     *
     * @param n  node on which we add the robot.
     * @param id id of the new Robot.
     */
    public void addCrossCountryFirebot(NodeView n, int id) {
        this.robots.add(new CrossCountryFirebotView(n, id));
    }

    /**
     * Add a new LeggedFirebotView on a NodeView
     *
     * @param n  node on which we add the robot.
     * @param id id of the new Robot.
     */
    public void addLeggedFirebot(NodeView n, int id) {
        this.robots.add(new LeggedFirebotView(n, id));
    }

    /**
     * Add new TrackedFirebotView on a NodeView.
     *
     * @param n  node on which we add the robot.
     * @param id id of the new Robot.
     */
    public void addTrackedFirebot(NodeView n, int id) {
        this.robots.add(new TrackedFirebotView(n, id));
    }

}
