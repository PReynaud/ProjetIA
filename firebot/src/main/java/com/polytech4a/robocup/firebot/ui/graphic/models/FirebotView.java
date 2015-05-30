package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;

/**
 * Created by Pierre on 28/05/2015.
 * Class that allow the displaying of the robots in the graphic view
 */
public class FirebotView {
    /**
     * Position of the robot in the graphic panel
     */
    private int x;
    /**
     * Position of the robot in the graphic panel
     */
    private int y;

    /**
     * Color of the point
     */
    protected Color color;

    /**
     * Indicate if the robot is moving between two nodes
     */
    private boolean isMoving;
    /**
     * Indicate the direction of the robot
     */
    private int directionX;
    /**
     * Indicate the direction of the robot
     */
    private int directionY;

    private NodeView currentNode;
    private NodeView destinationNode;

    public FirebotView(NodeView node) {
        this.x = node.getX();
        this.y = node.getY();
        this.currentNode = node;
        this.isMoving = false;
        this.directionX = 0;
        this.directionY = 0;
        this.color = Color.GREEN;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public boolean isMoving() {
        return isMoving;
    }
    public int getDirectionX() {
        return directionX;
    }
    public int getDirectionY() {
        return directionY;
    }

    public void setDirection(int x, int y){
        this.directionX = x;
        this.directionY = y;
    }

    /**
     * Draw the robot in the graphic panel with it's won color and position
     * @param g
     */
    public void draw(Graphics g){
        Polygon polygon = new Polygon();
        g.setColor(this.color);
        g.fillPolygon(new int[]{x - 5, x + 5, x}, new int[]{y + 3, y + 3, y - 7}, 3);
    }
}
