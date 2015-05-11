package com.polytech4a.robocup.firebot.ui.simulationpanel;

import javax.swing.*;

/**
 * Created by Pierre on 06/05/2015.
 */
public class SimulationPanel extends JPanel {
    private PlayButton playButton;
    private PauseButton pauseButton;
    private StopButton stopButton;

    public SimulationPanel(){
        playButton = new PlayButton();
        pauseButton = new PauseButton();
        stopButton = new StopButton();

        this.add(playButton);
        this.add(pauseButton);
        this.add(stopButton);

        this.setSize(300, 150);
        this.setLocation(0, 0);
    }

    public PlayButton getPlayButton() {
        return playButton;
    }

    public PauseButton getPauseButton() {
        return pauseButton;
    }

    public StopButton getStopButton() {
        return stopButton;
    }
}
