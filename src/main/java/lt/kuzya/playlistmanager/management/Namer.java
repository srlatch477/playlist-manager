/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lt.kuzya.playlistmanager.management;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Максим
 */
public class Namer {
    private Manager manager;
    
    public Namer(Manager manager) {
        this.manager = manager;
    }
    
    public void nameSongs() {
        for (int i = 0; i<manager.songs.size(); i++) {
            manager.songs.get(i).name = nameSong(i);
        }
    }
    
    public String nameSong(int index) {
        String name = manager.songs.get(index).name;
            
        String extension = "";
        int dotIndex = name.lastIndexOf('.');

        if (dotIndex == -1) {
            dotIndex = name.length();
        }
        
        String cleanName;

        if (dotIndex > 0 && (name.length() - dotIndex) <= 4) {
            extension = name.substring(dotIndex);
            cleanName = name.substring(0, dotIndex);
        } else {
            cleanName = name;
        }

        int trackNumber = index + 1;
        Pattern numPrefixPattern = Pattern.compile("^(\\d+)[\\.\\-_\\s]+(.*)$");
        Matcher matcher = numPrefixPattern.matcher(cleanName);

        String songTitle;

        if (matcher.matches()) {
            songTitle = matcher.group(2).trim();
        } else {
            songTitle = cleanName;
        }

        String newName;
        if (manager.convention.equals("%s")) {
            newName = String.format(manager.convention, songTitle);
        } else {
            newName = String.format(manager.convention, trackNumber, songTitle);
        }
        
        return newName + extension;
    }
}
