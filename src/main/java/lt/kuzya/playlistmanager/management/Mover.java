/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lt.kuzya.playlistmanager.management;

import lt.kuzya.playlistmanager.data.Song;

/**
 *
 * @author Максим
 */
public class Mover {
    private Manager manager;
    
    public Mover(Manager manager) {
        this.manager = manager;
    }
    
    private void move(int offset) {
        int index = manager.frame.uiManager.getListIndex();
        if (index + offset < 0 || index + offset > manager.songs.size()-1) return;
        if (index == -1) return;
        Song element = manager.songs.get(index);
        manager.songs.remove(index);
        manager.songs.add(index+offset, element);
        
        manager.namer.nameSongs();
        manager.refreshList();
        manager.frame.uiManager.updateListSelection(index+offset);
    }
    
    public void moveUp() {
        move(-1);
    }
    
    public void moveDown() {
        move(1);
    }
}
