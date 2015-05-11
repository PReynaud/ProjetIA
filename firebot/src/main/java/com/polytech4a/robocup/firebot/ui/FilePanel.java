package com.polytech4a.robocup.firebot.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Pierre on 06/05/2015.
 */
public class FilePanel extends JPanel {
    private InterfaceButton loadGraphButton;
    private InterfaceButton loadImageButton;
    private InterfaceButton saveGraphButton;

    public FilePanel(){
        this.loadGraphButton = new InterfaceButton("Charger graphique");
        this.loadImageButton = new InterfaceButton("Charger image");
        this.saveGraphButton = new InterfaceButton("Sauvegarder graphique");

        this.add(loadImageButton);
        this.add(loadGraphButton);
        this.add(saveGraphButton);

        this.setSize(700, 150);
        this.setLocation(0, 0);

    }

    public InterfaceButton getLoadGraphButton() {
        return loadGraphButton;
    }

    public InterfaceButton getLoadImageButton() {
        return loadImageButton;
    }

    public InterfaceButton getSaveGraphButton() {
        return saveGraphButton;
    }

    public Insets getInsets() {
        Insets normal = super.getInsets();
        return new Insets(normal.top + 10, normal.left + 10,
                normal.bottom + 10, normal.right + 200);
    }
}
