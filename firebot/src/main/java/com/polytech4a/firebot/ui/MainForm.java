package com.polytech4a.firebot.ui;

import com.polytech4a.firebot.ui.filepanel.FilePanel;
import com.polytech4a.firebot.ui.graphiccontrolpanel.GraphicControlPanel;
import com.polytech4a.firebot.ui.graphicviewpanel.GraphicViewPanel;
import com.polytech4a.firebot.ui.simulationpanel.SimulationPanel;

import javax.swing.*;
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
        this.setSize(600, 400);
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

        mainPanel.add(filePanel);
        this.add(mainPanel);
    }
}
