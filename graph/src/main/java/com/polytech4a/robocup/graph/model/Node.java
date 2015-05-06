package com.polytech4a.robocup.graph.model;

import java.util.HashMap;

/**
 * Created by Antoine CARON on 06/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *
 * Node object of a Graph.
 */
public class Node {

    /**
     * Parameters of a Node.
     */
    private HashMap<String,String> parameters;

    public HashMap<String, String> getParameters() {
        return parameters;
    }

    public int getId(){
        return Integer.valueOf(parameters.get("id")).intValue();
    }

    public Node(int id) {
        this.parameters =new HashMap<String, String>();
        parameters.put("id", String.valueOf(id));
    }

    @Override
    public String toString() {
        StringBuffer stbf=new StringBuffer();
        stbf.append("Node{");
        for(String key:parameters.keySet()){
            stbf.append(key).append("=").append(parameters.get(key)).append(",");
        }
        stbf.append("}");
        return stbf.toString();
    }

    @Override
    protected Node clone() {
        return new Node(Integer.valueOf(parameters.get("id")).intValue());
    }

    public boolean equals(Node node) {
        return this.getId()==node.getId();
    }
}
