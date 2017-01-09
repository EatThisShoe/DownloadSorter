/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import downloadsorter.model.FileOperation;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Eric
 */
public class SettingsManager {
    private static final Path _settingsPath = Paths.get("settings.txt");
    private ObservableList<FileOperation> settings;
    
    public SettingsManager() {
        SettingsReader reader = new SettingsReader(_settingsPath);
        settings = reader.readSettingsFile();
    }
    
    public void writeSettingsFile() {
        try {
            BufferedWriter writer = Files.newBufferedWriter(_settingsPath);
            settings.stream()
                    .forEach(fileOp ->  {writeFileOperation(fileOp, writer);});
            writer.close();
        } catch(Exception e) {System.err.format("IOException (creating settings writer): %s%n", e);}
    }
    
    private void writeFileOperation(FileOperation f, BufferedWriter writer) {
        try { writer.write(f.stringForFile() + "\n");}
        catch(Exception e) {System.err.format("IOException (writing line): %s%n", e);}
    }
    
    
    public void readSettingsFromGUI() {
        
    }
    
    public ObservableList<FileOperation> getSettings() {
        return settings;
    }
}
