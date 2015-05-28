package com.polytech4a.robocup.graph.model.search;

import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.utils.Load;
import com.polytech4a.robocup.graph.utils.MalformGraphException;
import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @author Dimitri on 27/05/2015.
 * @version 1.0
 */

@Ignore
public abstract class SearchAlgorithmTest {
    protected static Graph graph;
    protected SearchAlgorithm searchAlgorithm;

    public static void setGraph() {
        File file = FileUtils.getFile("src", "test", "resources", "test.xml");
        Load l = new Load();
        try {
            SearchAlgorithmTest.graph = l.loadGraph(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (MalformGraphException e) {
            e.printStackTrace();
        }
    }
}