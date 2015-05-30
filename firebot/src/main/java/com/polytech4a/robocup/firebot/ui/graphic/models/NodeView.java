package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;

/**
 * Created by Pierre on 16/05/2015.
 * @version 1.0
 *          <p/>
 *          Class for displaying the node in the graphic panel
 */
public class NodeView {
    /**
     * Id of the node based on the one from the model
     */
    private int id;

    /**
     * Position where to display the node int the graphic view
     */
    private int x;
    /**
     * Position where to display the node int the graphic view
     */
    private int y;

    /**
     * Radius of the point which will be drawn
     */
    private int size;

    /**
     * Color of the point
     */
    protected Color color;

    public NodeView(int x, int y){
        this.id = 0;
        this.x = x;
        this.y = y;

        this.size = 20;
        this.color = Color.MAGENTA;
    }

    public NodeView(int x, int y, int id){
        this.id = id;
        this.x = x;
        this.y = y;

        this.size = 20;
        this.color = Color.MAGENTA;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public int getId() {
        return id;
    }

    /**
     * Draw the node
     * @param g Graphic object from the graphic panel
     */
    public void drawNode(Graphics g){
        g.setColor(this.color);
        g.fillOval(x-(size/2), y-(size/2), size, size);
    }

}
