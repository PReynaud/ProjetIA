package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;

/**
 * Created by Pierre on 28/05/2015.
 */
public class FirebotView {
    private int x;
    private int y;

    /**
     * Color of the point
     */
    protected Color color;

    public FirebotView(int x, int y) {
        this.x = x;
        this.y = y;

        this.color = Color.GREEN;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g){
        Polygon polygon = new Polygon();
        g.setColor(this.color);
        g.fillPolygon(new int[]{x - 5, x + 5, x}, new int[]{y + 3, y + 3, y - 7}, 3);
    }
}
