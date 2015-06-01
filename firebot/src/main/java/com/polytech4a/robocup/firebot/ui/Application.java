package com.polytech4a.robocup.firebot.ui;

import com.polytech4a.robocup.firebot.controller.MainController;
import com.polytech4a.robocup.firebot.robots.RobotManager;
import com.polytech4a.robocup.graph.model.Graph;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Pierre on 06/05/2015.
 */
public class Application {

    /**
     * Logger.
     */
    private static final Logger logger = Logger.getLogger(Application.class);

    public static void main(String[] args){
        logger.info("Starting Application...");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainForm window = new MainForm();
                RobotManager robotManager = new RobotManager(new ArrayList<>(), new Graph());
                MainController controller = new MainController(window, robotManager);
                window.setVisible(true);
            }
        });
    }
}