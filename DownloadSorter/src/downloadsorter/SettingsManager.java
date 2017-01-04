/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.view.MainGUI;
import downloadsorter.model.FileOperation;
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
    private static final Path _settingsPath = Paths.get("settings.txt");
    private Settings settings;
    
    public SettingsManager() {
        SettingsReader reader = new SettingsReader(_settingsPath);
        settings = reader.readSettingsFile();
    }
    
    public void writeSettingsFile() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(_settingsPath);
            settings.getFilters().stream()
                    .forEach(filter ->  {writeFilter(filter, writer);});
            writer.close();
        } catch(Exception e) {System.err.format("IOException (creating settings writer): %s%n", e);}
    }
    
    void writeFilter(FileOperation f, BufferedWriter writer) {
        try { writer.write(f.stringForFile() + "\n");}
        catch(Exception e) {System.err.format("IOException (writing line): %s%n", e);}
    }
    
    
    public void readSettingsFromGUI() {
        
    }
    
    public Settings getSettings() {
        return settings;
    }
}
