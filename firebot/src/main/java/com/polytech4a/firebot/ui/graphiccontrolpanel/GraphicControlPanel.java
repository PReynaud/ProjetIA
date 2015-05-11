package com.polytech4a.firebot.ui.graphiccontrolpanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Pierre on 06/05/2015.
 */
public class GraphicControlPanel extends JPanel {
    private AddNodeButton addNodeButton;
    private AddEdgeButton addEdgeButton;
    private AddRobotButton addRobotButton;
    private AddFireButton addFireButton;

    public GraphicControlPanel(){
        super();

        addNodeButton = new AddNodeButton();
        addEdgeButton = new AddEdgeButton();
        addRobotButton = new AddRobotButton();
        addFireButton = new AddFireButton();

        this.setSize(150, 500);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(addNodeButton);
        this.add(addEdgeButton);
        this.add(addRobotButton);
        this.add(addFireButton);
    }

    public Insets getInsets() {
        Insets normal = super.getInsets();
        return new Insets(normal.top + 10, normal.left + 10,
                normal.bottom + 50, normal.right + 10);
    }
}
