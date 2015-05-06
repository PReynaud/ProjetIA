package com.polytech4a.firebot.ui.filepanel;

import javax.swing.*;

/**
 * Created by Pierre on 06/05/2015.
 */
public class LoadImageButton extends JButton {
    public LoadImageButton(){
        super();
        this.setText("Charger une image");
        this.setVisible(true);
    }

    public void addAction(Action a){
        this.setAction(a);
    }

}
