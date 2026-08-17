/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lt.kuzya.playlistmanager.management;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import lt.kuzya.playlistmanager.data.Song;
import lt.kuzya.playlistmanager.ui.MainFrame;

/**
 *
 * @author Максим
 */
public class Manager {
    public Path openedPath = null;
    public List<Song> songs = new ArrayList<>();
    public String convention = "%d. %s";
    
    public MainFrame frame;
    public Mover mover = new Mover(this);
    public FileManager fileM = new FileManager(this);
    public Namer namer = new Namer(this);
    public Applier applier = new Applier(this);
    
    private String[] conventions = new String[] {"%d. %s", "0%d. %s", "0%d - %s", "%s"};
    
    public Manager(MainFrame frame) {
        this.frame = frame;
    }
    
    public void refreshList() {
        frame.uiManager.updateList();
    }
    
    public String getFullPath(String filename) {
        return openedPath.toString() + filename;
    }
    
    public void setConvention(int index) {
        convention = conventions[index];
    }
    
    public void apply() {
        int answer = JOptionPane.showConfirmDialog(
            frame,
            "Do you want to apply changes?",
            "Question",
            JOptionPane.YES_NO_OPTION
        );
        if (answer == JOptionPane.YES_OPTION) {
            applier.applyChanges();
        }
    }
}
