package com.polytech4a.firebot.ui.graphicviewpanel;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Pierre on 06/05/2015.
 */
public class GraphicViewPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        Dimension dim = getSize();
        g.fillRect(0, 0, dim.width, dim.height);
        g.setColor(Color.white);
        super.paintComponent(g);
    }
}
