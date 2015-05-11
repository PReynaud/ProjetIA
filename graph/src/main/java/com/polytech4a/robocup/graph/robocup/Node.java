package com.polytech4a.robocup.graph.robocup;

import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.robocup.exceptions.MissingParameterException;
import com.polytech4a.robocup.graph.robocup.exceptions.NotFoundTypeException;

/**
 * Created by Antoine CARON on 11/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class Node extends com.polytech4a.robocup.graph.model.Node {

    public Node(int id) {
        super(id);
    }

    public NodeType getType() throws NotFoundTypeException {
        String s = getParameters().get("type");
        if (!s.isEmpty()) {
            for (NodeType e : NodeType.values()) {
                if (e.equals(s)) {
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
