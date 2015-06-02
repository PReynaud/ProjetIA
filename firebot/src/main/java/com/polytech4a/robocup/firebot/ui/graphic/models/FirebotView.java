package com.polytech4a.robocup.firebot.ui.graphic.models;

import java.awt.*;

/**
 * Created by Pierre on 28/05/2015.
 * <p/>
 * Class that allow the displaying of the robots in the graphic view.
 */
public class FirebotView {

    /**
     * Color of the point.
     */
    protected Color color;
    /**
     * Id.
     */
    private int id;
    /**
     * Position of the robot in the graphic panel.
     */
    private int x;
    /**
     * Position of the robot in the graphic panel.
     */
    private int y;
    /**
     * Indicate if the robot is moving between two nodes.
     */
    private boolean isMoving;
    /**
     * Indicate the direction of the robot.
     */
    private int directionX;
    /**
     * Indicate the direction of the robot.
     */
    private int directionY;
    /**
     * Current Node of the Firebot.
     */
    private NodeView currentNode;

    /**
     * DestinationNode of the Firebot.
     */
    private NodeView destinationNode;

    /**
     * Time of the current travel of the firebot.
     */
    private long time;

    public FirebotView(int id, NodeView node) {
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

    public void setMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public NodeView getCurrentNode() {
        return currentNode;
    }

    /**
     * Set the currentNode for firebot.
     *
     * @param currentNode new currentNode View.
     */
    public void setCurrentNode(NodeView currentNode) {
        this.currentNode = currentNode;
        this.x = currentNode.getX();
        this.y = currentNode.getY();
    }

    public NodeView getDestinationNode() {
        return destinationNode;
    }

    public void setDestinationNode(NodeView destinationNode) {
        this.destinationNode = destinationNode;
    }

    public void setDirection(int x, int y) {
        this.directionX = x;
        this.directionY = y;
    }

    public void setTime(long time) {
        this.time = time;
    }

    /**
     * Draw the robot in the graphic panel with it's own color and position.
     *
     * @param g graphics.
     */
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillPolygon(new int[]{x - 5, x + 5, x}, new int[]{y + 3, y + 3, y - 7}, 3);
    }

    /**
     * Move the bot following it's direction.
     */
    public void moveBot() {
        double distanceX = x - destinationNode.getX();
        double distanceY = y - destinationNode.getY();

        directionX = -(int) (distanceX / (time / 500));
        directionY = -(int) (distanceY / (time / 500));

        x += directionX;
        y += directionY;

        time -= 500;
    }
}
