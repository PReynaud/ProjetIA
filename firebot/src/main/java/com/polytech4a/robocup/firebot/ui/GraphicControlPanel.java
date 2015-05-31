package com.polytech4a.robocup.firebot.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Pierre on 06/05/2015.
 */
public class GraphicControlPanel extends JPanel {
    private JComboBox selectionEdge;
    private JComboBox selectionRobot;

    private InterfaceButton addNodeButton;
    private InterfaceButton addEdgeButton;
    private InterfaceButton addRobotButton;
    private InterfaceButton addFireButton;

    public GraphicControlPanel() {
        super();

        String[] tab = {"Normal", "Escarpé"};
        addNodeButton = new InterfaceButton("Ajouter noeud");
        selectionEdge = new JComboBox(tab);
        addEdgeButton = new InterfaceButton("Ajouter arc");
        String[] tab2 = {"Tout terrain", "Chenille", "A pattes"};
        selectionRobot = new JComboBox(tab2);
        addRobotButton = new InterfaceButton("Ajouter robot");
        addFireButton = new InterfaceButton("Ajouter incendie");

        this.setSize(150, 500);

        this.add(addNodeButton);
        this.add(insertSeparator());
        this.add(selectionEdge);
        this.add(addEdgeButton);
        this.add(insertSeparator());

        this.add(selectionRobot);
        this.add(addRobotButton);
        this.add(insertSeparator());

        this.add(addFireButton);
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

    public JComboBox getSelectionEdge() {
        return selectionEdge;
    }

    public JComboBox getSelectionRobot() {
        return selectionRobot;
    }

    private JSeparator insertSeparator(){
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        separator.setPreferredSize(new Dimension(20, 0));
        return separator;
    }

    public void resetColorOfButtons(){
        this.addEdgeButton.setBackground(null);
        this.addFireButton.setBackground(null);
        this.addNodeButton.setBackground(null);
        this.addRobotButton.setBackground(null);
    }
}
