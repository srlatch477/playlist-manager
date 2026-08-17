/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lt.kuzya.playlistmanager.data;

import java.nio.file.Path;

/**
 *
 * @author Максим
 */
public class Song {
    public String name;
    public final Path originalPath;
    public boolean foreign;
    
    public Song(String name, Path originalPath) {
        this(name, originalPath, false);
    }
    
    public Song(String name, Path originalPath, boolean foreign) {
        this.name = name;
        this.originalPath = originalPath;
        this.foreign = foreign;
    }
}
