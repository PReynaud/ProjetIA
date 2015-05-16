package com.polytech4a.robocup.graph.model;

import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.exceptions.MissingParameterException;
import com.polytech4a.robocup.graph.model.exceptions.NotFoundTypeException;

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

    public boolean equals(Node node) {
        return this.getId() == node.getId();
    }

    public NodeType getType() throws NotFoundTypeException {
        String s = getParameters().get("type");
        if (!s.isEmpty()) {
            for (NodeType e : NodeType.values()) {
                if (e.name().equals(s)) {
                    return e;
                }
            }
        }
        throw new NotFoundTypeException("Type '" + s + "' not declared in NodeType.");
    }

    public double getX() throws MissingParameterException {
        try {
            return Double.valueOf(getParameters().get("x"));
        } catch (NumberFormatException e) {
            throw new MissingParameterException("X value is missing or not a number", e);
        }
    }

    public double getY() throws MissingParameterException {
        try {
            return Double.valueOf(getParameters().get("y"));
        } catch (NumberFormatException e) {
            throw new MissingParameterException("y value is missing or not a number", e);
        }
    }
}
