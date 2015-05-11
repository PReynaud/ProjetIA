package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.ui.FilePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Created by Pierre on 11/05/2015.
 */
public class FileController {
    private MainController mainController;

    public FileController(MainController mainController){
        this.mainController = mainController;

        FilePanel filePanel = (FilePanel) mainController.getView().getFilePanel();
        filePanel.getLoadGraphButton().addAction(new LoadGraphAction());
        filePanel.getLoadImageButton().addAction(new LoadImageAction());
        filePanel.getSaveGraphButton().addAction(new SaveGraphAction());
    }
}


class LoadGraphAction extends AbstractAction{
    public LoadGraphAction(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File file;
        JFileChooser fileChooser = new JFileChooser(new File("."));

        if(fileChooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        }
    }
}


class LoadImageAction extends AbstractAction{
    public LoadImageAction(){

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}


class SaveGraphAction extends AbstractAction{
    public SaveGraphAction() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}