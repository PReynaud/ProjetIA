package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 * Created by Pierre on 16/05/2015.
 */
public class GraphView {
    private ArrayList<NodeView> nodes;
    private ArrayList<EdgeView> edges;
    private ArrayList<FirebotView> robots;


    public GraphView() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        robots = new ArrayList<>();
    }

    public void drawGraph(Graphics g){
        for(EdgeView edge: edges){
            edge.drawEdge(g);
        }
        for(NodeView node: nodes){
            node.drawNode(g);
        }
        for(FirebotView robot: robots){
            robot.draw(g);
        }
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

    public void addCrossCountryFirebot(NodeView n){
        this.robots.add(new CrossCountryFirebotView(n));
    }
    public void addLeggedFirebot(NodeView n){ this.robots.add(new LeggedFirebotView(n));}
    public void addTrackedFirebot(NodeView n){ this.robots.add(new TrackedFirebotView(n));}

    public ArrayList<NodeView> getNodes() {
        return nodes;
    }
}
