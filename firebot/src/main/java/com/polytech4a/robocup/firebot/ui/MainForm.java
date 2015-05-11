package com.polytech4a.robocup.firebot.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Pierre on 06/05/2015.
 */
public class MainForm extends JFrame{
    private JPanel mainPanel;
    private JPanel filePanel;
    private JPanel graphicControlPanel;
    private JPanel simulationPanel;
    private JPanel graphicViewPanel;

    public MainForm(){
        super("FireBots");
        this.setSize(1000, 600);
        this.setResizable(false);
        initComponents();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });

        this.validate();
    }

    public void initComponents(){
        this.mainPanel = new JPanel();
        this.filePanel = new FilePanel();
        this.graphicControlPanel = new GraphicControlPanel();
        this.simulationPanel = new SimulationPanel();
        this.graphicViewPanel = new GraphicViewPanel();

        mainPanel.add(filePanel, BorderLayout.NORTH);
        mainPanel.add(simulationPanel, BorderLayout.NORTH);
        mainPanel.add(graphicViewPanel, BorderLayout.NORTH);
        mainPanel.add(graphicControlPanel, BorderLayout.EAST);
        this.add(mainPanel);
    }

    public JPanel getFilePanel() {
        return filePanel;
    }

    public JPanel getGraphicControlPanel() {
        return graphicControlPanel;
    }

    public JPanel getSimulationPanel() {
        return simulationPanel;
    }

    public JPanel getGraphicViewPanel() {
        return graphicViewPanel;
    }
}
