package com.polytech4a.robocup.graph.model;

import com.polytech4a.robocup.graph.enums.EdgeType;
import com.polytech4a.robocup.graph.model.exceptions.NotFoundTypeException;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Antoine CARON on 16/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class EdgeTest {

    @Test
    public void testGetType() throws Exception {
        Edge e=new Edge(1,2, EdgeType.ESCARPE);
        assertEquals(EdgeType.ESCARPE, e.getType());
        HashMap<String,String> map=new HashMap<>();
        map.put("nd1","0");
        map.put("nd2","1");
        map.put("type", EdgeType.ESCARPE.name());
        Edge e1=new Edge(map);
        assertEquals(EdgeType.ESCARPE,e1.getType());
    }

    @Test(expected = NotFoundTypeException.class)
    public void testGetTypeExc() throws Exception {
        HashMap<String,String> map=new HashMap<>();
        map.put("nd1","0");
        map.put("nd2","1");
        map.put("type", "TEST");
        Edge e1=new Edge(map);
        e1.getType();
    }
}