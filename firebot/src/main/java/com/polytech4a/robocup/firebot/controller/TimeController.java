package com.polytech4a.robocup.firebot.controller;

import org.apache.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Pierre on 31/05/2015.
 */
public class TimeController {
    private static final Logger logger = Logger.getLogger(TimeController.class);

    private boolean running;
    private Timer timer;

    public TimeController(MainController mainController) {
        timer = new Timer();
        running = false;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(running){
                    logger.debug("View: regular update for moving the bots");
                    /*mainController.getGraphicViewController().animateFirebots();
                    GraphicViewPanel graphicViewPanel = (GraphicViewPanel) mainController.getView().getGraphicViewPanel();
                    graphicViewPanel.paintComponent(graphicViewPanel.getGraphics());*/
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
