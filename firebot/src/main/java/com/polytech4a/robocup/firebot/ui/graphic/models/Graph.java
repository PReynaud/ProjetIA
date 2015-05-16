package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Pierre on 16/05/2015.
 */
public class Graph {
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;


    public Graph() {
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
    }

    public void drawGraph(Graphics g){
        for(Edge edge: edges){
            edge.drawEdge(g);
        }
        for(Node node: nodes){
            node.drawNode(g);
        }
    }

    public void addNode(int x, int y){
        this.nodes.add(new Node(x, y));
    }
}
