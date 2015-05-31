package com.polytech4a.robocup.firebot.controller;

import com.polytech4a.robocup.firebot.ui.FilePanel;
import com.polytech4a.robocup.firebot.ui.GraphicControlPanel;
import com.polytech4a.robocup.firebot.ui.GraphicViewPanel;
import com.polytech4a.robocup.graph.model.Graph;
import com.polytech4a.robocup.graph.utils.Load;
import com.polytech4a.robocup.graph.utils.MalformGraphException;
import com.polytech4a.robocup.graph.utils.Save;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Pierre on 11/05/2015.
 * Controller of the buttons that allow the loading and saving of files
 */
public class FileController {
    private static final Logger logger = Logger.getLogger(FileController.class);

    private MainController mainController;

    public FileController(MainController mainController){
        this.mainController = mainController;

        FilePanel filePanel = (FilePanel) mainController.getView().getFilePanel();
        filePanel.getLoadGraphButton().addAction(new LoadGraphAction(mainController));
        filePanel.getLoadImageButton().addAction(new LoadImageAction(mainController));
        filePanel.getSaveGraphButton().addAction(new SaveGraphAction(mainController));
    }
}

/**
 * Action of showing a file chooser dialog and loading a graph from a xml file
 */
class LoadGraphAction extends AbstractAction{
    private static final Logger logger = Logger.getLogger(FileController.class);

    MainController mainController;
    public LoadGraphAction(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        graphicControlPanel.resetColorOfButtons();

        File file;
        JFileChooser fileChooser = new JFileChooser(new File("."));

        if(fileChooser.showOpenDialog(null)== JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            try {
                Graph loadedGraph = new Load().loadGraph(file);
                mainController.setGraph(loadedGraph);
                mainController.transformModelGraphToView();
            } catch (IOException e1) {
                logger.error("Error in the loading of the graph");
            } catch (ParserConfigurationException e1) {
                logger.error("Error in the loading of the graph: " + e1.getMessage());
            } catch (SAXException e1) {
                logger.error("Error in the loading of the graph: " + e1.getMessage());
            } catch (MalformGraphException e1) {
                logger.error("Error in the loading of the graph: " + e1.getMessage());
            }
        }
    }
}

/**
 * Acttion of showing a file chooser dialog and load an image in the background
 * If the image is smaller, it will be not resized, otherwise it will be adjusted to the window
 */
class LoadImageAction extends AbstractAction{
    private static final Logger logger = Logger.getLogger(FileController.class);

    MainController mainController;
    public LoadImageAction(MainController mainController){
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        graphicControlPanel.resetColorOfButtons();

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
            Image img;
            try {
                img = ImageIO.read(file);
                GraphicViewPanel graphicViewPanel = (GraphicViewPanel) mainController.getView().getGraphicViewPanel();
                graphicViewPanel.setBasicImage(img);
                graphicViewPanel.paintComponent(graphicViewPanel.getGraphics());
            } catch (IOException e1) {
                logger.error("Error in the loading of the image");
            }
        }
    }
}

/**
 * Acttion of showing a file chooser dialog and save the current graph in a xml file
 */
class SaveGraphAction extends AbstractAction{
    private static final Logger logger = Logger.getLogger(FileController.class);

    MainController mainController;
    public SaveGraphAction(MainController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraphicControlPanel graphicControlPanel = (GraphicControlPanel) mainController.getView().getGraphicControlPanel();
        graphicControlPanel.resetColorOfButtons();

        File file;
        JFileChooser fileChooser = new JFileChooser(new File("."));

        if(fileChooser.showSaveDialog(null)== JFileChooser.APPROVE_OPTION) {
            file = new File(fileChooser.getSelectedFile() + ".xml");
            new Save().saveGraph(mainController.getGraph(), file);
        }
    }
}