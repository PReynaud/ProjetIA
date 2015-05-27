package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.ui.GraphicViewPanel;
import com.polytech4a.robocup.firebot.ui.MainForm;
import com.polytech4a.robocup.firebot.ui.graphic.models.NodeView;
import com.polytech4a.robocup.graph.model.Edge;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.model.Node;
import com.polytech4a.robocup.graph.model.exceptions.MissingParameterException;

/**
 * Created by Pierre on 11/05/2015.
 */
public class MainController {
    private MainForm view;
    private Graph model;
    private Graph graph;

    private FileController fileController;
    private GraphicControlController graphicControlController;
    private SimulationController simulationController;
    private MouseController mouseController;

    private EnumSelection selectionMode;

    private NodeView lastClickedNode;

    public MainController(){
    }

    //TODO Constructeur avec en passage le modele en plus
    public  MainController(MainForm mainForm){
        this.view = mainForm;

        this.fileController = new FileController(this);
        this.graphicControlController = new GraphicControlController(this);
        this.simulationController = new SimulationController(this);
        this.mouseController = new MouseController(this);
        this.view.getGraphicViewPanel().addMouseListener(this.mouseController);
        this.view.getGraphicViewPanel().addMouseMotionListener(this.mouseController);
        this.selectionMode = EnumSelection.NOTHING;
    }

    public MainForm getView() {
        return view;
    }
    public EnumSelection getSelectionMode() {return selectionMode;}
    public NodeView getLastClickedNode() {
        return lastClickedNode;
    }
    public Graph getModel() {
        return model;
    }
    public Graph getGraph() {
        return graph;
    }
    public void setGraph(Graph graph) {
        this.graph = graph;
    }
    public void setModel(Graph model) {
        this.model = model;
    }
    public void setLastClickedNode(NodeView lastClickedNode) {
        this.lastClickedNode = lastClickedNode;
    }
    public void setSelectionMode(EnumSelection newMode){this.selectionMode = newMode;}

    public void transformModelGraphToView(){
        GraphicViewPanel graphicViewPanel = (GraphicViewPanel) view.getGraphicViewPanel();
        for(Node node: graph.getNodes()){
            try {
                graphicViewPanel.getGraph().addNode((int)node.getX(), (int)node.getY());
            } catch (MissingParameterException e) {
                //TODO exception
            }
        }
        for(Edge edge: graph.getEdges()){

            //graphicViewPanel.getGraph().
        }
    }
}

enum EnumSelection {
    NOTHING,
    ADD_NODE,
    ADD_EDGE
}
