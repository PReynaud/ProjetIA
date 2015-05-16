package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;

/**
 * Created by Pierre on 16/05/2015.
 */
public class Node {
    private int x;
    private int y;

    private int size;

    protected Color color;

    public Node(int x, int y){
        this.x = x;
        this.y = y;

        this.size = 20;
        this.color = Color.MAGENTA;
    }


    public void drawNode(Graphics g){
        g.setColor(this.color);
        g.fillOval(x-(size/2), y-(size/2), size, size);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
