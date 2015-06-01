package com.polytech4a.robocup.graph.utils;

import com.polytech4a.robocup.graph.model.Edge;
import com.polytech4a.robocup.graph.model.Graph;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Antoine CARON on 06/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p>
 *          Load Class for loading graph from XML File.
 */
public final class Load {

    /**
     * Load function for loading a graph from a XML file.
     *
     * @param file file to read.
     * @return Graph with Nodes and Edges.
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public static Graph loadGraph(File file) throws IOException, ParserConfigurationException, SAXException, MalformGraphException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        //getting Nodes

        NodeList nodes = doc.getDocumentElement().getElementsByTagName("node");
        NodeList edges = doc.getDocumentElement().getElementsByTagName("edge");

        Graph graph = new Graph();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node xmlNode = nodes.item(i);
            Element el = (Element) xmlNode;
            NamedNodeMap attr = el.getAttributes();
            HashMap<String, String> map = new HashMap<>();
            if (!el.getAttribute("id").isEmpty()) {
                for (int j = 0; j < attr.getLength(); j++) {
                    Node attribut = attr.item(j);
                    map.put(attribut.getNodeName(), attribut.getNodeValue());
                }
                com.polytech4a.robocup.graph.model.Node graphNode =
                        new com.polytech4a.robocup.graph.model.Node(map);
                graph.addNode(graphNode);
            } else throw new MalformGraphException("One Node does not contain an id.");
        }
        for (int i = 0; i < edges.getLength(); i++) {
            Node xmlNode = edges.item(i);
            Element el = (Element) xmlNode;
            NamedNodeMap attr = el.getAttributes();
            if (!el.getAttribute("nd1").isEmpty() && !el.getAttribute("nd2").isEmpty()) {
                HashMap<String, String> map = new HashMap<>();
                for (int j = 0; j < attr.getLength(); j++) {
                    Node attribut = attr.item(j);
                    map.put(attribut.getNodeName(), attribut.getNodeValue());
                }
                Edge graphEdge = new Edge(map);
                graph.addEdge(graphEdge);
            } else throw new MalformGraphException("One Edge does not contain aan id.");
        }
        return graph;
    }
}
