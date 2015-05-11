package com.polytech4a.robocup.firebot.ui;

import javax.swing.*;

/**
 * Created by Pierre on 06/05/2015.
 */
public class SimulationPanel extends JPanel {
    private InterfaceButton playButton;
    private InterfaceButton pauseButton;
    private InterfaceButton stopButton;

    public SimulationPanel(){
        playButton = new InterfaceButton("Lancer");
        pauseButton = new InterfaceButton("Pause");
        stopButton = new InterfaceButton("Stop");

        this.add(playButton);
        this.add(pauseButton);
        this.add(stopButton);

        this.setSize(300, 150);
        this.setLocation(0, 0);
    }

    public InterfaceButton getPlayButton() {
        return playButton;
    }

    public InterfaceButton getPauseButton() {
        return pauseButton;
    }

    public InterfaceButton getStopButton() {
        return stopButton;
    }
}
