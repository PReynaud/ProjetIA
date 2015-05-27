package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Pierre on 16/05/2015.
 */
public class GraphView {
    private ArrayList<NodeView> nodes;
    private ArrayList<EdgeView> edges;


    public GraphView() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void drawGraph(Graphics g){
        for(EdgeView edge: edges){
            edge.drawEdge(g);
        }
        for(NodeView node: nodes){
            node.drawNode(g);
        }
    }

    //TODO rajouter sécurité pour ne pas avoir deux noeuds qui se chevauchent
    public void addNode(int x, int y){
        this.nodes.add(new NodeView(x, y));
    }

    public void addEdge(NodeView n1, NodeView n2){
        this.edges.add(new EdgeView(n1, n2));
    }

    public ArrayList<NodeView> getNodes() {
        return nodes;
    }
}
