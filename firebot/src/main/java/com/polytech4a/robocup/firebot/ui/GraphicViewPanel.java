package com.polytech4a.robocup.firebot.ui;

import com.polytech4a.robocup.firebot.ui.graphic.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

/**
 * Created by Pierre on 06/05/2015.
 */
public class GraphicViewPanel extends JPanel {
    private Graph graph;
    private BufferedImage canvas;
    private ImageIcon basicImage;

    public GraphicViewPanel(){
        super();
        Dimension dimension = new Dimension(700, 400);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.graph = new Graph();

        canvas = new BufferedImage(700,400,BufferedImage.TYPE_INT_RGB);
    }

    public Graph getGraph() {
        return graph;
    }

    public void setBasicImage(ImageIcon image) {
        this.basicImage = image;
    }

    private void updateCanvas(){
        Graphics2D g2d = canvas.createGraphics();
        if(basicImage == null){
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setPaint(Color.white);
            g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }
        else{
            basicImage.paintIcon(null, g2d, 0, 0);
            //g2d.drawImage(basicImage, 0, 0, 700, 400, null);
        }

        graph.drawGraph(g2d);
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        updateCanvas();
        g.drawImage(canvas, 0, 0, null);
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
        updateCanvas();
        Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        g2d.setPaint(Color.magenta);
        g2d.drawLine(x1, y1, x2, y2);

        repaint();
        g.drawImage(canvas, 0, 0, null);
    }
}
