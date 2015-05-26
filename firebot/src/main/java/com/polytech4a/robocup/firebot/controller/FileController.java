package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.ui.FilePanel;
import com.polytech4a.robocup.firebot.ui.GraphicViewPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Pierre on 11/05/2015.
 */
public class FileController {
    private MainController mainController;

    public FileController(MainController mainController){
        this.mainController = mainController;

        FilePanel filePanel = (FilePanel) mainController.getView().getFilePanel();
        filePanel.getLoadGraphButton().addAction(new LoadGraphAction(mainController));
        filePanel.getLoadImageButton().addAction(new LoadImageAction(mainController));
        filePanel.getSaveGraphButton().addAction(new SaveGraphAction());
    }
}


class LoadGraphAction extends AbstractAction{
    MainController mainController;
    public LoadGraphAction(MainController mainController){
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File file;
        JFileChooser fileChooser = new JFileChooser(new File("."));

        if(fileChooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            //TODO charger le graphe
        }
    }
}


class LoadImageAction extends AbstractAction{
    MainController mainController;
    public LoadImageAction(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File file;
        JFileChooser fileChooser = new JFileChooser(new File("."));

        FileFilter type1 = new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".jpeg") ||
                        f.getName().toLowerCase().endsWith(".jpg") ||
                        f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Image extensions";
            }
        };
        fileChooser.addChoosableFileFilter(type1);

        if(fileChooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            ImageIcon img = new ImageIcon(file.getAbsolutePath());
            GraphicViewPanel graphicViewPanel = (GraphicViewPanel) mainController.getView().getGraphicViewPanel();
            graphicViewPanel.setBasicImage(img);
            graphicViewPanel.paintComponent(graphicViewPanel.getGraphics());
        }
    }
}


class SaveGraphAction extends AbstractAction{
    public SaveGraphAction() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}