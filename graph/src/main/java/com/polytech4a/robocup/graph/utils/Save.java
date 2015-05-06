package com.polytech4a.robocup.graph.utils;

import com.polytech4a.robocup.graph.model.Edge;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import org.apache.log4j.Logger;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Map;


/**
 * Created by Antoine CARON on 06/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 */
public class Save {
    /**
     * Logger.
     */
    private static final Logger logger = Logger.getLogger(Save.class);

    /**
     * Save method in order to save a graph in a File.
     *
     * @param graph graph to save.
     * @param file  Fie into save.
     */
    public void saveGraph(Graph graph, File file) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            //Creating Root
            Document doc = docBuilder.newDocument();
            Element root = doc.createElement("osm");
            doc.appendChild(root);
            //Adding Node
            for (Node n : graph.getNodes()) {
                Element node = doc.createElement("node");
                printParameters(n.getParameters(), node, doc);
                root.appendChild(node);
            }
            //Adding Edge
            for (Edge e : graph.getEdges()) {
                Element edge = doc.createElement("edge");
                printParameters(e.getParameters(), edge, doc);
                root.appendChild(edge);
            }
            //File Saving
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
        } catch (ParserConfigurationException pce) {
            logger.error(pce);
        } catch (TransformerException tfe) {
            logger.error(tfe);
        }
    }

    /**
     * Print parameters from a map to attributes of Element xml.
     *
     * @param map     List of parameters.
     * @param element Element of the document.
     * @param doc     Document.
     */
    private void printParameters(Map<String, String> map, Element element, Document doc) {
        for (String param : map.keySet()) {
            Attr attr = doc.createAttribute(param);
            attr.setValue(map.get(param));
            element.setAttributeNode(attr);
        }
    }
}

