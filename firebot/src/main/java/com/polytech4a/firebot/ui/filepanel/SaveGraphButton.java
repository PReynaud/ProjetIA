package com.polytech4a.firebot.ui.filepanel;

import javax.swing.*;

/**
 * Created by Pierre on 06/05/2015.
 */
public class SaveGraphButton extends JButton {
    public SaveGraphButton(){
        super();
        this.setText("Sauvegarder graphique");
        this.setVisible(true);
    }

    public void addAction(Action a){
        this.setAction(a);
    }
}
