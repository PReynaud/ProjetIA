package com.polytech4a.robocup.graph.robocup;


import com.polytech4a.robocup.graph.enums.EdgeType;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Antoine CARON on 11/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class EdgeTest {

    @Test
    public void testGetType() throws Exception {
        Edge e = new Edge(1, 2, EdgeType.ESCARPE);
        TestCase.assertEquals(EdgeType.ESCARPE, e.getType());
    }

    @Test
    public void testGetTypeParameters() throws Exception {
        HashMap<String, String> param = new HashMap<>();
        param.put("nd1", "1");
        param.put("nd2", "2");
        param.put("type", "PLAT");
        Edge e = new Edge(param);
        TestCase.assertEquals(EdgeType.PLAT, e.getType());
    }
}