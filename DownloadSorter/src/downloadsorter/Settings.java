/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadsorter;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author Eric
 */
public class Settings {
    public Path sourceFolder;
    
    public Settings(SettingsManager s) {
        sourceFolder = s.getSourceFolder();
    }
    
    public Settings() {
        sourceFolder = Paths.get("");
    }
}
