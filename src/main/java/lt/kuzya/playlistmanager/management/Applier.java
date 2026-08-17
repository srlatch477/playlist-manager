/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lt.kuzya.playlistmanager.management;

import lt.kuzya.playlistmanager.data.Song;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Максим
 */
public class Applier {
    private Manager manager;
    
    public Applier(Manager manager) {
        this.manager = manager;
    }
    
    private void rename(Path origPath, String newName, boolean foreign) {
        Path destination = Path.of(manager.openedPath.toString() + '\\' + newName);
        
        try {
            if (foreign) {
                Files.copy(origPath, destination);
            } else {
                Files.move(origPath, destination, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void applyChanges() {
        if (manager.openedPath == null) {
            JOptionPane.showMessageDialog(manager.frame, "Please select a working directory!");
            return;
        }
        
        for (Song song : manager.songs) {
            rename(song.originalPath, song.name, song.foreign);
        }
        
        JOptionPane.showMessageDialog(manager.frame, "Wrote changes.");
    }
}
