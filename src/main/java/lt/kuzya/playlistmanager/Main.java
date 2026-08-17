/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package lt.kuzya.playlistmanager;

import lt.kuzya.playlistmanager.ui.MainFrame;
import com.formdev.flatlaf.FlatDarkLaf;

/**
 *
 * @author Максим
 */
public class Main {

    public static void main(String[] args) {
        // init flatlaf
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            System.out.println("Failed to initialize FlatLaf");
        }
        
        MainFrame frame = new MainFrame();
        frame.setVisible(true);
    }
}
