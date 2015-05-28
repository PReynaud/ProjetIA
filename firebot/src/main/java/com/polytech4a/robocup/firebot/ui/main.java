package com.polytech4a.robocup.firebot.ui;

import com.polytech4a.robocup.firebot.controller.MainController;
import com.polytech4a.robocup.firebot.robots.Firebot;
import com.polytech4a.robocup.firebot.robots.RobotManager;
import com.polytech4a.robocup.graph.model.Graph;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by Pierre on 06/05/2015.
 */
//TODO: classe à effacer, juste utile pour tester la fenêtre
public class main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainForm window = new MainForm();
                RobotManager robotManager = new RobotManager(new ArrayList<Firebot>(), new Graph());
                MainController controller = new MainController(window, robotManager);
                window.setVisible(true);
            }
        });
    }
}