/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lt.kuzya.playlistmanager.management;

import lt.kuzya.playlistmanager.data.Song;
import lt.kuzya.playlistmanager.ui.MainFrame;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

/**
 *
 * @author Максим
 */
public class UIManager {
    private MainFrame frame;
    
    public UIManager(MainFrame frame) {
        this.frame = frame;
    }
    
    public void updateList() {
        List<Song> files = frame.manager.songs;
        
        DefaultListModel<String> model = (DefaultListModel<String>) frame.getList().getModel();
        
        model.clear();
        
        for (int i = 0; i<files.size(); i++) {
            model.addElement(files.get(i).name);
        }
    }
    
    public void updatePathText() {
        frame.updatePathText();
    }
    
    public void updateListSelection(int index) {
        frame.getList().setSelectedIndex(index);
    }
    
    public int getListIndex() {
        int index = frame.getList().getSelectedIndex();
        
        if (index != -1) {
            return index;
        } else {
            JOptionPane.showMessageDialog(frame, "No file selected in list.");
            return -1;
        }
    }
}
