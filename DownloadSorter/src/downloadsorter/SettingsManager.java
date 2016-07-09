/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 *
 * @author Eric
 */
public class SettingsManager {
    static final Path _settingsPath = Paths.get("settings.txt").toAbsolutePath();
    private MainGUI GUI;
    private Path sourceFolder;
    
    SettingsManager() {
        GUI = new MainGUI(this);
        readSettingsFile();
    }
    
    public final void readSettingsFile() {
        if (Files.exists(_settingsPath)) {
            try {
                BufferedReader reader = Files.newBufferedReader(_settingsPath);
                String setting = null;

                if ((setting = reader.readLine()) != null) {
                    sourceFolder = Paths.get(setting);
                }

            } catch(Exception e) {System.err.format("IOException: %s%n", e);}
        } else { //defaults
            System.out.println("settings file not found");
            //sourceFolder = Paths.get("D:\\torrents");
        }
        
        
    }
    
    public void writeSettingsFile() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(_settingsPath, CREATE);
            
            writer.write(sourceFolder.toString());
            writer.newLine();
        } catch(Exception e) {System.err.format("IOException: %s%n", e);}
    }
    
    public void readSettingsFromGUI() {
        
    }
    
    public Settings getSettings() {
        Settings init = new Settings(this);
        return init;
    }

    /**
     * @return the sourceFolder
     */
    public Path getSourceFolder() {
        return sourceFolder;
    }

    /**
     * @param sourceFolder the sourceFolder to set
     */
    public void setSourceFolder(Path sourceFolder) {
        this.sourceFolder = sourceFolder;
    }

    /**
     * @return the GUI
     */
    public MainGUI getGUI() {
        return GUI;
    }

    /**
     * @param GUI the GUI to set
     */
    public void setGUI(MainGUI GUI) {
        this.GUI = GUI;
    }
}
