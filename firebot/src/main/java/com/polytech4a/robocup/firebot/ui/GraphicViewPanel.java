package com.polytech4a.robocup.firebot.ui;

import com.polytech4a.robocup.firebot.controller.EnumSelection;
import com.polytech4a.robocup.firebot.ui.graphic.models.*;
import com.polytech4a.robocup.graph.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Pierre on 06/05/2015.
 */
public class GraphicViewPanel extends JPanel {
    private GraphView graph;
    private BufferedImage canvas;
    private Image basicImage;

    public GraphicViewPanel(){
        super();
        Dimension dimension = new Dimension(700, 400);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.graph = new GraphView();

        canvas = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_RGB);
    }

    public GraphView getGraph() {
        return graph;
    }

    public void setBasicImage(Image image) {
        this.basicImage = image;
    }

    /**
     * Call the update of the graphic view
     */
    private void updateCanvas(){
        Graphics2D g2d = canvas.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.white);
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        if(basicImage != null){
            if(basicImage.getHeight(null) > this.getY() || basicImage.getWidth(null) > this.getX()){
                g2d.drawImage(basicImage, 0, 0, basicImage.getWidth(null), basicImage.getHeight(null), null);
            }
            else{
                g2d.drawImage(basicImage, 0, 0, this.getWidth(), this.getHeight(), null);
            }
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
    public void paintComponentWithCustomEdge(Graphics g, int x1, int y1, int x2, int y2, EnumSelection type){
        updateCanvas();
        Graphics2D g2d = (Graphics2D) canvas.getGraphics();
        if(type.equals(EnumSelection.ADD_STEEP_EDGE)){
            g2d.setPaint(Color.GREEN);
        }
        if(type.equals(EnumSelection.ADD_EDGE)){
            g2d.setPaint(Color.MAGENTA);
        }
        g2d.drawLine(x1, y1, x2, y2);

        repaint();
        g.drawImage(canvas, 0, 0, null);
    }

    public void resetGraphicView(){
        this.basicImage = null;
        this.graph = new GraphView();
        paintComponent(this.getGraphics());
    }
}
