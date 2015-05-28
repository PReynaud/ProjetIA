package com.polytech4a.robocup.firebot.ui.graphic.models;

import com.polytech4a.robocup.graph.model.Node;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

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

    public void addNode(int x, int y, int id){
        this.nodes.add(new NodeView(x, y, id));
    }

    public void addFireNode(int x, int y, int id){
        this.nodes.add(new FireNodeView(x, y, id));
    }

    public void deleteNode(int x, int y){
        Optional<NodeView> node = this.nodes.stream().filter(o -> o.getX() == x && o.getY() == y).findFirst();
        this.nodes.remove(node.get());
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

    public ArrayList<NodeView> getNodes() {
        return nodes;
    }
}
