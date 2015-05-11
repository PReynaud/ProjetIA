package com.polytech4a.robocup.firebot.ui.filepanel;

import javax.swing.*;
import java.awt.*;

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

        this.setSize(700, 150);
        this.setLocation(0, 0);

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

    public Insets getInsets() {
        Insets normal = super.getInsets();
        return new Insets(normal.top + 10, normal.left + 10,
                normal.bottom + 10, normal.right + 200);
    }
}
