package com.polytech4a.robocup.graph.utils;

import com.polytech4a.robocup.graph.model.Graph;
import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Created by Antoine CARON on 11/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class SaveTest {

    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void testSaveGraph() throws Exception {
        File file = temp.newFile("temp.xml");
        Save s = new Save();
        File fileLoaded = FileUtils.getFile("src", "test", "resources", "mapsixieme.xml");
        Load l = new Load();
        Graph g = l.loadGraph(fileLoaded);
        s.saveGraph(g, file);
        assertTrue(FileUtils.readFileToByteArray(file).length > 0);
    }
}