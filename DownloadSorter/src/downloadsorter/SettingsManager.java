/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.Filters.Filter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.CREATE;

/**
 *
 * @author Eric
 */
public class SettingsManager {
    static final Path _settingsPath = Paths.get("settings.txt");
    private MainGUI GUI;
    private Settings settings;
    
    public SettingsManager() {
        GUI = new MainGUI(this);
        SettingsReader reader = new SettingsReader(_settingsPath);
        settings = reader.readSettingsFile();
    }
    
    public void writeSettingsFile() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(_settingsPath, CREATE);
            settings.getFilters().stream()
                    .forEach(filter ->  {writeFilter(filter, writer);});
        } catch(Exception e) {System.err.format("IOException (creating settings writer): %s%n", e);}
    }
    
    void writeFilter(Filter f, BufferedWriter writer) {
        try { writer.write(f.toString() + "\n");}
        catch(Exception e) {System.err.format("IOException (writing line): %s%n", e);}
    }
    
    
    public void readSettingsFromGUI() {
        
    }
    
    public Settings getSettings() {
        Settings init = new Settings(this);
        return init;
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
