/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lt.kuzya.playlistmanager.management;

import lt.kuzya.playlistmanager.data.Song;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Максим
 */
public class FileManager {
    private Manager manager;
    
    public FileManager(Manager manager) {
        this.manager = manager;
    }
    
    public void getFiles() {
        try (Stream<Path> stream = Files.walk(manager.openedPath)) {
            List<Path> files = stream
                    .filter(Files::isRegularFile)
                    .filter(path -> {
                        try {
                            return !Files.isHidden(path);
                        } catch (IOException e) {
                            return false;
                        }
                    })
                    .collect(Collectors.toList());
            
            
            
            manager.songs.clear();

            for (int i = 0; i<files.size(); i++) {
                Path path = files.get(i);
                manager.songs.add(new Song(path.getFileName().toString(), path));
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void pickFiles() {
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setMultiSelectionEnabled(true);
        
        int response = fileChooser.showOpenDialog(manager.frame);
        
        if (response == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            
            for (File selectedFile : selectedFiles) {
                manager.songs.add(new Song(selectedFile.getName(), Path.of(selectedFile.getAbsolutePath()), true)); // foreign since this isnt from our working directory.
            }
        } else {
            JOptionPane.showMessageDialog(manager.frame, "No files selected.");
        }
        
        manager.namer.nameSongs();
        manager.refreshList();
    }
    
    public void selectFolder() {
        JFileChooser fileChooser = new JFileChooser();
        
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        int response = fileChooser.showOpenDialog(manager.frame);
        
        if (response == JFileChooser.APPROVE_OPTION) {
            manager.openedPath = Paths.get(fileChooser.getSelectedFile().getAbsolutePath());
            manager.frame.uiManager.updatePathText();
            int answer = JOptionPane.showConfirmDialog(
                manager.frame,
                "Do you want to load the files from that folder?",
                "Question",
                JOptionPane.YES_NO_OPTION
            );
            if (answer == JOptionPane.YES_OPTION) {
                getFiles();
            }
        } else {
            JOptionPane.showMessageDialog(manager.frame, "No folder selected.");
        }
        
        manager.namer.nameSongs();
        manager.refreshList();
    }
}
