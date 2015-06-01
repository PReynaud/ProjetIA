package com.polytech4a.robocup.firebot.robots;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.search.ISearch;

import java.util.ArrayList;

/**
 * Created by Adrien CHAUSSENDE on 06/05/2015.
 *
 * @author Adrien CHAUSSENDE
 * @version 1.0
 *          <p/>
 *          Class representing a robot using legs to move.
 */
public class LeggedFirebot extends Firebot {

    public LeggedFirebot(int id, Graph graph, int capacity, ISearch searchAlgorithm) {
        super(id, graph, capacity, constructEdgeConstraints(), new ArrayList<NodeType>(), 15, searchAlgorithm);
    }

    /**
     * Method to construct the array list containing the type of edges where the robot can't go by.
     *
     * @return ArrayList of Edge Type
     */
    private static ArrayList<EdgeType> constructEdgeConstraints() {
        ArrayList<EdgeType> result = new ArrayList<>();
        result.add(EdgeType.ESCARPE);
        return result;
    }

}
