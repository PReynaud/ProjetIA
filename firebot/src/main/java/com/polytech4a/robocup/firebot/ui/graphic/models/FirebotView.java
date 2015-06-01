package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;

/**
 * Created by Pierre on 28/05/2015.
 * Class that allow the displaying of the robots in the graphic view
 */
public class FirebotView {
    private int id;

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

    public FirebotView(int id, NodeView node){
        this.id = id;
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
    public int getId() {
        return id;
    }
    public boolean isMoving() {
        return isMoving;
    }
    public NodeView getCurrentNode() {
        return currentNode;
    }
    public NodeView getDestinationNode() {
        return destinationNode;
    }

    public void setDirection(int x, int y){
        this.directionX = x;
        this.directionY = y;
    }
    public void setCurrentNode(NodeView currentNode) {
        this.currentNode = currentNode;
        this.x = currentNode.getX();
        this.y = currentNode.getY();
    }
    public void setDestinationNode(NodeView destinationNode) {
        this.destinationNode = destinationNode;
    }
    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    /**
     * Draw the robot in the graphic panel with it's own color and position
     * @param g
     */
    public void draw(Graphics g){
        g.setColor(this.color);
        g.fillPolygon(new int[]{x - 5, x + 5, x}, new int[]{y + 3, y + 3, y - 7}, 3);
    }

    /**
     * Move the bot following it's direction
     */
    public void moveBot(){
        x += directionX;
        y += directionY;
    }
}
