package com.polytech4a.robocup.graph.model;

import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.exceptions.MissingParameterException;
import com.polytech4a.robocup.graph.model.exceptions.NotFoundTypeException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Antoine CARON on 06/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p/>
 *          Node object of a Graph.
 */
public class Node {

    private static final Logger logger=Logger.getLogger(Node.class);
    /**
     * Parameters of a Node.
     */
    private HashMap<String, String> parameters;

    public Node(int id, double x, double y, NodeType type) {
        this.parameters = new HashMap<String, String>();
        parameters.put("id", String.valueOf(id));
        parameters.put("x", String.valueOf(x));
        parameters.put("y", String.valueOf(y));
        parameters.put("type", String.valueOf(type));
    }


    public Node(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public int getId() {
        return Integer.valueOf(parameters.get("id")).intValue();
    }

    public NodeType getType() throws NotFoundTypeException, MissingParameterException {
        String s = getParameters().get("type");
        if (s != null) {
            if (!s.isEmpty()) {
                for (NodeType e : NodeType.values()) {
                    if (e.name().equals(s)) {
                        return e;
                    }
                }
            }
            throw new NotFoundTypeException("Type '" + s + "' not declared in NodeType.");
        }throw new MissingParameterException("Type is missing");
    }

    public void setType(NodeType type) {
        getParameters().put("type", type.name());
    }

    public double getX() throws MissingParameterException {
        try {
            return Double.valueOf(getParameters().get("x"));
        } catch (NumberFormatException e) {
            throw new MissingParameterException("X value is not a number", e);
        } catch (NullPointerException e) {
            throw new MissingParameterException("X value is missing", e);
        }
    }

    public double getY() throws MissingParameterException {
        try {
            return Double.valueOf(getParameters().get("y"));
        } catch (NumberFormatException e) {
            throw new MissingParameterException("Y value is missing or not a number", e);
        } catch (NullPointerException e) {
            throw new MissingParameterException("Y value is missing", e);
        }
    }


    /**
     * Get Euclidian space between two nodes
     *
     * @param node target node
     * @return space between the two nodes
     * @throws MissingParameterException
     */
    public double getEuclidianSpace(Node node) throws MissingParameterException {
        if (node == null) {
            throw new MissingParameterException("The second node is not initialized");
        }
        double deltaX = node.getX() - getX(),
                deltaY = node.getY() - getY();
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }

    public boolean isNodeFromType(ArrayList<NodeType> types){
        try {
            return types.contains(this.getType());
        } catch (NotFoundTypeException e) {
            logger.error(e.getMessage(),e);
        } catch (MissingParameterException e) {
            logger.error(e.getMessage(),e);
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuffer stbf = new StringBuffer();
        stbf.append("Node{");
        for (String key : parameters.keySet()) {
            stbf.append(key).append("=").append(parameters.get(key)).append(",");
        }
        stbf.append("}");
        return stbf.toString();
    }

    @Override
    protected Node clone() {
        HashMap<String, String> clonedParameters = new HashMap<>();
        for (String parameter : parameters.keySet()) {
            clonedParameters.put(parameter, parameters.get(parameter));
        }
        return new Node(clonedParameters);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Node) {
            return ((Node) obj).getId() == getId();
        }
        return false;
    }
}
