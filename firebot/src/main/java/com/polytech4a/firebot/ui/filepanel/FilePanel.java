package com.polytech4a.firebot.ui.filepanel;

import javax.swing.*;

/**
 * Created by Pierre on 06/05/2015.
 */
public class FilePanel extends JPanel {
    private LoadGraphButton loadGraphButton;
    private LoadImageButton loadImageButton;
    private SaveGraphButton saveGraphButton;

    public FilePanel(){
        this.loadGraphButton = new LoadGraphButton();
        this.loadImageButton = new LoadImageButton();
        this.saveGraphButton = new SaveGraphButton();

        this.add(loadImageButton);
        this.add(loadGraphButton);
        this.add(saveGraphButton);

        this.setVisible(true);
    }

    public LoadGraphButton getLoadGraphButton() {
        return loadGraphButton;
    }

    public LoadImageButton getLoadImageButton() {
        return loadImageButton;
    }

    public SaveGraphButton getSaveGraphButton() {
        return saveGraphButton;
    }
}
