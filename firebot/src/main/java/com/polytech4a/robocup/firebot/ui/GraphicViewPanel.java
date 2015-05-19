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
        this.setDoubleBuffered(true);
        this.graph = new Graph();
    }

    public Graph getGraph() {
        return graph;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        Dimension dim = getSize();
        g.fillRect(0, 0, dim.width, dim.height);
        //super.paintComponent(g);

        graph.drawGraph(g);
    }

    /**
     * Call the paint component and draw a line between the points passed in the parameters
     * @param g
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public void paintComponentWithCustomEdge(Graphics g, int x1, int y1, int x2, int y2){
        this.paintComponent(g);
        g.drawLine(x1, y1, x2, y2);
    }
}
