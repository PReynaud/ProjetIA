package com.polytech4a.robocup.graph.robocup;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.robocup.exceptions.NotFoundTypeException;

import java.util.HashMap;

/**
 * Created by Antoine CARON on 11/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class Edge extends com.polytech4a.robocup.graph.model.Edge {

    public Edge(int n1, int n2, EdgeType type) {
        super(n1, n2);
        getParameters().put("type", type.name());
    }

    public Edge(HashMap<String, String> parameters) {
        super(parameters);
    }

    public EdgeType getType() throws NotFoundTypeException {
        String s = getParameters().get("type");
        NotFoundTypeException ex = new NotFoundTypeException("Type '" + s + "' not declared in EdgeType.");
        if (!s.isEmpty()) {
            for (EdgeType e : EdgeType.values()) {
                if (e.name().equals(s)) {
                    return e;
                }
            }
            throw ex;
        } else throw ex;
    }
}
