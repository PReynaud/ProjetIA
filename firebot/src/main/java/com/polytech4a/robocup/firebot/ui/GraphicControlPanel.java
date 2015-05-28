package com.polytech4a.robocup.firebot.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Pierre on 06/05/2015.
 */
public class GraphicControlPanel extends JPanel {
    private InterfaceButton addNodeButton;
    private InterfaceButton addEdgeButton;
    private InterfaceButton addRobotButton;
    private InterfaceButton addFireButton;
    private InterfaceButton addSteepEdge;

    public GraphicControlPanel() {
        super();

        addNodeButton = new InterfaceButton("Ajouter noeud");
        addEdgeButton = new InterfaceButton("Ajouter arc");
        addRobotButton = new InterfaceButton("Ajouter robot");
        addFireButton = new InterfaceButton("Ajouter incendie");
        addSteepEdge = new InterfaceButton("Ajouter arc escarpe");

        this.setSize(150, 500);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(addNodeButton);
        this.add(addEdgeButton);
        this.add(addRobotButton);
        this.add(addFireButton);
        this.add(addSteepEdge);
    }

    public Insets getInsets() {
        Insets normal = super.getInsets();
        return new Insets(normal.top + 10, normal.left + 10,
                normal.bottom + 50, normal.right + 10);
    }

    public InterfaceButton getAddNodeButton() {
        return addNodeButton;
    }

    public InterfaceButton getAddEdgeButton() {
        return addEdgeButton;
    }

    public InterfaceButton getAddRobotButton() {
        return addRobotButton;
    }

    public InterfaceButton getAddFireButton() {
        return addFireButton;
    }

    public InterfaceButton getAddSteepEdge() {
        return addSteepEdge;
    }
}
