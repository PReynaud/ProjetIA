package com.polytech4a.robocup.graph.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Antoine CARON on 06/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class Edge {
    private Map<String, String> parameters;

    /**
     * Constructor of the Edge
     * @param n1 id of the first node
     * @param n2 id of the second node
     */
    public Edge (int n1, int n2){
        this.parameters = new HashMap<String, String>();
        this.parameters.put("nd1",String.valueOf(n1));
        this.parameters.put("nd2",String.valueOf(n2));
    }

    /**
     * Constructor of a Edge.
     *
     * @param parameters Hashmap of parameters.
     */
    public Edge(HashMap<String, String> parameters) {
        this.parameters = parameters;
    }

    /**
     * Getter of the first node
     * @return
     */
    public int getNode1() {
        return Integer.valueOf(parameters.get("nd1"));
    }

    /**
     * Getter of the second node
     * @return
     */
    public int getNode2() {
        return Integer.valueOf(parameters.get("nd2"));
    }

    /**
     * Getter of the Edge parameters
     * @return Map of parameters
     */
    public Map<String, String> getParameters() {
        return parameters;
    }

    public boolean equals(Edge obj) {
            return obj.getNode1() == this.getNode1() && obj.getNode2() == this.getNode2();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Edge [").append(this.getNode1()).append(";")
                .append(this.getNode2()).append("] : Parameters [");
        for (String key: parameters.keySet()){
            str.append("[").append(key).append(";").append(parameters.get(key)).append("]");
        }
        return str.append("]").toString();
    }

    @Override
    protected Edge clone(){
        HashMap<String,String> clonedParameters = new HashMap<>();
        for (String parameter : parameters.keySet()){
            clonedParameters.put(parameter, parameters.get(parameter));
        }
        return new Edge(clonedParameters);
    }

    /**
     * Test if the edge contains an certain node
     * @param idNode id of the node
     * @return true if the edge contains the node
     */
    public boolean hasNode (int idNode){
        return getNode1()==idNode||getNode2()==idNode;
    }

    /**
     * Get the second Node linked by the Edge
     * @param node id of the first node
     * @return id of the second node or -1 if the first node is not known
     */
    public int getSecondNode (int node){
        if (getNode1()==node){
            return getNode1();
        }
        else if(getNode2()==node){
            return  getNode2();
        }
        return -1;
    }
}
