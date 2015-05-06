package com.polytech4a.firebot.ui.filepanel;

import javax.swing.*;

/**
 * Created by Pierre on 06/05/2015.
 */
public class LoadGraphButton extends JButton {
    public LoadGraphButton(){
        super();
        this.setText("Charger un graphique");
        this.setVisible(true);
    }

    public void addAction(Action a){
        this.setAction(a);
    }
}
