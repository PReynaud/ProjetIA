package com.polytech4a.robocup.graph.model;

import com.polytech4a.robocup.graph.enums.NodeType;
import com.polytech4a.robocup.graph.model.exceptions.MissingParameterException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Antoine CARON on 16/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class NodeTest {

    private Node n1;

    private Node n2;

    @Before
    public void setUp() throws Exception {
        this.n1=new Node(1,2.0,3.0, NodeType.NORMAL);
        HashMap<String,String> map=new HashMap<>();
        map.put("id","0");
        map.put("x","3.72");
        map.put("y","7.2");
        map.put("type",NodeType.INCENDIE.name());
        this.n2=new Node(map);
    }

    @After
    public void tearDown() throws Exception {
        this.n1=null;
        this.n2=null;
    }

    @Test
    public void testGetType() throws Exception {
        assertEquals(NodeType.NORMAL,n1.getType());
        assertEquals(NodeType.INCENDIE,n2.getType());
    }

    @Test
    public void testGetX() throws Exception {
        assertEquals(2.0,n1.getX(),0.0001);
        assertEquals(3.72,n2.getX(),0.0001);
    }

    @Test
    public void testGetY() throws Exception {
        assertEquals(3.0,n1.getY(),0.0001);
        assertEquals(7.2,n2.getY(),0.0001);
    }

    @Test(expected = MissingParameterException.class)
    public void testGetXExc() throws Exception {
        n2.getParameters().remove("x");
        n2.getX();
    }

    @Test(expected = MissingParameterException.class)
    public void testGetYExc() throws Exception {
        n2.getParameters().remove("y");
        n2.getY();
    }

    @Test(expected = MissingParameterException.class)
    public void testGetXExc2() throws Exception {
        n2.getParameters().replace("x", "test");
        n2.getX();
    }

    @Test(expected = MissingParameterException.class)
    public void testGetYExc2() throws Exception {
        n2.getParameters().replace("y","test");
        n2.getY();
    }

    @Test
    public void testGetEuclidianSpace() throws Exception {
        double dist=n1.getEuclidianSpace(n2);
        double diffx=Math.pow(n2.getX() - n1.getX(), 2);
        double diffy=Math.pow(n2.getY() -n1.getY(),2);
        assertEquals(Math.sqrt(diffx+diffy),dist,0.0001);
    }
}