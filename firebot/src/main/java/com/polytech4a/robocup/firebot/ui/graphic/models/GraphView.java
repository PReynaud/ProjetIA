package com.polytech4a.robocup.firebot.ui.graphic.models;

import com.polytech4a.robocup.firebot.controller.EnumSelection;
import com.polytech4a.robocup.graph.model.Edge;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Pierre on 16/05/2015.
 */
public class GraphView {
    private CopyOnWriteArrayList<NodeView> nodes;
    private CopyOnWriteArrayList<EdgeView> edges;
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

    public void drawGraph(Graphics g){
        Iterator<EdgeView> iter1 = edges.iterator();

        while(iter1.hasNext()){
            EdgeView edge = iter1.next();
            edge.drawEdge(g);
        }

        Iterator<NodeView> iter2 = nodes.iterator();
        while(iter2.hasNext()){
            NodeView node = iter2.next();
            node.drawNode(g);
        }

        Iterator<FirebotView> iter3 = robots.iterator();
        while(iter3.hasNext()){
            FirebotView bot = iter3.next();
            bot.draw(g);
        }

        /*for(EdgeView edge: edges){
            edge.drawEdge(g);
        }
        for(NodeView node: nodes){
            node.drawNode(g);
        }
        for(FirebotView robot: robots){
            robot.draw(g);
        }*/
    }

    public void addNode(int x, int y, int id){
        this.nodes.add(new NodeView(x, y, id));
    }
    public void addFireNode(int x, int y, int id){
        this.nodes.add(new FireNodeView(x, y, id));
    }
    public void deleteNode(int id){
        Optional<NodeView> node = this.nodes.stream().filter(o -> o.getId() == id).findFirst();
        this.nodes.remove(node.get());
    }

    public void addEdge(NodeView n1, NodeView n2){
        this.edges.add(new EdgeView(n1, n2));
    }
    public void addSteepEdge(NodeView n1, NodeView n2){
        this.edges.add(new SteepEdgeView(n1, n2));
    }

    public void addCrossCountryFirebot(NodeView n, int id){
        this.robots.add(new CrossCountryFirebotView(n, id));
    }
    public void addLeggedFirebot(NodeView n, int id){ this.robots.add(new LeggedFirebotView(n, id));}
    public void addTrackedFirebot(NodeView n, int id){ this.robots.add(new TrackedFirebotView(n, id));}

}
