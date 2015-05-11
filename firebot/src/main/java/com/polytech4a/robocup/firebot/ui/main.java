package com.polytech4a.robocup.firebot.ui;

import com.polytech4a.robocup.firebot.controller.MainController;

import javax.swing.*;

/**
 * Created by Pierre on 06/05/2015.
 */
//TODO: classe à effacer, juste utile pour tester la fenêtre
public class main {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainForm window = new MainForm();
                MainController controller = new MainController(window);
                window.setVisible(true);
            }
        });
    }
}