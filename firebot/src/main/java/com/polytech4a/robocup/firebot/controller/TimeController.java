package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.ui.GraphicViewPanel;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Pierre on 31/05/2015.
 */
public class TimeController {
    private boolean running;
    private Timer timer;

    public TimeController(MainController mainController) {
        timer = new Timer();
        running = false;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(running){
                    mainController.getGraphicViewController().animateFirebots();
                    GraphicViewPanel graphicViewPanel = (GraphicViewPanel) mainController.getView().getGraphicViewPanel();
                    graphicViewPanel.paintComponent(graphicViewPanel.getGraphics());
                }
            }
        }, 500, 500);
    }

    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
}
