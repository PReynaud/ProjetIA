package com.polytech4a.robocup.firebot.ui;

import com.polytech4a.robocup.firebot.ui.graphic.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created by Pierre on 06/05/2015.
 */
public class GraphicViewPanel extends JPanel {
    private Graph graph;

    public GraphicViewPanel(){
        super();
        Dimension dimension = new Dimension(700, 400);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBackground(Color.white);
        this.graph = new Graph();
    }

    public Graph getGraph() {
        return graph;
    }

    @Override
    public void paintComponent(Graphics g) {
        Dimension dim = getSize();
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(Color.white);
        super.paintComponent(g);

        graph.drawGraph(g);
    }
}
