package com.polytech4a.robocup.firebot.ui;

import javax.swing.*;

/**
 * Created by Pierre on 11/05/2015.
 */
public class InterfaceButton extends JButton {
    private String label;

    public InterfaceButton(String label){
        super();
        this.label = label;
    }

    public void addAction(Action a){
        this.setAction(a);
        this.setText(label);
    }
}
