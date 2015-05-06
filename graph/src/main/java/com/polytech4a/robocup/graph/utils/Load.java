package com.polytech4a.robocup.graph.utils;

import com.polytech4a.robocup.graph.model.Edge;
import com.polytech4a.robocup.graph.model.Graph;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created by Antoine CARON on 06/05/2015.
 *
 * @author Antoine CARON
 * @version 1.0
 *          <p>
 *          Load Class for loading graph from XML File.
 */
public class Load {

    /**
     * Load function for loading a graph from a XML file.
     *
     * @param file file to read.
     * @return Graph with Nodes and Edges.
     * @throws IOException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public Graph loadGraph(File file) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();
        //getting Nodes
        NodeList nodes = doc.getElementsByTagName("node");
        NodeList edges = doc.getElementsByTagName("edge");

        Graph graph = new Graph();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node xmlNode = nodes.item(i);
            Element el = (Element) xmlNode;
            com.polytech4a.robocup.graph.model.Node graphNode =
                    new com.polytech4a.robocup.graph.model.Node(Integer.valueOf(el.getAttribute("id")));
            graph.addNode(graphNode);
        }
        for (int i = 0; i < edges.getLength(); i++) {
            Node xmlNode = nodes.item(i);
            Element el = (Element) xmlNode;
            Edge graphEdge = new Edge(Integer.valueOf(el.getAttribute("nb1")), Integer.valueOf(el.getAttribute("nb2")));
            graph.addEdge(graphEdge);
        }
        return graph;
    }
}
