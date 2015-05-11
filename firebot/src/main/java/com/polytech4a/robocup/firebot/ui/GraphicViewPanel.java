package com.polytech4a.robocup.firebot.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Pierre on 06/05/2015.
 */
public class GraphicViewPanel extends JPanel {
    public GraphicViewPanel(){
        super();
        Dimension dimension = new Dimension(700, 400);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBackground(Color.white);
    }

    @Override
    public void paintComponent(Graphics g) {
        Dimension dim = getSize();
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(Color.white);
        super.paintComponent(g);
    }
}
