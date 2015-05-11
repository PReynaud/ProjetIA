package com.polytech4a.robocup.firebot.ui;

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
                window.setVisible(true);
            }
        });
    }
}